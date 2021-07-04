package com.java.view;

import com.java.domain.Medicine;
import com.java.service.MedicineService;
import com.java.service.impl.MedicineServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

public class MedicineInfoQueryFrame extends JInternalFrame {
    private JTable table;
    // “条件内容”文本框
    private JTextField conditionContent;
    // “条件运算”下拉列表
    private JComboBox conditionOperation;
    // “条件名称”下拉列表
    private JComboBox conditionName;
    //药品查询的业务处理对象
    private MedicineService medicineService = new MedicineServiceImpl();

    public MedicineInfoQueryFrame(){
        super();
        //设置可别关闭
        setClosable(true);
        //设置面板布局
        getContentPane().setLayout(new GridBagLayout());
        setBounds(100, 100, 650, 375);// 设置商品查询内部窗体的位置和宽高

        //表格模型
        table = new JTable();
        table.setEnabled(false);
        //不自动调整表格宽度使用滚条
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //获取默认表格模型
        final DefaultTableModel dfm = (DefaultTableModel) table.getModel();
        //表头
        String[] tableHead = {"药品ID","药品名称","产地","生产批号","进价","售价","包装规格","生产日期","有效期","类别","供应商"};
        dfm.setColumnIdentifiers(tableHead);

        final JScrollPane scrollPane = new JScrollPane(table);
        final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
        gridBagConstraints_6.weighty = 1.0;
        gridBagConstraints_6.anchor = GridBagConstraints.NORTH;
        gridBagConstraints_6.insets = new Insets(0, 2, 0, 2);
        gridBagConstraints_6.fill = GridBagConstraints.BOTH;
        gridBagConstraints_6.gridwidth = 6;
        gridBagConstraints_6.gridy = 1;
        gridBagConstraints_6.gridx = 0;
        getContentPane().add(scrollPane, gridBagConstraints_6);

        // “选择查询条件”标签和“条件名称”下拉列表
        setupComponet(new JLabel(" 选择查询条件："), 0, 0, 1, 1, false);
        conditionName = new JComboBox();
        conditionName.setModel(new DefaultComboBoxModel(new String[]{"药品名称", "供应商名称", "产地"}));
        setupComponet(conditionName, 1, 0, 1, 30, true);

        // “条件运算”下拉列表
        conditionOperation = new JComboBox();
        conditionOperation.setModel(new DefaultComboBoxModel(new String[]{"等于", "包含"}));
        setupComponet(conditionOperation, 2, 0, 1, 30, true);

        // “条件内容”文本框
        conditionContent = new JTextField();
        setupComponet(conditionContent, 3, 0, 1, 140, true);

        // “查询”按钮
        final JButton queryButton = new JButton();
        setupComponet(queryButton, 4, 0, 1, 1, false);
        queryButton.setText("查询");
        // “显示全部数据”按钮
        final JButton showAllButton = new JButton();
        setupComponet(showAllButton, 5, 0, 1, 1, false);
        showAllButton.setText("显示全部数据");


        /**
         * 显示全部药品信息查询
         * */
        showAllButton.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                dfm.setRowCount( 0 );
                List<Medicine> medicines = medicineService.findAllMedicine();
                if(!medicines.isEmpty()) {
                    for (Medicine medicine : medicines) {
                        Vector<String> v = new Vector<String>();
                        v.add(medicine.getId());
                        v.add(medicine.getName());
                        v.add(medicine.getAddr());
                        v.add(medicine.getProBatch());
                        v.add(medicine.getPurcharPrice());
                        v.add(medicine.getSellPrice());
                        v.add(medicine.getPackingSpecification());
                        v.add(medicine.getProductionDate());
                        v.add(medicine.getValidity());
                        v.add(medicine.getCategory());
                        v.add(medicine.getSupplierName());
                        dfm.addRow(v);
                    }
                }
            }
        });

        /**
         * 根据信息查询药品信息
         * */
        queryButton.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                dfm.setRowCount( 0 );
                String condition1 = String.valueOf(conditionName.getSelectedItem());
                String condition2 = String.valueOf(conditionOperation.getSelectedItem());
                String content = conditionContent.getText();
                List<Medicine> medicines = medicineService.findMedicineByCondition(condition1, condition2, content);
                if(!medicines.isEmpty()) {
                    for (Medicine medicine : medicines) {
                        Vector<String> v = new Vector<String>();
                        v.add(medicine.getId());
                        v.add(medicine.getName());
                        v.add(medicine.getAddr());
                        v.add(medicine.getProBatch());
                        v.add(medicine.getPurcharPrice());
                        v.add(medicine.getSellPrice());
                        v.add(medicine.getPackingSpecification());
                        v.add(medicine.getProductionDate());
                        v.add(medicine.getValidity());
                        v.add(medicine.getCategory());
                        v.add(medicine.getSupplierName());
                        dfm.addRow(v);
                    }
                }
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
}
