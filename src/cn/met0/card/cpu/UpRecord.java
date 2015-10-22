package cn.met0.card.cpu;


import java.util.HashMap;
import java.util.Map;

import cn.met0.comm.SerialPortsListener;
import cn.met0.comm.command.Command;
import cn.met0.comm.command.cardFileManage.CardFile;
import cn.met0.util.MacKey;

/**
 * д���¼
 * 
 * @author �ƺ���
 *
 */
public class UpRecord extends CpuCardManage {

	public UpRecord(SerialPortsListener sp) throws Exception {
		super(sp);
	}

	/**
	 * д���⳵��¼
	 */
	@Override
	public Map manage() throws Exception {
		super.inIt();
		String response = null;
		int responseLen = 0;
		Map result = new HashMap();

		// ѡ��Ӧ���ļ�
		byte[] selectFile = CardFile.selectCatalog("3F01", "00");
		sp.write(selectFile);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("ѡ��Ӧ���ļ�����!!");
		}

		// ------------��ȡ�����������mac��д���⳵��¼------------
		String random = null;//�����
		String key = "07452A90556D722D405F65C896D7791E";//ά����Կ
		String data = "011E1234560007452A90011E1234777777452A90011E1234560007452A900000";//д�������
		String adpu = "04DC01BC24" + data;//adpuָ��

		// ��ȡ�����
		byte[] randomapdu = Command.getCommand("540084000004");
		sp.write(randomapdu);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("д���⳵��Ϣʧ��");
		}
		responseLen = response.length();

		// �����
		random = response.substring(0, responseLen - 4);
		// mac����
		String mac = new MacKey().Str3MAC(key, random + "00000000", adpu);

		//д������
		byte[] b = Command.getCommand("54" + adpu + mac);
		sp.write(b);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception();
		}

		return null;
	}

}
