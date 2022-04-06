/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicButtonUI;
import poly.dao.CTHoaDonDao;
import poly.dao.GiaoCaDAO;
import poly.dao.HoaDonDao;
import poly.dao.KhachHangDao;
import poly.dao.SanPhamDao;
import poly.entity.CTHoaDon;
import poly.entity.HoaDon;
import poly.entity.KhachHang;
import poly.helper.Auth;
import poly.helper.CustomTabbedPaneUI;
import poly.helper.ImageHelper;
import poly.helper.Messeger;

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
    private GiaoCaDAO gcDAO;
    private GiaoCaJDialog gcDialog;

    static ArrayList<CTHoaDon> listCTHD;
    static KhachHang k;

    /**
     * Creates new form MainFrm
     */
    public MainFrm() {
        initComponents();

        setLocationRelativeTo(null);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);//hiển thị toàn màn hình
        //set icon cho app
        this.setIconImage(ImageHelper.getAppIcon());

        this.daoHD = new HoaDonDao();
        this.daoKH = new KhachHangDao();
        this.daoSP = new SanPhamDao();
        this.daoCTHD = new CTHoaDonDao();
        gcDialog = new GiaoCaJDialog(this, true);

        // Đăng nhập
        new LoginJDialog(this, true).setVisible(true);
        //Add HoaDọnPanel vào jtabpen

        new loading(this, true, pnlTabs).setVisible(true);
//        loadHoaDonChoTT();
        pnlTabs.setUI(new CustomTabbedPaneUI());
        phanQuyen();
        startDongHo();
        mouseHover();
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
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mniThongKe = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mniQLKM = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        mniDangXuat = new javax.swing.JMenuItem();
        jDialog1 = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        pnlMain = new javax.swing.JPanel();
        pnlTabs = new javax.swing.JTabbedPane();
        pnlHeader = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblTenNV = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblDongHo = new javax.swing.JLabel();
        btnGC = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnMinimise = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        pnlFooter = new javax.swing.JPanel();
        btnKhoaManHinh = new javax.swing.JButton();
        btnMenu = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnKhuyenMai = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        btnQLCTSP = new javax.swing.JButton();
        btnQLSP = new javax.swing.JButton();
        btnQLKhachHang = new javax.swing.JButton();
        btnQLHoaDon = new javax.swing.JButton();

        jpmMenu.setBackground(new java.awt.Color(102, 51, 0));
        jpmMenu.setForeground(new java.awt.Color(255, 255, 255));
        jpmMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpmMenu.setPreferredSize(new java.awt.Dimension(300, 200));

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
        mniQLChiTietSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQLChiTietSPActionPerformed(evt);
            }
        });
        jpmMenu.add(mniQLChiTietSP);

        jSeparator2.setBackground(new java.awt.Color(102, 51, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setOpaque(true);
        jpmMenu.add(jSeparator2);

        mniThongKe.setBackground(new java.awt.Color(102, 51, 0));
        mniThongKe.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        mniThongKe.setForeground(new java.awt.Color(255, 255, 255));
        mniThongKe.setText("Thống Kê Doanh Thu");
        mniThongKe.setOpaque(true);
        mniThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniThongKeActionPerformed(evt);
            }
        });
        jpmMenu.add(mniThongKe);

        jSeparator3.setBackground(new java.awt.Color(102, 51, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setOpaque(true);
        jpmMenu.add(jSeparator3);

        mniQLKM.setBackground(new java.awt.Color(102, 51, 0));
        mniQLKM.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        mniQLKM.setForeground(new java.awt.Color(255, 255, 255));
        mniQLKM.setText("Quản lý khuyến mại");
        mniQLKM.setOpaque(true);
        mniQLKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQLKMActionPerformed(evt);
            }
        });
        jpmMenu.add(mniQLKM);

        jSeparator4.setBackground(new java.awt.Color(102, 51, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setOpaque(true);
        jpmMenu.add(jSeparator4);

        mniDangXuat.setBackground(new java.awt.Color(102, 51, 0));
        mniDangXuat.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        mniDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        mniDangXuat.setText("Đăng Xuất");
        mniDangXuat.setOpaque(true);
        mniDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangXuatActionPerformed(evt);
            }
        });
        jpmMenu.add(mniDangXuat);

        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel2.add(jProgressBar1, java.awt.BorderLayout.PAGE_END);

        jDialog1.getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlMain.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(51, 51, 51)));
        pnlMain.setLayout(new java.awt.BorderLayout());

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
        pnlMain.add(pnlTabs, java.awt.BorderLayout.CENTER);

        pnlHeader.setBackground(new java.awt.Color(0, 0, 0));
        pnlHeader.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 153)));
        pnlHeader.setPreferredSize(new java.awt.Dimension(1068, 50));
        pnlHeader.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));
        jPanel6.setPreferredSize(new java.awt.Dimension(456, 50));
        jPanel6.setLayout(new java.awt.BorderLayout());

        lblTenNV.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTenNV.setForeground(new java.awt.Color(255, 255, 51));
        lblTenNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenNV.setText("Mai Xuân Thành");
        lblTenNV.setToolTipText("Tên Nhân Viên");
        lblTenNV.setPreferredSize(new java.awt.Dimension(190, 17));
        jPanel6.add(lblTenNV, java.awt.BorderLayout.LINE_END);

        jPanel8.setBackground(new java.awt.Color(0, 153, 153));
        jPanel8.setLayout(new java.awt.BorderLayout());

        lblLogo.setBackground(new java.awt.Color(255, 153, 51));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/mainLogo.png"))); // NOI18N
        lblLogo.setPreferredSize(new java.awt.Dimension(250, 12));
        jPanel8.add(lblLogo, java.awt.BorderLayout.LINE_START);

        lblDongHo.setFont(new java.awt.Font("Times New Roman", 1, 32)); // NOI18N
        lblDongHo.setForeground(new java.awt.Color(255, 204, 0));
        lblDongHo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDongHo.setText("10");
        jPanel8.add(lblDongHo, java.awt.BorderLayout.CENTER);

        btnGC.setText("Giao Ca");
        btnGC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGCActionPerformed(evt);
            }
        });
        jPanel8.add(btnGC, java.awt.BorderLayout.LINE_END);

        jPanel6.add(jPanel8, java.awt.BorderLayout.CENTER);

        pnlHeader.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));
        jPanel5.setPreferredSize(new java.awt.Dimension(90, 0));
        jPanel5.setLayout(new java.awt.GridLayout(0, 2));

        btnMinimise.setBackground(new java.awt.Color(0, 153, 153));
        btnMinimise.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnMinimise.setForeground(new java.awt.Color(255, 255, 255));
        btnMinimise.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/mimise32.png"))); // NOI18N
        btnMinimise.setToolTipText("Thu nhỏ");
        btnMinimise.setBorder(null);
        btnMinimise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimiseActionPerformed(evt);
            }
        });
        jPanel5.add(btnMinimise);

        btnClose.setBackground(new java.awt.Color(0, 153, 153));
        btnClose.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/cancel32.png"))); // NOI18N
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
        pnlFooter.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 4, 4, 4, new java.awt.Color(255, 153, 51)));
        pnlFooter.setPreferredSize(new java.awt.Dimension(1068, 70));
        pnlFooter.setLayout(new java.awt.BorderLayout());

        btnKhoaManHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/lock32.png"))); // NOI18N
        btnKhoaManHinh.setToolTipText("Khóa Màn Hình");
        btnKhoaManHinh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKhoaManHinh.setPreferredSize(new java.awt.Dimension(85, 41));
        btnKhoaManHinh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKhoaManHinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoaManHinhActionPerformed(evt);
            }
        });
        pnlFooter.add(btnKhoaManHinh, java.awt.BorderLayout.LINE_START);

        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/menu32.png"))); // NOI18N
        btnMenu.setToolTipText("Hệ Thống");
        btnMenu.setPreferredSize(new java.awt.Dimension(85, 41));
        btnMenu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMenuMouseClicked(evt);
            }
        });
        pnlFooter.add(btnMenu, java.awt.BorderLayout.LINE_END);

        jPanel1.setBackground(new java.awt.Color(255, 153, 51));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        btnKhuyenMai.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnKhuyenMai.setForeground(new java.awt.Color(255, 255, 255));
        btnKhuyenMai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/sale36.png"))); // NOI18N
        btnKhuyenMai.setToolTipText("Quản Lý Khuyến Mại");
        btnKhuyenMai.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKhuyenMai.setPreferredSize(new java.awt.Dimension(173, 65));
        btnKhuyenMai.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhuyenMaiActionPerformed(evt);
            }
        });
        jPanel1.add(btnKhuyenMai);

        btnThongKe.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnThongKe.setForeground(new java.awt.Color(255, 255, 255));
        btnThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/chart_36.png"))); // NOI18N
        btnThongKe.setToolTipText("Thống Kê");
        btnThongKe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThongKe.setPreferredSize(new java.awt.Dimension(173, 65));
        btnThongKe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });
        jPanel1.add(btnThongKe);

        btnQLCTSP.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnQLCTSP.setForeground(new java.awt.Color(255, 255, 255));
        btnQLCTSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/size36.png"))); // NOI18N
        btnQLCTSP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnQLCTSP.setPreferredSize(new java.awt.Dimension(173, 65));
        btnQLCTSP.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnQLCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLCTSPActionPerformed(evt);
            }
        });
        jPanel1.add(btnQLCTSP);

        btnQLSP.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnQLSP.setForeground(new java.awt.Color(255, 255, 255));
        btnQLSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/new-product36.png"))); // NOI18N
        btnQLSP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnQLSP.setPreferredSize(new java.awt.Dimension(133, 65));
        btnQLSP.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnQLSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLSPActionPerformed(evt);
            }
        });
        jPanel1.add(btnQLSP);

        btnQLKhachHang.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnQLKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btnQLKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/customer36.png"))); // NOI18N
        btnQLKhachHang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnQLKhachHang.setPreferredSize(new java.awt.Dimension(133, 65));
        btnQLKhachHang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnQLKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLKhachHangActionPerformed(evt);
            }
        });
        jPanel1.add(btnQLKhachHang);

        btnQLHoaDon.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnQLHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnQLHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/budget36.png"))); // NOI18N
        btnQLHoaDon.setToolTipText("Quản lý hóa đơn");
        btnQLHoaDon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnQLHoaDon.setPreferredSize(new java.awt.Dimension(133, 65));
        btnQLHoaDon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnQLHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLHoaDonActionPerformed(evt);
            }
        });
        jPanel1.add(btnQLHoaDon);

        pnlFooter.add(jPanel1, java.awt.BorderLayout.CENTER);

        pnlMain.add(pnlFooter, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMinimiseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimiseActionPerformed
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimiseActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        checkPreExit();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuMouseClicked
        jpmMenu.show(btnMenu, -190, -190);

    }//GEN-LAST:event_btnMenuMouseClicked

    private void mniQLSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQLSanPhamActionPerformed
        new QLSanPhamJDialog(this, true).setVisible(true);
    }//GEN-LAST:event_mniQLSanPhamActionPerformed

    private void btnQLCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLCTSPActionPerformed
        new QL_CTSPJdialog(this, true).setVisible(true);
    }//GEN-LAST:event_btnQLCTSPActionPerformed

    private void btnKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhuyenMaiActionPerformed
        new QL_KhuyenMaiJDiaLog(this, true).setVisible(true);
    }//GEN-LAST:event_btnKhuyenMaiActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        new ThongKeJDialog(this, true).setVisible(true);
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void mniDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangXuatActionPerformed
        new LoginJDialog(this, true).setVisible(true);
        phanQuyen();
    }//GEN-LAST:event_mniDangXuatActionPerformed

    private void mniQLChiTietSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQLChiTietSPActionPerformed
        new QL_CTSPJdialog(this, true).setVisible(true);
    }//GEN-LAST:event_mniQLChiTietSPActionPerformed

    private void mniThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniThongKeActionPerformed
        new ThongKeJDialog(this, true).setVisible(true);
    }//GEN-LAST:event_mniThongKeActionPerformed

    private void mniQLKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQLKMActionPerformed
        new QL_KhuyenMaiJDiaLog(this, true).setVisible(true);
    }//GEN-LAST:event_mniQLKMActionPerformed

    private void pnlTabsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pnlTabsStateChanged
        if (pnlTabs.getComponentCount() > 1) {
            HoaDonJPanel hd = (HoaDonJPanel) pnlTabs.getSelectedComponent();
            if (hd != null) {
                hd.reloadTableSP();
            }
        }
    }//GEN-LAST:event_pnlTabsStateChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        checkPreExit();
    }//GEN-LAST:event_formWindowClosing

    private void btnKhoaManHinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoaManHinhActionPerformed
        new KhoaManHinh(this, true).setVisible(true);
    }//GEN-LAST:event_btnKhoaManHinhActionPerformed

    private void btnQLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLSPActionPerformed
        new QLSanPhamJDialog(this, true).setVisible(true);
    }//GEN-LAST:event_btnQLSPActionPerformed

    private void btnQLKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLKhachHangActionPerformed
        new QuanLyKhachHangJDialog(this, true).setVisible(true);
    }//GEN-LAST:event_btnQLKhachHangActionPerformed

    private void btnQLHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLHoaDonActionPerformed
        new QLHoaDonJDialog(this, true).setVisible(true);
    }//GEN-LAST:event_btnQLHoaDonActionPerformed

    private void btnGCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGCActionPerformed
        // TODO add your handling code here:
        gcDialog.nhanCa();
        gcDialog.setVisible(true);

    }//GEN-LAST:event_btnGCActionPerformed

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
    private javax.swing.JButton btnGC;
    private javax.swing.JButton btnKhoaManHinh;
    private javax.swing.JButton btnKhuyenMai;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnMinimise;
    private javax.swing.JButton btnQLCTSP;
    private javax.swing.JButton btnQLHoaDon;
    private javax.swing.JButton btnQLKhachHang;
    private javax.swing.JButton btnQLSP;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu jpmMenu;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JMenuItem mniDangXuat;
    private javax.swing.JMenuItem mniQLChiTietSP;
    private javax.swing.JMenuItem mniQLKM;
    private javax.swing.JMenuItem mniQLSanPham;
    private javax.swing.JMenuItem mniThongKe;
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

    private void checkPreExit() {
        for (int i = 0; i < pnlTabs.getTabCount(); i++) {
            HoaDonJPanel h = (HoaDonJPanel) pnlTabs.getComponentAt(i);
            if (!h.getLblHoaDon().getText().equalsIgnoreCase("Hóa đơn trống")) {
                if (Messeger.confirm(this, "Vẫn còn hóa đơn chưa thanh toán! Bạn chắc chắn muốn thoát chứ?")) {
                    System.exit(0);
                } else {
                    return;
                }
            } else {
                System.exit(0);
            }
        }
    }

    private void phanQuyen() {
        this.lblTenNV.setText(Auth.user.getHoTen());
    }

    private void startDongHo() {
        //Hiển thị đồng hồ
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date now = new Date();
                SimpleDateFormat fm = new SimpleDateFormat("hh:mm:ss a");
                String txt = fm.format(now);
                lblDongHo.setText(txt);
            }
        }).start();
    }

    private void mouseHover() {
        //Hiệu ứng di chuột vào các button menu
        JButton[] btns = {btnKhoaManHinh, btnKhuyenMai, btnMenu, btnQLCTSP, btnQLKhachHang, btnQLSP, btnQLHoaDon, btnThongKe, btnClose, btnMinimise};
        for (JButton btn : btns) {
            if (btn != btnClose && btn != btnMinimise) {
                btn.setBackground(new Color(255, 153, 51));
            } else {
                btn.setBackground(new Color(0, 153, 153));
            }
            btn.setUI(new BasicButtonUI());
            btn.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    Cursor cursor = new Cursor(Cursor.HAND_CURSOR); // HAND CURSOR
                    setCursor(cursor);
                    if (btn != btnClose && btn != btnMinimise) {
                        btn.setBackground(new Color(0, 153, 153));
                    } else {
                        btn.setBackground(new Color(255, 153, 51));
                    }
                    btn.setHorizontalAlignment(SwingConstants.CENTER);
                    if (btn == btnKhuyenMai) {
                        btn.setText("Quản lý khuyến mại");
                    } else if (btn == btnQLCTSP) {
                        btn.setText("Quản lý chi tiết sản phẩm");
                    } else if (btn == btnQLKhachHang) {
                        btn.setText("Quản lý khách hàng");
                    } else if (btn == btnQLSP) {
                        btn.setText("Quản lý sản phẩm");
                    } else if (btn == btnQLHoaDon) {
                        btn.setText("Quản lý hóa đơn");
                    } else if (btn == btnThongKe) {
                        btn.setText("Thống Kê");
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR); // HAND CURSOR
                    setCursor(cursor);
                    if (btn != btnClose && btn != btnMinimise) {
                        btn.setBackground(new Color(255, 153, 51));
                    } else {
                        btn.setBackground(new Color(0, 153, 153));
                    }

                    if (btn != btnKhoaManHinh && btn != btnMenu) {
                        btn.setText("");
                    }
                }
            });
        }
    }

}
