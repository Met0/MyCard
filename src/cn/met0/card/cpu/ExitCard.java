package cn.met0.card.cpu;


import java.util.HashMap;
import java.util.Map;

import cn.met0.comm.SerialPortsListener;
import cn.met0.comm.command.Command;
import cn.met0.util.DesUtil;

/**
 * 退卡
 * 
 * @author 闫号阳
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

		// 取随机数进行外部认证
		byte[] random = Command.getCommand("540084000008");
		sp.write(random);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("取随机数进行外部认证出错");
		}

		// des加密
		String des = null;
		responseLen = response.length();
		String desdata = response.substring(responseLen - 20,
				responseLen - 4);
		des = DesUtil.ENCRYPTMethod(desdata,
				"FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
		
		
		
		// 外部认证
		byte[] outCertificate = Command.getCommand("540082000008" + des);
		sp.write(outCertificate);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("外部认证出错");
		}

		// 擦除MF文件
		byte[] clearMF = Command.getCommand("54800E000000");
		sp.write(clearMF);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("擦除MF文件出错");
		}

		return result;
	}

}
