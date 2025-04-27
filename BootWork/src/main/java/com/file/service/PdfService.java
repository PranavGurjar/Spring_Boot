package com.file.service;


import com.file.controller.PdfController;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    private Logger logger = LoggerFactory.getLogger(PdfController.class);

    public ByteArrayInputStream createPdf(){
        logger.info("Create Pdf Started : ");

        String title = "Welcome to Pranav Website";
        String content = "I am a Pranav Mahajan, From Dasnur. I have done my graduation from SSBT Jalgaon. Now work as a Software developer";

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Document document = new Document();

        PdfWriter.getInstance(document, out);

        HeaderFooter footer = new HeaderFooter( true, new Phrase("PRM"));
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setBorderWidthBottom(0);
        document.setFooter(footer);


        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
        Paragraph titlePara = new Paragraph(title, titleFont);
        titlePara.setAlignment(Element.ALIGN_CENTER);

        document.add(titlePara);

        Font paraFont = FontFactory.getFont(FontFactory.HELVETICA, 18);
        Paragraph paragraph = new Paragraph(content, paraFont);
        document.add(new Chunk("Wow this text is adding after creating paragraph"));
        document.add(paragraph);

        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
