package com.java.view;

import com.java.domain.ResultInfo;
import com.java.domain.Supplier;
import com.java.service.SupplierService;
import com.java.service.impl.SupplierServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SupplierManageAddPanel extends JPanel {
    private JTextField supplierName;
    private JTextField supplierCity;
    private JTextField supplierPostCode;
    private JTextField supplierTel;
    private JTextField supplierFax;
    private JTextField supplierPeople;
    private JTextField supplierPeople_tel;
    private JTextField supplierMail;
    //添加供应商信息的按钮
    private JButton add;
    //供应商业务层处理对象
    private SupplierService supplierService = new SupplierServiceImpl();

    public SupplierManageAddPanel(){
        //设置面板信息
        super();
        this.setBounds(10,10,460,300);
        this.setLayout(new GridBagLayout());

        //设置面板内组件的位置
        //1、供应商名称位置
        final JLabel nameLable = new JLabel("供应商名称：");
        panelLayOut(nameLable,0,0,1,0,false);
        supplierName = new JTextField();
        panelLayOut(supplierName,1,0,3,0,true);
        //2、供应商电话
        final  JLabel telLable = new JLabel("电话：");
        panelLayOut(telLable,0,1,1,1,false);
        supplierTel = new JTextField();
        panelLayOut(supplierTel,1,1,1,160,true);
        //3、供应商邮编
        final JLabel postcode = new JLabel("邮政编码：");
        panelLayOut(postcode,2,1,1,1,false);
        supplierPostCode = new JTextField();
        panelLayOut(supplierPostCode,3,1,1,0,true);
        //4、供应商地址
        final JLabel addr = new JLabel("地址：");
        panelLayOut(addr,0,2,1,0,false);
        supplierCity = new JTextField();
        panelLayOut(supplierCity,1,2,3,350,true);
        //5、联系人
        final JLabel people = new JLabel("负责人：");
        panelLayOut(people,0,3,1,0,false);
        supplierPeople = new JTextField();
        panelLayOut(supplierPeople,1,3,1,160,true);
        //6、联系人电话
        final JLabel people_tel = new JLabel("负责人电话:");
        panelLayOut(people_tel,2,3,1,0,false);
        supplierPeople_tel = new JTextField();
        panelLayOut(supplierPeople_tel,3,3,1,160,true);
        //7、传真
        final JLabel fax = new JLabel("传真：");
        panelLayOut(fax,0,4,1,0,false);
        supplierFax = new JTextField();
        panelLayOut(supplierFax,1,4,1,160,true);
        //8、电子邮箱
        final JLabel mail = new JLabel("电子邮箱：");
        panelLayOut(mail,2,4,1,0,false);
        supplierMail = new JTextField();
        panelLayOut(supplierMail,3,4,1,160,true);

        add = new JButton("添加");
        panelLayOut(add,2,5,1,0,false);

        /**
         * 保存供应商信息的按钮
         * */
        add.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //封装为supplier对象
                Supplier supplier = new Supplier();
                supplier.setName(supplierName.getText());
                supplier.setCity(supplierCity.getText());
                supplier.setPostcode(supplierPostCode.getText());
                supplier.setTel(supplierTel.getText());
                supplier.setFax(supplierFax.getText());
                supplier.setPeople(supplierPeople.getText());
                supplier.setPeople_tel(supplierPeople_tel.getText());
                supplier.setMail(supplierMail.getText());
                //进行保存操作
                ResultInfo res = supplierService.saveSupplier(supplier);

                JOptionPane.showMessageDialog(null,res.getMsg());
            }
        });

    }

    private void panelLayOut(JComponent component, int gridx, int gridy, int gridwidth, int ipadx, boolean fill){
        // 创建网格限制对象
        final GridBagConstraints gbc = new GridBagConstraints();
        // 设置组件位于网格的横向索引为gridx
        gbc.gridx = gridx;
        // 设置组件位于网格的纵向索引为gridy
        gbc.gridy = gridy;
        // 组件彼此的间距
        gbc.insets = new Insets(20, 1, 10, 1);
        // 设置组件占用的网格列数
        if (gridwidth > 1)
            gbc.gridwidth = gridwidth;
        if (ipadx > 0)// 组件横向填充的大小大于0
            gbc.ipadx = ipadx;// 设置组件横向填充的大小
        if (fill)// 组件占据空白区域
            gbc.fill = GridBagConstraints.HORIZONTAL;// 组件水平扩大以占据空白区域
        add(component, gbc);// 添加组件
    }
}
