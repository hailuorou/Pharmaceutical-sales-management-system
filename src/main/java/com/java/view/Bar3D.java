package com.java.view;

import com.java.domain.SellDetail;
import com.java.domain.SellRank;
import com.java.service.SellService;
import com.java.service.impl.SellServiceImpl;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Bar3D {
    private SellService sellService = new SellServiceImpl();

    public Bar3D() {
        super();

        //创建主题样式
        StandardChartTheme mChartThem = new StandardChartTheme("CN");
        //设置图表标题
        mChartThem.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));
        //设置轴向字体
        mChartThem.setLargeFont(new Font("宋体", Font.PLAIN, 15));
        //设置图例字体
        mChartThem.setRegularFont(new Font("宋体", Font.PLAIN, 15));
        //应用主题
        ChartFactory.setChartTheme(mChartThem);
        //创建图表
        CategoryDataset mDataset = GetDataset();
        JFreeChart mChart = ChartFactory.createBarChart3D(
                "销售排行榜",
                "药品种类",
                "数量",
                mDataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                true
        );
        //设置内部属性
        CategoryPlot mPlot = (CategoryPlot) mChart.getPlot();
        //设置纵轴和横轴
        CategoryAxis mDomainAxis = mPlot.getDomainAxis();
        //设置柱状图举例x轴最左端的举例百分比10%
        mDomainAxis.setUpperMargin(0.1);
        //柱体显示数值
        BarRenderer mRenderer = new BarRenderer();
        mRenderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        mRenderer.setItemLabelFont(new Font("宋体", Font.PLAIN, 15));
        mRenderer.setItemLabelsVisible(true);
        mPlot.setRenderer(mRenderer);
        //图表显示
        ChartFrame mChartFrame = new ChartFrame("医药销售排行榜", mChart);
        mChartFrame.pack();
        mChartFrame.setVisible(true);

    }

    public  CategoryDataset GetDataset() {
        DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
        List<SellRank> sellRanks = sellService.findSellRank();
        int len = sellRanks.size();
        if(len > 5){
            len = 5;
        }

        //对销售信息按销售数量进行排序
        Collections.sort(sellRanks, new Comparator<SellRank>() {
            @Override
            public int compare(SellRank o1, SellRank o2) {
                if(o1.getNumber() - o2.getNumber() <0){
                    return 0;
                }else{
                    return -1;
                }
            }
        });

        //输出销售量排前五的药品及数量
        for(int i=0; i<len; i++) {
            SellRank sell = sellRanks.get(i);
            mDataset.addValue(sell.getNumber(), "超超医药销售有限公司", sell.getMname());
        }


        return mDataset;

    }

    public static void main(String[] args) {
        new Bar3D();
    }
}
