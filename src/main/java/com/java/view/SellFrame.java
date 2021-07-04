package com.java.view;

import com.java.domain.Customer;
import com.java.domain.Medicine;
import com.java.domain.ResultInfo;
import com.java.domain.Sell;
import com.java.service.CustomerService;
import com.java.service.MedicineService;
import com.java.service.SellService;
import com.java.service.impl.CustomerServiceImpl;
import com.java.service.impl.MedicineServiceImpl;
import com.java.service.impl.SellServiceImpl;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SellFrame extends JInternalFrame {
    //销售单号
    private JTextField xiaoshoudanhao;
    //客户
    private JComboBox customerComboBox;
    //结算方式
    private JComboBox jiesuanfangshi;
    //销售时间
    private JTextField xiaoshoushijian;
    //品种数量
    private JTextField pinzhongshuliang;
    //合计金额
    private JTextField hejijiner;
    //药品名称
    private JComboBox medicineName;
    //药品产地
    private JTextField medicineAddr;
    //生产批号
    private JTextField medicineBatch;
    //售价
    private JTextField medicinesellprice;
    //包装规格
    private JTextField packing;
    //生产日期
    private JTextField productionTime;
    //有效期
    private JTextField validity;
    //药品类别
    private JTextField category;
    //药品供应商
    private JComboBox supplier;

    //客户操作对象
    private CustomerService cservice = new CustomerServiceImpl();
    // 药品业务操作对象
    private MedicineService mservice = new MedicineServiceImpl();
    //销售业务操作对象
    private SellService sellService = new SellServiceImpl();
    //date对象获取时间
    private Date date;

    public SellFrame(){
        setClosable(true);

        //设置面板布局
        getContentPane().setLayout(new GridBagLayout());
        setBounds(100, 100, 650, 375);// 设置商品查询内部窗体的位置和宽高


        setupComponet(new JLabel("销售单号："),0,0,1,0,false);
        xiaoshoudanhao = new JTextField();
        xiaoshoudanhao.setEditable(false);
        setupComponet(xiaoshoudanhao,1,0,1,200,true);

        setupComponet(new JLabel("客户："),2,0,1,0,false);
        customerComboBox = new JComboBox();
        setupComponet(customerComboBox,3,0,1,200,true);

        setupComponet(new JLabel("结算方式："),0,1,1,0,false);
        jiesuanfangshi = new JComboBox();
        jiesuanfangshi.setModel(new DefaultComboBoxModel(new String[] {"货到付款", "支票结账", "网上电子支付"}));
        setupComponet(jiesuanfangshi,1,1,1,200,true);

        setupComponet(new JLabel("销售时间："),2,1,1,0,false);
        xiaoshoushijian = new JTextField();
        xiaoshoushijian.setEditable(false);
        setupComponet(xiaoshoushijian,3,1,1,200,true);


        setupComponet(new JLabel("药品名称："),0,2,1,0,false);
        medicineName = new JComboBox();
        setupComponet(medicineName,1,2,1,200,true);
        setupComponet(new JLabel("产地："),2,2,1,0,false);
        medicineAddr = new JTextField();
        medicineAddr.setEditable(false);
        setupComponet(medicineAddr,3,2,1,200,true);

        setupComponet(new JLabel("生产批号："),0,3,1,0,false);
        medicineBatch = new JTextField();
        medicineBatch.setEditable(false);
        setupComponet(medicineBatch,1,3,1,200,true);
        setupComponet(new JLabel("售价："),2,3,1,0,false);
        medicinesellprice = new JTextField();
        medicinesellprice.setEditable(false);
        setupComponet(medicinesellprice,3,3,1,200,true);

        setupComponet(new JLabel("包装规格："),0,4,1,0,false);
        packing = new JTextField();
        packing.setEditable(false);
        setupComponet(packing,1,4,1,200,true);
        setupComponet(new JLabel("生产日期："),2,4,1,0,false);
        productionTime = new JTextField();
        productionTime.setEditable(false);
        setupComponet(productionTime,3,4,1,200,true);

        setupComponet(new JLabel("有效期："),0,5,1,0,false);
        validity = new JTextField();
        validity.setEditable(false);
        setupComponet(validity,1,5,1,200,true);
        setupComponet(new JLabel("类别："),2,5,1,0,false);
        category = new JTextField();
        category.setEditable(false);
        setupComponet(category,3,5,1,200,true);

        setupComponet(new JLabel("供应商："),0,6,1,0,false);
        supplier = new JComboBox();
        setupComponet(supplier,1,6,3,350,true);



        setupComponet(new JLabel("商品总数量："),0,7,1,0,false);
        pinzhongshuliang = new JTextField();
        setupComponet(pinzhongshuliang,1,7,1,200,true);

        setupComponet(new JLabel("合计金额："),2,7,1,0,false);
        hejijiner = new JTextField();
        hejijiner.setEditable(false);
        setupComponet(hejijiner,3,7,1,200,true);


        JButton save = new JButton("销售");
        setupComponet(save,2,8,1,1,false);

        /**
         * 初始化客户下拉列表
         * */
        initCustomerComboBox();

        /**
         * 初始化销售时间
         * */
        initSellTime();

        /**
         * 初始化销售单号
         * */
        initDanHao();

        /**
         * 初始化所有药品选项
         * */
        initMedicineName();

        /**
         * 添加商品总数文本框监听事件自动算出总金额
         * */
        pinzhongshuliang.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                countMoney();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                countMoney();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                countMoney();
            }
        });


        /**
         * 销售按钮触发事件
         * */
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取销售订单号
                String dh = xiaoshoudanhao.getText();
                //获取客户名称
                String cname = String.valueOf(customerComboBox.getSelectedItem());
                //获取药品名称
                String mname = String.valueOf(medicineName.getSelectedItem());
                //获取供应商名称
                String sname = String.valueOf(supplier.getSelectedItem());
                //获取销售数量
                Integer number = Integer.valueOf(pinzhongshuliang.getText());
                //获取销售金额
                Double sum = Double.valueOf(hejijiner.getText());
                //获取销售时间
                String time = xiaoshoushijian.getText();

                //获取客户id
                Customer customer = cservice.findByName(cname);
                System.out.println("cid  = " + customer.getId());
                String cid = customer.getId();

                //获取药品id
                Medicine medicine = mservice.findMedicineByMedicineNameAndSupplier(mname, sname);
                String mid = medicine.getId();

                //创建一个销售对象
                Sell sell = new Sell();
                sell.setXsid(dh);
                sell.setMedicineId(mid);
                sell.setCid(cid);
                sell.setNumber(number);
                sell.setMoney(sum);
                sell.setXstime(time);

                //销售业务操作
                ResultInfo res = sellService.sellMedicine(sell);

                JOptionPane.showMessageDialog(null,res.getMsg());
                initDanHao();
                pinzhongshuliang.setText("");
            }
        });

        /**
         * 药品名字选择事件
         * */
        medicineName.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                supplier.removeAllItems();
                //获取药品名称
                String name = String.valueOf(medicineName.getSelectedItem());
                //根据药品名称查找药品
                List<Medicine> medicines = mservice.findMedicineByName(name);
                Medicine medicine = medicines.get(0);
                medicineAddr.setText(medicine.getAddr());
                medicineBatch.setText(medicine.getProBatch());
                medicinesellprice.setText(medicine.getSellPrice());
                packing.setText(medicine.getPackingSpecification());
                productionTime.setText(medicine.getProductionDate());
                validity.setText(medicine.getValidity());
                category.setText(medicine.getCategory());
                for (Medicine medicine1 : medicines) {
                    supplier.addItem(medicine1.getSupplierName());
                    System.out.println(medicine1.getSupplierName());
                }
            }
        });

        /**
         * 供应商名字选择事件
         * */
        supplier.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //获取药品名称
                String mname = String.valueOf(medicineName.getSelectedItem());
                //获取供应商名称
                String sname = String.valueOf(supplier.getSelectedItem());
                //根据药品名称和供应商名称查询药品
                Medicine medicine = mservice.findMedicineByMedicineNameAndSupplier(mname, sname);
                if(medicine != null) {
                    medicineAddr.setText(medicine.getAddr());
                    medicineBatch.setText(medicine.getProBatch());
                    medicinesellprice.setText(medicine.getSellPrice());
                    packing.setText(medicine.getPackingSpecification());
                    productionTime.setText(medicine.getProductionDate());
                    validity.setText(medicine.getValidity());
                    category.setText(medicine.getCategory());
                }
            }
        });



    }

    /**
     * 根据输入的购买数量计算商品总金额
     * */
    private void countMoney(){
        //获取输入的数量
        String number = pinzhongshuliang.getText();
        int num = 0;
        if(!"".equals(number)) {
            num = Integer.valueOf(number);
        }
        //获取销售单价
        double price = Double.parseDouble(medicinesellprice.getText());

        //设置金额到界面上
        hejijiner.setText(String.valueOf(num*price));
    }


    /**
     * 初始化客户列表选项
     * */
    private void initCustomerComboBox() {
        List<Customer> customers = cservice.findAll();
        if(!customers.isEmpty()) {
            for (Customer customer : customers) {
                customerComboBox.addItem(customer.getName());
            }
        }
    }

    /**
     * 初始化销售时间
     * */
    private void initSellTime() {
        //获取当前时间
        date = new Date();
        //格式化时间对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String t = sdf.format(date);
        xiaoshoushijian.setText(t);
    }

    /**
     * 初始化销售单号
     * */
    private void initDanHao() {
        //获取当前时间
        date = new Date();
        //格式化时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String t = sdf.format(date);
        String dh = "XS"+t;
        xiaoshoudanhao.setText(dh);
    }

    /**
     * 初始化药品名称选项
     * */
    private void initMedicineName() {
        //查找所有药品的信息
        List<Medicine> medName = mservice.findAllMedicine();
        if(!medName.isEmpty()) {
            for (Medicine medn : medName) {
                medicineName.addItem(medn.getName());
            }

            //获取当前的药品的名称
            String medn = String.valueOf(medicineName.getSelectedItem());
            //根据药品名称查找所有的供应商
            List<Medicine> medS = mservice.findMedicineByName(medn);
            if(!medS.isEmpty()){
                for (Medicine med : medS) {
                    supplier.addItem(med.getSupplierName());
                }


                String name = String.valueOf(supplier.getSelectedItem());
                Medicine me = mservice.findMedicineByMedicineNameAndSupplier(medn, name);
                if (me != null) {
                    medicineAddr.setText(me.getAddr());
                    medicineBatch.setText(me.getProBatch());
                    medicinesellprice.setText(me.getSellPrice());
                    packing.setText(me.getPackingSpecification());
                    productionTime.setText(me.getProductionDate());
                    validity.setText(me.getValidity());
                    category.setText(me.getCategory());
                }

            }
        }
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
}
