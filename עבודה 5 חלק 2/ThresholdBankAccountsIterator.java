import java.util.Iterator;
import java.util.NoSuchElementException;


public class ThresholdBankAccountsIterator implements Iterator<BankAccount> {

	private BinaryTreeInOrderIterator<BankAccount> iterator;
    private BankAccount current;
    private int balanceThreshold;
    
    public ThresholdBankAccountsIterator(BankAccountsBinarySearchTree bankAccountsTree, int balanceThreshold) {
        // task 5c
    	this.balanceThreshold=balanceThreshold;
    	this.current=null;
    	this.iterator=new BinaryTreeInOrderIterator<BankAccount>(bankAccountsTree.root);
    }

    //Complete the following method
    @Override
    public boolean hasNext() {//check for the next account, and return true if fined one
        // task 5c
        boolean fined=false;
        while(iterator.hasNext() & !fined) {
        	BankAccount tempNext=iterator.next();
        	if(tempNext.getBalance()>=balanceThreshold) {
        		fined=true;
        		current=tempNext;//change current to be the account we found
        	}
        }
        return fined;
    }

    //Complete the following method
    @Override
    public BankAccount next() {//return the next account if there is one
        // task 5c
        if(current==null)
        	throw new NoSuchElementException();
        return current;
    }
}
