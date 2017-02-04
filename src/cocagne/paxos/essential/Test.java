package cocagne.paxos.essential;

public class Test {
public static void main(String[] args)
{
	/* Proposers:	
	A: Prepare (2)
	B: Prepare (4)
	C: Prepare (6)
	A: Prepare (8)
	
	Acceptors:	X, Y, Z
	Learner: L	*/
	
	// adding proposer node: ProposerImpl obj1= new ProposerImpl("A", 2, 19);
	// adding acceptor node: AcceptorImpl acc1 = new AcceptorImpl("X");
	// adding learner node: LearnerImpl L = new LearnerImpl(2);
		
	
		
	ProposerImpl obj1= new ProposerImpl("A", 2, 0);
	ProposerImpl obj2= new ProposerImpl("B", 2, 0);
	ProposerImpl obj3= new ProposerImpl("C", 3, 0);
	ProposerImpl obj4= new ProposerImpl("D", 3, 0);
	ProposerImpl obj5= new ProposerImpl("E", 3, 0);
	LearnerImpl L = new LearnerImpl(2);
	AcceptorImpl acc1 = new AcceptorImpl("X");	acc1.lrn = L;
	AcceptorImpl acc2 = new AcceptorImpl("Y");	acc2.lrn = L;
	AcceptorImpl acc3 = new AcceptorImpl("Z");	acc3.lrn = L;
	
	long t1 = System.nanoTime();
	
	// 1. A proposing 2
	obj1.setProposal(2);
	obj1.getProposalID().setNumber(1);
	obj1.prepare();
	
	long t2 = System.nanoTime();
	System.out.println(t2-t1);
	System.out.println();
	
	// 2. B proposing 4
	obj2.setProposal(4);
	obj2.getProposalID().setNumber(2);
	obj2.prepare();
	
	t1 = System.nanoTime();
	System.out.println(t1-t2);
	System.out.println();
	
	// 3. C proposing 6
	obj3.setProposal(6);
	obj3.getProposalID().setNumber(3);
	obj3.prepare();
	
	t2 = System.nanoTime();
	System.out.println(t2-t1);
	System.out.println();
	
	// 4. D proposing 10, will be ignored
	obj4.setProposal(10);
	obj4.getProposalID().setNumber(2);
	obj4.prepare();
	
	t1 = System.nanoTime();
	System.out.println(t1-t2);
	System.out.println();
		
	
	// 5. A proposing 8 and E proposing 6
	obj1.setProposal(8);
	obj5.setProposal(6);
	obj1.getProposalID().setNumber(4);
	obj5.getProposalID().setNumber(3);
	obj1.prepare();
	obj5.prepare();
	
	t2 = System.nanoTime();
	System.out.println(t2-t1);
	System.out.println();
	
	// 6a. A proposing
	obj1.setProposal(5);
	obj1.getProposalID().setNumber(5);
	obj1.prepare();
	
	t1 = System.nanoTime();
	System.out.println(t1-t2);
	System.out.println();
	
	// 6b. A, B proposing
	obj1.setProposal(6);
	obj2.setProposal(7);
	obj1.getProposalID().setNumber(6);
	obj2.getProposalID().setNumber(7);
	obj1.prepare();
	obj2.prepare();
	
	t2 = System.nanoTime();
	System.out.println(t2-t1);
	System.out.println();
	
	// 6c. A, B, C proposing
	obj1.setProposal(8);
	obj2.setProposal(8);
	obj4.setProposal(9);
	obj1.getProposalID().setNumber(7);
	obj2.getProposalID().setNumber(5);
	obj4.getProposalID().setNumber(6);
	obj1.prepare();
	obj2.prepare();
	obj4.prepare();
	
	t1 = System.nanoTime();
	System.out.println(t1-t2);
	System.out.println();
	
	// 6d. A, B, C, D proposing
	obj1.setProposal(9);
	obj2.setProposal(10);
	obj3.setProposal(9);
	obj4.setProposal(10);
	obj1.getProposalID().setNumber(9);
	obj2.getProposalID().setNumber(10);
	obj3.getProposalID().setNumber(10);
	obj4.getProposalID().setNumber(9);
	obj1.prepare();
	obj2.prepare();
	obj3.prepare();
	obj4.prepare();
	
	t2 = System.nanoTime();
	System.out.println(t2-t1);
	System.out.println();
	
	// 6e. A, B, C, D, E proposing
	obj1.setProposal(10);
	obj2.setProposal(11);
	obj3.setProposal(12);
	obj4.setProposal(13);
	obj5.setProposal(14);
	obj1.getProposalID().setNumber(15);
	obj2.getProposalID().setNumber(16);
	obj3.getProposalID().setNumber(17);
	obj4.getProposalID().setNumber(18);
	obj5.getProposalID().setNumber(19);
	obj1.prepare();
	obj2.prepare();
	obj3.prepare();
	obj4.prepare();
	obj5.prepare();
	
	t1 = System.nanoTime();
	System.out.println(t1-t2);
	System.out.println();

	
}
}