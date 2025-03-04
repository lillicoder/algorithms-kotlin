/*
 * Copyright 2025 Scott Weeden-Moody
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this project except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lillicoder.algorithms.trees

/**
 * Implementation of a [BinaryTree] that uses linked references of [BinaryNode].
 * @param root Root [BinaryNode].
 */
class LinkedBinaryTree<T>(
    private val root: BinaryNode<T>,
) : BinaryTree<T> {
    override fun left(node: Node<T>) = node.toBinaryNode().left

    override fun right(node: Node<T>) = node.toBinaryNode().right

    override fun root() = root

    override fun size() = iterator().asSequence().count() // TODO Find cheaper method

    /**
     * Converts this [Node] to a [BinaryNode].
     * @return Binary node.
     */
    private fun Node<T>.toBinaryNode() = this as BinaryNode<T>

    /**
     * Builder for a [LinkedBinaryTree].
     * @param root Root value.
     */
    class Builder<T>(root: T) {
        private var size = 0L
        private var parent = BinaryNode(size++, root)

        /**
         * Instantiates a new [LinkedBinaryTree] from this builder.
         * @return Linked binary tree.
         */
        fun build() = LinkedBinaryTree(parent)

        /**
         * Type-safe builder for creating a left [BinaryNode] of a binary tree.
         *
         * Example usage:
         * ```
         * left("a") {
         *     left("b") { ... }
         *     right("c") { ... }
         * }
         * ```
         * @param value Value.
         * @param init Function with receiver.
         */
        fun left(
            value: T,
            init: (BinaryNode<T>.() -> Unit)? = null,
        ) = subtree(value, isLeft = true, init)

        /**
         * Type-safe builder for creating a right [BinaryNode] of a binary tree.
         *
         * Example usage:
         * ```
         * right("a") {
         *     left("b") { ... }
         *     right("c") { ... }
         * }
         * ```
         * @param value Value.
         * @param init Function with receiver.
         */
        fun right(
            value: T,
            init: (BinaryNode<T>.() -> Unit)? = null,
        ) = subtree(value, isLeft = false, init)

        /**
         * Adds the given value as a [BinaryNode] to this builder.
         * @param value Value
         * @param isLeft True to add value as left node of its parent, false to add value as right node of its parent.
         * @param init Function with receiver.
         */
        private fun subtree(
            value: T,
            isLeft: Boolean,
            init: (BinaryNode<T>.() -> Unit)? = null,
        ) {
            // Swap parent to new node and update its children
            val previous = parent
            parent = BinaryNode(size++, value)
            init?.invoke(parent)

            // Reset parent and update it's left/right child with the populated node
            val updated = parent
            parent = previous

            // Sub-nodes have been processed, associate them to the parent
            parent =
                when (isLeft) {
                    true -> parent.copy(left = updated)
                    else -> parent.copy(right = updated)
                }
        }
    }
}

/**
 * Type-safe builder for creating a [LinkedBinaryTree].
 *
 * Example usage:
 * ```
 * binaryTree("a") {
 *     left("b") {
 *         left("c")
 *         right("d")
 *     }
 *     right("e") {
 *         left("f") {
 *             left("g")
 *             right("h")
 *         }
 *     }
 * }
 * ```
 * @param root Value for the root of the tree.
 * @param init Function with receiver.
 */
fun <T> binaryTree(
    root: T,
    init: LinkedBinaryTree.Builder<T>.() -> Unit,
): LinkedBinaryTree<T> {
    val builder = LinkedBinaryTree.Builder(root)
    builder.init()
    return builder.build()
}
