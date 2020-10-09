import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import com.itextpdf.text.Document;
                 import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class PDF {

    public static void main(String[] args) throws IOException, DocumentException {

        // 1.新建document对象
        Document document = new Document();
//        String filename = "D:/iTextDemo/ChineseDisplay.pdf";
//        File file = new File(filename);
//        file.getParentFile().mkdirs();
        // 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
        // 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Administrator.DESKTOP-61JNMB9\\Desktop\\xgd.pdf"));
        // 中文字体
        BaseFont fontChinese = BaseFont.createFont("C:/Windows/Fonts/STSONG.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//        Document document = new Document();
//        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
        document.add(new Paragraph("你好，世界！", new Font(fontChinese, 12)));
        document.close();
        // 3.打开文档
        document.open();

        // 4.添加一个内容段落
        document.add(new Paragraph("Hello World!"));
        document.close();
        // 5.关闭文档
        writer.close();


    }

}
































