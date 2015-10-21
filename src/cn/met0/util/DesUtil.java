package cn.met0.util;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DesUtil {

	/**
	 * DES����
	 * 
	 * @param HexString
	 *            �ַ�����16λ16�����ַ�����
	 * @param keyStr
	 *            ��Կ16��1
	 * @throws Exception
	 */
	public static String ENCRYPTMethod(String HexString, String keyStr)
			throws Exception {
		
		if(keyStr==null)
			keyStr="FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
		
		
		try {
			byte[] theKey = null;
			byte[] theMsg = null;
			theMsg = hexToBytes(HexString);
			theKey = hexToBytes(keyStr);
			KeySpec ks = new DESKeySpec(theKey);
			SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
			SecretKey ky = kf.generateSecret(ks);
			Cipher cf = Cipher.getInstance("DES/ECB/NoPadding");
			cf.init(Cipher.ENCRYPT_MODE, ky);
			byte[] theCph = cf.doFinal(theMsg);
			return bytesToHex(theCph);
			/*
			 * System.out.println("*************DES����****************");
			 * System.out.println("��Կ    : "+bytesToHex(theKey));
			 * System.out.println("�ַ���: "+bytesToHex(theMsg));
			 * System.out.println("���ܺ�: "+bytesToHex(theCph));
			 */
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * DES����
	 * 
	 * @param hexStr
	 *            16λʮ�������ַ���
	 * @param keyStr
	 *            ��Կ16��1
	 * @param modeStr
	 *            ����ģʽ:ECB
	 * @throws Exception
	 */
	public static void test2(String hexStr, String keyStr, String modeStr)
			throws Exception {

		String algorithm = modeStr;
		try {
			byte[] theKey = null;
			byte[] theMsg = null;
			theMsg = hexToBytes(hexStr);
			theKey = hexToBytes(keyStr);
			KeySpec ks = new DESKeySpec(theKey);
			SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
			SecretKey ky = kf.generateSecret(ks);
			Cipher cf = Cipher.getInstance(algorithm);
			cf.init(Cipher.DECRYPT_MODE, ky);
			byte[] theCph = cf.doFinal(theMsg);
			System.out.println("*************DES����****************");
			System.out.println("��Կ    : " + bytesToHex(theKey));
			System.out.println("�ַ���: " + bytesToHex(theMsg));
			System.out.println("���ܺ�: " + bytesToHex(theCph));

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

	}

	public static byte[] hexToBytes(String str) {
		if (str == null) {
			return null;
		} else if (str.length() < 2) {
			return null;
		} else {
			int len = str.length() / 2;
			byte[] buffer = new byte[len];
			for (int i = 0; i < len; i++) {
				buffer[i] = (byte) Integer.parseInt(
						str.substring(i * 2, i * 2 + 2), 16);
			}
			return buffer;
		}

	}

	public static String bytesToHex(byte[] data) {
		if (data == null) {
			return null;
		} else {
			int len = data.length;
			String str = "";
			for (int i = 0; i < len; i++) {
				if ((data[i] & 0xFF) < 16)
					str = str + "0"
							+ java.lang.Integer.toHexString(data[i] & 0xFF);
				else
					str = str + java.lang.Integer.toHexString(data[i] & 0xFF);
			}
			return str.toUpperCase();
		}
	}
}
