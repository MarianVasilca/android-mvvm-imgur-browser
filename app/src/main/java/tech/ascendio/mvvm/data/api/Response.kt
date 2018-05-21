package tech.ascendio.mvvm.data.api

data class Response<T>(
        val data: T,
        val success: Boolean,
        val status: Int
)