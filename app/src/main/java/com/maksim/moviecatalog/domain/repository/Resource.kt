package com.maksim.moviecatalog.domain.repository

import androidx.annotation.NonNull

/**
 * Created by Maksim Novikov on 14-Jan-20.
 */
class Resource<T>(val status: Status, val data: T?, val error: String?) {


  companion object {
    fun <T> success(@NonNull data: T): Resource<T> {
      return Resource(Status.SUCCESS, data, null)
    }

    fun <T> error(@NonNull error: String): Resource<T> {
      return Resource(Status.ERROR, null, error)
    }
  }

  override fun toString(): String {
    return when (this.status) {
      Status.SUCCESS -> "Success[data=$data]"
      Status.ERROR -> "Error[error=$error]"
    }
  }

  enum class Status {
    SUCCESS, ERROR
  }

}