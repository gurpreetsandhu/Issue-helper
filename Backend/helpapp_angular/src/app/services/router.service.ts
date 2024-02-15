import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouterService {

  constructor(private router:Router) { }


  routeToLogin(){
    this.router.navigate(['login']);
  }

  routeToRegister(){
    this.router.navigate(['register']);
  }

  routeToHome(){
    this.router.navigate(['home']);
  }

  routeToAccount(){
    this.router.navigate(['account']);
  }

  routeToFaq(){
    this.router.navigate(['faqs']);
  }

  routeToProducts(){
    this.router.navigate(['products']);
  }
}
