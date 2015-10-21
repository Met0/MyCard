package cn.met0.comm.command.cardFileManage;

import cn.met0.comm.command.Command;



/**
 * 
 * 卡片文件管理命令
 * 
 * 此类用于二进制文件的 创建 读取 和 写入
 * 
 * 读取命令码 5400B0
 * 
 * 写入命令码 5400D6
 * 
 * 
 *  @author 闫号阳
 *
 * 
 * */
public class CardFile {


	
	//选中文件
	private static final String select = "5400A4";
	//卡片读取
	private static final String read = "5400B0";
	//卡片写入
	private static final String write = "5400D6";
	//创建文件
	private static final String create = "5480E0";
	
	
	
	/**
	 * 选择文件  当前目录文件，或子目录
	 */
	public static final String SELECT_FILE = "00";
	
	/**
	 * 选择当前目录，或同级目录
	 */
	public static final String SELECT_CATALOG = "04";
	
	
	/**
	 * 卡片文件写入
	 * @param hexFile 文件标示符 
	 * @param info  写入内容
	 * @return
	 */
	public static byte[] write(int file,String info) {
		 
		String commandHexStr = write + // 写入指令 
				fileMark(file) + //写入的文件标示符 
				"00" +
				infoLength(info) + //写入内容的长度
				info;//数据
		
		return Command.getCommand(commandHexStr);
	}
	
	
	/**
	 * 卡片文件读取
	 * @param file
	 * @param len 16进制 (00 为读取全部数据)
	 * @return
	 */
	public static byte[] read(int file,String len) {
		
		String commandHexStr = read + //读取指令
				fileMark(file) + //文件标示
				"00" + 
				len ; //读取长度		
		return  Command.getCommand(commandHexStr);
	}
	
	
	/**
	 * 获取信息长度
	 * @param info 信息
	 * @return
	 */
	private static String infoLength(String info){
		String lenHex = Integer.toHexString(info.length() / 2);
		return Command.Complement(lenHex,2);
	}
	
	
	/**
	 * 根据文件编号 获取文件标示
	 * @param file 文件编号
	 * @return
	 */
	private static String fileMark(int file){
		file = file + 128;
		return Command.Complement(Integer.toHexString(file), 2);
	}
	
	
	
	/**
	 * 创建文件
	 * @param file 文件标识 (hex) 2字节
	 * @param info 文件名称 (hex) 
	 * @return
	 */
	public static byte[] CreateFile(String file,String info){
		String len = infoLength(info);
		return Command.getCommand(create + file + len + info);
	}
	
	
	/**
	 * 选中文件
	 * @param file  选中的文件标示符
	 * @param selecttype 文件类型
	 * @return
	 */
	public static byte[] selectCatalog(String file,String selecttype){
		return Command.getCommand(select + selecttype + "0002"+ file + "00");
	}
	
	
	
	
	
}
