
/**
 * Iterative Solution
 * @author Chris Mays
 *
 */
public class State {
	public static final int LEFT=0;
	public static final int RIGHT=1;
	public static final int UP=2;
	public static final int DOWN=3;
	public static final int FIRE=4;
	
	String[] Letters={"A","B","C","D","E","F","G","H","I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	String currentLetter="A";
	int letterIndex;
	int index;
	char c=65; //A
	char z=90;//Z
	//char currentChar=65;
	int currentCharIndex;
	String currentString;
	int currentLevel;
	int currentMove;
	State previousState;
	public State(State _previousState, int move){
		previousState=_previousState;
		currentMove=move;
		if(_previousState==null){
			currentCharIndex=0;
			currentString="";
			currentLevel=0;
			letterIndex=0;
		}else{
		currentCharIndex=_previousState.getCurrentChar();
		currentString=_previousState.getCurrentString();
		currentLevel=_previousState.getCurrentLevel()+1;
		letterIndex=_previousState.getLetterIndex();
		if(move==LEFT){
			letterIndex=_previousState.getLetterIndex()-1;
		}
		else if(move==RIGHT){
			//System.out.print("Right is a gooooo " + _previousState.getLetterIndex());
			letterIndex=_previousState.getLetterIndex()+1;
			//System.out.print("Right is a gooooo " + letterIndex);

		}else if(move==UP){
			currentCharIndex=currentCharIndex+1;
			if(currentCharIndex>25){
				currentCharIndex=0;
			}
			
			/*if(currentChar==90){
				currentChar=65;
				currentChar++;
			}*/
		}else if(move==DOWN){
			currentCharIndex=currentCharIndex-1;
			if(currentCharIndex<0){
				currentCharIndex=25;
			}			/*if(currentChar==65){
				currentChar=90;
			}else{
				currentChar--;
			}*/
		}else if(move==FIRE){
			
			if(letterIndex>=currentString.length()){
				//System.out.println("letter index greater ");

				currentString=currentString+Letters[currentCharIndex];
			}else if(letterIndex==0){
				//System.out.println("letter index 0");

				currentString=Letters[currentCharIndex]+currentString;
			}else{
				//System.out.println("letter index none of these ");
				String temp=currentString.substring(0, letterIndex);
				currentString=currentString.substring(letterIndex);
				currentString=temp+Letters[currentCharIndex]+currentString;
			}
			letterIndex=letterIndex+1;
			}
		
		}
	}
	
	public int getCurrentLevel(){
		return currentLevel;
	}
	public String getCurrentString(){
		return currentString;
	}
	public int getCurrentChar(){
		return currentCharIndex;
		
	}
	public int getLetterIndex(){
		return letterIndex;
	}
	public static boolean checkString(String current, String target){
		int eliminatedIndex=0;
		for(int i=0; i<current.length(); i++){
			/*if(!target.contains(""+current.charAt(i))){
				return false;
			}else{*/
				/*int eliminatetarget= target.indexOf(current.charAt(i));
				String temp=target.substring(0, eliminatetarget);
				if(eliminatetarget!=target.length()-1){
					target=target.substring(eliminatetarget+1);
				}else{
					target="";
				}
				target=temp+target;*/
				
				char curChar=current.charAt(i);
				if(target.indexOf(curChar,eliminatedIndex)>=0){
					eliminatedIndex=target.indexOf(curChar,eliminatedIndex)+1;
					
				}else{
					return false;
				}
				
				/*int eliminatetarget= target.indexOf(current.charAt(i));
				String temp;
				
				if(eliminatetarget+1<=target.length()-1){
				 temp=target.substring(eliminatetarget+1);
				}else{
					temp="";
				}*/
				
				/*if(eliminatetarget!=target.length()-1){
					target=target.substring(eliminatetarget+1);
				}else{
					target="";
				}*/
				//target=temp;
				
			
			
		}
		
		
		return true;
	}
	public boolean validState(String Target){
		if(previousState.getMove()==State.UP && this.getMove()==State.DOWN){ //Redundant move
			return false;
		}
		if(previousState.getMove()==State.DOWN && this.getMove()==State.UP){ //Redundant Move
			return false;
		}
			if(previousState.getMove()==State.LEFT && this.getMove()==State.RIGHT){//Redundant Move
			}
			if(previousState.getMove()==State.RIGHT && this.getMove()==State.LEFT){ //Redundant Move
			}
		if(letterIndex<0){ //Cursor less than zero
			//System.out.println("letter less than zero with a move of " + this);

			return false;
		}
		/*if(letterIndex>Target.length()){
			//System.out.println("greater than target");

			return false;
		}*/
		if(currentString.length()>Target.length()){ //String greater than target string
			return false;
		}

		 return checkString(currentString, Target); //Verifies correct amount of letters in the correct position
	}
	
    @Override
    public boolean equals(Object that) {
        if (that == this) {
            return true;
        }
        if (that == null || that.getClass() != this.getClass()) {
            return false;
        }
        State thatt = (State)that;
       return currentString.equals(thatt.getCurrentString()) && currentMove==thatt.getMove() && currentCharIndex==thatt.getCurrentChar();
      //return false;
    }
    @Override
    public int hashCode(){
    	return currentString.hashCode()+ currentMove+currentCharIndex;
    }
    public int getMove(){
    	return currentMove;
    }
    
    @Override
	public String toString(){
    	String tempMove="";
    	if(currentMove==0){
    	 tempMove="left";
    	}else if(currentMove==1){
    		tempMove="Right";
    	}else if(currentMove==2){
    		tempMove="UP";
    	}else if(currentMove==3){
    		tempMove="Down";
    	}else if(currentMove==4){
    		tempMove="Fire";
    	}
    	return tempMove + " " + Letters[currentCharIndex] + " " + currentLevel + " " +currentString + " " + letterIndex;
    }
}
