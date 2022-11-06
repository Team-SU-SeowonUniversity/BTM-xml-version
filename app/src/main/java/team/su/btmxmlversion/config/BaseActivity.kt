package team.su.btmxmlversion.config

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import team.su.btmxmlversion.until.LoadingDialog
import java.util.Timer

abstract class BaseActivity<B : ViewBinding>(private val inflate: (LayoutInflater) -> B) :
    AppCompatActivity() {

    companion object {
        var timerTask: Timer? = null
    }

    protected lateinit var binding: B
        private set
    lateinit var mLoadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun showLoadingDialog(context: Context) {
        mLoadingDialog = LoadingDialog(context)
        mLoadingDialog.show()
    }

    fun dismissLoadingDialog() {
        if (mLoadingDialog.isShowing) {
            mLoadingDialog.dismiss()
        }
    }

    fun showCustomToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun setTimer(count: Int, textView: TextView, context: Context) {
        var time = count + 1

        timerTask = kotlin.concurrent.timer(period = 1000) {
            time--

            if (time == 0) {
                timerTask?.cancel()
                runOnUiThread { Toast.makeText(context,"시간초과",Toast.LENGTH_SHORT).show() }
                finish()
            }

            runOnUiThread {
                textView.text = time.toString()
            }
        }
    }

}