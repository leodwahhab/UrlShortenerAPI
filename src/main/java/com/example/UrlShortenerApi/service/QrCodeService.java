package com.example.UrlShortenerApi.service;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface QrCodeService {
    String generateQrCode(String url) throws WriterException, IOException;
}
