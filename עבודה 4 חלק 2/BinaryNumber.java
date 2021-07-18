

import java.util.Iterator;

public class BinaryNumber implements Comparable<BinaryNumber>{
    private static final BinaryNumber ZERO = new BinaryNumber(0);
    private static final BinaryNumber ONE = new BinaryNumber(1);

    private BitList bits;

    // Copy constructor
    //Do not chainge this constructor
    public BinaryNumber(BinaryNumber number) {
        bits = new BitList(number.bits);
    }

    //Do not change this constructor
    private BinaryNumber(int i) {
        bits = new BitList();
        bits.addFirst(Bit.ZERO);
        if (i == 1)
            bits.addFirst(Bit.ONE);
        else if (i != 0)
            throw new IllegalArgumentException("This Constructor may only get either zero or one.");
    }

    //Do not change this method
    public int length() {
        return bits.size();
    }

    //Do not change this method
    public boolean isLegal() {
        return bits.isNumber() & bits.isReduced();
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.1 ================================================
    public BinaryNumber(char c) {//make a binary number 0-9
        if(c < '0' | c > '9')
        	throw new IllegalArgumentException("Illegal argument");
        int cNum= c-'0';//making c to be int num 0-9
        bits = new BitList();
        while(cNum>0) {
        	if(cNum %2 == 0)//checking to insert 0 or 1 to the binaryNumber
        		bits.addLast(Bit.ZERO);
        	else
        		bits.addLast(Bit.ONE);
        	cNum=cNum/2;
        }
        bits.addLast(Bit.ZERO);//the number is positive, add 0 to the start
    }

  //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.2 ================================================
    public String toString() {//return the String of BinaryNumber
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
        String str="";
        Iterator<Bit> iter= bits.iterator();//using iterator for going throw the bits
        while(iter.hasNext())
        	str=iter.next()+str;
        return str;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.3 ================================================
    public boolean equals(Object other) {//return if equals or not
        boolean ans=true;
    	if(!(other instanceof BinaryNumber))
        	ans=false;
    	else {
    		BinaryNumber otherBin=(BinaryNumber) other;
    		Iterator<Bit> iterOther=otherBin.bits.iterator();//using iterator to go throw the BitList
    		Iterator<Bit> iter=bits.iterator();
    		while(iter.hasNext() & ans) {
    			if(iterOther.hasNext()) {
    			if(!(iter.next().equals(iterOther.next()))) // check if equals
    				ans=false;
    			}
    			else
    				ans=false;
    		}
    	}
    	return ans;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.4 ================================================
    public BinaryNumber add(BinaryNumber addMe) {//making addition for 2 BinaryNumber
        if (addMe==null)
        	throw new IllegalArgumentException("can't add null BinaryNumber");
        BinaryNumber thisNum=this;
        BinaryNumber addition=new BinaryNumber('0');
        addition.bits.removeFirst();
        int size=0;
        if(thisNum.length()<addMe.length()) {//checking how BinaryNumber is longer to padding them
        	size=addMe.length();
        	thisNum.bits.padding(size+1);
        	addMe.bits.padding(size+1);
        }
        else {
        	size=thisNum.length();
        	thisNum.bits.padding(size+1);
        	addMe.bits.padding(size+1);
        }
        Iterator<Bit> iter=addMe.bits.iterator();
        Iterator<Bit> iterThis=thisNum.bits.iterator();
        int remember=0;
        while(iterThis.hasNext()) {//making the addition using Iterator
        	int tempBit=iter.next().toInt()+iterThis.next().toInt();
        	if(tempBit+remember==0) {//4 options for addition
        		addition.bits.addLast(Bit.ZERO);
        		remember=0;
        	}
        	else if(tempBit+remember==2) {
        		addition.bits.addLast(Bit.ZERO);
        		remember=1;        	
        	}
        	else if (tempBit+remember==1) {
        		addition.bits.addLast(Bit.ONE);
        	    remember=0; 
        	}
        	else {
        		addition.bits.addLast(Bit.ONE);
        	    remember=1;
        	}
        }
        addMe.bits.reduce();//reducing back to legal BinaryNumber
        thisNum.bits.reduce();
        addition.bits.reduce();
        return addition;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.5 ================================================
    public BinaryNumber negate() {//return the negative BinaryNumber
    	BinaryNumber negativeNum=new BinaryNumber(0);
    	negativeNum.bits.removeFirst();
    	int size=this.length();
    	BinaryNumber addOne=new BinaryNumber('1');//making 01
    	Iterator <Bit> iter=this.bits.iterator();
    	while(iter.hasNext()) {//copy the negative BinaryNumber
    		if(iter.next().toInt()==0)
    			negativeNum.bits.addLast(Bit.ONE);
    		else
    			negativeNum.bits.addLast(Bit.ZERO);
    	}
    	negativeNum=negativeNum.add(addOne);//adding the 01
    	if(negativeNum.length()>size)//checking if needs to remove the last Bit
    		negativeNum.bits.removeLast();
    	return negativeNum;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.6 ================================================
    public BinaryNumber subtract(BinaryNumber subtractMe) {//return the subtract of BinaryNumber
        if(subtractMe==null)
        	throw new IllegalArgumentException("cant subtract null or illegal BinaryNumber");
        BinaryNumber ans=new BinaryNumber(0);
        ans=ans.add(this);
        subtractMe=subtractMe.negate();//using negate to subtract 
        ans=ans.add(subtractMe);        
        return ans;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.7 ================================================
    public int signum() {//return 0,-1,1 for 0/negative/positive BinaryNumber
    	int ans=0;
    	BinaryNumber copy=new BinaryNumber(this);//making copy of BinaryNumber for not changing this
    	copy.bits.reduce();
    	int lastBinary=copy.bits.getLast().toInt();//checking the last index
    	if (lastBinary==1)
    		ans=-1;
    	else if(lastBinary==0 & copy.length()==1)
    		ans=0;
    	else
    		ans=1;
    	return ans;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.8 ================================================
    public int compareTo(BinaryNumber other) {//compare this and other BinaryNumber
        if (other==null)
        	throw new IllegalArgumentException("can't copare null argument");
        BinaryNumber copyThis=new BinaryNumber(this);//making copy for not changing the real BinaryNumber
        BinaryNumber copyOther=new BinaryNumber(other);
        copyThis=copyThis.subtract(copyOther);//using subtract to know how BinaryNumber is bigger
        return copyThis.signum();
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.9 ================================================
    public int toInt() {//convert BinaryNumber to int number
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
        BinaryNumber copy=new BinaryNumber(this);
        copy.bits.reduce();//Checking the number is not padding
        int ans=0;
        int pow=-1;
        boolean isNegativeNum=false;
        if(copy.signum()<0) {
        	copy=copy.negate();
        	isNegativeNum=true;
        }
        while(copy.length()>1) {//convert the BinaryNumber to int
        	if(copy.bits.getFirst().equals(Bit.ZERO)) {
        		pow=pow+1;
        		copy.bits.shiftRight();
        	}
        	else {
        		pow=pow+1;
        		ans=ans+ (int) Math.pow(2,pow);
        		if(ans<0 | pow>=31 & !isNegativeNum)//check if the BinaryNumber is not too big or too small
        			throw new RuntimeException("the BinaryNumber is to big or to small");
        		copy.bits.shiftRight();
        	}
        }
        if(isNegativeNum){//return the binary Number to negative
        	ans=-ans;
        	if(pow==31)//Math.pow(2,31) for negative numbers needs -1 
        		ans=ans-1;
        }
        return ans;
    }
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.10 ================================================
    // Do not change this method
    public BinaryNumber multiply(BinaryNumber multiplyMe) {//Multiplying binary Numbers
    	if (multiplyMe==null)
    		throw new IllegalArgumentException("cant multyply null");
    	BinaryNumber copyThis=new BinaryNumber(this);//making copies for not changing the real BinaryNumbers
    	BinaryNumber copyOther=new BinaryNumber(multiplyMe);
    	copyThis.bits.reduce();
    	copyOther.bits.reduce();
    	boolean neg=false;
    	if(this.signum()<0) {//check if the sum should be negative or positive
    		neg=true;
    		copyThis=copyThis.negate();
    	}
    	if(multiplyMe.signum()<0) {
    		copyOther=copyOther.negate();
    		if(neg)
    			neg=false;
    		else
    			neg=true;
    	}
    	BinaryNumber sum=new BinaryNumber('0');
    	sum=copyThis.multiplyPositive(copyOther);//using multiplyPositive
    	if(neg)//multiplyPositive return only positive answers
    		sum=sum.negate();
    	return sum;
    }

    private BinaryNumber multiplyPositive(BinaryNumber multiplyMe) {//Multiplying positive binary Numbers
        Iterator <Bit> iterOther=multiplyMe.bits.iterator();//using iterator to go throw the Binary number
        BinaryNumber sum=new BinaryNumber('0');
        while(iterOther.hasNext()) {
        	Bit next=iterOther.next();
        	if(iterOther.hasNext()) {
            	if(next.equals(Bit.ONE))//when other equals 1, need to add this BinaryNumber
            		sum=sum.add(this);
            	this.bits.shiftLeft();//need shiftLeft this BinaryNumber, like long multiplication
            }
        }
        return sum;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.11 ================================================
    // Do not change this method
    public BinaryNumber divide(BinaryNumber divisor) {//return The ration value of Division
    	if(divisor==null)
    		throw new IllegalArgumentException("cant divide Illegal binaryNumber division");
    	// Do not remove or change the next two lines
    	if (divisor.equals(ZERO)) // Do not change this line
            throw new RuntimeException("Cannot divide by zero."); // Do not change this line
    	//
    	boolean neg=false;
    	BinaryNumber copyThis=new BinaryNumber(this);//making copy for not changing the real BinaryNumbers
    	BinaryNumber copyOther=new BinaryNumber(divisor);
    	if(this.signum()<0) {//check if positive or negative
    		neg=true;
    		copyThis=copyThis.negate();
    	}
    	if(divisor.signum()<0) {
    		copyOther=copyOther.negate();
    		if(neg) 
    			neg=false;
    		else
    			neg=true;
    	}
    	BinaryNumber ans;
    	ans=copyThis.dividePositive(copyOther);//calling dividePositive
    	if(neg)//return to negative number
    		ans=ans.negate();
    	return ans;	
    }

    private BinaryNumber dividePositive(BinaryNumber divisor) {//return The ration value of Division positive BinaryNumbers
    	BinaryNumber sum=new BinaryNumber('0');
    	BinaryNumber temp=new BinaryNumber('0');
    	Iterator <Bit> iterThis=this.bits.descendingIterator();//using iterator to go throw this
    	iterThis.next();
    	while(iterThis.hasNext()) {
    		temp.bits.addFirst(iterThis.next());
    		if(temp.compareTo(divisor)>=0) {//when divisor smaller then temp, use subtract
    			temp=temp.subtract(divisor);
    			sum.bits.addFirst(Bit.ONE);
    		}
    		else
    			sum.bits.addFirst(Bit.ZERO);
    	}
    	sum.bits.reduce();//using reduce to return the smallest Binary number representation
    	return sum;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.12 ================================================
    public BinaryNumber(String s) {//convert from string to binary number
        if(s==null || s.length()==0)
        	throw new IllegalArgumentException("can't creat null/ length 0 - argument");
        BinaryNumber bitsAns=new BinaryNumber('0');
        BinaryNumber bn10=new BinaryNumber('9');
        BinaryNumber bnMulty=new BinaryNumber('1');
        bn10=bn10.add(bnMulty);//bnMulty will be 1/10/100/1000...
        for(int i=s.length()-1;i>=0;i--) {//go throw the string
        	char strChar=s.charAt(i);
        	if(strChar < '0' & strChar!='-' | strChar!='-' & strChar > '9')//check the char
        		throw new IllegalArgumentException("creat only BinaryNumbers");
        	if(strChar=='-' & i==0 & bitsAns.compareTo(new BinaryNumber('0'))!=0)
        		bitsAns=bitsAns.negate();//if - in the and use negate
        	else if (strChar=='-' & i!=0 | strChar=='-' & i==0 & bitsAns.compareTo(new BinaryNumber('0'))==0)//check for extreme case
        		throw new IllegalArgumentException("creat only BinaryNumbers");
        	else {
            	bitsAns=bitsAns.add(new BinaryNumber(strChar).multiply(bnMulty));//using bnMulty, and multiply by 1/10/100...
            	bnMulty=bnMulty.multiply(bn10);
        	}
        }
        bits=bitsAns.bits;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.13 ================================================
    public String toIntString() {//return String for Decimal representation of a BinaryNumber
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
        String bnToStr="0";
        boolean isPos=true;
        if (this.signum()==-1) //checking if the BinaryNumber is positive or negative
        	isPos=false;
        int pow=-1;
        Iterator <Bit> iter=this.bits.iterator();//using iterator to go throw the BinaryNumber
        while(iter.hasNext()) {
        	pow=pow+1;
        	String tempStr;
        	int zo=iter.next().toInt();
        	if(zo==1 & isPos) {//if the BinaryNumber is positive, do the String addition only when Bit is 1
        		tempStr=exponent(2,pow);
        		bnToStr=addition(bnToStr,tempStr);
        	}
        	else if(zo==0 & !isPos) {//if the BinaryNumber is negative, do the String addition only when Bit is 0
        		tempStr=exponent(2,pow);
        		bnToStr=addition(bnToStr,tempStr);
        	}
        }
        if(!isPos) {//covert back from positive to negative
        	bnToStr=addition(bnToStr,"1");
        	bnToStr="-"+bnToStr;
        }
        return bnToStr;
    }

    private static String addition (String str1,String str2) {//return the addition for two strings
    	String result="";
    	String change="";
    	boolean numBiggerTen=false;
    	int tempValue=0;
    	if (str1.length()<str2.length()) {//now we know str1 is bigger or equals
    		change=str1;
    		str1=str2;
    		str2=change;
    	}
    	for (int i=0;i<=str2.length()-1;i++) {//do the addition
    		tempValue=Integer.parseInt(String.valueOf(str1.charAt(str1.length()-i-1))) + Integer.parseInt(String.valueOf(str2.charAt(str2.length()-i-1)));//convert to int
    		if (numBiggerTen)//if bigger then 10
    			tempValue=tempValue+1;
    		if (tempValue>9) //remember we need +1 next digit
    			numBiggerTen=true;
    		else
    			numBiggerTen=false;
    		tempValue=tempValue%10;
    		result=String.valueOf(tempValue)+result;//convert to string
    	}
    	for(int i=str1.length()-str2.length()-1;i>=0;i--) {//where str1 is bigger then str2, just copy the rest of the number.
    		if (numBiggerTen) {//check if we need +1 
    			tempValue=Integer.parseInt(String.valueOf(str1.charAt(i)))+1;
    			tempValue=tempValue%10;
    		}
    		else
    			tempValue=Integer.parseInt(String.valueOf(str1.charAt(i)));
    		if (tempValue!=0)//check if we need +1 next digit
    			numBiggerTen=false;
    		result=String.valueOf(tempValue)+result;
    	}
    	if (numBiggerTen)//check the lest digit is not 0 and then we need more "1" in the start of the string
    		result="1"+result;
    	return result;
    }
    
    private static String multiplication (String str1,String str2) {//calculate multiplication of strings
    	String result="0";
    	for(int i=0;i<Integer.parseInt(str2);i++)// calling addition
    		result=addition(result,str1);
    	return result;
    }
    
    private static String exponent (int num,int exponent) {//calculate exponent
    	String result="0";
    	if (exponent==0)
    		result=String.valueOf("1");
    	else
    	    result=String.valueOf(num); // using string and not int
    	for(int i=1;i<exponent;i++)
    		result=multiplication(result,String.valueOf(num)); // calling multiplication
    	return result;
    } 
    
    // Returns this * 2
    public BinaryNumber multBy2() {
        BinaryNumber output = new BinaryNumber(this);
        output.bits.shiftLeft();
        output.bits.reduce();
        return output;
    }

    // Returens this / 2;
    public BinaryNumber divBy2() {
        BinaryNumber output = new BinaryNumber(this);
        if (!equals(ZERO)) {
            if (signum() == -1) {
                output.negate();
                output.bits.shiftRight();
                output.negate();
            } else output.bits.shiftRight();
        }
        return output;
    }

}
