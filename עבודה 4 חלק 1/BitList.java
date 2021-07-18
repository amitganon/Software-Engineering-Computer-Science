
import java.util.LinkedList;
import java.util.Iterator;

public class BitList extends LinkedList<Bit> {
    private int numberOfOnes;

    // Do not change the constructor
    public BitList() {
        numberOfOnes = 0;
    }

    // Do not change the method
    public int getNumberOfOnes() {
        return numberOfOnes;
    }


//=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.1 ================================================

    public void addLast(Bit element) {//add bit to the end of the linkedList
    	if (element==null)//check for null
    		throw new IllegalArgumentException("Can't insert null");
    	if (element.toInt()==1)
    	   numberOfOnes=numberOfOnes+1;//update numberOfOnes
    	super.addLast(element);
    }

    public void addFirst(Bit element) {//add bit to the start of the linkedList
    	if (element==null)//check for null
    		throw new IllegalArgumentException("Can't insert null");
    	if (element.toInt()==1)
    	   numberOfOnes=numberOfOnes+1;//update numberOfOnes
    	super.addFirst(element);
    }

    public Bit removeLast() {//remove bit from the end of the linkedList
    	if (super.size()==0)
    		throw new IllegalArgumentException("Can't remove in empty BitList");
    	Bit bitRemove=super.removeLast();
        if(bitRemove.toInt()==1 & numberOfOnes>=1)
        	numberOfOnes=numberOfOnes-1;//update numberOfOnes
        return bitRemove;
    }

    public Bit removeFirst() {//remove bit from the start of the linkedList
    	if (super.size()==0)
    		throw new IllegalArgumentException("Can't remove in empty BitList");
    	Bit bitRemove=super.removeFirst();
        if(bitRemove.toInt()==1 & numberOfOnes>=1)
        	numberOfOnes=numberOfOnes-1;//update numberOfOnes
        return bitRemove;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.2 ================================================
    public String toString() { //making toString for BItList
        String str="";
        Iterator<Bit> iter= super.iterator();//using iterator for going throw the linkedlist
        while(iter.hasNext())
        	str=iter.next()+str;
        return "<"+str+">";
    }
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.3 ================================================
    public BitList(BitList other) {//copy BitList;
    	if (other==null)//if null throw exception
    		throw new IllegalArgumentException("Can't copy null");
        Iterator<Bit> iter=other.iterator();//using iterator for going throw other linkedlist
        while(iter.hasNext())
        	 addLast(iter.next());
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.4 ================================================
    public boolean isNumber() {//check if it a number
        boolean ans=false;
        if (super.size()>0) {//check first condition: size>0
        	int countOne=0;
        	Iterator <Bit> iter =super.iterator();
        	int check=-1;
        	while(iter.hasNext() & countOne<=1) {
        		check=iter.next().toInt();
        		if (check==1)
        			countOne=countOne+1;
        	}
    		if(check==0 | countOne>1)//check second condition: or LSB=0 or at list two ones
    			ans=true;
        }
        return ans;
    }
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.5 ================================================
    public boolean isReduced() {//return if the BitList is reduced or not
        boolean ans=true;
    	if (!isNumber())
        	ans=false;
    	else{
		   Iterator <Bit> iter=super.iterator();
		   int countOne=0;
		   int secElement=-1;
		   int lastElement=-1;
		   while(iter.hasNext() & ans) {//using iterator to go throw the BitList, and get the last two Bits value
		     	secElement=lastElement;
		        lastElement=iter.next().toInt();
		    	if(lastElement==1)
		       	countOne=countOne+1;
	       }
	       if(super.size()<3) {
		      if(!(lastElement == 0 & secElement==-1 | lastElement == 0 & secElement==1 | lastElement == 1 & secElement==1))//check first condition
			    ans=false;
		   }
	       else {
		      if(!((secElement==1 & lastElement==0) | (secElement==0 & lastElement==1) | (secElement==1 & lastElement==1 & countOne==2)))//check condition 2,3
			    ans=false;
     	   }
	    }
        return ans;
    }

    public void reduce() {//reducing the BitList 
        if (!isNumber())//check the BitList represent a number
        	throw new IllegalArgumentException("the BitList is not a llegal number");
        while(!isReduced())
        	removeLast();
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.6 ================================================
    public BitList complement() {//Switch all the BitList to is negative
    	BitList newBit=new BitList();
    	Iterator <Bit> iter=super.iterator();
    	while(iter.hasNext()) {
    		newBit.addLast(iter.next().negate());//copy the BitLitst to the new one but using negate
    	}
    	return newBit;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.7 ================================================
    public Bit shiftRight() {//remove and return the first bit if it exist
    	Bit b1=new Bit(true);
        if (super.size()==0)//checking there is a Bit to remove
        	b1=null;
        else {
        	b1=super.getFirst();
        	removeFirst();
        }        
        return b1;
        
    }

    public void shiftLeft() {//add to first index Bit 0 to the BitList
        addFirst(Bit.ZERO);
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.8 ================================================
    public void padding(int newLength) {//padding the BitList by multiplying the last Bit
        if(super.size()==0)
        	throw new IllegalArgumentException("can't padding BitList without elements");
    	if (newLength>super.size()) {
        	for (int i=super.size(); i<newLength;i++) {//multiplying the last Bit value
        		if(super.getLast().toInt()==0)//using getLast to get the last value
        			addLast(Bit.ZERO);
        		else
        			addLast(Bit.ONE);
        	}
        }
    }

    

    //----------------------------------------------------------------------------------------------------------
    // The following overriding methods must not be changed.
    //----------------------------------------------------------------------------------------------------------
    public boolean add(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public void add(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit remove(int index) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offer(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerFirst(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerLast(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit set(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Do not use this method!");
    }
}
