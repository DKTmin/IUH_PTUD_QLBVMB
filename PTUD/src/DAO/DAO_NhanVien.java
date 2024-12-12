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
import ENTITY.NhanVien;
import ENTITY.TaiKhoan;

/**
 *
 * @author ADMIN
 */
public class DAO_NhanVien {
	//dao
	private DAO_TaiKhoan dao_TaiKhoan = new DAO_TaiKhoan(); 
	
	private ArrayList<NhanVien> dsNhanVien;

	//////////////////////////////////////// HÀM
	// Trả về list Nhan Vien
	public ArrayList<NhanVien> getalltbKhachHang() {
		dsNhanVien = new ArrayList<NhanVien>();
	    try {
	        KetNoiDB.getInstance();
	        Connection con = KetNoiDB.getConnection();
	        String sql = "SELECT * FROM NhanVien";
	        Statement statement = con.createStatement();
	        ResultSet rs = statement.executeQuery(sql);
	        
	        while (rs.next()) {
	            String maNhanVien = rs.getString("MaNhanVien");
	            String tenNhanVien = rs.getString("TenNhanVien");
	            String email = rs.getString("Email");
	            String diaChi = rs.getString("DiaChi");
	            String soDienThoai = rs.getString("SoDienThoai");
	            String gioiTinh = rs.getString("GioiTinh");
	            String hinhAnh = rs.getString("HinhAnh");
	            TaiKhoan maTaiKhoan = dao_TaiKhoan.getOneObjTaiKhoan( rs.getString("MaTaiKhoan")); 
	            
	            NhanVien nv = new NhanVien(maNhanVien, tenNhanVien, email, diaChi, soDienThoai, gioiTinh, hinhAnh, maTaiKhoan); 
	            dsNhanVien.add(nv);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsNhanVien;
	}
	
	// tra ve mot nhan vien 
	public NhanVien getOneObjNhanVien(String maObj) {
		NhanVien nv = null;
		try {
			KetNoiDB.getInstance();
			Connection con = KetNoiDB.getConnection();
			String sql = "Select * from NhanVien where MaNhanVien = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maObj);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String maNhanVien = rs.getString("MaNhanVien");
	            String tenNhanVien = rs.getString("TenNhanVien");
	            String email = rs.getString("Email");
	            String diaChi = rs.getString("DiaChi");
	            String soDienThoai = rs.getString("SoDienThoai");
	            String gioiTinh = rs.getString("GioiTinh");
	            String hinhAnh = rs.getString("HinhAnh");
	            TaiKhoan maTaiKhoan = dao_TaiKhoan.getOneObjTaiKhoan( rs.getString("MaTaiKhoan")); 
	            
	            nv = new NhanVien(maNhanVien, tenNhanVien, email, diaChi, soDienThoai, gioiTinh, hinhAnh, maTaiKhoan); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}
}
