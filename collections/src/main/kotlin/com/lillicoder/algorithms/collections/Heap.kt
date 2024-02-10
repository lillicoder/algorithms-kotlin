package com.lillicoder.algorithms.collections

/**
 * Implementation of a [Heap](https://en.wikipedia.org/wiki/Heap_(data_structure)).
 */
class Heap<T : Comparable<T>>(
    collection: Collection<T>,
    private val buffer: MutableList<T> = collection.toMutableList(),
) {
    init {
        heapify(buffer)
    }

    /**
     * Sorts this heap in place in the natural order of elements.
     */
    fun sort() {
        for (end in buffer.size - 1 downTo 1) {
            buffer.swap(end, 0)
            siftDown(buffer, 0, end)
        }
    }

    /**
     * Gets a heap of all elements stable sorted according to their natural sort order.
     * @return Sorted heap.
     */
    fun sorted(): Heap<T> {
        if (buffer.size < 1) return toHeap()
        return toHeap().apply { sort() }
    }

    /**
     * Gets a list containing all elements of this heap.
     * @return List of all elements.
     */
    fun toList() = buffer.toList()

    /**
     * Gets a heap containing all elements of this heap
     * @return Heap of all elements.
     */
    private fun toHeap() = Heap(buffer.toList())

    /**
     * Sorts elements of the given buffer in heap order.
     * @param buffer Buffer to sort.
     */
    private fun heapify(buffer: MutableList<T>) {
        if (buffer.size < 1) return

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
