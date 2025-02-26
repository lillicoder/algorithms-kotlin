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

package com.lillicoder.algorithms.graphs.traversal

import com.lillicoder.algorithms.graphs.Graph
import com.lillicoder.algorithms.graphs.Vertex

/**
 * A traversal method for a [Graph].
 */
interface Traversal<T> {
    /**
     * Gets the path from the given start [Vertex].
     * @param start Start vertex.
     * @param destination Optional destination vertex. If null, all vertices will be visited.
     * @return Path taken.
     */
    fun path(
        start: Vertex<T>,
        destination: Vertex<T>? = null,
    ): List<Vertex<T>>
}
