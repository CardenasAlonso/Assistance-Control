import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface DidacticUnit {
  id?: string; courseAssignmentId: string; unitNumber: number;
  title: string; description?: string; startDate?: string; endDate?: string; isActive?: number;
}

@Injectable({ providedIn: 'root' })
export class DidacticUnitService {
  private http = inject(HttpClient);
  private url = `${environment.apiUrl}/didactic-units`;
  getAll(): Observable<DidacticUnit[]> { return this.http.get<DidacticUnit[]>(this.url); }
  getByAssignment(courseAssignmentId: string): Observable<DidacticUnit[]> {
    return this.http.get<DidacticUnit[]>(`${this.url}/by-assignment/${courseAssignmentId}`);
  }
  create(u: DidacticUnit): Observable<DidacticUnit> { return this.http.post<DidacticUnit>(this.url, u); }
  update(id: string, u: DidacticUnit): Observable<DidacticUnit> { return this.http.put<DidacticUnit>(`${this.url}/${id}`, u); }
  close(id: string): Observable<DidacticUnit> { return this.http.patch<DidacticUnit>(`${this.url}/${id}/close`, {}); }
  delete(id: string): Observable<void> { return this.http.delete<void>(`${this.url}/${id}`); }
}
