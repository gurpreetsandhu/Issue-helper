import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../products/product';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private httpClient:HttpClient) { }

  getProducts(){
    return this.httpClient.get<Array<Product>>(`http://localhost:8765/productservice/product`);
  }

}
