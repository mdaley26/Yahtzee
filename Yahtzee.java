import java.util.Arrays;

public class Yahtzee {
	
	private Dice[] dice;
	

	
	public Yahtzee() {
		// initialize the dice ARRAY and roll them for random values
		
		dice = new Dice[5]; // created empty array that dice will be in with 5 null spaces
		
		for (int i = 0; i < 5; i++) {
			dice[i] = new Dice();
		}
		for (int i = 0; i < 5; i++) {
			dice[i].roll();
		}		
	}
	
	public Yahtzee(Dice[] dice) {
		// initialize the dice array with the given argument
		this.dice = dice;
		// MIGHT NEED TO PUT THIS. DICE FOR EVERYTHING
	}
			
	public int[] getValueCount() {
		// count how many dice show each of the possible values from 1-6 and record all of them in an integer array
		int[] valueCountArray = {0, 0, 0, 0, 0, 0};
		// iterate through each value in the dice array
		for (int i=0; i < 5; i++) {
			// adding to the counter if one of the matching numbers is found
			if (dice[i].getValue() == 1) {
				valueCountArray[0] = valueCountArray[0] + 1;
			}
			if (dice[i].getValue() == 2) {
				valueCountArray[1] = valueCountArray[1] + 1;
			}
			if (dice[i].getValue() == 3) {
				valueCountArray[2] = valueCountArray[2] + 1;
			}
			if (dice[i].getValue() == 4) {
				valueCountArray[3] = valueCountArray[3] + 1;
			}
			if (dice[i].getValue() == 5) {
				valueCountArray[4] = valueCountArray[4] + 1;
			}
			if (dice[i].getValue() == 6) {
				valueCountArray[5] = valueCountArray[5] + 1;
			}	
		}
		// returning the integer array of counters
		return valueCountArray;

		// return this array with the 6 counters
		
	}
	
	public int[] getScoreOptions() {
		// create integer array with 13 elements
		int[] scoreOptions = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] values = new int[6];
		boolean threeDieCounter = false;
		boolean twoDieCounter = false;
		int smallStraightCounter = 0;
		int largeStraightCounter = 0;
		values = getValueCount();
		// aces option

		scoreOptions[0] = values[0];
		
		// twos option
		scoreOptions[1] = values[1]*2;
		
		// threes option
		scoreOptions[2] = values[2]*3;
		
		// fours option
		scoreOptions[3] = values[3]*4;
				
		// fives option
		scoreOptions[4] = values[4]*5;
		
		// sixes option
		scoreOptions[5] = values[5]*6;
	
		// 3 of a kind option
		for (int i=0; i < 6; i++) {
			if (values[i] >= 3) {
				scoreOptions[6] = (values[0]*1 + values[1]*2 + values[2]*3 + values[3]*4 + values[4]*5 + values[5]*6);
			}
		}
		
		// 4 of a kind option
		for (int i=0; i < 6; i++) {
			if (values[i] >= 4) {
				scoreOptions[7] = (values[0]*1 + values[1]*2 + values[2]*3 + values[3]*4 + values[4]*5 + values[5]*6);
			}
		}
		
		// full house option
		for (int i=0; i < 6; i++) {
			if (values[i] == 3) {
				threeDieCounter = true;
			}
			if (values[i] == 2) {
				twoDieCounter = true;
			}
		}
		if (twoDieCounter && threeDieCounter) {
			scoreOptions[8] = 25;
		}
		
		// small straight option
		for (int i=0; i < 6; i++) {
			if (values[i] > 0) {
				smallStraightCounter = smallStraightCounter + 1;
			}
			if (smallStraightCounter == 4) {
				scoreOptions[9] = 30;	
			}
			if (values[i] == 0) {
				smallStraightCounter = 0;
			}
		}
				
		// large straight option
		for (int i=0; i < 6; i++) {
			if (values[i] > 0) {
				largeStraightCounter = largeStraightCounter + 1;
			}
			if (largeStraightCounter == 5) {
				scoreOptions[10] = 40;
			}
			if (values[i] == 0) {
				largeStraightCounter = 0;
			}
		}
		
		// yahtzee option
		for (int i=0; i < 6; i++) {
			if (values[i] == 5) {
				scoreOptions[11] = 50;
			}
		}
		
		// chance option
		scoreOptions[12] = (values[0]*1 + values[1]*2 + values[2]*3 + values[3]*4 + values[4]*5 + values[5]*6);
		
		// returning the score options
		return scoreOptions;
		
	}
	
	public int[] score() {
		//determining maximum value and position of maximum value from array of possible scores
		int[] maxValueIndex = new int[2]; 
		int maximum = 0;
		int positionOfMax = 0;
		int[] findBestOption;
		findBestOption = getScoreOptions();
		
		// iterating through each element in the possible scores and continually updating the max
		for (int i=0; i < 13; i++) {
			if (findBestOption[i] > maximum) {
				maximum = findBestOption[i];
				positionOfMax = i;
			}
		}
		maxValueIndex[0] = maximum;
		maxValueIndex[1] = positionOfMax;
			
		// returning the integer array containing the maximum value (index 0) and the corresponding index (index 1)
		return maxValueIndex;
		
	}
	
	public boolean equals(Yahtzee givenDice) {
		// compare given object with the this.dice object to see if they are equal
		// equality counts not matter the order of the dice
		boolean equals = false;
		int[] valueCountGiven;
		int[] valueCountOther;
		valueCountGiven = givenDice.getValueCount();
		valueCountOther = getValueCount();
		if (Arrays.equals(valueCountGiven, valueCountOther)) {
			equals = true;
		}
		
		return equals;
		
	}
	
	public String toString() {
		// return a string of the dice values following "Dice: "
		return "Dice: {" + dice[0].getValue() + ", "+ dice[1].getValue() + ", " + dice[2].getValue() + ", "+ dice[3].getValue() + ", " + dice[4].getValue() + "}";
		
	}
	
}	
