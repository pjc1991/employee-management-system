package dev.pjc1991.ems.domain.employee.service;

import dev.pjc1991.ems.domain.employee.dto.*;
import dev.pjc1991.ems.domain.employee.entity.*;
import dev.pjc1991.ems.domain.employee.repository.DepartmentRepository;
import dev.pjc1991.ems.domain.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

        log.info("Employee: " + employee);
    }

    @Test
    void getEmployeeResponseById() {
        // given
        Employee randomEmployee = employeeRepository.findById(TEST_EMPLOYEE_ID).orElseThrow();

        // when
        EmployeeResponse response = employeeService.getEmployeeResponseById(randomEmployee.getId());

        // then
        assertEquals(randomEmployee.getId(), response.getId());

        log.info("Employee: " + response);
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
        page.getContent().forEach(jobHistory -> log.info("Job History : {} from {} to {}", jobHistory.getJob().getJobTitle(), jobHistory.getStartDate(), jobHistory.getEndDate()));

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
        page.getContent().forEach(jobHistory -> log.info("Job History Response : {} from {} to {}", jobHistory.getJobTitle(), jobHistory.getStartDate(), jobHistory.getEndDate()));

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
        Department department = departmentRepository.findById(TEST_DEPARTMENT_ID).orElseThrow();
        List<Employee> before = department.getEmployees();
        HashMap<Integer, BigDecimal> salaryMap = before.stream().map(employee -> Map.entry(employee.getId(), employee.getSalary())).collect(HashMap::new, (m, v) -> m.put(v.getKey(), v.getValue()), HashMap::putAll);

        int raisePercentage = 10;

        SalaryRaiseRequest request = new SalaryRaiseRequest();
        request.setDepartmentId(TEST_DEPARTMENT_ID);
        request.setRaisePercentage(raisePercentage);


        // when
        List<Employee> employees = employeeService.raiseSalaryByDepartmentId(request);

        // then
        employees.forEach(employee -> {
            log.info("Employee: " + employee.getId());
            BigDecimal maxSalary = employee.getJob().getMaxSalary();
            BigDecimal minSalary = employee.getJob().getMinSalary();
            BigDecimal salaryBefore = salaryMap.get(employee.getId());
            BigDecimal salaryExpected = salaryBefore.multiply(BigDecimal.valueOf(1 + (raisePercentage / 100.0))).min(maxSalary).max(minSalary);
            BigDecimal salary = employee.getSalary();
            log.info("Max Salary: " + maxSalary);
            log.info("Min Salary: " + minSalary);
            log.info("Salary Before: " + salaryBefore);
            log.info("Salary Expected: " + salaryExpected);
            log.info("Salary: " + salary);
            assertEquals(TEST_DEPARTMENT_ID, employee.getDepartment().getId());
            assertTrue(salary.compareTo(minSalary) >= 0);
            assertTrue(salary.compareTo(maxSalary) <= 0);
            assertEquals(salary, salaryExpected);
        });
    }

    @Test
    void raiseSalaryResponseByDepartmentId() {
        // given
        Department department = departmentRepository.findById(TEST_DEPARTMENT_ID).orElseThrow();
        List<Employee> before = department.getEmployees();
        HashMap<Integer, BigDecimal> salaryMap = before.stream().map(employee -> Map.entry(employee.getId(), employee.getSalary())).collect(HashMap::new, (m, v) -> m.put(v.getKey(), v.getValue()), HashMap::putAll);

        Set<Job> jobs = before.stream().map(Employee::getJob).collect(Collectors.toSet());
        HashMap<String, BigDecimal> maxSalaryMap = jobs.stream().map(job -> Map.entry(job.getJobId(), job.getMaxSalary())).collect(HashMap::new, (m, v) -> m.put(v.getKey(), v.getValue()), HashMap::putAll);
        HashMap<String, BigDecimal> minSalaryMap = jobs.stream().map(job -> Map.entry(job.getJobId(), job.getMinSalary())).collect(HashMap::new, (m, v) -> m.put(v.getKey(), v.getValue()), HashMap::putAll);

        int raisePercentage = 10;

        SalaryRaiseRequest request = new SalaryRaiseRequest();
        request.setDepartmentId(TEST_DEPARTMENT_ID);
        request.setRaisePercentage(raisePercentage);

        // when
        List<EmployeeResponse> responses = employeeService.raiseSalaryResponseByDepartmentId(request);

        // then

        responses.forEach(response -> {
            log.info("Employee: " + response.getId());
            BigDecimal maxSalary = maxSalaryMap.get(response.getJobId());
            BigDecimal minSalary = minSalaryMap.get(response.getJobId());
            BigDecimal salaryBefore = salaryMap.get(response.getId());
            BigDecimal salaryExpected = salaryBefore.multiply(BigDecimal.valueOf(1 + (raisePercentage / 100.0))).min(maxSalary).max(minSalary);
            BigDecimal salary = response.getSalary();
            log.info("Max Salary: " + maxSalary);
            log.info("Min Salary: " + minSalary);
            log.info("Salary Before: " + salaryBefore);
            log.info("Salary Expected: " + salaryExpected);
            log.info("Salary: " + salary);
            assertEquals(TEST_DEPARTMENT_ID, response.getDepartmentId());
            assertTrue(salary.compareTo(minSalary) >= 0);
            assertTrue(salary.compareTo(maxSalary) <= 0);
            assertEquals(salary, salaryExpected);
        });
    }

    @Test
    void updateEmployee() {
        // given
        Employee before = employeeRepository.findById(TEST_EMPLOYEE_ID).orElseThrow();
        log.info("Before Employee: " + before);

        EmployeeUpdateRequest request = getEmployeeUpdateRequest();

        // when
        Employee employee = employeeService.updateEmployee(request);

        // then
        log.info("Employee: " + employee.toString());
        assertEquals(TEST_EMPLOYEE_ID, employee.getId());
        assertEquals(request.getFirstName(), employee.getFirstName());
        assertEquals(request.getLastName(), employee.getLastName());
        assertEquals(request.getEmail(), employee.getEmail());
        assertEquals(request.getPhoneNumber(), employee.getPhoneNumber());
        assertEquals(request.getJobId(), employee.getJob().getJobId());
        assertEquals(request.getSalary(), employee.getSalary());
        assertEquals(request.getCommissionPct(), employee.getCommissionPct());
        assertEquals(request.getManagerId(), employee.getManager().getId());
        assertEquals(request.getDepartmentId(), employee.getDepartment().getId());
    }

    @Test
    void updateEmployeeResponse() {
        // given
        EmployeeResponse beforeResponse = new EmployeeResponse(employeeRepository.findById(TEST_EMPLOYEE_ID).orElseThrow());
        log.info("Before Employee Response: " + beforeResponse);

        EmployeeUpdateRequest request = getEmployeeUpdateRequest();


        // when
        EmployeeResponse response = employeeService.updateEmployeeResponse(request);

        // then
        log.info("Employee Response: " + response);
        assertEquals(TEST_EMPLOYEE_ID, response.getId());
        assertEquals(request.getFirstName(), response.getFirstName());
        assertEquals(request.getLastName(), response.getLastName());
        assertEquals(request.getEmail(), response.getEmail());
        assertEquals(request.getPhoneNumber(), response.getPhoneNumber());
        assertEquals(request.getJobId(), response.getJobId());
        assertEquals(request.getSalary(), response.getSalary());
        assertEquals(request.getCommissionPct(), response.getCommissionPct());
        assertEquals(request.getManagerId(), response.getManagerId());
        assertEquals(request.getDepartmentId(), response.getDepartmentId());
    }


    private static EmployeeUpdateRequest getEmployeeUpdateRequest() {
        EmployeeUpdateRequest request = new EmployeeUpdateRequest();
        request.setId(TEST_EMPLOYEE_ID);
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("new@email.com");
        request.setPhoneNumber("1234567890");
        request.setJobId("AD_PRES");
        request.setSalary(BigDecimal.valueOf(30000));
        request.setCommissionPct(BigDecimal.valueOf(10));
        request.setManagerId(110);
        request.setDepartmentId(10);

        return request;
    }
}