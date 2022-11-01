package team.su.btmxmlversion.main.infirmMain.quiz

import android.os.Bundle
import android.view.View
import team.su.btmxmlversion.R
import team.su.btmxmlversion.config.BaseFragment
import team.su.btmxmlversion.databinding.FragmentQuizListViewPagerBinding
import team.su.btmxmlversion.main.infirmMain.quiz.adapter.QuizRvAdapter
import team.su.btmxmlversion.main.infirmMain.quiz.models.Quiz
import team.su.btmxmlversion.main.infirmMain.quiz.models.QuizData

class QuizListFragment:BaseFragment<FragmentQuizListViewPagerBinding>(FragmentQuizListViewPagerBinding::bind, R.layout.fragment_quiz_list_view_pager) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val test = quizAddData()

        arguments?.takeIf { it.containsKey("quiz_type_index") }?.apply {

            val type = getInt("quiz_type_index")

            binding.quizListRv.adapter = QuizRvAdapter(test[type].list)

        }
    }

    private fun quizAddData(): List<Quiz> {

        return listOf(
            Quiz(
                listOf(
                    QuizData("그림자 찾기", R.drawable.panda_shadow, 0, 1),
                    QuizData("동물 찾기", R.drawable.thumbnail_test, 0, 2),
                )
            ),

            Quiz(
                listOf(
                    QuizData("그림자 찾", R.drawable.thumbnail_test, 1, 111),
                )
            ),
            Quiz(
                listOf(
                    QuizData("그림자 찾", R.drawable.thumbnail_test, 2, 111),
                    QuizData("그림자 ", R.drawable.thumbnail_test, 2, 111),
                    QuizData("그림자", R.drawable.thumbnail_test, 2, 111),
                )
            ),
            Quiz(
                listOf(
                    QuizData("사칙연산", R.drawable.thumbnail_test, 3, 3),
                )
            ),
            Quiz(
                listOf(
                    QuizData("일기예보", R.drawable.thumbnail_test, 4, 4),
                )
            ),
        )
    }

}