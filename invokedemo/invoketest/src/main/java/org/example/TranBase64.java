package org.example;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class TranBase64 {

    public static void main(String[] args) throws UnsupportedEncodingException {

        String fileName = "4月跃跃欲试也月匜月修理费明细.jpg";

        final Base64.Decoder decoder = Base64.getDecoder();
        final Base64.Encoder encoder = Base64.getEncoder();
        final String text = "字串文字";
        final byte[] textByte = fileName.getBytes("UTF-8");
        //編碼
        final String encodedText = encoder.encodeToString(textByte).replace("/","_");
        System.out.println(encodedText);

    }

}
