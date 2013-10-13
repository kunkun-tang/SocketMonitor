package org.liang.SocketMonitor;


/**
 * 
 * @author liang
 *
 */
 

public class GrepCommand extends Command{
	//public HeartbeatCommand() {}
	
	private String argu;
	
	public GrepCommand( int port, String hn, String a_argu) {
		super.SetHostName(hn);
		super.SetPort(port);
		super.SetType(cmdType.GREP);
		this.argu = a_argu;
	}

	public String GetArgument() {
		return this.argu;
	}
	
	@Override
	public void PrintCommand() {
		System.out.println("HostName: "+ super.GetHostName() + " Port: "+super.GetPort() + " Type: Grep"
				+ " Arguments: " + this.argu);
	}
}
