



class Student{
    int roll;
    String name;
    int age;
    String grade;
    Student next;
    Student(int roll, String name, int age, String grade) {
        this.roll = roll;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}
class StudentList{
    Student head;
    void addAtBegining(int roll, String name, int age,String grade){
        Student newStudent = new Student(roll,name,age,grade);
        newStudent.next = head;
        head = newStudent;
    }

    void addAtend(int roll, String name, int age, String grade){
        Student newStudent = new Student(roll, name, age, grade);
        if (head== null){
            head = newStudent;
        }else{
            Student temp = head;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = newStudent;
        }
    }
    void addAtPosition(int position, int roll, String name, int age,String grade){
        if (position == 0){
            addAtBegining(roll, name, age, grade);
            return;
        }
        Student newStudent = new Student(roll, name, age, grade);
        Student temp = head;
        int count = 0 ;
        while (temp != null && count < position-1){
            temp = temp.next;
            count++;
        }
        if(temp == null){
            System.out.println("out of range");
        }else{
            newStudent.next = temp.next;
            temp.next = newStudent;
        }
    }

    void deleteByRoll(int roll){
        Student temp = head,prev = null;
        while (temp != null && temp.roll != roll){
            prev = temp;
            temp = temp.next;
        }
        if(temp == null){
            System.out.println("No student found with Roll No" + roll);
        }
        else{
            if (prev == null) {
                head = temp.next;
            } else {
                prev.next = temp.next;
            }
            System.out.println("Student with Roll No " + roll + " deleted.");
        }
    }
    void searchByRoll(int roll) {
        Student temp = head;
        while (temp != null) {
            if (temp.roll == roll) {
                System.out.println("Student Found - Roll: " + temp.roll + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("No student found with Roll No " + roll);
    }
    void updateGrade(int roll, String newGrade) {
        Student temp = head;
        while (temp != null) {
            if (temp.roll == roll) {
                temp.grade = newGrade;
                System.out.println("Grade updated for Roll No " + roll + ". New Grade: " + newGrade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("No student found with Roll No " + roll);
    }

    void displayAll() {
        Student temp = head;
        if (temp == null) {
            System.out.println("No student records available.");
            return;
        }
        System.out.println("Student Records:");
        while (temp != null) {
            System.out.println("Roll: " + temp.roll + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
            temp = temp.next;
        }
    }
}
public class StudentRecordManagement {
    public static void main(String[] args) {
        StudentList sl = new StudentList();
        sl.addAtend(1, "Alice", 20, "A");
        sl.addAtend(2, "Bob", 21, "B");
        sl.addAtBegining(0, "Charlie", 19, "A+");
        sl.addAtPosition(2, 3, "Daisy", 22, "C");
        sl.displayAll();
        sl.searchByRoll(2);
        sl.updateGrade(3, "B+");
        sl.deleteByRoll(1);
        sl.displayAll();
    }
}