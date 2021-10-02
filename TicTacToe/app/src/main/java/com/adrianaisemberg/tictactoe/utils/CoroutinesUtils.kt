@file:Suppress("FunctionName", "unused")

package com.adrianaisemberg.tictactoe.utils

import kotlinx.coroutines.*
import java.util.*
import kotlin.concurrent.schedule

@OptIn(DelicateCoroutinesApi::class)
fun async(block: suspend CoroutineScope.() -> Unit) = GlobalScope.launch(
    block = block
)

@OptIn(DelicateCoroutinesApi::class)
fun async_io(block: suspend CoroutineScope.() -> Unit) = GlobalScope.launch(
    context = Dispatchers.IO,
    block = block
)

@OptIn(DelicateCoroutinesApi::class)
fun async_ui(block: suspend CoroutineScope.() -> Unit) = GlobalScope.launch(
    context = Dispatchers.Main,
    block = block
)

suspend fun <T> async_return(block: suspend CoroutineScope.() -> T): T = withContext(
    context = Dispatchers.Default,
    block = block
)

suspend fun <T> async_io_return(block: suspend CoroutineScope.() -> T): T = withContext(
    context = Dispatchers.IO,
    block = block
)

suspend fun <T> async_ui_return(block: suspend CoroutineScope.() -> T): T = withContext(
    context = Dispatchers.Main,
    block = block
)

fun schedule(delay: Long, action: Action) = Timer("Timer").schedule(delay) {
    action.invoke()
}

fun schedule_ui(delay: Long, action: Action) = Timer("Timer").schedule(delay) {
    async_ui {
        action.invoke()
    }
}
