package cn.met0.card.cpu;


import cn.met0.card.Card;
import cn.met0.comm.SerialPortsListener;
import cn.met0.comm.command.Command;

/**
 * cpu��Ƭ
 * @author �ƺ���
 *
 */
public abstract class CpuCardManage implements Card {

	
	
	
	
	/**
	 * ����
	 */
	protected SerialPortsListener sp; 
	
	
	/**
	 * ��ʼ����Ƭ
	 * @param sp
	 * @throws Exception 
	 */
	public CpuCardManage(SerialPortsListener sp) throws Exception{
		this.sp = sp;
	}
	
	/**
	 * cpu��Ƭ��ʼ��
	 */
	public void inIt() throws Exception {

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
		
	
}
