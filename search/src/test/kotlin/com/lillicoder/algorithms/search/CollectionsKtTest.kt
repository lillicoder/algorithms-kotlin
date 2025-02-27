package com.lillicoder.algorithms.search

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Unit tests for list search extensions.
 */
internal class CollectionsKtTest {
    private val list = listOf("delta", "tau", "zeta", "alpha", "gamma", "alpha", "beta", "epsilon")

    @Test
    fun `Binary search finds expected element`() {
        val expected = 6
        val actual = list.sorted().binarySearch("tau")
        assertEquals(expected, actual)
    }

    @Test
    fun `Binary search for invalid element returns -1`() {
        val expected = -1
        val actual = list.binarySearch("test")
        assertEquals(expected, actual)
    }

    @Test
    fun `Linear search finds expected element`() {
        val expected = 3
        val actual = list.linearSearch("alpha")
        assertEquals(expected, actual)
    }

    @Test
    fun `Linear search for invalid element returns -1`() {
        val expected = -1
        val actual = list.linearSearch("test")
        assertEquals(expected, actual)
    }
}
