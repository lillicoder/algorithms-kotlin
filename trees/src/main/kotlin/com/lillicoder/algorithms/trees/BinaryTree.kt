package com.lillicoder.algorithms.trees

/**
 * Interface for implementations of a [BinaryTree](https://en.wikipedia.org/wiki/Binary_tree).
 */
interface BinaryTree<T> : Tree<T> {
    /**
     * Gets the left child of the given node.
     * @param key Key.
     * @return Left child or null if there is no such node.
     */
    fun left(key: T): T?

    /**
     * Gets the right child of the given node.
     * @param key Key.
     * @return Right child or null if there is no such node.
     */
    fun right(key: T): T?

    override fun iterator(traversal: Traversal) =
        when (traversal) {
            Traversal.IN_ORDER -> InOrderIterator(this)
            Traversal.POST_ORDER -> PostOrderIterator(this)
            Traversal.PRE_ORDER -> PreOrderIterator(this)
        }

    /**
     * Implementation of an [Iterator] for a [BinarySearchTree] that uses [Traversal.IN_ORDER] order.
     * @param root Root node of a tree.
     */
    private class InOrderIterator<T>(
        private val tree: BinaryTree<T>,
        root: T? = tree.root(),
    ) : Iterator<T> {
        private val stack = ArrayDeque<T>()
        private var current = root

        override fun hasNext() = stack.isNotEmpty() || current != null

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
        private fun next(node: T?): Pair<T, T?> {
            var next = node
            while (hasNext()) {
                if (next != null) {
                    stack.addFirst(next)
                    next = tree.left(next)
                } else {
                    next = stack.removeFirst()
                    val key = next
                    next = tree.right(next)

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
        private val tree: BinaryTree<T>,
        root: T? = tree.root(),
    ) : Iterator<T> {
        private val stack = ArrayDeque<T>()
        private var current = root
        private var lastVisited: T? = null

        override fun hasNext() = stack.isNotEmpty() || current != null

        override fun next(): T {
            val (key, last) = next(current, lastVisited)
            current = null // TODO Side effect here
            lastVisited = last
            return key
        }

        private fun next(
            node: T?,
            last: T?,
        ): Pair<T, T?> {
            var next = node
            while (hasNext()) {
                if (next != null) {
                    stack.addFirst(next)
                    next = tree.left(next)
                } else {
                    val peek = stack.first()
                    val right = tree.right(peek)
                    if (right != null && last != right) {
                        next = right
                    } else {
                        return Pair(peek, stack.removeFirst())
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
        private val tree: BinaryTree<T>,
        root: T? = tree.root(),
    ) : Iterator<T> {
        private val stack = ArrayDeque(listOfNotNull(root))

        override fun hasNext() = stack.isNotEmpty()

        override fun next(): T {
            val node = stack.removeFirst()
            tree.right(node)?.let { stack.addFirst(it) }
            tree.left(node)?.let { stack.addFirst(it) }

            return node
        }
    }
}
