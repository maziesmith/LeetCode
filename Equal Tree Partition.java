/*
Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees which have the equal sum of values after removing exactly one edge on the original tree.

Example 1:
Input:     
    5
   / \
  10 10
    /  \
   2   3

Output: True
Explanation: 
    5
   / 
  10
      
Sum: 15

   10
  /  \
 2    3

Sum: 15
Example 2:
Input:     
    1
   / \
  2  10
    /  \
   2   20

Output: False
Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
Note:
The range of tree node value is in the range of [-100000, 100000].
1 <= n <= 10000
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean checkEqualTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        Set<Integer> sum = new HashSet<>();
        int totalSum = getTotalSum(root, sum, null);
        if (totalSum % 2 != 0) {
            return false;
        }
        return sum.contains(totalSum / 2);
    }
    
    private int getTotalSum(TreeNode pNode, Set<Integer> sum, TreeNode parentNode) {
        if (pNode == null) {
            return 0;
        }
        int curSum = pNode.val + getTotalSum(pNode.left, sum, pNode) + getTotalSum(pNode.right, sum, pNode);
        if (parentNode != null) {
            sum.add(curSum);
        }
        return curSum;
    }
}