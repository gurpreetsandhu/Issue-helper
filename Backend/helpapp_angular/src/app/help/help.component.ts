import { Component, OnInit } from '@angular/core';
import { HelpService } from '../services/help.service';
import { Help } from './help';

@Component({
  selector: 'app-help',
  templateUrl: './help.component.html',
  styleUrls: ['./help.component.css']
})
export class HelpComponent implements OnInit {

  public helpResult:Help[];
  public query:Help = new Help(null,null,null,null);
  public errMessage:string;

  constructor(private helpService:HelpService) {}

  ngOnInit() {
  }
  search()
  {
    this.helpResult=null;
    this.errMessage=null;
    if(this.query.q != '' && this.query.q != undefined && this.query.q != null){
      this.helpService.getSearchResult(this.query).subscribe(
        data =>{
          this.helpResult = data;
        },
        error=>{
          console.log("Error in Help");
          this.errMessage='No Result Found';
        }
      );
    }
  }

}
