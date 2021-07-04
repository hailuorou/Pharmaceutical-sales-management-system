package com.java.view;

import com.java.domain.ResultInfo;
import com.java.domain.User;
import com.java.service.UserService;
import com.java.service.impl.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class UserManageUpdatePanel extends JPanel {
    //用户名称
    private JTextField userName;
    //用户密码
    private JTextField password;
    //联系方式
    private JTextField telText;
    //名称下来框
    private JComboBox nameBox;
    //用户操作对象
    private UserService userService = new UserServiceImpl();

    public UserManageUpdatePanel(){
        super();
        this.setBounds(10,10,460,300);
        this.setLayout(new GridBagLayout());

        //设置面板格式
        final JLabel name = new JLabel();
        name.setText("客户名称");
        setupComponet(name,0,0,1,0,false);
        userName = new JTextField();
        userName.setEditable(false);
        setupComponet(userName,1,0,3,350,true);


        final JLabel people = new JLabel();
        people.setText("新密码");
        setupComponet(people,0,1,1,0,false);
        password = new JTextField();
        setupComponet(password,1,1,3,350,true);

        final JLabel tel = new JLabel("联系电话");
        setupComponet(tel,0,2,1,0,false);
        telText = new JTextField();
        setupComponet(telText,1,2,3,350,true);

        final JLabel label = new JLabel("用户");
        setupComponet(label,0,8,1,0,false);
        nameBox = new JComboBox();
        jComboBoxInit();
        setupComponet(nameBox,1,8,2,200,false);

        final JButton update = new JButton();
        update.setText("修改");
        setupComponet(update,3,8,1,0,false);

        final JButton delete = new JButton();
        delete.setText("删除");
        setupComponet(delete,4,8,1,0,false);

        //处理下拉列表选中的事件
        nameBox.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                // 只处理选中的状态
                String name = String.valueOf(nameBox.getSelectedItem());
                User user = userService.findUserByName(name);
                userName.setText(user.getUsername());
                password.setText("");
                telText.setText(user.getTel());
            }
        });

        //修改按钮事件
        update.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //获取文本框内的用户信息封装为USER对象
                String name = userName.getText();
                String p =password.getText();
                String tel = telText.getText();
                //新建一个customer对象
                User user = new User();
                user.setUsername(name);
                user.setPassword(p);
                user.setTel(tel);

                ResultInfo info = userService.update(user);

                JOptionPane.showMessageDialog(null,info.getMsg());


            }
        });

        /**
         * 删除按钮的事件
         * */
        delete.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //获取客户的名称
                String name = userName.getText();
                //根据客户的名称删除客户的信息
                ResultInfo resultInfo = userService.deleteUserByName(name);

                JOptionPane.showMessageDialog(null,resultInfo.getMsg());
            }
        });
    }

    /**
     * 初始化下拉列表信息
     * */
    void jComboBoxInit(){
        //获取所有的用户信息
        List<User> users = userService.findAll();
        if(users.isEmpty()) {
            JOptionPane.showMessageDialog(null,"目前没有用户的相关信息");
        }else {
            for (User user : users) {
                nameBox.addItem(user.getUsername());
            }
            // 只处理选中的状态
            String name = String.valueOf(nameBox.getSelectedItem());
            User user = userService.findUserByName(name);
            userName.setText(user.getUsername());
            password.setText("");
            telText.setText(user.getTel());
        }
    }
    /**
     * 设置组件位置并添加到容器中
     * */
    private void setupComponet(JComponent component, int gridx, int gridy, int gridwidth, int ipadx, boolean fill) {

        final GridBagConstraints gridBagConstrains = new GridBagConstraints();
        // 设置组件位于网格的横向索引为gridx
        gridBagConstrains.gridx = gridx;
        // 设置组件位于网格的纵向索引为gridy
        gridBagConstrains.gridy = gridy;
        // 组件彼此的间距
        gridBagConstrains.insets = new Insets(20, 1, 10, 1);
        // 设置组件占用的网格列数
        if (gridwidth > 1)
            gridBagConstrains.gridwidth = gridwidth;
        if (ipadx > 0)// 组件横向填充的大小大于0
            gridBagConstrains.ipadx = ipadx;// 设置组件横向填充的大小
        if (fill)// 组件占据空白区域
            gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;// 组件水平扩大以占据空白区域
        add(component, gridBagConstrains);// 添加组件
    }
}
