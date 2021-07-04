package com.java.view;

import com.java.domain.Medicine;
import com.java.domain.ResultInfo;
import com.java.service.MedicineService;
import com.java.service.impl.MedicineServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class PriceAdjustFrame extends JInternalFrame {
    //药品名称选择
    private JComboBox nameBox;
    //售价
    private JTextField sellPrice;
    //进价
    private JTextField purchar;
    //更改按钮
    JButton update  = new JButton("更改");
    //关闭按钮
    JButton closeB = new JButton("关闭");
    //药品信息业务处理对象
    private MedicineService medicineService = new MedicineServiceImpl();

    //
    JLabel maddr;
    JLabel mg;
    JLabel mc;
    JComboBox ms;

    public PriceAdjustFrame(){
        super();
        setClosable(true);
        getContentPane().setLayout(new GridBagLayout());

        //设置名称标签
        final JLabel name = new JLabel("名称：");
        final JLabel addr = new JLabel("产地：");
        final JLabel pu = new JLabel("进价：");
        final JLabel sell = new JLabel("售价：");
        final JLabel gg = new JLabel("包装规格：");
        final JLabel ca = new JLabel("类别：");
        final JLabel sn = new JLabel("供应商：");

        maddr = new JLabel();
        maddr.setForeground(Color.BLUE);
        mg = new JLabel();
        mg.setForeground(Color.BLUE);
        mc = new JLabel();
        mc.setForeground(Color.BLUE);
        ms = new JComboBox();
        ms.setForeground(Color.BLUE);
        nameBox = new JComboBox();
        nameBox.setPreferredSize(new Dimension(220,21));
        sellPrice = new JTextField();
        sellPrice.setPreferredSize(new Dimension(220,21));
        purchar = new JTextField();
        purchar.setEditable(false);

        /**
         * 初始化JcomBox获取药品信息的内容
         * */
        init();

        //设置布局
        setupComponet(name,0,0,1,1,false);
        setupComponet(nameBox,1,0,1,0,true);
        setupComponet(ca,2,0,1,0,false);
        setupComponet(mc,3,0,1,0,true);

        setupComponet(addr,0,1,1,1,false);
        setupComponet(maddr,1,1,3,350,true);

        setupComponet(pu,0,2,1,0,false);
        setupComponet(purchar,1,2,1,0,true);
        setupComponet(sell,2,2,1,0,false);
        setupComponet(sellPrice,3,2,1,0,true);

        setupComponet(gg,0,3,1,0,false);
        setupComponet(mg,1,3,1,0,true);
        setupComponet(sn,2,3,1,0,false);
        setupComponet(ms,3,3,1,0,true);

        setupComponet(update,1,4,1,1,false);
        setupComponet(closeB,3,4,1,1,false);


        /**
         * 关闭按钮的事件
         * */
        closeB.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        /**
         * 更改的按钮事件
         * */
        update.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //获取药品的售价
                Double sell = Double.valueOf(sellPrice.getText());
                //获取药品的名称
                String name = String.valueOf(nameBox.getSelectedItem());

                ResultInfo res = medicineService.updatePriceByName(name, sell);
                JOptionPane.showMessageDialog(null,res.getMsg());
            }
        });

        /**
         * 药品选项选择事件
         * */
        nameBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //获取药品名称
                String ItemName = String.valueOf(nameBox.getSelectedItem());

                ms.removeAllItems();

                List<Medicine> m1 = medicineService.findMedicineByName(ItemName);
                Medicine m = m1.get(0);
                maddr.setText(m.getAddr());
                mg.setText(m.getPackingSpecification());
                mc.setText(m.getCategory());
                sellPrice.setText(m.getSellPrice());
                purchar.setText(m.getPurcharPrice());
                for (Medicine medicine : m1) {
                    ms.addItem(medicine.getSupplierName());
                }
            }
        });

        /**
         * 供应商下拉框选项
         * */
        ms.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //获取药品名称
                String mname = String.valueOf(nameBox.getSelectedItem());
                //获取供应商名称
                String sname = String.valueOf(ms.getSelectedItem());
                //根据药品名称和供应商名称查询药品
                Medicine medicine = medicineService.findMedicineByMedicineNameAndSupplier(mname, sname);
                if(medicine != null) {
                    maddr.setText(medicine.getAddr());
                    mg.setText(medicine.getPackingSpecification());
                    mc.setText(medicine.getCategory());
                    sellPrice.setText(medicine.getSellPrice());
                    purchar.setText(medicine.getPurcharPrice());
                }
            }
        });


    }

    /**
     * 初始化药品信息的内容
     * */
    private void init(){
        List<Medicine> me = medicineService.findAllMedicine();
        if(!me.isEmpty()) {
            for (Medicine medicine : me) {
                nameBox.addItem(medicine.getName());
            }

            String ItemName = String.valueOf(nameBox.getSelectedItem());

            List<Medicine> m1 = medicineService.findMedicineByName(ItemName);
            Medicine m = m1.get(0);
            maddr.setText(m.getAddr());
            mg.setText(m.getPackingSpecification());
            mc.setText(m.getCategory());
            sellPrice.setText(m.getSellPrice());
            purchar.setText(m.getPurcharPrice());
            for (Medicine medicine : m1) {
                ms.addItem(medicine.getSupplierName());
                System.out.println(medicine.getSupplierName());
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
