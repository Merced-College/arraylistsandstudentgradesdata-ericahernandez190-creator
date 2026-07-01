/*
 * Name: Erica Hernandez
 * Date: 06/30/2026
 * Program: Course Grades Analyzer - reads CSV grade totals and analyzes A percentages.
 */
import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Main {
    static ArrayList<Course> courses = new ArrayList<Course>();
    public static void main(String[] args) {
        readCoursesFromCSV("courseAndGradesData.csv");

        // Print the courses in a formatted table
        System.out.printf("%-10s %7s %7s %7s %7s %7s %8s %8s%n",
                "Course", "A", "B", "C", "D", "F", "Total", "A%");
        for (Course course : courses) {
            System.out.println(course);
        }

        Course bestCourse = courses.get(0);
        for (Course course : courses) {
            if (course.getAPercentage() > bestCourse.getAPercentage()) {
                bestCourse = course;
            }
        }

        System.out.println();
        System.out.println("Best course:");
        System.out.println("Course name: " + bestCourse.getCourseName());
        System.out.println("A%: " + String.format("%.2f", bestCourse.getAPercentage()));
        System.out.println("Total: " + bestCourse.getTotalGrades());
        System.out.println("A-F counts: " + bestCourse.getCourseGrades());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a course name (example: ACTG-31): ");
        String searchName = scanner.nextLine();

        boolean found = false;
        for (Course course : courses) {
            if (course.getCourseName().equalsIgnoreCase(searchName)) {
                found = true;
                System.out.println();
                System.out.println("Course found:");
                System.out.println("Course name: " + course.getCourseName());
                System.out.println("A%: " + String.format("%.2f", course.getAPercentage()));
                System.out.println("Total: " + course.getTotalGrades());
                System.out.println("A-F counts: " + course.getCourseGrades());
                break;
            }
        }

        if (!found) {
            System.out.println();
            System.out.println("Course not found. Please enter a valid course name.");
        }

        scanner.close();
    }

    static void readCoursesFromCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                //skip first 2 lines
                if (lineNumber <= 2) {
                    continue;
                }

                String[] parts = line.split(","); 
                String courseName = parts[0]; 
                ArrayList<Integer> grades = new ArrayList<>(); 
                // parts[1] = A count, parts[2] = B count, ... parts[5] = F count
                for (int i = 1; i < parts.length; i++) {
                    int count = Integer.parseInt(parts[i].trim());
                    grades.add(count);  // index 0 = A, 1 = B, 2 = C, 3 = D, 4 = F
                }//end for loop

                Course course = new Course(courseName, grades);
                courses.add(course);
            }//end while

        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }//end readCoursesFromCSV
 }//end main class
        