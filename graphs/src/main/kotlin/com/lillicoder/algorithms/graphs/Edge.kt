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

package com.lillicoder.algorithms.graphs

import java.util.Objects

/**
 * A single edge between two [Vertex] of a [Graph].
 * @param source Source vertex.
 * @param destination Destination vertex.
 * @param directed True if this edge is directed, false if this edge is undirected.
 * @param weight Weight.
 */
data class Edge<T>(
    val source: Vertex<T>,
    val destination: Vertex<T>,
    val directed: Boolean = false,
    val weight: Long = 0L,
) {
    internal val vertices =
        when (directed) {
            true -> source to destination
            else -> UnorderedPair(source, destination)
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Edge<*>) return false
        return vertices == other.vertices &&
            directed == other.directed &&
            weight == other.weight
    }

    override fun hashCode() = Objects.hash(vertices, directed, weight)
}
