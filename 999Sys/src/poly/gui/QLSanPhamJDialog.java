/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import poly.dao.ChatLieuDao;
import poly.dao.DanhMucDao;
import poly.dao.DonViTinhDao;
import poly.dao.MaMauDao;
import poly.dao.SanPhamDao;
import poly.dao.SizeDao;
import poly.entity.ChatLieu;
import poly.entity.DanhMuc;
import poly.entity.DonViTinh;
import poly.entity.MauSac;
import poly.entity.SanPham;
import poly.entity.Size;
import poly.helper.ButtonColumn;
import poly.helper.ImageColumn;
import poly.helper.ImageHelper;
import poly.helper.Messeger;
import poly.helper.XDate;
import poly.helper.XExcel;
import poly.helper.XValidate;

/**
 *
 * @author XUÂN THÀNH
 */
public class QLSanPhamJDialog extends javax.swing.JDialog {

    private JFileChooser fileChooser;

    DefaultTableModel tableModel;
    DefaultTableModel tableDeletedModel;
    DefaultComboBoxModel<DanhMuc> dCBDM;
    DefaultComboBoxModel<MauSac> dCBMS;
    DefaultComboBoxModel<ChatLieu> dCBCL;
    DefaultComboBoxModel<Size> dCBSize;
    DefaultComboBoxModel<DanhMuc> dCBTimDM;
    DefaultComboBoxModel<MauSac> dCBTimMS;
    DefaultComboBoxModel<ChatLieu> dCBTimCL;
    DefaultComboBoxModel<Size> dCBTimSize;
    DefaultComboBoxModel<DonViTinh> dCBDVT;

    DanhMucDao daoDM;
    MaMauDao daoMauSac;
    ChatLieuDao daoCL;
    SizeDao daoSize;
    DonViTinhDao daoDVT;
    SanPhamDao daoSP;

    Thread loadData;

    /**
     * Creates new form QL_SanPham
     */
    public QLSanPhamJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        //full màn hình
//        setSize(parent.getWidth(), parent.getHeight());
        setLocationRelativeTo(null);

        this.themDMJDialog.setModal(modal);
        this.themChatLieuJDialog.setModal(modal);
        this.themDVTJDialog.setModal(modal);
        this.themMauSacJDialog.setModal(modal);
        this.themSizeJDialog.setModal(modal);
        this.DaXoaJDialog.setModal(modal);
        this.loadDataJDialog.setModal(modal);

        this.daoCL = new ChatLieuDao();
        this.daoDM = new DanhMucDao();
        this.daoDVT = new DonViTinhDao();
        this.daoMauSac = new MaMauDao();
        this.daoSize = new SizeDao();
        this.daoSP = new SanPhamDao();

        this.tableModel = new DefaultTableModel();
        this.tableDeletedModel = new DefaultTableModel();

        this.dCBCL = new DefaultComboBoxModel<>();
        this.dCBDM = new DefaultComboBoxModel<>();
        this.dCBMS = new DefaultComboBoxModel<>();
        this.dCBSize = new DefaultComboBoxModel<>();
        this.dCBTimCL = new DefaultComboBoxModel<>();
        this.dCBTimDM = new DefaultComboBoxModel<>();
        this.dCBTimMS = new DefaultComboBoxModel<>();
        this.dCBTimSize = new DefaultComboBoxModel<>();
        this.dCBDVT = new DefaultComboBoxModel<>();

        this.tableModel = (DefaultTableModel) tblSanPham.getModel();
        this.tableDeletedModel = (DefaultTableModel) tblDaXoa.getModel();

        this.dCBCL = (DefaultComboBoxModel) cbbChatLieu.getModel();
        this.dCBDM = (DefaultComboBoxModel) cbbDanhMuc.getModel();
        this.dCBMS = (DefaultComboBoxModel) cbbMau.getModel();
        this.dCBSize = (DefaultComboBoxModel) cbbSize.getModel();
        this.dCBDVT = (DefaultComboBoxModel) cbbDonViTinh.getModel();

        this.dCBTimCL = (DefaultComboBoxModel) cbbTimChatLieu.getModel();
        this.dCBTimDM = (DefaultComboBoxModel) cbbTimDanhMuc.getModel();
        this.dCBTimMS = (DefaultComboBoxModel) cbbTimMau.getModel();
        this.dCBTimSize = (DefaultComboBoxModel) cbbTimSize.getModel();

        this.fileChooser = new JFileChooser();

        addButtonToTable();
        tblSanPham.getColumn("AnhSP").setCellRenderer(new ImageColumn());
        tblDaXoa.getColumn("AnhSP").setCellRenderer(new ImageColumn());
        loadDataToCBBCL();
        loadDataToCBBDM();
        loadDataToCBBMau();
        loadDataToCBBSize();
        loadDataToCBBDVT();
        Object[] objKey = {"%%", "%%", "%%", "%%", "%%", "%%", "%%"};
        loadDataToTable(objKey);
    }

    private void addButtonToTable() {

        //Viết mã sử lỹ cho sự kiện xóa ở đây
        Action delete = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                int maSP = Integer.parseInt((((DefaultTableModel) table.getModel()).getValueAt(modelRow, 0)) + "");

                if (Messeger.confirm(null, "Xác nhận xóa sản phẩm có mã: " + maSP + "?")) {
                    try {
                        daoSP.delete(maSP);
                        ((DefaultTableModel) table.getModel()).removeRow(modelRow);
                        Object[] objKey = {"%%", "%%", "%%", "%%", "%%", "%%", "%%"};
                        loadDataToTable(objKey);
                    } catch (Exception ex) {
                        Logger.getLogger(QLSanPhamJDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        //khởi tạo buttonColum để tạo button có sự kiện xóa vào bảng sản phẩm ở cột 12 đặt tên là xóa
        ButtonColumn buttonColumn = new ButtonColumn(tblSanPham, delete, 13, "Xóa", new javax.swing.ImageIcon(getClass().getResource("/poly/icons/Exit32.png")));
        buttonColumn.setMnemonic(KeyEvent.VK_D);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        themDMJDialog = new javax.swing.JDialog();
        jPanel13 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        txtTenDanhMucMoi = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        btnThemDM = new javax.swing.JButton();
        btnHuyThemDM = new javax.swing.JButton();
        DaXoaJDialog = new javax.swing.JDialog();
        jPanel23 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDaXoa = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        btnKhoiPhuc = new javax.swing.JButton();
        btnKhoiPhucTatCa = new javax.swing.JButton();
        themDVTJDialog = new javax.swing.JDialog();
        jPanel26 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        txtTenDVTMoi = new javax.swing.JTextField();
        jPanel41 = new javax.swing.JPanel();
        btnThemDVTMoi = new javax.swing.JButton();
        btnHuyThemDVTMoi = new javax.swing.JButton();
        themSizeJDialog = new javax.swing.JDialog();
        jPanel42 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        txtMaSize = new javax.swing.JTextField();
        txtLoaiSize = new javax.swing.JTextField();
        jPanel46 = new javax.swing.JPanel();
        btnThemSizeMoi = new javax.swing.JButton();
        btnHuyThemSizeMoi = new javax.swing.JButton();
        themMauSacJDialog = new javax.swing.JDialog();
        jPanel47 = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jPanel49 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel50 = new javax.swing.JPanel();
        txtMauMoi = new javax.swing.JTextField();
        jPanel51 = new javax.swing.JPanel();
        btnThemMauMoi = new javax.swing.JButton();
        btnHuyThemMau = new javax.swing.JButton();
        themChatLieuJDialog = new javax.swing.JDialog();
        jPanel52 = new javax.swing.JPanel();
        jPanel53 = new javax.swing.JPanel();
        jPanel54 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel55 = new javax.swing.JPanel();
        txtTenChatLieuMoi = new javax.swing.JTextField();
        jPanel56 = new javax.swing.JPanel();
        btnThemChatLieuMoi = new javax.swing.JButton();
        btnHuyThemChatLieuMoi = new javax.swing.JButton();
        loadDataJDialog = new javax.swing.JDialog();
        jPanel57 = new javax.swing.JPanel();
        pgbLoadingData = new javax.swing.JProgressBar();
        lblStatus = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        btnChonAnh = new javax.swing.JButton();
        btnXoaAnh = new javax.swing.JButton();
        lblAnh = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        btnNhapExcel = new javax.swing.JButton();
        btnXuatExcel = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        btnLuu = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnThungRac = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        cbbTimDanhMuc = new javax.swing.JComboBox<>();
        cbbTimMau = new javax.swing.JComboBox<>();
        cbbTimSize = new javax.swing.JComboBox<>();
        cbbTimChatLieu = new javax.swing.JComboBox<>();
        btnTimKiem = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        jPanel35 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cbbDanhMuc = new javax.swing.JComboBox<>();
        btnThemDanhMuc = new javax.swing.JButton();
        jPanel37 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtMaVach = new javax.swing.JTextField();
        jPanel33 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cbbDonViTinh = new javax.swing.JComboBox<>();
        btnThemDVT = new javax.swing.JButton();
        jPanel34 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jPanel31 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbbSize = new javax.swing.JComboBox<>();
        btnThemSize = new javax.swing.JButton();
        jPanel32 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JSpinner();
        jPanel27 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbbMau = new javax.swing.JComboBox<>();
        btnThemMau = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JSpinner();
        jPanel28 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cbbChatLieu = new javax.swing.JComboBox<>();
        btnThemChatLieu = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JSpinner();
        jPanel29 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtNgayNhap = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(new java.awt.BorderLayout());

        jPanel17.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(204, 204, 0)));
        jPanel17.setPreferredSize(new java.awt.Dimension(428, 70));
        jPanel17.setLayout(new java.awt.BorderLayout());

        jPanel18.setBackground(new java.awt.Color(204, 204, 0));
        jPanel18.setPreferredSize(new java.awt.Dimension(128, 147));
        jPanel18.setLayout(new java.awt.BorderLayout());

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setText("Tên danh mục:");
        jPanel18.add(jLabel15, java.awt.BorderLayout.CENTER);

        jPanel17.add(jPanel18, java.awt.BorderLayout.WEST);

        jPanel19.setBackground(new java.awt.Color(204, 204, 0));
        jPanel19.setLayout(new java.awt.BorderLayout());

        txtTenDanhMucMoi.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel19.add(txtTenDanhMucMoi, java.awt.BorderLayout.CENTER);

        jPanel17.add(jPanel19, java.awt.BorderLayout.CENTER);

        jPanel13.add(jPanel17, java.awt.BorderLayout.PAGE_START);

        jPanel20.setBackground(new java.awt.Color(255, 204, 153));
        jPanel20.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(255, 204, 153)));
        jPanel20.setLayout(new java.awt.GridLayout(1, 0, 30, 0));

        btnThemDM.setText("Thêm");
        btnThemDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDMActionPerformed(evt);
            }
        });
        jPanel20.add(btnThemDM);

        btnHuyThemDM.setText("Hủy");
        btnHuyThemDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyThemDMActionPerformed(evt);
            }
        });
        jPanel20.add(btnHuyThemDM);

        jPanel13.add(jPanel20, java.awt.BorderLayout.CENTER);

        themDMJDialog.getContentPane().add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel23.setBackground(new java.awt.Color(0, 0, 0));
        jPanel23.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));
        jPanel23.setLayout(new java.awt.BorderLayout());

        jLabel16.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("SẢN PHẨM ĐÃ XÓA");
        jPanel23.add(jLabel16, java.awt.BorderLayout.PAGE_START);

        jPanel24.setBackground(new java.awt.Color(0, 0, 0));
        jPanel24.setLayout(new java.awt.BorderLayout());

        tblDaXoa.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblDaXoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ma SP", "Ma Vach", "Ten SP", "Gia Ban", "Gia Nhap", "So Luong", "Danh Muc", "DVT", "Size", "Mau", "Chat Lieu", "Ngay Nhap", "AnhSP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDaXoa.setGridColor(new java.awt.Color(0, 0, 0));
        tblDaXoa.setRowHeight(104);
        tblDaXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDaXoaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDaXoa);
        if (tblDaXoa.getColumnModel().getColumnCount() > 0) {
            tblDaXoa.getColumnModel().getColumn(12).setMinWidth(84);
            tblDaXoa.getColumnModel().getColumn(12).setPreferredWidth(84);
            tblDaXoa.getColumnModel().getColumn(12).setMaxWidth(84);
        }

        jPanel24.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel23.add(jPanel24, java.awt.BorderLayout.CENTER);

        jPanel25.setBackground(new java.awt.Color(255, 153, 102));
        jPanel25.setPreferredSize(new java.awt.Dimension(858, 50));

        btnKhoiPhuc.setText("Khôi phục");
        btnKhoiPhuc.setMinimumSize(new java.awt.Dimension(149, 21));
        btnKhoiPhuc.setPreferredSize(new java.awt.Dimension(149, 41));
        btnKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucActionPerformed(evt);
            }
        });
        jPanel25.add(btnKhoiPhuc);

        btnKhoiPhucTatCa.setText("Khôi phục tất cả");
        btnKhoiPhucTatCa.setPreferredSize(new java.awt.Dimension(149, 41));
        btnKhoiPhucTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucTatCaActionPerformed(evt);
            }
        });
        jPanel25.add(btnKhoiPhucTatCa);

        jPanel23.add(jPanel25, java.awt.BorderLayout.PAGE_END);

        DaXoaJDialog.getContentPane().add(jPanel23, java.awt.BorderLayout.CENTER);

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setLayout(new java.awt.BorderLayout());

        jPanel38.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(204, 204, 0)));
        jPanel38.setPreferredSize(new java.awt.Dimension(428, 70));
        jPanel38.setLayout(new java.awt.BorderLayout());

        jPanel39.setBackground(new java.awt.Color(204, 204, 0));
        jPanel39.setPreferredSize(new java.awt.Dimension(128, 147));
        jPanel39.setLayout(new java.awt.BorderLayout());

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("Tên đơn vị tính:");
        jPanel39.add(jLabel17, java.awt.BorderLayout.CENTER);

        jPanel38.add(jPanel39, java.awt.BorderLayout.WEST);

        jPanel40.setBackground(new java.awt.Color(204, 204, 0));
        jPanel40.setLayout(new java.awt.BorderLayout());

        txtTenDVTMoi.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel40.add(txtTenDVTMoi, java.awt.BorderLayout.CENTER);

        jPanel38.add(jPanel40, java.awt.BorderLayout.CENTER);

        jPanel26.add(jPanel38, java.awt.BorderLayout.PAGE_START);

        jPanel41.setBackground(new java.awt.Color(255, 204, 153));
        jPanel41.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(255, 204, 153)));
        jPanel41.setLayout(new java.awt.GridLayout(1, 0, 30, 0));

        btnThemDVTMoi.setText("Thêm");
        btnThemDVTMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDVTMoiActionPerformed(evt);
            }
        });
        jPanel41.add(btnThemDVTMoi);

        btnHuyThemDVTMoi.setText("Hủy");
        btnHuyThemDVTMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyThemDVTMoiActionPerformed(evt);
            }
        });
        jPanel41.add(btnHuyThemDVTMoi);

        jPanel26.add(jPanel41, java.awt.BorderLayout.CENTER);

        themDVTJDialog.getContentPane().add(jPanel26, java.awt.BorderLayout.CENTER);

        themSizeJDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));
        jPanel42.setLayout(new java.awt.BorderLayout());

        jPanel43.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(204, 204, 0)));
        jPanel43.setPreferredSize(new java.awt.Dimension(428, 100));
        jPanel43.setLayout(new java.awt.BorderLayout());

        jPanel44.setBackground(new java.awt.Color(204, 204, 0));
        jPanel44.setPreferredSize(new java.awt.Dimension(128, 147));
        jPanel44.setLayout(new java.awt.GridLayout(2, 1));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setText("Size:");
        jPanel44.add(jLabel18);

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setText("Loại Size:");
        jPanel44.add(jLabel21);

        jPanel43.add(jPanel44, java.awt.BorderLayout.WEST);

        jPanel45.setBackground(new java.awt.Color(204, 204, 0));
        jPanel45.setLayout(new java.awt.GridLayout(2, 1, 5, 5));

        txtMaSize.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel45.add(txtMaSize);

        txtLoaiSize.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel45.add(txtLoaiSize);

        jPanel43.add(jPanel45, java.awt.BorderLayout.CENTER);

        jPanel42.add(jPanel43, java.awt.BorderLayout.PAGE_START);

        jPanel46.setBackground(new java.awt.Color(255, 204, 153));
        jPanel46.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(255, 204, 153)));
        jPanel46.setLayout(new java.awt.GridLayout(1, 0, 30, 0));

        btnThemSizeMoi.setText("Thêm");
        btnThemSizeMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSizeMoiActionPerformed(evt);
            }
        });
        jPanel46.add(btnThemSizeMoi);

        btnHuyThemSizeMoi.setText("Hủy");
        btnHuyThemSizeMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyThemSizeMoiActionPerformed(evt);
            }
        });
        jPanel46.add(btnHuyThemSizeMoi);

        jPanel42.add(jPanel46, java.awt.BorderLayout.CENTER);

        themSizeJDialog.getContentPane().add(jPanel42, java.awt.BorderLayout.CENTER);

        jPanel47.setBackground(new java.awt.Color(255, 255, 255));
        jPanel47.setLayout(new java.awt.BorderLayout());

        jPanel48.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(204, 204, 0)));
        jPanel48.setPreferredSize(new java.awt.Dimension(428, 70));
        jPanel48.setLayout(new java.awt.BorderLayout());

        jPanel49.setBackground(new java.awt.Color(204, 204, 0));
        jPanel49.setPreferredSize(new java.awt.Dimension(128, 147));
        jPanel49.setLayout(new java.awt.BorderLayout());

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setText("Tên màu:");
        jPanel49.add(jLabel19, java.awt.BorderLayout.CENTER);

        jPanel48.add(jPanel49, java.awt.BorderLayout.WEST);

        jPanel50.setBackground(new java.awt.Color(204, 204, 0));
        jPanel50.setLayout(new java.awt.BorderLayout());

        txtMauMoi.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel50.add(txtMauMoi, java.awt.BorderLayout.CENTER);

        jPanel48.add(jPanel50, java.awt.BorderLayout.CENTER);

        jPanel47.add(jPanel48, java.awt.BorderLayout.PAGE_START);

        jPanel51.setBackground(new java.awt.Color(255, 204, 153));
        jPanel51.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(255, 204, 153)));
        jPanel51.setLayout(new java.awt.GridLayout(1, 0, 30, 0));

        btnThemMauMoi.setText("Thêm");
        btnThemMauMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMauMoiActionPerformed(evt);
            }
        });
        jPanel51.add(btnThemMauMoi);

        btnHuyThemMau.setText("Hủy");
        btnHuyThemMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyThemMauActionPerformed(evt);
            }
        });
        jPanel51.add(btnHuyThemMau);

        jPanel47.add(jPanel51, java.awt.BorderLayout.CENTER);

        themMauSacJDialog.getContentPane().add(jPanel47, java.awt.BorderLayout.CENTER);

        jPanel52.setBackground(new java.awt.Color(255, 255, 255));
        jPanel52.setLayout(new java.awt.BorderLayout());

        jPanel53.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(204, 204, 0)));
        jPanel53.setPreferredSize(new java.awt.Dimension(428, 70));
        jPanel53.setLayout(new java.awt.BorderLayout());

        jPanel54.setBackground(new java.awt.Color(204, 204, 0));
        jPanel54.setPreferredSize(new java.awt.Dimension(128, 147));
        jPanel54.setLayout(new java.awt.BorderLayout());

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setText("Tên chất liệu:");
        jPanel54.add(jLabel20, java.awt.BorderLayout.CENTER);

        jPanel53.add(jPanel54, java.awt.BorderLayout.WEST);

        jPanel55.setBackground(new java.awt.Color(204, 204, 0));
        jPanel55.setLayout(new java.awt.BorderLayout());

        txtTenChatLieuMoi.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel55.add(txtTenChatLieuMoi, java.awt.BorderLayout.CENTER);

        jPanel53.add(jPanel55, java.awt.BorderLayout.CENTER);

        jPanel52.add(jPanel53, java.awt.BorderLayout.PAGE_START);

        jPanel56.setBackground(new java.awt.Color(255, 204, 153));
        jPanel56.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(255, 204, 153)));
        jPanel56.setLayout(new java.awt.GridLayout(1, 0, 30, 0));

        btnThemChatLieuMoi.setText("Thêm");
        btnThemChatLieuMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemChatLieuMoiActionPerformed(evt);
            }
        });
        jPanel56.add(btnThemChatLieuMoi);

        btnHuyThemChatLieuMoi.setText("Hủy");
        btnHuyThemChatLieuMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyThemChatLieuMoiActionPerformed(evt);
            }
        });
        jPanel56.add(btnHuyThemChatLieuMoi);

        jPanel52.add(jPanel56, java.awt.BorderLayout.CENTER);

        themChatLieuJDialog.getContentPane().add(jPanel52, java.awt.BorderLayout.CENTER);

        loadDataJDialog.setMinimumSize(new java.awt.Dimension(819, 140));
        loadDataJDialog.setPreferredSize(new java.awt.Dimension(819, 200));
        loadDataJDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                loadDataJDialogWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                loadDataJDialogWindowOpened(evt);
            }
        });

        jPanel57.setMinimumSize(new java.awt.Dimension(656, 200));
        jPanel57.setPreferredSize(new java.awt.Dimension(656, 200));
        jPanel57.setLayout(new java.awt.BorderLayout());

        pgbLoadingData.setPreferredSize(new java.awt.Dimension(146, 21));
        pgbLoadingData.setStringPainted(true);
        jPanel57.add(pgbLoadingData, java.awt.BorderLayout.PAGE_END);

        lblStatus.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setText("Đang tải dữ liệu ....");
        jPanel57.add(lblStatus, java.awt.BorderLayout.CENTER);

        loadDataJDialog.getContentPane().add(jPanel57, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 6, 6, 6, new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 102)));
        jPanel2.setPreferredSize(new java.awt.Dimension(978, 40));
        jPanel2.setLayout(new java.awt.BorderLayout());

        btnExit.setBackground(new java.awt.Color(0, 0, 0));
        btnExit.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 0, 0));
        btnExit.setText("X");
        btnExit.setBorder(null);
        btnExit.setPreferredSize(new java.awt.Dimension(40, 40));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel2.add(btnExit, java.awt.BorderLayout.LINE_END);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("QUẢN LÝ SẢN PHẨM");
        jPanel2.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setPreferredSize(new java.awt.Dimension(10, 40));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4, java.awt.BorderLayout.LINE_START);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setPreferredSize(new java.awt.Dimension(1011, 350));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel7.setPreferredSize(new java.awt.Dimension(300, 348));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));
        jPanel10.setPreferredSize(new java.awt.Dimension(300, 50));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel10, java.awt.BorderLayout.PAGE_END);

        jPanel11.setBackground(new java.awt.Color(0, 0, 0));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel12.setBackground(new java.awt.Color(0, 0, 0));
        jPanel12.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel12.setLayout(new java.awt.GridLayout(1, 0));

        btnChonAnh.setBackground(new java.awt.Color(0, 0, 0));
        btnChonAnh.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnChonAnh.setForeground(new java.awt.Color(255, 255, 255));
        btnChonAnh.setText("Chọn ảnh");
        btnChonAnh.setBorder(null);
        btnChonAnh.setOpaque(false);
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });
        jPanel12.add(btnChonAnh);

        btnXoaAnh.setBackground(new java.awt.Color(0, 0, 0));
        btnXoaAnh.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnXoaAnh.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaAnh.setText("Xóa ảnh");
        btnXoaAnh.setBorder(null);
        btnXoaAnh.setOpaque(false);
        jPanel12.add(btnXoaAnh);

        jPanel11.add(jPanel12, java.awt.BorderLayout.PAGE_END);

        lblAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/book.png"))); // NOI18N
        jPanel11.add(lblAnh, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel11, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel7, java.awt.BorderLayout.LINE_END);

        jPanel8.setPreferredSize(new java.awt.Dimension(1065, 60));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel22.setBackground(new java.awt.Color(0, 0, 0));
        jPanel22.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 1, 5, 10, new java.awt.Color(0, 0, 0)));
        jPanel22.setPreferredSize(new java.awt.Dimension(300, 0));
        jPanel22.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnNhapExcel.setText("Nhập từ EXCEL");
        btnNhapExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapExcelActionPerformed(evt);
            }
        });
        jPanel22.add(btnNhapExcel);

        btnXuatExcel.setText("Xuất ra EXCEL");
        btnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelActionPerformed(evt);
            }
        });
        jPanel22.add(btnXuatExcel);

        jPanel8.add(jPanel22, java.awt.BorderLayout.EAST);

        jPanel15.setBackground(new java.awt.Color(0, 0, 0));
        jPanel15.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 10, new java.awt.Color(0, 0, 0)));
        jPanel15.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

        btnLuu.setBackground(new java.awt.Color(102, 102, 255));
        btnLuu.setText("Lưu");
        btnLuu.setToolTipText("lưu vào cơ sở dữ liệu");
        btnLuu.setPreferredSize(new java.awt.Dimension(61, 21));
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel15.add(btnLuu);

        btnMoi.setBackground(new java.awt.Color(102, 102, 255));
        btnMoi.setText("Mới");
        btnMoi.setToolTipText("Tạo mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        jPanel15.add(btnMoi);

        btnThungRac.setBackground(new java.awt.Color(102, 102, 255));
        btnThungRac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/delete32.png"))); // NOI18N
        btnThungRac.setToolTipText("Sản phẩm đã xóa");
        btnThungRac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThungRacActionPerformed(evt);
            }
        });
        jPanel15.add(btnThungRac);

        jPanel8.add(jPanel15, java.awt.BorderLayout.WEST);

        jPanel16.setBackground(new java.awt.Color(0, 0, 0));
        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

        txtTimKiem.setPreferredSize(new java.awt.Dimension(218, 40));
        jPanel16.add(txtTimKiem);

        cbbTimDanhMuc.setToolTipText("Danh Mục");
        cbbTimDanhMuc.setPreferredSize(new java.awt.Dimension(154, 40));
        jPanel16.add(cbbTimDanhMuc);

        cbbTimMau.setToolTipText("Màu Sắc");
        cbbTimMau.setPreferredSize(new java.awt.Dimension(154, 40));
        jPanel16.add(cbbTimMau);

        cbbTimSize.setToolTipText("Size");
        cbbTimSize.setPreferredSize(new java.awt.Dimension(154, 40));
        jPanel16.add(cbbTimSize);

        cbbTimChatLieu.setToolTipText("Chất Liệu");
        cbbTimChatLieu.setPreferredSize(new java.awt.Dimension(154, 40));
        jPanel16.add(cbbTimChatLieu);

        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/poly/icons/search.jpg"))); // NOI18N
        btnTimKiem.setToolTipText("Tìm Kiếm");
        btnTimKiem.setPreferredSize(new java.awt.Dimension(73, 40));
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });
        jPanel16.add(btnTimKiem);

        jPanel8.add(jPanel16, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel8, java.awt.BorderLayout.PAGE_END);

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel14.setBackground(new java.awt.Color(0, 0, 0));
        jPanel14.setLayout(new java.awt.GridLayout(6, 2, 10, 10));

        jPanel36.setBackground(new java.awt.Color(0, 0, 0));
        jPanel36.setLayout(new java.awt.BorderLayout());

        jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Mã sản phẩm");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel13.setPreferredSize(new java.awt.Dimension(150, 12));
        jPanel36.add(jLabel13, java.awt.BorderLayout.LINE_START);

        txtMaSanPham.setEditable(false);
        txtMaSanPham.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jPanel36.add(txtMaSanPham, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanel36);

        jPanel35.setBackground(new java.awt.Color(0, 0, 0));
        jPanel35.setLayout(new java.awt.BorderLayout());

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Danh mục");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel12.setPreferredSize(new java.awt.Dimension(150, 12));
        jPanel35.add(jLabel12, java.awt.BorderLayout.LINE_START);

        jPanel35.add(cbbDanhMuc, java.awt.BorderLayout.CENTER);

        btnThemDanhMuc.setText("Thêm Danh mục mới");
        btnThemDanhMuc.setPreferredSize(new java.awt.Dimension(143, 21));
        btnThemDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDanhMucActionPerformed(evt);
            }
        });
        jPanel35.add(btnThemDanhMuc, java.awt.BorderLayout.LINE_END);

        jPanel14.add(jPanel35);

        jPanel37.setBackground(new java.awt.Color(0, 0, 0));
        jPanel37.setLayout(new java.awt.BorderLayout());

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Mã vạch");
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel14.setPreferredSize(new java.awt.Dimension(150, 12));
        jPanel37.add(jLabel14, java.awt.BorderLayout.LINE_START);

        txtMaVach.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jPanel37.add(txtMaVach, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanel37);

        jPanel33.setBackground(new java.awt.Color(0, 0, 0));
        jPanel33.setLayout(new java.awt.BorderLayout());

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Đơn vị tính");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel10.setPreferredSize(new java.awt.Dimension(150, 12));
        jPanel33.add(jLabel10, java.awt.BorderLayout.LINE_START);

        jPanel33.add(cbbDonViTinh, java.awt.BorderLayout.CENTER);

        btnThemDVT.setText("Thêm ĐVT mới");
        btnThemDVT.setPreferredSize(new java.awt.Dimension(143, 21));
        btnThemDVT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDVTActionPerformed(evt);
            }
        });
        jPanel33.add(btnThemDVT, java.awt.BorderLayout.LINE_END);

        jPanel14.add(jPanel33);

        jPanel34.setBackground(new java.awt.Color(0, 0, 0));
        jPanel34.setLayout(new java.awt.BorderLayout());

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tên sản phẩm");
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel11.setPreferredSize(new java.awt.Dimension(150, 12));
        jPanel34.add(jLabel11, java.awt.BorderLayout.LINE_START);

        txtTenSP.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jPanel34.add(txtTenSP, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanel34);

        jPanel31.setBackground(new java.awt.Color(0, 0, 0));
        jPanel31.setLayout(new java.awt.BorderLayout());

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Size");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel8.setPreferredSize(new java.awt.Dimension(150, 12));
        jPanel31.add(jLabel8, java.awt.BorderLayout.LINE_START);

        jPanel31.add(cbbSize, java.awt.BorderLayout.CENTER);

        btnThemSize.setText("Thêm size mới");
        btnThemSize.setPreferredSize(new java.awt.Dimension(143, 21));
        btnThemSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSizeActionPerformed(evt);
            }
        });
        jPanel31.add(btnThemSize, java.awt.BorderLayout.LINE_END);

        jPanel14.add(jPanel31);

        jPanel32.setBackground(new java.awt.Color(0, 0, 0));
        jPanel32.setLayout(new java.awt.BorderLayout());

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Giá bán");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel9.setPreferredSize(new java.awt.Dimension(150, 12));
        jPanel32.add(jLabel9, java.awt.BorderLayout.LINE_START);

        txtGiaBan.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtGiaBan.setModel(new javax.swing.SpinnerNumberModel(1000.0d, 0.0d, null, 10000.0d));
        jPanel32.add(txtGiaBan, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanel32);

        jPanel27.setBackground(new java.awt.Color(0, 0, 0));
        jPanel27.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Màu");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel4.setPreferredSize(new java.awt.Dimension(150, 12));
        jPanel27.add(jLabel4, java.awt.BorderLayout.LINE_START);

        jPanel27.add(cbbMau, java.awt.BorderLayout.CENTER);

        btnThemMau.setText("Thêm màu mới");
        btnThemMau.setPreferredSize(new java.awt.Dimension(143, 21));
        btnThemMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMauActionPerformed(evt);
            }
        });
        jPanel27.add(btnThemMau, java.awt.BorderLayout.LINE_END);

        jPanel14.add(jPanel27);

        jPanel30.setBackground(new java.awt.Color(0, 0, 0));
        jPanel30.setLayout(new java.awt.BorderLayout());

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Giá nhập");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel7.setPreferredSize(new java.awt.Dimension(150, 12));
        jPanel30.add(jLabel7, java.awt.BorderLayout.LINE_START);

        txtGiaNhap.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtGiaNhap.setModel(new javax.swing.SpinnerNumberModel(1000.0d, 0.0d, null, 10000.0d));
        jPanel30.add(txtGiaNhap, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanel30);

        jPanel28.setBackground(new java.awt.Color(0, 0, 0));
        jPanel28.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Chất liệu");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setPreferredSize(new java.awt.Dimension(150, 12));
        jPanel28.add(jLabel5, java.awt.BorderLayout.LINE_START);

        jPanel28.add(cbbChatLieu, java.awt.BorderLayout.CENTER);

        btnThemChatLieu.setText("Thêm chất liệu mới");
        btnThemChatLieu.setPreferredSize(new java.awt.Dimension(143, 21));
        btnThemChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemChatLieuActionPerformed(evt);
            }
        });
        jPanel28.add(btnThemChatLieu, java.awt.BorderLayout.LINE_END);

        jPanel14.add(jPanel28);

        jPanel21.setBackground(new java.awt.Color(0, 0, 0));
        jPanel21.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Số lượng");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 12));
        jPanel21.add(jLabel3, java.awt.BorderLayout.LINE_START);

        txtSoLuong.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtSoLuong.setModel(new javax.swing.SpinnerNumberModel(1000.0d, 0.0d, null, 10000.0d));
        jPanel21.add(txtSoLuong, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanel21);

        jPanel29.setBackground(new java.awt.Color(0, 0, 0));
        jPanel29.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Ngày nhập");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel6.setPreferredSize(new java.awt.Dimension(150, 12));
        jPanel29.add(jLabel6, java.awt.BorderLayout.LINE_START);

        txtNgayNhap.setEditable(false);
        txtNgayNhap.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jPanel29.add(txtNgayNhap, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanel29);

        jPanel9.add(jPanel14, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        tblSanPham.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ma SP", "Ma Vach", "Ten SP", "Gia Ban", "Gia Nhap", "So Luong", "Danh Muc", "DVT", "Size", "Mau", "Chat Lieu", "Ngay Nhap", "AnhSP", "tool"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setGridColor(new java.awt.Color(0, 0, 0));
        tblSanPham.setRowHeight(104);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(12).setMinWidth(84);
            tblSanPham.getColumnModel().getColumn(12).setPreferredWidth(84);
            tblSanPham.getColumnModel().getColumn(12).setMaxWidth(84);
            tblSanPham.getColumnModel().getColumn(13).setMinWidth(60);
            tblSanPham.getColumnModel().getColumn(13).setPreferredWidth(60);
            tblSanPham.getColumnModel().getColumn(13).setMaxWidth(60);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1492, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        StringBuilder sp = new StringBuilder();
        if (XValidate.isEmpty(txtTenSP)) {
            sp.append("Không để trống tên sản phẩm!\n");
            return;
        }
        saveSP();
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnThemDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDanhMucActionPerformed
        this.themDMJDialog.setSize(614, 200);
        this.themDMJDialog.setLocationRelativeTo(this);
        this.themDMJDialog.setVisible(true);
    }//GEN-LAST:event_btnThemDanhMucActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        clearForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        if (mouseClicked())
            return;
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnThungRacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThungRacActionPerformed
        loadDataToTableDeleted();
        if (this.tblDaXoa.getRowCount() == 0) {
            btnKhoiPhuc.setEnabled(false);
            btnKhoiPhucTatCa.setEnabled(false);
        } else {
            btnKhoiPhuc.setEnabled(true);
            btnKhoiPhucTatCa.setEnabled(true);
        }
        this.DaXoaJDialog.setSize(this.getWidth(), this.getHeight());
        this.DaXoaJDialog.setLocationRelativeTo(this);
        this.DaXoaJDialog.setVisible(true);
    }//GEN-LAST:event_btnThungRacActionPerformed

    private void tblDaXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDaXoaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDaXoaMouseClicked

    private void btnThemDVTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDVTActionPerformed
        this.themDVTJDialog.setSize(614, 200);
        this.themDVTJDialog.setLocationRelativeTo(this);
        this.themDVTJDialog.setVisible(true);
    }//GEN-LAST:event_btnThemDVTActionPerformed

    private void btnThemSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSizeActionPerformed
        this.themSizeJDialog.setSize(614, 200);
        this.themSizeJDialog.setLocationRelativeTo(this);
        this.themSizeJDialog.setVisible(true);
    }//GEN-LAST:event_btnThemSizeActionPerformed

    private void btnThemMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMauActionPerformed
        this.themMauSacJDialog.setSize(614, 200);
        this.themMauSacJDialog.setLocationRelativeTo(this);
        this.themMauSacJDialog.setVisible(true);
    }//GEN-LAST:event_btnThemMauActionPerformed

    private void btnThemChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemChatLieuActionPerformed
        this.themChatLieuJDialog.setSize(614, 200);
        this.themChatLieuJDialog.setLocationRelativeTo(this);
        this.themChatLieuJDialog.setVisible(true);
    }//GEN-LAST:event_btnThemChatLieuActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        String danhMuc, mau, Size, ChatLieu, key;
        danhMuc = String.valueOf(cbbTimDanhMuc.getSelectedItem()).equalsIgnoreCase("ALL") ? "" : String.valueOf(cbbTimDanhMuc.getSelectedItem());
        mau = String.valueOf(cbbTimMau.getSelectedItem()).equalsIgnoreCase("ALL") ? "" : String.valueOf(cbbTimMau.getSelectedItem());
        Size = String.valueOf(cbbTimSize.getSelectedItem()).equalsIgnoreCase("ALL") ? "" : String.valueOf(cbbTimSize.getSelectedItem());
        ChatLieu = String.valueOf(cbbTimChatLieu.getSelectedItem()).equalsIgnoreCase("ALL") ? "" : String.valueOf(cbbTimChatLieu.getSelectedItem());
        key = this.txtTimKiem.getText();

        Object[] objKey = {key, "%" + key + "%", "%" + key + "%", "%" + danhMuc + "%", "%" + mau + "%", "%" + Size + "%", "%" + ChatLieu + "%"};
        loadDataToTable(objKey);
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelActionPerformed
        try {
            File file = XExcel.xuatExcel(tblSanPham, "San_Pham");
            if (file != null) {
                Messeger.alert(this, "Đã xong: " + file.getAbsolutePath());
            } else {
                return;
            }
        } catch (Exception ex) {
            Logger.getLogger(ThongKeJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnXuatExcelActionPerformed

    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhActionPerformed
        chonAnh();
    }//GEN-LAST:event_btnChonAnhActionPerformed

    private void btnKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucActionPerformed

        if (this.tblDaXoa.getSelectedRow() == -1) {
            Messeger.alert(DaXoaJDialog, "Không có sản phẩm nào được chọn!");
            return;
        }

        int[] rows = tblDaXoa.getSelectedRows();
        if (rows.length > 0 && Messeger.confirm(DaXoaJDialog, "Bạn có muốn khôi phục lại các sản phẩm đã chọn không?")) {
            for (int row : rows) {
                int masp = (Integer) tblDaXoa.getValueAt(row, 0);
                try {
                    daoSP.restore(masp);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Messeger.showErrorDialog(DaXoaJDialog, "Lỗi truy vấn!", "Lỗi");
                    return;
                }
            }
            Messeger.alert(DaXoaJDialog, "khôi phục thành công!");
            Object[] objKey = {"%%", "%%", "%%", "%%", "%%", "%%", "%%"};
            loadDataToTable(objKey);
            loadDataToTableDeleted();
        }
    }//GEN-LAST:event_btnKhoiPhucActionPerformed

    private void btnKhoiPhucTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucTatCaActionPerformed
        if (this.tblDaXoa.getSelectedRow() == -1) {
            Messeger.alert(DaXoaJDialog, "Không có sản phẩm nào được chọn!");
            return;
        }
        if (Messeger.confirm(DaXoaJDialog, "Bạn có muốn khôi phục lại các sản phẩm đã xóa không?")) {
            for (int i = 0; i < tblDaXoa.getRowCount(); i++) {

                int masp = (Integer) tblDaXoa.getValueAt(i, 0);
                try {
                    daoSP.restore(masp);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Messeger.showErrorDialog(DaXoaJDialog, "Lỗi truy vấn!", "Lỗi");
                    return;
                }
            }
            Messeger.alert(DaXoaJDialog, "khôi phục thành công!");
            Object[] objKey = {"%%", "%%", "%%", "%%", "%%", "%%", "%%"};
            loadDataToTable(objKey);
            loadDataToTableDeleted();
        }
    }//GEN-LAST:event_btnKhoiPhucTatCaActionPerformed

    private void btnThemDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDMActionPerformed
        if (XValidate.isEmpty(txtTenDanhMucMoi)) {
            Messeger.showErrorDialog(themDMJDialog, "Không để trống tên danh mục!", "lỗi");
            return;
        }
        DanhMuc dm = new DanhMuc();
        dm.setTenDanhMuc(txtTenDanhMucMoi.getText());
        dm.setNgayThem(XDate.toString(new Date(), "MM/dd/yyyy"));
        try {
            this.daoDM.insert(dm);
            Messeger.alert(themDMJDialog, "thêm thành công thành công!");
            loadDataToCBBDM();
            this.themDMJDialog.dispose();
        } catch (Exception ex) {
            Logger.getLogger(QLSanPhamJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnThemDMActionPerformed

    private void btnHuyThemDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyThemDMActionPerformed
        this.themDMJDialog.dispose();
    }//GEN-LAST:event_btnHuyThemDMActionPerformed

    private void btnThemDVTMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDVTMoiActionPerformed
        if (XValidate.isEmpty(txtTenDVTMoi)) {
            Messeger.showErrorDialog(themDVTJDialog, "Không để trống tên đơn vị tính!", "lỗi");
            return;
        }
        DonViTinh dvt = new DonViTinh();
        dvt.setTenDVT(txtTenDVTMoi.getText());
        try {
            this.daoDVT.insert(dvt);
            Messeger.alert(themDVTJDialog, "thêm thành công thành công!");
            loadDataToCBBDVT();
            this.themDVTJDialog.dispose();
        } catch (Exception ex) {
            Logger.getLogger(QLSanPhamJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThemDVTMoiActionPerformed

    private void btnHuyThemDVTMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyThemDVTMoiActionPerformed
        this.themDVTJDialog.dispose();
    }//GEN-LAST:event_btnHuyThemDVTMoiActionPerformed

    private void btnThemSizeMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSizeMoiActionPerformed
        if (XValidate.isEmpty(txtMaSize)) {
            Messeger.showErrorDialog(themSizeJDialog, "Không để trống Size!", "lỗi");
            return;
        }
        if (XValidate.isEmpty(txtLoaiSize)) {
            Messeger.showErrorDialog(themSizeJDialog, "Không để trống loại Size!", "lỗi");
            return;
        }
        Size size = new Size();
        size.setMaSize(txtMaSize.getText());
        size.setTenSize(txtLoaiSize.getText());
        try {
            this.daoSize.insert(size);
            Messeger.alert(themSizeJDialog, "thêm thành công thành công!");
            loadDataToCBBSize();
            this.themSizeJDialog.dispose();
        } catch (Exception ex) {
            Logger.getLogger(QLSanPhamJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThemSizeMoiActionPerformed

    private void btnHuyThemSizeMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyThemSizeMoiActionPerformed
        this.themSizeJDialog.dispose();
    }//GEN-LAST:event_btnHuyThemSizeMoiActionPerformed

    private void btnThemMauMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMauMoiActionPerformed
        if (XValidate.isEmpty(txtMauMoi)) {
            Messeger.showErrorDialog(themMauSacJDialog, "Không để trống tên màu!", "lỗi");
            return;
        }
        MauSac ms = new MauSac();
        ms.setTenMau(txtMauMoi.getText());
        try {
            this.daoMauSac.insert(ms);
            Messeger.alert(themMauSacJDialog, "thêm thành công thành công!");
            loadDataToCBBMau();
            this.themMauSacJDialog.dispose();
        } catch (Exception ex) {
            Logger.getLogger(QLSanPhamJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThemMauMoiActionPerformed

    private void btnHuyThemMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyThemMauActionPerformed
        this.themMauSacJDialog.dispose();
    }//GEN-LAST:event_btnHuyThemMauActionPerformed

    private void btnThemChatLieuMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemChatLieuMoiActionPerformed
        if (XValidate.isEmpty(txtTenChatLieuMoi)) {
            Messeger.showErrorDialog(themChatLieuJDialog, "Không để trống tên chất liệu!", "lỗi");
            return;
        }
        ChatLieu cl = new ChatLieu();
        cl.setTenChatLieu(txtTenChatLieuMoi.getText());
        try {
            this.daoCL.insert(cl);
            Messeger.alert(themChatLieuJDialog, "thêm thành công thành công!");
            loadDataToCBBCL();
            this.themChatLieuJDialog.dispose();
        } catch (Exception ex) {
            Logger.getLogger(QLSanPhamJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThemChatLieuMoiActionPerformed

    private void btnHuyThemChatLieuMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyThemChatLieuMoiActionPerformed
        this.themChatLieuJDialog.dispose();
    }//GEN-LAST:event_btnHuyThemChatLieuMoiActionPerformed

    private void btnNhapExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapExcelActionPerformed
        try {
            tableModel.setRowCount(0);
            XExcel.readExcel(tableModel);
            loadDataJDialog.setSize(this.getWidth(), this.getHeight());
            loadDataJDialog.setLocationRelativeTo(this);
            loadDataJDialog.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(QLSanPhamJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNhapExcelActionPerformed

    private void loadDataJDialogWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_loadDataJDialogWindowOpened
        this.pgbLoadingData.setMaximum(tableModel.getRowCount() - 1);

        loadData = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    if (i == pgbLoadingData.getMaximum()) {
                        loadDataJDialog.dispose();
                    }
                    tblSanPham.setRowSelectionInterval(i, i);
                    mouseClicked();
                    clearImage();
                    insertSP();
                    pgbLoadingData.setValue(i);
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(QLSanPhamJDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        loadData.start();
    }//GEN-LAST:event_loadDataJDialogWindowOpened

    private void loadDataJDialogWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_loadDataJDialogWindowClosed
        this.loadData.stop();
        Messeger.alert(null, "Thêm dữ liệu mới thành công");
        loadDataToTable(new Object[]{"%%", "%%", "%%", "%%", "%%", "%%", "%%"});
    }//GEN-LAST:event_loadDataJDialogWindowClosed

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
            java.util.logging.Logger.getLogger(QLSanPhamJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLSanPhamJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLSanPhamJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLSanPhamJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QLSanPhamJDialog dialog = new QLSanPhamJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DaXoaJDialog;
    private javax.swing.JButton btnChonAnh;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnHuyThemChatLieuMoi;
    private javax.swing.JButton btnHuyThemDM;
    private javax.swing.JButton btnHuyThemDVTMoi;
    private javax.swing.JButton btnHuyThemMau;
    private javax.swing.JButton btnHuyThemSizeMoi;
    private javax.swing.JButton btnKhoiPhuc;
    private javax.swing.JButton btnKhoiPhucTatCa;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNhapExcel;
    private javax.swing.JButton btnThemChatLieu;
    private javax.swing.JButton btnThemChatLieuMoi;
    private javax.swing.JButton btnThemDM;
    private javax.swing.JButton btnThemDVT;
    private javax.swing.JButton btnThemDVTMoi;
    private javax.swing.JButton btnThemDanhMuc;
    private javax.swing.JButton btnThemMau;
    private javax.swing.JButton btnThemMauMoi;
    private javax.swing.JButton btnThemSize;
    private javax.swing.JButton btnThemSizeMoi;
    private javax.swing.JButton btnThungRac;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoaAnh;
    private javax.swing.JButton btnXuatExcel;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbDanhMuc;
    private javax.swing.JComboBox<String> cbbDonViTinh;
    private javax.swing.JComboBox<String> cbbMau;
    private javax.swing.JComboBox<String> cbbSize;
    private javax.swing.JComboBox<String> cbbTimChatLieu;
    private javax.swing.JComboBox<String> cbbTimDanhMuc;
    private javax.swing.JComboBox<String> cbbTimMau;
    private javax.swing.JComboBox<String> cbbTimSize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JDialog loadDataJDialog;
    private javax.swing.JProgressBar pgbLoadingData;
    private javax.swing.JTable tblDaXoa;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JDialog themChatLieuJDialog;
    private javax.swing.JDialog themDMJDialog;
    private javax.swing.JDialog themDVTJDialog;
    private javax.swing.JDialog themMauSacJDialog;
    private javax.swing.JDialog themSizeJDialog;
    private javax.swing.JSpinner txtGiaBan;
    private javax.swing.JSpinner txtGiaNhap;
    private javax.swing.JTextField txtLoaiSize;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtMaSize;
    private javax.swing.JTextField txtMaVach;
    private javax.swing.JTextField txtMauMoi;
    private javax.swing.JTextField txtNgayNhap;
    private javax.swing.JSpinner txtSoLuong;
    private javax.swing.JTextField txtTenChatLieuMoi;
    private javax.swing.JTextField txtTenDVTMoi;
    private javax.swing.JTextField txtTenDanhMucMoi;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    private void loadDataToCBBCL() {
        this.dCBCL.removeAllElements();
        this.dCBTimCL.removeAllElements();
        List<ChatLieu> list = new ArrayList<>();
        try {
            list = this.daoCL.selectAll();
            this.cbbTimChatLieu.addItem("ALL");
            for (ChatLieu cl : list) {
                this.dCBCL.addElement(cl);
                this.dCBTimCL.addElement(cl);
            }
        } catch (Exception ex) {
            Logger.getLogger(QLSanPhamJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDataToCBBDM() {
        this.dCBDM.removeAllElements();
        this.dCBTimDM.removeAllElements();
        List<DanhMuc> list = new ArrayList<>();
        try {
            list = this.daoDM.selectAll();
            this.cbbTimDanhMuc.addItem("All");
            for (DanhMuc dm : list) {
                this.dCBDM.addElement(dm);
                this.dCBTimDM.addElement(dm);
            }
        } catch (Exception ex) {
            Logger.getLogger(QLSanPhamJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadDataToCBBMau() {
        this.dCBMS.removeAllElements();
        this.dCBTimMS.removeAllElements();
        List<MauSac> list = new ArrayList<>();
        try {
            list = this.daoMauSac.selectAll();
            this.cbbTimMau.addItem("ALL");
            for (MauSac mauSac : list) {
                this.dCBMS.addElement(mauSac);
                this.dCBTimMS.addElement(mauSac);
            }
        } catch (Exception ex) {
            Logger.getLogger(QLSanPhamJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadDataToCBBSize() {
        this.dCBSize.removeAllElements();
        this.dCBTimSize.removeAllElements();
        List<Size> list = new ArrayList<>();
        try {
            list = this.daoSize.selectAll();
            this.cbbTimSize.addItem("ALL");
            for (Size s : list) {
                this.dCBSize.addElement(s);
                this.dCBTimSize.addElement(s);
            }
            this.dCBSize.setSelectedItem("XXL");
        } catch (Exception ex) {
            Logger.getLogger(QLSanPhamJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadDataToCBBDVT() {
        this.dCBDVT.removeAllElements();
        List<DonViTinh> list = new ArrayList<>();
        try {
            list = this.daoDVT.selectAll();
            for (DonViTinh donViTinh : list) {
                this.dCBDVT.addElement(donViTinh);
            }
        } catch (Exception ex) {
            Logger.getLogger(QLSanPhamJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDataToTable(Object[] objKey) {
        this.tableModel.setRowCount(0);
        List<Object[]> listSP = new ArrayList<>();
        listSP = this.daoSP.getListSanPhamByListKey(objKey);
        for (Object[] o : listSP) {
            this.tableModel.addRow(o);
        }
    }

    public SanPham getForm() {
        SanPham sp = new SanPham();
        sp.setAnhSanPham(lblAnh.getToolTipText());
        sp.setGiaBan(Double.parseDouble(txtGiaBan.getValue() + ""));
        sp.setGiaNhap(Double.parseDouble(txtGiaNhap.getValue() + ""));
        ChatLieu c = (ChatLieu) dCBCL.getSelectedItem();
        sp.setMaChatLieu(c.getMaChatLieu());
        DonViTinh dvt = (DonViTinh) dCBDVT.getSelectedItem();
        sp.setMaDVT(dvt.getMaDVT());
        DanhMuc dm = (DanhMuc) dCBDM.getSelectedItem();
        sp.setMaDanhMuc(dm.getMaDM());
        MauSac mau = (MauSac) dCBMS.getSelectedItem();
        sp.setMaMau(mau.getMaMau());
        if (!txtMaSanPham.getText().isEmpty()) {
            sp.setMaSP(Integer.parseInt(txtMaSanPham.getText()));
        }
        Size s = (Size) dCBSize.getSelectedItem();
        sp.setMaSize(s.getMaSize());
        sp.setMaVach(txtMaVach.getText());
        sp.setNgayNhap(XDate.toDate(XDate.toString(new Date(), "MM/dd/yyyy"), "MM/dd/yyyy"));
        sp.setSoLuong((int) Double.parseDouble(txtSoLuong.getValue() + ""));
        sp.setTenSanPham(txtTenSP.getText());
        return sp;
    }

    private void clearForm() {
        this.txtMaSanPham.setText("");
        this.txtGiaBan.setValue(0);
        this.txtGiaNhap.setValue(0);
        this.txtMaVach.setText("");
        this.txtNgayNhap.setText("");
        this.txtSoLuong.setValue(0);
        this.txtTenSP.setText("");
        clearImage();
    }

    private boolean mouseClicked() throws NumberFormatException {
        int index = this.tblSanPham.getSelectedRow();
        if (index == -1) {
            return true;
        }
        int masp = Integer.parseInt(tblSanPham.getValueAt(index, 0) + "");
        try {
            SanPham sp = this.daoSP.selectById(masp);
            this.txtMaSanPham.setText(tblSanPham.getValueAt(index, 0) + "");
            this.txtMaVach.setText(tblSanPham.getValueAt(index, 1) + "");
            this.txtTenSP.setText(tblSanPham.getValueAt(index, 2) + "");
            this.txtGiaBan.setValue(Double.parseDouble(tblSanPham.getValueAt(index, 3) + ""));
            this.txtGiaNhap.setValue(Double.parseDouble(tblSanPham.getValueAt(index, 4) + ""));
            this.txtSoLuong.setValue(Integer.parseInt(tblSanPham.getValueAt(index, 5) + ""));
            this.txtNgayNhap.setText(tblSanPham.getValueAt(index, 11) + "");
            for (int i = 0; i < dCBDM.getSize(); i++) {
                DanhMuc d = (DanhMuc) dCBDM.getElementAt(i);
                if (d.getTenDanhMuc().equals(tblSanPham.getValueAt(index, 6) + "")) {
                    cbbDanhMuc.setSelectedIndex(i);
                }
            }
            for (int i = 0; i < dCBDVT.getSize(); i++) {
                DonViTinh d = (DonViTinh) dCBDVT.getElementAt(i);
                if (d.getTenDVT().equals(tblSanPham.getValueAt(index, 7) + "")) {
                    cbbDonViTinh.setSelectedIndex(i);
                }
            }
            for (int i = 0; i < dCBSize.getSize(); i++) {
                Size d = (Size) dCBSize.getElementAt(i);
                if (d.getMaSize().equals(tblSanPham.getValueAt(index, 8) + "")) {
                    cbbSize.setSelectedIndex(i);
                }
            }
            for (int i = 0; i < dCBMS.getSize(); i++) {
                MauSac d = (MauSac) dCBMS.getElementAt(i);
                if (d.getTenMau().equals(tblSanPham.getValueAt(index, 9) + "")) {
                    cbbMau.setSelectedIndex(i);
                }
            }
            for (int i = 0; i < dCBCL.getSize(); i++) {
                ChatLieu d = (ChatLieu) dCBCL.getElementAt(i);
                if (d.getTenChatLieu().equals(tblSanPham.getValueAt(index, 10) + "")) {
                    cbbChatLieu.setSelectedIndex(i);
                }
            }
//            this.dCBDM.setSelectedItem(tblSanPham.getValueAt(index, 6) + "");
//            this.dCBDVT.setSelectedItem(tblSanPham.getValueAt(index, 7) + "");
//            this.dCBMS.setSelectedItem(tblSanPham.getValueAt(index, 9) + "");
//            this.dCBSize.setSelectedItem(tblSanPham.getValueAt(index, 8) + "");
//            this.dCBCL.setSelectedItem(tblSanPham.getValueAt(index, 10) + "");
            String tooltip = sp.getAnhSanPham();
            if (tooltip != null) {
                this.lblAnh.setToolTipText(tooltip);
                this.lblAnh.setIcon(ImageHelper.read(tooltip));
            } else {
                clearImage();
            }

        } catch (Exception ex) {
            Logger.getLogger(QLSanPhamJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void clearImage() {
        ImageIcon icon = new ImageIcon(".\\AnhSP\\noImage.jpg");
        Image img = icon.getImage().getScaledInstance(84, 104, Image.SCALE_SMOOTH);
        this.lblAnh.setIcon(new ImageIcon(img));
        this.lblAnh.setToolTipText("noImage.jpg");
    }

    private void saveSP() {
        if (!txtMaSanPham.getText().isEmpty()) {
            if (Messeger.confirm(this, "Mã sản phẩm đã tồn tại!, Bạn có muốn cập nhập với mã này không?"
                    + "\n nếu muốn tạo mới Hãy ấn nút 'Mói'")) {
                updateSP();
                Messeger.alert(this, "Cập nhật thành công");

                this.loadDataToTable(new Object[]{
                    "%" + getForm().getMaSP() + "%",
                    "%" + getForm().getMaVach() + "%",
                    "%" + getForm().getTenSanPham() + "%",
                    "%" + getForm().getMaVach() + "%",
                    "%" + getForm().getMaVach() + "%",
                    "%" + getForm().getMaVach() + "%",
                    "%" + getForm().getMaVach() + "%"
                });
            }
        } else {
            if (Messeger.confirm(this, "Xác nhận thêm sản phẩm này?")) {
                insertSP();
                Messeger.alert(this, "Thêm thành công");
                this.loadDataToTable(new Object[]{
                    "%" + getForm().getMaVach() + "%",
                    "%" + getForm().getMaVach() + "%",
                    "%" + getForm().getTenSanPham() + "%",
                    "%" + getForm().getMaVach() + "%",
                    "%" + getForm().getMaVach() + "%",
                    "%" + getForm().getMaVach() + "%",
                    "%" + getForm().getMaVach() + "%"
                });
            }
        }
    }

    private void insertSP() {
        try {
            this.daoSP.insert(getForm());

        } catch (Exception ex) {
            Logger.getLogger(QLSanPhamJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateSP() {
        try {
            this.daoSP.update(getForm());

        } catch (Exception ex) {
            Logger.getLogger(QLSanPhamJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDataToTableDeleted() {
        this.tableDeletedModel.setRowCount(0);
        List<Object[]> listSPDeleted = new ArrayList<>();
        listSPDeleted = this.daoSP.getListSanPhamDeleted();
        for (Object[] o : listSPDeleted) {
            this.tableDeletedModel.addRow(o);
        }
    }

    private void chonAnh() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ImageHelper.save(file); // lưu hình ảnh vào thư mục logos
            ImageIcon icon = ImageHelper.read(file.getName());//đọc hình từ thư mục logos
            lblAnh.setIcon(icon);
            lblAnh.setToolTipText(file.getName());//giữ tên hình trong tooltop
        }
    }
}
