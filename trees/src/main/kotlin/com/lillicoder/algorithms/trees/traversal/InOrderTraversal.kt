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

import com.lillicoder.algorithms.collections.dequeOf
import com.lillicoder.algorithms.trees.BinaryTree
import com.lillicoder.algorithms.trees.Node

/**
 * In-order [Traversal] of a given [BinaryTree].
 * @param tree Tree to traverse.
 */
class InOrderTraversal<T>(private val tree: BinaryTree<T>) : Traversal<T> {
    override fun path(
        start: Node<T>,
        destination: Node<T>?,
    ): List<Node<T>> {
        val visited = mutableListOf<Node<T>>()

        val stack = dequeOf<Node<T>>()
        var current: Node<T>? = tree.find { it == start } // Ensure node is in the tree

        while (stack.isNotEmpty() || current != null) {
            if (current != null && current == destination) {
                return visited + current
            }

            if (current != null) {
                stack.addFirst(current)
                current = tree.left(current)
            } else {
                current = stack.removeFirst()
                visited.add(current)
                current = tree.right(current)
            }
        }

        return when (destination) {
            null -> visited
            else -> emptyList() // Could not find destination, return empty
        }
    }
}
