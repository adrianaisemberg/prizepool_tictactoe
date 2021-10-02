package com.adrianaisemberg.tictactoe.utils

typealias Action = () -> Unit
typealias ActionOf<T> = (t: T) -> Unit
typealias Func<TIn, TOut> = (t: TIn) -> TOut
typealias FuncOf<TOut> = () -> TOut

val NOOP = {}

