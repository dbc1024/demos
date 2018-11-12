/*package com.ectrip.ticket.service.message;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ectrip.base.util.Debug;
import com.ectrip.base.util.WebContant;
import com.ectrip.ticket.service.message.jzg.Balance;
import com.ectrip.ticket.service.message.jzg.MTMessage;
import com.ectrip.ticket.service.message.jzg.MTResponse;
import com.ectrip.ticket.service.message.jzg.SmsOperatorSoapBindingStub;

*//**
 * 手机短信发送平台。
 *
 * @author LIJIN
 *
 *//*
public class WebSendMsg {
	*//**
	 * 手机短信发送
	 *
	 * @param socket
	 * @param writeTo
	 * @return
	 * @throws Exception
	 *//*
	private static String writeToReadFromSocket(Socket socket, String writeTo) throws Exception {
		try {
			// write text to the socket
			BufferedWriter bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
			bufferedWriter.write(writeTo);
			bufferedWriter.flush();

			// read text from the socket
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();

			char c;
			while (!bufferedReader.ready()) {
			}
			while ((c = (char) bufferedReader.read()) != -1) {
				sb.append(c);
				if (sb.toString().endsWith("</DES>"))
					break;
				Thread.sleep(100);
			}

			bufferedReader.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	*//**
	 * 九网发送短信的方法
	 *
	 * @param SequenceID
	 * @param PhoneNumber
	 * @param Content
	 * @return
	 *//*

	public static boolean sendinfomms(String mbno, String mblr, String orid) {
		boolean ib_true = true;
		// 调用线程,以防止保存订单不成功
		// System.out.println("开发发送");
		Thread thread = new Thread(new YourWorkMms(mbno, mblr, ""));
		thread.start();
		// System.out.println("开发结速");
		return ib_true;
	}

	*//**
	 * 短信发送平台
	 *
	 * @param mobile
	 *            手机号
	 * @param body
	 *            发送内容
	 * @return
	 * @throws Exception
	 *//*
	public static int WebSend(String mobile, String body) throws Exception {
		String account =WebContant.GetKeyValue("MBACCOUNT");
		String password = WebContant.GetKeyValue("MBPASSWORD");
		// String body = "测试信息,收到请在QQ中回复";
		
		 * <MBACCOUNT>101100-SJB-HUAX-627184</MBACCOUNT> <MBPASSWORD>GVBYIYBC</MBPASSWORD>
		 * <MBHOST>www.stongnet.com</MBHOST> <MBPORT>8080</MBPORT>
		 
		// String mobile = "15074429994";
		String host = WebContant.GetKeyValue("MBHOST");
		String strport = WebContant.GetKeyValue("MBPORT");
		int port = Integer.valueOf(strport).intValue();
		String content = "/<END><COMMAND>S058</COMMAND>" + "<REGISTRYCODE>" + account + "</REGISTRYCODE><PWD>"
				+ password + "</PWD><TIM></TIM><CONTENT>" + body + "</CONTENT><SMSID>1</SMSID><MOBILE>" + mobile
				+ "</MOBILE></END>";

		Socket socket = new Socket(host, port);
		StringBuilder buf = new StringBuilder();
		buf.append("GET ").append(content).append(" HTTP/1.0\r\n").append(
				"Content-Type: application/x-www-form-urlencoded\r\n").append("\r\n");
		String rawResponse = writeToReadFromSocket(socket, buf.toString());
		System.out.println("rawResponse: " + rawResponse);

		Pattern pattern = Pattern.compile("result=(.*)&des=(.*)");
		Matcher matcher = pattern.matcher(rawResponse);
		int rc_id = 0;
		if (matcher.find()) {
			int result = Integer.parseInt(matcher.group(1));
			String des = matcher.group(2);
			rc_id = 1;
		} else
			rc_id = 0;
		socket.close();
		return rc_id;
	}

	*//**
	 * 短信发送平台
	 *
	 * @param mobile
	 *            手机号
	 * @param body
	 *            发送内容
	 * @return
	 * @throws Exception
	 *//*
	public static int WebSendjzg(String mobile, String body) throws Exception {
		String account = WebContant.GetKeyValue("MBACCOUNT");
		String password = WebContant.GetKeyValue("MBPASSWORD");
		// String body = "测试信息,收到请在QQ中回复";
		
		 * <MBACCOUNT>101100-SJB-HUAX-627184</MBACCOUNT> <MBPASSWORD>GVBYIYBC</MBPASSWORD>
		 * <MBHOST>www.stongnet.com</MBHOST> <MBPORT>8080</MBPORT>
		 
		// String mobile = "15074429994";
		String host = WebContant.GetKeyValue("MBHOST");
		String strport = WebContant.GetKeyValue("MBPORT");
		int port = Integer.valueOf(strport).intValue();
		String content = "/<END><COMMAND>S058</COMMAND>" + "<REGISTRYCODE>" + account + "</REGISTRYCODE><PWD>"
				+ password + "</PWD><TIM></TIM><CONTENT>" + body + "</CONTENT><SMSID>1</SMSID><MOBILE>" + mobile
				+ "</MOBILE></END>";

		Socket socket = new Socket(host, port);
		StringBuilder buf = new StringBuilder();
		buf.append("GET ").append(content).append(" HTTP/1.0\r\n").append(
				"Content-Type: application/x-www-form-urlencoded\r\n").append("\r\n");
		String rawResponse = writeToReadFromSocket(socket, buf.toString());
		System.out.println("rawResponse: " + rawResponse);

		Pattern pattern = Pattern.compile("result=(.*)&des=(.*)");
		Matcher matcher = pattern.matcher(rawResponse);
		int rc_id = 0;
		if (matcher.find()) {
			int result = Integer.parseInt(matcher.group(1));
			String des = matcher.group(2);
			rc_id = 1;
		} else
			rc_id = 0;
		socket.close();
		return rc_id;
	}

	public static void main(String[] args) throws Exception {
		// 用户名：88010110 接口密码：cbff36039c3d0212b3e34c23dcde1456

		
		 * String account = "101100-SJB-HUAX-018686"; String password = "GVBYIYBC"; String body = "测试信息,收到请在QQ中回复";
		 * String mobile = "13923834675"; String host = "www.stongnet.com"; int port = 8080; String content =
		 * "/<END><COMMAND>S058</COMMAND>" + "<REGISTRYCODE>" + account + "</REGISTRYCODE><PWD>" + password +
		 * "</PWD><TIM></TIM><CONTENT>" + body + "</CONTENT><SMSID>1</SMSID><MOBILE>" + mobile + "</MOBILE></END>";
		 * Socket socket = new Socket(host, port); StringBuilder buf = new StringBuilder();
		 * buf.append("GET ").append(content).append(" HTTP/1.0\r\n").append(
		 * "Content-Type: application/x-www-form-urlencoded\r\n").append("\r\n"); String rawResponse =
		 * writeToReadFromSocket(socket, buf.toString()); System.out.println("rawResponse: " + rawResponse);
		 *
		 * Pattern pattern = Pattern.compile("result=(.*)&des=(.*)"); Matcher matcher = pattern.matcher(rawResponse); if
		 * (matcher.find()) { int result = Integer.parseInt(matcher.group(1)); String des = matcher.group(2);
		 * System.out.println("result: " + result + ", des=" + des); } else System.out.println("Not matched");
		 * socket.close();
		 

		WebSendMsg.jzgsendinfo("3", "13923834675", "测试式ddd");
	}

	*//**
	 * 黄山短信发送程序
	 * @param SequenceID
	 * @param PhoneNumber
	 * @param Content
	 * @return
	 *//*

	public static boolean hssendinfo(String SequenceID, String PhoneNumber, String Content) {
		// TODO Auto-generated method stub
		// 用户名：88010110 接口密码：cbff36039c3d0212b3e34c23dcde1456
		boolean ib_true = false;
		try {
			javax.xml.rpc.Service service = null;

			String account = WebContant.GetKeyValue("MBACCOUNT");
			String password = WebContant.GetKeyValue("MBPASSWORD");
			String host = WebContant.GetKeyValue("MBHOST");

			// 调试完成后，一定要将这里去掉
			account = "6115";
			password = "tourmart";
			host = "http://211.147.248.65:3388/CellServer/services/SmsAPI?wsdl";

			java.net.URL endpointURL = new java.net.URL(host);
			EctripMd5 md5 = new EctripMd5(SequenceID);
			md5.calc();
			String StrSequenceID = md5.toString();
			// SmsOperatorServiceLocator ssl = new SmsOperatorServiceLocator();



			// Balance balance = ssl.getBalance("88010110", "cbff36039c3d0212b3e34c23dcde1456");
//			if (mr.length > 0) {
//				ib_true = mr[0].isIsSuccess();
//				System.out.print(mr[0].isIsSuccess());
//			} else {
//				ib_true = false;
//			}
			// System.out.print(balance.getSmsBalance());
		} catch (Exception e) {
			System.out.print(e);
		}
		return ib_true;
		// MTResponse[] sendSms(String account, String password,MTMessage message);
	}


	*//**
	 * 九网发送短信的方法
	 *
	 * @param SequenceID
	 * @param PhoneNumber
	 * @param Content
	 * @return
	 *//*

	public static boolean jcsendinfo(String mbno, String mblr, String seq) {
		boolean ib_true = true;
		// 调用线程,以防止保存订单不成功
		// System.out.println("开发发送");
		Thread thread = new Thread(new YourWork(mbno, mblr, seq));
		thread.start();
		// System.out.println("开发结速");
		return ib_true;
	}

	public static boolean jzgsendinfo(String SequenceID, String PhoneNumber, String Content) {
		// TODO Auto-generated method stub
		// 用户名：88010110 接口密码：cbff36039c3d0212b3e34c23dcde1456
		boolean ib_true = false;
		try {
			// java.net.URL url = new java.net.URL("3tong.cn:8080");
			javax.xml.rpc.Service service = null;

			String account = WebContant.GetKeyValue("MBACCOUNT");
			String password = WebContant.GetKeyValue("MBPASSWORD");
			String host = WebContant.GetKeyValue("MBHOST");

			// 调试完成后，一定要将这里去掉
			account = "88010110";
			password = "cbff36039c3d0212b3e34c23dcde1456";
			host = "http://3tong.cn:8080/ema_new/services/SmsOperator?wsdl";

			java.net.URL endpointURL = new java.net.URL(host);
			EctripMd5 md5 = new EctripMd5(SequenceID);
			md5.calc();
			String StrSequenceID = md5.toString();
			// SmsOperatorServiceLocator ssl = new SmsOperatorServiceLocator();
			SmsOperatorSoapBindingStub ssl = new SmsOperatorSoapBindingStub(endpointURL, service);
			MTMessage message = new MTMessage();
			message.setContent(Content);
			message.setPhoneNumber(PhoneNumber);
			message.setScheduleTime(null);
			message.setSequenceId(StrSequenceID);
			MTResponse mr[] = ssl.sendSms(account, password, message);
			// Balance balance = ssl.getBalance("88010110", "cbff36039c3d0212b3e34c23dcde1456");
			if (mr.length > 0) {
				ib_true = mr[0].isIsSuccess();
				System.out.print(mr[0].isIsSuccess());
			} else {
				ib_true = false;
			}
			// System.out.print(balance.getSmsBalance());
		} catch (Exception e) {
			System.out.print(e);
		}
		return ib_true;
		// MTResponse[] sendSms(String account, String password,MTMessage message);
	}

	public float jzgblance() {
		float rc = 0f;
		// TODO Auto-generated method stub
		// 用户名：88010110 接口密码：cbff36039c3d0212b3e34c23dcde1456
		try {
			// java.net.URL url = new java.net.URL("3tong.cn:8080");

			String account = WebContant.GetKeyValue("MBACCOUNT");
			String password = WebContant.GetKeyValue("MBPASSWORD");
			String host = WebContant.GetKeyValue("MBHOST");

			// 调试完成后，一定要将这里去掉
			account = "88010110";
			password = "cbff36039c3d0212b3e34c23dcde1456";
			host = "http://3tong.cn:8080/ema_new/services/SmsOperator?wsdl";

			javax.xml.rpc.Service service = null;

			java.net.URL endpointURL = new java.net.URL(host);

			// SmsOperatorServiceLocator ssl = new SmsOperatorServiceLocator();
			SmsOperatorSoapBindingStub ssl = new SmsOperatorSoapBindingStub(endpointURL, service);

			Balance balance = ssl.getBalance(account, password);
			rc = balance.getSmsBalance();

		} catch (Exception e) {
			System.out.print(e);
		}
		//
		return rc;
	}

}

*//**
 * 发送短信的线程方法
 *
 * @author LiJin
 *
 *//*
class YourWork implements Runnable {
	// 手机号码
	private String mbno;

	public String getMbno() {
		return mbno;
	}

	public void setMbno(String mbno) {
		this.mbno = mbno;
	}

	public String getMblr() {
		return mblr;
	}

	public void setMblr(String mblr) {
		this.mblr = mblr;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	// 发送内容
	private String mblr;
	// 唯一ID
	private String seq;

	public void run() {
//		try {
//			boolean ib_true = false;
//			SMSClient client = new SMSClient();
//			System.out.println(ToServerXML.getSendSmsXML());
//			System.out.println("正在发送");
//			String sendSmsToBack = client
//					.SendReceive(ToServerXML.getSendSmsXML(mbno, mblr, seq), "urn:SendSmsToServer"); //
//			// System.out.println("SendSmsToServer...xml：" + sendSmsToBack);
//
//			if (sendSmsToBack.indexOf("<ErrorFlag>0</ErrorFlag>") > 0)
//
//				ib_true = true;
//			else
//				ib_true = false;
//
//		} catch (Exception e) {
//			System.out.println("SendSmsToServer...xml：" + e.toString());
//		}
	}

	// 线程构造涵数
	YourWork(String mbno, String mblr, String seq) {
		this.setMbno(mbno);
		this.setMblr(mblr);
		this.setSeq(seq);
	}
}

*//**
 * 发送采信的多线程序，不然程序会死掉
 *
 * @author LiJin
 *
 *//*
class YourWorkMms implements Runnable {
	// 手机号码
	private String mbno;

	public String getMbno() {
		return mbno;
	}

	public void setMbno(String mbno) {
		this.mbno = mbno;
	}

	public String getMblr() {
		return mblr;
	}

	public void setMblr(String mblr) {
		this.mblr = mblr;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	// 发送内容
	private String mblr;
	// 唯一ID
	private String seq;

	public void run() {
		boolean ib_true = false;
		try {

			Debug.print("在这里加入真正的发程序");
			Debug.print("短信已经发送成功,mbno=" + mbno + ",mblr=" + mblr);
			// 彩信
			WebSendMms.sendMms(mbno, mblr);
			Debug.print("彩信发送完毕");
			//
		} catch (Exception e) {
			System.out.println("SendSmsToServer...xml：" + e.toString());
		}
	}

	// 线程构造涵数
	YourWorkMms(String mbno, String mblr, String seq) {
		this.setMbno(mbno);
		this.setMblr(mblr);
		this.setSeq(seq);
	}
}

*//**
 * 自用EctripMd5 算法，有时在不同的JDK中，算 出的MD5密文不相等，所以调用自己的密文。 使用方法如下：
 *
 * EctripMd5 md5 = new EctripMd5(srcstr); md5.calc(); md5.toString()
 *
 * @author LIJIN
 *
 *//*
class EctripMd5 {
	int A, B, C, D;
	int d[];
	int numwords;

	public EctripMd5(String s) {
		s = s + "ectrip";
		byte in[] = new byte[s.length()];
		int i;

		for (i = 0; i < s.length(); i++) {
			in[i] = (byte) (s.charAt(i) & 0xff);
		}
		mdinit(in);
	}

	*//**
	 * 用自用MD5算法进行密文比较
	 *
	 * @param mingwen
	 *            明文
	 * @param miwen
	 *            密文
	 * @return
	 *//*
	public static boolean compareToMd5(String mingwen, String miwen) {

		EctripMd5 md5 = new EctripMd5(mingwen);
		md5.calc();

		boolean ib_true = miwen.equalsIgnoreCase(md5.toString());

		return ib_true;

	}

	*//**
	 *
	 * @param in
	 *//*
	public EctripMd5(byte in[]) {

		byte ec[] = "ectrip".getBytes();
		byte in1[] = new byte[ec.length + in.length];
		System.arraycopy(in, 0, in1, 0, in.length);
		System.arraycopy(ec, 0, in1, in1.length - ec.length, ec.length);
		mdinit(in1);
	}

	public void mdinit(byte in[]) {
		int newlen, endblklen, pad, i;
		long datalenbits;

		datalenbits = in.length * 8;
		endblklen = in.length % 64;
		if (endblklen < 56) {
			pad = 64 - endblklen;
		} else {
			pad = (64 - endblklen) + 64;
		}
		newlen = in.length + pad;
		byte b[] = new byte[newlen];
		for (i = 0; i < in.length; i++) {
			b[i] = in[i];
		}
		b[in.length] = (byte) 0x80;
		for (i = b.length + 1; i < (newlen - 8); i++) {
			b[i] = 0;
		}
		for (i = 0; i < 8; i++) {
			b[newlen - 8 + i] = (byte) (datalenbits & 0xff);
			datalenbits >>= 8;
		}
		 init registers 
		A = 0x67452301;
		B = 0xefcdab89;
		C = 0x98badcfe;
		D = 0x10325476;
		this.numwords = newlen / 4;
		this.d = new int[this.numwords];
		for (i = 0; i < newlen; i += 4) {
			this.d[i / 4] = (b[i] & 0xff) + ((b[i + 1] & 0xff) << 8) + ((b[i + 2] & 0xff) << 16)
					+ ((b[i + 3] & 0xff) << 24);
		}
	}

	private static int F(int x, int y, int z) {
		return ((x & y) | (~x & z));
	}

	private static int G(int x, int y, int z) {
		return ((x & z) | (y & ~z));
	}

	private static int H(int x, int y, int z) {
		return (x ^ y ^ z);
	}

	private static int I(int x, int y, int z) {
		return (y ^ (x | ~z));
	}

	static int rotintlft(int val, int numbits) {
		return ((val << numbits) | (val >>> (32 - numbits)));
	}

	static String tohex(int i) {
		int b;
		String tmpstr;

		tmpstr = "";
		for (b = 0; b < 4; b++) {
			tmpstr += Integer.toString((i >> 4) & 0xf, 16) + Integer.toString(i & 0xf, 16);
			i >>= 8;
		}
		return tmpstr;
	}

	void round1(int blk) {
		A = rotintlft(A + F(B, C, D) + d[0 + 16 * blk] + 0xd76aa478, 7) + B;
		D = rotintlft(D + F(A, B, C) + d[1 + 16 * blk] + 0xe8c7b756, 12) + A;
		C = rotintlft(C + F(D, A, B) + d[2 + 16 * blk] + 0x242070db, 17) + D;
		B = rotintlft(B + F(C, D, A) + d[3 + 16 * blk] + 0xc1bdceee, 22) + C;

		A = rotintlft(A + F(B, C, D) + d[4 + 16 * blk] + 0xf57c0faf, 7) + B;
		D = rotintlft(D + F(A, B, C) + d[5 + 16 * blk] + 0x4787c62a, 12) + A;
		C = rotintlft(C + F(D, A, B) + d[6 + 16 * blk] + 0xa8304613, 17) + D;
		B = rotintlft(B + F(C, D, A) + d[7 + 16 * blk] + 0xfd469501, 22) + C;
		A = rotintlft(A + F(B, C, D) + d[8 + 16 * blk] + 0x698098d8, 7) + B;
		D = rotintlft(D + F(A, B, C) + d[9 + 16 * blk] + 0x8b44f7af, 12) + A;
		C = rotintlft(C + F(D, A, B) + d[10 + 16 * blk] + 0xffff5bb1, 17) + D;
		B = rotintlft(B + F(C, D, A) + d[11 + 16 * blk] + 0x895cd7be, 22) + C;
		A = rotintlft(A + F(B, C, D) + d[12 + 16 * blk] + 0x6b901122, 7) + B;
		D = rotintlft(D + F(A, B, C) + d[13 + 16 * blk] + 0xfd987193, 12) + A;
		C = rotintlft(C + F(D, A, B) + d[14 + 16 * blk] + 0xa679438e, 17) + D;
		B = rotintlft(B + F(C, D, A) + d[15 + 16 * blk] + 0x49b40821, 22) + C;
	}

	void round2(int blk) {
		A = rotintlft(A + G(B, C, D) + d[1 + 16 * blk] + 0xf61e2562, 5) + B;
		D = rotintlft(D + G(A, B, C) + d[6 + 16 * blk] + 0xc040b340, 9) + A;
		C = rotintlft(C + G(D, A, B) + d[11 + 16 * blk] + 0x265e5a51, 14) + D;
		B = rotintlft(B + G(C, D, A) + d[0 + 16 * blk] + 0xe9b6c7aa, 20) + C;
		A = rotintlft(A + G(B, C, D) + d[5 + 16 * blk] + 0xd62f105d, 5) + B;
		D = rotintlft(D + G(A, B, C) + d[10 + 16 * blk] + 0x02441453, 9) + A;
		C = rotintlft(C + G(D, A, B) + d[15 + 16 * blk] + 0xd8a1e681, 14) + D;
		B = rotintlft(B + G(C, D, A) + d[4 + 16 * blk] + 0xe7d3fbc8, 20) + C;
		A = rotintlft(A + G(B, C, D) + d[9 + 16 * blk] + 0x21e1cde6, 5) + B;
		D = rotintlft(D + G(A, B, C) + d[14 + 16 * blk] + 0xc33707d6, 9) + A;
		C = rotintlft(C + G(D, A, B) + d[3 + 16 * blk] + 0xf4d50d87, 14) + D;
		B = rotintlft(B + G(C, D, A) + d[8 + 16 * blk] + 0x455a14ed, 20) + C;
		A = rotintlft(A + G(B, C, D) + d[13 + 16 * blk] + 0xa9e3e905, 5) + B;
		D = rotintlft(D + G(A, B, C) + d[2 + 16 * blk] + 0xfcefa3f8, 9) + A;
		C = rotintlft(C + G(D, A, B) + d[7 + 16 * blk] + 0x676f02d9, 14) + D;
		B = rotintlft(B + G(C, D, A) + d[12 + 16 * blk] + 0x8d2a4c8a, 20) + C;
	}

	void round3(int blk) {
		A = rotintlft(A + H(B, C, D) + d[5 + 16 * blk] + 0xfffa3942, 4) + B;
		D = rotintlft(D + H(A, B, C) + d[8 + 16 * blk] + 0x8771f681, 11) + A;
		C = rotintlft(C + H(D, A, B) + d[11 + 16 * blk] + 0x6d9d6122, 16) + D;
		B = rotintlft(B + H(C, D, A) + d[14 + 16 * blk] + 0xfde5380c, 23) + C;
		A = rotintlft(A + H(B, C, D) + d[1 + 16 * blk] + 0xa4beea44, 4) + B;
		D = rotintlft(D + H(A, B, C) + d[4 + 16 * blk] + 0x4bdecfa9, 11) + A;
		C = rotintlft(C + H(D, A, B) + d[7 + 16 * blk] + 0xf6bb4b60, 16) + D;
		B = rotintlft(B + H(C, D, A) + d[10 + 16 * blk] + 0xbebfbc70, 23) + C;
		A = rotintlft(A + H(B, C, D) + d[13 + 16 * blk] + 0x289b7ec6, 4) + B;
		D = rotintlft(D + H(A, B, C) + d[0 + 16 * blk] + 0xeaa127fa, 11) + A;
		C = rotintlft(C + H(D, A, B) + d[3 + 16 * blk] + 0xd4ef3085, 16) + D;
		B = rotintlft(B + H(C, D, A) + d[6 + 16 * blk] + 0x04881d05, 23) + C;
		A = rotintlft(A + H(B, C, D) + d[9 + 16 * blk] + 0xd9d4d039, 4) + B;
		D = rotintlft(D + H(A, B, C) + d[12 + 16 * blk] + 0xe6db99e5, 11) + A;
		C = rotintlft(C + H(D, A, B) + d[15 + 16 * blk] + 0x1fa27cf8, 16) + D;
		B = rotintlft(B + H(C, D, A) + d[2 + 16 * blk] + 0xc4ac5665, 23) + C;
	}

	void round4(int blk) {
		A = rotintlft(A + I(B, C, D) + d[0 + 16 * blk] + 0xf4292244, 6) + B;
		D = rotintlft(D + I(A, B, C) + d[7 + 16 * blk] + 0x432aff97, 10) + A;
		C = rotintlft(C + I(D, A, B) + d[14 + 16 * blk] + 0xab9423a7, 15) + D;
		B = rotintlft(B + I(C, D, A) + d[5 + 16 * blk] + 0xfc93a039, 21) + C;
		A = rotintlft(A + I(B, C, D) + d[12 + 16 * blk] + 0x655b59c3, 6) + B;
		D = rotintlft(D + I(A, B, C) + d[3 + 16 * blk] + 0x8f0ccc92, 10) + A;
		C = rotintlft(C + I(D, A, B) + d[10 + 16 * blk] + 0xffeff47d, 15) + D;
		B = rotintlft(B + I(C, D, A) + d[1 + 16 * blk] + 0x85845dd1, 21) + C;
		A = rotintlft(A + I(B, C, D) + d[8 + 16 * blk] + 0x6fa87e4f, 6) + B;
		D = rotintlft(D + I(A, B, C) + d[15 + 16 * blk] + 0xfe2ce6e0, 10) + A;
		C = rotintlft(C + I(D, A, B) + d[6 + 16 * blk] + 0xa3014314, 15) + D;
		B = rotintlft(B + I(C, D, A) + d[13 + 16 * blk] + 0x4e0811a1, 21) + C;
		A = rotintlft(A + I(B, C, D) + d[4 + 16 * blk] + 0xf7537e82, 6) + B;
		D = rotintlft(D + I(A, B, C) + d[11 + 16 * blk] + 0xbd3af235, 10) + A;
		C = rotintlft(C + I(D, A, B) + d[2 + 16 * blk] + 0x2ad7d2bb, 15) + D;
		B = rotintlft(B + I(C, D, A) + d[9 + 16 * blk] + 0xeb86d391, 21) + C;
	}

	public String toString() {
		String s;

		return (tohex(A) + tohex(B) + tohex(C) + tohex(D));
	}

	public int[] getregs() {
		int regs[] = { this.A, this.B, this.C, this.D };

		return regs;
	}

	public void calc() {
		int AA, BB, CC, DD, i;

		for (i = 0; i < numwords / 16; i++) {
			AA = A;
			BB = B;
			CC = C;
			DD = D;
			round1(i);
			round2(i);
			round3(i);
			round4(i);
			A += AA;
			B += BB;
			C += CC;
			D += DD;
		}
	}
}
*/