package com.lillicoder.algorithms.trees.traversal

import com.lillicoder.algorithms.trees.BinaryNode
import com.lillicoder.algorithms.trees.Node
import com.lillicoder.algorithms.trees.binaryTree
import kotlin.test.Test
import kotlin.test.assertContentEquals

/**
 * Unit tests for [PostOrderTraversal].
 */
internal class PostOrderTraversalTest {
    private val tree =
        binaryTree("A") {
            left("B") {
                left("D")
            }
            right("C") {
                left("E") {
                    left("G")
                    right("H")
                }
                right("F")
            }
        }
    private val traversal = PostOrderTraversal(tree)

    @Test
    fun `Traversal with destination gives expected nodes in-order`() {
        val start = tree.root()
        val destination = tree.find { it.value == "G" }

        val expected = listOf("D", "B", "G")
        val actual =
            traversal.path(
                start,
                destination,
            ).map { it.value }
        assertContentEquals(expected, actual)
    }

    @Test
    fun `Traversal with no destination gives all nodes in-order`() {
        val expected = listOf("D", "B", "G", "H", "E", "F", "C", "A")
        val actual = traversal.path(tree.root()).map { it.value }
        assertContentEquals(expected, actual)
    }

    @Test
    fun `Traversal with invalid start is empty`() {
        val start = BinaryNode(100L, "123")

        val expected = emptyList<Node<String>>()
        val actual = traversal.path(start)

        assertContentEquals(expected, actual)
    }

    @Test
    fun `Traversal with invalid destination is empty`() {
        val start = tree.root()
        val destination = BinaryNode(100L, "123")

        val expected = emptyList<Node<String>>()
        val actual = traversal.path(start, destination)

        assertContentEquals(expected, actual)
    }

    @Test
    fun `Traversal with invalid nodes is empty`() {
        val start = BinaryNode(100L, "321")
        val destination = BinaryNode(101L, "123")

        val expected = emptyList<Node<String>>()
        val actual = traversal.path(start, destination)

        assertContentEquals(expected, actual)
    }
}
