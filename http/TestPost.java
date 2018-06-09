package http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class ReadByPost extends Thread {
	public void run() {
		try {
			URL url = new URL("http://fanyi.youdao.com/openapi.do");
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.addRequestProperty("encoding", "UTF-8");
			connection.setDoInput(true);  //可以从connection读取
			connection.setDoOutput(true);  //可以向connection发送数据
			connection.setRequestMethod("POST");  //connection访问方式设定为POST
			
			
			//向服务器提交数据和参数
			OutputStream os = connection.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
			
			bw.write("keyfrom=mudi-java.http-test=343166845&type=data&doctype=<doctype>&version=1.1&q=Welcom");
			bw.flush(); //强制输出
			
			//从服务器提取数据
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String line;
			StringBuilder builder = new StringBuilder();
			while ((line = br.readLine()) != null) {
				builder.append(line);
			}
			
			//关闭资源
			bw.close();
			osw.close();
			os.close();
			br.close();
			isr.close();
			is.close();
			
			System.out.println(builder.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class TestPost {

	public static void main(String[] args) {
		ReadByPost readByPost = new ReadByPost();
		readByPost.start();

	}

}
