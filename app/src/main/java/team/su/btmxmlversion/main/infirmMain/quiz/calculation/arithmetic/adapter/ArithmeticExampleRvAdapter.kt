package team.su.btmxmlversion.main.infirmMain.quiz.calculation.arithmetic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import team.su.btmxmlversion.databinding.MultipleChoiceQuizRecyclerViewItemBinding
import team.su.btmxmlversion.main.infirmMain.quiz.calculation.arithmetic.ArithmeticActivity
import team.su.btmxmlversion.main.infirmMain.quiz.calculation.arithmetic.model.ArithmeticExampleModel
import team.su.btmxmlversion.main.infirmMain.quiz.perception.shadowing.ShadowingActivity
import kotlin.concurrent.thread

class ArithmeticExampleRvAdapter(private val list: List<ArithmeticExampleModel>, private val answer: Int):
    RecyclerView.Adapter<ArithmeticExampleRvAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MultipleChoiceQuizRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ArithmeticExampleModel) {
            binding.exampleText.text = item.example.toString()

            binding.example.setOnClickListener {
                if(item.example == answer) {
                    Toast.makeText(binding.root.context, "정답입니다!", Toast.LENGTH_SHORT).show()
                    (binding.root.context as ArithmeticActivity).finish()
                } else {
                    Toast.makeText(binding.root.context, "틀렸습니다!", Toast.LENGTH_SHORT).show()
                    (binding.root.context as ArithmeticActivity).finish()
                }
            }
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