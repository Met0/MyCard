package cn.met0.card;


import java.util.Map;

import cn.met0.comm.SerialPortsListener;
import cn.met0.comm.command.Command;

public abstract class CardManage extends Card {

	
	
	
	
	/**
	 * 串口
	 */
	protected SerialPortsListener sp; 
	
	
	/**
	 * 初始化卡片管理
	 * @param sp
	 * @throws Exception 
	 */
	public CardManage(SerialPortsListener sp) throws Exception{
		this.sp = sp;
		inIt();
	}
		
	/**
	 * 卡片操作前的初始化
	 * @throws Exception 
	 */
	protected void inIt() throws Exception {

		String response = null;
		
		// 寻卡
		byte[] findCard = Command.createData("50");
		sp.write(findCard);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("寻卡失败。");
		}
		
		

		// 上电复位
		byte[] powerOn = Command.createData("51");
		sp.write(powerOn);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("上电复位失败。");
		}

	}

	/**
	 * 卡片管理
	 * @return
	 */
	public abstract Map Manage() throws Exception;
	
}
