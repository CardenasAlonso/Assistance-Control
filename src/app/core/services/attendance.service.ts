import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface Attendance {
  id?: string; studentId: string; courseAssignmentId?: string; date: string;
  status: string; checkInTime?: string; recordMethod?: string; comments?: string;
}

@Injectable({ providedIn: 'root' })
export class AttendanceService {
  private http = inject(HttpClient);
  private url = `${environment.apiUrl}/attendance`;

  getAll(): Observable<Attendance[]>                      { return this.http.get<Attendance[]>(this.url); }
  getByDate(date: string): Observable<Attendance[]>       { return this.http.get<Attendance[]>(`${this.url}/date/${date}`); }
  getByStudentId(id: string): Observable<Attendance[]>    { return this.http.get<Attendance[]>(`${this.url}/student/${id}`); }
  getBySection(sectionId: string): Observable<Attendance[]> { return this.http.get<Attendance[]>(`${this.url}/section/${sectionId}`); }
  create(a: Attendance): Observable<Attendance>           { return this.http.post<Attendance>(this.url, a); }
  createBulk(list: Attendance[]): Observable<Attendance[]>{ return this.http.post<Attendance[]>(`${this.url}/bulk`, list); }
  update(id: string, a: Attendance): Observable<Attendance>{ return this.http.put<Attendance>(`${this.url}/${id}`, a); }
}
