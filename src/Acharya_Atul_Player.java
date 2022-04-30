package src;
import src.ThreePrisonersDilemma.Player;

public class Acharya_Atul_Player extends Player{

    
	public Acharya_Atul_Player(){
        new ThreePrisonersDilemma().super();
    }

	@Override
    int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {

		// Rule 1
        if (n == 0) return 0; //cooperate by default

		// Rule 2
        if (n >= 99) return 1; // defect in the end

		// Rule 3, 4, 5

		/* 
		If oppHistory[n-1] == oppHistory2[n-1] then either both cooperated or both defected the previous round,
		hence we can just return the action that one of them played in the previous round

		Else if oppHistory[n-1] != oppHistory[n-2] then only 1 of them cooperated the previous round.
		This corresponds to Rule 5: playTolerantAction 
		*/
        return oppHistory1[n-1] == oppHistory2[n-1] ? oppHistory1[n-1]:
		playTolerantAction(n, myHistory, oppHistory1, oppHistory2);
    }

	// Implentation of Rule 5: Selecting a Tolerant Action 
    int playTolerantAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
        int opponent1 = 0;
        int opponent2 = 0;

        for (int i = 0 ; i < n; ++i){
            opponent1 += (oppHistory1[i] == 0? 1 : -1);
            
            opponent2 += (oppHistory2[i] == 0? 1: -1);
        }

		// If the value > 0 then ooponent has cooperated more times than defected
		// Only if both values > 0 then we cooperate, else defect

        if (opponent1 >= 0 && opponent2 >= 0) return 0;
        return 1;

    }
}