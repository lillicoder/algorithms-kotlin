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
 * Implementation of a [Binary Search Tree](https://en.wikipedia.org/wiki/Binary_search_tree).
 * @param tree Backing [BinaryTree].
 */
class BinarySearchTree<T : Comparable<T>>(
    private val tree: BinaryTree<T>,
) : BinaryTree<T> by tree {
    constructor(collection: Iterable<T>) : this(Builder<T>(collection).build())

    /**
     * Builder for a [BinarySearchTree].
     * @param elements Elements to build a tree from.
     */
    internal class Builder<T : Comparable<T>>(
        private val elements: Iterable<T>,
    ) {
        private var size = 0L
        private var parent: BinaryNode<T>? = null

        /**
         * Instantiates a new [BinarySearchTree] from this builder.
         * @return Binary search tree.
         */
        internal fun build(): BinarySearchTree<T> {
            elements.forEach { insert(it) }
            return BinarySearchTree(
                LinkedBinaryTree(parent!!),
            )
        }

        /**
         * Inserts a new [BinaryNode] in this tree for the given value.
         * @param value Value to insert.
         */
        private fun insert(value: T) {
            parent = insert(parent, value)
        }

        /**
         * Inserts a new [BinaryNode] in this tree for the given value starting at the given node.
         * @param node Starting node.
         * @param value Value to insert.
         */
        private fun insert(
            node: BinaryNode<T>?,
            value: T,
        ): BinaryNode<T> =
            when {
                node == null -> BinaryNode(size++, value)
                value <= node.value -> node.copy(left = insert(node.left, value))
                else -> node.copy(right = insert(node.right, value))
            }
    }
}
