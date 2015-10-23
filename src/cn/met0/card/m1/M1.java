package cn.met0.card.m1;

import cn.met0.card.Card;
import cn.met0.comm.SerialPortsListener;
import cn.met0.comm.command.Command;

/**
 * m1������
 * 
 * @author �ƺ���
 *
 */
public abstract class M1 implements Card {

	/**
	 * ����
	 */
	protected SerialPortsListener sp;

	/**
	 * ��ʼ��M1������
	 * 
	 * @param sp
	 */
	public M1(SerialPortsListener sp) {
		this.sp = sp;
	}

	/**
	 * ��ʼ��M1������ 1.Ѱ�� 2.����ͻ 3.ѡ��
	 */
	@Override
	public void inIt() throws Exception {

		String response = null;

		// Ѱ��
		byte[] findCard = Command.getCommand("42");
		sp.write(findCard);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("Ѱ��ʧ�ܡ�");
		}

		// ����ͻ
		byte[] powerOn = Command.getCommand("43");
		sp.write(powerOn);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("����ͻʧ�ܡ�");
		}

		// ѡ��
		byte[] selectCard = Command.getCommand("44");
		sp.write(selectCard);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("ѡ��ʧ�ܡ�");
		}

	}

}
