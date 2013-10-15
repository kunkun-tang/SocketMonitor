package org.liang.SocketMonitor;


/**
 * 
 * @author liang
 *
 */
 
public class MachineCommand extends Command{
	//public HeartbeatCommand() {}
	
	private int srcID;

	public MachineCommand( int port, String hn, int a_id) {
		super.SetHostName(hn);
		super.SetPort(port);
		super.SetType(cmdType.INIT);
		srcID = a_id;
	}

	public int GetID() {
		return this.srcID;
	}
	
	@Override
	public void PrintCommand() {
		System.out.println("HostName: "+ super.GetHostName() + " Port: "+super.GetPort() + " Type: INIT"
				+ " id: " + this.srcID);
	}
}
