class Arrays {

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/727/
    fun removeDuplicates(nums: IntArray): Int {
        var current = nums[0]
        var k = 1
        for (i in 1..nums.lastIndex) {
            if (nums[i] != current) {
                nums[k] = nums[i]
                k++
                current = nums[i]
            }
        }
        return k
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/564/
    fun maxProfit(prices: IntArray): Int {
        var catchBot = -1
        var catchTop = -1
        var takeProfit = 0
        for (i in prices) {
            if (catchBot == -1 || i < catchBot && catchTop == -1) {
                catchBot = i
            } else if (i > catchTop) {
                catchTop = i
            } else {
                takeProfit += catchTop - catchBot
                catchTop = -1
                catchBot = i
            }
        }
        if (catchTop != -1) takeProfit += catchTop - catchBot

        return takeProfit
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/646/
    fun rotate(nums: IntArray, k: Int) {
        fun reverse (fromIndex:Int,toIndex:Int, array:IntArray) {
            val midPoint = (fromIndex + toIndex) / 2
            if (fromIndex == midPoint) return
            var reverseIndex = toIndex - 1
            for (index in fromIndex until midPoint) {
                val tmp = array[index]
                array[index] = array[reverseIndex]
                array[reverseIndex] = tmp
                reverseIndex--
            }
        }
        nums.reverse()
        reverse(0, k%nums.size,nums)
        reverse(k%nums.size, nums.lastIndex+1, nums)
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/578/
    fun containsDuplicate(nums: IntArray): Boolean {
        for (i in 0 until nums.lastIndex) {
            for (j in (i+1)..nums.lastIndex) {
                if (nums[i]==nums[j]) return true
            }
        }
        return false
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/549/
    fun singleNumber(nums: IntArray): Int {
        var counter = 2
        var result:Int = nums[0]
        nums.sortedArray().forEach {i ->
            if (counter==2) {
                result = i
                counter = 1
            } else if (i == result) {
                counter++
            }
        }
        return result
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/674/
    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
        var counter = true
        if (nums1.size < nums2.size) {
            nums1.forEachIndexed { ind, it ->
                if (it in nums2) {

                    nums2[nums2.indexOf(it)] = -1
                } else nums1[ind] = -1
            }

        } else {
            nums2.forEachIndexed { ind, it ->
                if (it in nums1) {

                    nums1[nums1.indexOf(it)] = -1
                } else nums2[ind] = -1
            }
            counter = false
        }
        return if (counter) nums1.filter { it != -1 }.toIntArray() else nums2.filter { it != -1 }.toIntArray()
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/559/
    fun plusOne(digits: IntArray, num: Int = 1): IntArray {
        for (i in digits.lastIndex downTo 0) {
            if (digits[i] + 1 != 10) {
                digits[i] += 1
                return digits
            } else if (i == 0) {
                digits[i] = 0
                return intArrayOf(1) + digits
            } else digits[i] = 0
        }
        return digits
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/567/
    fun moveZeroes(nums: IntArray): Unit {
        var counter = 0
        for (i in nums.indices) {
            if (nums[i] == 0) counter++
            else if (counter > 0) {
                nums[i - counter] = nums[i]
                nums[i] = 0
            }

        }
        println(nums.joinToString(" "))
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/546/
    fun twoSum(nums: IntArray, target: Int): IntArray {
        for (i in 0 until nums.size) {
            for (j in i + 1 until nums.size) {
                if (nums[i] + nums[j] == target) return intArrayOf(i, j)
            }
        }
        return intArrayOf(0, 0)
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/769/
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        var counter: IntArray
        for (i in board) {
            counter = IntArray(9) {0}
            for (j in i) {
                if (j.isDigit()) {
                    counter[j.toString().toInt() - 1] += 1
                    if (counter[j.toString().toInt() - 1] > 1) return false
                }
            }
        }

        for (i in 0..8) {
            counter = IntArray(9) {0}
            for (j in board) {
                if (j[i].isDigit()) {
                    counter[j[i].toString().toInt() - 1] += 1
                    if (counter[j[i].toString().toInt() - 1] > 1) return false
                }
            }
        }

        for (i in 0..8 step 3) {
            counter = IntArray(9) {0}
            for (j in 0..8 step 3) {
                counter = IntArray(9) {0}
                for (k in j..j + 2) {
                    for (l in i..i + 2) {
                        if (board[k][l].isDigit()) {
                            counter[board[k][l].toString().toInt() - 1] += 1
                            if (counter[board[k][l].toString().toInt() - 1] > 1) return false
                        }

                    }

                }
            }
        }
        return true
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/770/
    fun rotate(matrix: Array<IntArray>): Unit {
        for (i in 0..matrix.lastIndex) {
            for (j in 0 until i) {
                matrix[i][j] = matrix[j][i].also { matrix[j][i] = matrix[i][j] }
            }
        }
        for (i in matrix) i.reverse()

    }

}