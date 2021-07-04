package com.java.view;

import javax.swing.*;

public class SupplierManageFrame extends JInternalFrame{
    //选项卡面板
    JTabbedPane tabbedPane;


    public SupplierManageFrame(){
        this.setVisible(true);
        this.setClosable(true);
        this.setTitle("供应商管理");

        //设置选项卡面板
        tabbedPane = new JTabbedPane();
        SupplierManageAddPanel smap = new SupplierManageAddPanel();
        tabbedPane.addTab("供应商信息添加",null,smap,"增加供应商信息");
        SupplierManageUpdatePanel smup = new SupplierManageUpdatePanel();
        tabbedPane.addTab("供应商信息修改",null,smup,"修改供应商信息");

        getContentPane().add(tabbedPane);
        pack();
    }
}
