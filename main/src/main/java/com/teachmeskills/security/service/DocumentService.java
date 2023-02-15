package com.teachmeskills.security.service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.teachmeskills.security.client.DocumentClient;
import com.teachmeskills.security.dto.CashReceiptDto;
import com.teachmeskills.security.dto.CashVoucherDto;
import com.teachmeskills.security.dto.CreateCashReceiptDto;
import com.teachmeskills.security.dto.CreateCashVoucherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentClient documentClient;

    public CashReceiptDto createOrder(final CreateCashReceiptDto createCashReceiptDto) {
        return documentClient.createCashReceipt(createCashReceiptDto);
    }

    public CashVoucherDto createVoucher(final CreateCashVoucherDto createCashVoucherDto) {
        return documentClient.createCashVoucher(createCashVoucherDto);
    }

    public void generatePDF() throws IOException {

//        PdfDocument pdfDoc = new PdfDocument(new PdfWriter("C:/Users/natas/Documents/diploma/main/src/main/resources/templates/cash.html"));
//
//        pdfDoc.setDefaultPageSize(new PageSize(1500, 842));
//        HtmlConverter.convertToPdf(new FileInputStream("index-to-pdf.pdf"), pdfDoc);

        HtmlConverter.convertToPdf(new FileInputStream("C:/Users/natas/Documents/diploma/main/src/main/resources/templates/cash.html"),
                new FileOutputStream("C:/Users/natas/Documents/diploma/main/src/main/resources/templates/index-to-pdf.pdf"));

//        File htmlSource = new File("http://localhost:9090/cash-receipt/cash.html");
//        File pdfDest = new File("output.pdf");
//        // pdfHTML specific code
//        InputStream stream = new URL("http://localhost:9090/cash-receipt/cash.html").openStream();
//        ConverterProperties converterProperties = new ConverterProperties();
//        HtmlConverter.convertToPdf(stream,
//                new FileOutputStream(pdfDest), converterProperties);
    }
}
