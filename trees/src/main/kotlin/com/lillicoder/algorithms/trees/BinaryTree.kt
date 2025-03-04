package com.lillicoder.algorithms.trees

import com.lillicoder.algorithms.collections.dequeOfNotNull

/**
 * Interface for implementations of a [BinaryTree](https://en.wikipedia.org/wiki/Binary_tree).
 */
interface BinaryTree<T> : Iterable<Node<T>> {
    override fun iterator() =
        object : Iterator<Node<T>> {
            private val queue = dequeOfNotNull(root())

            override fun hasNext() = queue.isNotEmpty()

            override fun next(): Node<T> {
                val next = queue.removeFirst()

                // Queue up next level
                val left = left(next)
                if (left != null) queue.add(left)

                val right = right(next)
                if (right != null) queue.add(right)

                return next
            }
        }

    /**
     * Gets the left child of the given [Node].
     * @param node Node.
     * @return Left child or null if there is no such child.
     */
    fun left(node: Node<T>): Node<T>?

    /**
     * Gets the right child of the given [Node].
     * @param node Node.
     * @return Right child or null if there is no such child.
     */
    fun right(node: Node<T>): Node<T>?

    /**
     * Gets the root [Node] of this tree.
     * @return Root or null if this tree is empty.
     */
    fun root(): Node<T>?

    /**
     * Gets the number of nodes in this tree.
     * @return Size.
     */
    fun size(): Int
}
