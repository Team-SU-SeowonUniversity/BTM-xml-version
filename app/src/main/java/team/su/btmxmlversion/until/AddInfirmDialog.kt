package team.su.btmxmlversion.until

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.widget.Toast
import team.su.btmxmlversion.databinding.InterlockDialogScreenBinding
import team.su.btmxmlversion.dto.InterlockInfoRequestBody
import team.su.btmxmlversion.models.BaseResponse
import team.su.btmxmlversion.network.CommonDataServiceLocator
import team.su.btmxmlversion.repository.InterlockRepository

class AddInfirmDialog(
    context: Context,
    private val email: String,
    private val protectorName: String,
    private val facilityName: String?
) : Dialog(context), AddInfirmCallback {

    private lateinit var binding: InterlockDialogScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = InterlockDialogScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCanceledOnTouchOutside(false)

        binding.phoneNumberInput.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        binding.closeButton.setOnClickListener {
            this.cancel()
        }

        binding.interlockButton.setOnClickListener {
            val phoneNumber = binding.phoneNumberInput.text.toString()
            val infirmName = binding.nameInput.text.toString()

            InterlockRepository(CommonDataServiceLocator.infirmInfoParentService)
                .trySetInterlockInfo(InterlockInfoRequestBody(
                    phoneNumber = phoneNumber,
                    infirmName = infirmName,
                    email = email,
                    protectorName = protectorName,
                    facilityName = facilityName
                ),this)
        }
    }

    override fun setInterlock(response: BaseResponse) {
        if (response.result_code == 100) {
            Toast.makeText(binding.root.context, response.message, Toast.LENGTH_SHORT).show()
            this.cancel()
        } else {
            Toast.makeText(binding.root.context, response.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getRetrofitException() {
        Toast.makeText(binding.root.context, "통신 오류", Toast.LENGTH_SHORT).show()
    }

}