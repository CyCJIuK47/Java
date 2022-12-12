package DumpStatistics;


public class FineReport {
    private Integer year;
    private String name;
    private String surname;
    private String type;
    private Double amount;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FineReport{" +
                "date=" + year +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }
}
