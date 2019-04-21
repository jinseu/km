/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func searchBST(root *TreeNode, val int) *TreeNode {
	current := root
	for current != nil {
		if current.Val == val {
			return current
		} else if  val < current.Val {
			current = current.Left
		} else {
			current = current.Right
		}
	}
	return nil
}
