package team.su.btmxmlversion.main.infirmMain.quiz

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import team.su.btmxmlversion.R
import team.su.btmxmlversion.config.BaseFragment
import team.su.btmxmlversion.databinding.FragmentQuizBinding
import team.su.btmxmlversion.main.infirmMain.MainActivity
import team.su.btmxmlversion.signup.adapter.QuizTypeVpAdapter

class QuizFragment:BaseFragment<FragmentQuizBinding>(FragmentQuizBinding::bind, R.layout.fragment_quiz) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.quizTypeVp.adapter = QuizTypeVpAdapter(this)

        TabLayoutMediator(binding.quizTypeTab, binding.quizTypeVp) { tab, position ->
            when(position) {
                0 -> tab.text = "지각"
                1 -> tab.text = "기억"
                2 -> tab.text = "직감"
                3 -> tab.text = "계산"
                4 -> tab.text = "분석"
            }
        }.attach()
    }

}