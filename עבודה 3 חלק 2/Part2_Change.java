
class Part2_Change{

    public static int countChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        int ans = countChangeLimited(coins,n,numOfCoinsToUse,0,0);//calling the recursion
        return ans;
    }
    
    public static int countChangeLimited(int [] coins, int n,int numOfCoinsToUse ,int index,int ans) {//count the options of change with numbers of change coins
    	if (n==0 & numOfCoinsToUse==0) { // there is a solution
    		ans=ans+1;
    		ans=countChangeLimited(coins,n+coins[index],numOfCoinsToUse,index+1,ans);//go back one "step" but change the index forward
    	}
    	else if (index>=coins.length || n<0 | numOfCoinsToUse<=0 | coins[index]>n)// if index>=coins.length its means we don't have any more solutions
    		;
    	else {
    		ans= countChangeLimited(coins,n-coins[index],numOfCoinsToUse-1,index,ans); //check the both options for not losing a solution
    		ans=countChangeLimited(coins,n,numOfCoinsToUse,index+1,ans);
    	}
    	return ans;	
    }
    
    public static void printAllChangeLimited(int[] coins, int n, int numOfCoinsToUse){//calling the recursion printAllChangeLimited
        int [][] arrays=new int [countChangeLimited(coins,n,numOfCoinsToUse)][numOfCoinsToUse];//using countChangeLimited to now the length
        int indexDArray=0;
        if (arrays.length>0)
           printAllChangeLimited(coins,n,numOfCoinsToUse,0,arrays,0,indexDArray);//calling the recursion
    }
    
    public static void printAllChangeLimited(int [] coins, int n,int numOfCoinsToUse ,int index,int [][] array,int indexArrey,int indexDArray) {//print all change options
    	if (n==0 & numOfCoinsToUse==0) { // if true we find a solution and print it
    		for (int i=0;i<array.length;i++) {//printing the solution
    			for (int j=0;j<array[i].length-1;j++){//check if needed ","
    				if(array[i][j]>0)
    				System.out.print(array[i][j]+",");
    			}
    			if(array[i][array[i].length-1]>0)//check if array.length not 0
    			System.out.println(array[i][array[i].length-1]);
    		}
    		printAllChangeLimited(coins,n+coins[index],numOfCoinsToUse,index+1,array,0,indexDArray+1);//go back one "step" but change the indexes forward
    	}
    	else if (index>=coins.length | n<0 | numOfCoinsToUse<=0) // if false this "pass" is not a solution and we need to "go back"
    		;
    	else {
    		array[indexDArray][indexArrey]=coins[index];
    		printAllChangeLimited(coins,n-coins[index],numOfCoinsToUse-1,index,array,indexArrey+1,indexDArray);
    		array[indexDArray][indexArrey]=0;
    		printAllChangeLimited(coins,n,numOfCoinsToUse,index+1,array,indexArrey,indexDArray);
    	}
    }
    
 
    public static int changeInCuba(int cuc){
        int ans = 0;
        int [] coins= {1,3,3,5,9,10,15,20,30,50,60,100,150,300};//changing 'coins' like we have cuc and cup coins
        int countOptions=0;
        for (int i=1;i<=cuc*3;i++) {//Because we use countChangeLimited we need the recursion for every numOfCoinsToUse
        	countOptions=countOptions+countChangeLimited(coins,cuc*3,i);//calling the recursion
        }
        ans=countOptions;
        return ans;
    }

    public static void main(String[] args){

    }
}
