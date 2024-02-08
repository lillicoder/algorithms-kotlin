package com.lillicoder.algorithms.sort

import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Unit tests for [Heapsort].
 */
internal class HeapsortTest {
    @Test
    fun `Heapsort handles empty list`() {
        val input = mutableListOf<Int>()
        val actual = InsertionSort().sort(input)

        assertTrue(actual.isEmpty())
    }

    @Test
    fun `Heapsort matches known concrete sorted list`() {
        val input = listOf("delta", "tau", "zeta", "alpha", "gamma", "alpha", "beta", "epsilon")
        val expected = listOf("alpha", "alpha", "beta", "delta", "epsilon", "gamma", "tau", "zeta")
        val actual = InsertionSort().sort(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `Heapsort matches Kotlin SDK for a random list`() {
        val input = IntRange(1, 100).map { Random.Default.nextInt() }
        val expected = input.sorted()
        val actual = InsertionSort().sort(input)

        assertEquals(expected, actual)
    }
}
