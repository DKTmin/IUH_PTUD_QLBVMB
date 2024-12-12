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


/**
 *
 * @author ADMIN
 */
public class DAO_KhachHang {
public ArrayList<KhachHang> dsKhachHang; 
	
	public ArrayList<KhachHang> getallTBKhachHang(){
		dsKhachHang = new ArrayList<KhachHang>();
		try {
			KetNoiDB.getInstance();
			Connection con = KetNoiDB.getConnection();
			String sql = "Select * from KhachHang";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maKH = rs.getString("MaKhachHang");
				String hoTen = rs.getString("TenKhachHang");
				String SoDienThoai = rs.getString("SoDienThoai"); 
				String cccd = rs.getString("ChungMinhNhanDan"); 
				String email = rs.getString("Email"); 
				String gioiTinh = rs.getString("GioiTinh"); 
				KhachHang kh = new KhachHang(maKH, hoTen, SoDienThoai,email,gioiTinh, cccd); 
				dsKhachHang.add(kh);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return dsKhachHang;
	}
	
	public boolean themKhachHang(KhachHang kh) { 
		KetNoiDB.getInstance();
		Connection con = KetNoiDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("Insert into KhachHang (MaKhachHang,TenKhachHang,SoDienThoai,ChungMinhNhanDan,Email,GioiTinh)" + " values(?,?,?,?,?,?)");
			stmt.setString(1, kh.getMaKH());
			stmt.setString(2, kh.getHoTen());
			stmt.setString(3, kh.getSoDienThoai());
			stmt.setString(4, kh.getCccd());
			stmt.setString(5, kh.getEmail());
			stmt.setString(6, kh.getGioiTinh());
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean xoaKhachHang(String maKhachHang) {
	    Connection con = KetNoiDB.getConnection();
	    PreparedStatement stmt = null;
	    int n = 0;
	    try {
	        // SQL để xóa khách hàng theo mã khách hàng
	        String sql = "DELETE FROM KhachHang WHERE MaKhachHang = ?";
	        stmt = con.prepareStatement(sql);
	        stmt.setString(1, maKhachHang);
	        
	        // Thực thi câu lệnh SQL
	        n = stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return n > 0;
	}
	public boolean updateCustomer(KhachHang kh) {
	    // Lấy kết nối từ lớp KetNoiDB
	    Connection con = KetNoiDB.getConnection();
	    PreparedStatement stmt = null;
	    int n = 0;

	    try {
	        // SQL để cập nhật thông tin khách hàng
	        String sql = "UPDATE KhachHang SET TenKhachHang = ?, SoDienThoai = ?, Email = ?, ChungMinhNhanDan = ?, GioiTinh = ? WHERE MaKhachHang = ?";
	        stmt = con.prepareStatement(sql);
	        
	        // Gán giá trị từ đối tượng KhachHang vào câu lệnh SQL
	        stmt.setString(1, kh.getHoTen());
	        stmt.setString(2, kh.getSoDienThoai());
	        stmt.setString(3, kh.getEmail());
	        stmt.setString(4, kh.getCccd());
	        stmt.setString(5, kh.getGioiTinh());
	        stmt.setString(6, kh.getMaKH());  // MaKhachHang là điều kiện để cập nhật
	        
	        // Thực thi câu lệnh SQL
	        n = stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return n > 0; // Trả về true nếu có ít nhất một dòng dữ liệu được cập nhật
	}

	
}
