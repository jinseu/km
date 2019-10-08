## Go-Raft

### 节点

在raft协议中一个节点的状态可以用一个raftState来表示。

```
type RaftState uint32

const (
        // Follower is the initial state of a Raft node.
        Follower RaftState = iota

        // Candidate is one of the valid states of a Raft node.
        Candidate

        // Leader is one of the valid states of a Raft node.
        Leader

        // Shutdown is the terminal state of a Raft node.
        Shutdown
)

type raftState struct {
        // currentTerm commitIndex, lastApplied,  must be kept at the top of
        // the struct so they're 64 bit aligned which is a requirement for
        // atomic ops on 32 bit platforms.

        // The current term, cache of StableStore
        currentTerm uint64

        // Highest committed log entry
        commitIndex uint64

        // Last applied log to the FSM
        lastApplied uint64

        // protects 4 next fields
        lastLock sync.Mutex

        // Cache the latest snapshot index/term
        lastSnapshotIndex uint64
        lastSnapshotTerm  uint64

        // Cache the latest log from LogStore
        lastLogIndex uint64
        lastLogTerm  uint64

        // Tracks running goroutines
        routinesGroup sync.WaitGroup

        // The current state
        state RaftState
}
```


注意
```
func (r *raftState) getState() RaftState {
        stateAddr := (*uint32)(&r.state)
        return RaftState(atomic.LoadUint32(stateAddr))
}
```
