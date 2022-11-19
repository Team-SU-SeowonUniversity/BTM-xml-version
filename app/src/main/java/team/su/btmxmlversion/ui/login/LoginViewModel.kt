package team.su.btmxmlversion.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _phoneNumInput = MutableLiveData<String>()
    val phoneNumInput: LiveData<String> = _phoneNumInput

    private val _emailInput = MutableLiveData<String>()
    val emailInput: LiveData<String> = _emailInput

    private val _passWardInput = MutableLiveData<String>()
    val passWardInput: LiveData<String> = _passWardInput

    fun setInfirmPhoneNumber(
        phoneNum: String
    ){
        _phoneNumInput.postValue(phoneNum)
    }

    fun setProtectorEmail(
        email: String
    ) {
        _emailInput.postValue(email)
    }

    fun setProtectorPassWard(
        passWard: String
    ) {
        _passWardInput.postValue(passWard)
    }
}
