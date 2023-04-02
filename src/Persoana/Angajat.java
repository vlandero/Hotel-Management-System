package Persoana;

import java.time.LocalDate;

public class Angajat extends Persoana {
    private int Salariu;
    private String UnitateSalariu;
    private String Job;
    private LocalDate StartDate;
    private LocalDate EndDate;

    public LocalDate getEndDate() {
        return EndDate;
    }

    public void setEndDate(LocalDate endDate) {
        EndDate = endDate;
    }

    public LocalDate getStartDate() {
        return StartDate;
    }

    public String getJob() {
        return Job;
    }

    public int getSalariu() {
        return Salariu;
    }

    public String getUnitateSalariu() {
        return UnitateSalariu;
    }


    @Override
    public String toString() {
        String s = "Angajat {" +
                "Salary=" + Salariu +
                ", SalaryUnit='" + UnitateSalariu + '\'' +
                ", Job='" + Job + '\'' +
                ", StartDate=" + StartDate +
                ", Nume='" + Nume + '\'' +
                ", Prenume='" + Prenume + '\'' +
                ", CNP='" + CNP + '\'' +
                '}';
        if(EndDate != null)
            s += ", EndDate=" + EndDate;
        return s;
    }

    public Angajat(String nume, String prenume, String cnp, String username, int salary, String salaryUnit, String job, LocalDate startDate) {
        super(nume, prenume, cnp, username);
        Salariu = salary;
        UnitateSalariu = salaryUnit;
        Job = job;
        StartDate = startDate;
        EndDate = null;
    }
}
