package DAO;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import Connect.KetNoiDB;
import ENTITY.ChiTietHoaDon;
import ENTITY.ChiTietVe;
import ENTITY.HoaDon;
import ENTITY.KhachHang;
import ENTITY.LoaiVe;
import ENTITY.NhanVien;
import ENTITY.TrangThaiGhe;
import ENTITY.VeMayBay;

public class DAO_ChiTietHoaDon {
	// list
	private ArrayList<ChiTietHoaDon> dsChiTietHoaDon;

	// dao
	private DAO_KhachHang dao_KhachHang = new DAO_KhachHang();
	private DAO_VeMayBay dao_VeMayBay = new DAO_VeMayBay();
	private DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
	private DAO_HoaDon dao_HoaDon = new DAO_HoaDon();

	//////////////////////////////////////// HÀM
	// Trả về list ChiTietHoaDon
	public ArrayList<ChiTietHoaDon> getalltbChiTietHoaDon() {
		dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		try {
			KetNoiDB.getInstance();
			Connection con = KetNoiDB.getConnection();
			String sql = "SELECT * FROM ChiTietHoaDon";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				HoaDon hd = dao_HoaDon.getOneObjHoaDon(rs.getString("MaHoaDon"));
				VeMayBay vmb = dao_VeMayBay.getOneObjVeMayBay(rs.getString("MaVe"));
				NhanVien nv = dao_NhanVien.getOneObjNhanVien(rs.getString("MaNhanVien"));
				double TongTien = rs.getDouble("TongTien");
				LocalDateTime ngayLap = rs.getTimestamp("NgayLapHoaDon").toLocalDateTime();

				ChiTietHoaDon cthd = new ChiTietHoaDon(hd, vmb, nv, TongTien, ngayLap);
				dsChiTietHoaDon.add(cthd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsChiTietHoaDon;
	}

	/// tra ve mot ChiTietHoaDon theo ma
	public ChiTietHoaDon getOneObjChiTietHoaDon(String maHoaDon, String maVe, String maNhanVien) {
		ChiTietHoaDon chiTietHoaDon = null;
		try {
			KetNoiDB.getInstance();
			Connection con = KetNoiDB.getConnection();
			String sql = "SELECT * FROM ChiTietHoaDon WHERE MaHoaDon = ? AND MaVe = ? AND MaNhanVien = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maHoaDon);
			preparedStatement.setString(2, maVe);
			preparedStatement.setString(3, maNhanVien);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				// Lấy các giá trị cần thiết từ ResultSet
				HoaDon hd = dao_HoaDon.getOneObjHoaDon(rs.getString("MaHoaDon"));
				VeMayBay vmb = dao_VeMayBay.getOneObjVeMayBay(rs.getString("MaVe"));
				NhanVien nv = dao_NhanVien.getOneObjNhanVien(rs.getString("MaNhanVien"));
				double TongTien = rs.getDouble("TongTien");
				LocalDateTime ngayLap = rs.getTimestamp("NgayLapHoaDon").toLocalDateTime();

				chiTietHoaDon = new ChiTietHoaDon(hd, vmb, nv, TongTien, ngayLap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chiTietHoaDon;
	}

	public boolean themChiTietHoaDon(ChiTietHoaDon cthd) {
	    KetNoiDB.getInstance();
	    Connection con = KetNoiDB.getConnection();
	    PreparedStatement stmt = null;
	    int n = 0;
	    try {
	        stmt = con.prepareStatement(
	                "INSERT INTO ChiTietHoaDon (MaHoaDon, MaVe, MaNhanVien, TongTien, NgayLapHoaDon) VALUES (?, ?, ?, ?, ?)");
	        stmt.setString(1, cthd.getMaHoaDon().getMaHoaDon());
	        stmt.setString(2, cthd.getMaVe().getMaVe());
	        stmt.setString(3, cthd.getMaNhanVien().getMaNhanVien());
	        stmt.setDouble(4, cthd.getTongTien());
	        
	        // Chuyển đổi LocalDateTime sang Timestamp
	        LocalDateTime ngayLap = cthd.getNgayLapHoaDon();
	        if (ngayLap != null) {
	            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(ngayLap));
	        } else {
	            stmt.setTimestamp(5, null);
	        }

	        n = stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return n > 0;
	}

	public String in() {
		return dsChiTietHoaDon.toString();
	}

}
