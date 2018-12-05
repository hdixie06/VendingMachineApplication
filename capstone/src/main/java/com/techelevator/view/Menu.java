package com.techelevator.view;

//import java.io.File;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.math.BigDecimal;
//import java.sql.Timestamp;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.techelevator.Machine;
import com.techelevator.MoneyUtils;

///////////NOTES//////////////
//clean up unused imports

//add lots of comments, maybe some tool tips
//when asking for whole dollar amount input in menu, figure out a way to cut off any decimal points and the values after it
//unit tests

//add location to purchase on logfile

//////////////////////////

public class Menu {

	public static void main(String[] args) throws IOException, InterruptedException {

		Machine m = new Machine();
		String userInput;
		boolean subMenuDone = false;
		boolean done = false;
		boolean mainMenuDone = false;
		try (Scanner inputReader = new Scanner(System.in);) {
			m.stock();

			while (!done) {
				System.out.println("\n****************\nVendo-Matic 500\n****************" + "\n");
				done = true;
				mainMenuDone = false;
				while (!mainMenuDone) {

					System.out.println("Please choose from the following options: " + "\n"
							+ "(1) Display Vending Machine Items\n" + "(2) Purchase");

					String selection = inputReader.nextLine();
					if (selection.equals("1")) {
						System.out.println(m.displayItems());

						mainMenuDone = false;
					} else if (selection.equals("2")) {

						mainMenuDone = true;
						subMenuDone = false;

					} else if (selection.equals("3")) {

						System.out.println("Please enter password:");

						String enteredPassword = inputReader.nextLine().toUpperCase();

						if (enteredPassword.equals("PASSWORD")) {

							m.generateReport();
							m.setMachineBalance(new BigDecimal(0));
							m.stock();

							System.out.println(
									"Sales report generated. Rebooting and Restocking. Machine balance reset to zero.");
							String reboot = ".";
							for (int i = 0; i < 10; i++) {
								System.out.print(reboot);
								TimeUnit.SECONDS.sleep(1);
								reboot += ".";
							}
							done = false;
							subMenuDone = true;
							mainMenuDone = true;

						} else {
							subMenuDone = false;
							mainMenuDone = false;
						}
					}

					//
				}

				while (!subMenuDone) {

					System.out.println("(1) Feed Money");
					System.out.println("(2) Select Product");
					System.out.println("(3) Finish Transaction");
					// System.out.println("(4) Get sales report");
					userInput = inputReader.nextLine();

					if (userInput.equals("1")) {
						System.out.println("Please enter a whole dollar amount");
						userInput = inputReader.nextLine();
						int moneyEntered = Integer.parseInt(userInput);
						if (moneyEntered >= 0) {
							m.addMoney(moneyEntered);
							subMenuDone = false;

						}
					} else if (userInput.equals("2")) {
						System.out.println(m.displayItems());
						System.out.println("Please enter your item selection: ");
						String selectedItem = inputReader.nextLine().toUpperCase();
						if (m.getStock().containsKey(selectedItem)) {
							System.out.println(m.selectItem(selectedItem));
						} else {
							System.out.println("Invalid selection. Please try again");
						}

					} else if (userInput.equals("3")) {

						// give back change AND set transaction balance to zero
						String change = MoneyUtils.getChange(m.getTransactionBalance());
						System.out.println(change);

						System.out.println("Transaction completed! Enjoy!");
						System.out.println(m.getSounds());

						// empties the users transaction list, adds items to the total sales list
						m.finishTransaction();

						done = false;
						subMenuDone = true;

					} else {

						subMenuDone = false;
					}

				}

			}
		}
	}
}
