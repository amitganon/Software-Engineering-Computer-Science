
public class Assignment2 {
	public static void main(String[] args) {

	/*-----------------------
	 *| Part A - tasks 1-11 |
	 * ----------------------*/
	}
	
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
	
	/*------------------------
	 *| Part B - tasks 12-20 |
	 * -----------------------*/
	
	// task 12a
	public static int map(int i, int j, int n) { //calculate for n cities a single k for every (i,j)
		int k=(i*n + j%n)+1;
		return k;
	}
	
	// task 12b
	public static int[] reverseMap(int k, int n) {// calculate for n cities a single (i,j) for every k
		int [] reveMap = new int [2];
		reveMap[0]=(k-1)/n;
		reveMap[1]=(k-1)%n;
		return reveMap;
	}
	
	// task 13
	public static int[][] oneCityInEachStep(int n) { // create cnf with the constraint
		int pairs= (n*(n-1))/2; //calculate the pairs that exactlyOne will create (task 11),formula n*(n-1)/2
		int [][] cnf=new int [n*(pairs+1)][2];
		int cnfJump =0;
		for (int i=0;i<n;i=i+1) {
			int [] tempList=new int [n];
			for (int j=0;j<n;j=j+1)
			    tempList [j]=map(i,j,n); // create clause - map(i,0,n)...map(i,n-1,n) for every i
			int [][] tempArray=exactlyOne(tempList); // use exactlyOne on clause
			for (int s=0;s<(pairs+1);s=s+1)
				cnf[s+cnfJump]=tempArray[s]; // create cnf from all the clause. using cnf_jump
			cnfJump =cnfJump +(pairs+1); // calculate the jump in cnf for the next clause
		}
		return cnf;
	}

	// task 14
	public static int[][] eachCityIsVisitedOnce(int n) {// create cnf with the constraint2
		// the all algorithm is exactly like task 13 but i and j swipes (the "for" loop)
		int pairs= (n*(n-1))/2; //calculate the pairs that exactlyOne will create (task 11),formula n*(n-1)/2
		int [][] cnf=new int [n*(pairs+1)][2];
		int cnfJump =0;
		for (int j=0;j<n;j=j+1) {//j takes i place
			int [] tempList=new int [n];
			for (int i=0;i<n;i=i+1)////i takes j place
			    tempList [i]=map(i,j,n); // create clause - map(0,i,n)...map(n-1,i,n) for every j
			int [][] tempArray=exactlyOne(tempList); // use exactlyOne on clause	
			for (int s=0;s<(pairs+1);s=s+1)
				cnf[s+cnfJump]=tempArray[s]; // create cnf from all the clause. using cnf_jump
			cnfJump =cnfJump +(pairs+1); // calculate the jump in cnf for the next clause
		}
		return cnf;
	}
	
	// task 15
	public static int[][] fixSourceCity(int n) {//cnf that verified that the origin city is 0
		int [][] cnf = {{1}};
		return cnf;
	}
	
	// task 16
	public static int[][] noIllegalSteps(boolean[][] flights) {//make cnf with the Constraint 4
		int count=0; 
		for (int j=0; j<flights.length;j=j+1) {// calculate the cnf.length
			for (int k=0; k<flights.length;k=k+1) {
				if (flights[j][k]==false & k!=j)
					count=count+1;
			}
		}
		int [][] cnf =new int [count*flights.length][2];//cnf.length = for every false in flights there is n clause
	    count=0;
		for (int i=0; i<flights.length;i=i+1) { //for every step i
			for (int j=0; j<flights.length;j=j+1) {
				for (int k=0; k<flights.length;k=k+1) {
					if (flights [j][k]==false & j!=k) {//cnf constraint 4
						cnf [count][0]=(map(i,j,flights.length)*-1);
						cnf[count][1]=(map((i+1)%flights.length,k,flights.length)*-1);
						count=count+1;
					}					
				}				
			}
		}
		return cnf;				
	}
	
	// task 17
	public static int[][] encode(boolean[][] flights) {//return "conunicatioa" of all 4 constraints
		int [][]cnfQ13 =oneCityInEachStep(flights.length);
		int [][]cnfQ14=eachCityIsVisitedOnce(flights.length);
		int [][]cnfQ15=fixSourceCity(flights.length);
		int [][]cnfQ16=noIllegalSteps(flights);
		int lengthCon=cnfQ13.length+cnfQ14.length+cnfQ15.length+cnfQ16.length; //calculate the sum of length of all cnf
		int [][]cnfCon=new int [lengthCon][];
		for (int i=0;i<cnfQ13.length;i++) // "copy" cnfQ13 to cnfCon. index 0-cnfQ13.length
			cnfCon[i]=cnfQ13[i];			
		for (int i=0;i<cnfQ14.length;i++)// "copy" cnfQ14 to cnfCon. index cnfQ13.length-cnfQ14.length
			cnfCon[i+cnfQ13.length]=cnfQ14[i];			
		for (int i=0;i<cnfQ15.length;i++) // "copy" cnfQ15 to cnfCon. index cnfQ14.length-cnfQ15.length
			cnfCon[i+cnfQ13.length+cnfQ14.length]=cnfQ15[i];			
		for (int i=0;i<cnfQ16.length;i++)// "copy" cnfQ16 to cnfCon. index cnfQ15.length-cnfQ16.length
			cnfCon[i+cnfQ13.length+cnfQ14.length+cnfQ15.length]=cnfQ16[i];				
		return cnfCon;
	}

	// task 18
	public static int[] decode(boolean[] assignment, int n) {//return array "tour" that satisfies the condition
		if (assignment==null) // first exception
			throw new IllegalArgumentException("assignment in null");
		if (assignment.length!=n*n+1) // second exception
			throw new IllegalArgumentException("assignment length nut n^2+1");
		int []tour=new int [n];
		for (int i=0;i<n;i=i+1) {
			for (int j=0;j<n;j=j+1) {
				if (assignment[map(i,j,n)]==true) //check the condition
					tour[i]=j;
			}
		}
		return tour;
	}
	
	// task19
	public static int[] solve(boolean[][] flights) {//get "the Big Trip" problem and find solution
		 if (!isLegalInstance(flights))
			 throw new IllegalArgumentException("flights ilegal");
		 int [] tour=null;
		 int countVar=flights.length*flights.length;
		 SATSolver.init(countVar);//start SAT solver
		 int cnf [][] =encode(flights);//make cnf
		 SATSolver.addClauses(cnf);//add cnf to SAT solver
		 boolean [] assignment=SATSolver.getSolution();//use the SAT to find a possible solution
		 if (assignment==null)//can't find solution in time limits
			 throw new IllegalArgumentException("can't find solution in time limits");
		 else if (assignment.length==countVar+1) {//a possible solution
			 tour=decode(assignment,flights.length);
			 if (! isSolution(flights,tour))//check if tour is legal solution
				 throw new IllegalArgumentException("The solution ilegal");
		 }
		return tour;		
	}
	
	// task20
	public static boolean solve2(boolean[][] flights) {//get "the Big Trip" problem and find 2 solution
		int [] tour = solve(flights); // find the first solution
		boolean sol2=false;
		int [] clause=new int [tour.length-1];//new clause 1
		for (int i=1;i<tour.length;i=i+1)
			clause[i-1]=(map(i,tour[i],flights.length))*-1;
		int [] clause2=new int [tour.length-1];//new clause 2
		for (int i=tour.length-1;i>0;i=i-1) 
			clause2[tour.length-i-1]=(map(tour.length-i,tour[i],flights.length))*-1;
		int countVar=flights.length*flights.length;//check for second solution
		SATSolver.init(countVar);
		int cnf [][] =encode(flights);//make cnf
		SATSolver.addClauses(cnf);
		SATSolver.addClause(clause); // input to SATSolver tow more clause
		SATSolver.addClause(clause2);
		boolean [] assignment=SATSolver.getSolution();//the second solution
		if (assignment==null)//checks of the solution
	       throw new IllegalArgumentException("can'n find solution in time limits");
		else if(assignment.length!=0) {
			 tour=decode(assignment,flights.length);
			 if (! isSolution(flights,tour))
		        throw new IllegalArgumentException("The solution ilegal");
			 if (assignment.length==countVar+1)//there are 2 solution without opposite flights order
	            sol2=true;						
	    }
		return sol2;	
	}		
}
