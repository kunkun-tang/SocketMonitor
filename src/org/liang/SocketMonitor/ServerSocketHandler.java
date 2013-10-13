package org.liang.SocketMonitor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
/**
 * 
 * @author liang 
 * The server setup a listener to hear the heartbeat from clients.
 * server will generate a handler to handle what it received.
 */

public class ServerSocketHandler implements Runnable{
	private Socket _socket;
	
	public static Integer ClientListMutex = 0;
	
	public ServerSocketHandler(Socket socket){ this._socket = socket;}

	@Override
	public void run() {		
		try {
            System.out.println("New connection accepted: "+_socket.getInetAddress()+":"+_socket.getPort());
            ObjectInputStream br = new ObjectInputStream(_socket.getInputStream());
         //   PrintWriter pw = new PrintWriter(_socket.getOutputStream(),true);
            Command grep=null;
            if(( grep=(Command)br.readObject())!=null){
				grep.PrintCommand();
                //String key = hrtbtcmd.GetHostName() + ":" + String.valueOf(hrtbtcmd.GetPort());
                //synchronized(ClientListMutex) {
					//if(!ProcessManagerServer.GetClientList().containsKey(key) || 
							//ProcessManagerServer.GetClientList().get(key) != hrtbtcmd.GetWorkload()) {
						//ProcessManagerServer.GetClientList().put(key, hrtbtcmd.GetWorkload());
					//} 
                //}

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
            try {
                if(_socket!=null)
                    _socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
	}
}
