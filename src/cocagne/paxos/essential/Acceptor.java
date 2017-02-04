package cocagne.paxos.essential;


public interface Acceptor {
	public void receivePrepare(String fromUID, ProposalID proposalID);
	
	public void receiveAcceptRequest(String fromUID, ProposalID proposalID, Object value);
	
	public void sendPromise(String fromUID, ProposalID proposalID, ProposalID previousID, Object acceptedValue);

	public void sendAccepted(ProposalID proposalID, Object acceptedValue, LearnerImpl lrn);
}
