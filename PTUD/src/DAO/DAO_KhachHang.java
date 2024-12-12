/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Connect.KetNoiDB;
import ENTITY.KhachHang;
import ENTITY.LoaiTaiKhoan;
import ENTITY.TaiKhoan;

/**
 *
 * @author ADMIN
 */
public class DAO_KhachHang {
	private ArrayList<KhachHang> dsKhachHang;

	//////////////////////////////////////// HÀM
	// Trả về list Lịch Bay
	public ArrayList<KhachHang> getalltbKhachHang() {
		dsKhachHang = new ArrayList<KhachHang>();
		try {
			KetNoiDB.getInstance();
			Connection con = KetNoiDB.getConnection();
			String sql = "SELECT * FROM KhachHang";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				String maKhachHang = rs.getString("MaKhachHang");
				String tenKhachHang = rs.getString("TenKhachHang");
				String soDienThoai = rs.getString("SoDienThoai");
				String email = rs.getString("Email");
				String gioiTinh = rs.getString("GioiTinh");
				String cmnd = rs.getString("ChungMinhNhanDan");

				KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, soDienThoai, email, gioiTinh, cmnd);
				dsKhachHang.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsKhachHang;
	}
	
	/// tra ve mot KhachHang theo ma 
		public KhachHang getOneObjKhachHang(String maObj) {
			KhachHang khachHang = null;
			try {
				KetNoiDB.getInstance();
				Connection con = KetNoiDB.getConnection();
				String sql = "Select * from KhachHang where MaKhachHang = ?";
				PreparedStatement preparedStatement = con.prepareStatement(sql);
				preparedStatement.setString(1, maObj);
				ResultSet rs = preparedStatement.executeQuery();
				if (rs.next()) {
					String maKhachHang = rs.getString("MaKhachHang");
					String tenKhachHang = rs.getString("TenKhachHang");
					String soDienThoai = rs.getString("SoDienThoai");
					String email = rs.getString("Email");
					String gioiTinh = rs.getString("GioiTinh");
					String cmnd = rs.getString("ChungMinhNhanDan");
					khachHang = new KhachHang(maKhachHang, tenKhachHang, soDienThoai, email, gioiTinh, cmnd);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return khachHang;
		}
	/// tra ve mot KhachHang theo sodienthoai
	public KhachHang getOneObjKhachHangTheoSoDienThoai(String sdtObj) {
		KhachHang khachHang = null;
		try {
			KetNoiDB.getInstance();
			Connection con = KetNoiDB.getConnection();
			String sql = "Select * from KhachHang where SoDienThoai = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, sdtObj);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String maKhachHang = rs.getString("MaKhachHang");
				String tenKhachHang = rs.getString("TenKhachHang");
				String soDienThoai = rs.getString("SoDienThoai");
				String email = rs.getString("Email");
				String gioiTinh = rs.getString("GioiTinh");
				String cmnd = rs.getString("ChungMinhNhanDan");
				khachHang = new KhachHang(maKhachHang, tenKhachHang, soDienThoai, email, gioiTinh, cmnd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return khachHang;
	}
}
