package cocagne.paxos.essential;

public class ProposalID {
	

	private int          number;
	private final ProposerImpl prp;
	
	public ProposalID(int number, ProposerImpl prp) {
		this.number = number;
		this.prp    = prp;
	}

	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void incrementNumber() {
		this.number += 1;
	}

	public ProposerImpl getProposer() {
		return prp;
	}
	
	public int compare( ProposalID rhs ) {
		if ( equals(rhs) )
			return -1;
		if ( number < rhs.number || (number == rhs.number && prp.proposerUID.compareTo(rhs.prp.proposerUID) < 0) )
			return -1;
		return 1;
	}
	
	public boolean isGreaterThan( ProposalID rhs ) {
		return compare(rhs) > 0;
	}
	
	public boolean isLessThan( ProposalID rhs ) {
		return compare(rhs) < 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		result = prime * result + ((prp.proposerUID == null) ? 0 : prp.proposerUID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProposalID other = (ProposalID) obj;
		if (number != other.number)
			return false;
		if (prp.proposerUID == null) {
			if (other.prp.proposerUID != null)
				return false;
		} else if (!prp.proposerUID.equals(other.prp.proposerUID))
			return false;
		return true;
	}

	
}
