package ENTITY;

import java.util.Objects;

public class HoaDon {
	private String MaHoaDon; 
	private KhachHang MaKhachHang; 
	private int SoLuong; 
	private double Gia;
	private boolean TrangThai;
	
	
	public HoaDon(String maHoaDon) {
		super();
		MaHoaDon = maHoaDon;
	}


	public HoaDon(String maHoaDon, KhachHang maKhachHang, int soLuong, double gia, boolean trangThai) {
		super();
		MaHoaDon = maHoaDon;
		MaKhachHang = maKhachHang;
		SoLuong = soLuong;
		Gia = gia;
		TrangThai = trangThai;
	}
	
	
	public String getMaHoaDon() {
		return MaHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		MaHoaDon = maHoaDon;
	}
	public KhachHang getMaKhachHang() {
		return MaKhachHang;
	}
	public void setMaKhachHang(KhachHang maKhachHang) {
		MaKhachHang = maKhachHang;
	}
	public int getSoLuong() {
		return SoLuong;
	}
	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}
	public double getGia() {
		return Gia;
	}
	public void setGia(double gia) {
		Gia = gia;
	}
	public boolean isTrangThai() {
		return TrangThai;
	}
	public void setTrangThai(boolean trangThai) {
		TrangThai = trangThai;
	}


	@Override
	public int hashCode() {
		return Objects.hash(MaHoaDon);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(MaHoaDon, other.MaHoaDon);
	}


	@Override
	public String toString() {
		return "HoaDon [MaHoaDon=" + MaHoaDon + ", MaKhachHang=" + MaKhachHang + ", SoLuong=" + SoLuong + ", Gia=" + Gia
				+ ", TrangThai=" + TrangThai + "]";
	} 
	
	
	
	
	
	
}
