package com.java.view;

import com.java.domain.Category;
import com.java.domain.Medicine;
import com.java.domain.ResultInfo;
import com.java.domain.Supplier;
import com.java.service.CategoryService;
import com.java.service.MedicineService;
import com.java.service.SupplierService;
import com.java.service.impl.CategoryServiceImpl;
import com.java.service.impl.MedicineServiceImpl;
import com.java.service.impl.SupplierServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MedicineInfoManageAddPanel extends JPanel {
    private JTextField medicineName;
    private JTextField medicineAddr;
    private JTextField medicineBatch;
    private JTextField medicinePurcharPrice;
    private JTextField medicineSellPrice;
    private JTextField medicinePackingSpecification;
    private JTextField medicineProductionDate;
    private JTextField medicineValidity;
    private JComboBox medicineCategory;
    private JComboBox medicineSupplierName;
    //按钮事件
    JButton add;
    JButton reset;
    //与药品类别相关的业务处理对象
    private CategoryService cservice = new CategoryServiceImpl();
    //与药品相关的业务处理对象
    private MedicineService mservice = new MedicineServiceImpl();
    //药品供应商的业务处理对象
    private SupplierService sservice = new SupplierServiceImpl();

    public MedicineInfoManageAddPanel(){
        super();
        this.setBounds(10,10,460,300);
        this.setVisible(true);
        this.setLayout(new GridBagLayout());

        //设置面板布局
        final JLabel name = new JLabel("药品名称：");
        set(name,0,0,1,1,false);
        medicineName = new JTextField();
        set(medicineName,1,0,3,350,true);

        final JLabel purcharPrice  = new JLabel("进价：");
        set(purcharPrice,0,1,1,1,false);
        medicinePurcharPrice = new JTextField();
        set(medicinePurcharPrice,1,1,1,150,true);

        final JLabel sellPrice = new JLabel("售价：");
        set(sellPrice,2,1,1,1,false);
        medicineSellPrice = new JTextField();
        set(medicineSellPrice,3,1,1,1,true);

        final JLabel addr = new JLabel("产地：");
        set(addr,0,2,1,1,false);
        medicineAddr = new JTextField();
        set(medicineAddr,1,2,3,350,true);

        final JLabel packing  = new JLabel("包装规格：");
        set(packing,0,3,1,1,false);
        medicinePackingSpecification = new JTextField();
        set(medicinePackingSpecification,1,3,1,150,true);

        final JLabel batch = new JLabel("生产批号：");
        set(batch,2,3,1,1,false);
        medicineBatch = new JTextField();
        set(medicineBatch,3,3,1,1,true);

        final JLabel productDate = new JLabel("生产日期：");
        set(productDate,0,4,1,1,false);
        medicineProductionDate = new JTextField();
        set(medicineProductionDate,1,4,1,150,true);

        final JLabel val = new JLabel("有效期：");
        set(val,2,4,1,1,false);
        medicineValidity = new JTextField();
        set(medicineValidity,3,4,1,1,true);

        final JLabel supplier = new JLabel("供应商：");
        set(supplier,0,5,1,1,true);
        medicineSupplierName = new JComboBox();
        set(medicineSupplierName,1,5,3,350,true);

        final JLabel cate = new JLabel("所属类别：");
        set(cate,0,6,1,1,false);
        medicineCategory = new JComboBox();
        set(medicineCategory,1,6,1,150,true);

        //init初始化下拉框内容
        init();

        add = new JButton("保存");
        set(add,2,6,1,1,false);
        reset = new JButton("重置");
        set(reset,3,6,1,1,false);


        /**
         * 重置按钮触发的事件
         * */
        reset.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //设置所有文本框内容为空
                medicineName.setText("");
                medicineAddr.setText("");
                medicineBatch.setText("");
                medicinePackingSpecification.setText("");
                medicineProductionDate.setText("");
                medicinePurcharPrice.setText("");
                medicineSellPrice.setText("");
                medicineValidity.setText("");
            }
        });


        /**
         * 登录按钮触发的事件
         * */
        add.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //获取信息封装为Medicine对象
                Medicine medicine = new Medicine();
                medicine.setName(medicineName.getText());
                medicine.setAddr(medicineAddr.getText());
                medicine.setCategory(String.valueOf(medicineCategory.getSelectedItem()));
                medicine.setPackingSpecification(medicinePackingSpecification.getText());
                medicine.setProBatch(medicineBatch.getText());
                medicine.setProductionDate(medicineProductionDate.getText());
                medicine.setPurcharPrice(medicinePurcharPrice.getText());
                medicine.setSellPrice(medicineSellPrice.getText());
                medicine.setSupplierName(String.valueOf(medicineSupplierName.getSelectedItem()));
                medicine.setValidity(medicineValidity.getText());
                //保存药品的信息
                ResultInfo res = mservice.saveMedicine(medicine);

                JOptionPane.showMessageDialog(null,res.getMsg());
            }
        });

    }

    /**
     * 从数据库获取下拉框内容
     * */
    private void init(){
        //首先获取药品类别的信息
        List<Category> categories = cservice.findAllCategory();
        for (Category category : categories) {
            medicineCategory.addItem(category.getName());
        }
        //在获取供应商的相关信息
        List<Supplier> suppliers = sservice.findAllSupplier();
        if(suppliers.isEmpty()){
            JOptionPane.showMessageDialog(null,"没有任何供应商请先添加供应商信息");
        }else{
            for (Supplier supplier : suppliers) {
                medicineSupplierName.addItem(supplier.getName());
            }
        }
    }


    private void set(JComponent component, int gridx, int gridy, int gridwidth, int ipadx, boolean fill){
        //实例化布局对象
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        // 组件彼此的间距
        gridBagConstraints.insets = new Insets(20, 1, 10, 1);
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.ipadx = ipadx;
        if(fill){
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        }
        add(component,gridBagConstraints);

    }

}
