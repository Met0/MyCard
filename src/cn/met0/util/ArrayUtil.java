package cn.met0.util;



public class ArrayUtil<T> {
	
	
	

	
	/**
	 * �����������
	 * @param arr ��Ҫ��ӵ�����
	 * @param arr2 ��ӵ�����
	 * @return
	 */
	public static Object[] arrayAdd(Object[] arr,Object...arr2){
		
		int len=arr.length+arr2.length;
		
		Object[] newArr=new Object[len];
		
		for(int i=0;i<arr.length;i++){
			newArr[i]=arr[i];
		}
		int j=0;
		for(int i=arr.length;i<len;i++){
			newArr[i]=arr2[j++];
		}
		
		return newArr;
	}
	
	
	/**
	 * ������ַ���
	 * @param objects
	 * @return
	 */
	public static String toString(Object...objects){
		StringBuilder data=new StringBuilder();
		
		for(Object str:objects){
			data.append(str);
		}
		return new String(data);
	}
	

	
	
	
}

