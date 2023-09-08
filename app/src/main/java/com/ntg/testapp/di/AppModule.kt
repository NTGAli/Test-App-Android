package com.ntg.testapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ntg.testapp.data.local.ContactDB
import com.ntg.testapp.data.local.ContactDao
import com.ntg.testapp.remote.HeaderInterceptor
import com.ntg.testapp.remote.LoggingInterceptor
import com.ntg.testapp.remote.UnsplashApi
import com.ntg.testapp.repositories.DefaultContactRepository
import com.ntg.testapp.util.Constants.BASE_URL
import com.ntg.testapp.util.Constants.CONTACTS_DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideUnsplashDB(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ContactDB::class.java,
        CONTACTS_DB
    ).build()


    @Singleton
    @Provides
    fun provideContactDao(
        database: ContactDB
    ) = database.contactsDao()

    @Singleton
    @Provides
    fun provideDefaultContactRepository(
        dao: ContactDao,
        api: UnsplashApi
    ) = DefaultContactRepository(dao, api)


    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(LoggingInterceptor().httpLoggingInterceptor())
            .addInterceptor(HeaderInterceptor())
            .build()
    }



    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getRetrofitInstance(retrofit: Retrofit): UnsplashApi {
        return retrofit.create(UnsplashApi::class.java)
    }



}