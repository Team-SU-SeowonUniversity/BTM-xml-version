package team.su.btmxmlversion.ui.main.infirmMain.dementiaDiagnosis

import team.su.btmxmlversion.base.BaseCallback
import team.su.btmxmlversion.models.DiagnosisHistoryResponse

interface DementiaDiagnosisCallback: BaseCallback {

    fun getDiagnosisHistory(response: DiagnosisHistoryResponse)

}