package dev.pjc1991.ems.domain.employee.service;

import dev.pjc1991.ems.domain.employee.dto.EmployeeResponse;
import dev.pjc1991.ems.domain.employee.dto.EmployeeSearchPageRequest;
import dev.pjc1991.ems.domain.employee.dto.JobHistoryResponse;
import dev.pjc1991.ems.domain.employee.entity.Employee;
import dev.pjc1991.ems.domain.employee.entity.JobHistory;
import dev.pjc1991.ems.domain.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EmployeeServiceTest {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceTest.class.getName());
    private static final int TEST_EMPLOYEE_ID = 102;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

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
}