package team.su.btmxmlversion.ui.main.parentMain.makeQuiz

import android.os.Bundle
import android.view.View
import team.su.btmxmlversion.R
import team.su.btmxmlversion.base.BaseFragment
import team.su.btmxmlversion.databinding.FragmentMakeQuizParentBinding

class MakeQuizParentFragment:
    BaseFragment<FragmentMakeQuizParentBinding>(FragmentMakeQuizParentBinding::bind, R.layout.fragment_make_quiz_parent)
{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 퀴즈 제작 해야함

    }
}