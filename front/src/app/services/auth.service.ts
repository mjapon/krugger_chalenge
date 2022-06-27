import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { environment } from 'src/environments/environment';
import { BaseHttpService } from './basehttp.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService extends BaseHttpService {


  constructor(protected override http: HttpClient) {
    super('/aut/authenticate', http);
  }

  doLogin(loginCredentials: any) {
    return this.http.post(this.urlEndpoint, loginCredentials);
  }

  doLogout() {
    localStorage.clear();
  }

  isLoggedIn() {
    return this.getToken() !== null;
  }

  saveToken(token: string, empid: number) {
    localStorage.setItem('token', token);
    localStorage.setItem('empid', empid.toString());
    this.saveRoleFromToken();
  }

  getRole() {
    return localStorage.getItem('role');
  }

  getToken() {
    return localStorage.getItem('token');
  }
  getEmpId() {
    return localStorage.getItem('empid');
  }

  saveRoleFromToken() {
    let loginToken = this.getToken();
    if (loginToken) {
      const helper = new JwtHelperService();
      const decodedToken = helper.decodeToken(loginToken);
      if (decodedToken) {
        localStorage.setItem('principal', decodedToken.sub);
        localStorage.setItem('role', decodedToken.role);
      }
    }
  }

}
