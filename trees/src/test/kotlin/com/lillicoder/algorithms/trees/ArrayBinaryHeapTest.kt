package com.lillicoder.algorithms.trees

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Unit tests for [ArrayBinaryHeap].
 */
internal class ArrayBinaryHeapTest {
    private lateinit var heap: ArrayBinaryHeap<Int>

    @BeforeTest
    fun before() {
        heap = ArrayBinaryHeap()
        heap.insert(1)
        heap.insert(2)
        heap.insert(3)
    }

    @Test
    fun `Delete removes a node`() {
        assertEquals(3, heap.size())

        heap.delete(3)
        assertEquals(2, heap.size())
    }

    @Test
    fun `Insert creates a new node`() {
        assertEquals(3, heap.size())

        heap.insert(10)
        assertEquals(4, heap.size())
    }

    @Test
    fun `Iterator walks tree properly`() {
        // TODO Only level-order is supported regardless of Traversal type, update when support is added
        val expectedOrder = listOf(3, 1, 2)

        val actualInOrder = heap.iterator(Traversal.IN_ORDER).asSequence().toList()
        assertEquals(expectedOrder, actualInOrder)

        val actualPostOrder = heap.iterator(Traversal.POST_ORDER).asSequence().toList()
        assertEquals(expectedOrder, actualPostOrder)

        val actualPreOrder = heap.iterator(Traversal.PRE_ORDER).asSequence().toList()
        assertEquals(expectedOrder, actualPreOrder)
    }
}
