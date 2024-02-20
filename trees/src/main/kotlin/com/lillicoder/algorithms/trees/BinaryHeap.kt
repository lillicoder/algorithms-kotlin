package com.lillicoder.algorithms.trees

import com.lillicoder.algorithms.collections.swap

/**
 * Implementation of a [Binary Heap](https://en.wikipedia.org/wiki/Binary_heap).
 */
class BinaryHeap<T : Comparable<T>>() : Heap<T> {
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
