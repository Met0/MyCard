package cn.met0.comm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;

import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;

import org.apache.log4j.Logger;

public class SerialPortsUtil
{
  static Logger log = Logger.getLogger(SerialPortsUtil.class);

  /**
   * 获取串口名称
   * @return
   */
  public static String[] getSerialPortsNameList()
  {
    List comms = getSerialPortsList();
    String[] commsarr = new String[comms.size()];
    /*int i = 0;
    for (CommPortIdentifier comm : comms) {
      commsarr[(i++)] = comm.getName();
    }*/
    for(int i=0;i<comms.size();i++){
    	commsarr[i]=((CommPortIdentifier)comms.get(i)).getName();
    }

    return commsarr;
  }

  
  
  /**
   * 获取所有串口
   * @return
   */
  public static List<CommPortIdentifier> getSerialPortsList()
  {
    Enumeration ee = CommPortIdentifier.getPortIdentifiers();
    List comms = new ArrayList();
    while (ee.hasMoreElements()) {
      CommPortIdentifier comm = (CommPortIdentifier)ee.nextElement();
      if (comm.getName().indexOf("COM") != -1)
        comms.add(comm);
    }
    return comms;
  }

  
  
  /**
   * 获取个串口监听
   * @param SerialPortName 串口名称
   * @param Name	
   * @param bt 波特率
   * @return
   * @throws TooManyListenersException
   */
  public static SerialPortsListener StartSerialPortListener(String SerialPortName, String Name, int bt)
    throws TooManyListenersException
  {
    SerialPort serial = null;
    SerialPortsListener spl = null;
    try {
      CommPortIdentifier comm = CommPortIdentifier.getPortIdentifier(SerialPortName);
      serial = (SerialPort)comm.open(Name, bt);
      spl = new SerialPortsListener(serial);
      spl.setListener();
    }
    catch (NoSuchPortException e) {
      log.warn(e.getMessage() + "\t" + "获取串口出错,可能串口名不存在本PC。");
    } catch (PortInUseException e) {
      log.warn(e.getMessage() + "\t" + "打开串口出错,未知原因。");
    } catch (IOException e) {
      log.warn(e.getMessage() + "\t" + "初始化串口出错,IO原因。");
    }

    log.info(SerialPortName + "已打开，并开始监听...");
    return spl;
  }
}