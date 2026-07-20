package com.example.smartpark.feature_splash.presentaion

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartpark.navigation.Routes
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _state = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<SplashEffect>()
    val effect = _effect.asSharedFlow()

    init {
        onEvent(SplashEvent.Initialize)
    }

    fun onEvent(event: SplashEvent) {
        when (event) {
            SplashEvent.Initialize -> initializeApp()
        }
    }

    private fun initializeApp() {

        viewModelScope.launch {

            _state.update { it.copy(isLoading = true) }

            delay(2500)

//            firebaseAuth.signOut()


            val currentUser = firebaseAuth.currentUser

            Log.d("SmartPark", "Current User = $currentUser")

            if (currentUser == null) {

                Log.d("SmartPark", "Navigate -> Login")

                _effect.emit(
                    SplashEffect.Navigate(Routes.Login)
                )

            } else {

                Log.d("SmartPark", "Navigate -> Home")

                _effect.emit(
                    SplashEffect.Navigate(Routes.Home)
                )

            }

            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
}