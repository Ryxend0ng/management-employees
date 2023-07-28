package com.amis.misa.repositories;



import org.springframework.stereotype.Repository;

import com.amis.misa.entities.app.Department;
@Repository(value = "departRepo")
public interface DepartmentRepository extends BaseRepository<Department, Integer>{

}
