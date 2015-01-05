/**
 * Chris Mays
 * 
 * ICPC problem set Mid Atlantic Region 2014
 * Problem A Hy-phe-na-tion Rulez
 * 
 * 
 * @author Chris Mays
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class Driver {
	/**
	 * It is very important that str comes before st
	 * the way the algorithm works is that it loops through
	 * the array of cons and vow and replaces all occurrences of each with
	 * either a 0 for a vowel or the length of the cons for a cons  
	 */
	static String[] cons = {"str","st","qu","br","sl","bl","cr","ph","ch","b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","z" };
	static String[] vow= {"a","e","i","o","u","y"};
	
	static ArrayList<String> consList=new ArrayList<String>(Arrays.asList(cons));
	static ArrayList<String> vowList=new ArrayList<String>(Arrays.asList(vow));
	
	
	public static void main(String[] args){
		
		Scanner s= new Scanner(System.in);
		String currentWord;
		
		currentWord=s.next();
		while(!currentWord.equals("===")){
			String editableString=convertWordToNumRep(currentWord);
			ArrayList<Integer> vcvLocations = getListOfVCVLocations(editableString, currentWord.charAt(currentWord.length()-1)=='e');
			ArrayList<Integer> vccvLocations= getListOfVCCVLocations(editableString);
			ArrayList<Integer> hyphenLocationsReversed= mergeSortAndReverse(vcvLocations,vccvLocations);
			System.out.println(addHyphensToString(hyphenLocationsReversed, currentWord));
			currentWord=s.next();
			
		}
	}
	/**
	 * return the list of VCV hyphen locations for numString
	 * 	-Accounts for rule if ends in "e" and CVC combinations then don't hyphenate
	 * @param numString the converted string Note: use convertWordToNumRep(String)
	 * @param lastLetterE a flag to tell the method if the source string (non converted) ends with an e
	 * @return Returns a list of hyphen locations for all VCV combinations in the string
	 */
	public static ArrayList<Integer> getListOfVCVLocations(String numString, boolean lastLetterE){
		ArrayList<Integer> VCVLocations = new ArrayList<Integer>();
		int hyphenLocation=0;
		for(int i=0; i<numString.length()-2; i++){
			char firstLetter=numString.charAt(i);
			char secondLetter=numString.charAt(i+1);
			char thirdLetter=numString.charAt(i+2);
			/**
			 * Account for rule where if e is last letter and you encounter
			 * VCV then you don't add a hyphen
			 */
			if(i+2==numString.length()-1 && lastLetterE){ 
				
				break;
			}
			/**
			 * Verify they are numbers and not punctuation 
			 * 
			 */
			if(charIsNum(firstLetter) && charIsNum(secondLetter) && charIsNum(thirdLetter)){
				
				int firstNum=Integer.parseInt(firstLetter+"");
				int secondNum=Integer.parseInt(secondLetter+"");
				int thirdNum=Integer.parseInt(thirdLetter+"");
				hyphenLocation+= (firstNum==0) ? 1 : firstNum;
				if(firstNum==0 && secondNum>0 && thirdNum==0){
					VCVLocations.add(new Integer(hyphenLocation));
				}
			}
			//If one of the three are punctuation increase the counter for the hyphen index and
			//consume the first value of the 3 letter set
			else{
				
					int firstValue = (charIsNum(firstLetter)) ? Integer.parseInt(firstLetter+"") : 1;
					firstValue= (firstValue==0) ? 1 : firstValue;
					hyphenLocation+=firstValue;
					
				
			}
			
		}
		return VCVLocations;
	}
	
	
	
	/**
	 * Returns a list of hyphen locations for all VCCV combinations in the string
	 * @param numString the converted string Note: use convertWordToNumRep(String)
	 * @return the list of VCCV hyphen locations for numString
	 */
	public static ArrayList<Integer> getListOfVCCVLocations(String numString){
		ArrayList<Integer> VCVLocations = new ArrayList<Integer>();
		int hyphenLocation=0;
		for(int i=0; i<numString.length()-3; i++){
			char firstLetter=numString.charAt(i);
			char secondLetter=numString.charAt(i+1);
			char thirdLetter=numString.charAt(i+2);
			char fourthLetter=numString.charAt(i+3);
			if(charIsNum(firstLetter) && charIsNum(secondLetter) && charIsNum(thirdLetter) && charIsNum(fourthLetter)){
				
				int firstNum=Integer.parseInt(firstLetter+"");
				int secondNum=Integer.parseInt(secondLetter+"");
				int thirdNum=Integer.parseInt(thirdLetter+"");
				int fourthNum=Integer.parseInt(fourthLetter+"");
				
				
				
				/**
				 * If it is the first time going through add both first and second num
				 * 
				 * 
				 */
				if(hyphenLocation==0){
					hyphenLocation+= (firstNum==0) ? 1 : firstNum;
					hyphenLocation+= (secondNum==0) ? 1 : secondNum;
				}else{
					hyphenLocation+= (secondNum==0) ? 1 : secondNum;
				}
				
				
				if(firstNum==0 && secondNum>0 && thirdNum>0 && fourthNum==0){
					VCVLocations.add(new Integer(hyphenLocation));
				}
			
			}else{
				/**
				 * If it is the first time going through add both first and second num
				 * Have to also check for non numbers
				 * 
				 */
				if(hyphenLocation==0){
					int firstNum= (charIsNum(firstLetter)) ? Integer.parseInt(firstLetter+""): 1;
					firstNum= (firstNum==0) ? 1 : firstNum;
					int secondNum = (charIsNum(secondLetter)) ? Integer.parseInt(secondLetter+""): 1;
					secondNum= (secondNum==0) ? 1 : secondNum;
					
					hyphenLocation+= (firstNum==0) ? 1 : firstNum;
					hyphenLocation+= (secondNum==0) ? 1 : secondNum;
				}else{
					int secondNum = (charIsNum(secondLetter)) ? Integer.parseInt(secondLetter+""): 1;
					secondNum= (secondNum==0) ? 1 : secondNum;
					hyphenLocation+= (secondNum==0) ? 1 : secondNum;
				}
			}
			
		}
		return VCVLocations;
	}
	
	
	
	
	/**
	 * Converts all Vowels to 0 and all Consonants to the consonant length (1-9)
	 * @param currentWord the word to convert
	 * @return the new converted string
	 */
	public static String convertWordToNumRep(String currentWord){
		String editableString=currentWord.toLowerCase();
		for (String con: consList){
			editableString=editableString.replace(con, ""+con.length());
		}
		for(String vo: vowList){
			editableString=editableString.replace(vo, 0 + "");
		}
		return editableString;
	}
	/**
	 * Tests if a char is a number
	 * @param isNum the char you want to test
	 * @return True if isNum is a number false otherwise
	 */
	public static boolean charIsNum(char isNum){
		String num=""+isNum;
		try{
			Integer.parseInt(num);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
    /**
     * It is better to accept a reversed ordered list of numbers so that the inserted
     * hyphens don't cause problems with the other hyphen indexed
     * @param hyphenList the list of hyphen indexes
     * @param word the word to add it to 
     * @return
     */
	public  static  String  addHyphensToString (ArrayList<Integer> hyphenList, String word){
		StringBuilder buildableWord=new StringBuilder(word);
		for(Integer location: hyphenList) {
			buildableWord = buildableWord.insert(location.intValue(), "-");
		}
		return buildableWord.toString();
	}
	/**
	 * Takes in list2 and merges it with list1 it then sorts and reverses the newly provided list
	 * @param list1 first list to merge
	 * @param list2 second list to merge
	 * @return the newly merged sorted and reversed list
	 */
	public static ArrayList<Integer> mergeSortAndReverse(ArrayList<Integer> list1, ArrayList<Integer> list2 ){
		list1.addAll(list2);
		Collections.sort(list1);
		Collections.reverse(list1);
		return list1;
	}
	
	/*
	 * Utility Methods
	 */
	public  static <E>  void  printArrayList (ArrayList<E> list){
		for(E li: list){
			System.out.println(li);
		}
	}
	
}
