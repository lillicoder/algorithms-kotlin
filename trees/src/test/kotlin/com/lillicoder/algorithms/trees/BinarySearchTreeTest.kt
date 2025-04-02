package com.lillicoder.algorithms.trees

import kotlin.test.Test
import kotlin.test.assertContentEquals

/**
 * Unit tests for [BinarySearchTree].
 */
internal class BinarySearchTreeTest {
    private val tree =
        BinarySearchTree(
            listOf(
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
                "11",
            ),
        )

    @Test
    fun `Default iteration matches expected order`() {
        // Lexicographic order puts 10 and 11 on the left branch of 2
        val expected =
            listOf(
                "1",
                "2",
                "10",
                "3",
                "11",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
            )
        val actual = tree.map { it.value }
        assertContentEquals(expected, actual)
    }
}
