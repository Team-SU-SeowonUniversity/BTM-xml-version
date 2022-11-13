package team.su.btmxmlversion.ui.main.infirmMain.quiz.perception.blinking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import team.su.btmxmlversion.databinding.MultipleChoiceQuizRecyclerViewItemBinding
import team.su.btmxmlversion.ui.main.infirmMain.quiz.perception.blinking.BlinkingActivity
import team.su.btmxmlversion.ui.main.infirmMain.quiz.perception.blinking.models.BlinkingExamples

class BlinkingAnswerRvAdapter(
    private val examples: List<BlinkingExamples>,
    private val answer: String,
    private val timeOut: () -> Unit,
    private val onPassChanged: (Boolean?) -> Unit,
    private val isDiagnosis: Boolean,
): RecyclerView.Adapter<BlinkingAnswerRvAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MultipleChoiceQuizRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        internal fun practiceBind(item: BlinkingExamples) {
            binding.exampleText.text = item.example
            binding.exampleText.textSize = 25f

            binding.example.setOnClickListener {
                onPassChanged(null)
                if (answer == item.example) {
                    timeOut()
                    Toast.makeText(binding.root.context, "정답입니다!", Toast.LENGTH_SHORT).show()
                    (binding.root.context as BlinkingActivity).finish()
                } else {
                    timeOut()
                    Toast.makeText(binding.root.context, "틀렸습니다!", Toast.LENGTH_SHORT).show()
                    (binding.root.context as BlinkingActivity).finish()
                }
            }
        }

        internal fun diagnosisBind(item: BlinkingExamples) {
            binding.exampleText.text = item.example
            binding.exampleText.textSize = 25f

            binding.example.setOnClickListener {
                if (answer == item.example) {
                    timeOut()
                    Toast.makeText(binding.root.context, "정답입니다!", Toast.LENGTH_SHORT).show()
                    onPassChanged(true)
                    (binding.root.context as BlinkingActivity).finish()
                } else {
                    timeOut()
                    Toast.makeText(binding.root.context, "틀렸습니다!", Toast.LENGTH_SHORT).show()
                    onPassChanged(false)
                    (binding.root.context as BlinkingActivity).finish()
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