package com.example.qrcodescanwriter;


import android.graphics.Bitmap;
import android.graphics.Color;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


public class QRCodeGenerator{
    public static Bitmap generate(String text) {
        Map<EncodeHintType,Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        QRCodeWriter qrCodeWriter = new QRCodeWriter();// QR-code generator object
        Bitmap bitmap = null;
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 500, 500, hints);
            bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.RGB_565);
            for (int x = 0; x < 500; ++x) {
                for (int y = 0; y < 500; ++y) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
        return bitmap;

    }
}
