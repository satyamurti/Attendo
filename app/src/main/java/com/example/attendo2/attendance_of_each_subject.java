package com.example.attendo2;

public class attendance_of_each_subject implements  Comparable<attendance_of_each_subject>{
    String dates ;
    String presenty;

    public   attendance_of_each_subject(){}

    public   attendance_of_each_subject(String dates, String presenty){

        this.dates = dates;
        this.presenty = presenty;

    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getPresenty() {
        return presenty;
    }

    public void setPresenty(String presenty) {
        this.presenty = presenty;
    }

    @Override
    public int compareTo(attendance_of_each_subject u) {
        if (getDates().substring(3,5) == null || u.getDates().substring(3,5) == null) {
            return 0;
        }
        return getDates().substring(3,5).compareTo(u.getDates().substring(3,5));

    }

}
