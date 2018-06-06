package pl.kriscool.transport.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import pl.kriscool.transport.domain.Order;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfGenerator {

    public void PdfOrder(Order order) throws IOException, DocumentException {
        String dest = "C:\\Users\\kriscool\\Desktop\\Transport\\kapj2018-transport\\order-{id}.pdf";
        File file = new File(dest.replace("{id}", String.valueOf(order.getId())));
        file.createNewFile();
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file, false));
        document.open();
        Font chapterFont = FontFactory.getFont(FontFactory.TIMES_ITALIC, 24, Font.BOLD);
        Font paragraphFont = FontFactory.getFont(FontFactory.TIMES_ITALIC, 12, Font.NORMAL);
        Chunk chunk = new Chunk("Order number" + String.valueOf(order.getId()), chapterFont);
        Chapter chapter = new Chapter(new Paragraph(chunk), 1);
  
        chapter.add(new Paragraph("---------------------", paragraphFont));
        chapter.add(new Paragraph("Order Description", paragraphFont));
        chapter.add(new Paragraph(order.getDescription()));
        chapter.add(new Paragraph("---------------------", paragraphFont));
        chapter.add(new Paragraph("Order cars", paragraphFont));
        chapter.add(new Paragraph(order.getCars().iterator().next().getMarka() + " " +order.getCars().iterator().next().getTablica()));
        chapter.add(new Paragraph("---------------------", paragraphFont));
        chapter.add(new Paragraph("Order customer", paragraphFont));
        chapter.add(new Paragraph(order.getUser().getFirstname() + order.getUser().getLasttname()));
        document.add(chapter);
        document.close();
    }

}