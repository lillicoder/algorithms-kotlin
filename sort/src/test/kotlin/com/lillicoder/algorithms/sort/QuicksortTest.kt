package com.lillicoder.algorithms.sort

import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Unit tests for [Quicksort].
 */
internal class QuicksortTest {
    @Test
    fun `Quicksort handles empty list`() {
        val input = mutableListOf<Int>()
        val actual = Quicksort().sort(input)

        assertTrue(actual.isEmpty())
    }

    @Test
    fun `Quicksort matches known concrete sorted list`() {
        val input = listOf(2, 209, 44, 12039, 2, 1241, 4, 55555)
        val expected = listOf(2, 2, 4, 44, 209, 1241, 12039, 55555)
        val actual = Quicksort().sort(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `Quicksort matches Kotlin SDK for a random list`() {
        val input = IntRange(1, 100).map { Random.Default.nextInt() }
        val expected = input.sorted()
        val actual = Quicksort().sort(input)

        assertEquals(expected, actual)
    }
}
