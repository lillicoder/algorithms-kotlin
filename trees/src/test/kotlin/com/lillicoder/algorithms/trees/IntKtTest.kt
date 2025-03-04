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

package com.lillicoder.algorithms.trees

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * Unit tests for integer extensions.
 */
internal class IntKtTest {
    private val list =
        listOf(
            "a",
            "b",
            "c",
            "d",
            "e",
            "f",
        )

    @Test
    fun `Left child of valid index matches expected`() {
        val expected = "f"
        val actual = list[list.indexOf("c").leftChild()]
        assertEquals(expected, actual)
    }

    @Test
    fun `Left child of index with no children is null`() {
        val actual = list.getOrNull(list.indexOf("f").leftChild())
        assertNull(actual)
    }

    @Test
    fun `Left child of invalid index is null`() {
        val actual = list.getOrNull((-1).leftChild())
        assertNull(actual)
    }

    @Test
    fun `Parent of index matches expected`() {
        val expected = "a"
        val actual = list[list.indexOf("c").parent()]
        assertEquals(expected, actual)
    }

    @Test
    fun `Parent of index with no parent is null`() {
        val actual = list.getOrNull(list.indexOf("a").parent())
        assertNull(actual)
    }

    @Test
    fun `Parent of invalid index is null`() {
        val actual = list.getOrNull((-1).parent())
        assertNull(actual)
    }

    @Test
    fun `Right child of valid index matches expected`() {
        val expected = "e"
        val actual = list[list.indexOf("b").rightChild()]
        assertEquals(expected, actual)
    }

    @Test
    fun `Right child of index with no right child is null`() {
        val actual = list.getOrNull(list.indexOf("c").rightChild())
        assertNull(actual)
    }

    @Test
    fun `Right child of index with no children is null`() {
        val actual = list.getOrNull(list.indexOf("f").rightChild())
        assertNull(actual)
    }

    @Test
    fun `Right child of invalid index is null`() {
        val actual = list.getOrNull((-2).rightChild())
        assertNull(actual)
    }
}
