package com.teachmeskills.security.service;

import com.itextpdf.html2pdf.ConverterProperties;
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

import java.io.ByteArrayOutputStream;

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

    public byte[] generatePDF(String stringFilledHtml) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfWriter pdfWriter = new PdfWriter(outputStream);
        PdfDocument pdfDoc = new PdfDocument(pdfWriter);
        pdfDoc.setDefaultPageSize(new PageSize(774, 1095));

        HtmlConverter.convertToPdf(stringFilledHtml, pdfDoc, new ConverterProperties());

        return outputStream.toByteArray();
    }
}
