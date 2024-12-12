/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilies;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import javax.swing.*;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

/**
 *
 * @author ADMIN
 */
public class ExportPDF {
	public void exportToPDF(JFrame frame, String outputPath) {
        try {
            // Chụp giao diện JFrame thành BufferedImage
            BufferedImage image = new BufferedImage(
                frame.getWidth(),
                frame.getHeight(),
                BufferedImage.TYPE_INT_RGB
            );
            Graphics2D g2d = image.createGraphics();
            frame.paint(g2d);
            g2d.dispose();

            // Lưu ảnh tạm thời
            String tempImagePath = "temp_image.png";
            FileOutputStream fos = new FileOutputStream(tempImagePath);
            javax.imageio.ImageIO.write(image, "png", fos);
            fos.close();

            // Tạo file PDF và thêm ảnh
            PdfWriter writer = new PdfWriter(outputPath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            ImageData imageData = ImageDataFactory.create(tempImagePath);
            Image pdfImage = new Image(imageData);
            document.add(pdfImage);

            // Đóng tài nguyên
            document.close();
            System.out.println("Xuất file PDF thành công: " + outputPath);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi trong quá trình xuất file PDF.");
        }
    }
}
