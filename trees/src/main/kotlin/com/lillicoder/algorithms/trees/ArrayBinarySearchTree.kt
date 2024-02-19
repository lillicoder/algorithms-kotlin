package com.lillicoder.algorithms.trees

class ArrayBinarySearchTree<T : Comparable<T>> : Tree<T> {
    // TODO Arrays cannot have generic types without a lot of hassle,
    // using ArrayList to simplify things
    private val nodes: MutableList<T?> = MutableList(10) { null }

    override fun delete(key: T) {
        nodes[0] = delete(0, key)
    }

    private fun delete(node: Int, key: T): T? {
        val current = nodes.getOrNull(node) ?: return null

        val leftPosition = leftChild(node)
        val rightPosition = rightChild(node)
        if (key < current) {
            nodes[leftPosition] = delete(leftPosition, key)
        } else if (key > current) {
            nodes[rightPosition] = delete(rightPosition, key)
        } else {
            val leftChild = nodes.getOrNull(leftPosition)
            val rightChild = nodes.getOrNull(rightPosition)

            if (leftChild == null) return rightChild
            if (rightChild == null) return leftChild

            val successor = successor(rightPosition)
            nodes[node] = key
            nodes[rightPosition] = delete(rightPosition, nodes[successor]!!)
        }

        return current
    }

    override fun insert(key: T) {
        nodes[0] = insert(0, key)
    }

    private fun insert(node: Int, key: T): T {
        ensureCapacity()
        val current = nodes.getOrNull(node) ?: return key

        val leftPosition = leftChild(node)
        val rightPosition = rightChild(node)
        if (key < current) {
            nodes[leftPosition] = insert(leftPosition, key)
        } else {
            nodes[rightPosition] = insert(rightPosition, key)
        }

        return current
    }

    override fun iterator(traversal: Traversal) =
        when (traversal) {
            Traversal.PRE_ORDER -> PreOrderIterator(nodes)
            Traversal.IN_ORDER -> InOrderIterator(root)
            Traversal.POST_ORDER -> PostOrderIterator(root)
        }

    private class PostOrderIterator<T>(
        private val nodes: List<T?>
    ) : Iterator<T> {
        private val stack = ArrayDeque<Int>()
        private var current: Int? = 0
        private var lastVisited: Int? = null

        override fun hasNext() = !stack.isEmpty() || current != null

        override fun next(): T {
            val (key, last) = next(current, lastVisited)
            current = null // TODO Side effect here
            lastVisited = last
            return key
        }

        private fun next(
            index: Int?,
            last: Int?
        ) : Pair<T, Int?> {
            var next = index
            while (hasNext()) {
                if (next != null) {
                    stack.addFirst(next)
                    next = leftChild(next)
                } else {
                    val peek = stack.first()
                    val rightPosition = rightChild(peek)
                    val rightChild = nodes.getOrNull(rightPosition)
                    if (rightChild != null && last != rightPosition) {
                        next = rightPosition
                    } else {
                        val key = nodes[peek]!! // TODO Weird null guard here
                        return Pair(key, stack.removeFirst())
                    }
                }
            }

            throw IllegalStateException("Failed to iterate nodes in post-order traversal.")
        }
    }

    private class PreOrderIterator<T>(
        private val nodes: List<T?>
    ) : Iterator<T> {
        private val stack = ArrayDeque<Int>(0)

        override fun hasNext() = !stack.isEmpty()

        override fun next(): T {
            val index = stack.removeFirst()

            val rightPosition = rightChild(index)
            val right = nodes.getOrNull(rightPosition)
            if (right != null) stack.addFirst(rightPosition)

            val leftPosition = leftChild(index)
            val left = nodes.getOrNull(leftPosition)
            if (left != null) stack.addFirst(leftPosition)

            return nodes[index]!!
        }
    }

    override fun size() = nodes.filterNotNull().size

    /**
     * Ensures this tree's node list has enough capacity for a new node.
     */
    private fun ensureCapacity() {
        val count = size() + 3
        if (count > nodes.size) {
            // Doubles the size of the nodes list with null values
            nodes.addAll(MutableList(nodes.size) { null} )
        }
    }

    /**
     * Gets the index of the left child node for the given index.
     * @param index Index to get the left child of.
     * @return Left child index.
     */
    private fun leftChild(index: Int) = (2 * index) + 1

    /**
     * Gets the index of the right child node for the given index.
     * @param index Index to get the right child of.
     * @return Right child index.
     */
    private fun rightChild(index: Int) = (2 * index) + 2

    /**
     * Gets the index of the parent node for the given index.
     * @param index Index to get parent of.
     * @return Parent node index.
     */
    private fun parent(index: Int) = index - 1 ushr 1

    /**
     * Gets the successor index for the given index. The successor node is
     * the node with the minimum key in a node's right subtree.
     * @param index Index to get successor of.
     * @return Successor index.
     */
    private fun successor(index: Int): Int {
        val leftPosition = leftChild(index)
        return when (nodes.getOrNull(leftPosition)) {
            null -> index
            else -> successor(leftPosition)
        }
    }
}
