package com.java.view;

import com.java.domain.BuyDetail;
import com.java.domain.SellDetail;
import com.java.service.BuyService;
import com.java.service.SellService;
import com.java.service.impl.BuyServiceImpl;
import com.java.service.impl.SellServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;
import java.util.List;

public class PrintFrame extends JInternalFrame {
    JTextArea textArea = null;
    JComboBox comboBox = null;
    JButton printButton = null;
    JButton queryButton = null;
    private BuyService bservice = new BuyServiceImpl();
    private SellService sservice = new SellServiceImpl();

    public PrintFrame(){
        super();
        getContentPane().setLayout(new GridBagLayout());
        setBounds(100, 100, 650, 375);// 设置商品查询内部窗体的位置和宽高

        JLabel label = new JLabel("请选择需要打印的销售订单号或入库单号：");
        setupComponet(label,0,1,4,400,true);

        comboBox = new JComboBox();
        setupComponet(comboBox,0,2,4,400,true);
        init();

        textArea = new JTextArea();
        final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
        gridBagConstraints_6.weighty = 1.0;
        gridBagConstraints_6.anchor = GridBagConstraints.NORTH;
        gridBagConstraints_6.insets = new Insets(0, 2, 0, 2);
        gridBagConstraints_6.fill = GridBagConstraints.BOTH;
        gridBagConstraints_6.gridwidth = 6;
        gridBagConstraints_6.gridy = 3;
        gridBagConstraints_6.gridx = 0;
        getContentPane().add(textArea, gridBagConstraints_6);

        queryButton = new JButton("查询");
        setupComponet(queryButton,2,5,1,0,false);

        printButton = new JButton("打印");
        setupComponet(printButton,4,5,1,0,false);


        //查询的监听事件
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //先获取选中的订单号
                String id = String.valueOf(comboBox.getSelectedItem());
                if(id.charAt(0) == 'R'){
                    System.out.println("入库订单号");
                    printBuyInfo(id);
                }else{
                    System.out.println("销售订单号");
                    printSellInfo(id);
                }
            }
        });


        //打印的监听事件
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrinterJob job = PrinterJob.getPrinterJob();

                if(!job.printDialog())
                    return;

                job.setPrintable(new Printable() {
                    @Override
                    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                        if(pageIndex > 0)
                            return Printable.NO_SUCH_PAGE;
                        int x = (int) pageFormat.getImageableX();
                        int y = (int) pageFormat.getImageableY();
                        Graphics2D g2 = (Graphics2D)graphics;
                        g2.setStroke(new BasicStroke(4.0F));
                        g2.setColor(Color.blue);

                        String content = textArea.getText();

                        String[] strings = content.split("\n");
                        for(int i=0;i<strings.length;i++){
                            g2.drawString(strings[i],x+100,y+30*(i+1));
                        }


                        g2.setColor(Color.black);
                        return Printable.PAGE_EXISTS;
                    }
                });
                job.setJobName("打印报表");
                try {
                    job.print();
                } catch (PrinterException e1) {
                    e1.printStackTrace();
                }
            }
        });


        setClosable(true);
        this.setVisible(true);


    }

    /**
     * 显示入库订单的详细信息
     * */
    private void printBuyInfo(String id){
        BuyDetail buyDetail = bservice.findBuyDetailById(id);
        String str = "入库订单号："+buyDetail.getRkid()+"\n"+
                     "药品名称："+buyDetail.getMname()+"\n"+
                     "供应商名称："+buyDetail.getSname()+"\n"+
                     "药品类别："+buyDetail.getCategory()+"\n"+
                     "药品产地："+buyDetail.getAddr()+"\n"+
                     "包装规格："+buyDetail.getPack()+"\n"+
                     "生产批号："+buyDetail.getProBatch()+"\n"+
                     "进价："+buyDetail.getBuyPrice()+"￥\n"+
                     "生产日期："+buyDetail.getProductDate()+"\n"+
                     "有效期："+buyDetail.getValidity()+"\n"+
                     "入库数量："+buyDetail.getNumber()+"\n"+
                     "总金额："+buyDetail.getSum()+"￥";
        textArea.setText(str);
    }

    /**
     * 显示销售订单信息
     * */
    private void printSellInfo(String id){
        SellDetail sellDetail = sservice.findSellDetailById(id);
        String str = "销售订单号："+sellDetail.getXsid()+"\n"+
                     "药品名称：" + sellDetail.getMname()+"\n"+
                     "客户名称："+sellDetail.getCname()+"\n"+
                     "药品产地："+sellDetail.getAddr()+"\n"+
                     "药品类别："+sellDetail.getCategory()+"\n"+
                     "药品产地："+sellDetail.getAddr()+"\n"+
                     "包装规格："+sellDetail.getPack()+"\n"+
                     "生产批号："+sellDetail.getProBatch()+"\n"+
                     "售价："+sellDetail.getSellPrice()+"￥\n"+
                     "生产日期："+sellDetail.getProductDate()+"\n"+
                     "有效期："+sellDetail.getValidity()+"\n"+
                     "入库数量："+sellDetail.getNumber()+"\n"+
                     "总金额："+sellDetail.getSum()+"￥";
        textArea.setText(str);

    }

    //初始化下拉列表获取订单号或入库单号
    private void init(){
        //获取销售订单号
        List<SellDetail> sellDetails = sservice.findAllSellDetail();
        if(!sellDetails.isEmpty()) {
            for (SellDetail sellDetail : sellDetails) {
                comboBox.addItem(sellDetail.getXsid());
            }
        }

        //获取入库订单号
        List<BuyDetail> buyDetails = bservice.findAllBuyDetail();
        if(!buyDetails.isEmpty()) {
            for (BuyDetail buyDetail : buyDetails) {
                comboBox.addItem(buyDetail.getRkid());
            }
        }

    }

    // 设置组件位置并添加到容器中
    private void setupComponet(JComponent component, int gridx, int gridy, int gridwidth, int ipadx, boolean fill) {
        final GridBagConstraints gridBagConstrains = new GridBagConstraints();// 创建网格限制对象
        gridBagConstrains.gridx = gridx;// 设置组件位于网格的横向索引为gridx
        gridBagConstrains.gridy = gridy;// 设置组件位于网格的纵向索引为gridy
        gridBagConstrains.insets = new Insets(5, 1, 3, 1);// 组件彼此的间距
        if (gridwidth > 1)// 组件横跨网格数大于1
            gridBagConstrains.gridwidth = gridwidth;// 设置组件横跨网格数为gridwidth
        if (ipadx > 0)// 组件横向填充的大小大于0
            gridBagConstrains.ipadx = ipadx;// 设置组件横向填充的大小
        if (fill)
            gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;// 组件水平扩大以占据空白区域
        getContentPane().add(component, gridBagConstrains);// 添加组件
    }
}
