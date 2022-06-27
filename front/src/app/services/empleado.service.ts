import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseHttpService } from './basehttp.service';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService extends BaseHttpService {

  constructor(protected override http: HttpClient) {
    super('/employes', http);
  }

  getAll() {
    return this.http.get(this.urlEndpoint + '/all', this.getHOT());
  }

  getBydId(empid: string) {
    return this.http.get(this.urlEndpoint + `/${empid}`, this.getHOT());
  }

  save(form: any) {
    return this.http.post(this.urlEndpoint + '/save', form, this.getHOT())
  }

  delete(empId: number) {
    return this.http.delete(this.urlEndpoint + `/delete/${empId}`, this.getHOT());
  }

  update(empId: number, form: any) {
    return this.http.put(this.urlEndpoint + `/update/${empId}`, form, this.getHOT());
  }

}
