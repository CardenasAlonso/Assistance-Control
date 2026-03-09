package com.schoolmgmt.infrastructure.adapters.out.database.mapper;
import com.schoolmgmt.domain.model.SchoolYear;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.SchoolYearEntity;

public class SchoolYearMapper {
    private SchoolYearMapper() {}

    public static SchoolYear toDomain(SchoolYearEntity e) {
        return SchoolYear.builder().id(e.getId())
            .academicLevelId(e.getAcademicLevelId()).name(e.getName())
            .gradeNumber(e.getGradeNumber()).isActive(e.getIsActive()).build();
    }

    public static SchoolYearEntity toEntity(SchoolYear s) {
        return SchoolYearEntity.builder().id(s.getId())
            .academicLevelId(s.getAcademicLevelId()).name(s.getName())
            .gradeNumber(s.getGradeNumber()).isActive(s.getIsActive()).build();
    }
}
