package com.excer;

import java.util.*;

public class UrlParser {

	private String mHost, mUri, mProtocol;
	private int mPort = 80;
	
	public UrlParser(String url){

		StringTokenizer tok = new StringTokenizer(url);
		mProtocol = tok.nextToken(":");
	
		if(isHttp()){
			mHost= tok.nextToken(":/");
			
			try{
				mUri= tok.nextToken("");
				if(mUri.charAt(0)==';'){
					tok = new StringTokenizer(mUri);
					mPort = Integer.parseInt(tok.nextToken(":/"));
					mUri = tok.nextToken("");
							
				}
			}catch(NoSuchElementException nsee){
				mUri = "/";
			}
			
		}
		
		
	}
	

	private boolean isHttp() {
		return ("http".equals(mProtocol));
	}


	public String getmHost() {
		return mHost;
	}


	public String getmUri() {
		return mUri;
	}


	public String getmProtocol() {
		return mProtocol;
	}


	public int getmPort() {
		return mPort;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}
