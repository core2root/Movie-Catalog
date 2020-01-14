package com.maksim.moviecatalog.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maksim.moviecatalog.app.di.AppContainer
import java.lang.IllegalArgumentException

/**
 * Created by Maksim Novikov on 13-Jan-20.
 */
class ViewModelFactory(private val appContainer: AppContainer) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {

    throw IllegalArgumentException("Wrong view model class")
  }


}