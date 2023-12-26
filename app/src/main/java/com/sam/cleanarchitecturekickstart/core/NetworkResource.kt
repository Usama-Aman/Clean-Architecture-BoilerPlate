package com.sam.cleanarchitecturekickstart.core

sealed class NetworkResource<T>(val data: T? = null, val error: String? = null) {
    class Success<T>(data: T) : NetworkResource<T>(data = data)
    class Error<T>(error: String? = null) : NetworkResource<T>(error = error)
    class Loading<T>() : NetworkResource<T>()
}