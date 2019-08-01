
public class CandidateExistsException extends Exception {

	private String name;
	
	CandidateExistsException(String name){
		this.name= name;
	}
	
}
