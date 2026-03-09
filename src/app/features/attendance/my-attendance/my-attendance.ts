import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LucideAngularModule, Calendar, CheckCircle, XCircle, Clock, AlertTriangle } from 'lucide-angular';

@Component({ selector: 'app-my-attendance', standalone: true, imports: [CommonModule, LucideAngularModule], templateUrl: './my-attendance.html', styleUrl: './my-attendance.scss' })
export class MyAttendance implements OnInit {
  readonly CalIcon = Calendar; readonly CheckIcon = CheckCircle; readonly XIcon = XCircle;
  readonly ClockIcon = Clock; readonly AlertIcon = AlertTriangle;
  userName = ''; selectedMonth = '2025-04';
  records = signal([
    { date: '2025-04-28', day: 'Lun', status: 'PRESENT', time: '07:45' },
    { date: '2025-04-25', day: 'Vie', status: 'PRESENT', time: '07:52' },
    { date: '2025-04-24', day: 'Jue', status: 'LATE', time: '08:15' },
    { date: '2025-04-23', day: 'Mié', status: 'PRESENT', time: '07:40' },
    { date: '2025-04-22', day: 'Mar', status: 'PRESENT', time: '07:38' },
    { date: '2025-04-21', day: 'Lun', status: 'ABSENT', time: '–' },
    { date: '2025-04-18', day: 'Vie', status: 'PRESENT', time: '07:50' },
    { date: '2025-04-17', day: 'Jue', status: 'PRESENT', time: '07:48' },
    { date: '2025-04-16', day: 'Mié', status: 'EXCUSED', time: '–' },
    { date: '2025-04-15', day: 'Mar', status: 'PRESENT', time: '07:41' },
    { date: '2025-04-14', day: 'Lun', status: 'PRESENT', time: '07:39' },
    { date: '2025-04-11', day: 'Vie', status: 'LATE', time: '08:05' },
    { date: '2025-04-10', day: 'Jue', status: 'PRESENT', time: '07:45' },
    { date: '2025-04-09', day: 'Mié', status: 'PRESENT', time: '07:47' },
    { date: '2025-04-08', day: 'Mar', status: 'PRESENT', time: '07:43' },
  ]);
  ngOnInit() { this.userName = localStorage.getItem('userName') || 'Estudiante'; }
  get present() { return this.records().filter(r => r.status === 'PRESENT').length; }
  get absent() { return this.records().filter(r => r.status === 'ABSENT').length; }
  get late() { return this.records().filter(r => r.status === 'LATE').length; }
  get excused() { return this.records().filter(r => r.status === 'EXCUSED').length; }
  get pct() { return Math.round((this.present + this.late) / (this.records().length || 1) * 100); }
  statusLabel(s: string): string { const l: any = { PRESENT: 'Presente', ABSENT: 'Ausente', LATE: 'Tardanza', EXCUSED: 'Justificado' }; return l[s] ?? s; }
  statusClass(s: string): string { const c: any = { PRESENT: 'badge-success', ABSENT: 'badge-danger', LATE: 'badge-warning', EXCUSED: 'badge-info' }; return c[s] ?? ''; }
}
