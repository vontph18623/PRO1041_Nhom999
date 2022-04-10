/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.newgui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartPanel;
import poly.dao.DoanhThuDao;
import poly.helper.CustomTabbedPaneUI;
import poly.helper.Messeger;
import poly.helper.XExcel;
import poly.helper.XInternal;

/**
 *
 * @author NTV
 */
public class ThongKeFrm extends javax.swing.JInternalFrame {

    private DoanhThuDao dt_dao;
    private CardLayout baseCard;
    private JDesktopPane main;

    /**
     * Creates new form ThongKeFrm
     */
    public ThongKeFrm(JDesktopPane jFrame) {
        initComponents();
        main = jFrame;
        
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblMoney = new javax.swing.JLabel();
        lblTCDay = new javax.swing.JLabel();
        lblHuyDay = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblMoneyMonths = new javax.swing.JLabel();
        lblTCMonth = new javax.swing.JLabel();
        lblHuyMonth = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblMoneyYear = new javax.swing.JLabel();
        lblTCYear = new javax.swing.JLabel();
        lblHuyYear = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cbxChoose = new javax.swing.JComboBox<>();
        basecard = new javax.swing.JPanel();
        dayCard = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cbxDaysMonth = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cbxDaysYear = new javax.swing.JComboBox<>();
        monthCard = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cbxMonthYear = new javax.swing.JComboBox<>();
        yearCard = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        tpBang = new javax.swing.JTabbedPane();
        jPanel15 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTK = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        btnXuatExcel = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        panelChard = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnXuatSP = new javax.swing.JButton();

        setMaximizable(true);
        setPreferredSize(new java.awt.Dimension(1920, 960));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jTabbedPane1.setBackground(new java.awt.Color(102, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(102, 0, 153));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTabbedPane1.setOpaque(true);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(255, 255, 255)));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 150));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 15, 0));

        jPanel10.setBackground(new java.awt.Color(255, 102, 102));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Doanh Thu Ngày");
        jPanel10.add(jLabel5, java.awt.BorderLayout.PAGE_START);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/pay.png"))); // NOI18N
        jLabel6.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 0, 0, 10, new java.awt.Color(255, 102, 102)));
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 20));
        jPanel10.add(jLabel6, java.awt.BorderLayout.EAST);

        jPanel5.setBackground(new java.awt.Color(255, 102, 102));
        jPanel5.setLayout(new java.awt.GridLayout(4, 0));

        lblMoney.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMoney.setForeground(new java.awt.Color(255, 255, 255));
        lblMoney.setText("Tiền");
        lblMoney.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, new java.awt.Color(255, 102, 102)));
        jPanel5.add(lblMoney);

        lblTCDay.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTCDay.setForeground(new java.awt.Color(255, 255, 255));
        lblTCDay.setText("Thành Công");
        lblTCDay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, new java.awt.Color(255, 102, 102)));
        jPanel5.add(lblTCDay);

        lblHuyDay.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblHuyDay.setForeground(new java.awt.Color(255, 255, 255));
        lblHuyDay.setText("Bị Huỷ");
        lblHuyDay.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, new java.awt.Color(255, 102, 102)));
        jPanel5.add(lblHuyDay);

        jPanel10.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel10);

        jPanel12.setBackground(new java.awt.Color(255, 153, 51));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Doanh Thu Tháng");
        jPanel12.add(jLabel2, java.awt.BorderLayout.PAGE_START);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/moneymonth.png"))); // NOI18N
        jLabel8.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 1, 1, 10, new java.awt.Color(255, 153, 51)));
        jLabel8.setPreferredSize(new java.awt.Dimension(80, 64));
        jPanel12.add(jLabel8, java.awt.BorderLayout.EAST);

        jPanel6.setBackground(new java.awt.Color(255, 153, 51));
        jPanel6.setLayout(new java.awt.GridLayout(4, 0));

        lblMoneyMonths.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMoneyMonths.setForeground(new java.awt.Color(255, 255, 255));
        lblMoneyMonths.setText("Tiền");
        lblMoneyMonths.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, new java.awt.Color(255, 153, 51)));
        jPanel6.add(lblMoneyMonths);

        lblTCMonth.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTCMonth.setForeground(new java.awt.Color(255, 255, 255));
        lblTCMonth.setText("Thành Công");
        lblTCMonth.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, new java.awt.Color(255, 153, 51)));
        jPanel6.add(lblTCMonth);

        lblHuyMonth.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblHuyMonth.setForeground(new java.awt.Color(255, 255, 255));
        lblHuyMonth.setText("Bị Huỷ");
        lblHuyMonth.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, new java.awt.Color(255, 153, 51)));
        jPanel6.add(lblHuyMonth);

        jPanel12.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel12);

        jPanel11.setBackground(new java.awt.Color(51, 153, 255));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Doanh Thu Năm");
        jPanel11.add(jLabel3, java.awt.BorderLayout.PAGE_START);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/moneyyear.png"))); // NOI18N
        jLabel7.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 1, 1, 10, new java.awt.Color(51, 153, 255)));
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel7.setPreferredSize(new java.awt.Dimension(80, 20));
        jPanel11.add(jLabel7, java.awt.BorderLayout.EAST);

        jPanel7.setBackground(new java.awt.Color(51, 153, 255));
        jPanel7.setLayout(new java.awt.GridLayout(4, 0));

        lblMoneyYear.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMoneyYear.setForeground(new java.awt.Color(255, 255, 255));
        lblMoneyYear.setText("Tiền");
        lblMoneyYear.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, new java.awt.Color(51, 153, 255)));
        jPanel7.add(lblMoneyYear);

        lblTCYear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTCYear.setForeground(new java.awt.Color(255, 255, 255));
        lblTCYear.setText("Thành Công");
        lblTCYear.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, new java.awt.Color(51, 153, 255)));
        jPanel7.add(lblTCYear);

        lblHuyYear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblHuyYear.setForeground(new java.awt.Color(255, 255, 255));
        lblHuyYear.setText("Huỷ");
        lblHuyYear.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, new java.awt.Color(51, 153, 255)));
        jPanel7.add(lblHuyYear);

        jPanel11.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel11);

        jPanel1.add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel13.setBackground(new java.awt.Color(204, 102, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 250, 0, 20, new java.awt.Color(204, 102, 255)));
        jPanel13.setPreferredSize(new java.awt.Dimension(1469, 41));
        jPanel13.setLayout(new java.awt.BorderLayout());

        jPanel16.setBackground(new java.awt.Color(204, 102, 255));
        jPanel16.setPreferredSize(new java.awt.Dimension(269, 41));
        jPanel16.setLayout(new java.awt.BorderLayout(20, 0));

        jLabel9.setBackground(new java.awt.Color(204, 102, 255));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Thời Gian:");
        jLabel9.setPreferredSize(new java.awt.Dimension(105, 20));
        jPanel16.add(jLabel9, java.awt.BorderLayout.WEST);

        cbxChoose.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày Trong Tháng", "Tháng Trong Năm", "Năm" }));
        cbxChoose.setPreferredSize(new java.awt.Dimension(106, 26));
        cbxChoose.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxChooseItemStateChanged(evt);
            }
        });
        jPanel16.add(cbxChoose, java.awt.BorderLayout.CENTER);

        jPanel13.add(jPanel16, java.awt.BorderLayout.WEST);

        basecard.setBackground(new java.awt.Color(204, 102, 255));
        basecard.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 150, 0, 250, new java.awt.Color(204, 102, 255)));
        basecard.setLayout(new java.awt.CardLayout());

        dayCard.setBackground(new java.awt.Color(204, 102, 255));
        dayCard.setLayout(new java.awt.GridLayout(1, 4, 20, 0));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Tháng:");
        dayCard.add(jLabel10);

        cbxDaysMonth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDaysMonthItemStateChanged(evt);
            }
        });
        dayCard.add(cbxDaysMonth);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Năm:");
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        dayCard.add(jLabel11);

        cbxDaysYear.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDaysYearItemStateChanged(evt);
            }
        });
        dayCard.add(cbxDaysYear);

        basecard.add(dayCard, "dayCard");

        monthCard.setBackground(new java.awt.Color(204, 102, 255));
        monthCard.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 100, 0, 100, new java.awt.Color(204, 102, 255)));
        monthCard.setLayout(new java.awt.GridLayout(1, 0));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Năm:");
        jLabel12.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 20, new java.awt.Color(204, 102, 255)));
        monthCard.add(jLabel12);

        cbxMonthYear.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMonthYearItemStateChanged(evt);
            }
        });
        monthCard.add(cbxMonthYear);

        basecard.add(monthCard, "monthCard");

        yearCard.setBackground(new java.awt.Color(204, 102, 255));

        javax.swing.GroupLayout yearCardLayout = new javax.swing.GroupLayout(yearCard);
        yearCard.setLayout(yearCardLayout);
        yearCardLayout.setHorizontalGroup(
            yearCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 326, Short.MAX_VALUE)
        );
        yearCardLayout.setVerticalGroup(
            yearCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 41, Short.MAX_VALUE)
        );

        basecard.add(yearCard, "yearCard");

        jPanel13.add(basecard, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel13, java.awt.BorderLayout.NORTH);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setPreferredSize(new java.awt.Dimension(1469, 500));
        jPanel14.setLayout(new java.awt.GridLayout(1, 0));

        tpBang.setBackground(new java.awt.Color(102, 255, 255));
        tpBang.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tpBang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tpBang.setOpaque(true);

        jPanel15.setLayout(new java.awt.GridLayout(1, 0));

        jPanel18.setLayout(new java.awt.BorderLayout());

        tblTK.setBackground(new java.awt.Color(255, 153, 51));
        tblTK.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblTK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblTK.setRowHeight(24);
        jScrollPane1.setViewportView(tblTK);

        jPanel18.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel19.setBackground(new java.awt.Color(153, 255, 255));
        jPanel19.setPreferredSize(new java.awt.Dimension(1464, 50));
        jPanel19.setLayout(new java.awt.BorderLayout());

        btnXuatExcel.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnXuatExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/excel.png"))); // NOI18N
        btnXuatExcel.setText("Xuất File");
        btnXuatExcel.setPreferredSize(new java.awt.Dimension(180, 29));
        btnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelActionPerformed(evt);
            }
        });
        jPanel19.add(btnXuatExcel, java.awt.BorderLayout.EAST);

        jPanel18.add(jPanel19, java.awt.BorderLayout.PAGE_END);

        jPanel15.add(jPanel18);

        tpBang.addTab("Bảng", jPanel15);

        jPanel17.setLayout(new java.awt.GridLayout(1, 0));

        panelChard.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(240, 240, 240)));
        panelChard.setLayout(new java.awt.BorderLayout());
        jPanel17.add(panelChard);

        tpBang.addTab("Biểu Đồ", jPanel17);

        jPanel14.add(tpBang);

        jPanel8.add(jPanel14, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel8, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Doanh Thu", jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        tblSP.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Mã Vạch", "Tên DM", "Màu", "Đơn Vị Tính", "Size", "Chất Liệu", "Số Lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSP.setRowHeight(24);
        jScrollPane2.setViewportView(tblSP);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(1469, 50));
        jPanel4.setLayout(new java.awt.BorderLayout());

        btnXuatSP.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnXuatSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/excel.png"))); // NOI18N
        btnXuatSP.setText("Xuất File");
        btnXuatSP.setPreferredSize(new java.awt.Dimension(230, 49));
        btnXuatSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatSPActionPerformed(evt);
            }
        });
        jPanel4.add(btnXuatSP, java.awt.BorderLayout.EAST);

        jPanel2.add(jPanel4, java.awt.BorderLayout.SOUTH);

        jTabbedPane1.addTab("Sản Phẩm", jPanel2);

        getContentPane().add(jTabbedPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxChooseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxChooseItemStateChanged
        // TODO add your handling code here:
        int index = cbxChoose.getSelectedIndex();
        switch (index) {
            case 0:
                baseCard.show(basecard, "dayCard");
                addChartDays();
                loadDataDoanhThuDays();
                break;
            case 1:
                baseCard.show(basecard, "monthCard");
                addChartMonths();
                loadDataDoanhThuMonths();
                break;
            case 2:
                baseCard.show(basecard, "yearCard");
                addChartYear();
                loadDataDoanhThuYear();
                break;
        }
    }//GEN-LAST:event_cbxChooseItemStateChanged

    private void cbxDaysMonthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDaysMonthItemStateChanged
        // TODO add your handling code here:
        addChartDays();
        loadDataDoanhThuDays();
    }//GEN-LAST:event_cbxDaysMonthItemStateChanged

    private void cbxDaysYearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDaysYearItemStateChanged
        // TODO add your handling code here:
        addChartDays();
        loadDataDoanhThuDays();
    }//GEN-LAST:event_cbxDaysYearItemStateChanged

    private void cbxMonthYearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMonthYearItemStateChanged
        // TODO add your handling code here:
        addChartMonths();
        loadDataDoanhThuMonths();
    }//GEN-LAST:event_cbxMonthYearItemStateChanged

    private void btnXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelActionPerformed
        try {
            File file = XExcel.xuatExcel(tblTK, "DoanhThu");
            if (file != null) {
                Messeger.alert(this, "Đã tạo xong: " + file.getAbsolutePath());
            } else {
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Messeger.showErrorDialog(null, ex.getMessage(), "Error");
        }
    }//GEN-LAST:event_btnXuatExcelActionPerformed

    private void btnXuatSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatSPActionPerformed
        try {
            File file = XExcel.xuatExcel(tblSP, "SanPham");
            if (file != null) {
                Messeger.alert(this, "Đã xong: " + file.getAbsolutePath());
            } else {
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Messeger.showErrorDialog(null, ex.getMessage(), "Error");
        }
    }//GEN-LAST:event_btnXuatSPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel basecard;
    private javax.swing.JButton btnXuatExcel;
    private javax.swing.JButton btnXuatSP;
    private javax.swing.JComboBox<String> cbxChoose;
    private javax.swing.JComboBox<String> cbxDaysMonth;
    private javax.swing.JComboBox<String> cbxDaysYear;
    private javax.swing.JComboBox<String> cbxMonthYear;
    private javax.swing.JPanel dayCard;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblHuyDay;
    private javax.swing.JLabel lblHuyMonth;
    private javax.swing.JLabel lblHuyYear;
    private javax.swing.JLabel lblMoney;
    private javax.swing.JLabel lblMoneyMonths;
    private javax.swing.JLabel lblMoneyYear;
    private javax.swing.JLabel lblTCDay;
    private javax.swing.JLabel lblTCMonth;
    private javax.swing.JLabel lblTCYear;
    private javax.swing.JPanel monthCard;
    private javax.swing.JPanel panelChard;
    private javax.swing.JTable tblSP;
    private javax.swing.JTable tblTK;
    private javax.swing.JTabbedPane tpBang;
    private javax.swing.JPanel yearCard;
    // End of variables declaration//GEN-END:variables

    private void init() {
        XInternal.uncorated(this);
        this.setPreferredSize(new Dimension(main.getWidth(), main.getHeight()));
        dt_dao = new DoanhThuDao();
        jTabbedPane1.setUI(new CustomTabbedPaneUI());
        jTabbedPane1.setForeground(Color.BLACK);
        jTabbedPane1.setIconAt(0, new javax.swing.ImageIcon(getClass().getResource("/poly/icons/revenue.png")));
        jTabbedPane1.setIconAt(1, new javax.swing.ImageIcon(getClass().getResource("/poly/icons/box.png")));
        tpBang.setUI(new CustomTabbedPaneUI());
        loadMonths();
        loadYears();
        baseCard = (CardLayout) basecard.getLayout();
        baseCard.show(basecard, "dayCard");
        addChartDays();
        loadDataDoanhThuDays();
        loadDataThongKeToDay();
        loadDataSP();
    }

    private void loadYears() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbxDaysYear.getModel();
        DefaultComboBoxModel model1 = (DefaultComboBoxModel) cbxMonthYear.getModel();
        model.removeAllElements();
        model1.removeAllElements();
        List<Integer> list;
        try {
            list = dt_dao.selectYears();
            for (Integer year : list) {
                model.addElement(year);
                model1.addElement(year);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void loadMonths() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbxDaysMonth.getModel();
        model.removeAllElements();
        List<Integer> list;
        try {
            list = dt_dao.selectMonths();
            for (Integer year : list) {
                model.addElement(year);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void addChartYear() {
        try {
            panelChard.removeAll();
            ChartPanel chartPanel = new ChartPanel(dt_dao.createChartYear());
            chartPanel.setMouseZoomable(false);
            panelChard.add(chartPanel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDataDoanhThuYear() {
        DefaultTableModel model = (DefaultTableModel) tblTK.getModel();
        model.setRowCount(0);
        List<Object[]> list = dt_dao.getThongKeYear();
        model.setColumnCount(0);

        Object columns[] = {"Năm", "Số HD", "Số Lượng", "Giá Bán", "Khuyến Mại", "Doanh Thu"};
        for (Object o : columns) {
            model.addColumn(o);
        }
        for (Object[] row : list) {
            model.addRow(row);
        }
    }

    private void addChartDays() {
        if (cbxDaysMonth.getSelectedItem() != null & cbxDaysYear.getSelectedItem() != null) {
            try {
                int month = Integer.parseInt(cbxDaysMonth.getSelectedItem().toString());
                int year = Integer.parseInt(cbxDaysYear.getSelectedItem().toString());
                panelChard.removeAll();
                ChartPanel chartPanel = new ChartPanel(dt_dao.createChart(month, year));
                chartPanel.setMouseZoomable(false);
                panelChard.add(chartPanel);
                pack();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void loadDataDoanhThuDays() {
        DefaultTableModel model = (DefaultTableModel) tblTK.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);

        Object columns[] = {"Ngày", "Số HD", "Số Lượng", "Giá Bán", "Khuyến Mại", "Doanh Thu"};
        for (Object o : columns) {
            model.addColumn(o);
        }

        if (cbxDaysMonth.getSelectedItem() != null & cbxDaysYear.getSelectedItem() != null) {
            int month = Integer.parseInt(cbxDaysMonth.getSelectedItem().toString());
            int year = Integer.parseInt(cbxDaysYear.getSelectedItem().toString());
            List<Object[]> list = dt_dao.getThongKeNgay(month, year);
            for (Object[] row : list) {
                model.addRow(row);
            }
        }
    }

    private void addChartMonths() {
        if (cbxMonthYear.getSelectedItem() != null) {
            try {
                int year = Integer.parseInt(cbxMonthYear.getSelectedItem().toString());
                panelChard.removeAll();
                ChartPanel chartPanel = new ChartPanel(dt_dao.createChartMonths(year));
                chartPanel.setMouseZoomable(false);
                panelChard.add(chartPanel);
                this.pack();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadDataDoanhThuMonths() {
        DefaultTableModel model = (DefaultTableModel) tblTK.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);

        Object columns[] = {"Tháng", "Số HD", "Số Lượng", "Giá Bán", "Khuyến Mại", "Doanh Thu"};
        for (Object o : columns) {
            model.addColumn(o);
        }
        if (cbxMonthYear.getSelectedItem() != null) {
            int year = Integer.parseInt(cbxMonthYear.getSelectedItem().toString());
            List<Object[]> list = dt_dao.getThongKeMonths(year);
            for (Object[] row : list) {
                model.addRow(row);
            }
        }
    }

    private void loadDataThongKeToDay() {
        List<Object> list;
        List<Object> list1;
        List<Object> list2;
        try {
            list = dt_dao.getThongKeToDay();
            list1 = dt_dao.getThongKeToMonth();
            list2 = dt_dao.getThongKeToYear();
            lblMoney.setText(list.get(0).toString());
            lblHuyDay.setText(list.get(2).toString());
            lblTCDay.setText(list.get(1).toString());

            lblMoneyMonths.setText(list1.get(0).toString());
            lblHuyMonth.setText(list1.get(2).toString());
            lblTCMonth.setText(list1.get(1).toString());

            lblMoneyYear.setText(list2.get(0).toString());
            lblHuyYear.setText(list2.get(2).toString());
            lblTCYear.setText(list2.get(1).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            Messeger.showErrorDialog(null, ex.getMessage(), "Error");
        }
    }

    private void loadDataSP() {
        DefaultTableModel model = (DefaultTableModel) tblSP.getModel();
        model.setRowCount(0);
        List<Object[]> list;

        try {
            list = dt_dao.getThongKeSP();
            for (Object[] row : list) {
                model.addRow(row);
            }

            tblSP.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table,
                        Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                    int sl = Integer.parseInt(table.getModel().getValueAt(row, 8).toString());
                    if (sl < 51) {
                        setBackground(Color.RED);
                        setForeground(Color.YELLOW);
                    } else if (sl < 101) {
                        setBackground(Color.YELLOW);
                        setForeground(Color.RED);
                    } else {
                        setBackground(table.getBackground());
                        setForeground(table.getForeground());
                    }
                    return this;
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
            Messeger.showErrorDialog(null, ex.getMessage(), "Error");
        }

    }
}
