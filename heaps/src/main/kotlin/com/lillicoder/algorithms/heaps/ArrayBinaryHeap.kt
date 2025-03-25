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

import com.lillicoder.algorithms.collections.swap
import com.lillicoder.algorithms.trees.Traversal

/**
 * Implementation of a [BinaryHeap] that uses a backing array of values.
 */
class ArrayBinaryHeap<T : Comparable<T>>() : BinaryHeap<T> {
    private val buffer = mutableListOf<T>()

    /**
     * Instantiates this heap with the contents of the given [Collection].
     * @param collection Collection.
     * @return Heap.
     */
    constructor(collection: Collection<T>) : this() {
        buffer.addAll(collection)
        heapify(buffer)
    }

    override fun left(key: T) =
        buffer.getOrNull(
            leftChild(
                buffer.indexOf(key),
            ),
        )

    override fun right(key: T) =
        buffer.getOrNull(
            rightChild(
                buffer.indexOf(key),
            ),
        )

    override fun root(): T? = buffer.firstOrNull()

    override fun insert(key: T) {
        buffer.add(key)
        heapify(buffer)
    }

    override fun delete(key: T) {
        val position = buffer.indexOf(key)
        if (position >= 0) {
            buffer.swap(position, buffer.size - 1)
            buffer.removeLast()
            heapify(buffer)
        }
    }

    // TODO Implement support for traversal types
    override fun iterator(traversal: Traversal) = buffer.iterator()

    /**
     * Sorts elements of the given buffer in heap order.
     * @param buffer Buffer to sort.
     */
    private fun heapify(buffer: MutableList<T>) {
        if (buffer.size <= 1) return

        for (start in parent(buffer.size - 1) downTo 0) {
            siftDown(buffer, start, buffer.size)
        }
    }

    /**
     * Gets the index of the left child node for the given index.
     * @param index Index to get the left child of.
     * @return Left child index.
     */
    private fun leftChild(index: Int) = (2 * index) + 1

    /**
     * Gets the index of the right child node for the given index.
     * @param index Index to get the right child of.
     * @return Right child index.
     */
    private fun rightChild(index: Int) = (2 * index) + 2

    /**
     * Gets the index of the parent node for the given index.
     * @param index Index to get parent of.
     * @return Parent node index.
     */
    private fun parent(index: Int) = index - 1 ushr 1

    /**
     * Moves the element at the given start position to its proper heap position.
     * @param buffer Heap.
     * @param start Starting node index.
     * @param end Ending node index.
     */
    private fun siftDown(
        buffer: MutableList<T>,
        start: Int,
        end: Int,
    ) {
        var root = start
        while (leftChild(root) < end) {
            var child = leftChild(root)
            if (child + 1 < end && buffer[child] < buffer[child + 1]) {
                child++
            }

            if (buffer[root] < buffer[child]) {
                buffer.swap(root, child)
                root = child
            } else {
                return
            }
        }
    }
}
