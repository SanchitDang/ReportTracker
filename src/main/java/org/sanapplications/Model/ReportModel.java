package org.sanapplications.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "reports")
public class ReportModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String time;
    private String report;
    private String employee_id;

    public ReportModel() {
    }

    public ReportModel(Long id, String time, String report, String employee_id) {
        this.id = id;
        this.time = time;
        this.report = report;
        this.employee_id = employee_id;
    }

    public ReportModel(String time, String report, String employee_id) {
        this.time = time;
        this.report = report;
        this.employee_id = employee_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }
}
