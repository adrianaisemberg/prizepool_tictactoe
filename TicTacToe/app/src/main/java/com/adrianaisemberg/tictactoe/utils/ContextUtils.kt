package com.adrianaisemberg.tictactoe.utils

import android.content.Context
import android.view.LayoutInflater

fun Context.layoutInflater(): LayoutInflater = LayoutInflater.from(this)

fun Context.version(): String = packageManager.getPackageInfo(packageName, 0).versionName
