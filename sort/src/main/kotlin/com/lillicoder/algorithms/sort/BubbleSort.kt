package com.lillicoder.algorithms.sort

/**
 * Implementation of [Bubble Sort](https://en.wikipedia.org/wiki/Bubble_sort).
 */
class BubbleSort : Sort {
    override fun <T : Comparable<T>> sort(list: List<T>) = list.toMutableList().apply { sort(this) }

    /**
     * Sorts the given [MutableList] using Bubble Sort.
     * @param list List to sort.
     */
    private fun <T : Comparable<T>> sort(list: MutableList<T>) {
        if (list.isEmpty()) return

        var didSwap: Boolean
        do {
            didSwap = false
            for (index in 1..<list.size) {
                val previous = index - 1
                if (list[previous] > list[index]) {
                    list.swap(previous, index)
                    didSwap = true
                }
            }
        } while (didSwap)
    }
}
