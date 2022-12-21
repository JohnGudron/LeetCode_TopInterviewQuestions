import kotlin.math.max

class DynamicProgramming {

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/97/dynamic-programming/569/
    fun climbStairs(n: Int): Int {
        val arr = IntArray(n) {0}
        if (n == 1) return 1
        if (n == 2) return 2
        if (n == 3) return 3
        arr[0] = 1
        arr[1] = 2
        arr[2] = 3
        fun dynamic(arr: IntArray, n: Int): Int {
            if (arr[n-1] != 0) return arr[n-1]
            arr[n - 1] = dynamic(arr, n - 1) + dynamic(arr, n - 2)
            return arr[n - 1]
        }
        dynamic(arr, n)
        return arr[n-1]
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/97/dynamic-programming/572/
    fun maxProfit(prices: IntArray): Int {
        var catchBot = -1
        var catchTop = -1
        val result = mutableListOf<Int>()

        for (i in prices) {
            if (catchBot == -1 || i < catchBot && catchTop == -1) {
                catchBot = i
            } else if (i > catchTop) {
                catchTop = i
                result.add(catchTop - catchBot)
                catchTop = -1
            }
        }

        if (catchBot != -1 && catchTop != -1) result.add(catchTop - catchBot)
        return result.max() ?: 0
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/97/dynamic-programming/566/
    fun maxSubArray(nums: IntArray): Int {
        if (nums.size == 1) return nums[0]
        var lateMax = -10001
        var curMax = 0
        for (i in nums) {
            curMax += i
            if (lateMax < curMax) lateMax = curMax
            if (curMax < 0) curMax = 0
        }
        return lateMax
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/97/dynamic-programming/576/
    fun rob(nums: IntArray): Int {
        if (nums.size == 1) return nums[0] // handling simple case
        if (nums.size == 2) return max(nums[0], nums[1]) // handling another simple case

        /* Idea:
        1) We create two variables with first and second elements of an array
        2) We start the for cycle from third to last element of an array
        3) In each step of the cycle we:
           3.1 save maxSum value of two steps before + current arrays' element (currentMax)
           3.2 change value of maxSum value of two steps before by finding max between its current value and maxSum value of one step before
           3.3 prepare maxSum value of one step before (latestMax) for the next iteration
        4) finding max value between our variables last time and returning result

        *
        */
        var preLatestMax = nums[0] // 1
        var latestMax = nums[1] // 1
        for (i in 2..nums.lastIndex) { // 2
            val currentMax = preLatestMax + nums[i] // 3.1
            preLatestMax = max(latestMax, preLatestMax) // 3.2
            latestMax = currentMax // 3.3
        }
        return max(latestMax, preLatestMax)
    }
}

}