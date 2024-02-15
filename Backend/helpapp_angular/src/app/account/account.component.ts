import { Component, OnInit } from '@angular/core';
import { AccountService } from '../services/account.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  account:any;
  accountNumber:any;

  constructor(private accountService:AccountService, private userService:UserService) { 
    this.accountService.getSubject().subscribe(
      accNo =>{
        if(this.userService.getAccountNumber() !== null && this.userService.getAccountNumber() !== undefined){
          this.accountNumber=this.userService.getAccountNumber()
        }
        else{
          this.accountNumber=accNo;
        }
        this.accountService.getAccount(accNo).subscribe(
          data =>{
            this.account = data;
          },
          error =>{
            console.log("Error In Constructor");
          }
        );
      },
      error =>{
        console.log("Error in account");
      }
    );
  }

  ngOnInit() {
    
  }

}
