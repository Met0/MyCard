package cn.met0.util;

/**
 * 16进制工具类
 * 
 * @author 闫号阳
 *
 */
public class HexUtil {

/*	
	*//**
	 * 16进制字符串转字节数组
	 * @param hexStr 字符串 16 进制
	 * @return
	 *//*
	public byte[] hexStrToBytes(String hexStr){
		hexStr = toHexStr(hexStr);
		for
	}
	*/
	
	
	

	/**/
	
	
	
	/**
	 * 16进制最高位补码
	 * @return
	 */
	private String toHexStr(String hexStr){
		if(hexStr.length()%2 == 0){
			return hexStr;
		}
		return "0"+hexStr;
	}
	
	
	
	
}
