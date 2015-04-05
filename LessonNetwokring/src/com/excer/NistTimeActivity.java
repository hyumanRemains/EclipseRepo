package com.excer;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

public class NistTimeActivity extends Activity{

	private String mHost = "time-b.nist.gov";
	private int mPort = 13;
	private TextView mResultDisplay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nist_time);
		enableStrictMode();
		mResultDisplay = (TextView)findViewById(R.id.time_display);
	}

	public void enableStrictMode(){
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

	}

	public void showTime(View clickedButton) throws IOException{

		try{

			Socket socket = new Socket(mHost, mPort);
			BufferedReader in = SocketUtils.getReader(socket);
			in.readLine();

			String timeResult = in.readLine();
			mResultDisplay.setText(timeResult);
			socket.close();
		}catch(UnknownHostException uhe){
			mResultDisplay.setText("Unknown Host");
		} catch (IOException e) {
			mResultDisplay.setText("IOException");
			e.printStackTrace();
		}
	}


}
