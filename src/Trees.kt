import kotlin.math.max

class Trees {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/94/trees/555/
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        val array = arrayOf(root.left, root.right)
        var height = 1
        for (i in array) {
            if (i != null) height = max(height, 1 + maxDepth(i))
        }
        return height
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/94/trees/625/

    // here we check for certain node if following nodes are less than or greater than this certain node value
    fun valid(rootVal: Int, node: TreeNode?, comparator: Boolean): Boolean {
        if (node != null) {
            return when (comparator) {
                true -> { // checking less than case
                    if (rootVal <= node.`val`) false
                    else valid(rootVal, node.left, comparator) && valid(rootVal, node.right, comparator)
                }
                else -> { // checking grater than case
                    if (rootVal >= node.`val`) false
                    else valid(rootVal, node.left, comparator) && valid(rootVal, node.right, comparator)
                }
            }
        }
        return true
    }

    // here we check:
    // 1) are all nodes to the left of the root less than root, and are all nodes to the right of the root grater than root
    // 2) check if left and right also valid (recursively)
    fun isValidBST(root: TreeNode?): Boolean {
        if (root != null) {
            return if (valid(root.`val`, root.left, true) && valid(root.`val`, root.right, false)) {
                isValidBST(root.left) && isValidBST(root.right)
            } else false
        }
        return true
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/94/trees/627/
    /*fun isSymmetric(root: TreeNode?): Boolean {

    }*/

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/94/trees/628/
    /*fun levelOrder(root: TreeNode?): List<List<Int>> {

    }*/

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/94/trees/631/
    /*fun sortedArrayToBST(nums: IntArray): TreeNode? {

    }*/
}