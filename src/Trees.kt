import kotlin.math.max

class Trees {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    class Node(var value: Int) {
        var next: Node? = null
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
    fun isSymmetric(root: TreeNode?): Boolean {
        val h = maxDepth(root)
        if (h == 1) return true
        if (h == 2) return root?.right?.`val` == root?.left?.`val`
        val leftArr = mutableListOf<Int> ()
        val rightArr = mutableListOf<Int> ()
        fun add (node: TreeNode?, height: Int, direction: String) {
            if (direction == "left") {
                leftArr.add(node?.`val` ?: -101)
            } else {
                rightArr.add(node?.`val` ?: -101)
            }
            if (height == h ) return
            if (direction == "left") {
                add(node?.left, height + 1, direction)
                add(node?.right, height + 1, direction)
            } else {
                add(node?.right, height + 1, direction)
                add(node?.left, height + 1, direction)
            }

        }

        add(root?.left, 2, "left")
        add(root?.right, 2, "right")

        return leftArr == rightArr
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/94/trees/628/
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()
        val result = mutableListOf<MutableList<Int>>()
        val level = 0
        fun add (node: TreeNode?, level: Int) {
            if (node == null) return
            if (result.getOrNull(level) == null ) result.add(mutableListOf())
            result[level].add(node.`val`)
            add(node.left, level + 1)
            add(node.right, level + 1)
        }
        add(root, level)
        return result
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/94/trees/631/
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        if (nums.size == 1) return TreeNode(nums[0])
        if (nums.isEmpty()) return null
        val root = TreeNode(nums[nums.size/2])
        root.left = sortedArrayToBST(nums.sliceArray(0 until (nums.size/2)))
        root.right = sortedArrayToBST(nums.sliceArray((nums.size/2 + 1)..nums.lastIndex))

        /*fun distrib (node:TreeNode) {
            root.left = sortedArrayToBST(nums.sliceArray(0 until (nums.size/2)))
            root.right = sortedArrayToBST(nums.sliceArray((nums.size/2 + 1)..nums.lastIndex))
        }*/

        return root
    }
    // -9,-8,-7,-6,-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7,8,9

}