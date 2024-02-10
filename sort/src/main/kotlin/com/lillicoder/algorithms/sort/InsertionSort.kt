package com.lillicoder.algorithms.sort

import com.lillicoder.algorithms.collections.swap

/**
 * Implementation of [Insertion Sort](https://en.wikipedia.org/wiki/Insertion_sort).
 */
class InsertionSort : Sort {
    override fun <T : Comparable<T>> sort(list: List<T>) = list.toMutableList().apply { sort(this) }

    private fun <T : Comparable<T>> sort(list: MutableList<T>) {
        if (list.isEmpty()) return

        for (index in 1..<list.size) {
            var currentIndex = index
            while (currentIndex > 0 && list[currentIndex - 1] > list[currentIndex]) {
                list.swap(currentIndex, currentIndex - 1)
                currentIndex--
            }
        }
    }
}
