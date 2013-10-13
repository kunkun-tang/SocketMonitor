
package org.liang.SocketMonitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author liang
 *The ServerManager setup as a server, which connect to every client.
 *The server provides three thread: 1. main thread handles console input, 2. Listen to the heartbeat from clients
 */
public class ServerManager{

	private static Map<String, Integer> clientList = new HashMap<String, Integer>();
	private int _port;
	
	private String _hostName;
	
	//Commands from console input
	private String GREP = "ls";
	
	private String PS = "ps";
	
	private String RUN = "run";
	
	private String MIG = "migrate";

	//The list of clients
	public static Map<String, Integer> GetClientList() {
		return clientList;
	}
	
	public ServerManager() {}
	
	public ServerManager(String hn, String p) throws IOException {
		this._hostName = hn;
		this._port = Integer.parseInt(p);
	}
	
	public int GetPort() {
		return this._port;
	}

	public String getHostname(){
		return this._hostName;	
	}	
	
	public void ConsoleHandler() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input;
		System.out.print("==>");
		while((input = in.readLine())!=null) {
			if(input.split(" ")[0].toLowerCase().equals("grep")) 
				GREPCommand(input);
			else 
				System.out.println("Command not found");
			
			//System.out.println(s);
			System.out.print("==>");
		}
	}
	

	public boolean isNumeric(String str){
		   for(int i=str.length();--i>=0;){
		      int chr=str.charAt(i);
		      if(chr<48 || chr>57)
		         return false;
		   }
		   return true;
		} 
	
	/*Send migrate process command to Clients
	 * migrate (src)localhost:10005 (des)localhost:10004 1
	 */
	/* List the clients connecting to server
	 * ls
	 */
	public void GREPCommand(String input) {
		if(input.split(" ").length != 3)
			System.out.println("Usage: grep localhost:8080  pattern");
		else {
			String DestHostAndPort = input.split(" ")[1];

			String desHost = DestHostAndPort.split(":")[0];
			int desPort = Integer.parseInt(DestHostAndPort.split(":")[1]);

			GrepCommand aGrep = new GrepCommand(desPort, desHost, input.split(" ")[2]);

			CommandSender cmdSender = new CommandSender(aGrep);
			Thread send = new Thread(cmdSender);
			send.start();
		}
	}
	
	
	public static void main(String[] arg) throws IOException, InterruptedException {
		if(arg == null || arg.length != 2) {
			System.err.println("Usage: ProcessManagerServer hostname port");
		}
		ServerManager SM = new ServerManager(arg[0], arg[1]);
		
		Thread socketListener = new Thread(new ServerSocketListener(SM.GetPort()));
		socketListener.start();

		SM.ConsoleHandler();

	}
}
