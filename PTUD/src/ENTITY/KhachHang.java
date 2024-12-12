/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITY;

import java.util.Objects;

/**
 *
 * @author ADMIN
 */
public class KhachHang {
	public String getMaKH() {
		return maKH;
	}
	
	public void setMaKH(String maKH) {
		
		this.maKH = maKH;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		if (hoTen == null || hoTen.trim().isEmpty() || hoTen.length() > 100) {
		    throw new IllegalArgumentException("Tên khách hàng không hợp lệ!");
		}
		this.hoTen = hoTen;
	}
	public String getSoDienThoai() {
		return SoDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		if (soDienThoai == null || soDienThoai.trim().isEmpty()){
		    throw new IllegalArgumentException("Só Điện Thoại không hợp lệ!");
		}
		SoDienThoai = soDienThoai;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if (email == null || email.trim().isEmpty()){
		    throw new IllegalArgumentException("email không hợp lệ!");
		}
		this.email = email;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getCccd() {
		return cccd;
	}
	public void setCccd(String cccd) {
		if (cccd == null || cccd.trim().isEmpty()){
		    throw new IllegalArgumentException("Só Căn Cước không hợp lệ!");
		}
		this.cccd = cccd;
	}
	public KhachHang(String maKH, String hoTen, String soDienThoai, String email, String gioiTinh, String cccd) {
		super();
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.SoDienThoai = soDienThoai;
		this.email = email;
		this.gioiTinh = gioiTinh;
		this.cccd = cccd;
		
	}
	private String maKH;
	private String hoTen;
	private String SoDienThoai;
	private String email;
	private String gioiTinh;
	private String cccd;

	@Override
	public int hashCode() {
		return Objects.hash(SoDienThoai, cccd, email, gioiTinh, hoTen, maKH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(SoDienThoai, other.SoDienThoai) && Objects.equals(cccd, other.cccd)
				&& Objects.equals(email, other.email) && Objects.equals(gioiTinh, other.gioiTinh)
				&& Objects.equals(hoTen, other.hoTen) && Objects.equals(maKH, other.maKH);
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", hoTen=" + hoTen + ", SoDienThoai=" + SoDienThoai + ", email=" + email
				+ ", gioiTinh=" + gioiTinh + ", cccd=" + cccd + "]";
	}
	}

