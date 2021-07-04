package com.java.view;


import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;


public class MainFrame extends JFrame{
    //定义菜单栏
    private JMenuBar menuBar;
    //定义菜单栏中的菜单
    private JMenu menu1;
    private JMenu menu2;
    private JMenu menu3;
    private JMenu menu4;
    private JMenu menu5;
    private JMenu menu6;
    private JMenu menu7;
    //定义菜单中的菜单子项
    private JMenuItem menuItem1 = new JMenuItem("进货");
    private JMenuItem menuItem2 = new JMenuItem("销售");
    JMenuItem menuItem3;
    JMenuItem menuItem4;
    JMenuItem menuItem5;
    JMenuItem menuItem6;
    JMenuItem menuItem7;
    JMenuItem menuItem8;
    JMenuItem menuItem9;
    JMenuItem menuItem10;
    JMenuItem menuItem11;
    JMenuItem menuItem12;
    JMenuItem menuItem13;

    //定义工具栏
    private JToolBar toolBar;
    //定义工具栏中的菜单
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    JButton button7;
    JButton button8;
    JButton button9;

    //初始化页面窗口
    private void init(){
        //定义菜单栏
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //定义工具栏
        toolBar = new JToolBar(SwingConstants.HORIZONTAL);
        button1 = new JButton("药品进货");
        toolBar.add(button1);
        button2 = new JButton("药品销售");
        toolBar.add(button2);
        button3 = new JButton("库存盘点");
        toolBar.add(button3);
        button4 = new JButton("价格调整");
        toolBar.add(button4);
        button5 = new JButton("药品查询");
        toolBar.add(button5);
        button6 = new JButton("药品资料管理");
        toolBar.add(button6);
        button7 = new JButton("客户资料管理");
        toolBar.add(button7);
        button8 = new JButton("供应商资料管理");
        toolBar.add(button8);
        button9 = new JButton("退出系统");
        toolBar.add(button9);


        BackPanel backPanel = new BackPanel("F:\\java\\PharmaceuticalSalesMS\\src\\main\\java\\com\\java\\img\\center.jpg");
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(toolBar, BorderLayout.NORTH);
        //panel.add(backPanel,BorderLayout.CENTER);
        setContentPane(panel);

        //定义菜单栏中的订单选项
        menu1 = new JMenu("进货管理");
        menu1.setFont(new Font("宋体",Font.BOLD,15));
        menu1.add(menuItem1);

        menu2 = new JMenu("销售管理");
        menu2.setFont(new Font("宋体",Font.BOLD,15));
        menu2.add(menuItem2);

        menu3 = new JMenu("库存管理");
        menu3.setFont(new Font("宋体",Font.BOLD,15));
        menuItem6 = new JMenuItem("库存盘点");
        menuItem7 = new JMenuItem("价格调整");
        menu3.add(menuItem6);
        menu3.add(menuItem7);

        menu4 = new JMenu("信息查询");
        menu4.setFont(new Font("宋体",Font.BOLD,15));
        menuItem8 = new JMenuItem("药品查询");
        menuItem9 = new JMenuItem("销售查询");
        menuItem11 = new JMenuItem("销售排行榜");
        menuItem12 = new JMenuItem("打印报表");
        menu4.add(menuItem8);
        menu4.add(menuItem9);
        menu4.add(menuItem11);
        menu4.add(menuItem12);

        menu5 = new JMenu("基本资料");
        menu5.setFont(new Font("宋体",Font.BOLD,15));
        menuItem3 = new JMenuItem("药品资料管理");
        menuItem4 = new JMenuItem("客户资料管理");
        menuItem5 = new JMenuItem("供应商资料管理");
        menu5.add(menuItem3);
        menu5.add(menuItem4);
        menu5.add(menuItem5);


        menu6 = new JMenu("系统维护");
        menu6.setFont(new Font("宋体",Font.BOLD,15));
        menuItem10 = new JMenuItem("数据库备份与恢复");
        menu6.add(menuItem10);
        menuItem13 = new JMenuItem("用户信息管理");
        menu6.add(menuItem13);

        menu7 = new JMenu("帮助");
        menu7.setFont(new Font("宋体",Font.BOLD,15));
        //将菜单选项加入到菜单栏
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        menuBar.add(menu4);
        menuBar.add(menu5);
        menuBar.add(menu6);
        menuBar.add(menu7);


        this.setTitle("医药销售管理系统");
        this.setSize(900,650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


    }

    //进货管理页面
    private void buyFrame(){
        MedicineBuyFrame medicineBuyFrame = new MedicineBuyFrame();
        medicineBuyFrame.setSize(460,300);
        medicineBuyFrame.setVisible(true);
        try {
            medicineBuyFrame.setSelected(true);
        } catch (PropertyVetoException e1) {
            e1.printStackTrace();
        }
        getContentPane().add(medicineBuyFrame);
    }

    //销售管理页面
    private void sellFrame(){
        SellFrame sellFrame = new SellFrame();
        sellFrame.setSize(460,300);
        sellFrame.setVisible(true);
        try {
            sellFrame.setSelected(true);
        } catch (PropertyVetoException e1) {
            e1.printStackTrace();
        }
        getContentPane().add(sellFrame);
    }

    //库存盘点页面
    private void inventoryCheckFrame(){
        InventoryCheckFrame inventoryCheckFrame = new InventoryCheckFrame();
        inventoryCheckFrame.setSize(460,300);
        inventoryCheckFrame.setVisible(true);
        try {
            inventoryCheckFrame.setSelected(true);
        } catch (PropertyVetoException e1) {
            e1.printStackTrace();
        }

        getContentPane().add(inventoryCheckFrame);
    }

    //价格调整页面
    private void adjustPriceFrame(){
        PriceAdjustFrame priceAdjustFrame = new PriceAdjustFrame();
        priceAdjustFrame.setSize(460,300);
        priceAdjustFrame.setVisible(true);
        try {
            priceAdjustFrame.setSelected(true);
        } catch (PropertyVetoException e1) {
            e1.printStackTrace();
        }
        getContentPane().add(priceAdjustFrame);
    }

    //药品信息查询页面
    private void infoQueryFrame(){
        MedicineInfoQueryFrame medicineInfoQueryFrame = new MedicineInfoQueryFrame();
        medicineInfoQueryFrame.setSize(460,300);
        medicineInfoQueryFrame.setVisible(true);
        try {
            medicineInfoQueryFrame.setSelected(true);
        } catch (PropertyVetoException e1) {
            System.out.println(e1.getMessage());
        }
        getContentPane().add(medicineInfoQueryFrame);
    }

    //药品资料管理
    private void MedicineManage(){
        MedicineInfoManageFrame medicineInfoManageFrame = new MedicineInfoManageFrame();
        medicineInfoManageFrame.setSize(new Dimension(460,300));
        medicineInfoManageFrame.setVisible(true);
        try {
            medicineInfoManageFrame.setSelected(true);
        } catch (PropertyVetoException e1) {
            e1.printStackTrace();
        }
        getContentPane().add(medicineInfoManageFrame);
    }

    //客户资料管理
    private void customerManage(){
        CustomerManageFrame customerManageFrame = new CustomerManageFrame();
        customerManageFrame.setSize(460,300);
        customerManageFrame.setVisible(true);
        try {
            customerManageFrame.setSelected(true);
        } catch (PropertyVetoException e1) {
            e1.printStackTrace();
        }
        getContentPane().add(customerManageFrame);
    }

    //供应商资料管理
    private void supplierManage(){
        SupplierManageFrame supplierManageFrame = new SupplierManageFrame();
        supplierManageFrame.setSize(460,300);
        supplierManageFrame.setVisible(true);

        try {
            supplierManageFrame.setSelected(true);
        } catch (PropertyVetoException e1) {
            e1.printStackTrace();
        }

        getContentPane().add(supplierManageFrame);
    }

    //销售管理页面
    private void sellManageFrame() {
        SellManageFrame sellManageFrame = new SellManageFrame();
        sellManageFrame.setSize(460,300);
        sellManageFrame.setVisible(true);
        try {
            sellManageFrame.setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        getContentPane().add(sellManageFrame);
    }

    //销售排行榜
    private void sellRankingFrame(){
        new Bar3D();

    }

    //备份与回复页面
    private void backUpAndRestore()  {
        BackupAndRestore backupAndRestore = new BackupAndRestore();
        backupAndRestore.setSize(460,300);
        backupAndRestore.setVisible(true);
        try {
            backupAndRestore.setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        getContentPane().add(backupAndRestore);
    }

    //打印报表
    private void print(){
        PrintFrame printFrame = new PrintFrame();
        printFrame.setSize(460,300);
        printFrame.setVisible(true);
        try {
            printFrame.setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        getContentPane().add(printFrame);
    }

    //管理用户的信息
    private void manageUser(){
        UserManageFrame userManageFrame = new UserManageFrame();
        userManageFrame.setSize(460,300);
        userManageFrame.setVisible(true);
        try {
            userManageFrame.setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        getContentPane().add(userManageFrame);
    }


    //添加监听器
    private void addAction(){
        //进货管理监听事件
        button1.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                buyFrame();
            }
        });

        //销售管理
        button2.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                sellFrame();
            }
        });

        //库存盘点监听事件
        button3.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                inventoryCheckFrame();
            }
        });

        //药品价格调整
        button4.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                adjustPriceFrame();
            }
        });

        //商品信息查询监听事件
        button5.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                infoQueryFrame();
            }
        });

        //药品信息管理监听事件
        button6.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                MedicineManage();
            }
        });

        //客户信息管理监听事件
        button7.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                customerManage();
            }
        });

        //供应商信息管理监听事件
        button8.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                supplierManage();
            }
        });


        //退出系统的监听器
        button9.addActionListener(new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                new LoginFrame();
                dispose();
            }
        });
    }

    /**
     * 菜单子项监听事件
     * */
    private void addItemAction(){
        //进货
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyFrame();
            }
        });

        //销售
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sellFrame();
            }
        });

        //药品资料管理
        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicineManage();
            }
        });

        //客户资料管理
        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerManage();
            }
        });

        //供应商资料管理
        menuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supplierManage();
            }
        });

        //库存盘点
        menuItem6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventoryCheckFrame();
            }
        });

        //价格调整
        menuItem7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adjustPriceFrame();
            }
        });

        //药品查询
        menuItem8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoQueryFrame();
            }
        });

        //销售查询
        menuItem9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sellManageFrame();
            }
        });

        //备份与恢复
        menuItem10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backUpAndRestore();
            }
        });

        //销售排行榜
        menuItem11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sellRankingFrame();
            }
        });

        //打印报表
        menuItem12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                print();
            }
        });

        //管理用户信息
        menuItem13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageUser();
            }
        });
    }

    public MainFrame(){
        init();
        addAction();
        addItemAction();
    }


    public static void main(String[] args) {
        new MainFrame();
    }
}




