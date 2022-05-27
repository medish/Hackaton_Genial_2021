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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class StudentGroup {

    @PlanningId
    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Degree degree;

    // No-arg constructor required for Hibernate and OptaPlanner
    public StudentGroup(String groupName, Degree degree) {
        this.name = groupName;
        this.degree = degree;
    }

    @Override
    public String toString() {
        return name + " (" + id + ")" + " - " + degree.toString();
    }

    // ************************************************************************
    // Getters and setters
    // ************************************************************************

    public Long getId() {
        return id;
    }
    public Degree getDegree() {
        return degree;
    }
    public String getName() {
        return name;
    }
}
