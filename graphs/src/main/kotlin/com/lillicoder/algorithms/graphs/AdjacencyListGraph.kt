package com.lillicoder.algorithms.graphs

/**
 * [Adjacency list](https://en.wikipedia.org/wiki/Adjacency_list) implementation of a [Graph].
 */
class AdjacencyListGraph<T> : Graph<T> {
    private val adjacency = mutableMapOf<T, MutableSet<T>>()

    override fun addEdge(
        first: T,
        second: T,
    ) {
        adjacency[first]?.add(second)
        adjacency[second]?.add(first)
    }

    override fun addVertex(element: T) {
        adjacency.putIfAbsent(
            element,
            mutableSetOf(),
        )
    }

    override fun adjacent(
        first: T,
        second: T,
    ): Boolean {
        val firstHasSecond = adjacency[first]?.contains(second) ?: false
        val secondHasFirst = adjacency[second]?.contains(first) ?: false
        return firstHasSecond && secondHasFirst
    }

    override fun contains(element: T) = adjacency.containsKey(element)

    override fun neighbors(element: T) = adjacency[element]?.map { it }?.toSet() ?: setOf()

    override fun removeEdge(
        first: T,
        second: T,
    ) {
        adjacency[first]?.remove(second)
        adjacency[second]?.remove(first)
    }

    override fun removeVertex(element: T) {
        adjacency.values.forEach { it.remove(element) }
        adjacency.remove(element)
    }

    override fun root() = adjacency.keys.first()

    override fun size() = adjacency.keys.size
}
