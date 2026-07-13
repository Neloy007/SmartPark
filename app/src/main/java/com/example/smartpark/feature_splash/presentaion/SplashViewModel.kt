package com.example.smartpark.feature_splash.presentaion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartpark.navigation.Routes
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
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
    val state: StateFlow<SplashState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<SplashEffect>()
    val effect: SharedFlow<SplashEffect> = _effect.asSharedFlow()

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

            _state.update {
                it.copy(isLoading = true)
            }

            // Show splash animation
            delay(2500)

            val currentUser = firebaseAuth.currentUser

            if (currentUser == null) {

                _effect.emit(
                    SplashEffect.Navigate(Routes.Login)
                )

            } else {

                /*
                 * Later we'll replace this section
                 * with Firestore role checking.
                 *
                 * Example:
                 *
                 * users/{uid}
                 *      role = DRIVER
                 */

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