
class Part1_Change{

    public static boolean change(int [] coins, int n){ // calling checkChange
        boolean ans = false;
        int index=0;
        ans = checkChange(coins,n,index);
        return ans;
    }
    
    public static boolean checkChange(int [] coins, int n, int index) {//check if there is change with the option of coins we have 
    	boolean ans=false;
    	if (n==0) // there is a solution
    		ans=true;
    	else if (index>=coins.length | n<0)// if index>=coins.length its means we don't have a solution
    		;
    	else
    		ans= checkChange(coins,n-coins[index],index) || checkChange(coins,n,index+1);//the recursion
    	return ans;
    }

    public static boolean changeLimited(int[] coins, int n, int numOfCoinsToUse){//calling checkChangeLimited
    	boolean ans = false;
        int index=0;
        ans = checkChangeLimited(coins,n,numOfCoinsToUse,index);//calling the recursion
        return ans;
    }
    
    public static boolean checkChangeLimited(int [] coins, int n,int numOfCoinsToUse ,int index) {//check if there is change with numbers of change coins
    	boolean ans=false;
    	if (n==0 & numOfCoinsToUse==0) // there is a solution
    		ans=true;
    	else if (index>=coins.length | n<0 | numOfCoinsToUse<=0)// if index>=coins.length its means we don't have a solution
    		;
    	else
    		ans= checkChangeLimited(coins,n-coins[index],numOfCoinsToUse-1,index) || checkChangeLimited(coins,n,numOfCoinsToUse,index+1);
    	return ans;	
    }

    public static void printChangeLimited(int[] coins, int n, int numOfCoinsToUse){//print the change and calling checkChangeLimited.
        int [] array=new int [numOfCoinsToUse];
        checkChangeLimited(coins,n,numOfCoinsToUse,0,array,0);//calling the recursion
        int indexArray=array.length-1;
        int lastIndex=0;
        while (indexArray>-1) {//check what the last index !=0
        	if (array[indexArray]!=0) {
        		lastIndex=indexArray;
        		indexArray=-1;
        	}
        	indexArray=indexArray-1;
        }
        for (int i=0; i<array.length;i++) {//print array
            if(array[i] !=0 & lastIndex!=i)
                System.out.print(array[i]+",");
            else if(array[i] !=0){//the last array(index) !=0 print without ","
            	System.out.print(array[i]);
            }
        }
    }
    
    public static boolean checkChangeLimited(int [] coins, int n,int numOfCoinsToUse ,int index,int [] array,int indexArrey) {//make the array of change
    	boolean ans=false;
    	if (n==0 & numOfCoinsToUse==0) // if true we find a solution
    		ans=true;
    	else if (index>=coins.length | n<0 | numOfCoinsToUse<=0) // if false this "pass" is not a solution and we need to "go back"
    		;
    	else {
    		array[indexArrey]=coins[index];
    		ans= checkChangeLimited(coins,n-coins[index],numOfCoinsToUse-1,index,array,indexArrey+1);
    		if(!ans) {//if ans = false, last array[indexArrey] = 0, and check for for solution with the next index
    			array[indexArrey]=0;
    		    ans=checkChangeLimited(coins,n,numOfCoinsToUse,index+1,array,indexArrey);
    		}
    	}
    	return ans;	
    }


    public static void main(String[] args){
    }
}
