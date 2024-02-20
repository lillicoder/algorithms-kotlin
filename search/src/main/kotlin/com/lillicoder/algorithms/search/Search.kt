package com.lillicoder.algorithms.search

/**
 * Performs a binary search on this list to find the position of the given element.
 * @param toFind Element to find.
 * @return List position or -1 if there is no such element.
 */
fun <T : Comparable<T>> List<T>.binarySearch(toFind: T): Int {
    var low = 0
    var high = size - 1
    while (low <= high) {
        val middle = (low + high) ushr 1
        val midElement = get(middle)

        if (midElement == toFind) {
            return middle
        } else if (midElement < toFind) {
            low = middle + 1
        } else {
            high = middle - 1
        }
    }

    return -1
}

/**
 * Performs a linear search on this list to find the position of the given element.
 * @param toFind Element to find.
 * @return List position or -1 if there is no such element.
 */
fun <T : Comparable<T>> List<T>.linearSearch(toFind: T): Int {
    forEachIndexed { index, element ->
        if (element == toFind) return index
    }

    return -1
}
