import java.util.Scanner;

public class VotingMachine {

	ElectionData electionData;

	Scanner keyboard = new Scanner(System.in);


	/**
	 * screen processes the votes with user input 
	 */
	public void screen() {

		try {
			electionData.printBallot();
			System.out.println("Who do you want to vote for first?");
			String candidate1 = keyboard.next();

			System.out.println("Who do you want to vote for second?");
			String candidate2 = keyboard.next();
			System.out.println("Who do you want to vote for third?");
			String candidate3 = keyboard.next();

			electionData.processVote(candidate1,candidate2,candidate3);
			System.out.println("You voted for " + candidate1+ ", "+ candidate2+ ",and "+ candidate3);
		}
		catch (UnknownCandidateException e) {
			System.out.println("Would you like to add the candidate to the ballot?");
			String answer= keyboard.next();
			if (answer.equals("y")||answer.equals("Y")) {
				addWriteIn(e.getUnknownCandidateName());
				screen();
			}
			else {
				System.out.println("Voting failed. Please try again");
				screen();
			}
		}
		catch (DuplicateVotesException e) {
			System.out.println("You can't vote for the same candidate twice");
			System.out.println("Restarting voting process...");
			screen();
		}


	}

	/**
	 * addWriteIn allows a user to add a candidate to the ballot
	 * @param candidate is the name of the candidate they would like to add to the ballot
	 */
	public void addWriteIn(String candidate) {
		try {
		electionData.addCandidate(candidate);
		}
		catch (CandidateExistsException e) {
			System.out.println("The user already exists");
		}
		finally {
			System.out.println("Candidate was added successfully");
		}

	}
}  