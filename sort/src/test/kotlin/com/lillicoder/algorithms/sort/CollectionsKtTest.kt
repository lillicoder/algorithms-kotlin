/*
 * Copyright 2025 Scott Weeden-Moody
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this project except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lillicoder.algorithms.sort

import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Unit tests for sort extensions.
 */
internal class CollectionsKtTest {
    /***************
     * Bubble Sort *
     ***************/

    @Test
    fun `Bubble sort handles empty list`() {
        val input = mutableListOf<Int>()
        val actual = input.bubbleSorted()

        assertTrue(actual.isEmpty())
    }

    @Test
    fun `Bubble sort matches known concrete sorted list`() {
        val input = listOf("delta", "tau", "zeta", "alpha", "gamma", "alpha", "beta", "epsilon")
        val expected = listOf("alpha", "alpha", "beta", "delta", "epsilon", "gamma", "tau", "zeta")
        val actual = input.bubbleSorted()

        assertEquals(expected, actual)
    }

    @Test
    fun `Bubble sort matches Kotlin SDK for a random list`() {
        val input = IntRange(1, 100).map { Random.Default.nextInt() }
        val expected = input.sorted()
        val actual = input.bubbleSorted()

        assertEquals(expected, actual)
    }

    /************
     * Heapsort *
     ************/

    @Test
    fun `Heapsort handles empty list`() {
        val input = mutableListOf<Int>()
        val actual = input.heapSorted()

        assertTrue(actual.isEmpty())
    }

    @Test
    fun `Heapsort matches known concrete sorted list`() {
        val input = listOf("delta", "tau", "zeta", "alpha", "gamma", "alpha", "beta", "epsilon")
        val expected = listOf("alpha", "alpha", "beta", "delta", "epsilon", "gamma", "tau", "zeta")
        val actual = input.heapSorted()

        assertEquals(expected, actual)
    }

    @Test
    fun `Heapsort matches Kotlin SDK for a random list`() {
        val input = IntRange(1, 100).map { Random.Default.nextInt() }
        val expected = input.sorted()
        val actual = input.heapSorted()

        assertEquals(expected, actual)
    }

    /******************
     * Insertion Sort *
     ******************/

    @Test
    fun `Insertion sort handles empty list`() {
        val input = mutableListOf<Int>()
        val actual = input.insertionSorted()

        assertTrue(actual.isEmpty())
    }

    @Test
    fun `Insertion sort matches known concrete sorted list`() {
        val input = listOf("delta", "tau", "zeta", "alpha", "gamma", "alpha", "beta", "epsilon")
        val expected = listOf("alpha", "alpha", "beta", "delta", "epsilon", "gamma", "tau", "zeta")
        val actual = input.insertionSorted()

        assertEquals(expected, actual)
    }

    @Test
    fun `Insertion sort matches Kotlin SDK for a random list`() {
        val input = IntRange(1, 100).map { Random.Default.nextInt() }
        val expected = input.sorted()
        val actual = input.insertionSorted()

        assertEquals(expected, actual)
    }

    /**************
     * Merge Sort *
     **************/

    @Test
    fun `Merge sort handles empty list`() {
        val input = mutableListOf<Int>()
        val actual = input.mergeSorted()

        assertTrue(actual.isEmpty())
    }

    @Test
    fun `Merge sort matches known concrete sorted list`() {
        val input = listOf("delta", "tau", "zeta", "alpha", "gamma", "alpha", "beta", "epsilon")
        val expected = listOf("alpha", "alpha", "beta", "delta", "epsilon", "gamma", "tau", "zeta")
        val actual = input.mergeSorted()

        assertEquals(expected, actual)
    }

    @Test
    fun `Merge sort matches Kotlin SDK for a random list`() {
        val input = IntRange(1, 100).map { Random.Default.nextInt() }
        val expected = input.sorted()
        val actual = input.mergeSorted()

        assertEquals(expected, actual)
    }

    /*************
     * Quicksort *
     *************/

    @Test
    fun `Quicksort handles empty list`() {
        val input = mutableListOf<Int>()
        val actual = input.quicksorted()

        assertTrue(actual.isEmpty())
    }

    @Test
    fun `Quicksort matches known concrete sorted list`() {
        val input = listOf("delta", "tau", "zeta", "alpha", "gamma", "alpha", "beta", "epsilon")
        val expected = listOf("alpha", "alpha", "beta", "delta", "epsilon", "gamma", "tau", "zeta")
        val actual = input.quicksorted()

        assertEquals(expected, actual)
    }

    @Test
    fun `Quicksort matches Kotlin SDK for a random list`() {
        val input = IntRange(1, 100).map { Random.Default.nextInt() }
        val expected = input.sorted()
        val actual = input.quicksorted()

        assertEquals(expected, actual)
    }

    /******************
     * Selection Sort *
     ******************/

    @Test
    fun `Selection sort handles empty list`() {
        val input = mutableListOf<Int>()
        val actual = input.selectionSorted()

        assertTrue(actual.isEmpty())
    }

    @Test
    fun `Selection sort matches known concrete sorted list`() {
        val input = listOf("delta", "tau", "zeta", "alpha", "gamma", "alpha", "beta", "epsilon")
        val expected = listOf("alpha", "alpha", "beta", "delta", "epsilon", "gamma", "tau", "zeta")
        val actual = input.selectionSorted()

        assertEquals(expected, actual)
    }

    @Test
    fun `Selection sort matches Kotlin SDK for a random list`() {
        val input = IntRange(1, 100).map { Random.Default.nextInt() }
        val expected = input.sorted()
        val actual = input.selectionSorted()

        assertEquals(expected, actual)
    }

    /*************
     * Tree Sort *
     *************/

    @Test
    fun `Tree sort handles empty list`() {
        val input = mutableListOf<Int>()
        val actual = input.treeSorted()

        assertTrue(actual.isEmpty())
    }

    @Test
    fun `Tree sort matches known concrete sorted list`() {
        // Binary trees don't allow duplicates, so only check for unique ordered values
        val input = listOf("delta", "tau", "zeta", "alpha", "gamma", "alpha", "beta", "epsilon")
        val expected = listOf("alpha", "alpha", "beta", "delta", "epsilon", "gamma", "tau", "zeta")
        val actual = input.treeSorted()

        assertEquals(expected, actual)
    }

    @Test
    fun `Tree sort matches Kotlin SDK for a random list`() {
        // Binary trees don't allow duplicates, so only check for unique ordered values
        val input = IntRange(1, 100).map { Random.Default.nextInt() }.distinct()
        val expected = input.sorted()
        val actual = input.treeSorted()

        assertEquals(expected, actual)
    }
}
