package com.excer;

import java.net.*;
import java.io.*;

/*
 * gets BufferReader and WriterReader for Socket	
 */
public class SocketUtils {

	public static BufferedReader getReader(Socket s) throws IOException{
	
		return (new BufferedReader(new InputStreamReader(s.getInputStream())));
	}
	
	public static PrintWriter getWriter(Socket s) throws IOException{
		return (new PrintWriter(s.getOutputStream(),true));
		
	}
}
