package com.lillicoder.algorithms.graphs

/**
 * [Adjacency list](https://en.wikipedia.org/wiki/Adjacency_list) implementation of a [Graph].
 */
class AdjacencyListGraph<T>(
    private val vertices: Map<Vertex<T>, Set<Edge<T>>>,
    private val edges: Set<Edge<T>>,
) : Graph<T> {
    override fun adjacent(
        first: Vertex<T>,
        second: Vertex<T>,
    ) = edges.contains(Edge(first, second))

    override fun edge(
        from: Vertex<T>,
        to: Vertex<T>,
    ) = Edge(from, to).let { edge ->
        edges.find {
            it.vertices == edge.vertices
        }
    }

    override fun neighbors(vertex: Vertex<T>) =
        vertices[vertex]?.map {
            if (vertex == it.source) it.destination else it.source
        }?.toSet() ?: setOf()

    override fun root() = vertices.keys.firstOrNull()

    override fun size() = vertices.keys.size

    override fun subgraph(vertices: List<Vertex<T>>) =
        AdjacencyListGraph(
            // Filter vertices to only those asked for
            this.vertices.filter {
                it.key in vertices
            }.mapValues {
                // Filter out any vertex edges that have vertices not in the sub-graph
                it.value.filter {
                    it.source in vertices && it.destination in vertices
                }.toSet()
            },
            // Filter out any edges that have vertices not in the sub-graph
            this.edges.filter {
                it.source in vertices && it.destination in vertices
            }.toSet(),
        )

    override fun vertex(id: Long) = vertices.keys.find { it.id == id }

    /**
     * Builder for adjacency list graphs.
     */
    class Builder<T> {
        /**
         * Builder for an edge in an adjacency list graph.
         * @param source Source [Vertex].
         * @param destination Destination vertex.
         */
        class EdgeBuilder<T>(
            private val source: Vertex<T>,
            private val destination: Vertex<T>,
        ) {
            private var isDirected: Boolean = false
            private var weight: Long = 0L

            /**
             * Instantiates a new [Edge] from this builder.
             * @return Edge.
             */
            fun build() = Edge(source, destination, isDirected, weight)

            /**
             * Marks this builder's edge as directed.
             */
            fun directed() {
                isDirected = true
            }

            /**
             * Sets the weight of this builder's edge.
             * @param value Weight.
             */
            fun weight(value: Long) {
                weight = value
            }
        }

        private val edges = mutableSetOf<Edge<T>>()
        private val vertices = mutableMapOf<Vertex<T>, MutableSet<Edge<T>>>()

        /**
         * Instantiates a new [AdjacencyListGraph] from this builder.
         * @return Adjacency list graph.
         */
        fun build() = AdjacencyListGraph(vertices, edges)

        /**
         * Type-safe builder for creating an [Edge] of a graph from an existing
         * pair of [Vertex].
         *
         * Example usage:
         * ```
         * vertex("a")
         * vertex("b")
         *
         * edge(0, 1) { // First vertex has ID of 0, second vertex has ID of 1
         *     directed()
         *     weight(10L)
         * }
         * ```
         * @param sourceId Source vertex ID.
         * @param destinationId Destination vertex ID.
         * @param init Function with receiver.
         * @return Edge.
         */
        fun edge(
            sourceId: Long,
            destinationId: Long,
            init: (EdgeBuilder<T>.() -> Unit)? = null,
        ) = edge(
            vertices.keys.find { it.id == sourceId }!!,
            vertices.keys.find { it.id == destinationId }!!,
            init,
        )

        /**
         * Type-safe builder for creating an [Edge] of a graph from an existing
         * pair of [Vertex].
         *
         * Note that the first vertex matching each value will be used,
         * avoid this method when building a graph whose nodes have duplicate values.
         *
         * Example usage:
         * ```
         * vertex("a")
         * vertex("b")
         *
         * edge("a", "b") {
         *     directed()
         *     weight(10L)
         * }
         * ```
         * @param source Source vertex value.
         * @param destination Destination vertex value.
         * @param init Function with receiver.
         * @return Edge.
         */
        fun edge(
            source: T,
            destination: T,
            init: (EdgeBuilder<T>.() -> Unit)? = null,
        ) = edge(
            vertices.keys.find { it.value == source }!!,
            vertices.keys.find { it.value == destination }!!,
            init,
        )

        /**
         * Type-safe builder for creating an [Edge] of a graph from a pair of existing [Vertex].
         * Used internally by more fluent builders (e.g. by-ID or by-value edge builders).
         * @param source Source vertex.
         * @param destination Destination vertex.
         * @param init Function with receiver.
         * @return Edge.
         */
        private fun edge(
            source: Vertex<T>,
            destination: Vertex<T>,
            init: (EdgeBuilder<T>.() -> Unit)? = null,
        ): Edge<T> {
            val builder = EdgeBuilder(source, destination)
            init?.invoke(builder)

            val edge = builder.build()
            vertices[source]?.add(edge)
            vertices[destination]?.add(edge)
            edges.add(edge)

            return edge
        }

        /**
         * Type-safe builder for creating a [Vertex] of a graph.
         *
         * Example usage:
         * ```
         * vertex("a")
         * ```
         * @param value Value.
         * @param init Function with receiver.
         * @return Vertex.
         */
        fun vertex(
            value: T,
            init: (Vertex<T>.() -> Unit)? = null,
        ) = vertex(
            vertices.size.toLong(),
            value,
            init,
        )

        /**
         * Type-safe builder for creating a [Vertex] of a graph from an existing vertex. ID and value
         * of the given vertex will be copied.
         *
         * Example usage:
         * ```
         * val other = someOtherVertex()
         * vertex(other)
         * ```
         * @param vertex Vertex to copy.
         * @param init Function with receiver.
         * @return Vertex.
         */
        fun vertex(
            vertex: Vertex<T>,
            init: (Vertex<T>.() -> Unit)? = null,
        ) = vertex(
            vertex.id,
            vertex.value,
            init,
        )

        /**
         * Type-safe builder for creating a [Vertex] of a graph.
         *
         * Example usage:
         * ```
         * vertex(0, "a")
         * ```
         * @param id ID.
         * @param value Value.
         * @param init Function with receiver.
         * @return Vertex.
         */
        private fun vertex(
            id: Long,
            value: T,
            init: (Vertex<T>.() -> Unit)? = null,
        ): Vertex<T> {
            val vertex = Vertex(id, value)
            init?.invoke(vertex)
            vertices[vertex] = mutableSetOf()
            return vertex
        }
    }
}

/**
 * Type-safe builder for creating an [AdjacencyListGraph].
 *
 * Example usage:
 * ```
 * graph {
 *     vertex("a")
 *     vertex("b")
 *     vertex("c")
 *
 *     edge(1, 2)
 *     edge(2, 3) {
 *         directed()
 *         weight(10L)
 *     }
 * }
 * ```
 * @param init Function with receiver.
 * @return Adjacency list graph.
 */
fun <T> graph(init: AdjacencyListGraph.Builder<T>.() -> Unit): AdjacencyListGraph<T> {
    val builder = AdjacencyListGraph.Builder<T>()
    builder.init()
    return builder.build()
}
