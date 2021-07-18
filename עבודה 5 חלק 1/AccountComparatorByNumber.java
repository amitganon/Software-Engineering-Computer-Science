/*---------------------------------------
 Genuine author: <name>, I.D.: <id number>
 Date: xx-xx-2020 
---------------------------------------*/
import java.util.Comparator;

public class AccountComparatorByNumber implements Comparator<BankAccount>{

	@Override
	public int compare(BankAccount account1, BankAccount account2) {//return 0/1/-1 if accountNumber1 is same/bigger/smaller then accountNumber2
		// task 2b
		return ((Integer) account1.getAccountNumber()).compareTo((Integer)account2.getAccountNumber());//for using copareTo in java I casting to Integer
	}

}
