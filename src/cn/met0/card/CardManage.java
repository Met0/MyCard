package cn.met0.card;


import java.util.Map;

import cn.met0.comm.SerialPortsListener;
import cn.met0.comm.command.Command;

public abstract class CardManage extends Card {

	
	
	
	
	/**
	 * ����
	 */
	protected SerialPortsListener sp; 
	
	
	/**
	 * ��ʼ����Ƭ����
	 * @param sp
	 * @throws Exception 
	 */
	public CardManage(SerialPortsListener sp) throws Exception{
		this.sp = sp;
		inIt();
	}
		
	/**
	 * ��Ƭ����ǰ�ĳ�ʼ��
	 * @throws Exception 
	 */
	protected void inIt() throws Exception {

		String response = null;
		
		// Ѱ��
		byte[] findCard = Command.createData("50");
		sp.write(findCard);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("Ѱ��ʧ�ܡ�");
		}
		
		

		// �ϵ縴λ
		byte[] powerOn = Command.createData("51");
		sp.write(powerOn);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("�ϵ縴λʧ�ܡ�");
		}

	}

	/**
	 * ��Ƭ����
	 * @return
	 */
	public abstract Map Manage() throws Exception;
	
}
