package com.lillicoder.algorithms.trees

/**
 * A traversal order for a [Tree].
 */
enum class Traversal {
    /**
     * Traverses a tree pre-order: node -> left subtree -> right subtree.
     */
    PRE_ORDER,

    /**
     * Traverses a tree in-order: left subtree -> node -> right subtree.
     */
    IN_ORDER,

    /**
     * Traverses a tree post-order: left subtree -> right subtree -> node.
     */
    POST_ORDER,
}
