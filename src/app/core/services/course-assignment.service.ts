import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
export interface CourseAssignment { id?: string; courseId:string; teacherId:string; sectionId:string; schoolYearId:string; }
@Injectable({ providedIn: 'root' })
export class CourseAssignmentService {
  private http = inject(HttpClient); private url = `${environment.apiUrl}/course-assignments`;
  getAll(): Observable<CourseAssignment[]> { return this.http.get<CourseAssignment[]>(this.url); }
  getById(id: string): Observable<CourseAssignment> { return this.http.get<CourseAssignment>(`${this.url}/${id}`); }
  create(c: CourseAssignment): Observable<CourseAssignment> { return this.http.post<CourseAssignment>(this.url, c); }
  update(id: string, c: CourseAssignment): Observable<CourseAssignment> { return this.http.put<CourseAssignment>(`${this.url}/${id}`, c); }
  delete(id: string): Observable<void> { return this.http.delete<void>(`${this.url}/${id}`); }
}
