package com.lillicoder.algorithms.trees

/**
 * A single [Node] for a [Tree].
 */
interface Node<T> {
    /**
     * Key for this node.
     */
    val key: T

    /**
     * Children for this node.
     */
    val children: List<Node<T>>
}
