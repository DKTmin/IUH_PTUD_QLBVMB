/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ENTITY.ChiTietHoaDon;
import ENTITY.HoaDon;

/**
 *
 * @author dktrin
 */
public class ExcelFileExportForHoaDon {
	private ChiTietHoaDon chiTietHoaDon;
	private Workbook workbook;

	// dao
	DAO_ChiTietHoaDon dao_ChiTietHoaDon = new DAO_ChiTietHoaDon();

	public ExcelFileExportForHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExcelFileExportForHoaDon(ChiTietHoaDon hoaDon, double tienKhachDua, double tienTraKhach) {
		super();

		this.chiTietHoaDon = hoaDon;
		DecimalFormat df = new DecimalFormat("#,###");
		try {
			FileInputStream file = new FileInputStream(
					new File(getClass().getResource("/static/HoaDon.xlsx").getPath()));
			workbook = new XSSFWorkbook(file);
			Sheet currentSheet = workbook.getSheetAt(0);

			Cell cellMaHoadon = currentSheet.getRow(5).getCell(0);
			cellMaHoadon.setCellValue("Số HD: " + chiTietHoaDon.getMaHoaDon().getMaHoaDon()
					+ ((tienKhachDua >= 0) ? "" : " (Hoá đơn in lại)"));

			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy");
			Cell cellNgayGio = currentSheet.getRow(6).getCell(0);
			cellNgayGio.setCellValue("Ngày giờ: " + chiTietHoaDon.getNgayLapHoaDon());

			Cell cellKhachHang = currentSheet.getRow(7).getCell(0);
			cellKhachHang.setCellValue(
					"Khách hàng: " + (chiTietHoaDon.getMaHoaDon().getMaKhachHang().getTenKhachHang()));

			Cell cellNhanVien = currentSheet.getRow(8).getCell(0);
			cellNhanVien.setCellValue("NV bán hàng: " + chiTietHoaDon.getMaNhanVien().getTenNhanVien());

			////////////////////////////////////////////
			HoaDon tempCTHD = chiTietHoaDon.getMaHoaDon(); 
			int row1 = 11 + (2);
			int row2 = 12 + (2);

			try {
				CellRangeAddress cra = new CellRangeAddress(row1, row1, 1, 3);
				currentSheet.addMergedRegion(cra);

				CellRangeAddress cra1 = new CellRangeAddress(row1, row2, 0, 0);
				currentSheet.addMergedRegion(cra1);
			} catch (Exception e) {

			}

			// Create font
			Font font = workbook.createFont();
			font.setFontHeightInPoints((short) 20);
			font.setFontName("Times New Roman");

			CellStyle cellStyleSTT = workbook.createCellStyle();
			cellStyleSTT.setFont(font);

			cellStyleSTT.setAlignment(HorizontalAlignment.CENTER);
			cellStyleSTT.setVerticalAlignment(VerticalAlignment.CENTER);

			CellStyle cellStyleTen = workbook.createCellStyle();
			cellStyleTen.setFont(font);

			CellStyle cellStyleDG = workbook.createCellStyle();
			cellStyleDG.setFont(font);

			CellStyle cellStyleSL = workbook.createCellStyle();
			cellStyleSL.setFont(font);
			cellStyleSL.setAlignment(HorizontalAlignment.CENTER);

			CellStyle cellStyleTT = workbook.createCellStyle();
			cellStyleTT.setFont(font);
			cellStyleTT.setAlignment(HorizontalAlignment.RIGHT);

			Row newRow1 = currentSheet.createRow(row1);
			Cell cellSTT = newRow1.createCell(0);
			cellSTT.setCellValue(1);
			
			Cell cellTenSP = newRow1.createCell(1);
			cellTenSP.setCellValue(chiTietHoaDon.getMaVe().getMaHangBay().getTenHangBay());

			Row newRow2 = currentSheet.createRow(row2);
			Cell cellDonGia = newRow2.createCell(1);
			cellDonGia.setCellValue(df.format(chiTietHoaDon.getMaVe().getGia()));

			Cell cellSoLuong = newRow2.createCell(2);
			cellSoLuong.setCellValue(tempCTHD.getSoLuong());

			Cell cellTongTien = newRow2.createCell(3);
			cellTongTien.setCellValue(df.format(chiTietHoaDon.getTongTien()));

			currentSheet.getRow(row1).setHeightInPoints((float) 28.8);

			currentSheet.getRow(row2).setHeightInPoints((float) 28.8);

			currentSheet.getRow(row1).getCell(0).setCellStyle(cellStyleSTT);
			currentSheet.getRow(row1).getCell(1).setCellStyle(cellStyleTen);
			currentSheet.getRow(row2).getCell(3).setCellStyle(cellStyleTT);
			currentSheet.getRow(row2).getCell(2).setCellStyle(cellStyleSL);
			currentSheet.getRow(row2).getCell(1).setCellStyle(cellStyleDG);

			/////////////////////////////////////////

			Font font1 = workbook.createFont();
			font1.setFontHeightInPoints((short) 20);
			font1.setFontName("Times New Roman");
			font1.setBold(true);

			CellStyle cellStyleGeneral = workbook.createCellStyle();
			cellStyleGeneral.setFont(font1);
			cellStyleGeneral.setAlignment(HorizontalAlignment.RIGHT);

			CellStyle cellStyleHeading = workbook.createCellStyle();
			cellStyleHeading.setFont(font1);
			cellStyleHeading.setAlignment(HorizontalAlignment.RIGHT);
			cellStyleHeading.setBorderTop(BorderStyle.MEDIUM);

			Font font11 = workbook.createFont();
			font11.setFontHeightInPoints((short) 20);
			font11.setFontName("Times New Roman");
			font11.setItalic(true);
			CellStyle cellStyleI = workbook.createCellStyle();
			cellStyleI.setFont(font11);
			cellStyleI.setAlignment(HorizontalAlignment.CENTER);

			// Row tạm tính
			int row = 3 + ((dao_ChiTietHoaDon.getalltbChiTietHoaDon().size() - 1) * 2);
////			Row rowTamTinh = currentSheet.createRow(row);
////
////			rowTamTinh.createCell(0).setCellValue("");
////			rowTamTinh.getCell(0).setCellStyle(cellStyleHeading);
////
////			rowTamTinh.createCell(1).setCellValue("");
////			rowTamTinh.getCell(1).setCellStyle(cellStyleHeading);
////
////			rowTamTinh.createCell(2).setCellValue("Tạm tính:");
////			rowTamTinh.getCell(2).setCellStyle(cellStyleHeading);
////
////			rowTamTinh.createCell(3).setCellValue(df.format(hoaDon.getTongTien()));
////			rowTamTinh.getCell(3).setCellStyle(cellStyleHeading);
//
			// Row giảm giá
//			++row;
//			Row rowGiamGia = currentSheet.createRow(row);
//			rowGiamGia.createCell(2).setCellValue("Giảm giá:");
//			rowGiamGia.getCell(2).setCellStyle(cellStyleGeneral);
//
//			rowGiamGia.createCell(3).setCellValue(df.format(hoaDon.getGiaKhuyenMai()));
//			rowGiamGia.getCell(3).setCellStyle(cellStyleGeneral);

			// Row tổng cộng
//			++row;
			Row rowTC = currentSheet.createRow(row);
			rowTC.createCell(2).setCellValue("Tổng cộng:");
			rowTC.getCell(2).setCellStyle(cellStyleGeneral);

			rowTC.createCell(3).setCellValue(df.format(chiTietHoaDon.getTongTien()));
			rowTC.getCell(3).setCellStyle(cellStyleGeneral);

			// Row tiền đưa khách
			if (tienKhachDua >= 0) {
				++row;
				Row rowTDK = currentSheet.createRow(row);
				rowTDK.createCell(2).setCellValue("Tiền khách đưa:");
				rowTDK.getCell(2).setCellStyle(cellStyleGeneral);

				rowTDK.createCell(3).setCellValue(df.format(tienKhachDua));
				rowTDK.getCell(3).setCellStyle(cellStyleGeneral);
			}

			// Row tiền trả lại khách
			if (tienTraKhach >= 0) {
				++row;
				Row rowTTLK = currentSheet.createRow(row);
				rowTTLK.createCell(2).setCellValue("Tiền trả lại khách:");
				rowTTLK.getCell(2).setCellStyle(cellStyleGeneral);

				rowTTLK.createCell(3).setCellValue(df.format(tienTraKhach));
				rowTTLK.getCell(3).setCellStyle(cellStyleGeneral);
			}

			// Row Cảm ơn quý khách
			row = row + 2;
			Row rowXCO = currentSheet.createRow(row);
			CellRangeAddress craXCO = new CellRangeAddress(row, row, 0, 3);

			currentSheet.addMergedRegion(craXCO);
			rowXCO.createCell(0).setCellValue("FlySafe xin cảm ơn! Hẹn gặp lại quý khách!");
			rowXCO.getCell(0).setCellStyle(cellStyleI);

			FileOutputStream fos = new FileOutputStream("tempFile.xlsx");
			workbook.write(fos);

			workbook.setPrintArea(0, // Sheet index
					0, // Start column
					4, // End column
					0, // Start row
					row // End row
			);

			/*
			 * Desktop desktop = Desktop.getDesktop(); desktop.print(new
			 * File("tempFile.xlsx"));
			 */

			PrintExcel.printer("tempFile.xlsx");

			workbook.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		ConnectDB.getInstance().connect();
//		HoaDon_BUS hd_bus = new HoaDon_BUS();
//		ChiTietHoaDon_BUS cthd_bus = new ChiTietHoaDon_BUS();
//		HoaDon hdTemp = hd_bus.getHoaDonByID(new HoaDon("HD031223001"));
//		hdTemp.setListChiTietHoaDon(cthd_bus.getAllChiTietCuaMotHoaDon("HD031223001"));
//		
//		new ExcelFileExportForHoaDon(hdTemp, 0, 0);
//	}

}
