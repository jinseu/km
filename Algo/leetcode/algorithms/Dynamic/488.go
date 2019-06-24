import (
    "fmt"
)

type CurrentGame struct {
    Board  string
    Hand   string
    RoundCnt int
}

var gameMap map[string]CurrentGame

func removeHand(hand string, j int) string {
    return hand[:j] + hand[j+1:]
}

func add2Board(board string, i int, sub string) string {
    return board[:i] + sub + board[i:]
}

func tryRemoveBoard(board string) string {
    for i := 0; i < len(board); {
        j := i + 1
        for ; j < len(board); j++ {
            if board[i] != board[j] {
                break
            }
        }
        if j - i > 2 {
            res := board[:i] + board[j:]
            return tryRemoveBoard(res)
        }
        i = j
    }
    return board
}

func search(board string, hand string, roundCnt int) int {
    key := fmt.Sprintf("%s-%s", board, hand)
    if gameState, exist := gameMap[key]; exist {
        if gameState.RoundCnt == -1 {
            return -1
        }
        if gameState.RoundCnt <= roundCnt {
            return gameState.RoundCnt
        }
    }
    if len(board) == 0 {
        return roundCnt
    }
    res := -1

    for i := 0; i < len(board); i++ {
        for j := 0 ; j < len(hand); j++{
            if hand[j] == board[i] {
                newBoard := add2Board(board, i, hand[j:j+1])
                newBoard = tryRemoveBoard(newBoard)
                newHand := removeHand(hand, j)
                minBall := search(newBoard, newHand, roundCnt + 1)
                if minBall > 0 && (res == -1 || minBall < res){
                    res = minBall
                } 
            }
        }
    }
    gameMap[key] = CurrentGame{
        Board: board,
        Hand: hand,
        RoundCnt: res,
    }
    return res
}

func findMinStep(board string, hand string) int {
    gameMap = make(map[string]CurrentGame)
    return search(board, hand, 0)
}
