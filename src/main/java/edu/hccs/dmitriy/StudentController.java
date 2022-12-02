package edu.hccs.dmitriy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    /**
     *   URL :  http://localhost:8080/students
      * @return all students
     * @throws IOException
     */
    @GetMapping("/students")
    public List<Student>  students() throws IOException {
        return readData();
    }

    /**
     *   URL :  http://localhost:8080/gpa
      * @return ave gpa
     * @throws IOException
     */
    @GetMapping("/gpa")
    public String  gpa() throws IOException {
        List<Student>  students = readData();
        double sum=0.0;
        		
        for(Student student : students){
        	sum+=student.gpa;
        }
        return ""+(sum/students.size());
    }

    /***
     * http://localhost:8080/student/Algorithms Illuminated - Part 3
     * @param name
     * @return student
     * @throws IOException
     */
    @GetMapping("/student/{name}")
    public Student  student(@PathVariable String name) throws IOException {
        System.out.println("search student by name : "+name);
        List<Student>  students = readData();
        for(Student student : students){
            if( student.firstName.equalsIgnoreCase(name)){
                System.out.println("found student "+student);
                return student;
            }
        }
        System.out.println(" No student found for name "+name);
        return null;
    }

//  http://localhost:8080/student?gpa=Algorithms Illuminated - Part 3&gender=Tim Roughgarden
    @GetMapping("/student")
    public Student  student(@RequestParam String gpa , @RequestParam String gender) throws IOException {
        System.out.println("search student by gpa : "+gpa+" gender : "+gender);
        List<Student>  students = readData();

        for(Student student : students){
        
            if( gpa.equalsIgnoreCase(""+student.gpa) && student.gender.equalsIgnoreCase(gender)){
                System.out.println("found student "+student);
                return student;
            }
        }
        System.out.println(" No student found for gender "+gender);
        return null;
    }


    /***
     * Read the student.txt file
     * @return all the students
     * @throws IOException
     */
    public List<Student> readData() throws IOException {
        FileReader fileReader = new FileReader("student.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        List<Student> studentList = new ArrayList<Student>();

        bufferedReader.readLine(); // read the header
        String line = bufferedReader.readLine(); // read the first line

        while ( line != null){
        	Student student = new Student();
            String[] data = line.split(",");// split each read line by comma
            student.ID= data[0];
            student.firstName=data[1];
            student.gpa=Double.parseDouble(data[2]);
            student.email=data[3];
            student.gender=data[4];

            studentList.add(student);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return studentList;
    }
}