/*---------------------------------------
 Genuine author: <name>, I.D.: <id number>
 Date: xx-xx-2020 
---------------------------------------*/
import java.util.Comparator;

public class AccountComparatorByName implements Comparator<BankAccount>{

	@Override
	public int compare(BankAccount account1, BankAccount account2) {//return 0/1/-1 if accountName1 is same/bigger/smaller then accountName2
		// task 2a
		return account1.getName().compareTo(account2.getName());//using copareTo in java
	}

}
