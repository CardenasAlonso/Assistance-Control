import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface Teacher {
  id?: string; firstName: string; lastName: string; documentNumber: string;
  email?: string; speciality?: string; phone?: string; status?: string;
}

@Injectable({ providedIn: 'root' })
export class TeacherService {
  private http = inject(HttpClient);
  private url = `${environment.apiUrl}/teachers`;

  getAll(): Observable<Teacher[]>               { return this.http.get<Teacher[]>(this.url); }
  getById(id: string): Observable<Teacher>      { return this.http.get<Teacher>(`${this.url}/${id}`); }
  create(t: Teacher): Observable<Teacher>       { return this.http.post<Teacher>(this.url, t); }
  update(id: string, t: Teacher): Observable<Teacher> { return this.http.put<Teacher>(`${this.url}/${id}`, t); }
  delete(id: string): Observable<void>          { return this.http.delete<void>(`${this.url}/${id}`); }
}
