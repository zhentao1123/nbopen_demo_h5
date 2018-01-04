package com.elong.nbopen.api.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Http请求通用类
 * 
 * <p>
 * 修改历史:											<br>
 * 修改日期    		修改人员   	版本	 		修改内容	<br>
 * -------------------------------------------------<br>
 * 2017.07.21   qianqian.xu     1.0			初始化创建<br>
 * </p> 
 * 
 * @author		qianqian.xu
 * @department	northbound
 */
public class Http {

	public static String Send(String method, String url, String data)
	{
		HttpURLConnection conn = null;
		InputStream in = null;
		InputStreamReader isr = null;
		OutputStream out = null;
		StringBuffer result = null;
		try
		{
			
			URL u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
			conn.setRequestProperty("Accept-Encoding", "gzip");
			//conn.setRequestProperty("Content-Type", "");
			conn.setRequestProperty("Connection", "keep-alive");
			
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(60000);

			if(method.equals("POST")) {
				byte[] sendbyte = data.getBytes("UTF-8");
				out = conn.getOutputStream();
				out.write(sendbyte);
			}

			int status = conn.getResponseCode();
			if (status == 200)
			{
				String enc = conn.getContentEncoding();
				result = new StringBuffer();
				in = conn.getInputStream();
				enc = conn.getContentEncoding();
			
				if(enc != null && enc.equals("gzip")) {
					java.util.zip.GZIPInputStream gzin = new java.util.zip.GZIPInputStream(in);
					isr = new InputStreamReader(gzin, "UTF-8");
					
				} else {
					isr = new InputStreamReader(in, "UTF-8");
				}

				char[] c = new char[1024];
				int a = isr.read(c);
				while (a != -1)
				{
					result.append(new String(c, 0, a));
					a = isr.read(c);
				}
			} else {
				System.out.println("http code = " + status);	
			
			}

		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (conn != null)
			{
				conn.disconnect();
			}
			try
			{
				if (in != null)
				{
					in.close();
				}
				if (isr != null)
				{
					isr.close();
				}
				if (out != null)
				{
					out.close();
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		return result == null ? null : result + "";
	}
}
