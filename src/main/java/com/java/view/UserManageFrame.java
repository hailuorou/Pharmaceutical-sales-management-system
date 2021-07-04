package com.java.view;

import javax.swing.*;

public class UserManageFrame extends JInternalFrame {
    //创建选项卡面板
    JTabbedPane tabbedPane;

    public UserManageFrame(){
        //可以图标化
        this.setIconifiable(true);
        // 可以关闭
        this.setClosable(true);
        this.setTitle("用户信息管理");

        //设置选项卡面板内容
        tabbedPane = new JTabbedPane();
        UserManageAddPanel userManageAddPanel = new UserManageAddPanel();
        tabbedPane.addTab("用户信息添加",null,userManageAddPanel);
        UserManageUpdatePanel userManageUpdatePanel = new UserManageUpdatePanel();
        tabbedPane.addTab("用户信息修改",null,userManageUpdatePanel);

        getContentPane().add(tabbedPane);

        pack();
        this.setVisible(true);
    }

}
