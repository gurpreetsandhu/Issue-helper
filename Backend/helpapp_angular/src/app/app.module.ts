import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { BelowDashboardComponent } from './below-dashboard/below-dashboard.component';
import { AboveDashboardComponent } from './above-dashboard/above-dashboard.component';
import { FooterComponent } from './footer/footer.component';
import { AccountComponent } from './account/account.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { MatExpansionModule } from '@angular/material/expansion';
import { LoginComponent } from './login/login.component';
import { RegisterationComponent } from './registeration/registeration.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { FaqComponent } from './faq/faq.component';
import { MatCardModule } from '@angular/material/card';
import { ProductsComponent } from './products/products.component';
import { MatButtonModule } from '@angular/material/button'
import { HelpComponent } from './help/help.component';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    WelcomeComponent,
    BelowDashboardComponent,
    AboveDashboardComponent,
    FooterComponent,
    AccountComponent,
    LoginComponent,
    RegisterationComponent,
    FaqComponent,
    ProductsComponent,
    HelpComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatButtonModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
