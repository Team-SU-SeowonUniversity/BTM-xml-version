package team.su.btmxmlversion.main.infirmMain.quiz

import android.os.Bundle
import android.view.View
import team.su.btmxmlversion.R
import team.su.btmxmlversion.config.BaseFragment
import team.su.btmxmlversion.databinding.QuizViewPagerItemBinding
import team.su.btmxmlversion.main.infirmMain.quiz.adapter.QuizRvAdapter
import team.su.btmxmlversion.main.infirmMain.quiz.models.QuizThumbnailData
import java.util.ArrayList

class QuizVpFragment:BaseFragment<QuizViewPagerItemBinding>(QuizViewPagerItemBinding::bind, R.layout.quiz_view_pager_item) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf { it.containsKey("quiz_type_index") }?.apply {
            // binding.test.text = getInt("object").toString()
            // index에 따라 어댑터를 바꿔주고 0~4까지 각각의 타입에 맞는 퀴즈 목록을 로드

            binding.quizRv.adapter = QuizRvAdapter(quizAddData())
        }
    }

    private fun quizAddData(): ArrayList<QuizThumbnailData> {
        val item = arrayListOf<QuizThumbnailData>()

        item.add(QuizThumbnailData("그림자 찾기", R.drawable.thumbnail_test))
        item.add(QuizThumbnailData("그림자 찾기", R.drawable.thumbnail_test))
        item.add(QuizThumbnailData("그림자 찾기", R.drawable.thumbnail_test))
        item.add(QuizThumbnailData("그림자 찾기", R.drawable.thumbnail_test))
        item.add(QuizThumbnailData("그림자 찾기", R.drawable.thumbnail_test))
        item.add(QuizThumbnailData("그림자 찾기", R.drawable.thumbnail_test))
        item.add(QuizThumbnailData("그림자 찾기", R.drawable.thumbnail_test))
        item.add(QuizThumbnailData("그림자 찾기", R.drawable.thumbnail_test))
        item.add(QuizThumbnailData("그림자 찾기", R.drawable.thumbnail_test))

        return item
    }


}