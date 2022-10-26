package team.su.btmxmlversion.main.infirmMain.multipleChoiceQuiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.su.btmxmlversion.databinding.MultipleChoiceQuizRecyclerViewItemBinding
import team.su.btmxmlversion.main.infirmMain.multipleChoiceQuiz.models.ExampleImageModel

class AnswerRvAdapter(private val list: List<ExampleImageModel>, private val answer: Int): RecyclerView.Adapter<AnswerRvAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MultipleChoiceQuizRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ExampleImageModel) {
            binding.exampleImage.setImageResource(item.image)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MultipleChoiceQuizRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }

}