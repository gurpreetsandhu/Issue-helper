import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Faq } from '../faq/faq';

@Injectable({
  providedIn: 'root'
})
export class FaqService {

  constructor(private httpClient:HttpClient) { }

  getFaq(){
    return this.httpClient.get<Array<Faq>>(`http://localhost:8765/faqservice/faq`);
  }
}
