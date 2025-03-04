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

package com.lillicoder.algorithms.graphs.traversal

import com.lillicoder.algorithms.collections.dequeOf
import com.lillicoder.algorithms.graphs.Graph
import com.lillicoder.algorithms.graphs.Vertex

/**
 * Depth-first [Traversal] of a given [Graph].
 * @param graph Graph to traverse.
 */
class DepthFirstTraversal<T>(private val graph: Graph<T>) : Traversal<T> {
    private val stack = dequeOf<Vertex<T>>()
    private val visited = linkedMapOf<Vertex<T>, Boolean>()
    private val parent = mutableMapOf<Vertex<T>, Vertex<T>?>()

    override fun path(
        start: Vertex<T>,
        destination: Vertex<T>?,
    ): List<Vertex<T>> {
        stack.add(start)
        parent[start] = null

        while (stack.isNotEmpty()) {
            val next = stack.removeFirst()
            if (destination == next) return unwind(next)

            if (!visited.contains(next)) {
                visited[next] = true
                graph.neighbors(next).forEach {
                    if (!visited.contains(it) && !stack.contains(it)) {
                        stack.addFirst(it)
                        parent[it] = next
                    }
                }
            }
        }

        // Return empty list if an explicit destination was requested,
        // otherwise give back the vertices in visited order
        return when (destination == null) {
            true -> visited.keys.toList()
            else -> emptyList()
        }
    }

    /**
     * Builds the path taken to reach the given destination [Vertex].
     * @param destination Destination.
     * @return Path.
     */
    private fun unwind(destination: Vertex<T>): List<Vertex<T>> {
        val path = mutableListOf(destination)

        var previous = parent.getOrElse(destination) { null }
        while (previous != null) {
            path.add(previous)
            previous = parent[previous]
        }

        return path.reversed()
    }
}
