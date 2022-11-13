package team.su.btmxmlversion.ui.main.infirmMain.dementiaDiagnosis.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.su.btmxmlversion.databinding.DiagnosisStatementRecyclerViewItemBinding
import team.su.btmxmlversion.ui.main.infirmMain.dementiaDiagnosis.data.DiagnosisStatement

class DiagnosisStatementRvAdapter(
    private val result: List<DiagnosisStatement>
): RecyclerView.Adapter<DiagnosisStatementRvAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: DiagnosisStatementRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: DiagnosisStatement) {
            binding.statementNumber.text = item.resultNumber.toString()
            binding.diagnosisTime.text = "진단 시간 : ${item.diagnosisTime}"
            binding.diagnosisResult.text =
                "진단 결과 : ${String(Character.toChars(0x2B55))} ${item.pass} / ${String(Character.toChars(0x274C))} ${item.fail}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DiagnosisStatementRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(result[position])
    }

    override fun getItemCount(): Int {
        return result.size
    }

}