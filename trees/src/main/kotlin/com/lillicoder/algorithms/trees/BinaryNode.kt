package com.lillicoder.algorithms.trees

/**
 * Implementation of a [Node] for a [BinaryTree].
 * @param left Optional left child node.
 * @param right Optional right child node.
 */
data class BinaryNode<T>(
    override val key: T,
    var left: BinaryNode<T>? = null,
    var right: BinaryNode<T>? = null,
    override val children: List<Node<T>> = listOfNotNull(left, right),
) : Node<T>
