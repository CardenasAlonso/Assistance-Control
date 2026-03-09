import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface Student {
  id?: string; firstName: string; lastName: string; documentNumber: string;
  sectionId: string; email?: string; phone?: string; address?: string;
  birthDate?: string; bloodType?: string; status?: string;
}

@Injectable({ providedIn: 'root' })
export class StudentService {
  private http = inject(HttpClient);
  private url = `${environment.apiUrl}/students`;

  getAll(): Observable<Student[]> { return this.http.get<Student[]>(this.url); }
  getById(id: string): Observable<Student> { return this.http.get<Student>(`${this.url}/${id}`); }
  getBySection(sectionId: string): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.url}/section/${sectionId}`);
  }
  create(s: Student): Observable<Student> { return this.http.post<Student>(this.url, s); }
  update(id: string, s: Student): Observable<Student> { return this.http.put<Student>(`${this.url}/${id}`, s); }
  delete(id: string): Observable<void> { return this.http.delete<void>(`${this.url}/${id}`); }
}
