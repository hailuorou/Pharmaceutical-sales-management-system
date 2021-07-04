package com.java.view;

import javax.swing.*;


public class MedicineInfoManageFrame extends JInternalFrame {
    //面板选项卡
    JTabbedPane tabbedPane;

    public MedicineInfoManageFrame(){

        this.setClosable(true);
        tabbedPane = new JTabbedPane();

        MedicineInfoManageAddPanel medicineInfoManageAddPanel = new MedicineInfoManageAddPanel();
        MedicineInfoManageUpdatePanel medicineInfoManageUpdatePanel = new MedicineInfoManageUpdatePanel();

        tabbedPane.addTab("药品资料添加",null,medicineInfoManageAddPanel,"添加药品资料");
        tabbedPane.addTab("药品资料修改",null,medicineInfoManageUpdatePanel,"修改药品资料");

        getContentPane().add(tabbedPane);
    }
}
