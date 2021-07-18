
import java.util.Scanner;

public class Task4c {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        // input n, grill a number beetwin [2,n-1]
        int n = scanner.nextInt();
        int b =(int) (Math.random()*(n-2))+2; //Math.random return number beetwin 0-1. *n-2, now the number beetwin [0,n-3], +2, now beetwin [2,n-1]
        ans=b;
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
    }
}