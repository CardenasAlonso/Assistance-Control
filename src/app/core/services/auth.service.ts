import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { environment } from '../../../environments/environment';

export interface LoginCommand { email: string; password: string; }
export interface TokenResponse {
  token: string; refreshToken: string;
  userId: string; role: string; expiresIn: number;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private http = inject(HttpClient);
  private router = inject(Router);
  private currentUserSubject = new BehaviorSubject<TokenResponse | null>(this.getStoredUser());
  currentUser$ = this.currentUserSubject.asObservable();

  login(cmd: LoginCommand): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(`${environment.apiUrl}/auth/login`, cmd).pipe(
      tap(response => {
        localStorage.setItem('token', response.token);
        localStorage.setItem('role', response.role);
        localStorage.setItem('userId', response.userId);
        this.currentUserSubject.next(response);
      })
    );
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    localStorage.removeItem('userId');
    this.currentUserSubject.next(null);
    this.router.navigate(['/login']);
  }

  getToken(): string | null { return localStorage.getItem('token'); }
  getRole(): string | null { return localStorage.getItem('role'); }
  getUserId(): string | null { return localStorage.getItem('userId'); }
  isAuthenticated(): boolean { return !!this.getToken(); }
  hasRole(...roles: string[]): boolean {
    const role = this.getRole();
    return role ? roles.includes(role) : false;
  }

  private getStoredUser(): TokenResponse | null {
    const token = localStorage.getItem('token');
    const role = localStorage.getItem('role');
    const userId = localStorage.getItem('userId');
    if (token && role && userId) return { token, role, userId, refreshToken: '', expiresIn: 0 };
    return null;
  }
}
