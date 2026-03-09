import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LucideAngularModule, Users, CheckCircle, X, Clock, Download, Search } from 'lucide-angular';

@Component({
  selector: 'app-attendance-dashboard', standalone: true,
  imports: [CommonModule, FormsModule, LucideAngularModule],
  templateUrl: './attendance-dashboard.html'
})
export class AttendanceDashboard {
  readonly UsersIcon = Users; readonly CheckIcon = CheckCircle; readonly XIcon = X;
  readonly ClockIcon = Clock; readonly DownloadIcon = Download; readonly SearchIcon = Search;

  today = new Date().toLocaleDateString('es-PE', { weekday: 'long', day: 'numeric', month: 'long', year: 'numeric' });
  selectedDate = 'today'; searchQuery = '';

  sections = [
    { name: '3°A', pct: 97, present: 34, total: 35 }, { name: '3°B', pct: 91, present: 32, total: 35 },
    { name: '4°A', pct: 94, present: 33, total: 35 }, { name: '4°B', pct: 89, present: 31, total: 35 },
    { name: '5°A', pct: 100, present: 35, total: 35 }, { name: '5°B', pct: 83, present: 29, total: 35 },
  ];

  absentAlert = [
    { name: 'Luis Ccopa Flores', section: '3°B', absences: 8 },
    { name: 'Pedro Quispe Layme', section: '4°A', absences: 6 },
    { name: 'José Mamani Torres', section: '3°A', absences: 5 },
    { name: 'Miguel Ramos C.', section: '5°B', absences: 4 },
  ];

  records = [
    { name: 'Carlos Huamán Quispe', section: '3°A', time: '07:32', method: 'QR', status: 'PRESENT' },
    { name: 'Ángel Vásquez Díaz', section: '3°B', time: '07:28', method: 'QR', status: 'PRESENT' },
    { name: 'José Mamani Torres', section: '3°A', time: '07:51', method: 'Manual', status: 'LATE' },
    { name: 'Luis Ccopa Flores', section: '3°B', time: '—', method: '—', status: 'ABSENT' },
    { name: 'Miguel Ramos Condori', section: '4°A', time: '07:29', method: 'QR', status: 'PRESENT' },
  ];

  filteredRecords = [...this.records];

  filterRecords() {
    const q = this.searchQuery.toLowerCase();
    this.filteredRecords = this.records.filter(r => r.name.toLowerCase().includes(q));
  }

  getStatusLabel(s: string): string { return { PRESENT: 'Presente', ABSENT: 'Ausente', LATE: 'Tardanza' }[s] || s; }
}
