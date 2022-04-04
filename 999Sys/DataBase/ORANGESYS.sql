﻿CREATE DATABASE ORANGESYS
GO
USE ORANGESYS
GO
CREATE TABLE VAITRO
(
	MAVAITRO INT IDENTITY(101,1) PRIMARY KEY NOT NULL,
	TENVAITRO NVARCHAR(20) NOT NULL,
	TRANGTHAI BIT DEFAULT 1
)
GO
CREATE TABLE TAIKHOAN
(	
	MANV VARCHAR(20) PRIMARY KEY NOT NULL,
	MAVAITRO INT FOREIGN  KEY REFERENCES VAITRO(MAVAITRO) ON DELETE NO ACTION ON UPDATE CASCADE,
	HOTEN NVARCHAR(50) NOT NULL,
	MATKHAU VARCHAR(20) NOT NULL,
	NGAYSINH DATE NOT NULL,
	GIOITINH BIT NOT NULL,
	EMAIL VARCHAR(100) NOT NULL,
	SDT VARCHAR(12) NOT NULL,
	DIACHI NVARCHAR(MAX) NOT NULL,
	ANHDAIDIEN NVARCHAR(MAX) NOT NULL,
	NGAYTAO DATE NOT NULL,
	TRANGTHAI BIT DEFAULT 1
)
GO

CREATE TABLE TTGIAOCA
(
	MATT INT PRIMARY KEY NOT NULL,
	TENTT NVARCHAR(20) NOT NULL
)

GO
CREATE TABLE GIAOCA
(
	MAGIAOCA INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	MANVGIAOCA VARCHAR(20) NOT NULL FOREIGN KEY REFERENCES TAIKHOAN(MANV),
	MANVNHANCA VARCHAR(20) NOT NULL FOREIGN KEY REFERENCES TAIKHOAN(MANV),
	GIONHANCA DATETIME DEFAULT GETDATE(),
	GIOGIAOCA DATETIME DEFAULT GETDATE(),
	TIENCOSO MONEY DEFAULT 1000000,
	TIENPHATSINH MONEY,
	DOANHTHUCA MONEY,
	TIENDATHUHOI MONEY,
	GHICHU NVARCHAR(MAX),
	MATT INT FOREIGN KEY REFERENCES TTGIAOCA(MATT) DEFAULT 2
)

GO
CREATE TABLE LOAIKHACHHANG
(
	MALOAIKH INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TENLOAIKH NVARCHAR(20) NOT NULL,
	TRANGTHAI BIT DEFAULT 1
)
GO
CREATE TABLE KHACHHANG
(	
	MAKH VARCHAR(20) PRIMARY KEY NOT NULL,
	MALOAIKH INT FOREIGN  KEY REFERENCES LOAIKHACHHANG(MALOAIKH) ON DELETE NO ACTION ON UPDATE CASCADE,
	HOTEN NVARCHAR(50) NOT NULL,
	TICHDIEM INT DEFAULT 1000,
	NGAYSINH DATE NOT NULL,
	GIOITINH BIT NOT NULL,
	EMAIL VARCHAR(100) NOT NULL,
	SDT VARCHAR(12) NOT NULL,
	DIACHI NVARCHAR(MAX) NOT NULL,
	NGAYTAO DATE NOT NULL,
	TRANGTHAI BIT DEFAULT 1
)

GO
CREATE TABLE DONVITINH
(
	MADVT INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TENDVT NVARCHAR(20) NOT NULL,
	TRANGTHAI BIT DEFAULT 1
)
GO
CREATE TABLE MAUSAC
(
	MAMAU INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TENMAU NVARCHAR(20) NOT NULL,
	TRANGTHAI BIT DEFAULT 1
)
GO
CREATE TABLE DANHMUC
(
	MADM INT IDENTITY(100,1) PRIMARY KEY NOT NULL,
	TENDANHMUC NVARCHAR(50) NOT NULL,
	NGAYTHEM DATE NOT NULL,
	TRANGTHAI BIT DEFAULT 1
)
GO
CREATE TABLE SIZE
(
	MASIZE VARCHAR(10) PRIMARY KEY NOT NULL,
	LOAISIZE NVARCHAR(20) NOT NULL,
	TRANGTHAI BIT DEFAULT 1
)
GO
CREATE TABLE CHATLIEU
(
	MACHATLIEU INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TENCHATLIEU NVARCHAR(50) NOT NULL,
	TRANGTHAI BIT DEFAULT 1
)
GO
CREATE TABLE SANPHAM
(	
	MASP int identity(1000,1) PRIMARY KEY NOT NULL,
	MADM INT FOREIGN  KEY REFERENCES DANHMUC(MADM) ON DELETE NO ACTION ON UPDATE CASCADE,
	MAVACH VARCHAR(50),
	TENSP NVARCHAR(50) NOT NULL,
	ANHSANPHAM NVARCHAR(MAX),
	GIANHAP MONEY NOT NULL,
	GIABAN MONEY NOT NULL,
	SOLUONG INT NOT NULL,
	NGAYNHAP DATE,
	APDUNGKM BIT DEFAULT 1,
	MADVT INT FOREIGN  KEY REFERENCES DONVITINH(MADVT) ON DELETE NO ACTION ON UPDATE CASCADE,
	MAMAU INT FOREIGN  KEY REFERENCES MAUSAC(MAMAU) ON DELETE NO ACTION ON UPDATE CASCADE,
	MASIZE VARCHAR(10) FOREIGN  KEY REFERENCES SIZE(MASIZE) ON DELETE NO ACTION ON UPDATE CASCADE,
	MACHATLIEU INT FOREIGN  KEY REFERENCES CHATLIEU(MACHATLIEU) ON DELETE NO ACTION ON UPDATE CASCADE,
	TRANGTHAI BIT DEFAULT 1
)
GO

CREATE TABLE KHUYENMAI
(	
	MAKM INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	MASP int FOREIGN  KEY REFERENCES SANPHAM(MASP) ON DELETE NO ACTION ON UPDATE CASCADE,
	TENKM NVARCHAR(50) NOT NULL,
	LOAIKM BIT NOT NULL,
	HINHTHUCAD BIT NOT NULL,
	GIATRI MONEY NOT NULL,
	HDTOITHIEU MONEY,
	GIAMTOIDA MONEY NOT NULL,
	NGAYBATDAU DATE NOT NULL,
	NGAYKETTHUC DATE NOT NULL,
	TRANGTHAI BIT DEFAULT 1
)
GO
CREATE TABLE TT_HOADON
(
	MATT INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	TENTT NVARCHAR(50) NOT NULL
)
GO
CREATE TABLE HOADON
(	
	MAHOADON INT IDENTITY(10001,1) PRIMARY KEY NOT NULL,
	MANV VARCHAR(20) FOREIGN  KEY REFERENCES TAIKHOAN(MANV) ON DELETE NO ACTION ON UPDATE CASCADE,
	MAKH VARCHAR(20) FOREIGN  KEY REFERENCES KHACHHANG(MAKH) ON DELETE NO ACTION ON UPDATE CASCADE,
	MATT INT FOREIGN  KEY REFERENCES TT_HOADON(MATT) ON DELETE NO ACTION ON UPDATE CASCADE,
	NGAYMUA DATE,
	GIAMGIA MONEY,
	NGAYGIAOHANG DATE,
	TIENSHIP MONEY,
	THANHTIEN MONEY,
	GHICHU NVARCHAR(MAX),
	TRANGTHAI BIT DEFAULT 1
)
GO
CREATE TABLE CT_HOADON
(	
	MAHD INT NOT NULL FOREIGN KEY REFERENCES HOADON(MAHOADON) ON DELETE NO ACTION ON UPDATE CASCADE,
	MASP INT NOT NULL FOREIGN KEY REFERENCES SANPHAM(MASP) ON DELETE NO ACTION ON UPDATE CASCADE,
	SOLUONG INT NOT NULL,
	GHICHU NVARCHAR(MAX),
	TRANGTHAI BIT DEFAULT 1
)
GO
--tạo function doanh thu today
IF OBJECT_ID('FNDTTD') IS NOT NULL
	DROP FUNCTION FNDTTD
GO
CREATE FUNCTION FNDTTD (@TODAY DATE)
RETURNS @TONG TABLE (TONGTIEN VARCHAR(20), THANHCONG NVARCHAR(30), BIHUY NVARCHAR(30))
BEGIN	
	---ĐÊM HD THANH CONG
	DECLARE @THANHCONG INT
	SET @THANHCONG = (SELECT COUNT(MAHOADON) FROM HOADON
				WHERE NGAYMUA = @TODAY AND MATT = 2)
	---ĐÊM HD THANH CONG
	DECLARE @THATBAI INT
	SET @THATBAI = (SELECT COUNT(MAHOADON) FROM HOADON
				WHERE NGAYMUA = @TODAY AND MATT = 3)
	---ĐÊM TONG TIEN
	DECLARE @TONGTIEN VARCHAR(20)
	SET @TONGTIEN = (SELECT LEFT(CONVERT(VARCHAR(20),SUM(THANHTIEN),1),CHARINDEX('.',CONVERT(VARCHAR(20),SUM(THANHTIEN),1))-1) + ' VNĐ' FROM HOADON
				WHERE NGAYMUA = @TODAY AND MATT = 2)
	---HIEN THI
	INSERT @TONG VALUES(IIF(@TONGTIEN = NULL,'0', @TONGTIEN),N'Thành công: '+CAST(@THANHCONG AS NVARCHAR),N'Bị huỷ: '+ CAST(@THATBAI AS NVARCHAR))
	RETURN
END
GO

--FUNCTION THONG KE THANG
IF OBJECT_ID('FNDTMONTH') IS NOT NULL
	DROP FUNCTION FNDTMONTH
GO
CREATE FUNCTION FNDTMONTH (@TODAY DATE)
RETURNS @TONG TABLE (TONGTIEN VARCHAR(20), THANHCONG NVARCHAR(30), BIHUY NVARCHAR(30))
BEGIN	
	---ĐÊM HD THANH CONG
	DECLARE @THANHCONG INT
	SET @THANHCONG = (SELECT COUNT(MAHOADON) FROM HOADON
				WHERE MONTH(NGAYMUA) = MONTH(@TODAY) AND YEAR(NGAYMUA) = YEAR(@TODAY) AND MATT = 2)
	---ĐÊM HD THANH CONG
	DECLARE @THATBAI INT
	SET @THATBAI = (SELECT COUNT(MAHOADON) FROM HOADON
				WHERE MONTH(NGAYMUA) = MONTH(@TODAY) AND YEAR(NGAYMUA) = YEAR(@TODAY) AND MATT = 3)
	---ĐÊM TONG TIEN
	DECLARE @TONGTIEN VARCHAR(20)
	SET @TONGTIEN = (SELECT LEFT(CONVERT(VARCHAR(20),SUM(THANHTIEN),1),CHARINDEX('.',CONVERT(VARCHAR(20),SUM(THANHTIEN),1))-1) + ' VNĐ' FROM HOADON
				WHERE MONTH(NGAYMUA) = MONTH(@TODAY) AND YEAR(NGAYMUA) = YEAR(@TODAY) AND MATT = 2)
	---HIEN THI
	INSERT @TONG VALUES(IIF(@TONGTIEN = NULL,'0', @TONGTIEN),N'Thành công: '+CAST(@THANHCONG AS NVARCHAR),N'Bị huỷ: '+ CAST(@THATBAI AS NVARCHAR))
	RETURN
END
GO

--FUNCTION THONG KE NAM
IF OBJECT_ID('FNDTYEAR') IS NOT NULL
	DROP FUNCTION FNDTYEAR
GO
CREATE FUNCTION FNDTYEAR (@TODAY DATE)
RETURNS @TONG TABLE (TONGTIEN VARCHAR(20), THANHCONG NVARCHAR(50), BIHUY NVARCHAR(50))
BEGIN	
	---ĐÊM HD THANH CONG
	DECLARE @THANHCONG INT
	SET @THANHCONG = (SELECT COUNT(MAHOADON) FROM HOADON
				WHERE YEAR(NGAYMUA) = YEAR(@TODAY) AND MATT = 2)
	---ĐÊM HD THANH CONG
	DECLARE @THATBAI INT
	SET @THATBAI = (SELECT COUNT(MAHOADON) FROM HOADON
				WHERE YEAR(NGAYMUA) = YEAR(@TODAY) AND MATT = 3)
	---ĐÊM TONG TIEN
	DECLARE @TONGTIEN VARCHAR(20)
	SET @TONGTIEN = (SELECT LEFT(CONVERT(VARCHAR(20),SUM(THANHTIEN),1),CHARINDEX('.',CONVERT(VARCHAR(20),SUM(THANHTIEN),1))-1) + ' VNĐ' FROM HOADON
				WHERE YEAR(NGAYMUA) = YEAR(@TODAY) AND MATT = 2)
	---HIEN THI
	INSERT @TONG VALUES(IIF(@TONGTIEN = NULL,'0', @TONGTIEN),N'Thành công: '+CAST(@THANHCONG AS NVARCHAR),N'Bị huỷ: '+ CAST(@THATBAI AS NVARCHAR))
	RETURN
END
GO

--FUNCTION THONG KE SO HOA DON TRONG NGAY
IF OBJECT_ID('FNSOHDDAYSINMONTH') IS NOT NULL
	DROP FUNCTION FNSOHDDAYSINMONTH
GO
CREATE FUNCTION FNSOHDDAYSINMONTH(@DAY DATE)
RETURNS INT
AS
BEGIN
	DECLARE @SOHD INT
	SET @SOHD = (SELECT COUNT(MAHOADON) FROM HOADON WHERE NGAYMUA = @DAY AND MATT = 2)
	RETURN @SOHD
END
GO

--FUNCTION THONG KE SO HOA DON NAM
IF OBJECT_ID('FNSOHDDAYSINYEARS') IS NOT NULL
	DROP FUNCTION FNSOHDDAYSINYEARS
GO
CREATE FUNCTION FNSOHDDAYSINYEARS(@YEAR INT)
RETURNS VARCHAR(30)
AS
BEGIN
	DECLARE @DOANHTHU MONEY
	SET @DOANHTHU = (SELECT SUM(THANHTIEN) FROM HOADON WHERE YEAR(NGAYMUA) = @YEAR AND MATT = 2)
	RETURN CONVERT(VARCHAR(30),@DOANHTHU,1)
	RETURN @DOANHTHU
END
GO

--FUNCTION THONG KE SO HOA DON TRONG NAM
IF OBJECT_ID('FNSOHDDAYSINYEAR') IS NOT NULL
	DROP FUNCTION FNSOHDDAYSINYEAR
GO
CREATE FUNCTION FNSOHDDAYSINYEAR(@MONTH INT, @YEAR INT)
RETURNS VARCHAR(30)
AS
BEGIN
	DECLARE @DOANHTHU MONEY
	SET @DOANHTHU = (SELECT SUM(THANHTIEN) FROM HOADON WHERE MONTH(NGAYMUA) = @MONTH AND YEAR(NGAYMUA) = @YEAR AND MATT = 2)
	RETURN CONVERT(VARCHAR(50),@DOANHTHU,1)
	RETURN @DOANHTHU
END
GO

--FUNCTION THONG KE DOANH THU TRONG NGAY
IF OBJECT_ID('FNDTDAYSINMONTH') IS NOT NULL
	DROP FUNCTION FNDTDAYSINMONTH
GO
CREATE FUNCTION FNDTDAYSINMONTH(@DAY DATE)
RETURNS VARCHAR(50)
AS
BEGIN
	DECLARE @DOANHTHU MONEY
	SET @DOANHTHU = (SELECT SUM(THANHTIEN) FROM HOADON WHERE NGAYMUA = @DAY AND MATT = 2)
	RETURN CONVERT(VARCHAR(50),@DOANHTHU,1)
END
GO

--tạo thủ tục lưu thống kê DOANH THU NGAY TRONG THANG
IF OBJECT_ID('SP_DTBYNGAYINMONTH') IS NOT NULL
	DROP PROC SP_DTBYNGAYINMONTH
	GO
CREATE PROC SP_DTBYNGAYINMONTH (@MONTH INT, @YEAR INT)
AS BEGIN
	SELECT
		HD.NGAYMUA NGAYMUA,
		dbo.FNSOHDDAYSINMONTH(NGAYMUA) SOHD,							
		SUM(CT.SOLUONG) SOLUONG,
		LEFT(CONVERT(VARCHAR(50),SUM(CT.SOLUONG * SP.GIABAN),1),CHARINDEX('.',CONVERT(VARCHAR(50),SUM(CT.SOLUONG * SP.GIABAN),1)) -1) GIABAN,
		LEFT(CONVERT(VARCHAR(50),SUM(GIAMGIA),1),CHARINDEX('.',CONVERT(VARCHAR(50),SUM(GIAMGIA),1)) -1) GIAMGIA,
		LEFT(dbo.FNDTDAYSINMONTH(NGAYMUA),CHARINDEX('.',dbo.FNDTDAYSINMONTH(NGAYMUA))-1) AS DOANHTHU
	FROM HOADON HD
		JOIN CT_HOADON CT ON CT.MAHD = HD.MAHOADON
		JOIN SANPHAM SP ON SP.MASP = CT.MASP
	WHERE YEAR(NGAYMUA) = @YEAR AND MONTH(NGAYMUA) = @MONTH AND MATT = 2
	GROUP BY NGAYMUA
END
GO

--tạo thủ tục lưu biểu đồ DOANH THU tháng này
IF OBJECT_ID('SP_CHARTMONTH') IS NOT NULL
	DROP PROC SP_CHARTMONTH
	GO
CREATE PROC SP_CHARTMONTH (@MONTH INT, @YEAR INT)
AS BEGIN
	SELECT
		DAY(HD.NGAYMUA) NGAY,
		SUM(THANHTIEN) THANHTIEN
	FROM HOADON HD
	WHERE MONTH(HD.NGAYMUA) = @MONTH AND YEAR(HD.NGAYMUA) = @YEAR AND MATT = 2
	GROUP BY DAY(HD.NGAYMUA)
END
GO

--tạo thủ tục lưu biểu đồ DOANH THU NAM
IF OBJECT_ID('SP_CHARTYEAR') IS NOT NULL
	DROP PROC SP_CHARTYEAR
	GO
CREATE PROC SP_CHARTYEAR (@YEAR INT)
AS BEGIN
	SELECT
		 MONTH(HD.NGAYMUA) THANG,
		SUM(THANHTIEN) THANHTIEN
	FROM HOADON HD
	WHERE YEAR(HD.NGAYMUA) = @YEAR AND MATT = 2
	GROUP BY MONTH(HD.NGAYMUA)
END
GO

--tạo thủ tục lưu biểu đồ DOANH THU CÁC NĂM
IF OBJECT_ID('SP_CHARTYEARS') IS NOT NULL
	DROP PROC SP_CHARTYEARS
	GO
CREATE PROC SP_CHARTYEARS
AS BEGIN
	SELECT
		 YEAR(HD.NGAYMUA) NAM,
		SUM(THANHTIEN) THANHTIEN
	FROM HOADON HD
	WHERE MATT = 2
	GROUP BY YEAR(HD.NGAYMUA)
END
GO

--tạo thủ tục lưu thống kê DOANH THU THANG TRONG NAM
IF OBJECT_ID('SP_DTTHANG') IS NOT NULL
	DROP PROC SP_DTTHANG 
	GO
CREATE PROC SP_DTTHANG (@YEAR INT)
AS BEGIN
	SELECT
		CAST(MONTH(HD.NGAYMUA) AS VARCHAR) + '/' + CAST(YEAR(HD.NGAYMUA) AS VARCHAR)THANG,
		COUNT(MAHOADON) SOHD,
		SUM(CT.SOLUONG) SOLUONG,
		LEFT(CONVERT(VARCHAR(50),SUM(CT.SOLUONG * SP.GIABAN),1),CHARINDEX('.',CONVERT(VARCHAR(50),SUM(CT.SOLUONG * SP.GIABAN),1)) -1) GIABAN,
		LEFT(CONVERT(VARCHAR(50),SUM(GIAMGIA),1),CHARINDEX('.',CONVERT(VARCHAR(50),SUM(GIAMGIA),1)) -1) GIAMGIA,
		LEFT(dbo.FNSOHDDAYSINYEAR(MONTH(NGAYMUA), 2022),CHARINDEX('.',dbo.FNSOHDDAYSINYEAR(MONTH(NGAYMUA), 2022))-1) AS DOANHTHU
	FROM HOADON HD
		JOIN CT_HOADON CT ON CT.MAHD = HD.MAHOADON
		JOIN SANPHAM SP ON SP.MASP = CT.MASP
	WHERE YEAR(NGAYMUA) = @YEAR AND MATT = 2
	GROUP BY MONTH(NGAYMUA), YEAR(NGAYMUA)
END
GO

--tạo thủ tục lưu thống kê DOANH THU NAM
IF OBJECT_ID('SP_DTNAM') IS NOT NULL
	DROP PROC SP_DTNAM
	GO
CREATE PROC SP_DTNAM
AS BEGIN
	SELECT		
		YEAR(HD.NGAYMUA) NAM,
		COUNT(HD.MAHOADON) SOHD,							
		SUM(CT.SOLUONG) SOLUONG,
		LEFT(CONVERT(VARCHAR(50),SUM(CT.SOLUONG * SP.GIABAN),1),CHARINDEX('.',CONVERT(VARCHAR(50),SUM(CT.SOLUONG * SP.GIABAN),1)) -1) GIABAN,
		LEFT(CONVERT(VARCHAR(50),SUM(GIAMGIA),1),CHARINDEX('.',CONVERT(VARCHAR(50),SUM(GIAMGIA),1)) -1)  GIAMGIA,
		LEFT(dbo.FNSOHDDAYSINYEARS(YEAR(NGAYMUA)),CHARINDEX('.',dbo.FNSOHDDAYSINYEARS(YEAR(NGAYMUA)))-1)  AS DOANHTHU
	FROM HOADON HD
		JOIN CT_HOADON CT ON CT.MAHD = HD.MAHOADON
		JOIN SANPHAM SP ON SP.MASP = CT.MASP
		WHERE MATT = 2
	GROUP BY YEAR(HD.NGAYMUA)
END
GO

if OBJECT_id('sp_timkiemSPdaxoa') is not null	--tìm kiếm sản phẩm đã bị xóa
	drop proc sp_timkiemSPdaxoa
GO
CREATE PROC sp_timkiemSPdaxoa 
		--@MASP VARCHAR(50), @MAVACH VARCHAR(50), @TENSP NVARCHAR(50), @TENDANHMUC NVARCHAR(50), @TENMAU NVARCHAR(20),
		--@MASIZE VARCHAR(10), @TENCHATLIEU NVARCHAR(50)
AS BEGIN
	    SELECT SANPHAM.MASP, SANPHAM.TENSP, SANPHAM.MAVACH, 
				SANPHAM.GIABAN, SANPHAM.GIANHAP, SANPHAM.SOLUONG,
				DANHMUC.TENDANHMUC, DONVITINH.TENDVT, SANPHAM.MASIZE, 
				MAUSAC.TENMAU, CHATLIEU.TENCHATLIEU, SANPHAM.NGAYNHAP, SANPHAM.ANHSANPHAM
                  
		FROM     
				  CHATLIEU INNER JOIN
                  SANPHAM ON CHATLIEU.MACHATLIEU = SANPHAM.MACHATLIEU INNER JOIN
                  DANHMUC ON SANPHAM.MADM = DANHMUC.MADM INNER JOIN
                  MAUSAC ON SANPHAM.MAMAU = MAUSAC.MAMAU INNER JOIN
                  SIZE ON SANPHAM.MASIZE = SIZE.MASIZE INNER JOIN
                  DONVITINH ON SANPHAM.MADVT = DONVITINH.MADVT
		WHERE 
				  SANPHAM.TRANGTHAI = 0
END				  
GO
--TÌM KIẾM SP
if OBJECT_id('sp_timkiem') is not null	--drop proc sp_timkiemgo--IF OBJECT_ID('SPTIMKIEM') IS NOT NULL
	drop proc sp_timkiem
GO
CREATE PROC sp_timkiem 
		@MASP VARCHAR(50), @MAVACH VARCHAR(50), @TENSP NVARCHAR(50), @TENDANHMUC NVARCHAR(50), @TENMAU NVARCHAR(20),
		@MASIZE VARCHAR(10), @TENCHATLIEU NVARCHAR(50)
AS BEGIN
		SELECT SANPHAM.MASP, SANPHAM.TENSP, SANPHAM.MAVACH, 
				SANPHAM.GIABAN, SANPHAM.GIANHAP, SANPHAM.SOLUONG,
				DANHMUC.TENDANHMUC, DONVITINH.TENDVT, SANPHAM.MASIZE, 
				MAUSAC.TENMAU, CHATLIEU.TENCHATLIEU, SANPHAM.NGAYNHAP, SANPHAM.ANHSANPHAM
                  
		FROM     
				  CHATLIEU INNER JOIN
                  SANPHAM ON CHATLIEU.MACHATLIEU = SANPHAM.MACHATLIEU INNER JOIN
                  DANHMUC ON SANPHAM.MADM = DANHMUC.MADM INNER JOIN
                  MAUSAC ON SANPHAM.MAMAU = MAUSAC.MAMAU INNER JOIN
                  SIZE ON SANPHAM.MASIZE = SIZE.MASIZE INNER JOIN
                  DONVITINH ON SANPHAM.MADVT = DONVITINH.MADVT
		WHERE 
				  SANPHAM.TRANGTHAI = 1
				  and (SANPHAM.MASP like @MASP
				  or MAVACH like @MAVACH
				  or SANPHAM.TENSP like @TENSP 
				  and  DANHMUC.TENDANHMUC like @TENDANHMUC
				  and MAUSAC.TENMAU like @TENMAU 
				  and SANPHAM.MASIZE like @MASIZE 
				  and CHATLIEU.TENCHATLIEU like @TENCHATLIEU)
	END
GO
--Tạo proc xem Sản Phẩm còn lại
	IF OBJECT_id('SP_SP') is not null
		DROP PROC SP_SP
	GO
	CREATE PROC SP_SP
	AS BEGIN
	SELECT
		SP.MASP, SP.TENSP, SP.MAVACH, DM.TENDANHMUC, MS.TENMAU, DVT.TENDVT, 
		S.MASIZE, CL.TENCHATLIEU, SP.SOLUONG
	FROM SANPHAM SP
		JOIN DANHMUC DM ON DM.MADM = SP.MADM
		JOIN CHATLIEU CL ON CL.MACHATLIEU = SP.MACHATLIEU
		JOIN DONVITINH DVT ON DVT.MADVT = SP.MADVT
		JOIN SIZE S ON S.MASIZE = SP.MASIZE
		JOIN MAUSAC MS ON MS.MAMAU = SP.MAMAU
	ORDER BY SP.SOLUONG ASC
	END
GO
--FUNCTION TINH GIA TIEN SAU KM CUA SAN PHAM
IF OBJECT_ID('FNSPKM') IS NOT NULL
	DROP FUNCTION FNSPKM
GO
CREATE FUNCTION FNSPKM(@MASP INT)
RETURNS MONEY
AS
BEGIN	
	DECLARE @GIATRI INT
	SET @GIATRI = (SELECT TOP 1 CONVERT(INT, GIATRI,1) FROM SANPHAM SP LEFT JOIN KHUYENMAI KM ON KM.MASP = SP.MASP WHERE SP.MASP = @MASP AND KM.TRANGTHAI = 1 AND KM.NGAYBATDAU <= CAST(GETDATE() AS DATE) AND CAST(GETDATE() AS DATE)  <= KM.NGAYKETTHUC)
	
	DECLARE @MONEY MONEY
	IF (LEN(@GIATRI) > 2) 
		BEGIN
			SET @MONEY = (SELECT TOP 1 IIF(SP.APDUNGKM = 0, SP.GIABAN, IIF(SP.GIABAN - KM.GIATRI IS NULL, SP.GIABAN, SP.GIABAN - KM.GIATRI) )  FROM SANPHAM SP LEFT JOIN KHUYENMAI KM ON KM.MASP = SP.MASP WHERE SP.MASP = @MASP AND KM.TRANGTHAI =1 AND KM.TRANGTHAI = 1 AND KM.NGAYBATDAU <= CAST(GETDATE() AS DATE) AND CAST(GETDATE() AS DATE)  <= KM.NGAYKETTHUC)
		END
	ELSE 
		BEGIN
			SET @MONEY = (SELECT TOP 1 IIF(SP.APDUNGKM = 0, SP.GIABAN, IIF(SP.GIABAN * (KM.GIATRI /100)IS NULL, SP.GIABAN, SP.GIABAN - (SP.GIABAN * (KM.GIATRI/100))) )  FROM SANPHAM SP LEFT JOIN KHUYENMAI KM ON KM.MASP = SP.MASP WHERE SP.MASP = @MASP AND KM.TRANGTHAI = 1 AND KM.NGAYBATDAU <= CAST(GETDATE() AS DATE) AND CAST(GETDATE() AS DATE)  <= KM.NGAYKETTHUC)
		END
	RETURN  @MONEY
END
GO

-- TIM KIEM KHACH HANG
If OBJECT_ID('SP_searchCusToms') is not null
 drop proc SP_searchCusToms
GO 
Create PROC SP_searchCusToms
 @maKH varchar(50),@maLoaiKH int,@HoTen nvarchar(50),@DiaChi nvarchar(50) 
AS BEGIN
 SELECT MAKH,MALOAIKH,HOTEN,TICHDIEM,NGAYSINH,GIOITINH,EMAIL,SDT,DIACHI,NGAYTAO
 FROM KHACHHANG
 WHERE TRANGTHAI = 1 and
    MALOAIKH = @maLoaiKH and
    (MAKH like @maKH or
    HOTEN like @HoTen or
    DIACHI like @DiaChi)
END
Go