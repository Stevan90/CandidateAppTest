import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StatusComponent } from './status/status.component';

import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from 'src/Material-Module';
import { HttpClientModule, HttpClientXsrfModule} from '@angular/common/http';
import { CandidateComponent } from './candidate/candidate.component';
import { AddCandidateComponent } from './add-candidate/add-candidate.component';
import { ReactiveFormsModule} from "@angular/forms";
import { CandidatepopupComponent } from './candidatepopup/candidatepopup.component';
import { Routes } from '@angular/router';

import { NgxMaskModule, IConfig } from 'ngx-mask';
import { DatabaseErrorComponent } from './database-error/database-error.component';

//import { AccessRoutingModule } from './access/access-routing.module';



@NgModule({
  declarations: [
    AppComponent,
    StatusComponent,
    CandidateComponent,
    AddCandidateComponent,
    CandidatepopupComponent,
    DatabaseErrorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MaterialModule,
    ReactiveFormsModule,
    NgxMaskModule.forRoot()
    //AccessRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
