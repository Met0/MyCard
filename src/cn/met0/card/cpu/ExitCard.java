package cn.met0.card.cpu;


import java.util.HashMap;
import java.util.Map;

import cn.met0.comm.SerialPortsListener;
import cn.met0.comm.command.Command;
import cn.met0.util.DesUtil;

/**
 * �˿�
 * 
 * @author �ƺ���
 *
 */
public class ExitCard extends CpuCardManage {

	public ExitCard(SerialPortsListener sp) throws Exception {
		super(sp);
	}

	@Override
	public Map manage() throws Exception {
		super.inIt();
		String response = null;
		int responseLen = 0;
		Map result = new HashMap();

		// ȡ����������ⲿ��֤
		byte[] random = Command.getCommand("540084000008");
		sp.write(random);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("ȡ����������ⲿ��֤����");
		}

		// des����
		String des = null;
		responseLen = response.length();
		String desdata = response.substring(responseLen - 20,
				responseLen - 4);
		des = DesUtil.ENCRYPTMethod(desdata,
				"FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
		
		
		
		// �ⲿ��֤
		byte[] outCertificate = Command.getCommand("540082000008" + des);
		sp.write(outCertificate);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("�ⲿ��֤����");
		}

		// ����MF�ļ�
		byte[] clearMF = Command.getCommand("54800E000000");
		sp.write(clearMF);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("����MF�ļ�����");
		}

		return result;
	}

}
