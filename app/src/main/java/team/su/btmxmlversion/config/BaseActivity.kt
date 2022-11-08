package team.su.btmxmlversion.config

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import team.su.btmxmlversion.main.infirmMain.quiz.until.QuizType
import team.su.btmxmlversion.until.LoadingDialog
import java.util.Timer

abstract class BaseActivity<B : ViewBinding>(private val inflate: (LayoutInflater) -> B) :
    AppCompatActivity() {

    companion object {
        var timerTask: Timer? = null
        var time = 0
        var isDiagnosis: Boolean = false
    }

    //val isDiagnosis = intent.getBooleanExtra("isDiagnosis", false)

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
        time = count

        timerTask = kotlin.concurrent.timer(period = 1000) {
            if (time == 0) {
                timerTask?.cancel()
                runOnUiThread { Toast.makeText(context,"시간초과",Toast.LENGTH_SHORT).show() }
                finish()
            } else {
                runOnUiThread {
                    textView.text = time.toString()
                    time--
                }
            }
        }
    }

    fun onQuizResult(onPass: Boolean?, quizType: QuizType, count: Int) {
        val getQuizTypeValue = getSharedPreferences("BTM_APP", 0).getInt(quizType.toString(), 0)
        val getFailValue = getSharedPreferences("BTM_APP", 0).getInt("FAIL_VALUE", 0)
        val getPassValue = getSharedPreferences("BTM_APP", 0).getInt("PASS_VALUE", 0)
        val sharedPreferencesUpdate = getSharedPreferences("BTM_APP", 0).edit()

        when (onPass) {
            true -> {
                val resultScore = setScore(count)

                sharedPreferencesUpdate?.apply {
                    putInt("PASS_VALUE", getPassValue + 1)
                    putInt(quizType.toString(), getQuizTypeValue + resultScore)
                }?.apply()
            }
            false -> {
                sharedPreferencesUpdate?.apply {
                    putInt("FAIL_VALUE", getFailValue + 1)
                    putInt(quizType.toString(), getQuizTypeValue)
                }?.apply()
            }
            else -> { }
        }
    }

    private fun setScore(count: Int): Int {
        var score = 0

        when (count) {
            10 -> {
                when (time + 1) { // 화면에 보이는 시간과 실제 데이터값과 1 차이가 있기 때문에 +1
                    0 -> score = 0
                    1 -> score = 10
                    2 -> score = 20
                    3 -> score = 30
                    4 -> score = 40
                    5 -> score = 50
                    6 -> score = 60
                    7 -> score = 70
                    8 -> score = 80
                    9 -> score = 90
                    10 -> score = 100
                }
            }
            5 -> {
                when (time + 1) { // 화면에 보이는 시간과 실제 데이터값과 1 차이가 있기 때문에 +1
                    0 -> score = 0
                    1 -> score = 20
                    2 -> score = 40
                    3 -> score = 60
                    4 -> score = 80
                    5 -> score = 100
                }
            }
        }

        return score
    }

}