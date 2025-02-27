package com.lillicoder.algorithms.graphs

/**
 * Interface for implementations of a [Graph](https://en.wikipedia.org/wiki/Graph_(abstract_data_type)).
 */
interface Graph<T> : Iterable<Vertex<T>> {
    override fun iterator() =
        object : Iterator<Vertex<T>> {
            private val queue =
                ArrayDeque<Vertex<T>>().also {
                    val root = root()
                    if (root != null) it.add(root)
                }
            private val visited =
                linkedMapOf<Vertex<T>, Boolean>().also {
                    val root = root()
                    if (root != null) it[root] = true
                }

            override fun hasNext() = queue.isNotEmpty()

            override fun next() =
                queue.removeFirst().also { next ->
                    visited[next] = true
                    vertex(next.id.inc())?.let { queue.add(it) }
                }
        }

    /**
     * Determines if there is an edge between the two given [Vertex].
     * @param first First vertex.
     * @param second Second vertex.
     * @return True if there is an edge between the vertices, false otherwise.
     */
    fun adjacent(
        first: Vertex<T>,
        second: Vertex<T>,
    ): Boolean

    /**
     * Gets the [Edge] connecting the two given [Vertex].
     * @param from Source vertex.
     * @param to Destination vertex.
     * @return Edge or null if there is no edge connecting the given vertices.
     */
    fun edge(
        from: Vertex<T>,
        to: Vertex<T>,
    ): Edge<T>?

    /**
     * Gets all neighbors of the given [Vertex]. A vertex is considered
     * a neighbor if there is an edge to it from the given vertex.
     * @param vertex Vertex.
     * @return Neighbors or an empty set if there are no neighbors.
     */
    fun neighbors(vertex: Vertex<T>): Set<Vertex<T>>

    /**
     * Gets the first [Vertex] added to this graph.
     * @return First vertex or null if there are no vertices in this graph.
     */
    fun root(): Vertex<T>?

    /**
     * Gets the number of vertices in this graph.
     * @return Size.
     */
    fun size(): Int

    /**
     * Creates a sub-graph containing each of the
     * given [Vertex] in this graph.
     *
     * Any vertex not in this graph is ignored. Edges between vertices are preserved.
     * Any vertex that doesn't have a direct edge to one of the given vertices will be in
     * the sub-graph but have no edges.
     * @param vertices Vertices to get a sub-graph of.
     * @return Sub-graph.
     */
    fun subgraph(vertices: List<Vertex<T>>): Graph<T>

    /**
     * Gets the [Vertex] from this graph with the given ID.
     * @param id Vertex ID.
     * @return Vertex or null if there is no matching vertex.
     */
    fun vertex(id: Long): Vertex<T>?
}
