/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.acme.schooltimetabling.rest;

import org.acme.schooltimetabling.domain.Department;
import org.acme.schooltimetabling.domain.TimeTable;
import org.acme.schooltimetabling.persistence.DepartmentRepository;
import org.acme.schooltimetabling.persistence.TimeTableRepository;
import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.SolverManager;
import org.optaplanner.core.api.solver.SolverStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @RequestMapping("/departments")
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

   @PostMapping("/department")
    public void addDepartment(@RequestBody Department department) {
       departmentRepository.save(department);
    }

}
