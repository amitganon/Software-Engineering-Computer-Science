
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        //input 2 numbers ,and grill number between the input numbers (including the numbers themselves) 
        
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        ans =(int) (Math.random()*(b-a+1))+a; //the numbers between 0-1, *(b-a+!) ,now the numbers beteen 0-(b-a), +a ,now the numbers between a-b.
       
        //---------------write your code ABOVE this line only!--------------
        
        System.out.println(ans);
    }
}
