package cn.met0.card.m1;

import java.util.HashMap;
import java.util.Map;

import cn.met0.comm.SerialPortsListener;
import cn.met0.comm.command.Command;

public class M1KeyInstall extends M1 {

	public M1KeyInstall(SerialPortsListener sp) {
		super(sp);
	}

	@Override
	public Map manage() throws Exception {
		super.inIt();
		
		insert1Key();
		//authentication0();// 认证0扇区ab秘钥
		write1();
		read1();

		//write0();
		
	//	read0();
		
		return new HashMap();
	}

	
	
	
	
	/**
	 * 安装1扇区的秘钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map insert1Key() throws Exception {

		String response = null;

		byte[] insertA = Command.getCommand("450001FFFFFFFFFFFF");
		sp.write(insertA);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("扇区A安装密钥失败。");
		}

		byte[] insertB = Command.getCommand("450401FFFFFFFFFFFF");
		sp.write(insertB);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("扇区B安装密钥失败。");
		}

		return null;
	}

	
	/**
	 * 写入1扇区
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map write1() throws Exception {

		String response = null;

		byte[] write00 = Command
				.getCommand("490402020202020202020202020202020202");
		sp.write(write00);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("disk0写入失败。");
		}

		byte[] write01 = Command
				.getCommand("490502020202020202020202020202020202");
		sp.write(write01);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("disk1写入失败。");
		}

		byte[] write02 = Command
				.getCommand("490602020202020202020202020202020202");
		sp.write(write02);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("disk2写入失败。");
		}

		return null;
	}
	
	
	/**
	 * 读取1扇区
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map read1() throws Exception {
		String response = null;

		byte[] read0 = Command.getCommand("4804");
		sp.write(read0);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("数据块读取失败。。");
		}

		byte[] read1 = Command.getCommand("4805");
		sp.write(read1);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("数据块读取失败。");
		}

		byte[] read2 = Command.getCommand("4806");
		sp.write(read2);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("数据块读取失败。");
		}

		byte[] read3 = Command.getCommand("4807");
		sp.write(read3);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("数据块读取失败。");
		}

		return null;

	}

	
	
	//-------------扇区0的-----------------
	/**
	 * 安装0扇区的秘钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map insert0Key() throws Exception {

		String response = null;

		byte[] insertA = Command.getCommand("450000FFFFFFFFFFFF");
		sp.write(insertA);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("扇区A安装密钥失败。");
		}

		byte[] insertB = Command.getCommand("450400FFFFFFFFFFFF");
		sp.write(insertB);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("扇区B安装密钥失败。");
		}

		return null;
	}

	/**
	 * 认证0扇区
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map authentication0() throws Exception {
		String response = null;

		byte[] authenticationA = Command.getCommand("460000");
		sp.write(authenticationA);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("扇区A认证密钥失败。");
		}

		byte[] authenticationB = Command.getCommand("460400");
		sp.write(authenticationB);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("扇区B认证密钥失败。");
		}
		return null;
	}

	/**
	 * 写入0扇区
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map write0() throws Exception {

		String response = null;

		byte[] write00 = Command
				.getCommand("4901FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
		sp.write(write00);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("disk0写入失败。");
		}

		byte[] write01 = Command
				.getCommand("4902FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
		sp.write(write01);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("disk1写入失败。");
		}



		return null;
	}

	/**
	 * 读取0扇区
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map read0() throws Exception {
		String response = null;

		byte[] read0 = Command.getCommand("4800");
		sp.write(read0);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("数据块读取失败。。");
		}

		byte[] read1 = Command.getCommand("4801");
		sp.write(read1);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("数据块读取失败。");
		}

		byte[] read2 = Command.getCommand("4802");
		sp.write(read2);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("数据块读取失败。");
		}

		byte[] read3 = Command.getCommand("4803");
		sp.write(read3);
		sp.read(new byte[50]);
		response = sp.getResponse();
		if (response == null) {
			throw new Exception("数据块读取失败。");
		}

		return null;

	}

	
}
