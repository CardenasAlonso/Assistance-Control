import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface ScoreCmd {
  studentId: string; classTaskId: string; score: number; comments?: string;
}
export interface ScoreResponse {
  id: string; studentId: string; classTaskId: string; classTaskTitle?: string;
  competencyName?: string; score: number; performanceLevel: string;
  approved: boolean; comments?: string; registeredAt: string;
}

@Injectable({ providedIn: 'root' })
export class ScoreService {
  private http = inject(HttpClient);
  private url = `${environment.apiUrl}/scores`;

  getAll(): Observable<ScoreResponse[]> { return this.http.get<ScoreResponse[]>(this.url); }
  getByStudent(studentId: string): Observable<ScoreResponse[]> {
    return this.http.get<ScoreResponse[]>(`${this.url}/student/${studentId}`);
  }
  getByClassTask(classTaskId: string): Observable<ScoreResponse[]> {
    return this.http.get<ScoreResponse[]>(`${this.url}/class-task/${classTaskId}`);
  }
  create(cmd: ScoreCmd): Observable<ScoreResponse> { return this.http.post<ScoreResponse>(this.url, cmd); }
  update(id: string, cmd: Partial<ScoreCmd>): Observable<ScoreResponse> {
    return this.http.put<ScoreResponse>(`${this.url}/${id}`, cmd);
  }
  delete(id: string): Observable<void> { return this.http.delete<void>(`${this.url}/${id}`); }
  getAverage(studentId: string, periodId: string): Observable<number> {
    return this.http.get<number>(`${this.url}/average/${studentId}/period/${periodId}`);
  }
}
