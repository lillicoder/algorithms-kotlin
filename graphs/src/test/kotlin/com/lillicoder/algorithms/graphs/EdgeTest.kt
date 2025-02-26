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

package com.lillicoder.algorithms.graphs

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

/**
 * Unit tests for [Edge].
 */
internal class EdgeTest {
    @Test
    fun `Directed edges match if vertices are in same order`() {
        val a = Vertex(1, "Test 1")
        val b = Vertex(2, "Test 2")
        val first = Edge(a, b, directed = true)
        val second = Edge(a, b, directed = true)
        assertEquals(first, second)
    }

    @Test
    fun `Directed edges do not match if vertices are not in same order`() {
        val a = Vertex(1, "Test 1")
        val b = Vertex(2, "Test 2")
        val first = Edge(a, b, directed = true)
        val second = Edge(b, a, directed = true)
        assertNotEquals(first, second)
    }

    @Test
    fun `Undirected edges match if vertices are in same order`() {
        val a = Vertex(1, "Test 1")
        val b = Vertex(2, "Test 2")
        val first = Edge(a, b)
        val second = Edge(a, b)
        assertEquals(first, second)
    }

    @Test
    fun `Undirected edges match if vertices are not in same order`() {
        val a = Vertex(1, "Test 1")
        val b = Vertex(2, "Test 2")
        val first = Edge(a, b)
        val second = Edge(b, a)
        assertEquals(first, second)
    }
}