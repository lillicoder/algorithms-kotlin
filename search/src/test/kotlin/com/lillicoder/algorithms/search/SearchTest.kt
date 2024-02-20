package com.lillicoder.algorithms.search

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Unit tests for list search extensions.
 */
internal class SearchTest {
    private val list = listOf("delta", "tau", "zeta", "alpha", "gamma", "alpha", "beta", "epsilon")

    @Test
    fun binarySearch() {
        val expected = 6
        val actual = list.sorted().binarySearch("tau")
        assertEquals(expected, actual)
    }

    @Test
    fun linearSearch() {
        val expected = 3
        val actual = list.linearSearch("alpha")
        assertEquals(expected, actual)
    }
}
