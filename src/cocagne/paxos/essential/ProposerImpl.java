package cocagne.paxos.essential;
import java.util.*;


public class ProposerImpl implements Proposer {
	
    protected String       proposerUID;
    protected final int           quorumSize;

    protected ProposalID          proposalID;
    protected Object              proposedValue      = null;
    protected ProposalID          lastAcceptedID     = null;
    protected HashSet<String>     promisesReceived   = new HashSet<String>();
    
    public ProposerImpl(String proposerUID, int quorumSize, int num) {
		
		this.proposerUID = proposerUID;
		this.quorumSize  = quorumSize;
		this.proposalID  = new ProposalID(num, this);
	}

	@Override
	public void setProposal(Object value) {
		//if ( proposedValue == null )
			proposedValue = value;
	}

	@Override
	public void prepare() {
		promisesReceived.clear();
		
		proposalID.incrementNumber();
		
		Iterator<AcceptorImpl> itr = AcceptorImpl.Acceptors.iterator(); 
		while(itr.hasNext()) {
			AcceptorImpl element = (AcceptorImpl) itr.next();
			System.out.println("Prepare from " + proposalID.getProposer().getProposerUID() + " is sent to acceptor " + element.getName());
			element.receivePrepare(proposalID.getProposer().getProposerUID(), proposalID);
		}
		
		
	}

	@Override
	public void receivePromise(String fromUID, ProposalID proposalID,
			ProposalID prevAcceptedID, Object prevAcceptedValue) {
		if ( !proposalID.equals(this.proposalID) || promisesReceived.contains(fromUID) ) 
			return;
		
        promisesReceived.add( fromUID );
        // Updating its prev information
        if (lastAcceptedID == null || prevAcceptedID.isGreaterThan(lastAcceptedID))
        {
        	lastAcceptedID = prevAcceptedID;
        	
        	//if (prevAcceptedValue != null)
        	//	proposedValue = prevAcceptedValue;
        	
        }
        
        // send the value
        if (promisesReceived.size() == quorumSize)
        	if (proposedValue != null) {
        		sendAccept(this.proposalID, proposedValue);
      
        	}
        
	}
	
	public void sendAccept(ProposalID proposalID, Object proposalValue) {
		System.out.println("Accept is sent from " + proposalID.getProposer().getProposerUID() + " to Acceptors");
		Iterator<AcceptorImpl> itr = AcceptorImpl.Acceptors.iterator(); 
		while(itr.hasNext()) {
			AcceptorImpl element = (AcceptorImpl) itr.next();
			element.receiveAcceptRequest(getProposerUID(), proposalID, proposalValue);
		}
		
	}
	
	public void receiveAccept(String fromUID, ProposalID acceptedID, Object acceptedValue) {
		
		// We don't need to implement this function; however, it can be used for more practical implementations.  
		//System.out.println(proposerUID + ": " + fromUID + " accepted " + acceptedValue);
		
	}



	public String getProposerUID() {
		return proposerUID;
	}

	public int getQuorumSize() {
		return quorumSize;
	}

	public ProposalID getProposalID() {
		return proposalID;
	}

	public Object getProposedValue() {
		return proposedValue;
	}

	public ProposalID getLastAcceptedID() {
		return lastAcceptedID;
	}
	
	public int numPromises() {
		return promisesReceived.size();
	}


}
