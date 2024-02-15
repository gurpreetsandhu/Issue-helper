import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Details } from '../login/details';
import { RegisterationDetails } from '../registeration/registeration-details';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient:HttpClient) { }

  addUser(registerationDetail:RegisterationDetails){
    return this.httpClient.post(`http://localhost:8765/userservice/user`, registerationDetail);
  }

  getUser(){
    return this.httpClient.get<Array<RegisterationDetails>>(`http://localhost:8765/userservice/user`);
  }

  getToken(detail:Details){
    return this.httpClient.post(`http://localhost:8765/userservice/user/getToken`, detail);
  }

  getAccount(registrationDetails:RegisterationDetails){
    return this.httpClient.post(`http://localhost:8765/userservice/user/getAccount`, registrationDetails);
  }

  setToken(token) {
    localStorage.setItem('token', token);
  }

  setId(id) {
    localStorage.setItem('id', id);
  }

  removeId(){
    localStorage.removeItem('id');
  }

  removeToken(){
    localStorage.removeItem('token');
  }

  getId(){
    return localStorage.getItem('id');
  }

  getLocalToken(){
    return localStorage.getItem('token');
  }

  isAuthenticated(): boolean {
    let authorised = false
    if (this.getId() !== undefined && this.getId() !== null && this.getLocalToken() !== undefined && this.getLocalToken() !== null) {
      authorised = true;
    }

    return authorised;
  }

  setAccount(accNo){
    localStorage.setItem('acc',accNo);
  }

  getAccountNumber(){
    return localStorage.getItem('acc');
  }

  removeAccount(){
    localStorage.removeItem('acc');
  }

}
