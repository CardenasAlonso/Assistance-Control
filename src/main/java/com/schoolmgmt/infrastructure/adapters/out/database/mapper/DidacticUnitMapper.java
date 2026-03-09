package com.schoolmgmt.infrastructure.adapters.out.database.mapper;
import com.schoolmgmt.domain.model.DidacticUnit;
import com.schoolmgmt.infrastructure.adapters.out.database.entity.DidacticUnitEntity;

public class DidacticUnitMapper {
    private DidacticUnitMapper() {}

    public static DidacticUnit toDomain(DidacticUnitEntity e) {
        return DidacticUnit.builder().id(e.getId())
            .courseAssignmentId(e.getCourseAssignmentId()).unitNumber(e.getUnitNumber())
            .title(e.getTitle()).description(e.getDescription())
            .startDate(e.getStartDate()).endDate(e.getEndDate()).isActive(e.getIsActive()).build();
    }

    public static DidacticUnitEntity toEntity(DidacticUnit d) {
        return DidacticUnitEntity.builder().id(d.getId())
            .courseAssignmentId(d.getCourseAssignmentId()).unitNumber(d.getUnitNumber())
            .title(d.getTitle()).description(d.getDescription())
            .startDate(d.getStartDate()).endDate(d.getEndDate()).isActive(d.getIsActive()).build();
    }
}
