package org.liang.SocketMonitor;

import org.liang.protoc.MachineBookProtos.Machine;
import org.liang.protoc.MachineBookProtos.MachineBook;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;

/**
 * 
 * @author liang
 *
 */
public class CommandSender implements Runnable{
	public int _desPort;
	public String _desHostName;
	public Command _cmd;
	private Socket socket = null;
	
	public void PrintCommand() {
		System.out.println("destHostName: "+ this._desHostName + " Port: "+this._desPort);
	}

	static Machine convert( Command cmd ) throws IOException {
		Machine.Builder machine = Machine.newBuilder();

		if(cmd.GetType() == Command.cmdType.INIT){
			MachineCommand MC = (MachineCommand) cmd;
			machine.setId(MC.GetID());
			machine.setHostName(MC.GetHostName());
			machine.setPort(MC.GetPort());
		}
		return machine.build();
	}


	public CommandSender() {
		
	}
	
	public CommandSender(String address, int port, Command c) {
		this._desHostName = address;
		this._desPort = port;
		this._cmd = c;
		//System.out.println("******* "+this._desHostName+" "+this._desPort+" "+this._cmd.GetType());
	}
	
	@Override
	public void run() {	

		boolean exit = false;
		try {
			socket = new Socket("localhost", this._desPort);
		} catch (UnknownHostException e) {
			//e.printStackTrace();
			System.out.println(_desPort+" has not been initialized.");
			exit = true;

		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println(_desPort+" has not been initialized.");
			exit = true;
		}finally{
			if(exit == true)
			return ;
		} 

		if(socket == null) {
				
			System.out.println(" socket = null.");
			return ;
		}

		MachineBook.Builder machineBook = MachineBook.newBuilder();
   
		try {
			machineBook.addMachine( convert( _cmd )) ;

			while(true){
				OutputStream OS = socket.getOutputStream();
				machineBook.build().writeTo(OS);

				Thread.currentThread().sleep(5000);
				System.out.println(" constanly sending messages. ");
				OS.flush();

			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			if(socket != null)
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
