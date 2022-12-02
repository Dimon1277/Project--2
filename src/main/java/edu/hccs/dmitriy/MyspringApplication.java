package edu.hccs.dmitriy;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MyspringApplication {
	static List<Student> students;

	private static void extracted(ConfigurableApplicationContext context) throws IOException {

		StudentController bookController = context.getBean(StudentController.class);
		System.out.println(" books "+ bookController.readData());
	}

	public static void main(String[] args) throws IOException {
	
		ConfigurableApplicationContext context = SpringApplication.run(MyspringApplication.class, args);
		extracted(context);

	}
}
