
public class NumberAsBits {

    private Bit[] bits;
    //private boolean value;

    public NumberAsBits(Bit[] bits) {
        if(bits==null)//check the array is not null
        	throw new IllegalArgumentException("bits in null");
        else
        	this.bits=new Bit [bits.length];
        	for (int i=0;i<bits.length;i++) { // copy the bits array and not changing him
        		this.bits[i]=bits[i];
        	}
    }

    public String toString() {//return the number for bits array
    	String ans ="0";
    	if (bits.length==0)//if len =0, return ""
    		ans="";
        for (int i=bits.length-1;i>=0;i--) {
        	if(bits[i]==null)//if null throw Exception
        		throw new IllegalArgumentException("cant be null");
        	else {
        		if (bits[i].toInt() == 1)//calculate the number
        		    ans=addition(ans,exponent(2,bits.length-1-i));   		
        	}        	
        }
        return ans;
    }
    
    
    private static String addition (String str1,String str2) {//return the sum for two strings
    	String result="";
    	String change="";
    	boolean numBiggerTen=false;
    	int tempValue=0;
    	if (str1.length()<str2.length()) {//now we know str1 is bigger or equals
    		change=str1;
    		str1=str2;
    		str2=change;
    	}
    	for (int i=0;i<=str2.length()-1;i++) {//do the sum
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
    	for(int i=0;i<Integer.parseInt(str2);i++)// calling sum
    		result=addition(result,str1);
    	//System.out.println("multiplication= "+result);
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

    public String base2() {//return a string of binary represent
        String ans ="";
        for (int i=0; i<bits.length;i++) {
        	if(bits[i]==null)//check we don't have null in the array
        		throw new IllegalArgumentException("cant be null");
        	if (ans!="" | bits[i].toInt()!=0 | bits.length==1)//check there is no 0 before 1,except the binary number represent the number one. 
        	   ans=ans+bits[i].toString();
        }
        return ans;
    }
    
    public static void main(String[] args) {

    	}
}


