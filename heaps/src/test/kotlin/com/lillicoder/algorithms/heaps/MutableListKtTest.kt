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

package com.lillicoder.algorithms.heaps

import kotlin.test.Test
import kotlin.test.assertContentEquals

/**
 * Unit tests for mutable list extensions.
 */
internal class MutableListKtTest {
    private val list =
        listOf(
            "a",
            "b",
            "c",
            "d",
            "e",
            "f",
            "g",
        )
    private val heapified =
        listOf(
            "g",
            "e",
            "f",
            "d",
            "b",
            "a",
            "c",
        )

    @Test
    fun `Heapify on non-heap list matches expected`() {
        val expected = heapified
        val actual = list.toMutableList().also { it.heapify() }
        assertContentEquals(expected, actual)
    }

    @Test
    fun `Heapify on heapified list maintains order`() {
        val expected = heapified
        val actual = heapified.toMutableList().also { it.heapify() }
        assertContentEquals(expected, actual)
    }

    @Test
    fun `Sift down for valid indices matches expected`() {
        val expected = listOf("c", "b", "f", "d", "e", "a", "g")
        val actual = list.toMutableList().also { it.siftDown(0, 6) }
        assertContentEquals(expected, actual)
    }

    @Test
    fun `Sift down for valid indices already in correct place maintains order`() {
        val expected = heapified
        val actual = heapified.toMutableList().also { it.siftDown(0, 6) }
        assertContentEquals(expected, actual)
    }

    @Test
    fun `Sift down for invalid start index maintains order`() {
        val expected = list
        val actual = list.toMutableList().also { it.siftDown(-1, 6) }
        assertContentEquals(expected, actual)
    }

    @Test
    fun `Sift down for invalid end index maintains order`() {
        val expected = list
        val actual = list.toMutableList().also { it.siftDown(0, -1) }
        assertContentEquals(expected, actual)
    }

    @Test
    fun `Sift down for invalid indices maintains order`() {
        val expected = list
        val actual = list.toMutableList().also { it.siftDown(-1, -1) }
        assertContentEquals(expected, actual)
    }
}
