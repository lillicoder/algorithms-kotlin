package com.lillicoder.algorithms.trees

/**
 * Implementation of a [Binary Search Tree](https://en.wikipedia.org/wiki/Binary_search_tree).
 */
class BinarySearchTree<T : Comparable<T>>(
    collection: Collection<T>? = null,
) : Tree<T> {
    private var root: BinaryNode<T>? = null

    init {
        collection?.forEach { insert(it) }
    }

    override fun delete(key: T) {
        root = delete(root, key)
    }

    /**
     * Deletes the [BinaryNode] from this tree matching the given key starting at the given node.
     * @param node Starting node.
     * @param key Key to delete.
     */
    private fun delete(
        node: BinaryNode<T>?,
        key: T,
    ): BinaryNode<T>? {
        if (node == null) return null

        if (key < node.key) {
            node.left = delete(node.left, key)
        } else if (key > node.key) {
            node.right = delete(node.right, key)
        } else {
            if (node.left == null) return node.right
            if (node.right == null) return node.left

            // Time to delete; find successor and make a new node with that key and
            // a fixed right subtree by deleting the moved successor key
            val successor = successor(node.right!!)
            return BinaryNode(successor.key, node.left, delete(node.right, successor.key))
        }

        return node
    }

    override fun insert(key: T) {
        root = insert(root, key)
    }

    /**
     * Inserts a new [BinaryNode] in this tree for the given key starting at the given node.
     * @param node Starting node.
     * @param key Key to insert.
     */
    private fun insert(
        node: BinaryNode<T>?,
        key: T,
    ): BinaryNode<T> {
        if (node == null) return BinaryNode(key)

        if (key < node.key) {
            node.left = insert(node.left, key)
        } else {
            node.right = insert(node.right, key)
        }

        return node
    }

    override fun iterator(traversal: Traversal) =
        when (traversal) {
            Traversal.PRE_ORDER -> PreOrderIterator(root)
            Traversal.IN_ORDER -> InOrderIterator(root)
            Traversal.POST_ORDER -> PostOrderIterator(root)
        }

    override fun search(key: T): T? {
        // Can rely on BST properties to more efficiently search subtrees
        // compared to the default implementation in the interface
        var node = root
        while (node != null && key != node.key) {
            node = if (key < node.key) node.left else node.right
        }

        return null
    }

    /**
     * Gets the successor [BinaryNode] for the given node. The successor node is
     * the node with he minimum key in a node's right subtree.
     * @param node Node to get successor of.
     * @return Successor node.
     */
    private fun successor(node: BinaryNode<T>): BinaryNode<T> {
        return when (node.left) {
            null -> node
            else -> successor(node.left!!)
        }
    }
}

/**
 * Implementation of a [Node] for a [BinaryNode].
 * @param left Optional left child node.
 * @param right Optional right child node.
 */
data class BinaryNode<T>(
    override val key: T,
    var left: BinaryNode<T>? = null,
    var right: BinaryNode<T>? = null,
    override val children: List<Node<T>> = listOfNotNull(left, right),
) : Node<T>

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
