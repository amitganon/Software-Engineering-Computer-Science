
public class Assignment2 {

	
	/*-----------------------
	 *| Part A - tasks 1-11 |
	 * ----------------------*/
	
	// task 1
	public static boolean isSquareMatrix(boolean[][] matrix) { //check if matrix is Square
		boolean isSquare = true;
		if (matrix==null | matrix.length==0) //check the input not null or length=0;
			isSquare = false;
		for (int i=0;isSquare && i<matrix.length;i=i+1) { // check if Square
			if (matrix[i].length != matrix.length)
				isSquare =false;
		}
		return isSquare;
	}
	
	// task 2
	public static boolean isSymmetricMatrix(boolean[][] matrix) { // check if matrix is symmetric
		boolean isSymmetric = true;
		for (int i=0; i<matrix.length;i=i+1) { // check if matrix[i][j]!=matrix[j][i]
			for (int j=i+1; j<matrix[i].length;j=j+1)
				if (matrix[i][j]!=matrix[j][i])
					isSymmetric=false;
		}
		return isSymmetric;
	}

	// task 3
	public static boolean isAntiReflexiveMatrix(boolean[][] matrix) { // check if matrix is Anti Reflexive
		boolean antiRef=true;
		for (int i=0; i<matrix.length;i=i+1) {
			if (matrix [i][i] != false)
				antiRef=false;
		}
		return antiRef;
	}
		
	// task 4
	public static boolean isLegalInstance(boolean[][] matrix) { // check if all true: SquareMatrix & SymmetricMatrix & AntiReflexiveMatrix.
		boolean check = true;
		if (matrix==null | matrix.length==0) //check the input not null or length=0;
			check = false;
		if (check && ! (isSquareMatrix(matrix)))//check first condition
			check=false;
		if (check && ! isSymmetricMatrix(matrix) )//check second condition
			check=false;
		if (check && ! isAntiReflexiveMatrix(matrix))//check third condition
			check=false;
		return check;
	}
	
	// task 5
	public static boolean isPermutation(int[] array) { // check if array is Permutation
		boolean check = true;
		if (array.length==0) //check the input not length=0;
			check = false;
		for (int i=0; i<array.length;i=i+1) {
			int countApp=0;
			for (int j=0; j<array.length & countApp<2;j=j+1) {
				if (array[j]==i)
					countApp=countApp+1;
			}
			if (countApp!=1) // check if numbers appears only one time
				check = false;
		}
		return check;
	}
	
	// task 6
	public static boolean hasLegalSteps(boolean[][] flights, int[] tour) { // check if for all cities in the tour we have flights
		boolean check =  true;
		for (int i=0; i<tour.length-1;i=i+1) {
			if (flights[tour[i]][tour[i+1]]==false)
				check = false;
		}
		if (flights[tour[tour.length-1]][tour[0]]==false &check==true & tour.length>1) // check if there is flight to go back to the start
		check = false;
		return check;
	}
	
	// task 7
	public static boolean isSolution(boolean[][] flights, int[] tour) {//check hasLegalSteps,isPermutation=true, and tour[0]=0;
		if (tour ==null || tour.length!= flights.length) //exception when tour length not right or null
			throw new IllegalArgumentException("tour length not n");
		boolean check = true;
		if (!isPermutation(tour) | tour[0]!=0 | !hasLegalSteps(flights,tour))// check condition 1,2,3
			check = false;		
		return check;
	}
	
	// task 8
	public static boolean evaluate(int[][] cnf, boolean[] assign) { //Checks whether the placement provides the cnf
		boolean check = true;
		for (int i=0; i<cnf.length & check;i=i+1) {
			int countTrue=0;
			for (int k=0;k<cnf[i].length & countTrue!=1;k=k+1) {
				if (cnf[i][k]>0) // check if literal gets true
					if(assign[cnf[i][k]]==true)
						countTrue=1;
				if (cnf[i][k]<0) // check if literal gets false
					if(assign[cnf[i][k]*-1]==false)
						countTrue=1;
			}
			if (countTrue!=1) // check if we find a placement that provide
				check=false;
		}		
		return check;
	}
	
	// task 9
	public static int[][] atLeastOne(int[] lits) { //return cnf that Forcing at list 1 literal gets true;
		int [][]cnf=new int [1][lits.length];
		for (int i=0; i<lits.length;i=i+1)
			cnf[0][i]=lits[i];
		return cnf;
	}

	// task 10
	public static int[][] atMostOne(int[] lits) { // check if at most 1 literal gets true;
		int pairs= (lits.length*(lits.length-1))/2; // calculate how many pairs have in lits. formula n*(n-1)/2
		int indexCnf=0;
		int [][] cnf = new int [pairs][2];
		for (int i=0; i<lits.length;i=i+1) {
			for (int k=i+1;k<lits.length;k=k+1) { // make all the pair and put them in cnf
				int [] temp_pair= {-1*lits[i],-1*lits[k]}; // at most 1 literal gets true in all the pairs
				cnf[indexCnf]=temp_pair;
				indexCnf=indexCnf+1;
			}
		}
		return cnf;
	}
	
	// task 11
	public static int[][] exactlyOne(int[] lits) {
		int pairs= (lits.length*(lits.length-1))/2;// calculate how many pairs have in lits. formula n*(n-1)/2
		int [][] cnf = atMostOne(lits);//same cnf from atMostOne with one more condition
		int [][]cnf2=new int [pairs+1][2];
		cnf2 [pairs]=new int [lits.length];
		cnf2 [pairs]=lits; // exactly one literal gets true
		for (int i=0;i<cnf.length;i=i+1)// "copy" cnf to cnf2 but with the new condition
			cnf2[i]=cnf[i];
		return cnf2;
	}
}
