import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Chris Mays
 * 
 * ICPC problem set Mid Atlantic Region 2014
 * Problem D Everything in Excess!
 * 
 * 
 * @author Chris Mays
 *
 */




public class Driver {
	static HashMap<Integer,ArrayList<Integer>> primeMemo;
	static ArrayList<Integer> allPrimeNumbers;
	
	
	public static void main(String[] args){
		allPrimeNumbers=getPrimeNumbers();
		Scanner s= new Scanner(System.in);
		int startRange=s.nextInt();
		int endRange=s.nextInt();
		while(startRange!=0 && endRange!=0){
			
			System.out.println(getExcessInRange(startRange,endRange));
			startRange=s.nextInt();
			endRange=s.nextInt();
		}
		/* 
		 // Important test cases
	    printArrayList(getPrimeFactors(2));
		printArrayList(getPrimeFactors(3));
		printArrayList(getPrimeFactors(4));
		printArrayList(getPrimeFactors(5));
		printArrayList(getPrimeFactors(6));
		printArrayList(getPrimeFactors(7));
		printArrayList(getPrimeFactors(8));
		printArrayList(getPrimeFactors(9));
		printArrayList(getPrimeFactors(10));
		printArrayList(getPrimeFactors(11)); */
	}
	
	
	/**
	 * Gets the excess for num
	 * @param num the number you want an excess for
	 * @return the excess
	 */
	public static int getExcess(int num){
		
		ArrayList<Integer> factors=getPrimeFactors(num);
		int excess=0;
		int lastSeen=0;
		for(Integer number : factors){
			if(lastSeen==number){
				excess++;
			}else{
				lastSeen=number;
			}
		}
		return excess;
		
	}
	/**
	 * Creates a list of prime factors all the way up to the square root
	 * of 10000000
	 * @return the ArrayList<Integer> of prime numbers 
	 */
	public static ArrayList<Integer> getPrimeNumbers(){
		ArrayList<Integer> primeNums=new ArrayList<Integer>();
		for(int i=2; i<=Math.sqrt(10000000); i++){
			boolean shortCircuit=false;
			for(int x=2; x<=Math.sqrt(i) && !shortCircuit; x++){
				if(i%x==0){
					shortCircuit=true;
				}
			}
			if(shortCircuit==false){
				primeNums.add(new Integer(i));
			}
			
		}
		return primeNums;
	}
	/**
	 * Creates a List of prime factors for num
	 * @param num the number you want a list of prime factors for
	 * @return an ArrayList of Prime Factors for num
	 */
	public static ArrayList<Integer> getPrimeFactors(int num){
		//If the prime numbers have not been calculated yet
		if(allPrimeNumbers==null || allPrimeNumbers.size()==0){
			allPrimeNumbers=getPrimeNumbers();
		}
		ArrayList<Integer> factors=new ArrayList<Integer>();
		for(Integer check: allPrimeNumbers){
			while(num%check==0){
				factors.add(check);
				num=num/check;
			}
		}
		
		return factors;
	}
	
	
	/**
	 * Finds the Number with the biggest excess in 
	 * the range provided
	 * @param start the starting of the range (inclusive)
	 * @param finish the ending of the range (inclusive)
	 * @return the number within the range with the biggest excess
	 */
	public static int getExcessInRange(int start, int finish){
		int maxNum=start;
		int maxExcess=getExcess(start);
		for(int i=start+1; i<=finish; i++){
			int curExcess=getExcess(i);
			if(maxExcess<curExcess){
				maxExcess=curExcess;
				maxNum=i;
			}
			
			
		}
		return maxNum;
	}
	
	
	
	
	/**
	 * Utility methods
	 */
	
	
	public  static <E>  void  printArrayList (ArrayList<E> list){
		for(E li: list){
			System.out.print(li + " ");
		}
		
		System.out.println("\n--------");
	}
}
