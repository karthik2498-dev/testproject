package com.test.pro.ResponseVo;

import com.test.pro.model.Profiles;

public class DepartmentDTO {
    private Long departmentId;
    private String departmentName;

	public DepartmentDTO(Long departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}

