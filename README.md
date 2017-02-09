# Paxos

Summary: Java implementation of Paxos protocol

The goal of the Paxos protocol is for some number of peers to reach consensus on a value. Paxos guarantees that if one peer believes some value has been agreed upon by a majority, the majority will never agree on a different value. The protocol is designed so that any agreement must go through a majority of nodes. Any future attempts at agreement, if successful must also go through at least one of those nodes. Thus, any node that proposes after a decision has been reached must communicate with a node in the majority. The protocol guarantees that it will learn the previously agreed upon value from that majority.
