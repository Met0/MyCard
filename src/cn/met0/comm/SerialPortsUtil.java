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
   * ��ȡ��������
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
   * ��ȡ���д���
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
   * ��ȡ�����ڼ���
   * @param SerialPortName ��������
   * @param Name	
   * @param bt ������
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
      log.warn(e.getMessage() + "\t" + "��ȡ���ڳ���,���ܴ����������ڱ�PC��");
    } catch (PortInUseException e) {
      log.warn(e.getMessage() + "\t" + "�򿪴��ڳ���,δ֪ԭ��");
    } catch (IOException e) {
      log.warn(e.getMessage() + "\t" + "��ʼ�����ڳ���,IOԭ��");
    }

    log.info(SerialPortName + "�Ѵ򿪣�����ʼ����...");
    return spl;
  }
}