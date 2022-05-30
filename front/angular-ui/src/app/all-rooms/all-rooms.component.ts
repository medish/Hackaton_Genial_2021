import {Component, OnInit} from '@angular/core';
import {DataInterfaceService} from "../services/data-interface.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {document} from "ngx-bootstrap/utils";
import {Modal} from 'bootstrap';
import {RoomService} from "../services/room.service";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-all-rooms',
  templateUrl: './all-rooms.component.html',
  styleUrls: ['./all-rooms.component.scss']
})
export class AllRoomsComponent implements OnInit {

  constructor(private authService: AuthService,
              private dataService: DataInterfaceService,
              private formBuilderAddRoom: FormBuilder,
              private formBuilder: FormBuilder, private roomService: RoomService) {
  }

  formGroupModal: FormGroup;
  submitted: boolean = false;
  submitted_edit = false;
  rooms = [];
  loading: boolean = false;
  formAddRoom: FormGroup

  ngOnInit(): void {
    this.dataService.fetchAllRooms(this.onRoomsReceived, this)
    this.formGroupModal = this.formBuilder.group({
      name: ['', [Validators.required, Validators.max(255), Validators.min(20)]],
      capacity: ['', [Validators.required, Validators.max(255), Validators.min(20)]],
      roomTypes:['', [Validators.required, Validators.max(255), Validators.min(20)]],
      id: []
    });

    this.formAddRoom = this.formBuilderAddRoom.group({
      name: ['', [Validators.required, Validators.max(255), Validators.min(20)]],
      capacity: ['', [Validators.required, Validators.max(255), Validators.min(20)]],
      roomTypes:['', [Validators.required, Validators.max(255), Validators.min(20)]],
      id: []
    });
  }

  onRoomsReceived(rooms, context: this) {
    for (let room of rooms) {
      context.rooms.push(room)
    }
  }

  editRoom(room) {
    const modal = new Modal(document.getElementById("editRoomModal"), {
      keyboard: false
    });

    this.formGroupModal.controls['name'].setValue(room.name);
    this.formGroupModal.controls['capacity'].setValue(room.capacity);
    this.formGroupModal.controls['roomTypes'].setValue(room.roomTypes);
    this.formGroupModal.controls['id'].setValue(room.id)
    modal.show();
  }

  deleteRoom(room) {
    this.roomService.deleteRoom(room.id);
  }

  updateRoom() {
    this.submitted_edit = true;

    if (this.formGroupModal.invalid) {
      return;
    }

    this.roomService.updateRoom(
      this.formGroupModal.controls['name'].value, this.formGroupModal.controls['capacity'].value,
      this.formGroupModal.controls['roomTypes'].value,this.formGroupModal.controls['id'].value);
    const modal = new Modal(document.getElementById("editRoomModal"), {
      keyboard: false
    });
    modal.close();
  }

  get f() {
    return this.formAddRoom.controls;
  }

  get g() {
    return this.formGroupModal.controls;
  }

  addRoom() {
    const modal = new Modal(document.getElementById('addRoomModal'), {
      keyboard: false
    }).show();
  }

  onSubmitAddRoom() {
    this.submitted = true;

    if (this.formAddRoom.invalid) {
      return;
    }

    this.loading = true;

    this.roomService.addRoom(this.formAddRoom.controls['name'].value,
      this.formAddRoom.controls['capacity'].value,
      this.formAddRoom.controls['roomTypes'].value)
  }
}
