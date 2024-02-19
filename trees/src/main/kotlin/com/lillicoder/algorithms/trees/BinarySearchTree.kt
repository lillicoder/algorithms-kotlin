package com.lillicoder.algorithms.trees

/**
 * Implementation of a [Binary Search Tree](https://en.wikipedia.org/wiki/Binary_search_tree).
 */
class BinarySearchTree<T : Comparable<T>>(
    collection: Collection<T>? = null,
) : BinaryTree<T> {
    override var root: BinaryNode<T>? = null

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
     * the node with the minimum key in a node's right subtree.
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
