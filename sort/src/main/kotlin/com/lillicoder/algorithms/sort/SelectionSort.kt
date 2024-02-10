package com.lillicoder.algorithms.sort

import com.lillicoder.algorithms.collections.swap

/**
 * Implementation of [Selection Sort](https://en.wikipedia.org/wiki/Selection_sort).
 */
class SelectionSort : Sort {
    override fun <T : Comparable<T>> sort(list: List<T>) = list.toMutableList().apply { sort(this) }

    private fun <T : Comparable<T>> sort(list: MutableList<T>) {
        for (index in 0..<list.size) {
            var minIndex = index // Find next minimum, start at index
            for (nextIndex in index + 1..<list.size) {
                if (list[nextIndex] < list[minIndex]) {
                    minIndex = nextIndex // New minimum value
                    continue
                }
            }

            if (minIndex != index) list.swap(index, minIndex)
        }
    }
}
