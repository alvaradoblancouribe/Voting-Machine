
public class DuplicateVotesException extends Exception{

	private String name;
	
	DuplicateVotesException (String name){
		this.name=name;
	}
	
	/**
	 * getDuplicateVotesExceptionName returns the name of the candidate that was voted for twice 
	 * @return the name of the candidate that was voted for twice 
	 */
	public String getDuplicateVotesExceptionName() {
		return this.name;
	}
}
