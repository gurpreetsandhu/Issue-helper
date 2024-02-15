import { Component, OnInit } from '@angular/core';
import { RegisterationDetails } from './registeration-details';

@Component({
  selector: 'app-registeration',
  templateUrl: './registeration.component.html',
  styleUrls: ['./registeration.component.css']
})
export class RegisterationComponent implements OnInit {

  public userdetails: RegisterationDetails
  constructor() {
    this.userdetails=new RegisterationDetails();
   }

  ngOnInit() {
  }

}
