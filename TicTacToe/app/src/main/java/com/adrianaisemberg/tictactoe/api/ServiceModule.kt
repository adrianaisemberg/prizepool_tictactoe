package com.adrianaisemberg.tictactoe.api

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    // TODO: put in config
    private const val URL = "http://192.168.86.40:5000/"

    @Provides
    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder().build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    fun provideTicTacToeService(retrofit: Retrofit): TicTacToeService =
        retrofit.create(TicTacToeService::class.java)
}