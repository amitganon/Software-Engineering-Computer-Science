
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans = true;

        //---------------write your code BELOW this line only!--------------
        // input: 4 numbers a,b,q,r ,and check a%(q*b)=r
        
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int q = scanner.nextInt();
        int r = scanner.nextInt();
        if(!(b!=0 & r<b & a==q*b+r))// if this not, return true;
        	ans=false; 

        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
    }
}