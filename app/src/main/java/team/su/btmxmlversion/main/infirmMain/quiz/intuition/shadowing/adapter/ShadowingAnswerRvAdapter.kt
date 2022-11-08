package team.su.btmxmlversion.main.infirmMain.quiz.intuition.shadowing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import team.su.btmxmlversion.databinding.MultipleChoiceQuizRecyclerViewItemBinding
import team.su.btmxmlversion.main.infirmMain.quiz.intuition.shadowing.ShadowingActivity
import team.su.btmxmlversion.main.infirmMain.quiz.intuition.shadowing.models.ShadowingExamples

class ShadowingAnswerRvAdapter(
    private val examples: List<ShadowingExamples>,
    private val answer: Int,
    private val timeOut: () -> Unit,
    private val onPassChanged: (Boolean?) -> Unit,
    private val isDiagnosis: Boolean,
): RecyclerView.Adapter<ShadowingAnswerRvAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MultipleChoiceQuizRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        internal fun exampleBind(item: ShadowingExamples) {
            binding.exampleImage.setImageResource(item.example)
            binding.example.setOnClickListener {
                onPassChanged(null)
                if (answer == item.example) {
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

        internal fun diagnosisBind(item: ShadowingExamples) {
            binding.exampleImage.setImageResource(item.example)
            binding.example.setOnClickListener {
                if (answer == item.example) {
                    timeOut()
                    Toast.makeText(binding.root.context, "정답입니다!", Toast.LENGTH_SHORT).show()
                    onPassChanged(true)
                    (binding.root.context as ShadowingActivity).finish()
                } else {
                    timeOut()
                    Toast.makeText(binding.root.context, "틀렸습니다!", Toast.LENGTH_SHORT).show()
                    onPassChanged(false)
                    (binding.root.context as ShadowingActivity).finish()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MultipleChoiceQuizRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (isDiagnosis) {
            true -> holder.diagnosisBind(examples[position])
            false -> holder.exampleBind(examples[position])
        }
    }

    override fun getItemCount(): Int {
        return examples.size
    }

}