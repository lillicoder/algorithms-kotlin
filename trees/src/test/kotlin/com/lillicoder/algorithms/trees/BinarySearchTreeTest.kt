package com.lillicoder.algorithms.trees

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Unit tests for [BinarySearchTree].
 */
internal class BinarySearchTreeTest {
    private lateinit var tree: BinarySearchTree<Int>

    @BeforeTest
    fun before() {
        tree = BinarySearchTree()
        tree.insert(2)
        tree.insert(1)
        tree.insert(3)
    }

    @Test
    fun `Delete removes a node`() {
        assertEquals(3, tree.size())

        tree.delete(2)
        assertEquals(2, tree.size())
    }

    @Test
    fun `Insert creates a new node`() {
        assertEquals(3, tree.size())

        tree.insert(10)
        assertEquals(4, tree.size())
    }

    @Test
    fun `In-order iterator walks tree properly`() {
        val expectedInOrder = listOf(1, 2, 3)
        val actualInOrder = tree.iterator(Traversal.IN_ORDER).asSequence().toList()
        assertEquals(expectedInOrder, actualInOrder)
    }

    @Test
    fun `Post-order iterator walks tree properly`() {
        val expectedPostOrder = listOf(1, 3, 2)
        val actualPostOrder = tree.iterator(Traversal.POST_ORDER).asSequence().toList()
        assertEquals(expectedPostOrder, actualPostOrder)
    }

    @Test
    fun `Pre-order iterator walks tree properly`() {
        val expectedPreorder = listOf(2, 1, 3)
        val actualPreOrder = tree.iterator(Traversal.PRE_ORDER).asSequence().toList()
        assertEquals(expectedPreorder, actualPreOrder)
    }
}
