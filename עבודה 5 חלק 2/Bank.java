/*---------------------------------------
 Genuine author: <Amit Ganon>, I.D.: <314770629>
 Date: 02-01-2020
 version 1.0.0  
---------------------------------------*/
public class Bank {

	private BankAccountsBinarySearchTree namesTree;
	private BankAccountsBinarySearchTree accountNumbersTree;
	
	public Bank() {
		namesTree = new BankAccountsBinarySearchTree(new AccountComparatorByName());
		accountNumbersTree = new BankAccountsBinarySearchTree(new AccountComparatorByNumber());
	}

	public BankAccount lookUp(String name){
		// create an Entry with the given name, a "dummy" accountNumber (1) and zero balance
		// This "dummy" accountNumber will be ignored when executing getData
		BankAccount lookFor = new BankAccount(name, 1, 0);
		return (BankAccount)namesTree.findData(lookFor);
	}
	
	public BankAccount lookUp(int accountNumber){
		// create an Entry with a "dummy" name, zero balance and the given accountNumber
		// This "dummy" name will be ignored when executing getData
		BankAccount lookFor = new BankAccount("dummy", accountNumber,0);
		return (BankAccount)accountNumbersTree.findData(lookFor);
	}
	
	// END OF Given code -----------------------------------
	
	// Complete the methods from here on

	public boolean add(BankAccount newAccount) {
		// task 6a
		/*
		 * add an account if not exist
		 * @param newAccount, the account that the algorithm will add
		 * @return true if account added and false if not
		 */
		boolean added=false;
		if(lookUp(newAccount.getName())==null & lookUp(newAccount.getAccountNumber())==null) {//check if account already exist 
			added=true;
			namesTree.insert(newAccount);
			accountNumbersTree.insert(newAccount);
		}
		return added;
	}

	public boolean delete(String name){
		// this first line is given in the assignment file
		BankAccount toRemove = lookUp(name);
		// complete this:

		// task 6b
		/*
		 * delete an account from the bank by account name
		 * @param name, the name of the account that will delete 
		 * @return true if account deleted and false if not
		 */
		boolean deleted=false;
		if(toRemove!=null) {//check if there is an account with this name
			deleted=true;
			namesTree.remove(toRemove);//remove the account from both trees
			accountNumbersTree.remove(toRemove);
		}
		return deleted;
	}
	
	public boolean delete(int accountNumber) {
		// this first line is given in the assignment file
		BankAccount toRemove = lookUp(accountNumber);
		// complete this:

		// task 6c
		/*
		 * delete an account from the bank by account number
		 * @param accountNumber, the number of the account that will delete 
		 * @return true if account deleted and false if not
		 */
		boolean deleted=false;
		if(toRemove!=null) {//check if there is an account with this account number
			deleted=true;
			namesTree.remove(toRemove);//remove the account from both trees
			accountNumbersTree.remove(toRemove);
		}
		return deleted;
	}

	public boolean depositMoney(int amount, int accountNumber){
		// task 6d
		/*
		 * deposit money to the account
		 * @param amount - what the amount of deposit
		 * @param accountNumber - what the number of the account to deposite
		 * @return true if deposited or false if not
		 */
		boolean deposit=false;
		BankAccount account=lookUp(accountNumber);
		if(account!=null) {//check if the account exist or if amount<0
			boolean check = account.depositMoney(amount);//check if deposit succeeded
			if(check)
			   deposit=true;
		}
		return deposit;
	}

	public boolean withdrawMoney(int amount, int accountNumber){
		// task 6e
		/*
		 * withdraw money from the account
		 * @param amount - what the amount of deposit
		 * @param accountNumber - what the number of the account to withdraw
		 * @return true if withdraw or false if not
		 */
		boolean withdraw=false;
		BankAccount account=lookUp(accountNumber);
		if(account!=null & amount>=0) {//check if the account exist or if amount<0
			boolean check =account.withdrawMoney(amount);//check if withdrawMoney succeeded
			if(check)
			   withdraw=true;
		}
		return withdraw;
	}	
}
