package org.liang;

import org.liang.protoc.MachineBookProtos.Machine;
import org.liang.protoc.MachineBookProtos.MachineBook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;

class ReadMachine{
	  // Iterates though all people in the AddressBook and prints info about them.

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

	// Main function:  Reads the entire address book from a file and prints all
	//   the information inside.
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("Usage:  Readmachine Machine_BOOK_FILE");
			System.exit(-1);
		}

		// Read the existing address book.
		MachineBook addressBook =
			MachineBook.parseFrom(new FileInputStream(args[0]));

		Print(addressBook);
	}
}
