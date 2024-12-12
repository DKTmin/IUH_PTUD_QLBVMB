package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Connect.KetNoiDB;
import ENTITY.TrangThaiGhe;
import ENTITY.Ghe;
import ENTITY.LichBay;

public class DAO_TrangThaiGhe {
	// dao
	private DAO_Ghe dao_ghe = new DAO_Ghe();
	private DAO_LichBay dao_lichBay = new DAO_LichBay();

	private ArrayList<TrangThaiGhe> dsTrangThaiGhe;

	//////////////////////////////////////// HÀM
	// Trả về list trang thai Ghe
	public ArrayList<TrangThaiGhe> getalltbTrangThaiGhe() {
		dsTrangThaiGhe = new ArrayList<TrangThaiGhe>();
		try {
			KetNoiDB.getInstance();
			Connection con = KetNoiDB.getConnection();
			String sql = "SELECT * FROM TrangThaiGhe";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				String maMaTTGhe = rs.getString("MaChiTietTrangThai");
				String maGhe = rs.getString("MaGhe");
				String maLichBay = rs.getString("MaLichBay");
				boolean trangThai = rs.getBoolean("TrangThai");

				Ghe ghe = dao_ghe.getOneObjGhe(maGhe);
				LichBay lichBay = dao_lichBay.getOneObjLichBay(maLichBay);

				TrangThaiGhe lb = new TrangThaiGhe(maMaTTGhe, ghe, lichBay, trangThai);
				dsTrangThaiGhe.add(lb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTrangThaiGhe;
	}
	
	/// tra ve mot KhachHang theo sodienthoai
		public TrangThaiGhe getOneObjTrangThaiGhe(String maObj) {
			TrangThaiGhe trangThaiGhe = null;
			try {
				KetNoiDB.getInstance();
				Connection con = KetNoiDB.getConnection();
				String sql = "Select * from TrangThaiGhe where MaChiTietTrangThai = ?";
				PreparedStatement preparedStatement = con.prepareStatement(sql);
				preparedStatement.setString(1, maObj);
				ResultSet rs = preparedStatement.executeQuery();
				if (rs.next()) {
					String maMaTTGhe = rs.getString("MaChiTietTrangThai");
					String maGhe = rs.getString("MaGhe");
					String maLichBay = rs.getString("MaLichBay");
					boolean trangThai = rs.getBoolean("TrangThai");

					Ghe ghe = dao_ghe.getOneObjGhe(maGhe);
					LichBay lichBay = dao_lichBay.getOneObjLichBay(maLichBay);
					trangThaiGhe =  new TrangThaiGhe(maMaTTGhe, ghe, lichBay, trangThai);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return trangThaiGhe;
		}
	
	public boolean themTrangThaiGhe(TrangThaiGhe ttg) { 
		KetNoiDB.getInstance();
		Connection con = KetNoiDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("Insert into TrangThaiGhe (MaChiTietTrangThai,MaGhe,MaLichBay,TrangThai)" + " values(?,?,?,?)");
			stmt.setString(1, ttg.getMaChiTietTrangThai());
			stmt.setString(2, ttg.getMaGhe().getMaGhe());
			stmt.setString(3, ttg.getMaLichBay().getMaLichBay());
			stmt.setBoolean(4, ttg.isTrangThai());
			
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}


	public String in() {
		return dsTrangThaiGhe.toString();
	}
}
