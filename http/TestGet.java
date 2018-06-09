package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

class ReadByGet extends Thread {
	@Override
	public void run() {
		try {
			//最后q=是后要翻译的文本。doctype=后是返回格式xml, json
			URL url = new URL("http://fanyi.youdao.com/openapi.do?keyfrom=mudi-javahttp-test=343166845&type=data&doctype=xml&version=1.1&q=Hello Mudi!!!");
			URLConnection connection = url.openConnection();
			//字节流输入流
			InputStream inputStream = connection.getInputStream();
			//字符输入流
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String line;
			StringBuilder builder = new StringBuilder();
			while ((line = bufferedReader.readLine()) != null) {
				builder.append(line);
			}
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			
			System.out.println(builder.toString());
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

public class TestGet {	
	public static void main(String[] args) {
		ReadByGet readByGet = new ReadByGet();
		readByGet.start();

	}

}
