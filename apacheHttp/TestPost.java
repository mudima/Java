package apacheHttp;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

class Post extends Thread {
	HttpClient client = HttpClients.createDefault();
	
	@Override
	public void run() {
		HttpPost post = new HttpPost("http://fanyi.youdao.com/openapi.do");
		try {
			//keyfrom=mudi-javahttp-test&key=343166845&type=data&doctype=xml&version=1.1&q=Welcome
			List<BasicNameValuePair> parameters = new ArrayList<>();
			parameters.add(new BasicNameValuePair("keyfrom", "mudi-javahttp-test"));
			parameters.add(new BasicNameValuePair("key", "343166845"));
			parameters.add(new BasicNameValuePair("type", "data"));
			parameters.add(new BasicNameValuePair("doctype", "xml"));
			parameters.add(new BasicNameValuePair("version", "1.1"));
			parameters.add(new BasicNameValuePair("q", "Welcome"));
			
			post.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
			
			HttpResponse response = client.execute(post);
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

public class TestPost {

	public static void main(String[] args) {
		Post post  = new Post();
		post.start();
	}

}
