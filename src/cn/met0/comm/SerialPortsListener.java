package cn.met0.comm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;

import org.apache.log4j.Logger;

public class SerialPortsListener implements SerialPortEventListener {
	private InputStream is;
	private OutputStream os;
	private SerialPort serial;
	private String response = null;
	private StringBuilder result;
	private boolean isAPDU = false; 

	static Logger log = Logger.getLogger(SerialPortsListener.class);

	public SerialPortsListener(SerialPort serial) throws IOException {
		this.serial = serial;
		this.is = serial.getInputStream();
		this.os = serial.getOutputStream();
	}

	public void setListener() throws TooManyListenersException {
		/*
		  this.serial.addEventListener(this);
		  this.serial.notifyOnDataAvailable(true);
		 */
	}
	
	
	public void write(byte[] data) throws InterruptedException {
		result = null;
		result = new StringBuilder();
		log.info("------------------------------------------------------------------");
		String command = new String(data);
		if("54".equals(command.subSequence(7, 9))){
			isAPDU = true;
		}else{
			isAPDU = false;
		}
		log.info("request: " + command);

		if ((data == null) || (this.os == null))
			return;
		try {
			this.os.write(data);
			this.os.flush();
			this.os.close();
		} catch (IOException e) {
			log.warn("数据写入失败，IO错误。");
		}


	}

	public String read(byte[] read) {
		
		
		try {
			Thread.sleep(250);
		} catch (InterruptedException e1) {
			log.warn(e1.getMessage()+"读取串口间隔出错！！");	
		}/**/

		
			try {
				while (this.is.available() > 0) {
				int i = this.is.read(read);
				String res = new String(read);
				result.append(new String(res));
			}
			} catch (IOException e) {
				log.warn(e.getMessage()+"读取串口输入流出错！！");			
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				log.warn(e.getMessage()+"关闭串口输入流出错！！");
			}
		}
		return new String(read);
	}

	public void serialEvent(SerialPortEvent arg0) {

		switch (arg0.getEventType()) {
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
			break;
		case 1:
			byte[] readBuffer = new byte[1000];
			try {
				while (this.is.available() > 0) {
					Thread.sleep(500);
					int i = this.is.read(readBuffer);
					String res = new String(readBuffer);
					System.out.println(res);
					result.append(new String(res));
				}

			} catch (IOException localIOException1) {
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void closeAll() {
		this.serial.close();
		log.info(this.serial.getName() + ":串口已关闭！！");
	}

	/**
	 * 获取串口数据
	 * @return
	 */
	public String getResponse() {
		
		
		
		String re=new String(result);
		log.info("response: "+re);
		String lenhex=null;
		int len=0;
		try{
			lenhex=(String) re.subSequence(3, 7);
			if(lenhex==null)return null;
			len = Integer.parseInt(lenhex, 16);
			if(len == re.length()-7) return null;
		}catch(Exception e){
			return null;
		}
		
		//返回的信息体
		String reqinfo=re.substring(9, len + 5);
		
		
		//如果是APDU指令，并且状态码不是 9000
		if (isAPDU){
			int reqinfoLen = reqinfo.length();
			//状态码
			String status = reqinfo.substring(reqinfoLen - 4, reqinfoLen);
			if( ! "9000".equals(status))
			return null;
		}else{
			return re;
		}
		
		log.info("response data: "+reqinfo);
		return reqinfo;
	}


}