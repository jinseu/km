/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

func mergeTrees(t1 *TreeNode, t2 *TreeNode) *TreeNode {
    currentNode := new(TreeNode)
    if t1 != nil && t2 != nil {
        currentNode.Val = t1.Val + t2.Val
        currentNode.Left = mergeTrees(t1.Left, t2.Left)
        currentNode.Right = mergeTrees(t1.Right, t2.Right)
    }
    if t1 != nil && t2 == nil {
        currentNode.Val = t1.Val
        currentNode.Left = mergeTrees(t1.Left, nil)
        currentNode.Right = mergeTrees(t1.Right, nil)
    }
    if t1 == nil && t2 != nil {
        currentNode.Val = t2.Val
        currentNode.Left = mergeTrees(nil, t2.Left)
        currentNode.Right = mergeTrees(nil,t2.Right)
    }
    if t1 == nil && t2 == nil {
        currentNode = nil
    }
    return currentNode
}
