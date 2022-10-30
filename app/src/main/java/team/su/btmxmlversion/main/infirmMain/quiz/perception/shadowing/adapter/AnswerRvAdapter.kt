package team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import team.su.btmxmlversion.databinding.MultipleChoiceQuizRecyclerViewItemBinding
import team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.ShadowingActivity
import team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.models.ExampleImages

class AnswerRvAdapter(
    private val exampleImages: List<ExampleImages>,
    private val answerImage: Int,
    private val timeOut: () -> Unit
): RecyclerView.Adapter<AnswerRvAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MultipleChoiceQuizRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ExampleImages) {
            binding.exampleImage.setImageResource(item.exampleImage)

            binding.example.setOnClickListener {
                if (answerImage == item.exampleImage) {
                    timeOut()
                    Toast.makeText(binding.root.context, "정답입니다!", Toast.LENGTH_SHORT).show()
                    (binding.root.context as ShadowingActivity).finish()
                } else {
                    timeOut()
                    Toast.makeText(binding.root.context, "틀렸습니다!", Toast.LENGTH_SHORT).show()
                    (binding.root.context as ShadowingActivity).finish()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MultipleChoiceQuizRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(exampleImages[position])
    }

    override fun getItemCount(): Int {
        return exampleImages.size
    }

}