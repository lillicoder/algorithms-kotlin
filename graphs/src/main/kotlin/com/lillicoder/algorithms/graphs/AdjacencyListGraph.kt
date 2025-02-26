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

    override fun root() = vertices.keys.first()

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
}
