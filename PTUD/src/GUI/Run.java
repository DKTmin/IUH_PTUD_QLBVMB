/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import DAO.DAO_ChiTietHoaDon;
import DAO.DAO_ChiTietVe;
import DAO.DAO_Ghe;
import DAO.DAO_HangBay;
import DAO.DAO_KhachHang;
import DAO.DAO_LichBay;
import DAO.DAO_LoaiTaiKhoan;
import DAO.DAO_NhanVien;
import DAO.DAO_VeMayBay;
import ENTITY.ChiTietVe;
import ENTITY.KhachHang;
import ENTITY.LichBay;
import ENTITY.LoaiTaiKhoan;
import ENTITY.NhanVien;
import ENTITY.TrangThaiGhe;
import ENTITY.VeMayBay;

import utilies.*;

/**
 *
 * @author ADMIN
 */
public class Run {
	//list 
	private static ArrayList<ChiTietVe> listChiTiet = new ArrayList<ChiTietVe>();  
	
	private static DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
	private static DAO_ChiTietVe dao_ChiTietVe = new DAO_ChiTietVe(); 
	

	private static NhanVien nv = dao_NhanVien.getOneObjNhanVien("NV001");

	public static void main(String[] args) {
//		listChiTiet.add(dao_ChiTietVe.getOneObjChiTietVe("TTG001")); 
//		listChiTiet.add(dao_ChiTietVe.getOneObjChiTietVe("TTG0033"));
//		listChiTiet.add(dao_ChiTietVe.getOneObjChiTietVe("TTG003"));
//		listChiTiet.add(dao_ChiTietVe.getOneObjChiTietVe("TTG004"));
//		listChiTiet.add(dao_ChiTietVe.getOneObjChiTietVe("TTG005"));
		
		
//        GUI_HOME_QuanLi a = new GUI_HOME_QuanLi(nv);
//        a.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        a.setVisible(true);
//        a.setUndecorated(true);  

		GUI_HOME_NhanVien b = new GUI_HOME_NhanVien(nv);
		b.setExtendedState(JFrame.MAXIMIZED_BOTH);
		b.setVisible(true);
		
//		GUI_InVe gui_InVe = new GUI_InVe(listChiTiet, "VE001");
//		gui_InVe.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		gui_InVe.setVisible(true);
//		ExportPDF inve = new ExportPDF(); 
//		inve.exportToPDF(gui_InVe, "E:\\output_gui_in_ve.pdf");
//		gui_InVe.setVisible(false);

//        GUI_HOME_QuanLy  frame = new GUI_HOME_QuanLy();
//        // Đặt trạng thái undecorated trước khi làm JFrame hiển thị
//        frame.setUndecorated(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        // Sau đó mới làm JFrame hiển thị
//        frame.setVisible(true);

//        GUI_DangNhap a = new GUI_DangNhap(); 
//        a.setVisible(true);
//        a.setResizable(false);
//        a.setLocationRelativeTo(null);

//    	DAO_LoaiTaiKhoan dao_ltk = new DAO_LoaiTaiKhoan(); 
//    	dao_ltk.getalltbLoaiTaiKhoan(); 
//    	System.out.println(dao_ltk.in());

//    	DAO_LichBay dao_lb = new DAO_LichBay(); 
//    	dao_lb.getalltbLichBay(); 
//    	System.out.println(dao_lb.in());
//    	LichBay lb = dao_lb.getOneObjLichBay("LB001"); 
//    	System.out.println(lb.toString());

//    	DAO_VeMayBay dao_vmb = new DAO_VeMayBay(); 
//    	dao_vmb.getalltbVeMayBay(); 
//    	System.out.println(dao_vmb.in());

//    	DAO_HangBay dao_hb = new DAO_HangBay();  
//    	dao_hb.getalltbHangBay(); 
//    	System.out.println(dao_hb.in());

//    	DAO_Ghe dao_ghe = new DAO_Ghe();   
//    	dao_ghe.getalltbGhe(); 
//    	System.out.println(dao_ghe.in());

//    	DAO_KhachHang dao_kh = new DAO_KhachHang();   
//    	KhachHang kh = dao_kh.getOneObjKhachHangTheoSoDienThoai("0912345678"); 
//    	System.out.println(kh.toString());

//        DAO_ChiTietHoaDon dao_cthd = new DAO_ChiTietHoaDon(); 
//        dao_cthd.getalltbChiTietHoaDon(); 
//        System.out.println(dao_cthd.in());

	}
}
