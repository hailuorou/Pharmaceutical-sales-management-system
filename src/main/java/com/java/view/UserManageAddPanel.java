package com.java.view;

import com.java.domain.ResultInfo;
import com.java.domain.User;
import com.java.service.UserService;
import com.java.service.impl.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserManageAddPanel extends JPanel {
    //用户名称
    private JTextField userName;
    //用户密码
    private JPasswordField password;
    //联系方式
    private JTextField telText;

    public UserManageAddPanel(){
        super();
        this.setBounds(10,10,460,300);
        this.setLayout(new GridBagLayout());

        //设置面板格式
        final JLabel name = new JLabel();
        name.setText("用户名称");
        setupComponet(name,0,0,1,0,false);
        userName = new JTextField();
        setupComponet(userName,1,0,3,350,true);


        final JLabel people = new JLabel();
        people.setText("密码");
        setupComponet(people,0,1,1,0,false);
        password = new JPasswordField();
        setupComponet(password,1,1,3,350,true);

        final JLabel tel = new JLabel("联系电话");
        setupComponet(tel,0,2,1,0,false);
        telText = new JTextField();
        setupComponet(telText,1,2,3,350,true);

        final JButton save = new JButton();
        save.setText("注册");
        setupComponet(save,2,8,1,0,false);

        final JButton reset = new JButton();
        reset.setText("重置");
        setupComponet(reset,3,8,1,0,false);

        /**
         * 保存客户的资料到数据库中
         * */
        save.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //获取页面填写的内容
                String name = userName.getText();
                String passwordText = password.getText();
                String tel = telText.getText();
                //将其封装为User对象
                User user = new User();
                user.setUsername(name);
                user.setPassword(passwordText);
                user.setTel(tel);

                //进行客户信息保存
                UserService userService = new UserServiceImpl();
                ResultInfo resultInfo = userService.save(user);
                if(resultInfo.isFlag()) {
                    JOptionPane.showMessageDialog(null,"用户注册成功");
                }else {
                    JOptionPane.showMessageDialog(null,resultInfo.getMsg());
                }
            }
        });

        /**
         * 重置页面内容
         * */
        reset.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                userName.setText("");
                password.setText("");
                telText.setText("");
            }
        });
    }

    /**
     * 设置组件位置并添加到容器中
     * */
    private void setupComponet(JComponent component, int gridx, int gridy, int gridwidth, int ipadx, boolean fill) {
        // 创建网格限制对象
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
