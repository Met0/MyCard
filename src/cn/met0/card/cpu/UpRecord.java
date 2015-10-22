package cn.met0.card.cpu;


import java.util.HashMap;
import java.util.Map;

import cn.met0.comm.SerialPortsListener;
import cn.met0.comm.command.Command;
import cn.met0.comm.command.cardFileManage.CardFile;
import cn.met0.util.MacKey;

/**
 * 写入记录
 * 
 * @author 闫号阳
 *
 */
public class UpRecord extends CpuCardManage {

	public UpRecord(SerialPortsListener sp) throws Exception {
		super(sp);
	}

	/**
	 * 写入租车记录
	 */
	@Override
	public Map manage() throws Exception {
		super.inIt();
		String response = null;
		int responseLen = 0;
		Map result = new HashMap();

		// 选择应用文件
		byte[] selectFile = CardFile.selectCatalog("3F01", "00");
		sp.write(selectFile);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("选择应用文件出错!!");
		}

		// ------------获取随机数、加密mac、写入租车记录------------
		String random = null;//随机数
		String key = "07452A90556D722D405F65C896D7791E";//维护秘钥
		String data = "011E1234560007452A90011E1234777777452A90011E1234560007452A900000";//写入的数据
		String adpu = "04DC01BC24" + data;//adpu指令

		// 获取随机数
		byte[] randomapdu = Command.getCommand("540084000004");
		sp.write(randomapdu);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("写入租车信息失败");
		}
		responseLen = response.length();

		// 随机数
		random = response.substring(0, responseLen - 4);
		// mac加密
		String mac = new MacKey().Str3MAC(key, random + "00000000", adpu);

		//写入数据
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
