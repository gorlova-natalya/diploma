package com.teachmeskills.security.service;

import com.lowagie.text.DocumentException;
import com.teachmeskills.security.client.DocumentClient;
import com.teachmeskills.security.dto.CashReceiptDto;
import com.teachmeskills.security.dto.CashVoucherDto;
import com.teachmeskills.security.dto.CreateCashReceiptDto;
import com.teachmeskills.security.dto.CreateCashVoucherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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

    public void generatePDF(String inputHtmlPath, String outputPdfPath) {
        try {
//            String url = new File(inputHtmlPath).toURI().toURL().toString();
//            System.out.println("URL: " + url);

            OutputStream out = new FileOutputStream(outputPdfPath);

            ITextRenderer renderer = new ITextRenderer();

            renderer.setDocument(inputHtmlPath);
            renderer.layout();
            renderer.createPDF(out);

            out.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
