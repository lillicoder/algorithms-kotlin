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
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * Unit tests for [LinkedBinaryTree].
 */
internal class LinkedBinaryTreeTest {
    private val tree =
        binaryTree("1") {
            left("7") {
                left("2")
                right("6") {
                    left("5")
                    right("11")
                }
            }
            right("8") {
                left("3") {
                    left("9")
                }
            }
        }

    @Test
    fun `Default iteration is level order`() {
        val expected = listOf("1", "7", "8", "2", "6", "3", "5", "11", "9")
        val actual = tree.map { it.value }
        assertContentEquals(expected, actual)
    }

    @Test
    fun `Left child of node with no children is null`() {
        val parent = tree.find { it.value == "2" }!!
        val actual = tree.left(parent)
        assertNull(actual)
    }

    @Test
    fun `Right child of node with no children is null`() {
        val parent = tree.find { it.value == "2" }!!
        val actual = tree.right(parent)
        assertNull(actual)
    }

    @Test
    fun `Size matches expected`() {
        val expected = 9
        val actual = tree.size()
        assertEquals(expected, actual)
    }
}
