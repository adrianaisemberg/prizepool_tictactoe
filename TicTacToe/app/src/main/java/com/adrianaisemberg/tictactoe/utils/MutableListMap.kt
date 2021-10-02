package com.adrianaisemberg.tictactoe.utils

class MutableListMap<K, V> {
    val map = mutableMapOf<K, MutableList<V>>()

    val keys: MutableSet<K>
        get() = map.keys

    val values: MutableCollection<MutableList<V>>
        get() = map.values

    operator fun get(key: K): MutableList<V> = map[key] ?: mutableListOf()

    operator fun set(key: K, value: V) {
        val list = map[key] ?: mutableListOf()
        list.add(value)
        map[key] = list
    }
}

fun <K, V> mutableListMapOf(): MutableListMap<K, V> = MutableListMap()
