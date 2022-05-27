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

package org.acme.schooltimetabling;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.acme.schooltimetabling.domain.*;
import org.acme.schooltimetabling.persistence.LessonRepository;
import org.acme.schooltimetabling.persistence.RoomRepository;
import org.acme.schooltimetabling.persistence.TimeslotRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class TimeTableSpringBootApp {

    public static void main(String[] args) {
        SpringApplication.run(TimeTableSpringBootApp.class, args);
    }

    @Value("${timeTable.demoData:SMALL}")
    private DemoData demoData;

    @Bean
    public CommandLineRunner demoData(
            TimeslotRepository timeslotRepository,
            RoomRepository roomRepository,
            LessonRepository lessonRepository) {
        return (args) -> {
            if (demoData == DemoData.NONE) {
                return;
            }

            Department sophieGermain = new Department("Sophie Germain");
            Department hallAuxFarine = new Department("Hall aux farine");

            Degree m1 = new Degree("m1");
            Degree m2 = new Degree("m2");

            StudentGroup genial_m2 = new StudentGroup("genial", m2);
            StudentGroup imapairs_m2 = new StudentGroup("imapairs", m2);
            StudentGroup imapairs_m1 = new StudentGroup("imapairs", m1);
            StudentGroup data_m1 = new StudentGroup("data", m1);


            timeslotRepository.save(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
            timeslotRepository.save(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
            timeslotRepository.save(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
            timeslotRepository.save(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
            timeslotRepository.save(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));
            timeslotRepository.save(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
            timeslotRepository.save(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
            timeslotRepository.save(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
            timeslotRepository.save(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
            timeslotRepository.save(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));
            if (demoData == DemoData.LARGE) {
                timeslotRepository.save(new Timeslot(DayOfWeek.WEDNESDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
                timeslotRepository.save(new Timeslot(DayOfWeek.WEDNESDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
                timeslotRepository.save(new Timeslot(DayOfWeek.WEDNESDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
                timeslotRepository.save(new Timeslot(DayOfWeek.WEDNESDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
                timeslotRepository.save(new Timeslot(DayOfWeek.WEDNESDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));
                timeslotRepository.save(new Timeslot(DayOfWeek.THURSDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
                timeslotRepository.save(new Timeslot(DayOfWeek.THURSDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
                timeslotRepository.save(new Timeslot(DayOfWeek.THURSDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
                timeslotRepository.save(new Timeslot(DayOfWeek.THURSDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
                timeslotRepository.save(new Timeslot(DayOfWeek.THURSDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));
                timeslotRepository.save(new Timeslot(DayOfWeek.FRIDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
                timeslotRepository.save(new Timeslot(DayOfWeek.FRIDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
                timeslotRepository.save(new Timeslot(DayOfWeek.FRIDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
                timeslotRepository.save(new Timeslot(DayOfWeek.FRIDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
                timeslotRepository.save(new Timeslot(DayOfWeek.FRIDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));
            }

            // Rooms

            // Rooms Sophie Germain




            Room room1Sophie = new Room("room 1", Room.RoomType.SIMPLE, 40, sophieGermain);
            Room room2Sophie = new Room("room 2", Room.RoomType.SIMPLE, 40,  sophieGermain);
            Room room3Sophie = new Room("room 3", Room.RoomType.SIMPLE, 40,  sophieGermain);
            Room room4Sophie = new Room("room 4", Room.RoomType.SIMPLE, 40,  sophieGermain);
            Room room5Sophie = new Room("room 5", Room.RoomType.SIMPLE, 40,  sophieGermain);
            Room room6Sophie = new Room("room 6", Room.RoomType.SIMPLE, 40,  sophieGermain);
            Room room7Sophie = new Room("room 7", Room.RoomType.SIMPLE, 40,  sophieGermain);
            Room roomTp1Sophie = new Room("room Tp1", Room.RoomType.TP, 40,  sophieGermain);
            Room roomTp2Sophie = new Room("room Tp2", Room.RoomType.TP, 40,  sophieGermain);
            Room roomTp3Sophie = new Room("room Tp3", Room.RoomType.TP, 40,  sophieGermain);


            // Rooms Hall aux Farines

            Room Amphi1Hall = new Room("amphi 1", Room.RoomType.AMPHI, 150, hallAuxFarine);
            Room Amphi2Hall = new Room("amphi 2", Room.RoomType.AMPHI, 200, hallAuxFarine);
            Room Amphi3Hall = new Room("amphi 3", Room.RoomType.AMPHI, 300, hallAuxFarine);

            roomRepository.save(room1Sophie);
            roomRepository.save(room2Sophie);
            roomRepository.save(room3Sophie);
            roomRepository.save(room4Sophie);
            roomRepository.save(room5Sophie);
            roomRepository.save(room6Sophie);
            roomRepository.save(room7Sophie);
            roomRepository.save(roomTp1Sophie);
            roomRepository.save(roomTp2Sophie);
            roomRepository.save(roomTp3Sophie);

            roomRepository.save(Amphi1Hall);
            roomRepository.save(Amphi2Hall);
            roomRepository.save(Amphi3Hall);

//            Lesson griGenial_m2 = new Lesson("GRI", genial_m2);
//            Lesson pocaGenial_m2 = new Lesson("POCA", genial_m2);
//            Lesson pocaImpairs_m2 = new Lesson("POCA", imapairs_m2);
//            Lesson pocaImpairs_m1 = new Lesson("POCA", imapairs_m1);
//            Lesson dataData_m1 = new Lesson("data", data_m1);
//
//            Lesson siGenial_m2 = new Lesson("si", genial_m2);
//            Lessont dataScrapping_m1 = new Lesson("data scrapping", data_m1);
//            Lesson gdsiImpairs_m2 = new Lesson("gestion des systemes d'information", imapairs_m2);
//
//            lessonRepository.save(griGenial_m2);
//            lessonRepository.save(pocaGenial_m2);
//            lessonRepository.save(pocaImpairs_m2);
//            lessonRepository.save(pocaImpairs_m1);
//            lessonRepository.save(dataData_m1);
//
//            lessonRepository.save(siGenial_m2);
//            lessonRepository.save(dataScrapping_m1);
//            lessonRepository.save(gdsiImpairs_m2);
//
//
//
//            Lesson lesson = lessonRepository.findAll(Sort.by("id")).iterator().next();
//            lesson.setTimeslot(timeslotRepository.findAll(Sort.by("id")).iterator().next());
//            lesson.setRoom(roomRepository.findAll(Sort.by("id")).iterator().next());
//            lessonRepository.save(lesson);

/*
            // Teachers
            Teacher pocaTacher1 = new Teacher("POCA teacher 1", "1", pocaGenial_m2);
            Teacher pocaTacher2 = new Teacher("POCA teacher 2", "2", pocaImpairs_m1);
            Teacher pocaTacher2 = new Teacher("POCA teacher 2", "2", pocaImpairs_m2);

            Teacher pocaTacher2 = new Teacher("POCA teacher 2", "2");
            Teacher pocaTacher3 = new Teacher("POCA teacher 3", "3");
            Teacher teacher = new Teacher("Alain", "Turing");
*/









        };
    }

    public enum DemoData {
        NONE,
        SMALL,
        LARGE
    }

}
