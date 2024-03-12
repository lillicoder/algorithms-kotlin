package com.lillicoder.algorithms.graphs

/**
 * Interface for implementations of a [Graph](https://en.wikipedia.org/wiki/Graph_(abstract_data_type)).
 */
interface Graph<T> : Iterable<T> {
    /**
     * Implementation of an [Iterator] for a [Graph] that uses [Traversal.BREADTH_FIRST] order.
     * @param graph Graph to traverse.
     */
    private class BreadthFirstIterator<T>(
        private val graph: Graph<T>,
        root: T = graph.root(),
    ) : Iterator<T> {
        private val queue = ArrayDeque(listOf(root))
        private val visited = mutableMapOf(root to true)

        override fun hasNext() = !queue.isEmpty()

        override fun next(): T {
            val next = queue.removeFirst()
            graph.neighbors(next).forEach {
                if (!visited.getOrElse(it) { false }) {
                    visited[it] = true
                    queue.add(it)
                }
            }

            return next
        }
    }

    /**
     * Implementation of an [Iterator] for a [Graph] that uses [Traversal.DEPTH_FIRST] order.
     * @param graph Graph to traverse.
     */
    private class DepthFirstIterator<T>(
        private val graph: Graph<T>,
        root: T = graph.root(),
    ) : Iterator<T> {
        private val stack = ArrayDeque(listOf(root))
        private val visited = mutableMapOf<T, Boolean>()

        override fun hasNext() = !stack.isEmpty()

        override fun next(): T {
            val next = stack.removeFirst()
            if (!visited.getOrElse(next) { false }) {
                visited[next] = true
                graph.neighbors(next).forEach {
                    // Don't add nodes we've visited or will visit before this neighbor
                    if (!visited.getOrElse(it) { false } && !stack.contains(it)) {
                        stack.addFirst(it)
                    }
                }
            }

            return next
        }
    }

    /**
     * Adds an edge connecting the two given elements.
     * @param first First element.
     * @param second Second element.
     */
    fun addEdge(
        first: T,
        second: T,
    )

    /**
     * Adds the given element to this graph.
     * @param element Element to add.
     */
    fun addVertex(element: T)

    /**
     * Determines if there is an edge between the two given elements.
     * @param first First element.
     * @param second Second element.
     * @return True if there is an edge between the elements, false otherwise.
     */
    fun adjacent(
        first: T,
        second: T,
    ): Boolean

    /**
     * Determines if this graph contains the given element.
     * @param element Element to find.
     * @return True if this graph contains the element, false otherwise.
     */
    fun contains(element: T): Boolean

    override fun iterator() = iterator(Traversal.DEPTH_FIRST)

    /**
     * Gets an [Iterator] for this graph using the given [Traversal].
     * @param traversal Graph traversal.
     * @return Iterator.
     */
    fun iterator(traversal: Traversal): Iterator<T> {
        return when (traversal) {
            Traversal.BREADTH_FIRST -> BreadthFirstIterator(this)
            Traversal.DEPTH_FIRST -> DepthFirstIterator(this)
        }
    }

    /**
     * Gets all neighbors of the given element. An element is considered
     * a neighbor if there is an edge to it from the given element.
     * @param element Element.
     * @return Vertex neighbors.
     */
    fun neighbors(element: T): Set<T>

    /**
     * Removes an edge, if any, connecting the two given elements.
     * @param first First element.
     * @param second Second element.
     */
    fun removeEdge(
        first: T,
        second: T,
    )

    /**
     * Removes the given element from this graph.
     */
    fun removeVertex(element: T)

    /**
     * Gets the first node added to this graph.
     * @return First node.
     */
    fun root(): T

    /**
     * Gets the number of nodes in this graph.
     */
    fun size(): Int
}
