
import java.math.BigInteger;
import java.util.Random;
class Part1_BigInteger{

    public static BigInteger sumSmaller(BigInteger n){//return the sum of smaller and positive numbers
    	BigInteger num =  new BigInteger("0");//"copy" n because we don't want to change n
    	num=num.add(n);
        BigInteger sum =  new BigInteger("0");
        BigInteger lessOne = new BigInteger("-1"); // for making num get smaller
        num=num.add(lessOne);
        while (num.compareTo(lessOne.subtract(lessOne)) > 0) {
        	sum=sum.add(num);
        	num=num.add(lessOne);         
        }        
        return sum;
    }

    public static void printRandoms(int n){//print n random numbers
        Random rnd = new Random();
    	for (int i=0; i<n;i++) {
    		int randomNum =rnd.nextInt();
    		System.out.println(randomNum);
    	}
    }

    public static boolean isPrime(BigInteger n){//check if number is prime
        boolean ans= true;
        BigInteger count=new BigInteger("2");
        BigInteger zero=new BigInteger("0");
        BigInteger plasOne=new BigInteger("1");
        if (zero.compareTo(n)==0 | plasOne.compareTo(n)==0)//check if number is 1/0
        	ans=false;
        while (ans & count.pow(2).compareTo(n) <= 0) {//check if the number is prime
        	if (zero.compareTo(n.mod(count)) == 0)
        		ans=false;
        	else
        		count=count.add(plasOne);
        }
        return ans;
    }

    public static BigInteger randomPrime(int n){//return a random prime number, not high then n^2 
        BigInteger randBig = new BigInteger("0");
        boolean isPrime=false;
        while (!isPrime) {//until we fined a random prime number
        	Random rnd = new Random();//new random number
        	BigInteger bigNumRnd = new BigInteger(n,rnd);
        	if (isPrime(bigNumRnd)) {//check if prime
        		isPrime=true;
        		randBig=randBig.add(bigNumRnd);
        	}
        }
        return randBig;
    }

    public static void main(String[] args) {

    }
}