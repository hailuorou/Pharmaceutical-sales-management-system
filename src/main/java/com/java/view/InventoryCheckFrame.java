package com.java.view;

import com.java.domain.Medicine;
import com.java.service.MedicineService;
import com.java.service.impl.MedicineServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class InventoryCheckFrame extends JInternalFrame {
    private JTable table;
    //盘点时间
    private JTextField timeText;
    //库存数
    private JTextField numberText;
    //获取盘点时间
    private Date date;
    //药品操作对象
    private MedicineService mservice  = new MedicineServiceImpl();
    //设置默认表格模型
    private DefaultTableModel dfm;

    public InventoryCheckFrame(){
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
        dfm = (DefaultTableModel) table.getModel();
        //表头
        String[] tableHead = {"药品ID","药品名称","库存","产地","生产批号","进价","售价","包装规格","生产日期","有效期","类别","供应商"};
        dfm.setColumnIdentifiers(tableHead);

        final JScrollPane scrollPane = new JScrollPane(table);
        final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
        gridBagConstraints_6.weighty = 1.0;
        gridBagConstraints_6.anchor = GridBagConstraints.NORTH;
        gridBagConstraints_6.insets = new Insets(5, 1, 3, 1);
        gridBagConstraints_6.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints_6.gridwidth = 6;
        gridBagConstraints_6.gridy = 1;
        gridBagConstraints_6.gridx = 0;
        getContentPane().add(scrollPane, gridBagConstraints_6);

        setupComponet(new JLabel("盘点时间："),0,0,1,0,false);
        timeText = new JTextField();
        setupComponet(timeText,1,0,1,300,true);
        timeinit();

        setupComponet(new JLabel("药品数："),2,0,1,0,false);
        numberText = new JTextField();
        setupComponet(numberText,3,0,1,300,true);
        initMedicine();
    }

    /**
     * 初始化盘点时间
     * */
    private void timeinit(){
        //获取时间
        date = new Date();
        //格式化时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        timeText.setText(format);

    }

    /**
     * 初始化药品总数
     * */
    private void initMedicine(){
        List<Medicine> medicines = mservice.findMedicineInWareHouse();
        numberText.setText(String.valueOf(medicines.size()));
        if(!medicines.isEmpty()) {
            for (Medicine medicine : medicines) {
                Vector<String> v = new Vector<String>();
                v.add(medicine.getId());
                v.add(medicine.getName());
                v.add(String.valueOf(medicine.getNumber()));
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
