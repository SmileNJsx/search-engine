package search.engine.spider;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RetrivePage {
	public String getContent(String url) throws Exception{
		CloseableHttpClient httpClient=HttpClients.createDefault();
		try{
			HttpGet httpGet=new HttpGet(url);
			System.out.println("Executing Request  "+httpGet.getURI()+"...");
			
			ResponseHandler<String> responseHandler=new ResponseHandler<String>(){

				@Override
				public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
					// TODO Auto-generated method stub
					
					int status=response.getStatusLine().getStatusCode();
					if(status>=200&&status<300){
						HttpEntity entity=response.getEntity();
						return entity != null ? EntityUtils.toString(entity):null;
					}
					else{
						throw new ClientProtocolException("Unexpected Response Status:"+status);
					}
				}
			};
			
			String responseBody=httpClient.execute(httpGet, responseHandler);
			return responseBody;

		}finally{
			httpClient.close();
		}
	}
}
