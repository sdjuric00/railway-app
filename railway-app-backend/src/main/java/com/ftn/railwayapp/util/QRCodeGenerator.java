package com.ftn.railwayapp.util;

import com.ftn.railwayapp.exception.QRCodeException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.FileOutputStream;
import java.io.IOException;

import static com.ftn.railwayapp.util.Constants.*;

public class QRCodeGenerator {

    public static void createQrCode(final String qrUrl, final String id) throws QRCodeException {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrUrl, BarcodeFormat.QR_CODE, QR_CODE_WIDTH, QR_CODE_HEIGHT);
            FileOutputStream outputStream = new FileOutputStream(QR_CODE_PACKAGE_PATH + id + ".png");
            MatrixToImageWriter.writeToStream(bitMatrix,"png", outputStream);
        } catch (WriterException | IOException e) {
            throw new QRCodeException("Error happened during QR code creation.");
        }
    }

}
