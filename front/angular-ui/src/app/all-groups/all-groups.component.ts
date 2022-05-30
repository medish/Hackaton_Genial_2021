import {Component, OnInit} from '@angular/core';
import {Modal} from 'bootstrap';
import {
  Course,
  CoursecontrollerApi,
  CourseGroup,
  CoursegroupcontrollerApi,
  CourseGroupRoomTypeEnum,
  Duration, Major, MajorcontrollerApi,
  MajorCourse,
} from "../model/swagger/api";
import {document} from "ngx-bootstrap/utils";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
  params: new HttpParams()
}

@Component({
  selector: 'app-all-groups',
  templateUrl: './all-groups.component.html',
  styleUrls: ['./all-groups.component.scss']
})
export class AllGroupsComponent implements OnInit {

  constructor(private coursegroupcontroller: CoursegroupcontrollerApi,
              private course: CoursecontrollerApi,
              private majors: MajorcontrollerApi, private http: HttpClient) {

  }

  all_groups: CourseGroup[];
  all_courses: Course[];
  all_majors: Major[];

  all_rooms_type: CourseGroupRoomTypeEnum;
  array_filter: any[] = [];

  is_edit_cliked: boolean;

  formGroupModal = new FormGroup({
    groupe_name: new FormControl(),
    niveau: new FormControl(),
    cours_name: new FormControl([Validators.required]),
    major_name: new FormControl([Validators.required]),
    duration: new FormControl([Validators.required]),
    roomtype: new FormControl([Validators.required]),
    nb_students: new FormControl([Validators.required]),
  })


  async ngOnInit(): Promise<void> {
    this.all_groups = await this.coursegroupcontroller.getAllUsingGET1().then(data => {
      console.log(data);
      return data;
    })

    //todo get major degree and synchronization and filter enfonction des levels
    this.majors.getAllUsingGET5().then(data => {
        this.all_majors = data;
      }
    )


    // @ts-ignore
    var time_instance = flatpickr('#duration', {
      enableTime: true,
      noCalendar: true,
      dateFormat: "H:i",
      time_24hr: true,
      minTime: '00:30',
      maxTime: '09:00',
      defaultDate: this.toHHMMSS(this.formGroupModal.controls['duration'].value)
    });


    this.is_edit_cliked = false;

    console.log(this.all_rooms_type);
    this.course.getAllUsingGET().then(data => {
      this.all_courses = data;
      console.log(data);
    })
  }


  editGroup(group: CourseGroup) {

    const modal = new Modal(document.getElementById("modal-group"), {
      keyboard: false
    });
    this.is_edit_cliked = true;
    document.getElementById('div_groupe_name').hidden = false;
    this.formGroupModal.controls['groupe_name'].setValue("groupe " + group.id);
    this.formGroupModal.controls['groupe_name'].disable();
    this.formGroupModal.controls['duration'].setValue(this.toHHMMSS(group.duration));
    this.formGroupModal.controls['roomtype'].setValue(group.roomType);
    this.formGroupModal.controls['major_name'].setValue(group.majorCourse.major.id);
    this.formGroupModal.controls['cours_name'].setValue(group.majorCourse.course.id);
    this.formGroupModal.controls['nb_students'].setValue(group.size);
    modal.show();
  }


  courses_group_to_delete: CourseGroup;

  deleteGroup(group: CourseGroup) {
    const modal = new Modal(document.getElementById("modal-delete-group"));
    this.courses_group_to_delete = group;
    modal.show();
  }


  handleDeleteGroup(group: CourseGroup) {
    console.log('delete groupe');
  }


  handleAddEdit() {
    if (!this.is_edit_cliked) {
      this.insertNewGroupe()
    } else {
      console.log("edit group")
      this.handleEditCourseGroup()
    }
  }


  /**
   *
   * @param cours_id
   * @param major_id
   */
  getMajorCourse(cours_id: number, major_id: number) {
    let y: MajorCourse;
    y = new class implements MajorCourse {
      course: Course;
      major: Major;
    }
    let course = this.all_courses.filter(x => x.id == cours_id)[0];
    let major = this.all_majors.filter(x => x.id == major_id)[0];
    y.course = course;
    y.major = major;
    return y;
  }


  addCourseGroup() {
    let modal = new Modal(document.getElementById("modal-group"));
    document.getElementById('div_groupe_name').hidden = true;
    this.formGroupModal.reset();
    this.is_edit_cliked = false;
    modal.show();
  }



  handleDelete() {
    return this.http.delete(environment.baseUrl + '/courses-groups/' + this.courses_group_to_delete.id).subscribe(data => {
      console.log(data);
      alert(data);
    })

  }





  insertNewGroupe() {
    let res: CourseGroup;
    res = new class implements CourseGroup {
      duration: Duration;
      id: number;
      majorCourse: MajorCourse;
      roomType: CourseGroupRoomTypeEnum;
      size: number;
    }
    res.size = this.formGroupModal.value.nb_students;
    console.log(this.formGroupModal.get('duration'));
    // @ts-ignore
    res.duration = this.timeToSecond(this.formGroupModal.value.duration.toString());
    console.log(this.formGroupModal.value.duration);
    res.majorCourse = this.getMajorCourse(this.formGroupModal.value.cours_name, this.formGroupModal.value.major_name);
    res.roomType = this.formGroupModal.value.roomtype;
    console.log(res);

    //test is valid
    // [ngClass]="{'is-invalid': submitted && f.username.errors}

    //if(this.formGroupModal)
    this.coursegroupcontroller.insertUsingPOST1({courseGroup: res}).then(
      response => {
        if (response == true) {
          alert('Insertion OK ')
          location.reload();
        }
      }
    )
  }


  handleEditCourseGroup() {
    // check form

    let res: CourseGroup;
    res = new class implements CourseGroup {
      duration: Duration;
      id: number;
      majorCourse: MajorCourse;
      roomType: CourseGroupRoomTypeEnum;
      size: number;
    }

    res.size = this.formGroupModal.value.nb_students;
    console.log(this.formGroupModal.get('duration'));
    // @ts-ignore
    res.duration = this.timeToSecond(this.formGroupModal.value.duration.toString()); // todo get real duration and convert it to second

    console.log(this.formGroupModal.value.duration);
    res.majorCourse = this.getMajorCourse(this.formGroupModal.value.cours_name, this.formGroupModal.value.major_name);
    res.roomType = this.formGroupModal.value.roomtype;
    console.log(res);

    // todo use update
    //

    //httpOptions.params= httpOptions.params.set()
    /*this.http.post(environment.baseUrl + '/users', {}, httpOptions).subscribe(data =>{
      console.log(data);
    }) */


    /*  ({courseGroup: res}).then(
       response => {
         if (response == true) {
           alert('Modification OK ')
           location.reload();
         }
       }
     ) */


  }

  /**
   * Convert seconds to time format HH:MM:SS
   * @param timeInSeconds
   */
  toHHMMSS(timeInSeconds) {
    var sec_num = parseInt(timeInSeconds, 10);
    var hours: any = Math.floor(sec_num / 3600);
    var minutes: any = Math.floor((sec_num - (hours * 3600)) / 60);

    if (hours < 10) {
      hours = "0" + hours;
    }
    if (minutes < 10) {
      minutes = "0" + minutes;
    }
    return hours + ':' + minutes;
  }


  timeToSecond(time: string) {
    console.log(time)
    var a = time.split(':');
    // @ts-ignore
    return (a[0]) * 60 * 60 + (a[1]) * 60;

  }


}
