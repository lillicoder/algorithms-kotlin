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

import com.lillicoder.algorithms.graphs.Graph
import com.lillicoder.algorithms.graphs.Vertex

/**
 * Breadth-first [Traversal] of a given [Graph].
 * @param graph Graph to traverse.
 */
class BreadthFirstIterator<T>(private val graph: Graph<T>) : Traversal<T> {
    private val queue = ArrayDeque<Vertex<T>>()
    private val visited = linkedMapOf<Vertex<T>, Vertex<T>?>()

    override fun path(
        start: Vertex<T>,
        destination: Vertex<T>?,
    ): List<Vertex<T>> {
        queue.add(start)
        visited[start] = null

        while (queue.isNotEmpty()) {
            val next = queue.removeFirst()
            if (next == destination) return unwind(next)

            graph.neighbors(next).forEach {
                if (!visited.contains(it)) {
                    visited[it] = next
                    queue.add(it)
                }
            }
        }

        return visited.keys.toList()
    }

    /**
     * Builds the path taken to reach the given destination [Vertex].
     * @param destination Destination.
     * @return Path.
     */
    private fun unwind(destination: Vertex<T>): List<Vertex<T>> {
        val path = mutableListOf(destination)

        var previous = visited.getOrElse(destination) { null }
        while (previous != null) {
            path.add(previous)
            previous = visited[previous]
        }

        return path.reversed()
    }
}
