package com.lillicoder.algorithms.trees

/**
 * [Node] that supports optional left and right children.
 * @param id ID for this node.
 * @param value Value for this node.
 * @param left Optional left child node.
 * @param right Optional right child node.
 */
data class BinaryNode<T>(
    override val id: Long,
    override val value: T,
    val left: BinaryNode<T>? = null,
    val right: BinaryNode<T>? = null,
) : Node<T>
