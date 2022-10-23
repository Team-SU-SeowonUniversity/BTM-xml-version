package team.su.btmxmlversion.main.infirmMain.quiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.su.btmxmlversion.databinding.QuizRecyclerViewItemBinding
import team.su.btmxmlversion.main.infirmMain.quiz.models.QuizThumbnailData

class QuizRvAdapter(private val list: ArrayList<QuizThumbnailData>): RecyclerView.Adapter<QuizRvAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: QuizRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: QuizThumbnailData) {
            binding.thumbnailImage.setImageResource(item.quizThumbnailImage)
            binding.thumbnailText.text = item.quizName
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