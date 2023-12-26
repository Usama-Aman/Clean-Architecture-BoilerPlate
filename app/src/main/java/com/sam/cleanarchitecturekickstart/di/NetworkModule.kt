package com.sam.cleanarchitecturekickstart.di

import com.sam.cleanarchitecturekickstart.data.remote.dto.ApiService
import com.sam.cleanarchitecturekickstart.data.repository.RepositoryImpl
import com.sam.cleanarchitecturekickstart.domain.repository.Repository
import com.sam.cleanarchitecturekickstart.domain.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideKtorClient(ktorClient: KtorClient): HttpClient = ktorClient.getHttpClient()


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val clientInterceptor = Interceptor { chain: Interceptor.Chain ->
            var request = chain.request()
            val url = request.url.newBuilder()
                .addQueryParameter("Accept", "application/json")
                .addQueryParameter("Connection", "close")
                .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }

        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addNetworkInterceptor(clientInterceptor)
            .build()

        return Retrofit.Builder()
//            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesRepository(apiService: ApiService, httpClient: HttpClient): Repository {
        return RepositoryImpl(apiService = apiService, httpClient = httpClient)
    }

    @Provides
    @Singleton
    fun providesUseCase(repository: Repository) = UseCase(repository)


}