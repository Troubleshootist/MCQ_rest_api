package ru.sidorov.mcq.utils.mapping;

import org.springframework.stereotype.Service;
import ru.sidorov.mcq.DTO.CourseDto;
import ru.sidorov.mcq.model.Course;


@Service
public class CourseMapper {

    public CourseDto mapToCourseDto(Course entity) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(entity.getId());
        courseDto.setCourseNumber(entity.getCourseNumber());
        return courseDto;
    }
}
