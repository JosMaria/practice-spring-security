package org.genesiscode.practicesecurity.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS = List.of(
            new Student(1, "Jose Maria"),
            new Student(2, "Sara"),
            new Student(3, "Darleen"),
            new Student(4, "Liz")
    );

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer id) {
        return STUDENTS.stream()
                .filter(student -> Objects.equals(student.getId(), id))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student " + id + " does not exists"));
    }
}
