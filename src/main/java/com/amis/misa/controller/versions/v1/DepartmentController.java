package com.amis.misa.controller.versions.v1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amis.misa.annotation.RestApiV1;
import com.amis.misa.constants.UrlConstant;
import com.amis.misa.converter.ObjectConvert;
import com.amis.misa.dto.DepartmentDto;
import com.amis.misa.dto.JsonForErrorMessage;
import com.amis.misa.entities.Department;
import com.amis.misa.services.IDepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestApiV1
@Tag(name = "Department")
public class DepartmentController {
	@Autowired
	IDepartmentService departmentService;

	
	
	 /**
		 * 
	     * @author Nguyễn Văn Đông
	     * created_date: 06/06/2023
	     * Lấy ra danh sách List<Department> phòng ban
	     *
	     * @return Trả về  danh sách deparrtments
	     * @throws khi thực hiện không thành công và trả về message gồm: devMsg,userMsg
	     * }
	     */
		@GetMapping(UrlConstant.GET_DEPARTMENTS_DATA)
		@Operation(description = "Lấy  danh sách phòng ban",summary = "Lấy  danh sách phòng ban")
		public ResponseEntity<?> getAllDepartments(){
			try {
				
				return ResponseEntity.ok(departmentService.findAll());
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				JsonForErrorMessage json=new JsonForErrorMessage(e.getMessage(), "Có lỗi xảy ra vui lòng liên hệ Đông để biết thêm chi tiết");
				return ResponseEntity.badRequest().body(json);
			}
		}
}
