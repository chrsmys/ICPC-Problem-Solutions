import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Iterative Solution
 * @author Chris Mays
 *
 */

public class Driver {
	public static void main(String[] args){
		ArrayList<State> globalStateList=new ArrayList<State>();
		
		/*System.out.print("cat".contains("c"));
		for (int i=64; i<=92; i++ ){
			char temp=(char) i;
			System.out.println("" + temp + " " + Math.abs((i-65))%26);
		}*/
		Scanner s=new Scanner(System.in);
		String target=s.next();
		Queue<State> stateQueue= new LinkedList<State>();
		State start=new State(null, -1);
		stateQueue.add(start);
		State complete=null;
		while(!stateQueue.isEmpty() && complete==null){
			State currentState=stateQueue.remove();
			if(currentState.getCurrentString().equals(target)){
				complete=currentState;
			}else{
				State moveLeft=new State(currentState, State.LEFT);
				State moveRight=new State(currentState, State.RIGHT);
				State moveUp=new State(currentState, State.UP);
				State moveDown=new State(currentState, State.DOWN);
				State fire=new State(currentState, State.FIRE);
				

				if(moveLeft.validState(target) && !globalStateList.contains(moveLeft)){
					stateQueue.add(moveLeft);
					System.out.println(moveLeft);
					//System.out.println(moveLeft );

				}
				if(moveRight.validState(target) && !globalStateList.contains(moveRight)){
					stateQueue.add(moveRight);
					System.out.println(moveRight);

					//System.out.println(moveRight);

				}
				if(moveUp.validState(target) && !globalStateList.contains(moveUp)){
					stateQueue.add(moveUp);
					System.out.println(moveUp);

					//System.out.println(moveUp);

				}
				if(moveDown.validState(target)  && !globalStateList.contains(moveDown)){
					stateQueue.add(moveDown);
					System.out.println(moveDown);

					//System.out.println(moveDown.currentString);

				}if(fire.validState(target)  && !globalStateList.contains(fire)){
					stateQueue.add(fire);
					System.out.println(fire);

					//System.out.println(fire);

				}
				globalStateList.add(moveLeft);
				globalStateList.add(moveRight);
				globalStateList.add(moveUp);
				globalStateList.add(moveDown);
				globalStateList.add(fire);
				
				
				
			}
		}
		System.out.print("Complete" );
		if(complete!=null){
			
			System.out.print("Complete for real " + complete.currentLevel);
			System.out.print("\n\n\n\n\n");
			while(complete!=null){
				System.out.println(complete);
				complete=complete.previousState;
			}

		}
		/*State start=new State(null, 0);
		State move1=new State(start, State.FIRE);
		State move2=new State(move1, State.FIRE);
		State move3=new State(move2, State.LEFT);
		State move4=new State(move3, State.UP);
		State move5=new State(move4, State.UP);
		State move6=new State(move5, State.UP);
		State move7=new State(move6, State.UP);
		State move8=new State(move7, State.UP);
		State move9=new State(move8, State.UP);
		State move10=new State(move9, State.UP);
		State move11=new State(move10, State.UP);
		State move12=new State(move11, State.UP);
		State move13=new State(move12, State.UP);
		State move14=new State(move13, State.UP);
		State move15=new State(move14, State.FIRE);
		State move16=new State(move15, State.UP);
		State move17=new State(move16, State.FIRE);
		
		if(move17!=null){
			
			System.out.print("Complete for real " + move17.currentLevel);
			System.out.print("\n\n\n\n\n");
			while(move17!=null){
				System.out.println(move17);
				move17=move17.previousState;
			}

		}
*/
		 
		System.out.print(State.checkString("AMA", "ALMA"));
			//System.out.print(((63-65)+26)%26);
	}
	
}
