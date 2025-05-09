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

import com.lillicoder.algorithms.trees.ArrayBinaryTree
import com.lillicoder.algorithms.trees.BinaryNode
import com.lillicoder.algorithms.trees.BinaryTree

/**
 * Implementation of a [Binary Heap](https://en.wikipedia.org/wiki/Binary_heap)
 * that uses a backing array of values.
 * @param tree Backing [ArrayBinaryTree].
 */
class ArrayBinaryHeap<T : Comparable<T>>(
    private val tree: ArrayBinaryTree<T>,
) : BinaryTree<T> by tree {
    /**
     * Instantiates this heap with the contents of the given [Collection].
     * @param collection Collection.
     * @return Heap.
     */
    constructor(collection: Collection<T>) : this(
        ArrayBinaryTree(
            collection.heapified().mapIndexed { index, element ->
                BinaryNode(
                    index.toLong(),
                    element,
                )
            },
        ),
    )
}
