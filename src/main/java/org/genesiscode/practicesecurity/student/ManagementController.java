package org.genesiscode.practicesecurity.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class ManagementController {

    private static final List<Student> STUDENTS = List.of(
            new Student(1, "Jose Maria"),
            new Student(2, "Sara"),
            new Student(3, "Darleen"),
            new Student(4, "Liz")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_TRAINER', 'ROLE_ADMIN')")
    public List<Student> getAllStudents() {
        System.out.println("GET: getAllStudents");
        return STUDENTS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student) {
        System.out.println("POST: registerNewStudent");
        System.out.println(student);
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Integer id) {
        System.out.println("DELETE: deleteStudent");
        System.out.println(id);
    }

    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Integer id, @RequestBody Student student) {
        System.out.println("PUT: updateStudent");
        System.out.printf("%s %s\n", id, student);
    }
}
