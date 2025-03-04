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

package com.lillicoder.algorithms.heaps

import com.lillicoder.algorithms.collections.swap
import com.lillicoder.algorithms.trees.leftChild
import com.lillicoder.algorithms.trees.parent

/**
 * Sorts the elements in this list in heap order.
 * @return This list in heap order.
 */
fun <T : Comparable<T>> MutableList<T>.heapify() {
    for (start in (size - 1).parent() downTo 0) {
        siftDown(start, size)
    }
}

/**
 * Moves the element at the given start position to its proper heap position.
 * @param start Starting node index.
 * @param end Ending node index.
 */
fun <T : Comparable<T>> MutableList<T>.siftDown(
    start: Int,
    end: Int,
) {
    if (isEmpty()) return
    if (start < 0 || start > size) return
    if (end < 0 || end > size) return

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
