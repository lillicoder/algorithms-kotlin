package com.lillicoder.algorithms.graphs

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Unit tests for [AdjacencyListGraph].
 */
internal class AdjacencyListGraphTest {
    private val graph =
        graph {
            vertex(1)
            vertex(2)
            vertex(3)
            vertex(4)
            vertex(5)
            vertex(6)

            edge(source = 1, destination = 2)
            edge(source = 1, destination = 4)
            edge(source = 2, destination = 1)
            edge(source = 2, destination = 3)
            edge(source = 2, destination = 4)
            edge(source = 3, destination = 5)
            edge(source = 4, destination = 5)
            edge(source = 5, destination = 6)
        }
    private val emptyGraph = graph<Nothing> {}

    @Test
    fun `Default iterator is by vertex insert order`() {
        val expected = listOf(0L, 1L, 2L, 3L, 4L, 5L)
        val actual = graph.map { it.id }
        assertContentEquals(expected, actual)
    }

    @Test
    fun `Default iterator for empty graph is empty`() {
        val expected = emptyList<Long>()
        val actual = emptyGraph.map { it.id }
        assertContentEquals(expected, actual)
    }

    @Test
    fun `Adjacency for valid connected vertices is true`() {
        val actual =
            graph.adjacent(
                graph.vertex(0L)!!,
                graph.vertex(1L)!!,
            )
        assertTrue(actual)
    }

    @Test
    fun `Adjacency for valid unconnected vertices is false`() {
        val actual =
            graph.adjacent(
                graph.vertex(0L)!!,
                graph.vertex(5L)!!,
            )
        assertFalse(actual)
    }

    @Test
    fun `Adjacency for invalid source vertex is false`() {
        val actual =
            graph.adjacent(
                Vertex(100L, 123),
                graph.vertex(5L)!!,
            )
        assertFalse(actual)
    }

    @Test
    fun `Adjacency for invalid destination vertex is false`() {
        val actual =
            graph.adjacent(
                graph.vertex(0L)!!,
                Vertex(100L, 123),
            )
        assertFalse(actual)
    }

    @Test
    fun `Adjacency for invalid vertices is false`() {
        val actual =
            graph.adjacent(
                Vertex(100L, 123),
                Vertex(101L, 321),
            )
        assertFalse(actual)
    }

    @Test
    fun `Edge for valid vertices matches expected`() {
        val actual =
            graph.edge(
                graph.vertex(0L)!!,
                graph.vertex(1L)!!,
            )!!
        assertEquals(0L, actual.source.id)
        assertEquals(1L, actual.destination.id)
        assertFalse(actual.directed)
        assertEquals(0L, actual.weight)
    }

    @Test
    fun `Edge for invalid destination vertex is null`() {
        val actual =
            graph.edge(
                graph.vertex(0L)!!,
                Vertex(100L, 123),
            )
        assertNull(actual)
    }

    @Test
    fun `Edge for invalid source vertex is null`() {
        val actual =
            graph.edge(
                Vertex(100L, 123),
                graph.vertex(1L)!!,
            )
        assertNull(actual)
    }

    @Test
    fun `Edge for invalid vertices is null`() {
        val actual =
            graph.edge(
                Vertex(100L, 123),
                Vertex(101L, 321),
            )
        assertNull(actual)
    }

    @Test
    fun `Neighbors for a vertex match expected`() {
        val expected = listOf(1L, 3L)
        val actual =
            graph.neighbors(
                graph.vertex(0L)!!,
            ).map {
                it.id
            }
        assertContentEquals(expected, actual)
    }

    @Test
    fun `Neighbors for a non-existent vertex is empty`() {
        val expected = emptyList<Long>()
        val actual =
            graph.neighbors(
                Vertex(100L, 123),
            ).map {
                it.id
            }
        assertContentEquals(expected, actual)
    }

    @Test
    fun `Root vertex is first inserted vertex`() {
        val expected = 0L
        val actual = graph.root()!!
        assertEquals(expected, actual.id)
    }

    @Test
    fun `Root vertex is null for empty graph`() {
        val actual = emptyGraph.root()
        assertNull(actual)
    }

    @Test
    fun `Size matches expected`() {
        val expected = 6
        val actual = graph.size()
        assertEquals(expected, actual)
    }

    @Test
    fun `Size is zero for empty graph`() {
        val expected = 0
        val actual = emptyGraph.size()
        assertEquals(expected, actual)
    }

    @Test
    fun `Sub-graph for existing vertices matches expected`() {
        val vertices =
            listOf(
                graph.vertex(0L)!!,
                graph.vertex(1L)!!,
                graph.vertex(2L)!!,
            )
        val subgraph = graph.subgraph(vertices)

        // Check vertices
        val actual =
            listOf(
                subgraph.vertex(0L)!!,
                subgraph.vertex(1L)!!,
                subgraph.vertex(2L)!!,
            )
        assertContentEquals(vertices, actual)

        // Check edges
        assertTrue(subgraph.adjacent(actual[0], actual[1])) // edge for 1-2 and 2-1
        assertTrue(subgraph.adjacent(actual[1], actual[2])) // edge for 2-3

        // Check neighbors
        assertEquals(1, subgraph.neighbors(actual[0]).size)
        assertEquals(2, subgraph.neighbors(actual[1]).size)
        assertEquals(1, subgraph.neighbors(actual[2]).size)
    }

    @Test
    fun `Vertex by ID gets correct vertex`() {
        val expected = 2L
        val actual = graph.vertex(expected)
        assertEquals(expected, actual!!.id)
    }

    @Test
    fun `Vertex for invalid ID is null`() {
        val actual = graph.vertex(100L)
        assertNull(actual)
    }
}
