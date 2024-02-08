package com.lillicoder.algorithms.sort

/**
 * Implementation of [Quicksort](https://en.wikipedia.org/wiki/Quicksort).
 */
class Quicksort : Sort {
    override fun <T : Comparable<T>> sort(list: List<T>) = list.toMutableList().apply { sort(this) }

    /**
     * Sorts the given [MutableList] using Quicksort.
     * @param list List to sort.
     */
    private fun <T : Comparable<T>> sort(list: MutableList<T>) {
        if (list.isEmpty()) return

        val pivot = partition(list)
        sort(list.subList(0, pivot))
        sort(list.subList(pivot + 1, list.size))
    }

    /**
     * Partitions the given [MutableList] and returns the pivot index.
     * @param list List to partition.
     * @return Pivot index.
     */
    private fun <T : Comparable<T>> partition(list: MutableList<T>): Int {
        val pivot = list.last()

        var pivotIndex = -1
        list.forEachIndexed { index, element ->
            if (element < pivot) {
                pivotIndex++
                list.swap(pivotIndex, index)
            }
        }

        // Move pivot to final position
        pivotIndex++
        list.swap(pivotIndex, list.size - 1)

        return pivotIndex
    }
}
