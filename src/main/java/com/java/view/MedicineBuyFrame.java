package com.java.view;

import com.java.domain.Buy;
import com.java.domain.Medicine;
import com.java.domain.ResultInfo;
import com.java.domain.Supplier;
import com.java.service.BuyService;
import com.java.service.MedicineService;
import com.java.service.SupplierService;
import com.java.service.impl.BuyServiceImpl;
import com.java.service.impl.MedicineServiceImpl;
import com.java.service.impl.SupplierServiceImpl;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MedicineBuyFrame extends JInternalFrame {
    //进货单号
    private JTextField jinhuodanhao;
    //供应商
    private JComboBox supplierComboBox;
    //结算方式
    private JComboBox jiesuanfangshi;
    //进货时间
    private JTextField jinhuoshijian;
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
    //进价
    private JTextField medicinepurcharprice;
    //包装规格
    private JTextField packing;
    //生产日期
    private JTextField productionTime;
    //有效期
    private JTextField validity;
    //药品类别
    private JTextField category;

    //供应商业务操作对象
    private SupplierService sservice = new SupplierServiceImpl();
    //药品业务操作对象
    private MedicineService mservice = new MedicineServiceImpl();
    //药品入库业务操作对象
    private BuyService bservice = new BuyServiceImpl();

    //设置data时间对象
    private Date date;


    public MedicineBuyFrame() {
        setClosable(true);
        //设置面板布局
        getContentPane().setLayout(new GridBagLayout());
        setBounds(100, 100, 650, 375);// 设置商品查询内部窗体的位置和宽高


        setupComponet(new JLabel("进货单号："), 0, 0, 1, 0, false);
        jinhuodanhao = new JTextField();
        jinhuodanhao.setEditable(false);
        setupComponet(jinhuodanhao, 1, 0, 1, 200, true);

        setupComponet(new JLabel("供应商："), 2, 0, 1, 0, false);
        supplierComboBox = new JComboBox();
        setupComponet(supplierComboBox, 3, 0, 1, 200, true);

        setupComponet(new JLabel("结算方式："), 0, 1, 1, 0, false);
        jiesuanfangshi = new JComboBox();
        jiesuanfangshi.setModel(new DefaultComboBoxModel(new String[]{"货到付款", "支票结账", "网上电子支付"}));
        setupComponet(jiesuanfangshi, 1, 1, 1, 200, true);

        setupComponet(new JLabel("进货时间："), 2, 1, 1, 0, false);
        jinhuoshijian = new JTextField();
        jinhuoshijian.setEditable(false);
        setupComponet(jinhuoshijian, 3, 1, 1, 200, true);

        setupComponet(new JLabel("药品名称："), 0, 2, 1, 0, false);
        medicineName = new JComboBox();
        setupComponet(medicineName, 1, 2, 1, 200, true);
        setupComponet(new JLabel("产地："), 2, 2, 1, 0, false);
        medicineAddr = new JTextField();
        medicineAddr.setEditable(false);
        setupComponet(medicineAddr, 3, 2, 1, 200, true);

        setupComponet(new JLabel("生产批号："), 0, 3, 1, 0, false);
        medicineBatch = new JTextField();
        medicineBatch.setEditable(false);
        setupComponet(medicineBatch, 1, 3, 1, 200, true);
        setupComponet(new JLabel("进价："), 2, 3, 1, 0, false);
        medicinepurcharprice = new JTextField();
        medicinepurcharprice.setEditable(false);
        setupComponet(medicinepurcharprice, 3, 3, 1, 200, true);

        setupComponet(new JLabel("包装规格："), 0, 4, 1, 0, false);
        packing = new JTextField();
        packing.setEditable(false);
        setupComponet(packing, 1, 4, 1, 200, true);
        setupComponet(new JLabel("生产日期："), 2, 4, 1, 0, false);
        productionTime = new JTextField();
        productionTime.setEditable(false);
        setupComponet(productionTime, 3, 4, 1, 200, true);

        setupComponet(new JLabel("有效期："), 0, 5, 1, 0, false);
        validity = new JTextField();
        validity.setEditable(false);
        setupComponet(validity, 1, 5, 1, 200, true);
        setupComponet(new JLabel("类别："), 2, 5, 1, 0, false);
        category = new JTextField();
        category.setEditable(false);
        setupComponet(category, 3, 5, 1, 200, true);

        setupComponet(new JLabel("商品总数量："), 0, 6, 1, 0, false);
        pinzhongshuliang = new JTextField();
        setupComponet(pinzhongshuliang, 1, 6, 1, 200, true);

        setupComponet(new JLabel("合计金额："), 2, 6, 1, 0, false);
        hejijiner = new JTextField();
        hejijiner.setEditable(false);
        setupComponet(hejijiner, 3, 6, 1, 200, true);



        JButton save = new JButton("入库");
        setupComponet(save, 2, 7, 1, 1, false);

        /**
         * 初始化供应商的选项及药品名称选项
         * */
        initSupplierComboBox();
        /**
         * 初始化购买时间
         * */
        initBuyTime();
        /**
         * 初始化进货单号
         * */
        initDanHao();

        pack();

        supplierComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                medicineName.removeAllItems();
                //获取供应商名称
                String supplierName = String.valueOf(supplierComboBox.getSelectedItem());
                //根据供应商名字查找所有供应商
                List<Medicine> medicines = mservice.findMedicineBySupplier(supplierName);
                if (!medicines.isEmpty()) {
                    for (Medicine medicine : medicines) {
                        medicineName.addItem(medicine.getName());
                    }
                    String name = String.valueOf(medicineName.getSelectedItem());
                    Medicine me = mservice.findMedicineByMedicineNameAndSupplier(name, supplierName);
                    if (me != null) {
                        medicineAddr.setText(me.getAddr());
                        medicineBatch.setText(me.getProBatch());
                        medicinepurcharprice.setText(me.getPurcharPrice());
                        packing.setText(me.getPackingSpecification());
                        productionTime.setText(me.getProductionDate());
                        validity.setText(me.getValidity());
                        category.setText(me.getCategory());
                    }

                }
            }
        });

        medicineName.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //获取供应商名称
                String supplierName = String.valueOf(supplierComboBox.getSelectedItem());
                String name = String.valueOf(medicineName.getSelectedItem());
                Medicine me = mservice.findMedicineByMedicineNameAndSupplier(name, supplierName);
                if (me != null) {
                    medicineAddr.setText(me.getAddr());
                    medicineBatch.setText(me.getProBatch());
                    medicinepurcharprice.setText(me.getPurcharPrice());
                    packing.setText(me.getPackingSpecification());
                    productionTime.setText(me.getProductionDate());
                    validity.setText(me.getValidity());
                    category.setText(me.getCategory());
                }
            }
        });


        /**
         * 输入入库商品的文本框监听事件
         * */
        pinzhongshuliang.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                textDo();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                textDo();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                textDo();
            }
        });


        /**
         * 入库按钮监听事件
         * */
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取进货单号
                String dh = jinhuodanhao.getText();
                //获取供应商名称
                String supplier = String.valueOf(supplierComboBox.getSelectedItem());
                //获取药品名称
                String medicineN = String.valueOf(medicineName.getSelectedItem());
                //获取销售的数量
                Integer number = Integer.valueOf(pinzhongshuliang.getText());
                //获取购买花费
                Double sum = Double.valueOf(hejijiner.getText());
                //获取销售时间
                String time = jinhuoshijian.getText();

                //根据供应商和药品名称查出药品的id
                Medicine m = mservice.findMedicineByMedicineNameAndSupplier(medicineN, supplier);
                //药品id
                String mid = m.getId();

                //将购买信息封装为Buy对象
                Buy buy = new Buy();
                buy.setRkid(dh);
                buy.setMedicineId(mid);
                buy.setNumber(number);
                buy.setMoney(sum);
                buy.setRktime(time);

                //进行入库操作
                ResultInfo res = bservice.saveBuyInfo(buy);
                JOptionPane.showMessageDialog(null,res.getMsg());

                initDanHao();
                pinzhongshuliang.setText("");

            }
        });

    }

    /**
     * 文本框监听事件内容
     * */
    private void textDo(){
        //获取文本框的内容
        String number = pinzhongshuliang.getText();
        int num = 0;
        if(!"".equals(number)){
            num = Integer.valueOf(number);
        }
        //获取产品的单价
        double price = Double.parseDouble(medicinepurcharprice.getText());

        //计算商品总价
        double sum = num*price;

        hejijiner.setText(String.valueOf(sum));
    }

    /**
     * 初始化供应商下拉列表及药品名称选项
     * */
    private void initSupplierComboBox() {
        List<Supplier> suppliers = sservice.findAllSupplier();

        if(!suppliers.isEmpty()) {
            for (Supplier supplier : suppliers) {
                supplierComboBox.addItem(supplier.getName());
            }
            //获取当前供应商名称下拉列表内容
            String supplierName = String.valueOf(supplierComboBox.getSelectedItem());
            //根据供应商名字查找所有供应商
            List<Medicine> medicines = mservice.findMedicineBySupplier(supplierName);
            if(!medicines.isEmpty()) {
                for (Medicine medicine : medicines) {
                    medicineName.addItem(medicine.getName());
                }

            }

        }
    }

    /**
     * 获取销售时间
     * */
    private void initBuyTime() {
        //获取当前时间
        date = new Date();
        //格式化时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String t = sdf.format(date);
        //设置销售时间的格式
        jinhuoshijian.setText(t);
    }

    /**
     * 初始化入库单号
     * */
    private void initDanHao() {
        //获取当前时间
        date = new Date();
        //格式化时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(date);
        String dh = "RK"+format;
        jinhuodanhao.setText(dh);
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
