package team.su.btmxmlversion.main.infirmMain.quiz.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.su.btmxmlversion.databinding.QuizRecyclerViewItemBinding
import team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.ShadowingActivity
import team.su.btmxmlversion.main.infirmMain.quiz.models.QuizData

class QuizRvAdapter(private val list: List<QuizData>): RecyclerView.Adapter<QuizRvAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: QuizRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: QuizData) {
            binding.thumbnailImage.setImageResource(item.quizThumbnailImage)
            binding.thumbnailText.text = item.quizName

            binding.quizItemCard.setOnClickListener {
                val quizId = item.quizId
                val context = binding.root.context

                when (quizId) {
                    1 -> Intent(context, ShadowingActivity::class.java).run { context.startActivity(this) } // 지각 - 그림자 찾기

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