package team.su.btmxmlversion.signup.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import team.su.btmxmlversion.R
import team.su.btmxmlversion.main.infirmMain.quiz.QuizVpFragment
import team.su.btmxmlversion.main.infirmMain.quiz.models.QuizThumbnailData
import java.util.ArrayList

class QuizTypeVpAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = QuizVpFragment()

        fragment.arguments = Bundle().apply { // 0 - 직감, 1 - 지각 ... 4 - 계산 퀴즈 목록들을 불러온다
            putInt("quiz_type_index", position)
        }

        return fragment
    }

}