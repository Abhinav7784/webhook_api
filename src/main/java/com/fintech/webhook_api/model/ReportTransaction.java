package com.fintech.webhook_api.model;

import java.time.LocalDate;

public class ReportTransaction {

    private String naid;
    private String reportType;        // BUSINESS type
    private String reportFormat;      // txt | pdf | csv
    private String reportStatus;      // CREATED | IN_PROGRESS | SUCCESS
    private LocalDate fromDate;
    private LocalDate toDate;

    public ReportTransaction() {}

    public ReportTransaction(
            String naid,
            String reportType,
            String reportFormat,
            String reportStatus,
            LocalDate fromDate,
            LocalDate toDate
    ) {
        this.naid = naid;
        this.reportType = reportType;
        this.reportFormat = reportFormat;
        this.reportStatus = reportStatus;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    // getters & setters


    public String getNaid() {
        return naid;
    }

    public void setNaid(String naid) {
        this.naid = naid;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportFormat() {
        return reportFormat;
    }

    public void setReportFormat(String reportFormat) {
        this.reportFormat = reportFormat;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
