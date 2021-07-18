
public class Bit {

    private boolean value;

    public Bit(boolean value){
        this.value=value;
    }

    public int toInt(){ //return 0/1 for false/true
        int ans = 0;
        if (this.value)//else its already 0
        	ans=1;
        return ans;

    }

    public String toString(){// return "0"/"1" for false/true
        String ans = "";
        if (this.value)
        	ans="1";
        else
        	ans="0";
        return ans;
    }
    public static void main(String[] args) {

    	}

}

