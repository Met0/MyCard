package cn.met0.comm.command.cardFileManage;

import cn.met0.comm.command.Command;



/**
 * 
 * ��Ƭ�ļ���������
 * 
 * �������ڶ������ļ��� ���� ��ȡ �� д��
 * 
 * ��ȡ������ 5400B0
 * 
 * д�������� 5400D6
 * 
 * 
 *  @author �ƺ���
 *
 * 
 * */
public class CardFile {


	
	//ѡ���ļ�
	private static final String select = "5400A4";
	//��Ƭ��ȡ
	private static final String read = "5400B0";
	//��Ƭд��
	private static final String write = "5400D6";
	//�����ļ�
	private static final String create = "5480E0";
	
	
	
	/**
	 * ѡ���ļ�  ��ǰĿ¼�ļ�������Ŀ¼
	 */
	public static final String SELECT_FILE = "00";
	
	/**
	 * ѡ��ǰĿ¼����ͬ��Ŀ¼
	 */
	public static final String SELECT_CATALOG = "04";
	
	
	/**
	 * ��Ƭ�ļ�д��
	 * @param hexFile �ļ���ʾ�� 
	 * @param info  д������
	 * @return
	 */
	public static byte[] write(int file,String info) {
		 
		String commandHexStr = write + // д��ָ�� 
				fileMark(file) + //д����ļ���ʾ�� 
				"00" +
				infoLength(info) + //д�����ݵĳ���
				info;//����
		
		return Command.getCommand(commandHexStr);
	}
	
	
	/**
	 * ��Ƭ�ļ���ȡ
	 * @param file
	 * @param len 16���� (00 Ϊ��ȡȫ������)
	 * @return
	 */
	public static byte[] read(int file,String len) {
		
		String commandHexStr = read + //��ȡָ��
				fileMark(file) + //�ļ���ʾ
				"00" + 
				len ; //��ȡ����		
		return  Command.getCommand(commandHexStr);
	}
	
	
	/**
	 * ��ȡ��Ϣ����
	 * @param info ��Ϣ
	 * @return
	 */
	private static String infoLength(String info){
		String lenHex = Integer.toHexString(info.length() / 2);
		return Command.Complement(lenHex,2);
	}
	
	
	/**
	 * �����ļ���� ��ȡ�ļ���ʾ
	 * @param file �ļ����
	 * @return
	 */
	private static String fileMark(int file){
		file = file + 128;
		return Command.Complement(Integer.toHexString(file), 2);
	}
	
	
	
	/**
	 * �����ļ�
	 * @param file �ļ���ʶ (hex) 2�ֽ�
	 * @param info �ļ����� (hex) 
	 * @return
	 */
	public static byte[] CreateFile(String file,String info){
		String len = infoLength(info);
		return Command.getCommand(create + file + len + info);
	}
	
	
	/**
	 * ѡ���ļ�
	 * @param file  ѡ�е��ļ���ʾ��
	 * @param selecttype �ļ�����
	 * @return
	 */
	public static byte[] selectCatalog(String file,String selecttype){
		return Command.getCommand(select + selecttype + "0002"+ file + "00");
	}
	
	
	
	
	
}
