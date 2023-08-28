package dev.pjc1991.ems.employee.service;

import dev.pjc1991.ems.domain.employee.dto.EmployeeResponse;
import dev.pjc1991.ems.domain.employee.entity.Employee;
import dev.pjc1991.ems.domain.employee.repository.EmployeeRepository;
import dev.pjc1991.ems.domain.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EmployeeServiceTest {

    private static final Logger log = Logger.getLogger(EmployeeServiceTest.class.getName());

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
        List<Employee> employees = employeeRepository.findAll();
        Employee randomEmployee = employees.get((int) (Math.random() * employees.size()));

        // when
        EmployeeResponse response = employeeService.getEmployeeResponseById(randomEmployee.getId());

        // then
        assertEquals(randomEmployee.getId(), response.getId());

        log.info("Employee: " + response.toString());
    }
}