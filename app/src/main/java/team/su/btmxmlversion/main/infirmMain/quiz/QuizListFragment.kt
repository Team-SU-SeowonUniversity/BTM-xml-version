package team.su.btmxmlversion.main.infirmMain.quiz

import android.os.Bundle
import android.view.View
import team.su.btmxmlversion.R
import team.su.btmxmlversion.config.BaseFragment
import team.su.btmxmlversion.databinding.FragmentQuizListViewPagerBinding
import team.su.btmxmlversion.main.infirmMain.quiz.adapter.QuizRvAdapter
import team.su.btmxmlversion.main.infirmMain.quiz.data.QuizResponse
import team.su.btmxmlversion.main.infirmMain.quiz.data.Quiz

class QuizListFragment:
    BaseFragment<FragmentQuizListViewPagerBinding>(FragmentQuizListViewPagerBinding::bind, R.layout.fragment_quiz_list_view_pager) {

    companion object {
        val quizResponse = QuizResponse(
            listOf(
                Quiz("그림자 찾기", R.drawable.panda_shadow, 0, 1),
                Quiz("깜박깜박", R.drawable.thumbnail_test, 1, 5),
                Quiz("???", R.drawable.thumbnail_test, 2, 111),
                Quiz("사칙연산", R.drawable.thumbnail_test, 3, 3),
                Quiz("일기예보", R.drawable.thumbnail_test, 4, 4),
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf { it.containsKey("quiz_type_index") }?.apply {
            val quizType = getInt("quiz_type_index")
            val quizData = quizResponse.result.filter { it.quizType == quizType }

            binding.quizListRv.adapter = QuizRvAdapter(quizData)
        }

    }

}