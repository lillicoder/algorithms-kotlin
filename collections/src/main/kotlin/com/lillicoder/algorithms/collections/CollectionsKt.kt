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

package com.lillicoder.algorithms.collections

/**
 * Returns the number of elements that are not null.
 * @return Number of non-null elements.
 */
fun <T> Iterable<T>.countNotNull() = count { it != null }

/**
 * Returns a new [ArrayDeque] of the given elements.
 * @return Deque.
 */
fun <T> dequeOf(vararg elements: T) = ArrayDeque(elements.asList())

/**
 * Returns a new [ArrayDeque] only of those given elements that are not null.
 * @return Deque.
 */
fun <T> dequeOfNotNull(vararg elements: T?) = ArrayDeque(elements.filterNotNull())

/**
 * Returns an empty [ArrayDeque].
 * @return Empty deque.
 */
fun <T> emptyDeque() = ArrayDeque<T>()

/**
 * Returns a new [MutableList] with the specified size, where each element is
 * calculated by calling the specified [init] function.
 * @return Mutable list.
 */
fun <T> mutableListOf(
    capacity: Int,
    init: (index: Int) -> T,
): MutableList<T> = MutableList(capacity, init)
