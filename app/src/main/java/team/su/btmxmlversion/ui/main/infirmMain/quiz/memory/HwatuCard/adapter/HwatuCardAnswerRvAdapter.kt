package team.su.btmxmlversion.ui.main.infirmMain.quiz.memory.HwatuCard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import team.su.btmxmlversion.databinding.MultipleChoiceQuizRecyclerViewItemBinding
import team.su.btmxmlversion.ui.main.infirmMain.quiz.memory.HwatuCard.HwatuCardActivity
import team.su.btmxmlversion.ui.main.infirmMain.quiz.memory.HwatuCard.models.HwatuExample

class HwatuCardAnswerRvAdapter(
    private val examples: List<HwatuExample>,
    private val answer: Int,
    private val timeOut: () -> Unit,
    private val onPassChanged: (Boolean?) -> Unit,
    private val isDiagnosis: Boolean,
): RecyclerView.Adapter<HwatuCardAnswerRvAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MultipleChoiceQuizRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        internal fun practiceBind(item: HwatuExample) {
            binding.example.apply {
                layoutParams.width = 350
                layoutParams.height = 500
                strokeWidth = 0
            }
            binding.exampleImage.setImageResource(item.example)

            binding.example.setOnClickListener {
                onPassChanged(null)
                if (answer == item.example) {
                    timeOut()
                    Toast.makeText(binding.root.context, "정답입니다!", Toast.LENGTH_SHORT).show()
                    (binding.root.context as HwatuCardActivity).finish()
                } else {
                    timeOut()
                    Toast.makeText(binding.root.context, "틀렸습니다!", Toast.LENGTH_SHORT).show()
                    (binding.root.context as HwatuCardActivity).finish()
                }
            }
        }

        internal fun diagnosisBind(item: HwatuExample) {
            binding.example.apply {
                layoutParams.width = 350
                layoutParams.height = 500
                strokeWidth = 0
            }
            binding.exampleImage.setImageResource(item.example)

            binding.example.setOnClickListener {
                if (answer == item.example) {
                    timeOut()
                    Toast.makeText(binding.root.context, "정답입니다!", Toast.LENGTH_SHORT).show()
                    onPassChanged(true)
                    (binding.root.context as HwatuCardActivity).finish()
                } else {
                    timeOut()
                    Toast.makeText(binding.root.context, "틀렸습니다!", Toast.LENGTH_SHORT).show()
                    onPassChanged(false)
                    (binding.root.context as HwatuCardActivity).finish()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MultipleChoiceQuizRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(isDiagnosis) {
            true -> holder.diagnosisBind(examples[position])
            false -> holder.practiceBind(examples[position])
        }
    }

    override fun getItemCount(): Int {
        return examples.size
    }

}