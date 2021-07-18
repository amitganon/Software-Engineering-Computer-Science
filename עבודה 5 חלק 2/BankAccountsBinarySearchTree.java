/*---------------------------------------
 Genuine author: <name>, I.D.: <id number>
 Date: xx-xx-2020 
---------------------------------------*/
import java.util.Comparator;
import java.util.Iterator;

public class BankAccountsBinarySearchTree extends BinarySearchTree<BankAccount>{

	public BankAccountsBinarySearchTree(Comparator<BankAccount> myComparator) {
		super(myComparator);
	}
	
	// Complete the following methods
	public void balance(){//building balance tree, using buildBalancedTree
		// task 5b
		List <BankAccount> listCounts=new DynamicArray <BankAccount>();
	    Iterator <BankAccount> iter = this.iterator();
	    while(iter.hasNext()) {//put tree nodes in list, and empty the tree
	    	BankAccount temp=iter.next();
	    	listCounts.add(temp);
	    	this.remove(temp);
	    }
		buildBalancedTree(listCounts, 0,listCounts.size());
	}
	
	private void buildBalancedTree(List<BankAccount> list, int low, int high){//building balance tree
		// task 5b
		if(low<=high) {
			int mid=(high+low)/2;
			if(mid<list.size())
			   this.insert(list.get(mid));//insert node by node, using the middle of the list
			if(mid >low)
				buildBalancedTree(list,low,mid-1);
			if(mid<high)
				buildBalancedTree(list,mid+1,high);
		}
	}
}
