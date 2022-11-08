import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms'
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ResponseModel } from '../Model/ResponseModel';
import { CandidateMasterService } from '../Service/candidate-master.service';
import * as alertify from 'alertifyjs'

@Component({
  selector: 'app-candidatepopup',
  templateUrl: './candidatepopup.component.html',
  styleUrls: ['./candidatepopup.component.css']
})
export class CandidatepopupComponent implements OnInit {

  constructor(private router:Router,private service:CandidateMasterService, @Inject(MAT_DIALOG_DATA) public data:any, private ref:MatDialogRef<CandidatepopupComponent>) { }

  ngOnInit(): void {
    this.GetCandidate(this.data.id);
  }

  candidate:any;
  respdata!: ResponseModel;

  updateCandidateForm = new FormGroup({
    id: new FormControl(''),
    firstName: new FormControl('', Validators.compose([Validators.required, Validators.pattern('[a-zA-Z ]*')])),
    lastName: new FormControl('', Validators.compose([Validators.required, Validators.pattern('[a-zA-Z ]*')])),
    jmbg: new FormControl('', Validators.compose([Validators.required, this.jmbgValidator, Validators.pattern('[0-9]*')])),
    birthYear: new FormControl('',Validators.compose([Validators.minLength(4), Validators.maxLength(4), Validators.pattern('[0-9]*')])),
    email: new FormControl('', Validators.compose([Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')])),
    phone: new FormControl(''),
    note: new FormControl('', Validators.maxLength(1000)),
    employed: new FormControl(''),
  });

  UpadteCandidate(){
    if(this.updateCandidateForm.valid){
      this.service.UpdateCandidate<ResponseModel>(this.updateCandidateForm.value).subscribe(item=>{
        this.respdata=item;
        console.log(this.respdata);

        if(this.respdata.status){
          
          alertify.success(this.respdata.message);
          this.ref.close();
          } else {
            alertify.error(this.respdata.message);
          }
          
      });
    }
  }

  RedirectCandidate(){
    this.ref.close();
  }

  GetCandidate(id:any){
    this.service.GetCandidateById(id).subscribe(item=>{
      this.candidate = item;
      if(this.candidate!=null){
        this.updateCandidateForm.setValue({id:this.candidate.id, firstName:this.candidate.firstName, lastName:this.candidate.lastName, jmbg:this.candidate.jmbg, birthYear:this.candidate.birthYear, email:this.candidate.email, phone:this.candidate.phone, note:this.candidate.note, employed:this.candidate.employed});
      }else{
        alertify.error("Edit Candidate Error");
        this.ref.close();
      }
    });
  }

  jmbgValidator(control: FormControl) {

    let jmbg = control.value;
    let day = jmbg.substring(0, 2);
    let month = jmbg.substring(2, 4);
    let year = jmbg.substring(4, 7);

    //proveravamo duzinu
    if (jmbg.length != 13) {
      return {
        emailDomain: {
          parsedDomain: jmbg
        }
      }
    } else if (Number(day) < 1 || Number(day > 31)) {
      return {
        emailDomain: {
          parsedDomain: jmbg
        }
      }
    } else if (Number(month) < 1 || Number(month) > 12) {
      return {
        emailDomain: {
          parsedDomain: jmbg
        }
      }
    } else if ((Number(month) == 4 || Number(month) == 6 || Number(month) == 9 || Number(month) == 11) && Number(day) > 30) {
      return {
        emailDomain: {
          parsedDomain: jmbg
        }
      }
    } else if (Number(month) == 2 && Number(day) > 29) {
      return {
        emailDomain: {
          parsedDomain: jmbg
        }
      }
    } else {
      return null;
    }
  }
}
