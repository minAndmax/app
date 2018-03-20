package com.army.util;

import java.security.MessageDigest;

public class MD5Util {

	public static String getMD5(String s) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(s.getBytes("utf-8"));
			return toHex(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String toHex(byte[] bytes) {

		final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
		StringBuilder ret = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
			ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
		}
		return ret.toString();
	}
	
//	public static void main(String[] args) {
//		String app_key = "army201802271017";
//		
//		String app_secret = "1061326670";
//		
//		String app_key1 = "EC48CB312367E042756C51E59262DE44";
//		String app_secret1 = "33AC34DCD6CBBC0499E7916856D6EB30";
//		
//		try {
//			System.out.println(URLDecoder.decode(app_key1,"UTF-8"));
//			System.out.println(URLDecoder.decode(app_secret1,"UTF-8"));
//
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(getMD5(app_key));
//		System.out.println(getMD5(app_secret));
//	}

}
