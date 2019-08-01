//Isabel Alvarado Blanco Uribe

import static org.junit.Assert.*;

import org.junit.Test;

public class ElectionDataTest {
	ElectionData Setup1 () throws CandidateExistsException {

	    ElectionData ED = new ElectionData();
	  
	    // put candidates on the ballot

	    ED.addCandidate("gompei");
	    ED.addCandidate("husky");
	    ED.addCandidate("ziggy");
	    // cast votes

	    try {

	      ED.processVote("gompei", "husky", "ziggy");
	      ED.processVote("gompei", "ziggy", "husky");
	      ED.processVote("husky", "gompei", "ziggy");
	     

	    } catch (Exception e) {}

	    return(ED);

	  }

	  // now run a test on a specific election
	  @Test
	  public void testMostFirstWinner () throws CandidateExistsException {
		//Tests for addCandidate, processVote, and findWinnerMostFirstPoints
	    assertEquals ("gompei", Setup1().findWinnerMostFirstVotes());
	    
	    //Tests for addCandidate, proccessVote, and findWinnerMostPoints
	    assertEquals ("gompei", Setup1().findWinnerMostPoints()); 
	  }

	//Tests for UnknownCandidateException and for both exceptions
	  @Test(expected=UnknownCandidateException.class)
	  public void testSampleMethod1() throws UnknownCandidateException, DuplicateVotesException, CandidateExistsException
	  {
	  	Setup1().processVote("gompeiii","husky","ziggy");
	  	Setup1().processVote("gompei", "husky", "huskkky");
	  	Setup1().processVote("gompei", "husffdfdfky", "huskkky");
	  	Setup1().processVote("gompei", "husffdfdfky", "husky");
	   
	  }
	  //Tests for DuplicateVotesException
	  @Test(expected=DuplicateVotesException.class)
	  public void testSampleMethod2() throws UnknownCandidateException, DuplicateVotesException, CandidateExistsException
	  {
	  	Setup1().processVote("gompei","gompei","gompei");
	  	Setup1().processVote("gompei", "gompei", "husky");
	  	Setup1().processVote("husky", "gompei", "husky");
	  	Setup1().processVote("gompei", "husky", "husky");
	   
	  }

}
