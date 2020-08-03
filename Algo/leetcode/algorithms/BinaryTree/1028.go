/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

func recoverFromPreorder(S string) *TreeNode {
	stack := make([]*TreeNode, len(S))
	top := 0
	index := 0

	for index < len(S) {
		depth := 0
		for S[index] == '-' {
			depth++
			index++
		}
		value := 0
		for ; index < len(S) && S[index] >= '0' && S[index] <= '9'; index++ {
			value = value*10 + int(S[index]-'0')
		}
		current := &TreeNode{
			Val: value,
		}
		if depth == top {
			if top > 0 {
				stack[top-1].Left = current
			}
		} else {
			top = depth
			stack[top-1].Right = current
		}
		stack[top] = current
		top++
	}
	return stack[0]
}