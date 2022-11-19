package team.su.btmxmlversion.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupViewModel: ViewModel() {
    private val _emailInput = MutableLiveData<String>()
    val emailInput: LiveData<String> = _emailInput
    private val _passWordInput = MutableLiveData<String>()
    val passWordInput: LiveData<String> = _passWordInput

    fun setEmail(email: String) {
        _emailInput.postValue(email)
    }

    fun setPassWord(passWord: String) {
        _passWordInput.postValue(passWord)
    }
}