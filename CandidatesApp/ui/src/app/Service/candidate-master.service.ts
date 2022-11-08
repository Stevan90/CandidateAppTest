import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { getSystemErrorMap } from 'util';
import { CandidateModel } from '../Model/CandidateModel';

@Injectable({
  providedIn: 'root'
})
export class CandidateMasterService {

  constructor(private http: HttpClient) {

  }
  apiurl = 'http://localhost:8080/CandidatesApp/rest/candidate';

  GetAllCandidates():Observable<CandidateModel> {
    return this.http.get<CandidateModel>(this.apiurl + '/getAll');
  }

  GetCandidateById(id: any) {
    return this.http.get(this.apiurl + '/' + id + '/get');
  }

  DeleteCandidate<ResponseModel>(id: any) {
    return this.http.delete<ResponseModel>(this.apiurl + '/' + id + '/delete');
  }

  UpdateCandidate<ResponseModel>(inputdata: any) {
    return this.http.post<ResponseModel>(this.apiurl + '/add', inputdata);
  }
}

