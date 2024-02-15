import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Account } from '../account/account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  public subjectBehavior:BehaviorSubject<any> = new BehaviorSubject<any>('');

  constructor(private httpClient:HttpClient) { 
  }

  getSubject(){
    return this.subjectBehavior;
  }

  getAccount(accountNumber){
    return this.httpClient.get<Account>(`http://localhost:8765/accountservice/account?accountNumber=${accountNumber}`);
  }

  setSubject(accNumber){
    this.subjectBehavior.next(accNumber);
  }


}
