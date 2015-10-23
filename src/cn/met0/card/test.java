package cn.met0.card;


import org.apache.log4j.PropertyConfigurator;

import cn.met0.card.m1.Manage;
import cn.met0.comm.SerialPortsListener;
import cn.met0.comm.SerialPortsUtil;


public class test {
	
	static{
		PropertyConfigurator.configure("log4j.properties");
	}
	
	
	public static void main(String[] args) throws Exception {
		
		
		
		
		SerialPortsListener sp = SerialPortsUtil
				.StartSerialPortListener("COM7", "11", 9600);
		
		
		
		Manage m = new Manage(sp);
		m.writeKey("01", "04", "FFFFFFFFFFFF");
		
		m.writeData("05", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		
		m.readData("05");
		
		
		/*M1 m1KeyInstall = new M1KeyInstall(sp);
		m1KeyInstall.manage();
		*/
		
		
		
		/*CpuCardManage card = new UpRecord(sp);
		
		card.manage();*/
		
		
	}
}
