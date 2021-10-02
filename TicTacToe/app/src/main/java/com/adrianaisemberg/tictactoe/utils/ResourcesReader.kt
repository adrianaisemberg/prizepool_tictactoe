package com.adrianaisemberg.tictactoe.utils

import android.content.Context
import android.graphics.Typeface
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.ByteArrayOutputStream
import java.nio.charset.Charset
import javax.inject.Singleton

interface ResourcesReader {
    fun getRawString(@RawRes id: Int): String
    fun getFont(@FontRes id: Int): Typeface
    fun getBoolean(@BoolRes id: Int): Boolean
    fun getColor(@ColorRes id: Int): Int
    fun getInteger(@IntegerRes id: Int): Int
    fun getString(@StringRes id: Int, vararg formatArgs: Any?): String
    fun getStringArray(@ArrayRes id: Int): Array<String>
}

@Singleton
class ResourcesReaderImpl(private val context: Context) : ResourcesReader {
    override fun getRawString(id: Int): String {
        return String(getResource(id), Charset.forName("UTF-8"))
    }

    private fun getResource(id: Int): ByteArray {
        val inputStream = context.resources.openRawResource(id)
        val outputStream = ByteArrayOutputStream()
        val readBuffer = ByteArray(4 * 1024)
        inputStream.use {
            do {
                val read = it.read(readBuffer, 0, readBuffer.size)
                if (read == -1) {
                    break
                }
                outputStream.write(readBuffer, 0, read)
            } while (true)

            return outputStream.toByteArray()
        }
    }

    override fun getFont(id: Int): Typeface = ResourcesCompat.getFont(context, id) as Typeface

    override fun getBoolean(id: Int): Boolean = context.resources.getBoolean(id)

    override fun getColor(id: Int): Int = ContextCompat.getColor(context, id)

    override fun getInteger(id: Int): Int = context.resources.getInteger(id)

    override fun getString(id: Int, vararg formatArgs: Any?): String =
        context.resources.getString(id, *formatArgs)

    override fun getStringArray(id: Int): Array<String> = context.resources.getStringArray(id)
}

@Module
@InstallIn(SingletonComponent::class)
object ResourcesReaderModule {

    @Provides
    fun provideResourcesReader(@ApplicationContext context: Context): ResourcesReader =
        ResourcesReaderImpl(context)
}
