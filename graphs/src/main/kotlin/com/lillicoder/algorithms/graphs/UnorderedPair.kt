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

import java.io.Serializable
import java.util.Objects

/**
 * A pair of elements whose elements may be in any order and still considered equal.
 * @param first First element.
 * @param second Second element.
 */
data class UnorderedPair<out A, out B>(
    val first: A,
    val second: B,
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UnorderedPair<*, *>) return false
        return (first == other.first && second == other.second) ||
            (first == other.second && second == other.first)
    }

    override fun hashCode(): Int {
        // Force a consistent order regardless of element fields
        val (min, max) =
            listOf(
                first.hashCode(),
                second.hashCode(),
            ).sorted()
        return Objects.hash(min, max)
    }

    override fun toString() = "($first, $second)"
}

/**
 * Creates an unordered pair from this and that.
 * @param that Other value.
 * @return Unordered pair.
 */
fun <A, B> A.to(that: B) = UnorderedPair(this, that)
