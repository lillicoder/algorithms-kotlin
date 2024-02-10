package com.lillicoder.algorithms.sort

import com.lillicoder.algorithms.collections.Heap

/**
 * Implementation of [Heapsort](https://en.wikipedia.org/wiki/Heapsort).
 */
class Heapsort : Sort {
    override fun <T : Comparable<T>> sort(list: List<T>) = Heap(list).sorted().toList()
}
