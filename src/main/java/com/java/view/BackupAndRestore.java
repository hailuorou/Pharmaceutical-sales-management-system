package com.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class BackupAndRestore extends JInternalFrame{
    JButton backUpButton;
    JButton pathButton;
    JButton pathButton1;
    JButton restoreButton;
    JTextField bd;
    JTextField baPath;
    JTextField filepath;
    JFileChooser chooser = new JFileChooser();

   public BackupAndRestore(){
        super();
        setVisible(true);
        setClosable(true);
        getContentPane().setLayout(new GridBagLayout());
       this.setTitle("数据库备份与恢复");// 设置数据库备份与恢复窗体的标题

       JLabel label = new JLabel("数据库备份");
       setupComponet(label,0,0,1,0,false);

       baPath = new JTextField();
       baPath.setText("请选择备份路径");
       baPath.setEditable(false);
       setupComponet(baPath,1,0,3,300,true);

       pathButton1 = new JButton("浏览......");
       setupComponet(pathButton1,2,1,1,0,false);
       pathButton1.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               JFileChooser chooser1 = new JFileChooser();
               int returnVal = chooser1.showOpenDialog(pathButton1);        //是否打开文件选择框
               if (returnVal == JFileChooser.APPROVE_OPTION) {          //如果符合文件类型

                   String filepath = chooser1.getSelectedFile().getAbsolutePath();      //获取绝对路径
                   System.out.println(filepath);
                   baPath.setText(filepath);

               }
           }
       });

       backUpButton = new JButton("备份");
       setupComponet(backUpButton,3,1,1,0,false);

       JLabel label1 = new JLabel("数据库恢复");
       setupComponet(label1,0,2,1,0,false);

       filepath = new JTextField("请选择恢复文件路径");
       filepath.setEditable(false);
       setupComponet(filepath,1,2,1,150,true);

       JLabel label2 = new JLabel("数据库名");
       setupComponet(label2,2,2,1,0,false);
       bd = new JTextField();
       setupComponet(bd,3,2,1,150,true);

       pathButton = new JButton("浏览......");
       setupComponet(pathButton,2,3,1,0,false);
       pathButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               int returnVal = chooser.showOpenDialog(pathButton);        //是否打开文件选择框
               System.out.println("returnVal="+returnVal);

               if (returnVal == JFileChooser.APPROVE_OPTION) {          //如果符合文件类型

                   String filepath1 = chooser.getSelectedFile().getAbsolutePath();      //获取绝对路径
                   System.out.println(filepath1);
                   filepath.setText(filepath1);

               }
           }
       });

       restoreButton = new JButton("恢复");
       setupComponet(restoreButton,3,3,1,0,false);


       /**
        * 备份按钮触发的事件
        * */
       backUpButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                //获取路径的名称
               String path = baPath.getText();
               dbBackUp("root","root","psms",path);
               JOptionPane.showMessageDialog(null,"备份成功");
           }
       });

       /**
        * 恢复按钮触发的事件
        * */
       restoreButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                //获取备份到的数据库名
               String dbName = bd.getText();
               //获取恢复文件的路径
               String filepath1 = filepath.getText();
               dbRestore("root","root",dbName,filepath1);
               JOptionPane.showMessageDialog(null,"恢复成功");
           }
       });



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

    /**
     * 数据库备份
     * */
    private void dbBackUp(String root,String pwd,String dbName,String backPath) {
        String pathSql = backPath;
        File fileSql = new File(pathSql);
        //创建备份sql文件
        if (!fileSql.exists()){
            try {
                fileSql.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        StringBuffer sb = new StringBuffer();
        sb.append("mysqldump");
        sb.append(" -h127.0.0.1");
        sb.append(" -u"+root);
        sb.append(" -p"+pwd);
        sb.append(" "+dbName+" >");
        sb.append(pathSql);
        System.out.println("cmd命令为："+sb.toString());
        Runtime runtime = Runtime.getRuntime();
        System.out.println("开始备份："+dbName);
        try {
            Process process = runtime.exec("cmd /c"+sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("备份成功!");
    }


    /**
     * 数据库恢复
     * */
    private void dbRestore(String root,String pwd,String dbName,String filePath){
        StringBuilder sb = new StringBuilder();
        sb.append("mysql");
        sb.append(" -h127.0.0.1");
        sb.append(" -u"+root);
        sb.append(" -p"+pwd);
        sb.append(" "+dbName+" <");
        sb.append(filePath);
        System.out.println("cmd命令为："+sb.toString());
        Runtime runtime = Runtime.getRuntime();
        System.out.println("开始还原数据");
        try {
            Process process = runtime.exec("cmd /c"+sb.toString());
            InputStream is = process.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is,"utf8"));
            String line = null;
            while ((line=bf.readLine())!=null){
                System.out.println(line);
            }
            is.close();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("还原成功！");
    }


}
