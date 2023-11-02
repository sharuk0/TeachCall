package dbp.techcall.student.repository;

import dbp.techcall.student.domain.Student;
import dbp.techcall.user.infrastructure.BaseUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface StudentRepository extends BaseUserRepository<Student> {
    Student findByEmail(String username);
}
