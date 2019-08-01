import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashMap;

class ElectionData {

	private HashMap<String,LinkedList<Integer>>votes= new HashMap<String,LinkedList<Integer>>();
	private LinkedList<String> ballot= new LinkedList<String>();


	ElectionData() {}

	/**
	 * printBallot displays the candidates that are in the system
	 */
	public void printBallot() {
		System.out.println("The candidates are ");
		for (String s : ballot) {
			System.out.println(s);
		}
	}

	/**
	 * processVote stores the votes that are made in the data structure
	 * @param choice1 is the first choice vote
	 * @param choice2 is the second choice vote 
	 * @param choice3 is the third choice vote 
	 * @throws UnknownCandidateException when one of the choices is not in the ballot
	 * @throws DuplicateVotesException when there are two votes casted for the same candidate
	 */
	public void processVote(String choice1, String choice2, String choice3) throws UnknownCandidateException,DuplicateVotesException {

		//checks for unknown candidates
		int choice1legit=0;
		int choice2legit=0;
		int choice3legit=0;
		for (String candidate: this.ballot) {
			if (choice1.equals(candidate)) {
				choice1legit++;
			}
			if (choice2.equals(candidate)){
				choice2legit++;
			}
			if (choice3.equals(candidate)) {
				choice3legit++;
			}
		}
		if (choice1legit<1) {
			throw new UnknownCandidateException(choice1);
		}
		if (choice2legit<1) {
			throw new UnknownCandidateException(choice2);
		}
		if (choice3legit<1) {
			throw new UnknownCandidateException(choice3);
		}
		//checks for duplicates  
		if (choice1.equals(choice2)||choice1.equals(choice3)) {
			throw new DuplicateVotesException(choice1);
		}
		else if (choice2.equals(choice1)||choice1.equals(choice3)) {
			throw new DuplicateVotesException(choice2);
		}
		else if (choice3.equals(choice1)||choice3.equals(choice2)) {
			throw new DuplicateVotesException(choice3);
		}

		//gets existing votes for candidate 1
		LinkedList<Integer> candidate1= votes.get(choice1);

		//gets existing votes for candidate 2
		LinkedList<Integer> candidate2= votes.get(choice2);

		//gets existing votes for candidate 3
		LinkedList<Integer> candidate3= votes.get(choice3);

		//first choice
		int firstVote=candidate1.getFirst();
		candidate1.removeFirst();
		candidate1.addFirst(firstVote+1);

		//second choice
		int secondVote=candidate2.get(1);
		candidate2.remove(1);
		candidate2.add(1, secondVote+1);

		//third choice
		int thirdVote= candidate3.get(2);
		candidate3.remove(2);
		candidate3.add(2,thirdVote+1);

		//updating the hashtable
		votes.put(choice1, candidate1);
		votes.put(choice2, candidate2);
		votes.put(choice3, candidate3);
	}

	/**
	 * addCandidate adds a candidate into the data structure 
	 * @param newCandidate is the name of the candidate the user would like to add 
	 * @throws CandidateExistsException when the candidate the user wants to add is already in the system
	 */
	public void addCandidate(String newCandidate)throws CandidateExistsException {
		//throw exception if the candidate is already on the list

		if (candidateInBallot(newCandidate)) {
			throw new CandidateExistsException(newCandidate);
		}
		else {
			LinkedList<Integer> empty= new LinkedList<Integer>();
			empty.add(0);
			empty.add(0);
			empty.add(0);

			votes.put(newCandidate,empty);
			ballot.add(newCandidate);
		}
	}
	/**
	 * findWinnerMostPoints calculates the most points gathered using the points system provided
	 * @return the name of the candidate with the most points 
	 */
	public String findWinnerMostPoints() {
		int mostVotes=0;
		String winner="No winner";
		int points=0;
		for (String candidate:this.ballot) {
			points = (votes.get(candidate).get(0)*3)+(votes.get(candidate).get(1)*2)+(votes.get(candidate).get(2));
			//System.out.println(votes.get(candidate));
			if (mostVotes<points){
				mostVotes=points;
				winner=candidate;
			}	
		}
		return winner;
	}
	/**
	 * findWinnerMostFirstVotes calculates the number of votes casted and calculates the winner 
	 * based on who has more than 50% of the choice1 votes
	 * @return the name of the candidate that has more than 50% of the first choice votes. If no winner is obtained, 
	 * then it will return "Runnoff Requiered"
	 */
	public String findWinnerMostFirstVotes() {
		int TotalFirstPlaceVotes=0;
		String winner="No winner";
		for (String candidate:this.ballot) {
			TotalFirstPlaceVotes=votes.get(candidate).get(0)+TotalFirstPlaceVotes;
		}
		int noWinner=0;
		for (String candidate:this.ballot) {
			if (votes.get(candidate).get(0)>TotalFirstPlaceVotes/2) {
				winner= candidate;
				noWinner++;
			}
		}
		if (noWinner==0) {
			return "Runoff Requiered";
		}
		return winner;
	}
	/**
	 * candidateInBallot is a helper function that determines if a candidate is already in the ballot 
	 * @param gCandidate is the name of the candidate that will be tested 
	 * @return true if the candidate is already in the ballot 
	 */
	public boolean candidateInBallot(String gCandidate) {
		boolean inBallot=false;
		for (String candidate:this.ballot) {
			if (candidate.equals(gCandidate)) {
				return true;
			}
			else {
				inBallot=false;
			}
		}
		return inBallot;
	}
}
