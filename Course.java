/*
 * Name: Erica Hernandez
 * Date: 06/30/2026
 * Program: Course Grades Analyzer - reads CSV grade totals and analyzes A percentages.
 */
import java.util.ArrayList;

public class Course {

    private String courseName;
    private ArrayList<Integer> courseGrades;

    public Course(String courseName, ArrayList<Integer> courseGrades) {
        this.courseName = courseName;
        this.courseGrades = courseGrades;
    }

    //default constructor
    public Course() {
        this.courseName = "Unknown Course";
        this.courseGrades = new ArrayList<Integer>();
    }

    //all setters and getters
    public void setCourseName(String courseName) {  
        this.courseName = courseName;
    }   

    public String getCourseName() {
        return courseName;
    }

    public ArrayList<Integer> getCourseGrades() {
        return courseGrades;
    }

     // set courseGrades
    public void setCourseGrades(ArrayList<Integer> courseGrades) {
        this.courseGrades = courseGrades;
    }
   
    
    //get sum of { A + B + C + D + F } grades
    public int getTotalGrades() {
        int total = 0;
        for (int grade : courseGrades) {
            total += grade;  
            }
     return total;
    }

    //get percentage of A grades
    public double getAPercentage() {
        int totalGrades = getTotalGrades();
        if (totalGrades == 0) {
            return 0.0;  
        }
        return (double) courseGrades.get(0) / totalGrades * 100;
    }

    //toString method
    @Override
    public String toString() {
        return String.format("%-10s %7d %7d %7d %7d %7d %8d %7.2f",
                courseName,
                courseGrades.get(0),
                courseGrades.get(1),
                courseGrades.get(2),
                courseGrades.get(3),
                courseGrades.get(4),
                getTotalGrades(),
                getAPercentage());
        }
    
}//end Course class


