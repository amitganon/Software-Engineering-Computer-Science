
import java.util.Scanner;

public class Task3b {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        //input n,k. calculate division's remainder 2^n % k
        int n= scanner.nextInt();
        int k= scanner.nextInt();
        int sum_n=1;
        int sum_c=n/30; // sum_count : how many times we have 30 in n.        
        for (int i=0; i<(n%30); i=i+1) {// for 1 :we check the remainder for n%30
        	sum_n=sum_n*2;
        }
        int sum_temp=1;        
        for (int i=0;i<sum_c;i=i+1) {      //for 2: we check the remainder for 2^30=1073741824,and multiply sum_c times.
           sum_temp=(1073741824%k)*sum_temp;
        }
        ans = ((sum_n%k)*sum_temp)%k; // multiply result for 1 * result for 2. using (a*b)%k=((a%k)*(b%k))%k
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
    }
}