package com.lillicoder.algorithms.trees

/**
 * Interface for implementations of a [Tree](https://en.wikipedia.org/wiki/Tree_(data_structure).
 */
interface Tree<T> : Iterable<T> {
    override fun iterator(): Iterator<T> = iterator(Traversal.IN_ORDER)

    /**
     * Inserts a new [Node] in this tree for the given key.
     * @param key Key to insert.
     */
    fun insert(key: T)

    /**
     * Deletes the [Node] from this tree matching the given key.
     * @param key Key to delete.
     */
    fun delete(key: T)

    /**
     * Gets an [Iterator] for this tree that obeys the given [Traversal].
     * @param traversal Traversal order.
     * @return Iterator.
     */
    fun iterator(traversal: Traversal): Iterator<T>

    /**
     * Gets the root node of this tree.
     * @return Root node or null if this tree has no nodes.
     */
    fun root(): T?

    /**
     * Searches this tree for the given key.
     * @param key Key to search for.
     * @return Found key or null if there is no such key.
     */
    fun search(key: T) = find { it == key }

    /**
     * Gets the number of nodes in this tree.
     * @return Size.
     */
    fun size() = iterator().asSequence().count()
}
