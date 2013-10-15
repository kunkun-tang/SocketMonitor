
package org.liang.SocketMonitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.LinkedList;
import java.util.Properties;
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
	private int ID;
	
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
	
	public ServerManager(String hn, String p, String a_id) throws IOException {
		this._hostName = hn;
		this._port = Integer.parseInt(p);
		this.ID = Integer.parseInt(a_id);
	}
	
	public int getPort() {
		return this._port;
	}

	public int getID() {
		return this.ID;
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
		//if(input.split(" ").length != 3)
			//System.out.println("Usage: grep localhost:8080  pattern");
		//else {
			//String DestHostAndPort = input.split(" ")[1];

			//String desHost = DestHostAndPort.split(":")[0];
			//int desPort = Integer.parseInt(DestHostAndPort.split(":")[1]);

			//GrepCommand aGrep = new GrepCommand(desPort, desHost, input.split(" ")[2]);

			//CommandSender cmdSender = new CommandSender(aGrep);
			//Thread send = new Thread(cmdSender);
			//send.start();
		//}
	}

	public void tryConnectAll() throws FileNotFoundException, IOException{

		Command aCommand = new MachineCommand(getPort(), getHostname(), getID());
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("./liang.prop");
		prop.load(fis);

		String[] addresses = prop.getProperty("machines.address").split(",");
		//Create and start a list of client threads. Each thread takes server address and port as input.
		LinkedList<Thread> clientThreads = new LinkedList<Thread>();
		for (int i = 0; i < addresses.length; i++) {
			String[] hostPort = addresses[i].split(":");
			String address = hostPort[0];
			if(Integer.parseInt(hostPort[1]) == getPort()) continue;

			CommandSender sendCmd = new CommandSender(address,
					Integer.parseInt(hostPort[1]), aCommand);
			sendCmd.PrintCommand();

			Thread newThread = new Thread(sendCmd);
			newThread.start();
		clientThreads.add(newThread);
		}	

	}	
	
	public static void main(String[] arg) throws IOException, InterruptedException {
		if(arg == null || arg.length != 3) {
			System.err.println("Usage: ManagerServer hostname porti id");
		}
		ServerManager SM = new ServerManager(arg[0], arg[1], arg[2]);
		
		Thread socketListener = new Thread(new ServerSocketListener(SM.getPort()));
		socketListener.start();
		SM.tryConnectAll();

		SM.ConsoleHandler();
	}
}
