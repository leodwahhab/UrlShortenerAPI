package com.example.UrlShortenerApi.service.impl;

import com.example.UrlShortenerApi.service.QrCodeService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class QrCodeServiceImpl implements QrCodeService {
    public String generateQrCode(String url) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 500, 500);

        var byteArray = new ByteArrayOutputStream();

        MatrixToImageWriter.writeToStream(matrix, "jpg", byteArray);

        byte[] bytes = byteArray.toByteArray();

        Base64.Encoder encoder = Base64.getEncoder();

        return "data:image/jpg;base64," + encoder.encodeToString(bytes);
    }
}
