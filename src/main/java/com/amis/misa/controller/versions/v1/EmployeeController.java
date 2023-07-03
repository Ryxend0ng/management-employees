package com.amis.misa.controller.versions.v1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amis.misa.annotation.RestApiV1;
import com.amis.misa.constants.UrlConstant;
import com.amis.misa.constants.UserMessageConstant;
import com.amis.misa.converter.ObjectConvert;
import com.amis.misa.dto.DataInfPageForJson;
import com.amis.misa.dto.EmployeeDto;
import com.amis.misa.dto.JsonForErrorMessage;
import com.amis.misa.entities.Employee;
import com.amis.misa.services.IEmployeeService;
import com.amis.misa.services.impl.CustomUserDetailsService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestApiV1
@Tag(name = "Employee")
public class EmployeeController extends BaseController<Employee, EmployeeDto> {

	@Autowired
	IEmployeeService employeeService;
	private ObjectConvert<Employee, EmployeeDto> employeeConvert = new ObjectConvert<Employee, EmployeeDto>(
			Employee.class, EmployeeDto.class);

	/**
	 * 
	 * @author Nguyễn Văn Đông created_date: 06/06/2023 {@summary} Lấy ra danh sách
	 *         List<Employee> theo page và filter (Mã nhân viên hoặc tên nhân viên)
	 *
	 * @param pageSize       - số lượng record trên 1 trang
	 * @param pageNumber     - số page hiện tại
	 * @param employeeFilter - giá trị của employeeFilter có thể là mã nhân viên
	 *                       hoặc tên nhân viên
	 * @return Trả về đối tượng DataInfoPageForJson gồm
	 *         [TottalRecord,ToltalPage,Data] và được tự động convert thành json
	 *         qua @Restcontroller.
	 * @throws khi thực hiện không thành công và trả về message gồm: devMsg,userMsg
	 *             }
	 */
	@GetMapping(UrlConstant.GET_EMPLOYEE_DATA)
	@Operation(description = "Lấy  danh sách nhân viên theo page", summary = "Lấy  danh sách nhân viên theo page")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> filerWithPagationAndFilter(
			@RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(name = "pageNumber", required = false, defaultValue = "1") int pageNumber,
			@RequestParam(name = "employeeFilter", required = false, defaultValue = "") String employeeFilter) {
		Optional<Page<Employee>> pageEmployees = employeeService.findEntitiesByFilter(pageSize, pageNumber,
				employeeFilter, new ArrayList<String>(Arrays.asList("employeeCode", "employeeName")));
		List<EmployeeDto> listDto = new ArrayList<EmployeeDto>();
		pageEmployees.get().getContent().forEach(e -> listDto.add(employeeConvert.convertToDto(e)));
		return resListData(listDto, pageEmployees.get().getTotalPages(), pageEmployees.get().getTotalElements());
	}

	/**
	 * 
	 * @author Nguyễn Văn Đông created_date: 08/06/2023 {@summary} Thêm nhân viên {
	 *         *
	 * 
	 * @return true or false
	 * @throws khi thực hiện không thành công và trả về message gồm: devMsg,userMsg
	 *             }
	 */
	@PostMapping(UrlConstant.CREATE_EMPLOYEE)
	@Operation(description = "Thêm nhân viên", summary = "Thêm nhân viên vào DB")
	@ResponseStatus(HttpStatus.OK)
	public Boolean insertEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
		return employeeService.save(employeeDto);

	}

	/**
	 * 
	 * @author Nguyễn Văn Đông created_date: 08/06/2023 {@summary} Sửa nhân viên
	 *
	 * 
	 * @return
	 * @throws khi thực hiện không thành công và trả về message gồm: devMsg,userMsg
	 *             }
	 */
	@PutMapping(UrlConstant.UPDATE_EMPLOYEE)
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "Sửa nhân viên", summary = "Sửa nhân viên vào DB")
	public Boolean updatetEmployee(@RequestBody EmployeeDto employeeDto) {

		return employeeService.update(employeeDto);

//			}catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//				return resError(e.getMessage(), UserMessageConstant.ERROR_USER);
//			}
	}

	/**
	 * 
	 * @author Nguyễn Văn Đông created_date: 08/06/2023 Tạo mã nhân viên
	 *         mới(EmployeeCode) theo cách :lấy mã nhân viên tạo gần nhất theo
	 *         created date tách phần số và cộng thêm 1 => mã nhân viên mới
	 * 
	 * @return trả về mã nhân viên mới
	 * @throws khi thực hiện không thành công và trả về message gồm: devMsg,userMsg
	 *             }
	 */
	@GetMapping(UrlConstant.GET_NEW_EMPLOYEE_CODE)

	@Operation(description = "Tạo mã  nhân viên mới ", summary = "Tạo mã nhân viên mới")
	public String getNewEmployeeCode() {

		return (employeeService.generateEmployeeCode());

	}

	/**
	 * 
	 * @author Nguyễn Văn Đông created_date: 08/06/2023
	 * 
	 *         {@summary} Xóa nhân viên theo ID
	 * 
	 * 
	 * @return statuscode=200 thành công
	 * @throws khi thực hiện không thành công và trả về message gồm: devMsg,userMsg
	 *             }
	 */
	@DeleteMapping(UrlConstant.DELETE_BY_EMPLOYEE_ID)
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "Xóa nhân viên ", summary = "Xóa nhân viên theo id")
	public int deleteEmployeeById(@PathVariable(name = "employeeId") int employeeId) {

		return (employeeService.deleteById(employeeId));

	}

	/**
	 * 
	 * @author Nguyễn Văn Đông created_date: 10/06/2023
	 * 
	 *         {@summary} Xuất danh sách nhân viên theo
	 *         pageSize,pageNumber,employeeFilter
	 * 
	 * 
	 * @return statuscode=201 thành công
	 * @throws khi thực hiện không thành công và trả về message gồm: devMsg,userMsg
	 *             }
	 */
	@Operation(description = "Export excel danh sách nhân viên", summary = "Export excel danh sách nhân viên")
	@GetMapping(UrlConstant.EXPORT_EXCEL)
	public ResponseEntity<?> exportExcel(HttpServletResponse response,
			@RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(name = "pageNumber", required = false, defaultValue = "1") int pageNumber,
			@RequestParam(name = "employeeFilter", required = false, defaultValue = "") String employeeFilter)
			throws IOException {
		// set response

		// set header
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		HttpHeaders header = new HttpHeaders();
		// dùng để test API qua post man hoặc swager
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String fileType = "attachment; filename=employee_details_" + dateFormat.format(new Date()) + ".xls";
		header.setContentType(new MediaType("application", "force-download"));
		header.set(HttpHeaders.CONTENT_DISPOSITION, fileType);

		// lay du lieu
		Optional<Page<Employee>> pageEmployees = employeeService.findEntitiesByFilter(pageSize, pageNumber,
				employeeFilter, new ArrayList<String>());
		List<EmployeeDto> listEmpDto = new ArrayList<EmployeeDto>();
		pageEmployees.get().getContent().forEach(e -> listEmpDto.add(employeeConvert.convertToDto(e)));

		return new ResponseEntity<>(employeeService.exportExcelEmployee(stream, listEmpDto), header,
				HttpStatus.CREATED);

	}

	@GetMapping(UrlConstant.IMPORT_EXCEL)
	public void importExcel(HttpServletResponse response) throws IOException {

		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String fileType = "attachment; filename=employee_details_" + dateFormat.format(new Date()) + ".xls";
		response.setHeader("Content-Disposition", fileType);
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM.getType());

		// employeeService.exportExcelEmployee(response,
		// employeeService.findAllEmployee());
	}
}
