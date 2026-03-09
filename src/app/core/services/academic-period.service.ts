import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface AcademicPeriod {
  id?: string; name: string; startDate: string; endDate: string; isActive?: number;
}

@Injectable({ providedIn: 'root' })
export class AcademicPeriodService {
  private http = inject(HttpClient);
  private url = `${environment.apiUrl}/academic-periods`;
  getAll(): Observable<AcademicPeriod[]> { return this.http.get<AcademicPeriod[]>(this.url); }
  getActive(): Observable<AcademicPeriod> { return this.http.get<AcademicPeriod>(`${this.url}/active`); }
  create(p: AcademicPeriod): Observable<AcademicPeriod> { return this.http.post<AcademicPeriod>(this.url, p); }
  activate(id: string): Observable<AcademicPeriod> { return this.http.patch<AcademicPeriod>(`${this.url}/activate/${id}`, {}); }
  deactivate(id: string): Observable<AcademicPeriod> { return this.http.patch<AcademicPeriod>(`${this.url}/deactivate/${id}`, {}); }
  delete(id: string): Observable<void> { return this.http.delete<void>(`${this.url}/${id}`); }
}
