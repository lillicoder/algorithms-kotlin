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

package com.lillicoder.algorithms.trees

/**
 * Gets the index of the left child for this index.
 * @return Left child index.
 */
fun Int.leftChild() = (2 * this) + 1

/**
 * Gets the index of the parent for this index.
 * @return Parent index.
 */
fun Int.parent() = this - 1 ushr 1

/**
 * Gets the index of the right child for this index.
 * @return Right child index.
 */
fun Int.rightChild() = (2 * this) + 2
