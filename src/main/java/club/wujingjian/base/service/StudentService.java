package club.wujingjian.base.service;

import club.wujingjian.base.config.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    public void save(Student student);
}
