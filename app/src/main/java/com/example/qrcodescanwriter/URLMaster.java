package com.example.qrcodescanwriter;

public class URLMaster {
    public static boolean checkUrl(String s) {
            return s != null && s.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    }
}
