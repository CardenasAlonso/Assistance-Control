import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LucideAngularModule, Search, CheckCircle, XCircle, Clock, Shield, X } from 'lucide-angular';

@Component({
  selector: 'app-student-attendance', standalone: true,
  imports: [CommonModule, FormsModule, LucideAngularModule],
  templateUrl: './student-attendance.html'
})
export class StudentAttendance {
  readonly SearchIcon = Search; readonly CheckIcon = CheckCircle; readonly XCircleIcon = XCircle;
  readonly ClockIcon = Clock; readonly ShieldIcon = Shield; readonly XIcon = X;

  searchQuery = ''; selectedPeriod = '';
  selectedStudent: any = null;

  students = [
    { id: 's1', firstName: 'Carlos', lastName: 'Huamán Quispe', dni: '72345678', section: '3°A', pct: 94, present: 40, absent: 2, late: 1 },
    { id: 's2', firstName: 'José', lastName: 'Mamani Torres', dni: '73456789', section: '3°A', pct: 88, present: 37, absent: 4, late: 2 },
    { id: 's3', firstName: 'Luis', lastName: 'Ccopa Flores', dni: '74567890', section: '3°B', pct: 72, present: 31, absent: 9, late: 3 },
    { id: 's4', firstName: 'Ángel', lastName: 'Vásquez Díaz', dni: '75678901', section: '3°B', pct: 97, present: 42, absent: 1, late: 0 },
    { id: 's5', firstName: 'Miguel', lastName: 'Ramos Condori', dni: '76789012', section: '4°A', pct: 85, present: 36, absent: 5, late: 2 },
  ];

  filteredStudents = [...this.students];

  detailRecords = [
    { date: '2025-03-10', time: '07:32', method: 'QR', status: 'PRESENT' },
    { date: '2025-03-11', time: '07:28', method: 'QR', status: 'PRESENT' },
    { date: '2025-03-12', time: '07:50', method: 'Manual', status: 'LATE' },
    { date: '2025-03-13', time: '—', method: '—', status: 'ABSENT' },
    { date: '2025-03-14', time: '07:29', method: 'QR', status: 'PRESENT' },
  ];

  filterStudents() {
    const q = this.searchQuery.toLowerCase();
    this.filteredStudents = this.students.filter(s =>
      s.firstName.toLowerCase().includes(q) || s.lastName.toLowerCase().includes(q) || s.dni.includes(q)
    );
  }

  selectStudent(s: any) { this.selectedStudent = s; }

  getStatusClass(s: string): string { return { PRESENT: 'green', ABSENT: 'red', LATE: 'orange', EXCUSED: 'blue' }[s] || 'gray'; }
  getStatusLabel(s: string): string { return { PRESENT: 'Presente', ABSENT: 'Ausente', LATE: 'Tardanza', EXCUSED: 'Justificada' }[s] || s; }
}
