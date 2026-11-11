package Entity;

import java.time.LocalDate;

import Entity.enums.CodeResultat;

public class CompteRenduMedical {
    private String ID_CompteRenduMedical;
    private String ID_RDV;
    private LocalDate Date;
    private String RefOrdonnanceTextuelle,TranscriptionDiagnosticTextuel;
    private CodeResultat codeResultat;
    public CompteRenduMedical(String iD_CompteRenduMedical, String iD_RDV, LocalDate date,
            String refOrdonnanceTextuelle, String transcriptionDiagnosticTextuel, CodeResultat codeResultat) {
        ID_CompteRenduMedical = iD_CompteRenduMedical;
        ID_RDV = iD_RDV;
        Date = date;
        RefOrdonnanceTextuelle = refOrdonnanceTextuelle;
        TranscriptionDiagnosticTextuel = transcriptionDiagnosticTextuel;
        this.codeResultat = codeResultat;
    }
    public String getID_CompteRenduMedical() {
        return ID_CompteRenduMedical;
    }
    public void setID_CompteRenduMedical(String iD_CompteRenduMedical) {
        ID_CompteRenduMedical = iD_CompteRenduMedical;
    }
    public String getID_RDV() {
        return ID_RDV;
    }
    public void setID_RDV(String iD_RDV) {
        ID_RDV = iD_RDV;
    }
    public LocalDate getDate() {
        return Date;
    }
    public void setDate(LocalDate date) {
        Date = date;
    }
    public String getRefOrdonnanceTextuelle() {
        return RefOrdonnanceTextuelle;
    }
    public void setRefOrdonnanceTextuelle(String refOrdonnanceTextuelle) {
        RefOrdonnanceTextuelle = refOrdonnanceTextuelle;
    }
    public String getTranscriptionDiagnosticTextuel() {
        return TranscriptionDiagnosticTextuel;
    }
    public void setTranscriptionDiagnosticTextuel(String transcriptionDiagnosticTextuel) {
        TranscriptionDiagnosticTextuel = transcriptionDiagnosticTextuel;
    }
    public CodeResultat getCodeResultat() {
        return codeResultat;
    }
    public void setCodeResultat(CodeResultat codeResultat) {
        this.codeResultat = codeResultat;
    }   
    

}
