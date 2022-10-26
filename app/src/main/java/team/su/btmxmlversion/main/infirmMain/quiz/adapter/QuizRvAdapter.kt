package team.su.btmxmlversion.main.infirmMain.quiz.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.su.btmxmlversion.databinding.QuizRecyclerViewItemBinding
import team.su.btmxmlversion.main.infirmMain.multipleChoiceQuiz.MultipleChoiceQuizActivity
import team.su.btmxmlversion.main.infirmMain.quiz.models.QuizData

class QuizRvAdapter(private val list: List<QuizData>): RecyclerView.Adapter<QuizRvAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: QuizRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: QuizData) {
            binding.thumbnailImage.setImageResource(item.quizThumbnailImage)
            binding.thumbnailText.text = item.quizName

            binding.quizItemCard.setOnClickListener {
                val intent = Intent(binding.root.context, MultipleChoiceQuizActivity::class.java)
                intent.run { binding.root.context.startActivity(this) }
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