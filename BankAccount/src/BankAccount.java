import java.util.Scanner;
	import java.io.File;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.io.PrintWriter;
public class BankAccount {

			public static void main(String[] args) throws IOException  {
				PrintWriter Output = new PrintWriter("Output.txt");//prints out output in a separate file
				Scanner Input = new Scanner(System.in);
				
				//Arrays are Here
				final int MAX_NUM = 10000;//MAx number of accounts
				int [] acctNum = new int[MAX_NUM];//fills array with maximum number of accounts
				double [] balance = new double [MAX_NUM];// balances for the accounts
				
				int numAccts = readAccts(acctNum, balance, MAX_NUM);
				Output.println("Bank Menu Database");
				Output.println("==================");
				printAccts(acctNum, balance, numAccts, Output);//prints accounts
				Output.println("");
				MENU();
				String prompt = Input.next();
				do {//these cases will check for a string variable condition for each case
					switch(prompt) {
				case "W": withdrawal(acctNum, balance, numAccts, Input, Output);
				case "w" : withdrawal(acctNum, balance, numAccts, Input, Output);
					break;
				case "D": Deposit(acctNum, balance, MAX_NUM, Input, Output);
				case "d": Deposit(acctNum, balance, MAX_NUM, Input, Output);
					break;
				case "N": numAccts = newAcct(acctNum, balance,numAccts, Input, Output);
				case "n": numAccts = newAcct(acctNum, balance,numAccts, Input, Output);
					break;
				case "B": Balance(acctNum, balance, MAX_NUM, Input, Output);
				case "b": Balance(acctNum, balance, MAX_NUM, Input, Output);
					break;
				default : System.out.println("Please Try Again.");
					Output.println(prompt+ " is not a valid input");
					System.out.println("Not a valid Input");
				}
				Output.println("Bank Accounts Database");
				Output.println("======================="); 
				
				System.out.println();
				MENU();
				prompt = Input.next();
				}while (!prompt.equals("q") );//prompt does not equal "q". End of Do while loop
				System.out.println("Have a great day");// Message to end Program
			
				Output.println("Processed Bank Data Base");
				Output.println("========================");
				printAccts(acctNum, balance, numAccts, Output);// Prints the accounts and balances
				Output.close();
			}
				public static int readAccts( int[] acctNum, double[] balance, int maxAccts) throws FileNotFoundException {
					Scanner scan = new Scanner(new File("BankAccounts.txt"));
					int i = 0;
					int count = 0;// to declare and initialize count, to get arrays which would be index
					
					while(scan.hasNext()) {//will get the account and balance
						acctNum[i] =scan.nextInt();//scans and takes number of accounts
						balance[i]= scan.nextDouble();//scans and takes number of balances
						count++;
						i++;
					}
					return count;//return the number of accounts
				}// This method reads accounts
			
				public static void MENU() {
					System.out.println("Type a Letter to Access One of the Options below.");
					System.out.println("W-withdrawal\nD-Deposit\nN-New Account \nB-Balance\nQ-Quit");
				}// Menu for Main method
				public static int findAcct(int[] acctNum, int numAccts, int account) {
					
					for (int i = 0; i < numAccts; i++) {
			    		if (account == acctNum[i]) {
			    			return i;
				}
					}
					return  -1;
				}
				public static void withdrawal(int [] acctNum, double[] balance, int numAccts, Scanner Input, PrintWriter Output ) {
					int account;// This declares the account
					int exist;// checks for existing account
					double takeout;//takes out amount types
					double newbalance; // The new balance
					System.out.println("Type The Account Number, To STOP Press -1.");
					account  = Input.nextInt();// Input from user to find account
					do {
						exist  = findAcct(acctNum, numAccts, account);// User's Input is looked from find account method
						if (exist !=-1) { // If "exist" does not equal -1, if statement continues, -1 stops it.
							System.out.println("How much would you like to withdraw?");
							takeout = Input.nextDouble();//amount to withdraw from user
							
						if(balance[exist]>= takeout) {// array that gets the balance
							newbalance = balance[exist] - takeout;// New balance after it is processed
							Output.println("Transaction Type: Withdrawal");
							Output.println("Account Number: " + acctNum[exist]);
							Output.println("Current Balance: " + balance[exist]);
							balance[exist] = newbalance;// this changes the current balance to the brand new balance
							Output.printf("New Balance: $%.2f\n", newbalance);
							Output.println("===================");
							System.out.println();
						} else if (balance[exist] < takeout){// This makes it an error to occur if the amount taken is greater
							Output.println("Transaction Type: Withdrawal");
							Output.println("Account Number: " + acctNum[exist]);
							Output.println("Current Balance: " + balance[exist]);
							Output.println("Amount to WithDraw: $" + takeout);
							Output.printf("Unable to Process. Reason - Insufficient Funds Available\n");
							Output.println("===================");
							System.out.println();
							}// end of nested if statement
						}else {
							Output.println("Unable to find" + account);
						}
						System.out.println("Type in Account Number, -1 to Stop");
						account = Input.nextInt();
					} while(account>0);// account searched, if it is less it will not work
					Output.flush();
				}
				
				public static void Deposit(int [] acctNum, double [] balance, int numAccts, Scanner Input, PrintWriter Output) {
					int Account;
					int Exists;
					double Deposit;
					double newbalance;
					System.out.println("Type Account Number to Deposit, -1 is to End.");
					Account = Input.nextInt();
					do {
						Exists = findAcct(acctNum, numAccts, Account);
						if (Exists != -1) {
							System.out.println("Type Amount to Deposit");
							Deposit = Input.nextDouble();
							newbalance = balance[Exists] + Account;// New account balance 
							Output.println("Transaction: Deposit\nNCompleted");
							Output.println("Account Number: "+ acctNum[Exists]);
							Output.println("Account Balance"+ balance[Exists]);
							Output.println("Amount Deposited " + Account );
							balance[Exists]= newbalance;
							Output.println("New Balance:" + balance[Exists]);
							Output.println("===================");			
						}
						System.out.println(" Type in Account Number to Deposit, -1 to End.");
						Account = Input.nextInt();
					} while (Account>0);//While Loop ends when Account number is less than zero
					Output.flush();
				}// End of Deposit Method
				
				public static int newAcct( int [] acctNum, double [] balance, int numAccts, Scanner Input, PrintWriter Output) throws IOException {
					int Account;
					int Exists;
					int NewAccount = 0;
					System.out.println("Type in new Account, -1 to End.");
					Account = Input.nextInt();
					while(Account>0) {
					Exists = findAcct(acctNum, numAccts, Account);
					
						if (Exists == -1) {
							acctNum[numAccts+NewAccount] = Account;
							balance[numAccts+NewAccount] = 0;
							NewAccount++;
							Output.println("New Account Created: "+ Account);
							Output.println("Balance: $" + 0+"\n");
							Output.println("==================");
							System.out.println();
						}else {
							Output.println(Account + " Already exists\n");
							Output.println("=====================");
						  System.out.println();
						}//end of if statement
						Output.println("================");
						System.out.println();
						System.out.println("Create New Account, -1 to End.");
						Account = Input.nextInt();
					
					}
					 
					Output.flush();
					return NewAccount+ numAccts;	
				}
				
				public static void Balance(int [] acctNum, double [] balance, int numAccts,Scanner Input, PrintWriter Output) {
					int Account;
					int exists;
					System.out.println("Type Account Number, Press -1 to End");
					Account  = Input.nextInt();
					while(Account>0) {
						exists = findAcct(acctNum, numAccts, Account);
						if (exists != -1) {
							Output.println("Account Balance: " + Account+ "\nAvailable: "+ balance[exists]);
							Output.println("=====================");
									
						} else { 
							Output.println("Account "+ Account+ " Does not exist\n");
							Output.println("================");
							System.out.println();
						}// end of if statement 
					
						System.out.println("Type Account Number, Press, -1 to End");
						Account = Input.nextInt();
					}
					Output.flush();
				}
				public static void printAccts(int[] acctNum, double[]  balance, int numAccts, PrintWriter Output) {
				Output.println("Account\t\tBalance");
				for (int i = 0; i < numAccts; i++) {
			
					Output.println(acctNum[i] + "\t\t" + balance[i]);// parallel arrays\
				
				
		}
				
		}
	}


