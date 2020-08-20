package lesson3

class Trie : AbstractMutableSet<String>(), MutableSet<String> {
    override var size: Int = 0
        private set

    private class Node {
        val children: MutableMap<Char, Node> = linkedMapOf()
    }

    private var root = Node()

    override fun clear() {
        root.children.clear()
        size = 0
    }

    private fun String.withZero() = this + 0.toChar()

    private fun findNode(element: String): Node? {
        var current = root
        for (char in element) {
            current = current.children[char] ?: return null
        }
        return current
    }

    override fun contains(element: String): Boolean =
        findNode(element.withZero()) != null

    override fun add(element: String): Boolean {
        var current = root
        var modified = false
        for (char in element.withZero()) {
            val child = current.children[char]
            if (child != null) {
                current = child
            } else {
                modified = true
                val newChild = Node()
                current.children[char] = newChild
                current = newChild
            }
        }
        if (modified) {
            size++
        }
        return modified
    }

    override fun remove(element: String): Boolean {
        val current = findNode(element) ?: return false
        if (current.children.remove(0.toChar()) != null) {
            size--
            return true
        }
        return false
    }

    /**
     * Итератор для префиксного дерева
     * Сложная
     */
    override fun iterator(): MutableIterator<String> = TODO("TrieIterator(root, ")

    private inner class TrieIterator(node: Node?, prefix: String?) : MutableIterator<String> {
        override fun remove() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
        //private var current: Node<T>? = null

        //private fun findNext(): Node<T>? = TODO()

        override fun hasNext(): Boolean = TODO("nodes.isNotEmpty()")

        override fun next(): String = TODO()

//        override fun remove() {
//            current ?: throw NoSuchElementException()
//            if (current == start) {
//                start = start!!.next
//                size--
//                return
//            }
//            var previous = start
//            while (previous != null && previous.next != current) {
//                previous = previous.next
//            }
//            previous ?: throw NoSuchElementException()
//            previous.next = current!!.next
//            size--
//        }

    }

}