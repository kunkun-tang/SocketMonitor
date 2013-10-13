package org.liang.SocketMonitor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author liang 
 * The server socket listener
 */
public class ServerSocketListener implements Runnable{
	private final int POOL_SIZE = 10;
	
	private int _port;
	
	private ExecutorService _executorService;
	
	public ServerSocketListener(int port) {
		this._port = port;
		this._executorService = Executors.newFixedThreadPool(POOL_SIZE);
	}
	
	@Override
	public void run() {
		ServerSocket serversck = null;
		boolean listen = true;
		try {
			System.out.println("Server is running...");
			System.out.println("Waiting for connection...");
			serversck = new ServerSocket(this._port);
			while(listen) {
				Socket socket = serversck.accept();
				_executorService.execute(new ServerSocketHandler(socket));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				serversck.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
