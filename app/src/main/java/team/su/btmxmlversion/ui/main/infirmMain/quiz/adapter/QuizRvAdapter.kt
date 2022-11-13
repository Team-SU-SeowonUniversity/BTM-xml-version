package team.su.btmxmlversion.ui.main.infirmMain.quiz.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.su.btmxmlversion.databinding.QuizRecyclerViewItemBinding
import team.su.btmxmlversion.ui.main.infirmMain.quiz.analysis.weather.WeatherActivity
import team.su.btmxmlversion.ui.main.infirmMain.quiz.calculation.arithmetic.ArithmeticActivity
import team.su.btmxmlversion.ui.main.infirmMain.quiz.data.Quiz
import team.su.btmxmlversion.ui.main.infirmMain.quiz.intuition.shadowing.ShadowingActivity
import team.su.btmxmlversion.ui.main.infirmMain.quiz.memory.HwatuCard.HwatuCardActivity
import team.su.btmxmlversion.ui.main.infirmMain.quiz.perception.blinking.BlinkingActivity

class QuizRvAdapter(private val list: List<Quiz>): RecyclerView.Adapter<QuizRvAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: QuizRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Quiz) {
            binding.thumbnailImage.setImageResource(item.quizThumbnailImage)
            binding.thumbnailText.text = item.quizName

            binding.quizItemCard.setOnClickListener {
                val quizId = item.quizId
                val context = binding.root.context

                when (quizId) {
                    1 -> Intent(context, ShadowingActivity::class.java).run { context.startActivity(this) } // 직감 - 그림자 찾기
                    2 -> Intent(context, HwatuCardActivity::class.java).run { context.startActivity(this) } // 기억 - 화투패 맞추기
                    3 -> Intent(context, ArithmeticActivity::class.java).run { context.startActivity(this) } // 계산 - 사칙연산
                    4 -> Intent(context, WeatherActivity::class.java).run { context.startActivity(this) } // 분석 - 일기예보
                    5 -> Intent(context, BlinkingActivity::class.java).run { context.startActivity(this) } // 지각 - 깜박깜박
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(QuizRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}