import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RouterService } from '../services/router.service';
import { UserService } from '../services/user.service';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  display:boolean = true;
  otherDisplay:boolean = false;

  constructor(private router : Router, private routerService:RouterService, private userService:UserService) { }

  ngOnInit() {
    
    if(this.userService.isAuthenticated()){
      this.display = false;
      this.otherDisplay = true;
    }
    else{
      this.display = true;
      this.otherDisplay = false;
    }
  }
  home()
  {
    this.router.navigate(['home'])
  }
  login()
  {
    this.router.navigate(['login'])
  }
  logout(){
    this.userService.removeId();
    this.userService.removeToken();
    this.userService.removeAccount();
    this.router.navigate(['home']);
  }
  products()
  {
    this.router.navigate(['products'])
  }
  search(term)
  {
    if(term!=null && term!='')
    {
      console.log(term)
    }
    }

}
