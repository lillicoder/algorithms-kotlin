package com.lillicoder.algorithms.trees

/**
 * Implementation of a [Node] for a [BinaryTree].
 * @param left Optional left child node.
 * @param right Optional right child node.
 */
data class BinaryNode<T>(
    override val key: T,
    val left: BinaryNode<T>? = null,
    val right: BinaryNode<T>? = null,
    override val children: List<Node<T>> = listOfNotNull(left, right),
    override val count: Int = 0,
) : Node<T>
