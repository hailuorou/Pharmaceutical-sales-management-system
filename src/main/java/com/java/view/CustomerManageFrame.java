package com.java.view;

import javax.swing.*;


public class CustomerManageFrame extends JInternalFrame {
    //创建选项卡面板
    JTabbedPane tabbedPane;

    public CustomerManageFrame(){
        //可以图标化
        this.setIconifiable(true);
        // 可以关闭
        this.setClosable(true);
        this.setTitle("客户信息管理");

        //设置选项卡面板内容
        tabbedPane = new JTabbedPane();
        CustomerManageAddPanel customerManageAddPanel = new CustomerManageAddPanel();
        tabbedPane.addTab("客户信息添加",null,customerManageAddPanel);
        CustomerManageUpdatePanel customerManageUpdatePanel = new CustomerManageUpdatePanel();
        tabbedPane.addTab("客户信息修改",null,customerManageUpdatePanel);

        getContentPane().add(tabbedPane);

        pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new CustomerManageFrame();
    }
}
