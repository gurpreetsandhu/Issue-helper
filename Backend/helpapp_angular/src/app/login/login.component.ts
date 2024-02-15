import { Component, OnInit } from '@angular/core';
import { Details } from './details';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { AccountService } from '../services/account.service';
import { RegisterationDetails } from '../registeration/registeration-details';
import { RouterService } from '../services/router.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public details: Details
  public errMessage:String
  constructor(private userService:UserService, private accountService:AccountService, private routerService:RouterService) { 
    this.details=new Details();
  }

  ngOnInit() {
  }
  register()
  {
    this.routerService.routeToRegister();
  }

  login() {
    
    this.userService.getUser().subscribe(
      data => {
        //console.log(data);
        
        let valid = false;
        let id;
        for (let user of data) {
          //console.log(user);
          
          if (user['customerId'] === this.details.username && user['password'] === this.details.password) {
            valid = true;
            id = user['id'];
            //console.log(id);
            break;
          }
        }
        if (valid) {
          let token;
          //console.log(this.details);
          let registrationDetail:RegisterationDetails = new RegisterationDetails();
          registrationDetail.customerId = this.details.username
          registrationDetail.password = this.details.password
          this.userService.getToken(registrationDetail).subscribe(
            data =>{
            //  console.log("Token");
            //  console.log(data);
              
              
              token = data['token']
            },
            error =>{
              console.log("Error For Token");
            }
          );
          this.userService.setToken(token);
          this.userService.setId(id);

         
          
          this.userService.getAccount(registrationDetail).subscribe(
            data =>{
              this.accountService.setSubject(data['account']);
              this.userService.setAccount(data['account']);
            },
            error =>{
              console.log("Error in Account Number fetch");
            }
          );
          //console.log("Setting Account to : ", accNumber);
          this.routerService.routeToAccount();
        }
        else {
          this.errMessage = 'Invalid Username or Password';
        }
      },
      error => {
        this.errMessage = error.message;
      }
    )
  }

}
