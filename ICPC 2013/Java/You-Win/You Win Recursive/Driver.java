import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
/**
 * Recursive version
 * 
 * 
 * @author Chris Mays
 *
 */

public class Driver {
	static String[] Letters={"A","B","C","D","E","F","G","H","I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	//static String[] Letters={"A","H", "N"};

	public static void main(String[] args){
		ArrayList<Step> globalStateList=new ArrayList<Step>();
		
		Step tar=new Step("", 0, 'A');
		System.out.print(nextMove(tar,"ALMA"));
	    
		
		
		//System.out.print(1);
		//System.out.print(1);
		
	}
	
	
	
	
	
	public static double nextMove(Step st, String t){
		if(st.name.equals(t)){
			return 0;
		}else if(st.name.length()>t.length()){
			return Double.POSITIVE_INFINITY;
		}
		else if(!checkString(st.name, t)){
			return Double.POSITIVE_INFINITY;
		}
		else{
			double distSoFar=Double.POSITIVE_INFINITY;
			for(int i=0; i<=st.name.length(); i++){
				for(int j=0; j<Letters.length; j++){
					String newLetter=Letters[j];
					String newName=st.name;
					if(i==0){
						newName=newLetter+newName;
					}else if(i==st.name.length()){
						newName=newName+newLetter;
					}else{
						newName=newName.substring(0,i) + newLetter+ newName.substring(i);
					}
					Step newStep= new Step(newName, i+1, Letters[j].charAt(0));
					
					distSoFar=Math.min(distSoFar,nextMove(newStep, t)+movesFromIndex(st.index,i)+shortestToLetter(st.currentLetter, newStep.currentLetter)+1);
				}
			}
			return distSoFar;
		}
		//return 0;
	}
	
	public static int movesFromIndex(int curIndex, int moveToIndex){
		return Math.abs(curIndex-moveToIndex);
	}
	public static int shortestToLetter(char curLetter, char toLetter){
		int upCount=0;
		int downCount=0;
		int curIndex=curLetter-65;
		int toIndex=toLetter-65;
		
		if(curIndex-toIndex<0){
			upCount=26+curIndex-toIndex;
			downCount=toIndex-curIndex;
		}else if(curIndex-toIndex==0){
			return 0;
		}else{
			upCount=curIndex-toIndex;
			downCount=26+toIndex-curIndex;
		}
		return Math.min(upCount,downCount);
		
		
		
	}
	public static boolean checkString(String current, String target){
		int eliminatedIndex=0;
		for(int i=0; i<current.length(); i++){
			
				char curChar=current.charAt(i);
				if(target.indexOf(curChar,eliminatedIndex)>=0){
					eliminatedIndex=target.indexOf(curChar,eliminatedIndex)+1;
					
				}else{
					return false;
				}
		}
		return true;
	}
	
}
