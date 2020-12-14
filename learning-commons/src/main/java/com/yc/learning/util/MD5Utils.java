package com.yc.learning.util;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

	public static String stringToMD5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有这个md5算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		String s="BackModule_AdminService[adminDomain]=[AdminDomain(aid=null, aname=o, apwd=null, status=null)]";
		String s1 = s.split("=")[1].split("\\(")[0];
		s1=s1.substring(1,s1.length());
		//String c=Class.forName("com.yc.learning.domain."+s1).getSuperclass().getSimpleName();
		Class c=Class.forName("com.yc.learning.domain."+s1).getSuperclass();
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName());
		}
	}
}