/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.newgui;

import java.awt.CardLayout;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import poly.dao.CTHoaDonDao;
import poly.dao.GiaoHangDao;
import poly.dao.HoaDonDao;
import poly.dao.KhachHangDao;
import poly.dao.SanPhamDao;
import poly.entity.CTHoaDon;
import poly.entity.GiaoHang;
import poly.entity.HoaDon;
import poly.entity.KhachHang;
import poly.entity.SanPham;
import poly.helper.CustomTabbedPaneUI;
import poly.helper.Messeger;
import poly.helper.XDate;
import poly.helper.XInternal;

/**
 *
 * @author NTV
 */
public class BanHangFrm extends javax.swing.JInternalFrame {

    static int hoaDonIndex = 2;
    private HoaDonDao daoHD;
    private KhachHangDao daoKH;
    private SanPhamDao daoSP;
    private CTHoaDonDao daoCTHD;
    private GiaoHangDao daoGH;

    public static ArrayList<CTHoaDon> listCTHD;
    static KhachHang k;

    CardLayout cardTable;
    CardLayout cardButton;
    CardLayout cardGH_BH;
    boolean isChoGHSelected = true;

    DefaultTableModel dtmChoGH;
    DefaultTableModel dtmDangGH;
    DefaultTableModel dtmChiTietHoaDon;
    NewMainFrm jFrame;
    
    Locale localeVN = new Locale("vi", "VN");
    NumberFormat df = NumberFormat.getCurrencyInstance(localeVN);

    /**
     * Creates new form BanHangFrm
     */
    public BanHangFrm(JFrame jFrame) {
        initComponents();
        this.jFrame = (NewMainFrm) jFrame;
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

        pnlMain = new javax.swing.JPanel();
        pnlCardGH_BH = new javax.swing.JPanel();
        pnlTabs = new javax.swing.JTabbedPane();
        pnlGiaoHang = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        pnlCTHD = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHDChiTiet = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblMaHD = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        pnlTTHD = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        btnChoGH = new javax.swing.JButton();
        btnDangGH = new javax.swing.JButton();
        pnlButtonCard = new javax.swing.JPanel();
        pnlGiaoHangButton = new javax.swing.JPanel();
        btnGiaoHangTT = new javax.swing.JButton();
        pnlHuyGH = new javax.swing.JPanel();
        btnDaGH = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        btnHuyGiaoHang = new javax.swing.JButton();
        pnlTableCard = new javax.swing.JPanel();
        pnlHoaDonChoGH = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHDChoGiaoHang = new javax.swing.JTable();
        pnlHoaDonDangGH = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHDDangGiaoHang = new javax.swing.JTable();

        pnlMain.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(51, 51, 51)));
        pnlMain.setLayout(new java.awt.BorderLayout());

        pnlCardGH_BH.setLayout(new java.awt.CardLayout());

        pnlTabs.setBackground(new java.awt.Color(0, 204, 102));
        pnlTabs.setForeground(new java.awt.Color(255, 255, 255));
        pnlTabs.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        pnlTabs.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlTabs.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        pnlTabs.setOpaque(true);
        pnlTabs.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pnlTabsStateChanged(evt);
            }
        });
        pnlCardGH_BH.add(pnlTabs, "banHang");

        pnlGiaoHang.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(0, 128, 128));
        jPanel9.setLayout(new java.awt.BorderLayout());

        pnlCTHD.setBackground(new java.awt.Color(0, 128, 128));
        pnlCTHD.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(0, 128, 128)), "Danh sách sản phẩm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Tahoma", 1, 34), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlCTHD.setPreferredSize(new java.awt.Dimension(700, 652));
        pnlCTHD.setLayout(new java.awt.BorderLayout());

        jPanel10.setPreferredSize(new java.awt.Dimension(600, 400));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 672, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        pnlCTHD.add(jPanel10, java.awt.BorderLayout.PAGE_END);

        tblHDChiTiet.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tblHDChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Thành Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHDChiTiet.setRowHeight(40);
        jScrollPane1.setViewportView(tblHDChiTiet);
        if (tblHDChiTiet.getColumnModel().getColumnCount() > 0) {
            tblHDChiTiet.getColumnModel().getColumn(1).setMinWidth(240);
            tblHDChiTiet.getColumnModel().getColumn(1).setPreferredWidth(240);
            tblHDChiTiet.getColumnModel().getColumn(2).setMinWidth(60);
            tblHDChiTiet.getColumnModel().getColumn(2).setPreferredWidth(60);
        }

        pnlCTHD.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(0, 128, 128));
        jPanel2.setPreferredSize(new java.awt.Dimension(672, 50));
        jPanel2.setLayout(new java.awt.BorderLayout());

        lblMaHD.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMaHD.setForeground(new java.awt.Color(255, 153, 153));
        lblMaHD.setText("Mã Hóa Đơn:");
        lblMaHD.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 20, 1, 1, new java.awt.Color(0, 128, 128)));
        lblMaHD.setPreferredSize(new java.awt.Dimension(300, 16));
        jPanel2.add(lblMaHD, java.awt.BorderLayout.LINE_START);

        lblTenKH.setBackground(new java.awt.Color(255, 153, 51));
        lblTenKH.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTenKH.setForeground(new java.awt.Color(255, 153, 51));
        lblTenKH.setText("Tên Khách:");
        lblTenKH.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 20, 1, 1, new java.awt.Color(0, 128, 128)));
        jPanel2.add(lblTenKH, java.awt.BorderLayout.CENTER);

        pnlCTHD.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel9.add(pnlCTHD, java.awt.BorderLayout.LINE_END);

        pnlTTHD.setLayout(new java.awt.BorderLayout());

        jPanel11.setBackground(new java.awt.Color(0, 128, 128));
        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 80, new java.awt.Color(0, 128, 128)));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel12.setBackground(new java.awt.Color(0, 128, 128));
        jPanel12.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(0, 128, 128)));
        jPanel12.setPreferredSize(new java.awt.Dimension(340, 100));
        jPanel12.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        btnChoGH.setBackground(new java.awt.Color(51, 255, 51));
        btnChoGH.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnChoGH.setText("Chờ Giao Hàng");
        btnChoGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChoGHActionPerformed(evt);
            }
        });
        jPanel12.add(btnChoGH);

        btnDangGH.setBackground(new java.awt.Color(204, 153, 0));
        btnDangGH.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnDangGH.setText("Đang Giao Hàng");
        btnDangGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangGHActionPerformed(evt);
            }
        });
        jPanel12.add(btnDangGH);

        jPanel11.add(jPanel12, java.awt.BorderLayout.WEST);

        pnlButtonCard.setBackground(new java.awt.Color(255, 204, 204));
        pnlButtonCard.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(0, 128, 128)));
        pnlButtonCard.setPreferredSize(new java.awt.Dimension(200, 100));
        pnlButtonCard.setLayout(new java.awt.CardLayout());

        pnlGiaoHangButton.setLayout(new java.awt.BorderLayout());

        btnGiaoHangTT.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnGiaoHangTT.setText("Giao Hàng");
        btnGiaoHangTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiaoHangTTActionPerformed(evt);
            }
        });
        pnlGiaoHangButton.add(btnGiaoHangTT, java.awt.BorderLayout.CENTER);

        pnlButtonCard.add(pnlGiaoHangButton, "giaoHang");

        pnlHuyGH.setBackground(new java.awt.Color(255, 204, 204));
        pnlHuyGH.setLayout(new java.awt.BorderLayout());

        btnDaGH.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDaGH.setText("Đã Giao");
        btnDaGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaGHActionPerformed(evt);
            }
        });
        pnlHuyGH.add(btnDaGH, java.awt.BorderLayout.CENTER);

        pnlButtonCard.add(pnlHuyGH, "huyGH");

        jPanel11.add(pnlButtonCard, java.awt.BorderLayout.EAST);

        jPanel14.setBackground(new java.awt.Color(0, 128, 128));
        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING, 10, 10));

        btnHuyGiaoHang.setBackground(new java.awt.Color(204, 0, 0));
        btnHuyGiaoHang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnHuyGiaoHang.setText("Hủy Giao Hàng");
        btnHuyGiaoHang.setPreferredSize(new java.awt.Dimension(200, 80));
        btnHuyGiaoHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyGiaoHangActionPerformed(evt);
            }
        });
        jPanel14.add(btnHuyGiaoHang);

        jPanel11.add(jPanel14, java.awt.BorderLayout.CENTER);

        pnlTTHD.add(jPanel11, java.awt.BorderLayout.PAGE_START);

        pnlTableCard.setBackground(new java.awt.Color(0, 128, 128));
        pnlTableCard.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 10, 10, 0, new java.awt.Color(0, 128, 128)));
        pnlTableCard.setLayout(new java.awt.CardLayout());

        pnlHoaDonChoGH.setLayout(new java.awt.BorderLayout());

        tblHDChoGiaoHang.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tblHDChoGiaoHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Giao Hàng", "Mã Hóa Đơn", "Tên Khách Hàng", "Số Điện Thoại", "Địa Chỉ", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHDChoGiaoHang.setRowHeight(42);
        tblHDChoGiaoHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDChoGiaoHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHDChoGiaoHang);
        if (tblHDChoGiaoHang.getColumnModel().getColumnCount() > 0) {
            tblHDChoGiaoHang.getColumnModel().getColumn(0).setMinWidth(90);
            tblHDChoGiaoHang.getColumnModel().getColumn(0).setPreferredWidth(95);
            tblHDChoGiaoHang.getColumnModel().getColumn(1).setMinWidth(90);
            tblHDChoGiaoHang.getColumnModel().getColumn(1).setPreferredWidth(90);
            tblHDChoGiaoHang.getColumnModel().getColumn(1).setMaxWidth(90);
            tblHDChoGiaoHang.getColumnModel().getColumn(2).setMinWidth(200);
            tblHDChoGiaoHang.getColumnModel().getColumn(2).setPreferredWidth(200);
            tblHDChoGiaoHang.getColumnModel().getColumn(5).setMinWidth(140);
            tblHDChoGiaoHang.getColumnModel().getColumn(5).setPreferredWidth(140);
            tblHDChoGiaoHang.getColumnModel().getColumn(5).setMaxWidth(140);
        }

        pnlHoaDonChoGH.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pnlTableCard.add(pnlHoaDonChoGH, "choGH");

        pnlHoaDonDangGH.setBackground(new java.awt.Color(0, 128, 128));
        pnlHoaDonDangGH.setLayout(new java.awt.BorderLayout());

        tblHDDangGiaoHang.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tblHDDangGiaoHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Giao Hàng", "Mã Hóa Đơn", "Tên Khách Hàng", "Số Điện Thoại", "Địa Chỉ", "Ngày Giao Hàng", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHDDangGiaoHang.setRowHeight(42);
        tblHDDangGiaoHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDDangGiaoHangMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblHDDangGiaoHang);
        if (tblHDDangGiaoHang.getColumnModel().getColumnCount() > 0) {
            tblHDDangGiaoHang.getColumnModel().getColumn(2).setMinWidth(200);
            tblHDDangGiaoHang.getColumnModel().getColumn(2).setPreferredWidth(200);
            tblHDDangGiaoHang.getColumnModel().getColumn(5).setMinWidth(100);
            tblHDDangGiaoHang.getColumnModel().getColumn(5).setPreferredWidth(120);
        }

        pnlHoaDonDangGH.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        pnlTableCard.add(pnlHoaDonDangGH, "dangGH");

        pnlTTHD.add(pnlTableCard, java.awt.BorderLayout.CENTER);

        jPanel9.add(pnlTTHD, java.awt.BorderLayout.CENTER);

        pnlGiaoHang.add(jPanel9, java.awt.BorderLayout.CENTER);

        pnlCardGH_BH.add(pnlGiaoHang, "giaoHang");

        pnlMain.add(pnlCardGH_BH, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnlTabsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pnlTabsStateChanged
        if (pnlTabs.getComponentCount() > 1) {
            HoaDonFrm hd = (HoaDonFrm) pnlTabs.getSelectedComponent();
            if (hd != null) {
                hd.reloadTableSP();
            }
        }
    }//GEN-LAST:event_pnlTabsStateChanged

    private void btnChoGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChoGHActionPerformed
        this.cardButton.show(pnlButtonCard, "giaoHang");
        this.cardTable.show(pnlTableCard, "choGH");
        this.isChoGHSelected = true;
        status();
    }//GEN-LAST:event_btnChoGHActionPerformed

    private void btnDangGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangGHActionPerformed
        this.cardButton.show(pnlButtonCard, "huyGH");
        this.cardTable.show(pnlTableCard, "dangGH");
        this.isChoGHSelected = false;
        status();
    }//GEN-LAST:event_btnDangGHActionPerformed

    private void btnGiaoHangTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiaoHangTTActionPerformed
        if (xacNhanGiaoHang()) {
            return;
        }
    }//GEN-LAST:event_btnGiaoHangTTActionPerformed

    private void btnDaGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaGHActionPerformed
        if (xacNhanDaGiaoHang()) {
            return;
        }
        jFrame.setLblSoLuongDonHang();
    }//GEN-LAST:event_btnDaGHActionPerformed

    private void btnHuyGiaoHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyGiaoHangActionPerformed
        if (xacNhanHuyGiaoHang()) {
            return;
        }
        if (jFrame != null) {
            jFrame.setLblSoLuongDonHang();
        }
    }//GEN-LAST:event_btnHuyGiaoHangActionPerformed

    private void tblHDChoGiaoHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDChoGiaoHangMouseClicked
        if (mouseClickedTblChoGH()) {
            return;
        }
    }//GEN-LAST:event_tblHDChoGiaoHangMouseClicked

    private void tblHDDangGiaoHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDDangGiaoHangMouseClicked
        if (mouseClickedTblDangGH()) {
            return;
        }
    }//GEN-LAST:event_tblHDDangGiaoHangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChoGH;
    private javax.swing.JButton btnDaGH;
    private javax.swing.JButton btnDangGH;
    private javax.swing.JButton btnGiaoHangTT;
    private javax.swing.JButton btnHuyGiaoHang;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JPanel pnlButtonCard;
    private javax.swing.JPanel pnlCTHD;
    private javax.swing.JPanel pnlCardGH_BH;
    private javax.swing.JPanel pnlGiaoHang;
    private javax.swing.JPanel pnlGiaoHangButton;
    private javax.swing.JPanel pnlHoaDonChoGH;
    private javax.swing.JPanel pnlHoaDonDangGH;
    private javax.swing.JPanel pnlHuyGH;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTTHD;
    private javax.swing.JPanel pnlTableCard;
    private javax.swing.JTabbedPane pnlTabs;
    private javax.swing.JTable tblHDChiTiet;
    private javax.swing.JTable tblHDChoGiaoHang;
    private javax.swing.JTable tblHDDangGiaoHang;
    // End of variables declaration//GEN-END:variables

    public void loadHoaDonChoTT() {
        List<HoaDon> list = new ArrayList<>();
        try {
            list = this.daoHD.selectAll();
            boolean a = true;
            for (HoaDon h : list) {
                if (h.getMaTT() == 1) {
                    k = this.daoKH.selectById(h.getMaKH());
                    listCTHD = this.daoCTHD.selectCTHD(h.getMaHD());
                    HoaDonFrm hdpnl = new HoaDonFrm(this.jFrame, pnlTabs);
                    pnlTabs.addTab(k == null ? "Khách lẻ: " : k.getHoTen(), hdpnl);
                    pnlTabs.setSelectedComponent(hdpnl);
                    hdpnl.loadDataToHoaDon();
                    hdpnl.setLabelHoaDon(h);
                    a = false;
                }
            }
            if (a) {
                HoaDonFrm hdpnl = new HoaDonFrm(this.jFrame, pnlTabs);
                pnlTabs.addTab("Khách lẻ ", hdpnl);
                pnlTabs.setSelectedComponent(hdpnl);
            }
        } catch (Exception ex) {
            Messeger.showErrorDialog(null, ex.getMessage(), "Error");
            ex.printStackTrace();
        }
    }

    private void checkPreExit() {
        for (int i = 0; i < pnlTabs.getTabCount(); i++) {
            HoaDonFrm h = (HoaDonFrm) pnlTabs.getComponentAt(i);
            if (!h.getLblHoaDon().getText().equalsIgnoreCase("Hóa đơn trống")) {
                if (Messeger.confirm(this, "Vẫn còn hóa đơn chưa thanh toán! Bạn chắc chắn muốn thoát chứ?")) {
                    this.setVisible(false);
                } else {
                    return;
                }
            } else {
                this.setVisible(false);
            }
        }
    }

    private void init() {
        XInternal.uncorated(this);

        this.daoHD = new HoaDonDao();
        this.daoKH = new KhachHangDao();
        this.daoSP = new SanPhamDao();
        this.daoCTHD = new CTHoaDonDao();
        this.daoGH = new GiaoHangDao();
        this.dtmChiTietHoaDon = (DefaultTableModel) tblHDChiTiet.getModel();
        this.dtmChoGH = (DefaultTableModel) tblHDChoGiaoHang.getModel();
        this.dtmDangGH = (DefaultTableModel) tblHDDangGiaoHang.getModel();

        pnlTabs.setUI(new CustomTabbedPaneUI());

        this.cardButton = (CardLayout) this.pnlButtonCard.getLayout();
        this.cardTable = (CardLayout) this.pnlTableCard.getLayout();
        this.cardGH_BH = (CardLayout) this.pnlCardGH_BH.getLayout();
//        this.cardGH_BH.show(pnlCardGH_BH, "banHang");
//        this.cardButton.show(pnlButtonCard, "giaoHang");
//        this.showGiaoHang(btnDaGH);
//        this.cardTable.show(pnlTableCard, "choGH");
        // Đăng nhập
//        new LoadingFrm(this.jFrame, true, pnlTabs).setVisible(true);
//        loadHoaDonChoTT();
        loadDataToTableChoGH();
        loadDataToTableDangGH();

    }

    public void showGiaoHang(JButton btn) {
        if (btn.getText().equalsIgnoreCase("Giao Hàng")) {
            this.cardGH_BH.show(pnlCardGH_BH, "giaoHang");
            btn.setText("Bán Hàng");
            loadDataToTableChoGH();
            loadDataToTableDangGH();
        } else {
            this.cardGH_BH.show(pnlCardGH_BH, "banHang");
            btn.setText("Giao Hàng");
        }
    }

    private void status() {
        if (isChoGHSelected) {
            this.btnChoGH.setBackground(new Color(51, 255, 51));
            this.btnDangGH.setBackground(new Color(204, 153, 0));
        } else {
            this.btnChoGH.setBackground(new Color(204, 153, 0));
            this.btnDangGH.setBackground(new Color(51, 255, 51));
        }
    }

    public void loadDataToTableChoGH() {
        this.dtmChoGH.setRowCount(0);
        ArrayList<GiaoHang> list = new ArrayList<>();
        try {
            list = this.daoGH.selectListGiaoHangByTrangThai(6);
            for (GiaoHang gh : list) {
                this.dtmChoGH.addRow(new Object[]{
                    gh.getMaGiaoHang(),
                    gh.getMaHoaDon(),
                    gh.getTenKhachHang(),
                    gh.getSoDienThoai(),
                    gh.getDiaChi(),
//                    df.format(gh.getTienShip()),
                    "Chờ giao hàng"
                });
            }
        } catch (Exception ex) {
            Messeger.showErrorDialog(null, ex.getMessage(), "Error");
            ex.printStackTrace();
        }
    }

    public void loadDataToTableDangGH() {
        this.dtmDangGH.setRowCount(0);
        ArrayList<GiaoHang> list = new ArrayList<>();
        try {
            list = this.daoGH.selectListGiaoHangByTrangThai(5);
            for (GiaoHang gh : list) {
                this.dtmDangGH.addRow(new Object[]{
                    gh.getMaGiaoHang(),
                    gh.getMaHoaDon(),
                    gh.getTenKhachHang(),
                    gh.getSoDienThoai(),
                    gh.getDiaChi(),
                    XDate.toString(gh.getNgayGiaoHang(), "dd/MM/yyyy hh:mm:ss"),
//                    df.format(gh.getTienShip()),
                    "Đang giao hàng"
                });
            }
        } catch (Exception ex) {
            Messeger.showErrorDialog(null, ex.getMessage(), "Error");
            ex.printStackTrace();
        }
    }

    private boolean mouseClickedTblChoGH() throws NumberFormatException {
        int index = tblHDChoGiaoHang.getSelectedRow();
        if (index == -1) {
            return true;
        }
        int maHD = Integer.parseInt(tblHDChoGiaoHang.getValueAt(index, 1) + "");
        String name = tblHDChoGiaoHang.getValueAt(index, 2) + "";
        loadDataToTblChiTietHD(maHD, name);
        return false;
    }

    public void loadDataToTblChiTietHD(int maHD, String name) {
        dtmChiTietHoaDon.setRowCount(0);
        ArrayList<CTHoaDon> list = new ArrayList<>();
        this.lblMaHD.setText("Mã Hóa Đơn: " + maHD + "");
        this.lblTenKH.setText("Tên Khách: " + name);
        try {
            list = this.daoCTHD.selectCTHD(maHD);
            for (CTHoaDon c : list) {
                SanPham s = this.daoSP.selectById(c.getMaSP());
                dtmChiTietHoaDon.addRow(new Object[]{
                    c.getMaSP(),
                    s.getTenSanPham(),
                    c.getSoLuong(),
                    df.format(c.getGiaBan()),
                    df.format(c.getSoLuong() * c.getGiaBan())
                });
            }
        } catch (Exception ex) {
            Messeger.showErrorDialog(null, ex.getMessage(), "Error");
            ex.printStackTrace();
        }
    }

    private boolean mouseClickedTblDangGH() throws NumberFormatException {
        int index = tblHDDangGiaoHang.getSelectedRow();
        if (index == -1) {
            return true;
        }
        int maHD = Integer.parseInt(tblHDDangGiaoHang.getValueAt(index, 1) + "");
        String name = tblHDDangGiaoHang.getValueAt(index, 2) + "";
        loadDataToTblChiTietHD(maHD, name);
        return false;
    }

    private boolean xacNhanGiaoHang() throws NumberFormatException {
        int index = tblHDChoGiaoHang.getSelectedRow();
        if (index == -1) {
            Messeger.alert(this, "Chưa chọn đơn hàng nào!");
            return true;
        }
        String maHD = tblHDChoGiaoHang.getValueAt(index, 1) + "";
        int maGH = Integer.parseInt(tblHDChoGiaoHang.getValueAt(index, 0) + "");
        HoaDon hd = new HoaDon();
        GiaoHang gh = new GiaoHang();
        try {
            hd = this.daoHD.selectById(maHD);
            hd.setMaTT(5);
            gh = this.daoGH.selectById(maGH);
            gh.setMaTrangThai(5);
            if (Messeger.confirm(this, "Xác nhận Giao Hàng!")) {
                this.daoHD.update(hd);
                this.daoGH.update(gh);
                Messeger.alert(this, "Thành Công");
                loadDataToTableChoGH();
                loadDataToTableDangGH();
            }
        } catch (Exception ex) {
            Messeger.showErrorDialog(null, ex.getMessage(), "Error");
            ex.printStackTrace();
        }
        return false;
    }

    private boolean xacNhanDaGiaoHang() throws NumberFormatException {
        int index = tblHDDangGiaoHang.getSelectedRow();
        if (index == -1) {
            Messeger.alert(this, "Chưa chọn đơn hàng nào!");
            return true;
        }
        String maHD = tblHDDangGiaoHang.getValueAt(index, 1) + "";
        int maGH = Integer.parseInt(tblHDDangGiaoHang.getValueAt(index, 0) + "");
        HoaDon hd = new HoaDon();
        GiaoHang gh = new GiaoHang();
        try {
            hd = this.daoHD.selectById(maHD);
            hd.setMaTT(2);
            gh = this.daoGH.selectById(maGH);
            gh.setMaTrangThai(2);
            if (Messeger.confirm(this, "Xác nhận Đã Giao Hàng Thành Công!")) {
                this.daoHD.update(hd);
                this.daoGH.update(gh);
                Messeger.alert(this, "Thành Công");
                loadDataToTableChoGH();
                loadDataToTableDangGH();
            }
        } catch (Exception ex) {
            Messeger.showErrorDialog(null, ex.getMessage(), "Error");
            ex.printStackTrace();
        }
        return false;
    }

    private boolean xacNhanHuyGiaoHang() throws NumberFormatException {
        int index = -1;
        if (isChoGHSelected) {
            index = tblHDChoGiaoHang.getSelectedRow();
        } else {
            index = tblHDDangGiaoHang.getSelectedRow();
        }
        if (index == -1) {
            Messeger.alert(this, "Chưa chọn đơn hàng nào!");
            return true;
        }
        String maHD;
        int maGH;
        if (isChoGHSelected) {
            maHD = tblHDChoGiaoHang.getValueAt(index, 1) + "";
            maGH = Integer.parseInt(tblHDChoGiaoHang.getValueAt(index, 0) + "");
        } else {
            maHD = tblHDDangGiaoHang.getValueAt(index, 1) + "";
            maGH = Integer.parseInt(tblHDDangGiaoHang.getValueAt(index, 0) + "");
        }
        HoaDon hd = new HoaDon();
        GiaoHang gh = new GiaoHang();
        try {
            hd = this.daoHD.selectById(maHD);
            hd.setMaTT(3);
            gh = this.daoGH.selectById(maGH);
            gh.setMaTrangThai(3);
            if (Messeger.confirm(this, "Xác nhận Hủy Giao Hàng!")) {
                this.daoHD.update(hd);
                this.daoGH.update(gh);
                for (int i = 0; i < tblHDChiTiet.getRowCount(); i++) {
                    int maSP = Integer.parseInt(tblHDChiTiet.getValueAt(i, 0) + "");
                    int soLuongSP = Integer.parseInt(tblHDChiTiet.getValueAt(i, 2) + "");
                    SanPham sp = new SanPham();
                    sp = this.daoSP.selectById(maSP);
                    sp.setSoLuong(sp.getSoLuong() + soLuongSP);
                    this.daoSP.updateSP(sp);
                }
                Messeger.alert(this, "Thành Công");
                loadDataToTableChoGH();
                loadDataToTableDangGH();
            }
        } catch (Exception ex) {
            Messeger.showErrorDialog(null, ex.getMessage(), "Error");
            ex.printStackTrace();
        }
        return false;
    }

    public JTabbedPane getPnlTabs() {
        return pnlTabs;
    }

}
