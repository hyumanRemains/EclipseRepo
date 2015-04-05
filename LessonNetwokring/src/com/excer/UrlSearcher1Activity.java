package com.excer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/*check for keywords in a given URL
 * Displays the lines that match
 */

public class UrlSearcher1Activity extends Activity{
	
	protected EditText mUrlToSearch, mSearchString;
	protected TextView mUrlMessageResult;
	protected float mResultTextSize, mErrorTextSize;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.url_searcher);
		
		mUrlToSearch = (EditText)findViewById(R.id.url_to_search);
		mSearchString = (EditText)findViewById(R.id.search_string);
        mUrlMessageResult = (TextView)findViewById(R.id.url_search_result);
        
        Resources resources = getResources();
        mResultTextSize = resources.getDimension(R.dimen.url_search_results_size);
        mErrorTextSize = resources.getDimension(R.dimen.url_search_error_size);
        
        
	}

	public void searchUrl(View clickedButton){
		
		String urlString = mUrlToSearch.getText().toString();
		String searchString = mSearchString.getText().toString();
		showResults(urlString, searchString);
	}

	private void showResults(String urlString, String searchString) {
		HttpURLConnection urlConnection = null;
		try{
			
			URL url = new URL(urlString);
			urlConnection = (HttpURLConnection)url.openConnection();
			
			BufferedReader in = 
					new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			StringBuilder matches = new StringBuilder("");
			
			int lineNum = 0;
			int numMatches = 0;
			
			while((line = in.readLine()) != null){
				lineNum++;
				if(line.contains(searchString)){
					numMatches++;
					matches.append(makeMatch(line, lineNum));
				}
				
			}
			displayResults(matches, numMatches);
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			if (urlConnection != null){
				urlConnection.disconnect();
			}
		}
		
	}

	private void displayResults(StringBuilder matches, int numMatches) {

		if(numMatches > 0){
			showMatches(matches, numMatches);
		}else{
			showError("No Matches");
		}
		
	}

	private void showError(String string) {
		mUrlMessageResult.setTextSize(mErrorTextSize);
		mUrlMessageResult.setText("\n\n" + string);
	}

	private void showMatches(StringBuilder matches, int numMatches) {
		String introMessage = 
				String.format("FOUND %s MATCHES: %n%n", numMatches);
		
		matches.insert(0, introMessage);
		mUrlMessageResult.setTextSize(mResultTextSize);
		mUrlMessageResult.setText(matches);
		
	}

	private String makeMatch(String line, int lineNum) {
		return(String.format("Line %s: %s%n", line,lineNum));
	}
	

}
