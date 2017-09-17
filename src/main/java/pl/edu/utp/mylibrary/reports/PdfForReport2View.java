/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.utp.mylibrary.reports;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.edu.utp.mylibrary.model.Album;

/**
 *
 * @author nowakowska joanna
 */
public class PdfForReport2View extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Album> albums = (List<Album>) model.get("albums");

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(95);
        table.setWidths(new int[]{2, 4, 4, 4, 4});

        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, "Cp1250", BaseFont.EMBEDDED);
        Font font = FontFactory.getFont(FontFactory.HELVETICA, "Cp1250", BaseFont.EMBEDDED);

        PdfPCell hcell;
        hcell = new PdfPCell(new Phrase("L.p.", headFont));
        hcell.setPadding(10);
        hcell.setBorderColor(BaseColor.LIGHT_GRAY);
        hcell.setBorderColorBottom(BaseColor.DARK_GRAY);
        hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Nazwa", headFont));
        hcell.setPadding(10);
        hcell.setBorderColor(BaseColor.LIGHT_GRAY);
        hcell.setBorderColorBottom(BaseColor.DARK_GRAY);
        hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Wykonawca", headFont));
        hcell.setPadding(10);
        hcell.setBorderColor(BaseColor.LIGHT_GRAY);
        hcell.setBorderColorBottom(BaseColor.DARK_GRAY);
        hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Rok", headFont));
        hcell.setPadding(10);
        hcell.setBorderColor(BaseColor.LIGHT_GRAY);
        hcell.setBorderColorBottom(BaseColor.DARK_GRAY);
        hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Gatunek", headFont));
        hcell.setPadding(10);
        hcell.setBorderColor(BaseColor.LIGHT_GRAY);
        hcell.setBorderColorBottom(BaseColor.DARK_GRAY);
        hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        int i = 1;
        for (Album album : albums) {

            PdfPCell cell;

            cell = new PdfPCell(new Phrase(String.valueOf(i)));
            cell.setPadding(7);
            cell.setBorderColor(BaseColor.WHITE);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(album.getTitle(), font));
            cell.setPadding(7);
            cell.setBorderColor(BaseColor.WHITE);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(album.getArtist(), font));
            cell.setPadding(7);
            cell.setBorderColor(BaseColor.WHITE);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(album.getYear(), font));
            cell.setPadding(7);
            cell.setBorderColor(BaseColor.WHITE);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(album.getGenre(), font));
            cell.setPadding(7);
            cell.setBorderColor(BaseColor.WHITE);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            i++;
        }
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, "Cp1250", BaseFont.EMBEDDED, 25);
        
        document.addCreationDate();
        document.add(table);

    }

}
