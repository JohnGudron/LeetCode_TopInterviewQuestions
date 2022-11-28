class Strings {

    fun isAnagram(s: String, t: String): Boolean {
        return s.toCharArray().sortedArray().contentEquals(t.toCharArray().sortedArray())
    }

    fun isPalindrome(s: String): Boolean {
        val x = s.filter { it.isLetterOrDigit() }.lowercase()
        if (x.isEmpty()) return false
        val a = x.substring(0, x.length / 2)
        val b = x.substring(x.lastIndex / 2 + 1).reversed()
        return a == b
    }

    fun strStr(haystack: String, needle: String): Int {
        if (haystack.length == needle.length && haystack == needle) return 0
        if (needle.length == 1) return haystack.indexOf(needle)
        for (i in 0..haystack.lastIndex - needle.length) {
            if (haystack.substring(i, i + needle.length) == needle) return i
        }
        return -1
    }

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
}