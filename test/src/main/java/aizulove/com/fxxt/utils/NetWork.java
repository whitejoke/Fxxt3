package aizulove.com.fxxt.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetWork {


	public static StringBuilder postStringFromUrl(String baseUrl,
			Map<String, String> map) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, 20000);
		HttpConnectionParams.setSoTimeout(params, 20000);
		HttpPost httpPost = new HttpPost(baseUrl);
		List<BasicNameValuePair> postData = new ArrayList<BasicNameValuePair>();
		Set<Map.Entry<String, String>> entries = map.entrySet();// map��ĺ���
		for (Map.Entry<String, String> entry : entries) {
			postData.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postData,HTTP.UTF_8);
		httpPost.setEntity(entity);
		return obtainStringFromInputStream(httpClient.execute(httpPost)
				.getEntity().getContent());
	}


	public static String post(String path, Map<String, String> params, List<FormFile> files) throws Exception{
		final String BOUNDARY = "---------------------------7da2137580612"; //数据分隔线
		final String endline = "--" + BOUNDARY + "--\r\n";//数据结束标志
		String result="";
		int fileDataLength = 0;
		for(FormFile uploadFile : files){//得到文件类型数据的总长度
			StringBuilder fileExplain = new StringBuilder();
			fileExplain.append("--");
			fileExplain.append(BOUNDARY);
			fileExplain.append("\r\n");
			fileExplain.append("Content-Disposition: form-data;name=\""+ uploadFile.getParameterName()+"\";filename=\""+ uploadFile.getFilname() + "\"\r\n");
			fileExplain.append("Content-Type: "+ uploadFile.getContentType()+"\r\n\r\n");
			fileExplain.append("\r\n");
			fileDataLength += fileExplain.length();
			if(uploadFile.getInStream()!=null){
				fileDataLength += uploadFile.getFile().length();
			}else{
				fileDataLength += uploadFile.getData().length;
			}
		}
		StringBuilder textEntity = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {//构造文本类型参数的实体数据
			textEntity.append("--");
			textEntity.append(BOUNDARY);
			textEntity.append("\r\n");
			textEntity.append("Content-Disposition: form-data; name=\""+ entry.getKey() + "\"\r\n\r\n");
			textEntity.append(entry.getValue());
			textEntity.append("\r\n");
		}
		//计算传输给服务器的实体数据总长度
		int dataLength = textEntity.toString().getBytes().length + fileDataLength +  endline.getBytes().length;

		URL url = new URL(path);
		int port = url.getPort()==-1 ? 80 : url.getPort();
		Socket socket = new Socket(InetAddress.getByName(url.getHost()), port);
		OutputStream outStream = socket.getOutputStream();
		//下面完成HTTP请求头的发送
		String requestmethod = "POST "+ url.getPath()+" HTTP/1.1\r\n";
		outStream.write(requestmethod.getBytes());
		String accept = "Accept: image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*\r\n";
		outStream.write(accept.getBytes());
		String language = "Accept-Language: zh-CN\r\n";
		outStream.write(language.getBytes());
		String contenttype = "Content-Type: multipart/form-data; boundary="+ BOUNDARY+ "\r\n";
		outStream.write(contenttype.getBytes());
		String contentlength = "Content-Length: "+ dataLength + "\r\n";
		outStream.write(contentlength.getBytes());
		String alive = "Connection: Keep-Alive\r\n";
		outStream.write(alive.getBytes());
		String host = "Host: "+ url.getHost() +":"+ port +"\r\n";
		outStream.write(host.getBytes());
		//写完HTTP请求头后根据HTTP协议再写一个回车换行
		outStream.write("\r\n".getBytes());
		//把所有文本类型的实体数据发送出来
		outStream.write(textEntity.toString().getBytes());
		//把所有文件类型的实体数据发送出来
		for(FormFile uploadFile : files){
			StringBuilder fileEntity = new StringBuilder();
			fileEntity.append("--");
			fileEntity.append(BOUNDARY);
			fileEntity.append("\r\n");
			fileEntity.append("Content-Disposition: form-data;name=\""+ uploadFile.getParameterName()+"\";filename=\""+ uploadFile.getFilname() + "\"\r\n");
			fileEntity.append("Content-Type: "+ uploadFile.getContentType()+"\r\n\r\n");
			outStream.write(fileEntity.toString().getBytes());
			if(uploadFile.getInStream()!=null){
				byte[] buffer = new byte[1024];
				int len = 0;
				while((len = uploadFile.getInStream().read(buffer, 0, 1024))!=-1){
					outStream.write(buffer, 0, len);
				}
				uploadFile.getInStream().close();
			}else{
				outStream.write(uploadFile.getData(), 0, uploadFile.getData().length);
			}
			outStream.write("\r\n".getBytes());
		}

		//下面发送数据结束标志，表示数据已经结束
		outStream.write(endline.getBytes());

		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//		if(reader.readLine().indexOf("200")!=-1){//读取web服务器返回的数据，判断请求码是否为200，如果不是200，代表请求失败
			int ss;
			StringBuffer sb1 = new StringBuffer();
			while ((ss = reader.read()) != -1) {
				sb1.append((char) ss);
			}
			Log.i("susu",sb1.toString());
			result = sb1.toString();
//		}
		outStream.flush();
		outStream.close();
		reader.close();
		socket.close();
		return result;
	}



	public static StringBuilder obtainStringFromInputStream(InputStream is) {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		try {
			while (null != (line = br.readLine())) {
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sb;
	}


	public static String getStringFromUrl(String urlStr) throws Exception {
		StringBuilder result = null;
		InputStream is = null;
		if (null == (is = getInputStreamFromUrl(urlStr))) {
			throw new Exception("��URL���ص�InputStreamΪnull");
		}
		result = obtainStringFromInputStream(is);
		is.close();
		return result.toString();
	}


	public static InputStream getInputStreamFromUrl(String urlStr) {
		InputStream is = null;
		try {
			// URL url = new URL(urlStr);
			// HttpURLConnection conn = (HttpURLConnection)
			// url.openConnection();
			// is = conn.getInputStream();
			/*URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			InputStream in = conn.getInputStream();*/

			// HttpURLConnection conn = (HttpURLConnection)
			// url.openConnection();
			// InputStream inStream = conn.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	/**
	 * ����ֻ���������
	 * 
	 * @param context
	 * @return
	 */
	public static boolean checkNetWorkStatus(Context context) {
		boolean result;
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netinfo = cm.getActiveNetworkInfo();
		if (netinfo != null && netinfo.isConnected()) {
			result = true;
			Log.i("NetStatus", "The net was connected");
		} else {
			result = false;
			Log.i("NetStatus", "The net was bad!");
		}
		return result;
	}
}
