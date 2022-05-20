package com.example.loginapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapp.data.entities.User
import com.example.loginapp.data.network.Resource
import com.example.loginapp.repository.authentication.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authenticationRepository: AuthenticationRepository) :
    ViewModel() {

    private val _loginFlow =
        MutableStateFlow<Resource<User?>>(value = Resource.initial(data = null))
    val loginFlow: StateFlow<Resource<User?>>
        get() = _loginFlow.asStateFlow()

    suspend fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loginFlow.emit(Resource.loading(data = null))
            try {
                val response = authenticationRepository.authenticate(username, password)
                _loginFlow.emit(Resource.success(data = response.user))
            } catch (e: Exception) {
                _loginFlow.emit(Resource.error(null, message = e.message ?: e.toString()))
            }
        }
    }
}