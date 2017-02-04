package cocagne.paxos.essential;


public interface Proposer {
	
	public void setProposal(Object value);
	
	public void prepare();
	
	public void sendAccept(ProposalID proposalID, Object proposalValue);
	
	public void receivePromise(String fromUID, ProposalID proposalID, ProposalID prevAcceptedID, Object prevAcceptedValue);
	
	public void receiveAccept(String fromUID, ProposalID acceptedID, Object acceptedValue);

	
	
}
