import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface ClassTask {
  id?: string; courseAssignmentId: string; competencyId: string;
  academicPeriodId: string; title: string; description?: string;
  dueDate?: string; isActive?: number; createdAt?: string;
}
export interface ClassTaskResponse extends ClassTask {
  competencyName?: string;
}

@Injectable({ providedIn: 'root' })
export class ClassTaskService {
  private http = inject(HttpClient);
  private url = `${environment.apiUrl}/class-tasks`;

  getByAssignment(id: string): Observable<ClassTaskResponse[]> {
    return this.http.get<ClassTaskResponse[]>(`${this.url}/assignment/${id}`);
  }
  getByPeriod(id: string): Observable<ClassTaskResponse[]> {
    return this.http.get<ClassTaskResponse[]>(`${this.url}/period/${id}`);
  }
  create(t: ClassTask): Observable<ClassTaskResponse> { return this.http.post<ClassTaskResponse>(this.url, t); }
  update(id: string, t: ClassTask): Observable<ClassTaskResponse> { return this.http.put<ClassTaskResponse>(`${this.url}/${id}`, t); }
  close(id: string): Observable<ClassTaskResponse> { return this.http.patch<ClassTaskResponse>(`${this.url}/${id}/close`, {}); }
  delete(id: string): Observable<void> { return this.http.delete<void>(`${this.url}/${id}`); }
}
