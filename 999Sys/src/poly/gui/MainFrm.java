/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import poly.dao.CTHoaDonDao;
import poly.dao.HoaDonDao;
import poly.dao.KhachHangDao;
import poly.dao.SanPhamDao;
import poly.entity.CTHoaDon;
import poly.entity.HoaDon;
import poly.entity.KhachHang;

/**
 *
 * @author NTV
 */
public class MainFrm extends javax.swing.JFrame {

    static int hoaDonIndex = 2;
    private HoaDonDao daoHD;
    private KhachHangDao daoKH;
    private SanPhamDao daoSP;
    private CTHoaDonDao daoCTHD;

    static ArrayList<CTHoaDon> listCTHD;
    static KhachHang k;

    /**
     * Creates new form MainFrm
     */
    public MainFrm() {
        initComponents();

        setLocationRelativeTo(null);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);//hiển thị toàn màn hình

        this.daoHD = new HoaDonDao();
        this.daoKH = new KhachHangDao();
        this.daoSP = new SanPhamDao();
        this.daoCTHD = new CTHoaDonDao();
        // Đăng nhập
        new LoginJDialog(this, true).setVisible(true);
        //Add HoaDọnPanel vào jtabpen

        loadHoaDonChoTT();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpmMenu = new javax.swing.JPopupMenu();
        mniQLSanPham = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mniQLChiTietSP = new javax.swing.JMenuItem();
        pnlTabs = new javax.swing.JTabbedPane();
        pnlMain = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblTenNV = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnThemHoaDon = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnMinimise = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        pnlFooter = new javax.swing.JPanel();
        btnKhoaManHinh = new javax.swing.JButton();
        btnMenu = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnKhuyenMai = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jpmMenu.setBackground(new java.awt.Color(102, 51, 0));
        jpmMenu.setForeground(new java.awt.Color(255, 255, 255));
        jpmMenu.setPreferredSize(new java.awt.Dimension(200, 200));

        mniQLSanPham.setBackground(new java.awt.Color(102, 51, 0));
        mniQLSanPham.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        mniQLSanPham.setForeground(new java.awt.Color(255, 255, 255));
        mniQLSanPham.setText("Quản Lý Sản Phẩm");
        mniQLSanPham.setOpaque(true);
        mniQLSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQLSanPhamActionPerformed(evt);
            }
        });
        jpmMenu.add(mniQLSanPham);

        jSeparator1.setBackground(new java.awt.Color(102, 51, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOpaque(true);
        jpmMenu.add(jSeparator1);

        mniQLChiTietSP.setBackground(new java.awt.Color(102, 51, 0));
        mniQLChiTietSP.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        mniQLChiTietSP.setForeground(new java.awt.Color(255, 255, 255));
        mniQLChiTietSP.setText("Quản Lý Chi Tiết sản phẩm");
        mniQLChiTietSP.setOpaque(true);
        jpmMenu.add(mniQLChiTietSP);

        pnlTabs.setBackground(new java.awt.Color(255, 204, 153));
        pnlTabs.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        pnlTabs.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pnlTabsStateChanged(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        pnlMain.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(51, 51, 51)));
        pnlMain.setLayout(new java.awt.BorderLayout());

        pnlHeader.setBackground(new java.awt.Color(0, 0, 0));
        pnlHeader.setPreferredSize(new java.awt.Dimension(1068, 50));
        pnlHeader.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(255, 153, 51));
        jPanel6.setPreferredSize(new java.awt.Dimension(456, 50));
        jPanel6.setLayout(new java.awt.BorderLayout());

        lblTenNV.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTenNV.setForeground(new java.awt.Color(255, 255, 255));
        lblTenNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenNV.setText("Mai Xuân Thành");
        lblTenNV.setToolTipText("Tên Nhân Viên");
        lblTenNV.setPreferredSize(new java.awt.Dimension(160, 17));
        jPanel6.add(lblTenNV, java.awt.BorderLayout.LINE_END);

        jPanel8.setBackground(new java.awt.Color(255, 153, 51));
        jPanel8.setLayout(new java.awt.BorderLayout());
        jPanel6.add(jPanel8, java.awt.BorderLayout.CENTER);

        pnlHeader.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel7.setBackground(new java.awt.Color(255, 153, 51));
        jPanel7.setPreferredSize(new java.awt.Dimension(250, 0));
        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        btnThemHoaDon.setBackground(new java.awt.Color(153, 51, 0));
        btnThemHoaDon.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnThemHoaDon.setText("Thêm Hóa Đơn");
        btnThemHoaDon.setBorder(null);
        btnThemHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHoaDonActionPerformed(evt);
            }
        });
        jPanel7.add(btnThemHoaDon);

        pnlHeader.add(jPanel7, java.awt.BorderLayout.WEST);

        jPanel5.setBackground(new java.awt.Color(255, 153, 51));
        jPanel5.setPreferredSize(new java.awt.Dimension(90, 0));
        jPanel5.setLayout(new java.awt.GridLayout(0, 2));

        btnMinimise.setBackground(new java.awt.Color(0, 255, 0));
        btnMinimise.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnMinimise.setForeground(new java.awt.Color(255, 255, 255));
        btnMinimise.setText("--");
        btnMinimise.setToolTipText("Thu nhỏ");
        btnMinimise.setBorder(null);
        btnMinimise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimiseActionPerformed(evt);
            }
        });
        jPanel5.add(btnMinimise);

        btnClose.setBackground(new java.awt.Color(255, 51, 51));
        btnClose.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setText("X");
        btnClose.setToolTipText("Thoát");
        btnClose.setBorder(null);
        btnClose.setHideActionText(true);
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        jPanel5.add(btnClose);

        pnlHeader.add(jPanel5, java.awt.BorderLayout.EAST);

        pnlMain.add(pnlHeader, java.awt.BorderLayout.PAGE_START);

        pnlFooter.setBackground(new java.awt.Color(255, 153, 51));
        pnlFooter.setPreferredSize(new java.awt.Dimension(1068, 50));
        pnlFooter.setLayout(new java.awt.BorderLayout());

        btnKhoaManHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/password32.png"))); // NOI18N
        btnKhoaManHinh.setToolTipText("Khóa Màn Hình");
        pnlFooter.add(btnKhoaManHinh, java.awt.BorderLayout.LINE_START);

        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/checklist.png"))); // NOI18N
        btnMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMenuMouseClicked(evt);
            }
        });
        pnlFooter.add(btnMenu, java.awt.BorderLayout.LINE_END);

        jPanel1.setBackground(new java.awt.Color(255, 153, 51));

        btnKhuyenMai.setText("Quản Lý Khuyến Mại");
        btnKhuyenMai.setToolTipText("Quản Lý Khuyến Mại");
        btnKhuyenMai.setPreferredSize(new java.awt.Dimension(173, 40));
        btnKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhuyenMaiActionPerformed(evt);
            }
        });
        jPanel1.add(btnKhuyenMai);

        btnThongKe.setText("Thống Kê");
        btnThongKe.setToolTipText("Thống Kê");
        btnThongKe.setPreferredSize(new java.awt.Dimension(173, 40));
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });
        jPanel1.add(btnThongKe);

        jButton3.setText("jButton1");
        jButton3.setPreferredSize(new java.awt.Dimension(73, 40));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);

        jButton4.setText("jButton1");
        jButton4.setPreferredSize(new java.awt.Dimension(73, 40));
        jPanel1.add(jButton4);

        jButton5.setText("jButton1");
        jButton5.setPreferredSize(new java.awt.Dimension(73, 40));
        jPanel1.add(jButton5);

        pnlFooter.add(jPanel1, java.awt.BorderLayout.CENTER);

        pnlMain.add(pnlFooter, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMinimiseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimiseActionPerformed
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimiseActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnThemHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHoaDonActionPerformed
        HoaDonJPanel hdpnl = new HoaDonJPanel(pnlTabs);

        pnlTabs.addTab("Khách lẻ 0" + hoaDonIndex, hdpnl);
        hoaDonIndex++;
        pnlTabs.setSelectedComponent(hdpnl);
    }//GEN-LAST:event_btnThemHoaDonActionPerformed

    private void btnMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuMouseClicked
        jpmMenu.show(btnMenu, -160, -160);

    }//GEN-LAST:event_btnMenuMouseClicked

    private void mniQLSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQLSanPhamActionPerformed
        new QLSanPhamJDialog(this, true).setVisible(true);
    }//GEN-LAST:event_mniQLSanPhamActionPerformed

    private void pnlTabsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pnlTabsStateChanged
        if (pnlTabs.getComponentCount() > 1) {
            HoaDonJPanel hd = (HoaDonJPanel) pnlTabs.getSelectedComponent();
            if (hd != null) {
                hd.reloadTableSP();
            }
        }
    }//GEN-LAST:event_pnlTabsStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhuyenMaiActionPerformed
        new QL_KhuyenMaiJDiaLog(this, true).setVisible(true);
    }//GEN-LAST:event_btnKhuyenMaiActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        new DoanhThuJDialog(this, true).setVisible(true);
    }//GEN-LAST:event_btnThongKeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnKhoaManHinh;
    private javax.swing.JButton btnKhuyenMai;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnMinimise;
    private javax.swing.JButton btnThemHoaDon;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu jpmMenu;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JMenuItem mniQLChiTietSP;
    private javax.swing.JMenuItem mniQLSanPham;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTabbedPane pnlTabs;
    // End of variables declaration//GEN-END:variables

    private void loadHoaDonChoTT() {
        List<HoaDon> list = new ArrayList<>();
        try {
            list = this.daoHD.selectAll();
            boolean a = true;
            for (HoaDon h : list) {
                if (h.getMaTT() == 1) {
                    k = this.daoKH.selectById(h.getMaKH());
                    listCTHD = this.daoCTHD.selectCTHD(h.getMaHD());
                    HoaDonJPanel hdpnl = new HoaDonJPanel(pnlTabs);
                    pnlTabs.addTab(k == null ? "Khách lẻ: " : k.getHoTen(), hdpnl);
                    pnlTabs.setSelectedComponent(hdpnl);
                    hdpnl.loadDataToHoaDon();
                    hdpnl.setLabelHoaDon(h);
                    a = false;
                }
            }
            if (a) {
                HoaDonJPanel hdpnl = new HoaDonJPanel(pnlTabs);
                pnlTabs.addTab("Khách lẻ ", hdpnl);
                pnlTabs.setSelectedComponent(hdpnl);
            }
        } catch (Exception ex) {
            Logger.getLogger(MainFrm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
