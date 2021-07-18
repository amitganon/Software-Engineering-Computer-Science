
import java.util.Scanner;

public class Task4b {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        //input n, count how many prime numbers until n
        int n=scanner.nextInt();
        int num=2;
        while (num<=n){
            int i=2;
            boolean prime=true;
            while (i*i<=num & prime==true) { // for every , num ,until n run again the "GCD" algorithm
            	if (num%i==0) {
            		prime =false;
            	}
            	else { //if we can't fined prime check the next number
            		i=i+1;
            	}
            }
            	if (prime==true) { // if we fined prime count it
            		ans=ans+1;
            	}
            	num=num+1;           
        }
        
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);        
    }
}