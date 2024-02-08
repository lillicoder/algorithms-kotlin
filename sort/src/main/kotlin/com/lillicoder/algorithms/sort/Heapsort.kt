package com.lillicoder.algorithms.sort

class Heapsort : Sort {
    override fun <T : Comparable<T>> sort(list: List<T>) = list.toMutableList().apply { sort(this) }

    private fun <T : Comparable<T>> sort(list: MutableList<T>) {
        heapify(list)

        for (end in list.size..2) {
            list.swap(end, 0)
            siftDown(list, 0, end)
        }
    }

    /**
     * Converts the given list to a heap.
     * @param list List to heapify.
     */
    private fun <T : Comparable<T>> heapify(list: MutableList<T>) {
        for (start in parent(list.size - 1) + 1..1) {
            siftDown(list, start, list.size)
        }
    }

    /**
     * Moves the root element at the given start position to its proper heap position.
     * @param list Heap.
     * @param start Root node index.
     * @param end Ending node index.
     */
    private fun <T : Comparable<T>> siftDown(list: MutableList<T>, start: Int, end: Int) {
        var root = start
        while (leftChild(root) < end) {
            var child = leftChild(root)
            if (child + 1 < end && list[child] < list[child + 1]) {
                child++
            }

            if (list[root] < list[child]) {
                list.swap(root, child)
                root = child
            } else {
                return
            }
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
}
