package com.example.attendo2.models;

public class allsubjectsatt {
    private String subjects;
    private String attendanceall;
   private int photo;

    public allsubjectsatt(){}

    public allsubjectsatt(String subjects, String attendanceall, int photo) {
        this.subjects = subjects;
        this.attendanceall = attendanceall;
        this.photo = photo;
    }

    public String getSubjects() {
        return subjects;
    }

    public String getAttendanceall() {
        return attendanceall;
    }

   public int getPhoto() {
        return photo;
    }
}
