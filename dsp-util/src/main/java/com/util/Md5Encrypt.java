
package com.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
* 功能：支付宝MD5加密处理核心文件，不需要修改
* 版本：3.1
* 修改日期：2010-11-01
* 说明：
* 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
* 该代码仅供学习和研究支付宝接口使用，只是提供一个
* */

public class Md5Encrypt {
	/**
	 * Used building output as Hex
	 */
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 对字符串进行MD5加密
	 * 
	 * @param text
	 *            明文
	 * 
	 * @return 密文
	 */
	public static String standmd5(String text) {
		MessageDigest msgDigest = null;

		try {
			msgDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(
					"System doesn't support MD5 algorithm.");
		}

		try {
			msgDigest.update(text.getBytes("gbk"));    //注意改接口是按照指定编码形式签名
 
		} catch (UnsupportedEncodingException e) {

			throw new IllegalStateException(
					"System doesn't support your  EncodingException.");

		}

		byte[] bytes = msgDigest.digest();

		String md5Str = new String(encodeHex(bytes));

		return md5Str;
	}

	public static char[] encodeHex(byte[] data) {

		int l = data.length;

		char[] out = new char[l << 1];

		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS[0x0F & data[i]];
		}

		return out;
	}
	
	
	public static String md5(String msg)
	{
		StringBuilder sb=new StringBuilder();
		String re=standmd5(msg);
		
		char[] all=re.toCharArray();
		for(int i=0;i<all.length;i++)
		{
			sb.append(((char)(all[i]^4)));
			//all[i]=(char)(all[i]^0xF0);
			//sb.append((char)(byte)(all[i]^0xF2));
		}
		
		return sb.toString();
		
	}
	
	
	public static void main(String[] args) {
/*		String str=Md5Encrypt.md5("admin");
		System.out.println(str);
		System.out.println(str.length());*/
	/*	Admin admin=new Admin();
		admin.setEmail("admin@qq.com");
		admin.setUpwd(Md5Encrypt.md5("admin"));
		admin.setUpur("10000");
		try {
			Db.add(admin);
			System.out.println("OK");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	   System.out.println(Md5Encrypt.md5("admin"));
	}


}