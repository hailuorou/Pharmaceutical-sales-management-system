package com.java.view;

import com.java.domain.Customer;
import com.java.domain.ResultInfo;
import com.java.service.CustomerService;
import com.java.service.impl.CustomerServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class CustomerManageUpdatePanel extends JPanel {
    //客户名称
    private JTextField customerName;
    //客户地址
    private JTextField customerAddr;
    //客户联系人
    private JTextField customerPeople;
    //联系方式
    private JTextField customerTel;
    //名称下来框
    private JComboBox nameBox;
    //完成customer的业务操作
    CustomerService service = new CustomerServiceImpl();

    public CustomerManageUpdatePanel(){
        super();
        this.setBounds(10,10,460,300);
        this.setLayout(new GridBagLayout());

        //设置面板格式
        final JLabel name = new JLabel();
        name.setText("客户名称");
        setupComponet(name,0,0,1,0,false);
        customerName = new JTextField();
        customerName.setEditable(false);
        setupComponet(customerName,1,0,3,350,true);

        final JLabel addr = new JLabel();
        addr.setText("客户地址");
        setupComponet(addr,0,2,1,0,false);
        customerAddr = new JTextField();
        setupComponet(customerAddr,1,2,3,350,true);

        final JLabel people = new JLabel();
        people.setText("联系人");
        setupComponet(people,0,4,1,0,false);
        customerPeople = new JTextField();
        setupComponet(customerPeople,1,4,3,350,true);

        final JLabel tel = new JLabel("联系电话");
        setupComponet(tel,0,6,1,0,false);
        customerTel = new JTextField();
        setupComponet(customerTel,1,6,3,350,true);

        final JLabel label = new JLabel("选择客户");
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
                Customer cus = service.findByName(name);
                customerName.setText(cus.getName());
                customerAddr.setText(cus.getAddr());
                customerPeople.setText(cus.getPeople());
                customerTel.setText(cus.getTel());
            }
        });

        //修改按钮事件
        update.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //获取文本框内的客户信息封装为customer对象
                String name = customerName.getText();
                String addr = customerAddr.getText();
                String people = customerPeople.getText();
                String tel = customerTel.getText();
                //新建一个customer对象
                Customer customer = new Customer();
                customer.setName(name);
                customer.setAddr(addr);
                customer.setPeople(people);
                customer.setTel(tel);

                ResultInfo info = service.updateCustomer(customer);

                JOptionPane.showMessageDialog(null,info.getMsg());


            }
        });

        /**
         * 删除按钮的事件
         * */
        delete.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //获取客户的名称
                String name = customerName.getText();
                //根据客户的名称删除客户的信息
                ResultInfo resultInfo = service.deleteCustomerByName(name);

                JOptionPane.showMessageDialog(null,resultInfo.getMsg());
            }
        });

    }

    /**
     * 初始化下拉列表信息
     * */
    void jComboBoxInit(){
        //获取所有的客户信息
        CustomerService service = new CustomerServiceImpl();
        List<Customer> customers = service.findAll();
        if(customers.isEmpty()) {
            JOptionPane.showMessageDialog(null,"目前没有客户相关信息");
        }else {
            for (Customer customer : customers) {
                nameBox.addItem(customer.getName());
            }
            // 只处理选中的状态
            String name = String.valueOf(nameBox.getSelectedItem());
            Customer cus = service.findByName(name);
            customerName.setText(cus.getName());
            customerAddr.setText(cus.getAddr());
            customerPeople.setText(cus.getPeople());
            customerTel.setText(cus.getTel());
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
