package com.excer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UrlCheckerActivity extends Activity{

	private EditText mUrlToTest;
	private TextView mUrlMessageResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.url_checker);
	
		mUrlToTest = (EditText)findViewById(R.id.url_to_test);
		mUrlMessageResult = (TextView)findViewById(R.id.url_validation_result);
		
	}
	
	
	public void checkUrl(View clickedButton){
		
		NetworkTask task = new NetworkTask();
		task.execute(this.mUrlToTest);
		
	}
	
	class NetworkTask extends AsyncTask<TextView, Void, String>{

		@Override
		protected String doInBackground(TextView... tv) {
			
			//String url = mUrlToTest.getText().toString();
			String url= tv.toString();
			String info = null;
			// url parser here.. make parser class first.
			
			UrlParser parser = new UrlParser(url);
			String host = parser.getmHost();
			int port = parser.getmPort();
			String uri = parser.getmUri();
			
			try{
				
				Socket socket = new Socket(host, port);
				
				PrintWriter out = SocketUtils.getWriter(socket);
				BufferedReader i = SocketUtils.getReader(socket);
				
				out.printf("HEAD %s HTTP/1.1\r\n", uri);
				out.printf("Host : %s\r\n", host);
				out.printf("Connection : close \r\n\r\n");
				
				String serverResult = i.readLine();
				info = statusInfo(serverResult, i);
				
				//mUrlMessageResult.setText(info);
				socket.close();
				
				
			}catch(UnknownHostException uhe){
//				mUrlMessageResult.setText("Unkown Host: " + host);
				uhe.getCause();
				
			}catch(IOException ioe){
//				mUrlMessageResult.setText("IOException ");
				ioe.printStackTrace();
				ioe.getCause();
			}
			
			return info;
		}

		@Override
		protected void onPostExecute(String result) {
			
			mUrlMessageResult.setText(result);
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
			
		}
		
		

	}
	
	private String statusInfo(String serverResult, BufferedReader i) 
	throws IOException{
		StatusLineParser statusLine = new StatusLineParser(serverResult);
		String result;
		if(statusLine.isGood()){
			result = String.format("Good URL : %s --- %s",
					statusLine.getmStatusCode(),
					statusLine.getmMessage());
		} else if(statusLine.isForwarded()){
			result = String.format("URL Forwarded to %s", location(i));
		} else {
			result = String.format("BAD URL : %s---%s", 
					statusLine.getmStatusCode(),
					statusLine.getmMessage());
		}
		return (result);
	}

	private String location(BufferedReader i) throws IOException {
	
		String line;
		while((line = i.readLine()) != null){
			if(line.toUpperCase().startsWith("LOCATION")){
				String[] results = line.split("\\s+", 2);
				return(results[1]);
			}
		}
		return ("Unknown Location");
		
	}

		
}