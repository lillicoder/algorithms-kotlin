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

import com.lillicoder.algorithms.collections.countNotNull
import com.lillicoder.algorithms.collections.mutableListOf

/**
 * Implementation of a [BinaryTree] that uses a backing array of values.
 * @param nodes Nodes.
 */
class ArrayBinaryTree<T>(
    private val nodes: List<Node<T>?> = emptyList(),
) : BinaryTree<T> {
    override fun left(node: Node<T>) =
        nodes.getOrNull(
            nodes.indexOfFirst { it?.id == node.id }.leftChild(),
        )

    override fun right(node: Node<T>) =
        nodes.getOrNull(
            nodes.indexOfFirst { it?.id == node.id }.rightChild(),
        )

    override fun root() = nodes.firstOrNull()

    override fun size() = nodes.countNotNull()

    /**
     * Builder for an [ArrayBinaryTree].
     * @param root Root value.
     */
    class Builder<T>(root: T) {
        private var size = 0L
        private var parent = BinaryNode(size++, root)

        private val indexByNode = mutableMapOf<Node<T>, Int>(parent to 0)
        private val nodeByIndex = mutableMapOf<Int, Node<T>>(0 to parent)

        /**
         * Instantiates a new [ArrayBinaryTree] from this builder.
         * @return Array binary tree.
         */
        fun build(): ArrayBinaryTree<T> {
            val size = nodeByIndex.keys.maxBy { it } + 1 // Largest used index + 1 since indices are 0-based
            val filtered = mutableListOf<Node<T>?>(size) { null }
            nodeByIndex.map {
                filtered[it.key] = it.value
            }

            return ArrayBinaryTree(filtered)
        }

        /**
         * Type-safe builder for creating a left [Node] of a binary tree.
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
            init: (Node<T>.() -> Unit)? = null,
        ) = subtree(value, isLeft = true, init)

        /**
         * Type-safe builder for creating a right [Node] of a binary tree.
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
            init: (Node<T>.() -> Unit)? = null,
        ) = subtree(value, isLeft = false, init)

        /**
         * Adds the given value as a [Node] to this builder.
         * @param value Value
         * @param isLeft True to add value as left node of its parent, false to add value as right node of its parent.
         * @param init Function with receiver.
         */
        private fun subtree(
            value: T,
            isLeft: Boolean,
            init: (Node<T>.() -> Unit)? = null,
        ) {
            val previous = parent

            // Find the index where the new node will go based
            // on its parent node's position
            val index =
                indexByNode[parent]!!.let {
                    when (isLeft) {
                        true -> it.leftChild()
                        false -> it.rightChild()
                    }
                }
            parent = BinaryNode(size++, value)
            indexByNode[parent] = index
            nodeByIndex[index] = parent
            init?.invoke(parent)

            parent = previous
        }
    }
}

/**
 * Type-safe builder for creating an [ArrayBinaryTree].
 *
 * Example usage:
 * ```
 * arrayBinaryTree("a") {
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
fun <T> arrayBinaryTree(
    root: T,
    init: ArrayBinaryTree.Builder<T>.() -> Unit,
): ArrayBinaryTree<T> {
    val builder = ArrayBinaryTree.Builder(root)
    builder.init()
    return builder.build()
}
