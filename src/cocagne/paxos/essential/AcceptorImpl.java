package cocagne.paxos.essential;

import java.util.HashSet;


public class AcceptorImpl implements Acceptor {
	
	public static HashSet<AcceptorImpl> Acceptors = new HashSet<AcceptorImpl>();;
	protected ProposalID         promisedID;
	protected ProposalID         acceptedID;
	protected Object             acceptedValue;
	protected String			 name;
	public LearnerImpl lrn;

	public AcceptorImpl(String name) {
		this.name = name;
		Acceptors.add(this);
	}

	@Override
	public void receivePrepare(String fromUID, ProposalID proposalID) {
		
		if (this.promisedID != null && proposalID.equals(promisedID)) { // duplicate message
			sendPromise(fromUID, proposalID, acceptedID, acceptedValue);
		} else 
		if (this.promisedID == null || proposalID.isGreaterThan(promisedID)) {
			//System.out.println(proposalID.getNumber());
			promisedID = proposalID;
			System.out.println("Promise sent from " + name + " to " + proposalID.getProposer().getProposerUID());
			sendPromise(fromUID, promisedID, acceptedID, acceptedValue);
		}

	}
	
	@Override
	public void sendPromise(String fromUID, ProposalID proposalID, ProposalID prevAcceptedID, Object prevAcceptedValue) {
		
		proposalID.getProposer().receivePromise(this.getName(), proposalID, prevAcceptedID, prevAcceptedValue);
	}

	@Override
	public void receiveAcceptRequest(String fromUID, ProposalID proposalID,
			Object value) {
		if (promisedID == null || proposalID.isGreaterThan(promisedID) || proposalID.equals(promisedID)) {
			promisedID    = proposalID;
			acceptedID    = proposalID;
			acceptedValue = value;
			
			System.out.println(name + ": Receiving Accept Request. New Value: " + value);
			sendAccepted(acceptedID, acceptedValue, lrn);
		}
	}

	@Override
	public void sendAccepted(ProposalID acceptedID, Object acceptedValue, LearnerImpl lrn) {
		acceptedID.getProposer().receiveAccept(name, acceptedID,acceptedValue);
		lrn.receiveAccepted(name, acceptedID, acceptedValue);
		
		
	}
	

	public ProposalID getPromisedID() {
		return promisedID;
	}

	public ProposalID getAcceptedID() {
		return acceptedID;
	}

	public Object getAcceptedValue() {
		return acceptedValue;
	}
	
	public String getName() {
		return name;
	}

}
