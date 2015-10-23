package cn.met0.card.m1;

import java.util.HashMap;
import java.util.Map;

import cn.met0.card.Card;
import cn.met0.comm.SerialPortsListener;
import cn.met0.comm.command.Command;

public class Manage extends M1 implements Card {

	public Manage(SerialPortsListener sp) {
		super(sp);
	}

	@Override
	public Map manage() throws Exception {
		
		return null;
	}
	
	/**
	 * д��key
	 * @param disk ���� 0x00 ~ 0x0F
	 * @param keyType ��Կ���� 0x00 keyA | 0x04 Key B
	 * @param key ��Կ 16 bytes len
	 * @return
	 * @throws Exception 
	 */
	public Map writeKey(String disk,String keyType,String key) throws Exception{
		
		inIt();
		
		String response = null;

		// д��key
		byte[] writeKey = Command.getCommand("45" + keyType + disk + key);
		sp.write(writeKey);
		sp.read(new byte[50]);
		response = sp.getResponse();
		String str = response.substring(7, 9);
		if ( ! "00".equals(str)) {
			throw new Exception("д��keyʧ�ܡ�");
		}
		
		
		return new HashMap();
	}
	
	/**
	 * д������
	 * @param block  ��� 0x01 ~ 3F
	 * @param data  36 bytes len
	 * @return
	 * @throws Exception
	 */
	public Map writeData(String block,String data) throws Exception{
		
		String response = null;

		// д������
		byte[] writeKey = Command.getCommand("49" + block + data);
		sp.write(writeKey);
		sp.read(new byte[50]);
		response = sp.getResponse();
		String str = response.substring(7, 9);
		if ( ! "00".equals(str)) {
			throw new Exception("д������ʧ�ܡ�");
		}
		
		
		return new HashMap();
	}
	
	/**
	 * ��ȡ����
	 * @param block ��� 0x00 ~ 0x3F
	 * @return
	 * @throws Exception
	 */
	public Map readData(String block) throws Exception{
		
		String response = null;
		String data = null;
		Map result = new HashMap();
		// д������
		byte[] writeKey = Command.getCommand("48" + block);
		sp.write(writeKey);
		sp.read(new byte[50]);
		response = sp.getResponse();
		String str = response.substring(7, 9);
		data = response.substring(9 ,response.length() - 2);
		if ( ! "00".equals(str)) {
			throw new Exception("��ȡ����ʧ�ܡ�");
		}
		result.put("1", data);
		
		
		return result;
	}
	

	
	
}
