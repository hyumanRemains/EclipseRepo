package com.excer;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.webkit.WebView.FindListener;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskManager extends AsyncTask<String, Void, String>{

	private TextView dataField;
	private Context context;
	
	public AsyncTaskManager(Context context, TextView dataField){
		this.context = context;
		this.dataField = dataField;
	}
	
	private void checkInternetConnection(){
		ConnectivityManager check = (ConnectivityManager) this.context.
				getSystemService(Context.CONNECTIVITY_SERVICE);
		if (check != null){
			NetworkInfo[] info = check.getAllNetworkInfo();
			
			for(int i=0; i < info.length; i++){
				if(info[i].getState() == NetworkInfo.State.CONNECTED){
					Toast.makeText(context, "Internet Connected", 6).show();
					
				}
			}
		}else{
			Toast.makeText(context, "Not Connected", 5).show();
		}
		
	}

	public AsyncTaskManager(TextView dataField) {
	
		this.dataField = dataField;
	}

	@Override
	protected String doInBackground(String... params) {
		
		System.out.println("in background process------");
		return "in background process";
	}

	@Override
	protected void onPostExecute(String result) {
		System.out.println(result);
		dataField.setText(result);
	}
	
	
}

