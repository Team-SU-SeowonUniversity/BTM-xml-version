package team.su.btmxmlversion.ui.main.parentMain

import android.os.Bundle
import android.util.Log
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseActivity
import team.su.btmxmlversion.databinding.ActivityParentMainBinding
import team.su.btmxmlversion.ui.main.parentMain.infirmInfo.InfirmInfoParentFragment
import team.su.btmxmlversion.ui.main.parentMain.makeQuiz.MakeQuizParentFragment
import team.su.btmxmlversion.ui.main.parentMain.myPage.MyPageParentFragment

class ParentMainActivity:
    BaseActivity<ActivityParentMainBinding>(ActivityParentMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(R.id.protector_activity_main_frame, MakeQuizParentFragment()).commitAllowingStateLoss()

        binding.bottomNav.run {
            setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.make_quiz -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.protector_activity_main_frame, MakeQuizParentFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.infirm_info -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.protector_activity_main_frame, InfirmInfoParentFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.my_page -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.protector_activity_main_frame, MyPageParentFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
        }
    }
}