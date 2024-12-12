package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Connect.KetNoiDB;
import ENTITY.Ghe;

public class DAO_Ghe {
	private ArrayList<Ghe> dsGhe;

	// Trả về hết một danh sách hãng bay
	public ArrayList<Ghe> getalltbGhe() {
		dsGhe = new ArrayList<Ghe>();
		try {
			KetNoiDB.getInstance();
			Connection con = KetNoiDB.getConnection();
			String sql = "Select * from Ghe";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {

				String maGhe = rs.getString("MaGhe");
				String tenGhe = rs.getString("TenGhe");

				Ghe g = new Ghe(maGhe, tenGhe);
				dsGhe.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsGhe;
	}
	
	// Trả về một đối tượng theo mã
		public Ghe getOneObjGhe(String maObj) {
			Ghe ghe = null;
			try {
				KetNoiDB.getInstance();
				Connection con = KetNoiDB.getConnection();
				String sql = "Select * from Ghe where MaGhe = ?";
				PreparedStatement preparedStatement = con.prepareStatement(sql);
				preparedStatement.setString(1, maObj);
				ResultSet rs = preparedStatement.executeQuery();
				if (rs.next()) {
					String maLichBay = rs.getString("MaGhe");
					String noiDi = rs.getString("TenGhe");
					
					ghe = new Ghe(maLichBay, noiDi);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ghe;
		}

	public String in() {
		String in = null;
		for (Ghe ghe : dsGhe) {
			in = in + ghe.toString() + "\n";
		}
		return in; // Trả về chuỗi kết quả

	}
}
