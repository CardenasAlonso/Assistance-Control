import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface ChatRequest  { message: string; sessionId: string; }
export interface ChatResponse { response: string; sources?: string[]; sessionId: string; }

@Injectable({ providedIn: 'root' })
export class ChatService {
  private http = inject(HttpClient);
  private url = `${environment.chatbotUrl}/chat`;

  sendMessage(req: ChatRequest): Observable<ChatResponse> {
    return this.http.post<ChatResponse>(`${this.url}/message`, req);
  }
  getHistory(sessionId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.url}/history/${sessionId}`);
  }
  endSession(sessionId: string): Observable<void> {
    return this.http.delete<void>(`${this.url}/session/${sessionId}`);
  }
}
