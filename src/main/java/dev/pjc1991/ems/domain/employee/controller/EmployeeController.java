package dev.pjc1991.ems.domain.employee.controller;

import dev.pjc1991.ems.domain.employee.dto.*;
import dev.pjc1991.ems.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

 import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employee/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse getEmployeeResponseById(@PathVariable Integer id) {
        return employeeService.getEmployeeResponseById(id);
    }

    @GetMapping("/employee/{id}/history")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Page<JobHistoryResponse> getJobHistoryResponseByEmployeeId(@PathVariable Integer id, EmployeeSearchPageRequest request) {
        request.setEmployeeId(id);
        return employeeService.getJobHistoryResponseByEmployeeId(request);
    }

    @GetMapping("/location/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public LocationResponse getLocationResponseById(@PathVariable Integer id) {
        return employeeService.getLocationResponseById(id);
    }

    @GetMapping("/department/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public DepartmentResponse getDepartmentResponseById(@PathVariable Integer id) {
        return employeeService.getDepartmentResponseById(id);
    }

    @PutMapping("/department/{id}/raise")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponse> raiseSalaryResponseByDepartmentId(@PathVariable Integer id, @RequestBody SalaryRaiseRequest request) {
        request.setDepartmentId(id);
        return employeeService.raiseSalaryResponseByDepartmentId(request);
    }

    @PutMapping("/employee/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse updateEmployeeResponse(@PathVariable Integer id, @RequestBody EmployeeUpdateRequest request) {
        request.setId(id);
        return employeeService.updateEmployeeResponse(request);
    }


}
