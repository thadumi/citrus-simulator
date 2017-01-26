package com.consol.citrus.simulator.controller;

import com.consol.citrus.simulator.model.TestExecution;
import com.consol.citrus.simulator.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
public class ActivityController {

    @Autowired
    ActivityService executionService;

    // TODO MM rename execution to activity
    @RequestMapping(method = RequestMethod.GET, value = "/execution")
    public Collection<TestExecution> getTestExecutions(
            @RequestParam(value = "fromDate", required = false) Date fromDate,
            @RequestParam(value = "toDate", required = false) Date toDate,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size
    ) {
        return executionService.getTestExecutionsByStartDate(fromDate, toDate, page, size);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/execution")
    public void clearExecutions() {
        executionService.clearTestExecutions();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/execution/test/{name}")
    public Collection<TestExecution> getTestExecutionsByTestName(@PathVariable("name") String name) {
        return executionService.getTestExecutionsByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/execution/status/{status}")
    public Collection<TestExecution> getTestExecutionsByStatus(@PathVariable("status") String status) {
        return executionService.getTestExecutionsByStatus(TestExecution.Status.valueOf(status));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/execution/{id}")
    public TestExecution getTestExecution(@PathVariable("id") Long id) {
        return executionService.getTestExecutionById(id);
    }
}
