package cn.met0.card.cpu;


import cn.met0.card.Card;
import cn.met0.comm.SerialPortsListener;
import cn.met0.comm.command.Command;

/**
 * cpuø®∆¨
 * @author „∆∫≈—Ù
 *
 */
public abstract class CpuCardManage implements Card {

	
	
	
	
	/**
	 * ¥Æø⁄
	 */
	protected SerialPortsListener sp; 
	
	
	/**
	 * ≥ı ºªØø®∆¨
	 * @param sp
	 * @throws Exception 
	 */
	public CpuCardManage(SerialPortsListener sp) throws Exception{
		this.sp = sp;
	}
	
	/**
	 * cpuø®∆¨≥ı ºªØ
	 */
	public void inIt() throws Exception {

		String response = null;
		
		// —∞ø®
		byte[] findCard = Command.createData("50");
		sp.write(findCard);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("—∞ø® ß∞‹°£");
		}
		
		

		// …œµÁ∏¥Œª
		byte[] powerOn = Command.createData("51");
		sp.write(powerOn);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("…œµÁ∏¥Œª ß∞‹°£");
		}

	}
		
	
}
