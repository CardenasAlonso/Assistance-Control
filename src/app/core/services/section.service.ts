import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface Section {
  id?: string; schoolYearId: string; name: string; capacity: number; turn?: string; isActive?: number;
}

@Injectable({ providedIn: 'root' })
export class SectionService {
  private http = inject(HttpClient);
  private url = `${environment.apiUrl}/sections`;
  getAll(): Observable<Section[]> { return this.http.get<Section[]>(this.url); }
  getById(id: string): Observable<Section> { return this.http.get<Section>(`${this.url}/${id}`); }
  create(s: Section): Observable<Section> { return this.http.post<Section>(this.url, s); }
  update(id: string, s: Section): Observable<Section> { return this.http.put<Section>(`${this.url}/${id}`, s); }
  delete(id: string): Observable<void> { return this.http.delete<void>(`${this.url}/${id}`); }
}
