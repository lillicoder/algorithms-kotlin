package com.lillicoder.algorithms.sort

/**
 * Implementation of [Merge Sort](https://en.wikipedia.org/wiki/Merge_sort).
 */
class MergeSort : Sort {
    override fun <T : Comparable<T>> sort(list: List<T>): List<T> {
        if (list.isEmpty() || list.size == 1) return list

        val middle = list.size ushr 1
        val left = sort(list.subList(0, middle))
        val right = sort(list.subList(middle, list.size))
        return merge(left, right)
    }

    /**
     * Merges the given lists with elements in order.
     * @param first First list.
     * @param second Second list.
     * @return Merged list.
     */
    private fun <T : Comparable<T>> merge(
        first: List<T>,
        second: List<T>,
    ): List<T> {
        val merged = mutableListOf<T>()

        var leftPosition = 0
        var rightPosition = 0

        for (index in 0..<first.size + second.size) {
            val left = first.getOrNull(leftPosition)
            val right = second.getOrNull(rightPosition)

            if (left != null && right != null) {
                // Valid elements, put them in order
                when (left < right) {
                    true -> {
                        merged.add(left)
                        leftPosition++
                    }
                    false -> {
                        merged.add(right)
                        rightPosition++
                    }
                }
            } else if (left != null) {
                // Invalid right element, use left
                merged.add(left)
                leftPosition++
            } else if (right != null) {
                // Invalid left element, use right
                merged.add(right)
                rightPosition++
            } else {
                // Something has gone awry
                throw IndexOutOfBoundsException(
                    "Index out of bounds in merge. [leftPosition=$leftPosition, rightPosition=$rightPosition]",
                )
            }
        }

        return merged
    }
}
