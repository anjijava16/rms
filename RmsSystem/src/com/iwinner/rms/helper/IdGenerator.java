package com.iwinner.rms.helper;

import java.util.Random;

/**
 * The class IdGenerator explain helper class
 * @author FeelSafe
 * @version 1.x
 * @see  com.iwinner.feelsafe.helper.IdGenerator
 * @since 28 Sept,2014
 *
 */

public class IdGenerator {

	static Random random=new Random();
	static int strLen[]={1,2,4,'m','n','o','a','b','c','d','+','-','*','&'};
	public static String getId(){
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<strLen.length;i++){
			sb.append(strLen[random.nextInt(strLen.length)]);
		}
		return sb.toString();
	}
	
	
	public  static String randId()
	{
		Random random=new Random();
		int i=random.nextInt();
		System.out.println(i);
		if(i<0)
		{
			i=-(i);
		}
		if(i<1000)
			i=i+1000;
		while(i>9999)
		{
			i=i/10;
		}
		return i+"";
	}
public static void main(String[] args) {
	randId();
}
}
