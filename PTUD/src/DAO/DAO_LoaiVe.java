package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Connect.KetNoiDB;
import ENTITY.HangBay;
import ENTITY.HangVe;
import ENTITY.KhachHang;
import ENTITY.LichBay;
import ENTITY.LoaiVe;
import ENTITY.VeMayBay;

public class DAO_LoaiVe {
	private ArrayList<LoaiVe> dsLoaiVe;

	//////////////////////////////////////// HÀM
	// Trả về list Lịch Bay
	public ArrayList<LoaiVe> getalltbLoaiVe() {
		dsLoaiVe = new ArrayList<LoaiVe>();
		try {
			KetNoiDB.getInstance();
			Connection con = KetNoiDB.getConnection();
			String sql = "SELECT * FROM LoaiVe";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				String maLoaiVe = rs.getString("MaLoaiVe");
				String tenLoaiVe = rs.getString("TenLoaiVe");
				

				LoaiVe lv = new LoaiVe(maLoaiVe, tenLoaiVe);
				dsLoaiVe.add(lv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLoaiVe;
	}
	
	// Trả về một đối tượng theo mã
		public LoaiVe getOneObjLoaiVe(String maObj) {
			LoaiVe loaiVe = null;
			try {
				KetNoiDB.getInstance();
				Connection con = KetNoiDB.getConnection();
				String sql = "Select * from LoaiVe where MaLoaiVe = ?";
				PreparedStatement preparedStatement = con.prepareStatement(sql);
				preparedStatement.setString(1, maObj);
				ResultSet rs = preparedStatement.executeQuery();
				if (rs.next()) {
					String maLoaiVe = rs.getString("MaLoaiVe");
					String tenLoaiVe = rs.getString("TenLoaiVe");
					

					loaiVe = new LoaiVe(maLoaiVe, tenLoaiVe);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return loaiVe;
		}

		public String in() {
			return dsLoaiVe.toString();
		}
}
