/*---------------------------------------
 Genuine author: <name>, I.D.: <id number>
 Date: xx-xx-2020 
---------------------------------------*/
import java.util.Iterator;

public class PrimeIterator implements Iterator<Integer> {

    private List<Integer> primes;
   
	//Complete the following methods
    public PrimeIterator(){
    	// task 0
		primes=new DynamicArray<Integer>();//chose to use DynamicArray
		primes.add(2);//adding the first prime
    }

    public boolean hasNext(){
    	// task 0
    	if(primes.get(primes.size()-1)==0)
    		return false;
    	return true;
        
    }

    public Integer next(){//return the next prime number
    	// task 0
        Integer nextPrime=primes.get(primes.size()-1);//returning the prime
        boolean finedPrime=false;
        int newPrime=nextPrime+1;
        while(newPrime>1 & !finedPrime) {//get ready for next prime
        	Iterator <Integer> iter=primes.iterator();
        	boolean checkIfPrime=true;
        	Integer primesTempNum=iter.next();
        	while(checkIfPrime & primesTempNum*primesTempNum<=newPrime) {//check if the number is prime
        		if(newPrime%primesTempNum==0)
        			checkIfPrime=false;
        		primesTempNum=iter.next();
        	}
        	if(checkIfPrime) {//if the number is prime insert it to primes
        		finedPrime=true;
        		primes.add(newPrime);
        	}
        	else
        		newPrime=newPrime+1;
        }
        if(newPrime<0)//add 0 to know when the int limit
        	primes.add(0);
        return nextPrime;

    }
}
