package dev.pjc1991.ems.domain.employee.service;

import dev.pjc1991.ems.domain.employee.dto.*;
import dev.pjc1991.ems.domain.employee.entity.Department;
import dev.pjc1991.ems.domain.employee.entity.Employee;
import dev.pjc1991.ems.domain.employee.entity.JobHistory;
import dev.pjc1991.ems.domain.employee.entity.Location;
import dev.pjc1991.ems.domain.employee.repository.DepartmentRepository;
import dev.pjc1991.ems.domain.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EmployeeServiceTest {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceTest.class.getName());
    private static final int TEST_EMPLOYEE_ID = 102;
    private static final int TEST_LOCATION_ID = 1700;
    private static final int TEST_DEPARTMENT_ID = 10;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void getEmployeeById() {
        // given
        List<Employee> employees = employeeRepository.findAll();
        Employee randomEmployee = employees.get((int) (Math.random() * employees.size()));

        // when
        Employee employee = employeeService.getEmployeeById(randomEmployee.getId());

        // then
        assertEquals(randomEmployee.getId(), employee.getId());

        log.info("Employee: " + employee.toString());
    }

    @Test
    void getEmployeeResponseById() {
        // given
        Employee randomEmployee = employeeRepository.findById(TEST_EMPLOYEE_ID).orElseThrow();

        // when
        EmployeeResponse response = employeeService.getEmployeeResponseById(randomEmployee.getId());

        // then
        assertEquals(randomEmployee.getId(), response.getId());

        log.info("Employee: " + response.toString());
    }


    @Test
    void getJobHistoryByEmployeeId() {
        // given
        Employee randomEmployee = employeeRepository.findById(TEST_EMPLOYEE_ID).orElseThrow();

        EmployeeSearchPageRequest request = new EmployeeSearchPageRequest();
        request.setEmployeeId(randomEmployee.getId());

        // when

        log.info("Employee ID: " + request.getEmployeeId());
        Page<JobHistory> page = employeeService.getJobHistoryByEmployeeId(request);

        // then
        page.getContent().forEach(jobHistory -> {
            log.info("Job History: {} from {} to {}", jobHistory.getJob().getJobTitle(), jobHistory.getStartDate(), jobHistory.getEndDate());
        });

        log.info("total elements: " + page.getTotalElements());
        log.info("total pages: " + page.getTotalPages());
        log.info("page size: " + page.getSize());

        assertNotNull(page);
        assertTrue(page.getTotalElements() > 0);
        assertEquals(randomEmployee.getId(), page.getContent().get(0).getEmployee().getId());


    }

    @Test
    void getJobHistoryResponseByEmployeeId() {
        // given
        Employee randomEmployee = employeeRepository.findById(TEST_EMPLOYEE_ID).orElseThrow();

        EmployeeSearchPageRequest request = new EmployeeSearchPageRequest();
        request.setEmployeeId(randomEmployee.getId());

        // when

        log.info("Employee ID: " + request.getEmployeeId());
        Page<JobHistoryResponse> page = employeeService.getJobHistoryResponseByEmployeeId(request);

        // then
        page.getContent().forEach(jobHistory -> {
            log.info("Job History Response : {} from {} to {}", jobHistory.getJobTitle(), jobHistory.getStartDate(), jobHistory.getEndDate());
        });

        log.info("total elements: " + page.getTotalElements());
        log.info("total pages: " + page.getTotalPages());
        log.info("page size: " + page.getSize());

        assertNotNull(page, "Page is null");
        assertTrue(page.getTotalElements() > 0, "Page is empty");
        assertEquals(randomEmployee.getId(), page.getContent().get(0).getEmployeeId(), "Employee ID does not match");

    }

    @Test
    void getLocationById() {
        // given
        Integer locationId = TEST_LOCATION_ID;

        // when
        Location location = employeeService.getLocationById(locationId);

        // then
        log.info("Location: " + location.toString());

        assertNotNull(location);
        assertEquals(locationId, location.getId());
    }

    @Test
    void getLocationResponseById() {
        // given
        Integer locationId = TEST_LOCATION_ID;

        // when
        LocationResponse response = employeeService.getLocationResponseById(locationId);

        // then
        log.info("Location Response: " + response.toString());
        assertNotNull(response);
        assertEquals(locationId, response.getId());
    }

    @Test
    void getDepartmentById() {
        // given
        Integer departmentId = TEST_DEPARTMENT_ID;

        // when
        Department department = employeeService.getDepartmentById(departmentId);

        // then
        log.info("Department: " + department.toString());
        assertNotNull(department);
        assertEquals(departmentId, department.getId());
    }

    @Test
    void getDepartmentResponseById() {
        // given
        Integer departmentId = TEST_DEPARTMENT_ID;

        // when
        DepartmentResponse response = employeeService.getDepartmentResponseById(departmentId);

        // then
        log.info("Department Response: " + response.toString());
        assertNotNull(response);
        assertEquals(departmentId, response.getId());
    }

    @Test
    void raiseSalaryByDepartmentId() {
        // given
        SalaryRaiseRequest request = new SalaryRaiseRequest();
        request.setDepartmentId(TEST_DEPARTMENT_ID);
        request.setRaisePercentage(10);

        // when
        Set<Employee> employees = employeeService.raiseSalaryByDepartmentId(request);

        // then
        employees.forEach(employee -> {
            log.info("Employee: " + employee.toString());
            assertEquals(TEST_DEPARTMENT_ID, employee.getDepartment().getId());
        });
    }

    @Test
    void raiseSalaryResponseByDepartmentId() {
        // given
        Department department = departmentRepository.findById(TEST_DEPARTMENT_ID).orElseThrow();

        SalaryRaiseRequest request = new SalaryRaiseRequest();
        request.setDepartmentId(TEST_DEPARTMENT_ID);
        request.setRaisePercentage(10);

        // when
        Set<EmployeeResponse> responses = employeeService.raiseSalaryResponseByDepartmentId(request);

        // then
        responses.forEach(response -> {
            log.info("Employee Response: " + response.toString());
            assertEquals(department.getDepartmentName(), response.getDepartmentName());
        });
    }

    @Test
    void updateEmployee() {
        // given
        Employee before = employeeRepository.findById(TEST_EMPLOYEE_ID).orElseThrow();
        log.info("Before Employee: " + before.toString());

        EmployeeUpdateRequest request = new EmployeeUpdateRequest();
        request.setId(TEST_EMPLOYEE_ID);
        request.setFirstName("John");
        request.setLastName("Doe");

        // when
        Employee employee = employeeService.updateEmployee(request);

        // then
        log.info("Employee: " + employee.toString());
        assertEquals(TEST_EMPLOYEE_ID, employee.getId());

    }

    @Test
    void updateEmployeeResponse() {
        // given
        EmployeeResponse beforeResponse = new EmployeeResponse(employeeRepository.findById(TEST_EMPLOYEE_ID).orElseThrow());
        log.info("Before Employee Response: " + beforeResponse.toString());

        EmployeeUpdateRequest request = new EmployeeUpdateRequest();
        request.setId(TEST_EMPLOYEE_ID);
        request.setFirstName("John");
        request.setLastName("Doe");

        // when
        EmployeeResponse response = employeeService.updateEmployeeResponse(request);

        // then
        log.info("Employee Response: " + response.toString());
        assertEquals(TEST_EMPLOYEE_ID, response.getId());
    }
}