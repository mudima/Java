package apacheHttp;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

class Get extends Thread {
	HttpClient client = HttpClients.createDefault();
	
	@Override
	public void run() {
		HttpGet get = new HttpGet("http://www.baidu.com");
		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			String html = EntityUtils.toString(entity, "UTF-8");
			
			System.out.println(html);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
public class TestGet {

	public static void main(String[] args) {
		Get get  = new Get();
		get.start();
	}

}
