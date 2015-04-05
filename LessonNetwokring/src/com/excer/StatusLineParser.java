package com.excer;

public class StatusLineParser {


	private String mHttpVersion, mMessage;
	private int mStatusCode;

	public StatusLineParser(String statusLine) {
		String[] entries = statusLine.split("\\s+", 3);
		try{
			mHttpVersion = entries[0];
			mStatusCode = Integer.parseInt(entries[1]);
			mMessage = entries[2];
		}catch(Exception e){
			mStatusCode = -1;
			mMessage = "Unknown(Bad Status Line)";
		}
	}


	@Override
	public String toString() {
		return (String.format("HTTP Version: '%s', Status Code:'%s', Message: '%s'", 
				mHttpVersion, mStatusCode, mMessage));
	}

	public String getmHttpVersion() {
		return mHttpVersion;
	}
	public String getmMessage() {
		return mMessage;
	}
	public int getmStatusCode() {
		return mStatusCode;
	}
	public boolean isGood() {
		return((mStatusCode >= 200) && (mStatusCode  <= 299));
	}

	public boolean isForwarded() {
		return((mStatusCode == 301) || (mStatusCode  == 302));
	}

	public boolean isBad() {
		return(!(isGood() || isForwarded()));
	}



}
