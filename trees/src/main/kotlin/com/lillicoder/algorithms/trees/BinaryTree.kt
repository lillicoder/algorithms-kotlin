package com.lillicoder.algorithms.trees

/**
 * Interface for implementations of a [BinaryTree](https://en.wikipedia.org/wiki/Binary_tree).
 */
interface BinaryTree<T> : Tree<T> {
    var root: BinaryNode<T>?

    override fun iterator(traversal: Traversal) =
        when (traversal) {
            Traversal.PRE_ORDER -> PreOrderIterator(root)
            Traversal.IN_ORDER -> InOrderIterator(root)
            Traversal.POST_ORDER -> PostOrderIterator(root)
        }

    /**
     * Implementation of an [Iterator] for a [BinarySearchTree] that uses [Traversal.IN_ORDER] order.
     * @param root Root node of a tree.
     */
    private class InOrderIterator<T>(
        root: BinaryNode<T>?,
    ) : Iterator<T> {
        private val stack = ArrayDeque<BinaryNode<T>>()
        private var current = root

        override fun hasNext() = !stack.isEmpty() || current != null

        override fun next(): T {
            val (key, next) = next(current)
            current = next
            return key
        }

        /**
         * Finds the next in-order value starting from the given [BinaryNode].
         * @param node Starting node.
         * @return Pair of the next in-order value and next node to process.
         */
        private fun next(node: BinaryNode<T>?): Pair<T, BinaryNode<T>?> {
            var next = node
            while (hasNext()) {
                if (next != null) {
                    stack.addFirst(next)
                    next = next.left
                } else {
                    next = stack.removeFirst()
                    val key = next.key
                    next = next.right

                    return Pair(key, next)
                }
            }

            throw IllegalStateException("Failed to iterate nodes in in-order traversal.")
        }
    }

    /**
     * Implementation of an [Iterator] for a [BinarySearchTree] that uses [Traversal.POST_ORDER] order.
     * @param root Root node of a tree.
     */
    private class PostOrderIterator<T>(
        root: BinaryNode<T>?,
    ) : Iterator<T> {
        private val stack = ArrayDeque<BinaryNode<T>>()
        private var current = root
        private var lastVisited: BinaryNode<T>? = null

        override fun hasNext() = !stack.isEmpty() || current != null

        override fun next(): T {
            val (key, last) = next(current, lastVisited)
            current = null // TODO Side effect here
            lastVisited = last
            return key
        }

        private fun next(
            node: BinaryNode<T>?,
            last: BinaryNode<T>?,
        ): Pair<T, BinaryNode<T>?> {
            var next = node
            while (hasNext()) {
                if (next != null) {
                    stack.addFirst(next)
                    next = next.left
                } else {
                    val peek = stack.first()
                    if (peek.right != null && last != peek.right) {
                        next = peek.right
                    } else {
                        val key = peek.key
                        return Pair(key, stack.removeFirst())
                    }
                }
            }

            throw IllegalStateException("Failed to iterate nodes in post-order traversal.")
        }
    }

    /**
     * Implementation of an [Iterator] for a [BinarySearchTree] that uses [Traversal.PRE_ORDER] order.
     * @param root Root node of a tree.
     */
    private class PreOrderIterator<T>(
        root: BinaryNode<T>?,
    ) : Iterator<T> {
        private val stack = ArrayDeque(listOfNotNull(root))

        override fun hasNext() = !stack.isEmpty()

        override fun next(): T {
            val node = stack.removeFirst()
            node.right?.let { stack.addFirst(it) }
            node.left?.let { stack.addFirst(it) }

            return node.key
        }
    }
}
