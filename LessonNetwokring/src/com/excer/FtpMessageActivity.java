package com.excer;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FtpMessageActivity extends Activity {
	
	private EditText mFtpHost;
	private TextView mFtpMessageResult;
	private static final int FTP_PORT = 21;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		setContentView(R.layout.ftp_message);
		mFtpHost = (EditText)findViewById(R.id.ftp_host);
		mFtpMessageResult = (TextView)findViewById(R.id.ftp_message_result);
		
	}
	
	/*
	 * reads the host name entered by the user. ..
	 * connects to port 21 on the host 
	 * and prints out a welcome if the connection is successful
	 */
	
	public void showMessage(View clickedButton){
		
		String host = mFtpHost.getText().toString();
		
		Socket socket;
		try {
			socket = new Socket(host, FTP_PORT);
			BufferedReader in = SocketUtils.getReader(socket);
			List<String> results = new ArrayList<String>();
			String line = in.readLine();
			
			System.out.println(line);
			
			results.add(line);
			
			if (line.startsWith("220-")){
				while((line = in.readLine()) !=  null){
					results.add(line);
					if((line.equals("220") || line.startsWith("220 "))){
						break;
					}
				}
			}
			
			String output = makeOutputString(results);
			mFtpMessageResult.setText(output);
			socket.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	private String makeOutputString(List<String> results) {

		StringBuilder output = new StringBuilder();
		for(String s: results){
			output.append(s + "\n");
		}
		return (output.toString());
	}

}
