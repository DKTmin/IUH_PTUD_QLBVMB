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
import ENTITY.LichBay;
import ENTITY.VeMayBay;

public class DAO_VeMayBay {
	// list 
	private ArrayList<VeMayBay> dsVeMayBay;

	// Dao
	private DAO_HangBay dao_hb = new DAO_HangBay();
	private DAO_LichBay dao_lb = new DAO_LichBay();
	private DAO_HangVe dao_hv = new DAO_HangVe();

	//////////////////////////////////////////////////// HÀM SỬ LÝ
	// Trả về hết danh sách
	public ArrayList<VeMayBay> getalltbVeMayBay() {
		dsVeMayBay = new ArrayList<VeMayBay>();
		try {
			KetNoiDB.getInstance();
			Connection con = KetNoiDB.getConnection();
			String sql = "Select * from VeMayBay";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maVe = rs.getString("MaVe");
				HangBay hangBay = dao_hb.getOneObjHangBay(rs.getString("MaHangBay"));
				HangVe hangVe = dao_hv.getOneObjHangVe(rs.getString("MaHangVe"));
				LichBay lichBay = dao_lb.getOneObjLichBay(rs.getString("MaLichBay"));
				double gia = rs.getDouble("Gia");

				VeMayBay vmb = new VeMayBay(maVe, hangBay, hangVe, lichBay, gia);
				dsVeMayBay.add(vmb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsVeMayBay;
	}

	// Trả về một đối tượng theo mã
	public VeMayBay getOneObjVeMayBay(String maObj) {
		VeMayBay veMayBay = null;
		try {
			KetNoiDB.getInstance();
			Connection con = KetNoiDB.getConnection();
			String sql = "Select * from VeMayBay where MaVe = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maObj);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String maVe = rs.getString("MaVe");
				HangBay hangBay = dao_hb.getOneObjHangBay(rs.getString("MaHangBay"));
				HangVe hangVe = dao_hv.getOneObjHangVe(rs.getString("MaHangVe"));
				LichBay lichBay = dao_lb.getOneObjLichBay(rs.getString("MaLichBay"));
				double gia = rs.getDouble("Gia");

				veMayBay = new VeMayBay(maVe, hangBay, hangVe, lichBay, gia);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return veMayBay;
	}

	public String in() {
		return dsVeMayBay.toString();
	}
}
