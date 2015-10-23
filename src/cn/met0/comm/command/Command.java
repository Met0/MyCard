package cn.met0.comm.command;

import org.apache.log4j.Logger;

import cn.met0.util.ArrayUtil;

public class Command {

	static Logger log = Logger.getLogger(Command.class);
	private static final String ZERO = "0";

	/**
	 * 创建命令
	 * 
	 * @param command
	 *            命令码 (HEX)
	 * @param info
	 *            命令参数 (HEX)
	 * @return
	 */
	public static byte[] createData(String... data) {
		/**
		 * xor (异或值) command + param +xor(信息体) 长度 (command + param +xor)
		 */
		String xorValue = data.length == 1 ? data[0] : xorByHex(data);
		String strData = ArrayUtil.toString(ArrayUtil.arrayAdd(data, xorValue));
		String len = Complement(Integer.toHexString(strData.length()), 4);
		return ("JSC" + len + strData).getBytes();
	}

	/*
	 * public static byte[] selectFile(){
	 * 
	 * }
	 */

	/**
	 * 字符串解析命令byte[]
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] getCommand(String str) {

		str = str.replaceAll(" ", "");

		String[] commandarr = getCommandArray(str);
		return createData(commandarr);
	}

	/**
	 * 对16 进制的数据进行异或
	 * 
	 * @param strings
	 *            数据
	 * @return
	 */
	private static String xorByHex(String... strings) {

		int data[] = new int[strings.length];

		for (int i = 0; i < strings.length; i++) {
			try {
				data[i] = Integer.parseInt(strings[i], 16);
			} catch (Exception e) {
				data[i] = 00;
			}
		}

		return xor(data).toUpperCase();
	}

	/**
	 * 异或运算
	 * 
	 * @param data
	 *            异或的数据
	 * @return
	 */
	private static String xor(int[] data) {
		int tmp = data[0];

		for (int i = 1; i < data.length; i++) {
			tmp = tmp ^ data[i];
		}
		return Complement(Integer.toHexString(tmp), 2);
	}

	/**
	 * 获取数据长度并且进行补码
	 * 
	 * @param data
	 *            数据
	 * @return
	 */
	public static String datalength(String data) {
		int len = data.length();
		return Complement(len + "", 4).toLowerCase();
	}

	/**
	 * 字符串16进制补码
	 * 
	 * @param hex
	 *            需要补码的16进制的字符串
	 * @param x
	 *            需要补码的个数
	 * @return
	 */
	public static String Complement(String hex, int x) {
		String count = "";
		int num = x - hex.length();
		while (num > 0) {
			count += ZERO;
			num--;
		}
		return count + hex;
	}

	/**
	 * 字符串变数组
	 * 
	 * @param str
	 * @return
	 */
	private static String[] getCommandArray(String str) {

		String[] command = new String[str.length() / 2];
		int j = 0;
		for (int i = 2; i <= str.length();) {
			command[j++] = str.substring(i - 2, i);
			i += 2;

		}
		return command;
	}

}
