package com.java.view;

import com.java.service.UserService;
import com.java.service.impl.UserServiceImpl;
import com.java.utils.EncryptionUtil;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

    //用户名(变量)
    private JLabel userLable1;
    //用户名变量(输入框)
    private JTextField userField;
    //密码(变量)
    private JLabel userLable2;
    //密码(文本框)
    private JPasswordField passwordField;
    //确认按钮
    private JButton okButton;
    //取消按钮
    private JButton cancelButton;



    public LoginFrame(){
        //设置标签相关信息
        userLable1 = new JLabel("用户名");
        userLable1.setFont(new Font("宋体",Font.BOLD,18));
        userLable2 = new JLabel("密码");
        userLable2.setFont(new Font("宋体",Font.BOLD,18));

        //设置标签布局
        userLable1.setBounds(20,220,100,30);
        userLable2.setBounds(20,280,100,30);
        this.add(userLable1);
        this.add(userLable2);

        //设置文本输入框内容
        //1、用户名输入框内容
        userField = new JTextField();
        userField.setBounds(80, 220, 100, 30);
        //设置输入框凹陷效果
        userField.setBorder(BorderFactory.createLoweredBevelBorder());
        //设置输入框背景透明
        userField.setOpaque(false);
        this.add(userField);

        //2、密码输入框设置
        passwordField = new JPasswordField();
        passwordField.setBounds(80,280,100,30);
        //设置输入框凹陷效果
        passwordField.setBorder(BorderFactory.createLoweredBevelBorder());
        //设置输入框背景透明
        passwordField.setOpaque(false);
        this.add(passwordField);

        //设置按钮的位置
        okButton = new JButton("登录");
        okButton.setBounds(45,350,60,36);
        this.add(okButton);
        //设置退出按钮的位置
        cancelButton = new JButton("退出");
        cancelButton.setBounds(135,350,60,36);
        this.add(cancelButton);

        /**
         * 登录按钮实现登录功能
         * １、通过AES加密工具类加密输入的密码避免密码泄露、
         * ２、使用用户名查询数据库中的记录找到用户名对应的密码
         * ３、将数据库获得的密码与加密后的密码进行对比，一致则登录成功。否则登录失败
         * */
        okButton.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //获取用户输入的用户名
                String username = userField.getText();
                //获取用户输入的密码
                String password = passwordField.getText();
                //将用户的密码使用AES进行加密保证密码的安全性
                String ciperPassword = new EncryptionUtil().getEncryption(password);
                UserService userService = new UserServiceImpl();
                boolean login = userService.login(username, ciperPassword);

                if (login) {
                    JOptionPane.showMessageDialog(null,"登录成功欢迎您！");
                    new MainFrame();
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null,"用户名或密码不正确");
                }
            }
        });

        /**
         * 实现退出管理系统的功能
         * */
        cancelButton.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //设置背景面板
        JPanel panel = new JPanel();
        this.add(panel);


        this.setTitle("医药销售管理系统");
        this.setSize(900,530);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }




    public static void main(String[] args) {
        new LoginFrame();
    }
}
