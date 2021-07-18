
import java.util.Scanner;

public class Task3a {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        // input number, a ,and calculate 2^a
        int a = scanner.nextInt();
        ans=1;
        for (int i=0; i<a; i++) //use for to calculate 2*2*2*2.... 'a' times
        	ans=ans*2;  
        //---------------write your code ABOVE this line only!--------------
        
        System.out.println(ans);
    }
}