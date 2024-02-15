import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {WelcomeComponent} from './welcome/welcome.component';
import {BelowDashboardComponent} from './below-dashboard/below-dashboard.component';
import {AboveDashboardComponent} from './above-dashboard/above-dashboard.component';
import { AccountComponent } from './account/account.component';
import { LoginComponent } from './login/login.component';
import { RegisterationComponent } from './registeration/registeration.component';
import { FaqComponent } from './faq/faq.component';
import { ProductsComponent } from './products/products.component';
import {HelpComponent} from './help/help.component';
import { AccountGuardGuard } from './account-guard.guard';


const routes: Routes = [
  
  {
    path: 'welcome',
    component: WelcomeComponent,

  },
  {
    path: 'below',
    component: BelowDashboardComponent,

  },
  {
    path: 'above',
    component: AboveDashboardComponent,

  },
  {
    path: 'home',
    component: BelowDashboardComponent
  },
  {
    path: 'account',
    component: AccountComponent,
    canActivate: [AccountGuardGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterationComponent
  },
  {
    path: 'faq',
    component: FaqComponent
  },
  {
    path: 'products',
    component: ProductsComponent
  },
  {
    path: 'help', 
    component: HelpComponent
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: '**',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: '', //default
    component: BelowDashboardComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
