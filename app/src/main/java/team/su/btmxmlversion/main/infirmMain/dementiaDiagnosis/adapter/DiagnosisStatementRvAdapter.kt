package team.su.btmxmlversion.main.infirmMain.dementiaDiagnosis.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.su.btmxmlversion.databinding.DiagnosisStatementRecyclerViewItemBinding

class DiagnosisStatementRvAdapter: RecyclerView.Adapter<DiagnosisStatementRvAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: DiagnosisStatementRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DiagnosisStatementRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 15
    }

}