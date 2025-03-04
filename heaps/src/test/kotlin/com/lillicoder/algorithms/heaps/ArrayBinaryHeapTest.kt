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
 * Unit tests for [ArrayBinaryHeap].
 */
internal class ArrayBinaryHeapTest {
    private val heap =
        ArrayBinaryHeap(
            listOf(
                "a",
                "b",
                "c",
                "d",
                "e",
                "f",
                "g",
            ),
        )

    @Test
    fun `Default iteration is heap order`() {
        val expected = listOf("g", "e", "f", "d", "b", "a", "c")
        val actual = heap.map { it.value }
        assertContentEquals(expected, actual)
    }
}
