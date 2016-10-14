package aizulove.com.fxxt.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {


    public static String sendPost(String url, String param) throws UnsupportedEncodingException {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("POST"+e);
            e.printStackTrace();
        }
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result ;
    }

	@SuppressWarnings("rawtypes")
	public static String login(Map map,String urlStr)
    {
		String responseMsg = "";
        HttpPost request = new HttpPost(urlStr);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        if(map!=null&&!map.isEmpty()){
		Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
	        Map.Entry entry = (Map.Entry) iter.next();
	        Object key = entry.getKey();
	        Object val = entry.getValue();
	        params.add(new BasicNameValuePair(key.toString(),val.toString()));
         }
        }
        try
        {

            request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(request);

            if(response.getStatusLine().getStatusCode()==200)
            {
                responseMsg = EntityUtils.toString(response.getEntity());
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return responseMsg;
    }
}
