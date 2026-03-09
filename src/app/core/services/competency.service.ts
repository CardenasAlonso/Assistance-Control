import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface CourseCompetency {
  id?: string; courseId: string; name: string; description?: string; isActive?: number;
}

@Injectable({ providedIn: 'root' })
export class CompetencyService {
  private http = inject(HttpClient);
  private url = `${environment.apiUrl}/competencies`;

  getByCourse(courseId: string): Observable<CourseCompetency[]> {
    return this.http.get<CourseCompetency[]>(`${this.url}/course/${courseId}`);
  }
  getAll(): Observable<CourseCompetency[]> { return this.http.get<CourseCompetency[]>(this.url); }
  create(c: CourseCompetency): Observable<CourseCompetency> { return this.http.post<CourseCompetency>(this.url, c); }
  update(id: string, c: CourseCompetency): Observable<CourseCompetency> { return this.http.put<CourseCompetency>(`${this.url}/${id}`, c); }
  delete(id: string): Observable<void> { return this.http.delete<void>(`${this.url}/${id}`); }
}
