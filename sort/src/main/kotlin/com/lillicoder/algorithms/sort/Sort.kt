package com.lillicoder.algorithms.sort

/**
 * Interface for sort algorithms.
 */
interface Sort {
    /**
     * Sorts the given list of [Comparable] objects.
     * @param list List to sort.
     * @return Sorted list.
     */
    fun <T : Comparable<T>> sort(list: List<T>): List<T>
}
