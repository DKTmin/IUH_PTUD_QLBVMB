package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Connect.KetNoiDB;
import ENTITY.ChiTietHoaDon;
import ENTITY.ChiTietVe;
import ENTITY.HoaDon;
import ENTITY.KhachHang;
import ENTITY.LoaiVe;
import ENTITY.NhanVien;
import ENTITY.TrangThaiGhe;
import ENTITY.VeMayBay;

public class DAO_ChiTietVe {
	private ArrayList<ChiTietVe> dsChiTietVe;

	// dao
	DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
	DAO_TrangThaiGhe dao_TrangThaiGhe = new DAO_TrangThaiGhe();
	DAO_KhachHang dao_KhachHang = new DAO_KhachHang();
	DAO_LoaiVe dao_LoaiVe = new DAO_LoaiVe();

	// Trả về list trang thai Ghe
	public ArrayList<ChiTietVe> getalltbChiTietVe() {
		dsChiTietVe = new ArrayList<ChiTietVe>();
		try {
			KetNoiDB.getInstance();
			Connection con = KetNoiDB.getConnection();
			String sql = "SELECT * FROM ChiTietVe";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				TrangThaiGhe maMaTTGhe = dao_TrangThaiGhe.getOneObjTrangThaiGhe(rs.getString("MaTrangThaiGhe"));
				LoaiVe maLoaiVe = dao_LoaiVe.getOneObjLoaiVe(rs.getString("MaLoaiVe"));
				KhachHang maKhachHang = dao_KhachHang.getOneObjKhachHang(rs.getString("MaKhachHang"));
				NhanVien maNhanVien = dao_NhanVien.getOneObjNhanVien(rs.getString("MaNhanVien"));

				ChiTietVe chitietve = new ChiTietVe(maMaTTGhe, maLoaiVe, maKhachHang, maNhanVien);
				dsChiTietVe.add(chitietve);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsChiTietVe;
	}

	/// tra ve mot ChiTietVe theo ma
	public ChiTietVe getOneObjChiTietVe(String maObj) {
		ChiTietVe chiTietVe = null;
		try {
			KetNoiDB.getInstance();
			Connection con = KetNoiDB.getConnection();
			String sql = "SELECT * FROM ChiTietVe WHERE MaTrangThaiGhe = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maObj);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				TrangThaiGhe maMaTTGhe = dao_TrangThaiGhe.getOneObjTrangThaiGhe(rs.getString("MaTrangThaiGhe"));
				LoaiVe maLoaiVe = dao_LoaiVe.getOneObjLoaiVe(rs.getString("MaLoaiVe"));
				KhachHang maKhachHang = dao_KhachHang.getOneObjKhachHang(rs.getString("MaKhachHang"));
				NhanVien maNhanVien = dao_NhanVien.getOneObjNhanVien(rs.getString("MaNhanVien"));

				chiTietVe = new ChiTietVe(maMaTTGhe, maLoaiVe, maKhachHang, maNhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chiTietVe;
	}

	public boolean themChiTietVe(ChiTietVe ctv) {
		KetNoiDB.getInstance();
		Connection con = KetNoiDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"Insert into ChiTietVe (MaTrangThaiGhe,MaLoaiVe,MaKhachHang,MaNhanVien)" + " values(?,?,?,?)");
			stmt.setString(1, ctv.getMaTrangThaiGhe().getMaChiTietTrangThai());
			stmt.setString(2, ctv.getMaLoaiVe().getMaLoaiVe());
			stmt.setString(3, ctv.getMaKhachHang().getMaKhachHang());
			stmt.setString(4, ctv.getMaNhanVien().getMaNhanVien());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
}
