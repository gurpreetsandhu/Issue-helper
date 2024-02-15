import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  constructor(private router : Router) { }

  ngOnInit() {
  }
  below()
  {
    this.router.navigate(['below'])
  }
  above()
  {
    this.router.navigate(['above'])
  }
 not()
  {
    this.router.navigate(['below'])
  }

}
