package team.su.btmxmlversion.main.infirmMain.quiz.analysis.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import team.su.btmxmlversion.databinding.MultipleChoiceQuizRecyclerViewItemBinding
import team.su.btmxmlversion.main.infirmMain.quiz.analysis.weather.WeatherActivity
import team.su.btmxmlversion.main.infirmMain.quiz.analysis.weather.models.WeatherExamples

class WeatherExampleRvAdapter(
    private val exampleImages: List<WeatherExamples>,
    private val answerImage: Int,
    private val timeOut: () -> Unit,
    private val onPassChanged: (Boolean?) -> Unit,
    private val isDiagnosis: Boolean,
): RecyclerView.Adapter<WeatherExampleRvAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MultipleChoiceQuizRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        internal fun practiceBind(item: WeatherExamples) {
            binding.exampleImage.setImageResource(item.example)

            binding.example.setOnClickListener {
                onPassChanged(null)
                if (answerImage == item.example) {
                    timeOut()
                    Toast.makeText(binding.root.context, "정답입니다!", Toast.LENGTH_SHORT).show()
                    (binding.root.context as WeatherActivity).finish()
                } else {
                    timeOut()
                    Toast.makeText(binding.root.context, "틀렸습니다!", Toast.LENGTH_SHORT).show()
                    (binding.root.context as WeatherActivity).finish()
                }
            }
        }

        internal fun diagnosisBind(item: WeatherExamples) {
            binding.exampleImage.setImageResource(item.example)

            binding.example.setOnClickListener {
                if (answerImage == item.example) {
                    timeOut()
                    Toast.makeText(binding.root.context, "정답입니다!", Toast.LENGTH_SHORT).show()
                    onPassChanged(true)
                    (binding.root.context as WeatherActivity).finish()
                } else {
                    timeOut()
                    Toast.makeText(binding.root.context, "틀렸습니다!", Toast.LENGTH_SHORT).show()
                    onPassChanged(false)
                    (binding.root.context as WeatherActivity).finish()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MultipleChoiceQuizRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(isDiagnosis) {
            true -> holder.diagnosisBind(exampleImages[position])
            false -> holder.practiceBind(exampleImages[position])
        }
    }

    override fun getItemCount(): Int {
        return exampleImages.size
    }

}