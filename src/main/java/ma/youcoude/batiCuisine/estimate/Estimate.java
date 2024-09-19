package ma.youcoude.batiCuisine.estimate;

import ma.youcoude.batiCuisine.project.Project;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Estimate {
    private String estimateId;
    private double overallEstimatedPrice;
    private LocalDateTime issuedAt;
    private LocalDate validityDate;
    private boolean isAccepted;
    private Project project;

    public Estimate() {}

    public String getEstimateId() {
        return estimateId;
    }
    public void setEstimateId(String estimateId) {
        this.estimateId = estimateId;
    }
    public double getOverallEstimatedPrice() {
        return overallEstimatedPrice;
    }
    public void setOverallEstimatedPrice(double overallEstimatedPrice) {
        this.overallEstimatedPrice = overallEstimatedPrice;
    }
    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }
    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }
    public LocalDate getValidityDate() {
        return validityDate;
    }
    public void setValidityDate(LocalDate validityDate) {
        this.validityDate = validityDate;
    }
    public boolean isAccepted() {
        return isAccepted;
    }
    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
}
