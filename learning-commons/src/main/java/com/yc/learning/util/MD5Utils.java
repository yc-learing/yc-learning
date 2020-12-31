package com.yc.learning.util;

import com.yc.learning.entity.Exercises;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

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
	
	public static void main(String[] args){
		//根据考卷eids获取所有试题
		List<Exercises> list=new ArrayList<>();
		Exercises e=null;
		for(int i=0;i<10;i++){
			e=new Exercises();
			e.setAnswer("A");
			list.add(e);
		}
		//[1.A,2.B,3.-1]
		String[] answer="1.A,2.B,3.C,4.A,5.A,6.-1,7.-1,8.-1,9.-1,10.-1".split(",");
		int len=list.size();
		int flag=0;
		for(int i=0;i<len;i++){
			String ans=list.get(i).getAnswer();
			String uans=answer[i].substring(answer[i].indexOf('.')+1);
			if(ans.equals(uans)){
				++flag;
			}
		}
		double grade=(flag*1.0)/(len*1.0)*100;
		grade= Double.parseDouble(String.format("%.2f", grade));
		System.out.println(grade);
	}
}