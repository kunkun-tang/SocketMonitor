package org.liang.SocketMonitor;

import org.liang.protoc.MachineBookProtos.Machine;
import org.liang.protoc.MachineBookProtos.MachineBook;
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

	static void Print(MachineBook machineBook) {
		for (Machine machine: machineBook.getMachineList()) {
			System.out.println("Machine ID: " + machine.getId());
			System.out.println(" hostName: " + machine.getHostName());
			System.out.println(" port : " + machine.getPort());
			//if (person.hasEmail()) {
				//System.out.println("  E-mail address: " + person.getEmail());
			//}

		}
	}

	@Override
	public void run() {		
		try {
            System.out.println("New connection accepted: "+_socket.getInetAddress()+":"+_socket.getPort());
            //ObjectInputStream br = new ObjectInputStream(_socket.getInputStream());
         //   PrintWriter pw = new PrintWriter(_socket.getOutputStream(),true);
		
			while(true){ 
				MachineBook addressBook = MachineBook.parseFrom(_socket.getInputStream());

				System.out.println("constanly receiving messages ");
				if( addressBook != null){
					Print(addressBook);
					//String key = hrtbtcmd.GetHostName() + ":" + String.valueOf(hrtbtcmd.GetPort());
					//synchronized(ClientListMutex) {
					//if(!ProcessManagerServer.GetClientList().containsKey(key) || 
					//ProcessManagerServer.GetClientList().get(key) != hrtbtcmd.GetWorkload()) {
					//ProcessManagerServer.GetClientList().put(key, hrtbtcmd.GetWorkload());
					//} 

				}
				else return;
			}
        } catch (IOException e) {
            e.printStackTrace();
        //} catch (ClassNotFoundException e) {
			//// TODO Auto-generated catch block
			//e.printStackTrace();
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
