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

package com.lillicoder.algorithms.trees.traversal

import com.lillicoder.algorithms.collections.dequeOfNotNull
import com.lillicoder.algorithms.trees.BinaryTree
import com.lillicoder.algorithms.trees.Node

/**
 * Pre-order [Traversal] of a given [BinaryTree].
 * @param tree Tree to traverse.
 */
class PreOrderTraversal<T>(private val tree: BinaryTree<T>) : Traversal<T> {
    override fun path(
        start: Node<T>,
        destination: Node<T>?,
    ): List<Node<T>> {
        val visited = mutableListOf<Node<T>>()

        val stack = dequeOfNotNull(tree.find { it == start })
        while (stack.isNotEmpty()) {
            val current = stack.removeFirst()
            visited.add(current)

            if (current == destination) return visited

            if (tree.right(current) != null) {
                stack.addFirst(tree.right(current)!!)
            }

            if (tree.left(current) != null) {
                stack.addFirst(tree.left(current)!!)
            }
        }

        return when (destination) {
            null -> visited
            else -> emptyList() // Could not find destination, return empty
        }
    }
}
