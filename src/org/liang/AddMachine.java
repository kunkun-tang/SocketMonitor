package org.liang;

import org.liang.protoc.MachineBookProtos.Machine;
import org.liang.protoc.MachineBookProtos.MachineBook;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;

public class AddMachine{
  // This function fills in a Person message based on user input.
	static Machine PromptForAddress( PrintStream stdout ) throws IOException {
		Machine.Builder machine = Machine.newBuilder();

		machine.setId(9527);

		machine.setHostName("localhost");

		machine.setPort(7890);

		return machine.build();
	}

  //// Main function:  Reads the entire address book from a file,
  ////   adds one person based on user input, then writes it back out to the same
  ////   file.
  public static void main(String[] args) throws Exception {
	if (args.length != 1) {
	  System.err.println("Usage:  AddMachine Machine_BOOK_FILE");
	  System.exit(-1);
	}

	MachineBook.Builder machineBook = MachineBook.newBuilder();

	// Read the existing address book.
	//try {
	  //machineBook.mergeFrom(new FileInputStream(args[0]));
	//} catch (FileNotFoundException e) {
	  //System.out.println(args[0] + ": File not found.  Creating a new file.");
	//}

	// Add an address.
	machineBook.addMachine(
	  PromptForAddress( System.out )) ;

	// Write the new address book back to disk.
	FileOutputStream output = new FileOutputStream(args[0]);
	machineBook.build().writeTo(output);
	output.close();
  }
}
