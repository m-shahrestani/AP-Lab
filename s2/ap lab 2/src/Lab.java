/**
 * The Lab class represents a lab in a lab administration system.
 * It holds the lab details relevant in our context.
 *
 * @author Mohammadreza
 * @version 0.0
 */
public class Lab {
    // the lab’s students
    private Student[] students;
    // the lab’s average
    private int avg;
    // the lab’s day
    private String day;
    // the lab’s capacity
    private int capacity;
    // the lab’s current size
    private int currentSize;

    /**
     * Create a new lab with a given capacity and students and day.
     *
     * @param cap capacity of lab
     * @param d lab day
     */
    public Lab(int cap, String d) {
        capacity = cap;
        students = new Student[capacity];
        day = d;
    }

    /**
     * Enroll student to the lab.
     * @param std set students of a lab
     */
    public void enrollStudent(Student std) {
        if (currentSize < capacity) {
            students[currentSize] = std;
            currentSize++;
        }
        else {
            System.out.println("Lab is full!!!");
        }
    }

    /**
     * Print the student’s first name and ID number and average of lab to the output terminal.
     */
    public void print() {
        for (int i = 0; i <currentSize; i++) {
            System.out.println("std fname: " + students[i].getFirstName()
                    + " std id:" + students[i].getId()
                    + " std grade:"+ students[i].getGrade());
        }
        System.out.println("Lab AVG:" + avg);
    }

    /**
     * get the students of a lab
     * @return students field
     */
    public Student[] getStudents() {
        return students;
    }

    /**
     * @param students set students of a lab
     */
    public void setStudents(Student[] students)
    {
        this.students = students;
    }

    /**
     * get the average of a lab
     * @return avg field
     */
    public int getAvg() {
        return avg;
    }

    /**
     * Calculate average of lab.
     */
    public void calculateAvg()
    {
        int m = 0;
        for (int i = 0; i < currentSize; i++) {
                   m += students[i].getGrade();
        }
        avg = m/currentSize;
    }

    /**
     * get the day of a lab
     * @return  day field
     */
    public String getDay() {
        return day;
    }

    /**
     * @param day set day of a lab
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * get the capacity of a lab
     * @return capacity field
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity set capacity of a lab
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}