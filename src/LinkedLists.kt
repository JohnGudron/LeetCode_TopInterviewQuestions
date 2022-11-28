class LinkedLists {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    // Below class created only for solving "Merge Two sorted Lists" issue
    class LinkedList1() {
        var head:ListNode? = null
        var tail:ListNode? = null

        fun pushValue(value: Int): LinkedList1 {
            if (head==null) head = ListNode(value)
            head?.next = head
            if (tail==null) tail = head
            return this
        }

        fun append(value: Int):LinkedList1 {
            if (tail==null) pushValue(value)
            tail?.next = ListNode(value)
            tail = tail?.next
            return this
        }
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/93/linked-list/553/
    fun deleteNode(node: ListNode?) {
        node?.`val` = node?.next!!.`val`
        node.next = node.next?.next
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/93/linked-list/603/
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (head?.next == null) return null
        var size = 1
        var list = head
        while (list?.next != null) {
            size++
            list = list.next
        }
        var delete = size - n
        list = head
        while (delete != 0) {
            delete--
            if (list?.next?.next == null) {
                list?.next = null
                return head
            }
            list = list.next
        }

        list?.`val` = list?.next!!.`val`
        list.next = list.next!!.next
        return head
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/93/linked-list/560/
    fun reverseList(head: ListNode?): ListNode? {
        if (head == null) return head
        var cur = head
        var new: ListNode? = cur.next
        var result: ListNode? = null

        while(cur!=null) {
            cur.next = result //1
            result = cur  // 1
            cur = new    // 2345
            new = cur?.next  //345
        }
        return result
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/93/linked-list/771/
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        val result = LinkedList1()
        var first = list1
        var second = list2
        while (first != null || second != null) {
            if (second == null) {
                result.append(first!!.`val`)
                first = first.next
            } else if (first == null) {
                result.append(second.`val`)
                second = second.next
            } else if (first.`val` < second.`val`) {
                result.append(first.`val`)
                first = first.next
            } else {
                result.append(second.`val`)
                second = second.next
            }
        }
        return result.head?.next
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/93/linked-list/772/
    fun isPalindrome(head: ListNode?): Boolean {
        var headNew: ListNode? = ListNode(head!!.`val`)
        var next = head?.next
        while (next != null) {
            var new = ListNode(next.`val`)
            var tail = headNew
            headNew = new
            headNew.next = tail
            next = next.next
        }
        var first = head
        var second = headNew
        while (first != null && second != null) {
            if (first.`val` != second.`val`) return false
            first = first.next
            second = second.next
        }


        return true
    }

    // https://leetcode.com/explore/interview/card/top-interview-questions-easy/93/linked-list/773/
    fun hasCycle(head: ListNode?): Boolean {
        if (head?.next == null) return false
        var running = head
        var catching = head
        while (running?.next != null && catching?.next?.next != null) {
            running = running.next
            catching = catching.next?.next
            if (running == catching) return true
        }
        return false
    }

}