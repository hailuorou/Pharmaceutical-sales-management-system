package com.java.view;

import com.java.domain.SellDetail;
import com.java.service.SellService;
import com.java.service.impl.SellServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class SellManageFrame extends JInternalFrame {
    private JTable table;
    private SellService sellService = new SellServiceImpl();
    private DefaultTableModel dfm;


    public SellManageFrame(){
        super();
        //设置可别关闭
        setClosable(true);
        //设置面板布局
        getContentPane().setLayout(new GridBagLayout());
        setBounds(100, 100, 650, 375);// 设置商品查询内部窗体的位置和宽高

        //表格模型
        table = new JTable();
        table.setEnabled(false);
        //不自动调整表格宽度使用滚条
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //获取默认表格模型
        dfm = (DefaultTableModel) table.getModel();
        //表头
        String[] tableHead = {"销售单号","客户名称","药品名称","供应商","类别","售价","数量","总价","产地","生产批号","包装规格","生产日期","有效期"};
        dfm.setColumnIdentifiers(tableHead);

        final JScrollPane scrollPane = new JScrollPane(table);
        final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
        gridBagConstraints_6.weighty = 1.0;
        gridBagConstraints_6.anchor = GridBagConstraints.NORTH;
        gridBagConstraints_6.insets = new Insets(0, 2, 0, 2);
        gridBagConstraints_6.fill = GridBagConstraints.BOTH;
        gridBagConstraints_6.gridwidth = 6;
        gridBagConstraints_6.gridy = 0;
        gridBagConstraints_6.gridx = 0;
        gridBagConstraints_6.gridwidth = 3;
        gridBagConstraints_6.ipadx = 350;
        getContentPane().add(scrollPane, gridBagConstraints_6);

        init();
    }

    /**
     * 显示销售信息
     * */
    private void init(){
        List<SellDetail> sellDetails = sellService.findAllSellDetail();
        if(!sellDetails.isEmpty()){
            for (SellDetail sellDetail : sellDetails) {
                Vector<String> v = new Vector<String>();
                v.add(sellDetail.getXsid());
                v.add(sellDetail.getCname());
                v.add(sellDetail.getMname());
                v.add(sellDetail.getSname());
                v.add(sellDetail.getCategory());
                v.add(String.valueOf(sellDetail.getSellPrice()));
                v.add(String.valueOf(sellDetail.getNumber()));
                v.add(String.valueOf(sellDetail.getSum()));
                v.add(sellDetail.getAddr());
                v.add(sellDetail.getProBatch());
                v.add(sellDetail.getPack());
                v.add(sellDetail.getProductDate());
                v.add(sellDetail.getValidity());
                dfm.addRow(v);
            }
        }
    }

}
