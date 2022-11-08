import { Component, OnInit, ViewChild } from '@angular/core';
import { CandidateMasterService } from '../Service/candidate-master.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { CandidateModel } from '../Model/CandidateModel';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import * as alertify from 'alertifyjs'
import { CandidatepopupComponent } from '../candidatepopup/candidatepopup.component';
import { ResponseModel } from '../Model/ResponseModel';






@Component({
  selector: 'app-candidate',
  templateUrl: './candidate.component.html',
  styleUrls: ['./candidate.component.css']
})
export class CandidateComponent implements OnInit {

  constructor(private service:CandidateMasterService, private router:Router, private dialog:MatDialog) { }

  ngOnInit(): void {
    this.GetAllCandidates();
  }
  @ViewChild(MatPaginator) paginator !:MatPaginator;

  CandidateDetail: any;
  dataSource: any;
  respdata!: ResponseModel;

  GetAllCandidates(){
    this.service.GetAllCandidates().subscribe(item=>{
      this.CandidateDetail = item;
      this.dataSource = new MatTableDataSource<CandidateModel>(this.CandidateDetail);
      if(this.dataSource == null){
        this.router.navigate(['databaseError']);
      }
      this.dataSource.paginator = this.paginator;
    });
  }

  displayedColumns: string[] = ['id', 'firstName', 'lastName', 'jmbg', 'birthYear', 'email', 'phone', 'note', 'employed', 'modificationDate', 'Action'];
  //dataSource = ELEMENT_DATA;

  FunctionUpdate(id:any){
    
    let popup = this.dialog.open(CandidatepopupComponent,{
      width:'600px',
      //height:'800px',
      exitAnimationDuration:'1000ms',
      enterAnimationDuration:'1000ms',
      data:{
        id:id
      }
    });
    popup.afterClosed().subscribe(item=>{
      this.GetAllCandidates();
    });
  }

  FunctionDelete(id:any) {
    alertify.confirm("Delete Candidate", "Do you want to delete this candidate?", ()=>{
      this.service.DeleteCandidate<ResponseModel>(id).subscribe(item => {
        this.respdata = item;
        if(this.respdata.status){
          
          alertify.success(this.respdata.message);
        } else {
           alertify.error(this.respdata.message); 

        }
        this.GetAllCandidates();
      });
    }, function(){});
    
  }

  FunctionAdd(){
    this.router.navigate(['addCandidate']);
  }
}
