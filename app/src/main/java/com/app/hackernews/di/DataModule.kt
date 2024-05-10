package com.app.hackernews.di

import android.content.Context
import androidx.room.Room
import com.app.hackernews.data.PostDataSource
import com.app.hackernews.data.local.DataBase
import com.app.hackernews.data.local.LocalDataSource
import com.app.hackernews.data.local.PostDao
import com.app.hackernews.data.remote.PostService
import com.app.hackernews.data.remote.RemoteDataSource
import com.app.hackernews.domain.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun providesRetrofit(): Retrofit {
        val json = Json { ignoreUnknownKeys = true }
        val contentType = MediaType.get("application/json")
        return Retrofit.Builder()
            .baseUrl("https://hn.algolia.com/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    fun providePostService(retrofit: Retrofit): PostService {
        return retrofit.create(PostService::class.java)
    }

    @Provides
    @Singleton
    fun providesDataBase(@ApplicationContext context: Context): DataBase {
        return Room.databaseBuilder(context, DataBase::class.java, "hacker-news").build()
    }

    @Provides
    @Singleton
    fun providesPostDao(dataBase: DataBase): PostDao {
        return dataBase.postDao()
    }

    @Provides
    @Named("remote")
    fun providesRemotePostRepository(postService: PostService): PostRepository {
        return RemoteDataSource(service = postService)
    }

    @Provides
    @Named("local")
    fun provideLocalPostRepository(postDao: PostDao): PostRepository {
        return LocalDataSource(dao = postDao)
    }

    @Provides
    fun providesPostRepository(
        @Named("remote") remoteRepository: PostRepository,
        @Named("local") localRepository: PostRepository
    ): PostRepository {
        return PostDataSource(localRepository, remoteRepository)
    }
}
