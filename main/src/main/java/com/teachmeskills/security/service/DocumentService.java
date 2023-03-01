package com.teachmeskills.security.service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.teachmeskills.security.client.CashReceiptClient;
import com.teachmeskills.security.client.CashVoucherClient;
import com.teachmeskills.security.client.InvoiceClient;
import org.example.common.dto.document.CashVoucherDto;
import lombok.RequiredArgsConstructor;
import org.example.common.dto.document.CashReceiptDto;
import org.example.common.dto.document.CreateCashReceiptDto;
import org.example.common.dto.document.CreateCashVoucherDto;
import org.example.common.dto.document.CreateInvoiceDto;
import org.example.common.dto.document.InvoiceDto;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final CashReceiptClient cashReceiptClient;
    private final CashVoucherClient cashVoucherClient;
    private final InvoiceClient invoiceClient;

    public CashReceiptDto createOrder(final CreateCashReceiptDto createCashReceiptDto) {
        return cashReceiptClient.createCashReceipt(createCashReceiptDto);
    }

    public CashVoucherDto createVoucher(final CreateCashVoucherDto createCashVoucherDto) {
        return cashVoucherClient.createCashVoucher(createCashVoucherDto);
    }

    public InvoiceDto createInvoice(final CreateInvoiceDto createInvoiceDto) {
        return invoiceClient.createInvoice(createInvoiceDto);
    }

    public void generatePDF(String pdfFileName, String htmlFileName) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(pdfFileName));
        pdfDoc.setDefaultPageSize(new PageSize(774, 1095));
        HtmlConverter.convertToPdf(new FileInputStream(htmlFileName), pdfDoc);
        File directory = new File(pdfFileName);
        Runtime.getRuntime().exec("explorer.exe /select," + directory);
    }
}
