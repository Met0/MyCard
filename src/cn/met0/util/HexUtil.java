package cn.met0.util;

/**
 * 16���ƹ�����
 * 
 * @author �ƺ���
 *
 */
public class HexUtil {

/*	
	*//**
	 * 16�����ַ���ת�ֽ�����
	 * @param hexStr �ַ��� 16 ����
	 * @return
	 *//*
	public byte[] hexStrToBytes(String hexStr){
		hexStr = toHexStr(hexStr);
		for
	}
	*/
	
	
	

	/**/
	
	
	
	/**
	 * 16�������λ����
	 * @return
	 */
	private String toHexStr(String hexStr){
		if(hexStr.length()%2 == 0){
			return hexStr;
		}
		return "0"+hexStr;
	}
	
	
	
	
}
