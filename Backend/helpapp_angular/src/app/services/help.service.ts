import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Help } from '../help/help';

@Injectable({
  providedIn: 'root'
})
export class HelpService {

  constructor(private httpClient:HttpClient) { }

  getSearchResult(help:Help){
    return this.httpClient.post<Array<Help>>(`http://localhost:8765/queryservice/query/search`,help)
  }
}
