
import java.util.Scanner;

public class Task4a {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans = true;

        //---------------write your code BELOW this line only!--------------
        // the GCD algorithm
        int n = scanner.nextInt();
        int num=2;
        while (num*num<=n & ans==true) { // if num^2=n and ans not false, it means we coudn't fined divider
        	if (n%num==0) {
        		ans =false;
        	}
        	else {
        		num=num+1;
        	}
        }
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
    }
}