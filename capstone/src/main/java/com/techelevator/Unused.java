package com.techelevator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Unused {

	File logFile = new File("/tmp/Log.txt");
	logFile.createNewFile();
	
	
	public static void addToLog(String type, BigDecimal amount, BigDecimal transactionBalance) throws IOException {

		final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(timestamp);

		try (FileWriter fw = new FileWriter(logFile, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter writer = new PrintWriter(bw);) {

			writer.println(timestamp + type + amount + transactionBalance);

		} catch (IOException ex) {
			// Do something in case there's an error
		}

}
}
