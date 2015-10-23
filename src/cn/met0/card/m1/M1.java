package cn.met0.card.m1;

import cn.met0.card.Card;
import cn.met0.comm.SerialPortsListener;
import cn.met0.comm.command.Command;

/**
 * m1¿¨²Ù×÷
 * 
 * @author ãÆºÅÑô
 *
 */
public abstract class M1 implements Card {

	/**
	 * ´®¿Ú
	 */
	protected SerialPortsListener sp;

	/**
	 * ³õÊ¼»¯M1¿¨´®¿Ú
	 * 
	 * @param sp
	 */
	public M1(SerialPortsListener sp) {
		this.sp = sp;
	}

	/**
	 * ³õÊ¼»¯M1¿¨¹ÜÀí 1.Ñ°¿¨ 2.·À³åÍ» 3.Ñ¡Ôñ¿¨
	 */
	@Override
	public void inIt() throws Exception {

		String response = null;

		// Ñ°¿¨
		byte[] findCard = Command.getCommand("42");
		sp.write(findCard);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("Ñ°¿¨Ê§°Ü¡£");
		}

		// ·À³åÍ»
		byte[] powerOn = Command.getCommand("43");
		sp.write(powerOn);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("·À³åÍ»Ê§°Ü¡£");
		}

		// Ñ¡Ôñ¿¨
		byte[] selectCard = Command.getCommand("44");
		sp.write(selectCard);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("Ñ¡Ôñ¿¨Ê§°Ü¡£");
		}

	}

}
