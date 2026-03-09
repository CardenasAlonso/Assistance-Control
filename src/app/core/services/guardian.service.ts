import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface Guardian {
  id?: string; studentId: string; firstName: string; lastName: string;
  relationship: string; documentNumber: string; phone: string;
  email?: string; address?: string; isPrimaryContact?: number;
}

@Injectable({ providedIn: 'root' })
export class GuardianService {
  private http = inject(HttpClient);
  private url = `${environment.apiUrl}/guardians`;
  getAll(): Observable<Guardian[]> { return this.http.get<Guardian[]>(this.url); }
  getById(id: string): Observable<Guardian> { return this.http.get<Guardian>(`${this.url}/${id}`); }
  getByStudent(studentId: string): Observable<Guardian[]> {
    return this.http.get<Guardian[]>(`${this.url}/student/${studentId}`);
  }
  create(g: Guardian): Observable<Guardian> { return this.http.post<Guardian>(this.url, g); }
  update(id: string, g: Guardian): Observable<Guardian> { return this.http.put<Guardian>(`${this.url}/${id}`, g); }
  delete(id: string): Observable<void> { return this.http.delete<void>(`${this.url}/${id}`); }
}
