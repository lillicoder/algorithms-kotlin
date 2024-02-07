package com.lillicoder.algorithms.sort

import java.util.Collections

/**
 * Swaps the elements in this list at the given positions.
 * @param first First element index.
 * @param second Second element index.
 */
fun <T> MutableList<T>.swap(
    first: Int,
    second: Int,
) = Collections.swap(this, first, second)
