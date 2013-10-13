package org.liang.SocketMonitor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * 
 * @author liang
 *
 */
 
public class CommandSender implements Runnable{
	private int _desPort;
	private String _desHostName;
	private Command _cmd;
	private Socket socket = null;
	
	public CommandSender() {
		
	}
	
	public CommandSender(Command c) {
		this._desHostName = c.GetHostName();
		this._desPort = c.GetPort();
		this._cmd = c;
		//System.out.println("******* "+this._desHostName+" "+this._desPort+" "+this._cmd.GetType());
	}
	
	@Override
	public void run() {	
		try {
			socket = new Socket("localhost", this._desPort);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
        ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
					
				out.writeObject(this._cmd);
				out.flush();
				//pw.println("this is client "+ Thread.currentThread().getName());
				//Thread.currentThread().sleep(5000);
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			if(socket != null)
				try {
					out.close();
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
