package team.su.btmxmlversion.ui.main.infirmMain.quiz.calculation.arithmetic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import team.su.btmxmlversion.databinding.MultipleChoiceQuizRecyclerViewItemBinding
import team.su.btmxmlversion.ui.main.infirmMain.quiz.calculation.arithmetic.ArithmeticActivity

class ArithmeticExampleRvAdapter(
    private val examples: List<Int>,
    private val answer: Int,
    private val onTimeout: () -> Unit,
    private val onPassChanged: (Boolean?) -> Unit,
    private val isDiagnosis: Boolean,
) : RecyclerView.Adapter<ArithmeticExampleRvAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MultipleChoiceQuizRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal fun practiceBind(example: Int) {
            binding.exampleText.text = example.toString()
            binding.example.setOnClickListener {
                onPassChanged(null)
                if (example == answer) {
                    onTimeout()
                    Toast.makeText(binding.root.context, "정답입니다!", Toast.LENGTH_SHORT).show()
                    (binding.root.context as ArithmeticActivity).finish()
                } else {
                    onTimeout()
                    Toast.makeText(binding.root.context, "틀렸습니다!", Toast.LENGTH_SHORT).show()
                    (binding.root.context as ArithmeticActivity).finish()
                }
            }
        }

        internal fun diagnosisBind(example: Int) {
            binding.exampleText.text = example.toString()
            binding.example.setOnClickListener {
                if (example == answer) {
                    onTimeout()
                    Toast.makeText(binding.root.context, "정답입니다!", Toast.LENGTH_SHORT).show()
                    onPassChanged(true)
                    (binding.root.context as ArithmeticActivity).finish()
                } else {
                    onTimeout()
                    Toast.makeText(binding.root.context, "틀렸습니다!", Toast.LENGTH_SHORT).show()
                    onPassChanged(false)
                    (binding.root.context as ArithmeticActivity).finish()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MultipleChoiceQuizRecyclerViewItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
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