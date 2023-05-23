package Persoana;

import java.time.LocalDate;

public class Angajat extends Persoana implements Comparable<Angajat> {
    private Integer Salariu;
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

    public void setSalariu(Integer salariu) {
        Salariu = salariu;
    }

    public void setUnitateSalariu(String unitateSalariu) {
        UnitateSalariu = unitateSalariu;
    }


    @Override
    public String toString() {
        String s = "Angajat {" +
                "Salary=" + Salariu +
                ", SalaryUnit='" + UnitateSalariu + '\'' +
                ", Job='" + Job + '\'' +
                ", Nume='" + Nume + '\'' +
                ", Prenume='" + Prenume + '\'' +
                ", CNP='" + CNP + '\'' +
                '}';
        if(StartDate != null)
            s += ", StartDate=" + StartDate;
        if(EndDate != null)
            s += ", EndDate=" + EndDate;
        return s;
    }

    @Override
    public int compareTo(Angajat o) {
        if(this.Salariu > o.Salariu)
            return 1;
        else if(this.Salariu < o.Salariu)
            return -1;
        else {
            if(this.StartDate.isAfter(o.StartDate))
                return 1;
            else if(this.StartDate.isBefore(o.StartDate))
                return -1;
            else {
                if(this.Nume.compareTo(o.Nume) > 0)
                    return 1;
                else if(this.Nume.compareTo(o.Nume) < 0)
                    return -1;
                else {
                    if(this.Prenume.compareTo(o.Prenume) > 0)
                        return 1;
                    else if(this.Prenume.compareTo(o.Prenume) < 0)
                        return -1;
                    else return 0;
                }
            }
        }
    }

    public Angajat(String nume, String prenume, String cnp, String username, Integer salary, String salaryUnit, String job, LocalDate startDate, LocalDate endDate) {
        super(nume, prenume, cnp, username);
        Salariu = salary;
        UnitateSalariu = salaryUnit;
        Job = job;
        StartDate = startDate;
        EndDate = endDate;
    }
}
