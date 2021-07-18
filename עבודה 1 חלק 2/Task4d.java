
import java.util.Scanner;

public class Task4d {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans1 = 0;
        int ans2 = 0;

        //---------------write your code BELOW this line only!--------------
        //input number n , output calculate what the pow of 2 that entered in the number (n-1) and the remainder s
        int n=scanner.nextInt();
        n=n-1;
        while (n%2==0) {
        	ans1=ans1+1; // count s, the pow
        	n=n/2; // in the end of while n=d , the remainder
        }
        ans2=n; // ans2=d

        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans1);
        System.out.println(ans2);
    }
}