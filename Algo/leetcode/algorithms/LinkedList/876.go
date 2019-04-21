/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func middleNode(head *ListNode) *ListNode {
    middle := head
    current := head
    length := 0
    for current != nil {
        length ++
        if length % 2 == 0 {
            middle = middle.Next
        }
        current = current.Next
    }
    return middle
}
