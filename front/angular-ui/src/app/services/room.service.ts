import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
  params: new HttpParams()
}

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(private http: HttpClient) {
  }

  updateRoom(id: string, capacity: string, name: string,departement_id:string) {
    httpOptions.params = httpOptions.params.set("id", id).set("name", name).set("capacity",capacity).set("deparmtent_id",departement_id)

    let room = this.http.post(
      environment.baseUrl + '/rooms', {}, httpOptions
    ).pipe().toPromise().then(data => window.location.reload()).catch(err => {
      console.log("err on update room")
    });

    if (room) {
      console.log(room);
    }
  }

  deleteRoom(id: string) {
    return this.http.delete(environment.baseUrl + '/rooms?id=' + id, httpOptions)
      .pipe().toPromise().then(data => window.location.reload()).catch(err => {
        console.log("error on delete room");
      })
  }

  addRoom(name: string, capacity: string, departement_id:string) {
    return this.http.post(
      environment.baseUrl + '/rooms/add?name=' + name + '&capacity=' + capacity + '&departement_id=' + departement_id, httpOptions)
      .pipe().toPromise().then(data => window.location.reload())
      .catch(err => console.log(err))
  }
}
