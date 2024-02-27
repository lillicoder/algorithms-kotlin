package com.lillicoder.algorithms.sort

import com.lillicoder.algorithms.trees.ArrayBinaryHeap

/**
 * Implementation of [Heapsort](https://en.wikipedia.org/wiki/Heapsort).
 */
class Heapsort : Sort {
    override fun <T : Comparable<T>> sort(list: List<T>) = ArrayBinaryHeap(list).sorted().toList()
}
