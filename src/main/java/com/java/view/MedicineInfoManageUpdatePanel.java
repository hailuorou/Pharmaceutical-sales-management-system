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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class MedicineInfoManageUpdatePanel extends JPanel {
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
    private JComboBox medicineN;

    //供应商业务操作对象
    private SupplierService sservice = new SupplierServiceImpl();
    //药品信息业务操作对象
    private MedicineService mservice = new MedicineServiceImpl();
    //药品类别业务操作对象
    private CategoryService cservice = new CategoryServiceImpl();

    public MedicineInfoManageUpdatePanel(){
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

        final JLabel n = new JLabel("药品名称：");
        set(n,0,7,1,1,false);
        medicineN = new JComboBox();
        set(medicineN,1,7,3,350,true);

        init();

        JButton update = new JButton("修改");
        set(update,1,8,1,1,false);
        JButton del = new JButton("删除");
        set(del,3,8,1,1,false);

        /**
         * 根据药品下拉框查看信息
         * */
        medicineN.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String name = String.valueOf(medicineN.getSelectedItem());
                String name1 = String.valueOf(medicineSupplierName.getSelectedItem());
                //查询该药品的所有信息
                List<Medicine> medicineByName1 = mservice.findMedicineByName(name);
                //将该药品的信息在文本框中显示
                Medicine medicineByName = medicineByName1.get(0);
                medicineName.setText(medicineByName.getName());
                medicineAddr.setText(medicineByName.getAddr());
                medicineBatch.setText(medicineByName.getProBatch());
                medicinePackingSpecification.setText(medicineByName.getPackingSpecification());
                medicineProductionDate.setText(medicineByName.getProductionDate());
                medicinePurcharPrice.setText(medicineByName.getPurcharPrice());
                medicineSellPrice.setText(medicineByName.getSellPrice());
                medicineValidity.setText(medicineByName.getValidity());
                medicineCategory.setSelectedItem(medicineByName.getCategory());
                for (Medicine medicine : medicineByName1) {
                    medicineSupplierName.setSelectedItem(medicine.getSupplierName());
                }
            }
        });

        /**
         * 根据*/

        /**
         * 修改按钮的相关功能
         * */
        update.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //获取相关数据封装为Medicine对象
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

                ResultInfo res = mservice.updateMedicine(medicine);

                JOptionPane.showMessageDialog(null,res.getMsg());
            }
        });

        /**
         * 删除按钮相关操作
         * */
        del.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //获取药品的名称
                String name = medicineName.getText();
                ResultInfo resultInfo = mservice.deleteMedicine(name);
                JOptionPane.showMessageDialog(null,resultInfo.getMsg());
            }
        });
    }

    /**
     * 查询下拉框的内容选项
     * */
    private void init(){
        //1、查询供应商的相关信息
        List<Supplier> su = sservice.findAllSupplier();
        if(!su.isEmpty()){
            for (Supplier supplier : su) {
                medicineSupplierName.addItem(supplier.getName());
            }
        }
        //2、查询药品类别信息
        List<Category> categories = cservice.findAllCategory();
        for (Category category : categories) {
            medicineCategory.addItem(category.getName());
        }

        //3、查询药品信息
        List<Medicine> medicines = mservice.findAllMedicine();
        if(!medicines.isEmpty()){
            for (Medicine medicine : medicines) {
                medicineN.addItem(medicine.getName());
            }
            String name = String.valueOf(medicineN.getSelectedItem());
            //查询该药品的所有信息
            List<Medicine> medicineByName1 = mservice.findMedicineByName(name);
            Medicine medicineByName = medicineByName1.get(0);
            //将该药品的信息在文本框中显示
            medicineName.setText(medicineByName.getName());
            medicineAddr.setText(medicineByName.getAddr());
            medicineBatch.setText(medicineByName.getProBatch());
            medicinePackingSpecification.setText(medicineByName.getPackingSpecification());
            medicineProductionDate.setText(medicineByName.getProductionDate());
            medicinePurcharPrice.setText(medicineByName.getPurcharPrice());
            medicineSellPrice.setText(medicineByName.getSellPrice());
            medicineValidity.setText(medicineByName.getValidity());
            medicineCategory.setSelectedItem(medicineByName.getCategory());
            medicineSupplierName.setSelectedItem(medicineByName.getSupplierName());
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
