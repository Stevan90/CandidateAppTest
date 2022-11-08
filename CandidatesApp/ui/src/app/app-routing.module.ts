import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCandidateComponent } from './add-candidate/add-candidate.component';
import { CandidateComponent } from './candidate/candidate.component';
import { StatusComponent } from './status/status.component';
import { DatabaseErrorComponent } from './database-error/database-error.component';

const routes: Routes = [
  {path:"", component:CandidateComponent},
  {path:"candidate",component:CandidateComponent},
  {path:"addCandidate",component:AddCandidateComponent},
  {path:"databaseError",component:DatabaseErrorComponent},
  {path:"**",component:StatusComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
