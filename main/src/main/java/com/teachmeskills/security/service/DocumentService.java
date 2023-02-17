package com.teachmeskills.security.service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.teachmeskills.security.client.DocumentClient;
import org.example.common.dto.document.CashVoucherDto;
import lombok.RequiredArgsConstructor;
import org.example.common.dto.document.CashReceiptDto;
import org.example.common.dto.document.CreateCashReceiptDto;
import org.example.common.dto.document.CreateCashVoucherDto;
import org.example.common.dto.document.CreateInvoiceDto;
import org.example.common.dto.document.InvoiceDto;
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

    public InvoiceDto createInvoice(final CreateInvoiceDto createInvoiceDto) {
        return documentClient.createInvoice(createInvoiceDto);
    }

    public void generatePDF() throws IOException {

        PdfDocument pdfDoc = new PdfDocument(
                new PdfWriter("C:/Users/natas/Documents/diploma/main/src/main/resources/templates/index-to-pdf.pdf"));

        pdfDoc.setDefaultPageSize(new PageSize(774, 1095));

        HtmlConverter.convertToPdf(
                new FileInputStream("C:/Users/natas/Documents/diploma/main/src/main/resources/templates/index-to-pdf.html"),
                pdfDoc);

    }
}
