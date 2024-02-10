package com.lillicoder.algorithms.sort

import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Unit test for [SelectionSort].
 */
internal class SelectionSortTest {
    @Test
    fun `Selection sort handles empty list`() {
        val input = mutableListOf<Int>()
        val actual = SelectionSort().sort(input)

        assertTrue(actual.isEmpty())
    }

    @Test
    fun `Selection sort matches known concrete sorted list`() {
        val input = listOf("delta", "tau", "zeta", "alpha", "gamma", "alpha", "beta", "epsilon")
        val expected = listOf("alpha", "alpha", "beta", "delta", "epsilon", "gamma", "tau", "zeta")
        val actual = SelectionSort().sort(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `Selection sort matches Kotlin SDK for a random list`() {
        val input = IntRange(1, 100).map { Random.Default.nextInt() }
        val expected = input.sorted()
        val actual = SelectionSort().sort(input)

        assertEquals(expected, actual)
    }
}
