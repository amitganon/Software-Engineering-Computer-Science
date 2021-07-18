
import java.util.Scanner;

public class Task4e {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans1 = true;
        int ans2 = 0;

        //---------------write your code BELOW this line only!--------------
        // input 4 n,b,s,d numbers and check if number n is prime or not, if not output is divider
        int n=scanner.nextInt();
        int b=scanner.nextInt();
        int s=scanner.nextInt();
        int d=scanner.nextInt();
        boolean condition_2=true;
        int bd=1; //b^d
        for (int i=0; i<d;i=i+1) { // calculate b^d
        	bd=bd*b%n;
        }
        bd=bd%n;
        if (bd != 1){  // check the first condition
        	for (int i=0;i<s;i=i+1) { // check s times
                int b2id_1=1; //2^i
                int b2id_2=1; //b^(d*2^i)
        		for (int j=0;j<i;j=j+1) { //calculate 2^i
        			b2id_1=b2id_1*2;
        		}
        		b2id_1=b2id_1*d;           // calculate d*2^i
        		for (int j=0;j<b2id_1;j=j+1) { //calculate b^(d*2*i)
        			b2id_2=b2id_2*b%n;
        		}
        		b2id_2=b2id_2%n;
        		if (b2id_2==(n-1)) { // if the second condition false change condition_2 to false.
        			condition_2=false;
        		}
        	}
        if (condition_2==true) { //check if the second condition=true
       		ans1=false;
       		ans2=b;
        	}
        else // if the second condition false
        	ans2=-1;
        }
        else // if the first condition false
        	ans2=-1;
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans1);
        System.out.println(ans2);
    }
}