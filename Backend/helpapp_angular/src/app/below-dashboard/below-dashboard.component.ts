import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-below-dashboard',
  templateUrl: './below-dashboard.component.html',
  styleUrls: ['./below-dashboard.component.css']
})
export class BelowDashboardComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }
  chat()
  {
    this.router.navigate(['help'])
  }
  faq()
  {
    this.router.navigate(['faq'])
    console.log("faq clicked")
  }

}
