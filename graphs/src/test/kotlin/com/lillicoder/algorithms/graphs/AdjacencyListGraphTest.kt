package com.lillicoder.algorithms.graphs

import com.lillicoder.algorithms.graphs.traversal.Traversal
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Unit tests for [AdjacencyListGraph].
 */
internal class AdjacencyListGraphTest {
    private lateinit var graph: AdjacencyListGraph<Int>

    @BeforeTest
    fun before() {
        graph = AdjacencyListGraph()
        graph.addVertex(1)
        graph.addVertex(2)
        graph.addVertex(3)
    }

    @Test
    fun `Adds an edge between two existing nodes`() {
        assertEquals(graph.adjacent(1, 2), false)
        assertEquals(graph.adjacent(2, 1), false)

        graph.addEdge(1, 2)
        assertEquals(graph.adjacent(1, 2), true)
        assertEquals(graph.adjacent(2, 1), true)
    }

    @Test
    fun `Adds same edge only once`() {
        assertEquals(graph.neighbors(1).size, 0)
        assertEquals(graph.neighbors(2).size, 0)

        graph.addEdge(1, 2)
        assertEquals(graph.neighbors(1).size, 1)
        assertEquals(graph.neighbors(2).size, 1)

        // Add same edge again, shouldn't mutate graph
        graph.addEdge(1, 2)
        assertEquals(graph.neighbors(1).size, 1)
        assertEquals(graph.neighbors(2).size, 1)

        // Also works in reverse
        graph.addEdge(2, 1)
        assertEquals(graph.neighbors(1).size, 1)
        assertEquals(graph.neighbors(2).size, 1)
    }

    @Test
    fun `Ignores adding an edge for non-existent nodes`() {
        assertFalse(graph.contains(10))
        assertFalse(graph.contains(20))

        graph.addEdge(10, 20)
        assertFalse(graph.contains(10))
        assertFalse(graph.contains(20))
    }

    @Test
    fun `Adds a new vertex`() {
        assertFalse(graph.contains(10))

        graph.addVertex(10)
        assertTrue(graph.contains(10))
    }

    @Test
    fun `Adds same vertex only once`() {
        assertEquals(3, graph.size())

        graph.addVertex(10)
        assertEquals(4, graph.size())

        graph.addVertex(10)
        assertEquals(4, graph.size()) // Size should be same as before
    }

    @Test
    fun `New vertex has no neighbors`() {
        assertEquals(graph.neighbors(10).size, 0) // Node doesn't exist yet

        graph.addVertex(10)
        assertEquals(graph.neighbors(10).size, 0) // Has no neighbors by default
    }

    @Test
    fun `Removes existing edge`() {
        graph.addEdge(1, 2)
        assertEquals(graph.adjacent(1, 2), true)
        assertEquals(graph.adjacent(2, 1), true)

        graph.removeEdge(1, 2)
        assertEquals(graph.adjacent(1, 2), false)
        assertEquals(graph.adjacent(2, 1), false)

        // Also works in reverse
        graph.addEdge(1, 2)
        assertEquals(graph.adjacent(1, 2), true)
        assertEquals(graph.adjacent(2, 1), true)

        graph.removeEdge(2, 1)
        assertEquals(graph.adjacent(1, 2), false)
        assertEquals(graph.adjacent(2, 1), false)
    }

    @Test
    fun `Remove ignores non-existent edge`() {
        assertEquals(graph.adjacent(1, 2), false)
        assertEquals(graph.adjacent(2, 1), false)

        graph.removeEdge(1, 2)
        assertEquals(graph.adjacent(1, 2), false)
        assertEquals(graph.adjacent(2, 1), false)

        // Also works in reverse
        graph.removeEdge(2, 1)
        assertEquals(graph.adjacent(1, 2), false)
        assertEquals(graph.adjacent(2, 1), false)
    }

    @Test
    fun `Removes existing vertex`() {
        assertTrue(graph.contains(3))

        graph.removeVertex(3)
        assertFalse(graph.contains(3))
    }

    @Test
    fun `Remove ignores non-existent vertex`() {
        assertFalse(graph.contains(10))

        graph.removeVertex(10)
        assertFalse(graph.contains(10))
    }

    @Test
    fun `Traverses in breadth-first order`() {
        graph.addVertex(4)
        graph.addVertex(5)
        graph.addVertex(6)
        graph.addVertex(7)

        graph.addEdge(1, 2)
        graph.addEdge(1, 3)
        graph.addEdge(1, 4)
        graph.addEdge(2, 5)
        graph.addEdge(2, 6)
        graph.addEdge(3, 7)
        graph.addEdge(4, 6)

        val expected = listOf(1, 2, 3, 4, 5, 6, 7)
        val actual = graph.iterator(Traversal.BREADTH_FIRST).asSequence().toList()
        assertEquals(expected, actual)
    }

    @Test
    fun `Traverses in depth-first order`() {
        graph.addVertex(4)
        graph.addVertex(5)
        graph.addVertex(6)
        graph.addVertex(7)

        graph.addEdge(1, 2)
        graph.addEdge(1, 3)
        graph.addEdge(1, 4)
        graph.addEdge(2, 5)
        graph.addEdge(2, 6)
        graph.addEdge(3, 7)
        graph.addEdge(4, 6)

        val expected = listOf(1, 4, 6, 3, 7, 2, 5)
        val actual = graph.iterator(Traversal.DEPTH_FIRST).asSequence().toList()
        assertEquals(expected, actual)
    }
}
