package com.lillicoder.algorithms.sort

import com.lillicoder.algorithms.trees.BinarySearchTree

/**
 * Implementation of [Tree Sort](https://en.wikipedia.org/wiki/Tree_sort).
 */
class TreeSort : Sort {
    override fun <T : Comparable<T>> sort(list: List<T>) = BinarySearchTree(list).sorted()
}
