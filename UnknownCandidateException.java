
public class UnknownCandidateException extends Exception{

	private String name;
	
	UnknownCandidateException(String name){
		this.name=name;
	}
	
	/**
	 * getUnknownCandidateName returns the name of the candidate that was not in the ballot 
	 * @return the name of the candidate that was not in the ballot 
	 */
	public String getUnknownCandidateName() {
		return this.name;
	}
	
	
}
