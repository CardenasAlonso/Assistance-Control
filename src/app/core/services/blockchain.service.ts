import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class BlockchainService {
  private http = inject(HttpClient);
  private url = `${environment.apiUrl}/blockchain`;

  getAll(): Observable<any[]> { return this.http.get<any[]>(this.url); }
  getBlock(id: string): Observable<any> { return this.http.get<any>(`${this.url}/${id}`); }
  verify(): Observable<{valid: boolean; totalBlocks: number}> {
    return this.http.get<{valid:boolean;totalBlocks:number}>(`${this.url}/verify`);
  }
}
