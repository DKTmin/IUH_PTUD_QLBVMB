package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Connect.KetNoiDB;
import ENTITY.ChiTietVe;
import ENTITY.HoaDon;
import ENTITY.KhachHang;

public class DAO_HoaDon {
	// list
	private ArrayList<HoaDon> dsHoaDon;

	// dao
	private DAO_KhachHang dao_KhachHang = new DAO_KhachHang();

	//////////////////////////////////////// HÀM
	// Trả về list HoaDon
	public ArrayList<HoaDon> getalltbHoaDon() {
		dsHoaDon = new ArrayList<HoaDon>();
		try {
			KetNoiDB.getInstance();
			Connection con = KetNoiDB.getConnection();
			String sql = "SELECT * FROM HoaDon";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				String maHoaDon = rs.getString("MaHoaDon");
				KhachHang maKhachHang = dao_KhachHang.getOneObjKhachHang(rs.getString("MaKhachHang"));
				int soLuong = rs.getInt("SoLuong");
				double gia = rs.getDouble("Gia");
				boolean trangThai = rs.getBoolean("TrangThai");

				HoaDon hd = new HoaDon(maHoaDon, maKhachHang, soLuong, gia, trangThai);
				dsHoaDon.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsHoaDon;
	}

	/// tra ve mot HoaDon theo ma
	public HoaDon getOneObjHoaDon(String maObj) {
		HoaDon hoaDon = null;
		try {
			KetNoiDB.getInstance();
			Connection con = KetNoiDB.getConnection();
			String sql = "Select * from HoaDon where MaHoaDon = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maObj);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String maHoaDon = rs.getString("MaHoaDon");
				KhachHang maKhachHang = dao_KhachHang.getOneObjKhachHang(rs.getString("MaKhachHang"));
				int soLuong = rs.getInt("SoLuong");
				double gia = rs.getDouble("Gia");
				boolean trangThai = rs.getBoolean("TrangThai");
				hoaDon = new HoaDon(maHoaDon, maKhachHang, soLuong, gia, trangThai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hoaDon;
	}
	
	public boolean themHoaDon(HoaDon hd) {
		KetNoiDB.getInstance();
		Connection con = KetNoiDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"Insert into HoaDon (MaHoaDon,MaKhachHang,SoLuong,Gia,TrangThai)" + " values(?,?,?,?,?)");
			stmt.setString(1, hd.getMaHoaDon());
			stmt.setString(2, hd.getMaKhachHang().getMaKhachHang());
			stmt.setInt(3, hd.getSoLuong());
			stmt.setDouble(4, hd.getGia());
			stmt.setBoolean(5, hd.isTrangThai());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
}
