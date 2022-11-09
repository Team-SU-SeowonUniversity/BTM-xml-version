package team.su.btmxmlversion.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _phoneNumInput = MutableLiveData<String>()
    val phoneNumInput: LiveData<String> = _phoneNumInput

    fun setData(phoneNum: String){
        _phoneNumInput.postValue(phoneNum)
    }
}
