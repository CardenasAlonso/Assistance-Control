import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface Notification {
  id: string; type: string; title: string; body: string;
  referenceId?: string; isRead: number; createdAt: string;
}

@Injectable({ providedIn: 'root' })
export class NotificationService {
  private http = inject(HttpClient);
  private url = `${environment.apiUrl}/notifications`;
  getByRecipient(userId: string): Observable<Notification[]> {
    return this.http.get<Notification[]>(`${this.url}/recipient/${userId}`);
  }
  getUnread(userId: string): Observable<Notification[]> {
    return this.http.get<Notification[]>(`${this.url}/unread/${userId}`);
  }
  markAsRead(id: string): Observable<void> { return this.http.patch<void>(`${this.url}/${id}/read`, {}); }
  markAllAsRead(userId: string): Observable<void> {
    return this.http.patch<void>(`${this.url}/read-all/${userId}`, {});
  }
}
