package team.su.btmxmlversion.until

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import team.su.btmxmlversion.databinding.ChangeDialogBinding
import team.su.btmxmlversion.dto.EmailChangeRequestBody
import team.su.btmxmlversion.dto.NameChangeRequestBody
import team.su.btmxmlversion.models.BaseResponse
import team.su.btmxmlversion.network.CommonDataServiceLocator
import team.su.btmxmlversion.repository.ChangeInfoRepository
import java.util.regex.Pattern

class ChangeDialog(
    context: Context,
    private val email: String,
    private val isChangeEmail: Boolean,
    private val onRefresh: () -> Unit,
    private val sharedPreferences: SharedPreferences?
) : Dialog(context), ChangeCallback
{

    private lateinit var binding: ChangeDialogBinding
    private lateinit var changeInput: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChangeDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCanceledOnTouchOutside(false)

        if (isChangeEmail) {
            binding.guidText.text = "이메일 변경"
            binding.input.hint = "변경할 이메일"

            binding.closeButton.setOnClickListener {
                this.cancel()
            }

            binding.changeButton.setOnClickListener {
                val emailRegex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[.][a-zA-Z]{2,3}$"
                changeInput = binding.input.text.toString()

                if (Pattern.matches(emailRegex, changeInput)) {
                    ChangeInfoRepository(CommonDataServiceLocator.myPageParentService)
                        .tryEmailChange(EmailChangeRequestBody(email, changeInput), this)
                } else {
                    Toast.makeText(binding.root.context, "이메일을 다시 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            binding.guidText.text = "이름 변경"
            binding.input.hint = "변경할 이름"

            binding.closeButton.setOnClickListener {
                this.cancel()
            }

            binding.changeButton.setOnClickListener {
                val nameRegex = "^[가-힣]*$"
                changeInput = binding.input.text.toString()

                if (Pattern.matches(nameRegex, changeInput)) {
                    ChangeInfoRepository(CommonDataServiceLocator.myPageParentService)
                        .tryNameChange(NameChangeRequestBody(email, changeInput), this)
                } else {
                    Toast.makeText(binding.root.context, "이름을 다시 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun getChangeSuccess(response: BaseResponse) {
        if (isChangeEmail) {
            if (response.result_code == 100) {
                Toast.makeText(binding.root.context, response.message, Toast.LENGTH_SHORT).show()
                sharedPreferences?.edit()?.putString("email", changeInput)?.apply()
                this.cancel()
                onRefresh()
            } else {
                Toast.makeText(binding.root.context, response.message, Toast.LENGTH_SHORT).show()
            }
        } else {
            if (response.result_code == 100) {
                Toast.makeText(binding.root.context, response.message, Toast.LENGTH_SHORT).show()
                sharedPreferences?.edit()?.putString("name", changeInput)?.apply()
                this.cancel()
                onRefresh()
            } else {
                Toast.makeText(binding.root.context, response.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getRetrofitException() {
        Toast.makeText(binding.root.context, "통신 오류", Toast.LENGTH_SHORT).show()
    }

}