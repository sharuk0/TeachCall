package dbp.techcall.course;
import dbp.techcall.category.Category;
import dbp.techcall.professor.professor.infrastructure.models.Professor;
import dbp.techcall.professor.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dbp.techcall.course.CourseService;
import dbp.techcall.course.CourseRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> courses() {
        List<CourseDTO> courses= courseService.getAll();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/dashboard/student")
    public ResponseEntity<List<CourseDTO>> coursesStudent(){
        List<CourseDTO> courses = courseService.getLimitSixCourses();
        return ResponseEntity.ok(courses);

    }

    @GetMapping("/professor/{id}")
    public ResponseEntity<List<CourseDTO>> coursesByProfessor(@PathVariable Long id){
        Professor professor = professorRepository.findById(id);
        if (professor == null) {
            return ResponseEntity.status(404).body(null);
        }

        List<Course> courses = professor.getCourses();
        List<CourseDTO> coursesDto = courses.stream()
                .map(courseService::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.status(200).body(coursesDto);

    }

//    @PostMapping
//    public ResponseEntity<CourseDTO> create(@RequestBody CourseDTO courseDTO) {
//        Course course = courseService.convertToEntity(courseDTO);
//        Course courseCreated = courseRepository.save(course);
//
//        return ResponseEntity.status(201).body(courseService.convertToDTO(courseCreated));
//    }



}