package cn.met0.util;

/**
 * �ַ���������
 * @author �ƺ���
 *
 */
public class StrUtil {
	
	/**
	 * �ַ�����BCD��
	 * @param str
	 * @return
	 */
	public static String strTohexBCD(String str){
		
		StringBuilder sb = new StringBuilder();
		char[] arr = strtoHexLen(str).toCharArray();
		int len = str.length();
		
		for(int i=0;i < len ;i++){
			sb.append(arr[i]+""+arr[++i]);
		}
		
		return new String(sb);
	}
	
	
	/**
	 * ��λ  ���ַ����չ� len ���ȣ�
	 * 
	 * 
	 * @param hex 
	 * @param len
	 * @return
	 */
	public static String addHexLen(String hex,int len){
		hex = strtoHexLen(hex);
		String count = "";
		int num = len - hex.length();
		while(num > 0){
			count += 0;
			num--;
		}
		return count + hex;
	}
	
	/**
	 * 16�����ַ�������
	 * @param hexStr
	 * @return
	 */
	public static String strtoHexLen(String hexStr){
		if(hexStr.length()%2 == 0){
			return hexStr;
		}
		return "0"+hexStr;
	}
	
}
