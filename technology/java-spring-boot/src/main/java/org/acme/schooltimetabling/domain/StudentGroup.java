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

package org.acme.schooltimetabling.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import javax.persistence.*;
import java.util.List;

@Entity
public class StudentGroup {

    @PlanningId
    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_group_fid", referencedColumnName = "id")
    private List<Degree> degrees;

    // No-arg constructor required for Hibernate and OptaPlanner
    public StudentGroup(String groupName) {
        this.name = groupName;
    }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }

    // ************************************************************************
    // Getters and setters
    // ************************************************************************

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void addDegree(Degree degree) {
        degrees.add(degree);
    }
    public List<Degree> getDegrees() {
        return degrees;
    }
    
}
