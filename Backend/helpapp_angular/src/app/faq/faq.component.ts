import { Component, OnInit } from '@angular/core';
import { FaqService } from '../services/faq.service';
import { Faq } from './faq';

@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.css']
})
export class FaqComponent implements OnInit {

  faqs:Faq[] = [];

  constructor(private faqService:FaqService) { }

  ngOnInit() {
    this.faqService.getFaq().subscribe(
      data =>{
        this.faqs = data
      },
      error =>{
        console.log("Error in faq");
      }
    );
    
  }

}
