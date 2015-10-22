package cn.met0.card;


import org.apache.log4j.PropertyConfigurator;

import cn.met0.card.m1.M1CardManager;
import cn.met0.card.m1.M1KeyInstall;
import cn.met0.comm.SerialPortsListener;
import cn.met0.comm.SerialPortsUtil;


public class test {
	
	static{
		PropertyConfigurator.configure("log4j.properties");
	}
	
	
	public static void main(String[] args) throws Exception {
		
		SerialPortsListener sp = SerialPortsUtil
				.StartSerialPortListener("COM7", "11", 9600);
		
		M1CardManager m1KeyInstall = new M1KeyInstall(sp);
		m1KeyInstall.manage();
		/*CpuCardManage card = new UpRecord(sp);
		
		card.manage();*/
		
		
	}
}
