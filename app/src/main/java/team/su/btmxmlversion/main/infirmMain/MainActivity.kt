package team.su.btmxmlversion.main.infirmMain

import android.os.Bundle
import team.su.btmxmlversion.R
import team.su.btmxmlversion.config.BaseActivity
import team.su.btmxmlversion.databinding.ActivityMainBinding
import team.su.btmxmlversion.main.infirmMain.dementiaDiagnosis.DementiaDiagnosisFragment
import team.su.btmxmlversion.main.infirmMain.myPage.MyPageFragment
import team.su.btmxmlversion.main.infirmMain.quiz.QuizFragment

class MainActivity: BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.main_frame, QuizFragment()).commitAllowingStateLoss()

        binding.bottomNav.run{
            setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.quiz_btn -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frame, QuizFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.dementia_diagnosis_btn -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frame, DementiaDiagnosisFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.my_page_btn -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frame, MyPageFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
        }

    }

}