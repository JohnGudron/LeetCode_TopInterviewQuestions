class Strings {

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/879/
    fun reverseString(s: CharArray) {
        var gap = s.lastIndex
        for (i in s.lastIndex downTo s.size / 2) {
            s[gap - s.lastIndex] = s[i].also { s[i] = s[gap - s.lastIndex] }
            gap++
        }
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/880/
    fun reverse(x: Int): Int {
        val result = x.toString().filter { it.isDigit() }
        if (result.length > 10 || (result.length == 10 && result.reversed().toLong() > Int.MAX_VALUE)) return 0
        return if (x < 0) -(result.reversed().toInt()) else result.reversed().toInt()
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/881/
    fun firstUniqChar(s: String): Int {
        val set = emptyMap<Char, Int>().toMutableMap()
        for (i in s.indices) {
            if (s[i] in set.keys) {
                set[s[i]] = -1
            } else {
                set[s[i]] = i
            }
        }
        return try {
            set[set.keys.first { set[it]!! >= 0 }] ?: -1
        } catch (e: Exception) {
            -1
        }
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/882/
    fun isAnagram(s: String, t: String): Boolean {
        return s.toCharArray().sortedArray().contentEquals(t.toCharArray().sortedArray())
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/883/
    fun isPalindrome(s: String): Boolean {
        val x = s.filter { it.isLetterOrDigit() }.lowercase()
        if (x.isEmpty()) return false
        val a = x.substring(0, x.length / 2)
        val b = x.substring(x.lastIndex / 2 + 1).reversed()
        return a == b
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/884/
    fun myAtoi(s: String): Int {
        val str = s.trimIndent()
        if (str.isEmpty()) return 0
        // val regex = "\\+?-?\\d+\\D*".toRegex()
        val bool = str.first().isDigit() || (str.length > 1 && str.first() in charArrayOf(
            '+',
            '-'
        ) && str[1].isDigit())
        if (!bool) return 0
        val k = "\\+?-?\\d+".toRegex().find(str)?.value
        if (k?.length!! > 10 && k.first().isDigit() && k.first()!='0') {
            return Int.MAX_VALUE
        } else if (k.length > 11 && k.first() == '-' && k[1] != '0') {
            return Int.MIN_VALUE
        }
        val result: Long = "\\+?-?\\d+".toRegex().find(str)?.value?.toLongOrNull() ?: 0 // 1
        return if (result > Int.MAX_VALUE) Int.MAX_VALUE else if (result < Int.MIN_VALUE) Int.MIN_VALUE else result.toInt()
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/885/
    fun strStr(haystack: String, needle: String): Int {
        if (haystack.length == needle.length && haystack == needle) return 0
        if (needle.length == 1) return haystack.indexOf(needle)
        for (i in 0..haystack.lastIndex - needle.length) {
            if (haystack.substring(i, i + needle.length) == needle) return i
        }
        return -1
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/887/
    fun longestCommonPrefix(strs: Array<String>): String {
        strs.sortBy { it.length }
        var result = ""
        for (i in strs[0].indices) {
            var counter = 0
            for (j in strs) {
                if (j[i] == strs[0][i]) counter++ else return result
            }
            if (counter == strs.size) result += strs[0][i]
        }
        return result
    }

}