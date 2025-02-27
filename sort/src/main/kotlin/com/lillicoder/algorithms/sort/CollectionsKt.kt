/*
 * Copyright 2025 Scott Weeden-Moody
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this project except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lillicoder.algorithms.sort

import com.lillicoder.algorithms.collections.swap
import com.lillicoder.algorithms.trees.ArrayBinaryHeap
import com.lillicoder.algorithms.trees.BinarySearchTree

/***************
 * Bubble Sort *
 ***************/

/**
 * Returns a list of all elements sorted according to their natural sort order
 * using [bubble sort](https://en.wikipedia.org/wiki/Bubble_sort).
 */
fun <T : Comparable<T>> List<T>.bubbleSorted() = toMutableList().apply { bubbleSort() }

/**
 * Sorts elements in this list in-place according to their natural sort order
 * using [bubble sort](https://en.wikipedia.org/wiki/Bubble_sort).
 */
fun <T : Comparable<T>> MutableList<T>.bubbleSort() {
    if (isEmpty()) return

    var didSwap: Boolean
    do {
        didSwap = false
        for (index in 1..<size) {
            val previous = index - 1
            if (get(previous) > get(index)) {
                swap(previous, index)
                didSwap = true
            }
        }
    } while (didSwap)
}

/************
 * Heapsort *
 ************/

// TODO Reconcile this implementation with the heap types in the trees package
/**
 * Returns a list of all elements sorted according to their natural sort order
 * using [Heapsort](https://en.wikipedia.org/wiki/Heapsort).
 */
fun <T : Comparable<T>> Collection<T>.heapSorted(): List<T> {
    val heapified = ArrayBinaryHeap(this).toMutableList()

    var end = heapified.size
    while (end > 1) {
        end -= 1
        heapified.swap(end, 0)
        heapified.siftDown(0, end)
    }

    return heapified.toList()
}

/**
 * Gets the index of the left child for this index.
 * @return Left child index.
 */
private fun Int.leftChild() = (2 * this) + 1

/**
 * Performs a heap sift-down on this list.
 */
private fun <T : Comparable<T>> MutableList<T>.siftDown(start: Int, end: Int) {
    var root = start
    while (root.leftChild() < end) {
        var child = root.leftChild()
        if (child + 1 < end && get(child) < get(child + 1)) {
            child++
        }

        if (get(root) < get(child)) {
            swap(root, child)
            root = child
        } else {
            return
        }
    }
}

/******************
 * Insertion Sort *
 ******************/

/**
 * Returns a list of all elements sorted according to their natural sort order
 * using [insertion sort](https://en.wikipedia.org/wiki/Insertion_sort).
 */
fun <T : Comparable<T>> List<T>.insertionSorted() = toMutableList().apply { insertionSort() }

/**
 * Sorts elements in this list in-place according to their natural sort order
 * using [insertion sort](https://en.wikipedia.org/wiki/Insertion_sort).
 */
fun <T : Comparable<T>> MutableList<T>.insertionSort() {
    if (isEmpty()) return

    for (index in 1..<size) {
        var currentIndex = index
        while (currentIndex > 0 && get(currentIndex - 1) > get(currentIndex)) {
            swap(currentIndex, currentIndex - 1)
            currentIndex--
        }
    }
}

/**************
 * Merge Sort *
 **************/

/**
 * Returns a list of all elements sorted according to their natural sort order
 * using [merge sort](https://en.wikipedia.org/wiki/Merge_sort).
 */
fun <T : Comparable<T>> List<T>.mergeSorted(): List<T> {
    if (size <= 1) return this

    val middle = size ushr 1
    val left = subList(0, middle).mergeSorted()
    val right = subList(middle, size).mergeSorted()
    return left.merge(right)
}

/**
 * Merges this list with the given list while keeping elements in order.
 * @param other List to merge.
 * @return Merged list.
 */
private fun <T: Comparable<T>> List<T>.merge(other: List<T>): List<T> {
    val merged = mutableListOf<T>()

    var leftPosition = 0
    var rightPosition = 0

    for (index in 0..<size + other.size) {
        val left = getOrNull(leftPosition)
        val right = other.getOrNull(rightPosition)

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

/*************
 * Quicksort *
 *************/

/**
 * Returns a list of all elements sorted according to their natural sort order
 * using [quicksort](https://en.wikipedia.org/wiki/Quicksort).
 */
fun <T : Comparable<T>> List<T>.quicksorted() = toMutableList().apply { quicksort() }

/**
 * Sorts elements in this list in-place according to their natural sort order
 * using [quicksort](https://en.wikipedia.org/wiki/Quicksort).
 */
fun <T: Comparable<T>> MutableList<T>.quicksort() {
    if (isEmpty()) return

    val pivot = partition()
    subList(0, pivot).quicksort()
    subList(pivot + 1, size).quicksort()
}

/**
 * Partitions this list and returns the pivot index.
 * @return Pivot index.
 */
private fun <T : Comparable<T>> MutableList<T>.partition(): Int {
    val pivot = last()

    var pivotIndex = -1
    forEachIndexed { index, element ->
        if (element < pivot) {
            pivotIndex++
            swap(pivotIndex, index)
        }
    }

    // Move pivot to final position
    pivotIndex++
    swap(pivotIndex, size - 1)

    return pivotIndex
}

/******************
 * Selection Sort *
 ******************/

/**
 * Returns a list of all elements sorted according to their natural sort order
 * using [selection sort](https://en.wikipedia.org/wiki/Selection_sort).
 */
fun <T : Comparable<T>> List<T>.selectionSorted() = toMutableList().apply { selectionSort() }

/**
 * Sorts elements in this list in-place according to their natural sort order
 * using [selection sort](https://en.wikipedia.org/wiki/Selection_sort).
 */
fun <T : Comparable<T>> MutableList<T>.selectionSort() {
    for (index in 0..<size) {
        var minIndex = index // Find next minimum, start at index
        for (nextIndex in index + 1..<size) {
            if (get(nextIndex) < get(minIndex)) {
                minIndex = nextIndex // New minimum value
                continue
            }
        }

        if (minIndex != index) swap(index, minIndex)
    }
}

/*************
 * Tree Sort *
 *************/

/**
 * Returns a list of all elements sorted according to their natural sort order
 * using [tree sort](https://en.wikipedia.org/wiki/Tree_sort).
 */
// In-order iteration is sorted, just return a list
fun <T : Comparable<T>> Collection<T>.treeSorted() = BinarySearchTree(this).toList()
