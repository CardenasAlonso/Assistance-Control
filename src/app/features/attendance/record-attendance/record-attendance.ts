import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { LucideAngularModule, Save, RefreshCw, CheckCircle, X, Clock, Shield } from 'lucide-angular';
import Swal from 'sweetalert2';

interface AttEntry { studentId: string; firstName: string; lastName: string; documentNumber: string; status: string; note: string; }

@Component({
  selector: 'app-record-attendance', standalone: true,
  imports: [CommonModule, FormsModule, MatProgressSpinnerModule, LucideAngularModule],
  templateUrl: './record-attendance.html'
})
export class RecordAttendance implements OnInit {
  readonly SaveIcon = Save; readonly RefreshIcon = RefreshCw;
  readonly CheckIcon = CheckCircle; readonly XIcon = X; readonly ClockIcon = Clock; readonly ShieldIcon = Shield;

  loading = signal(false); saving = signal(false);
  selectedSection = ''; selectedAssignment = ''; attendanceDate = '';
  entries = signal<AttEntry[]>([]);

  sections = signal([
    { id: 's1', name: '3° A', turn: 'Mañana' }, { id: 's2', name: '3° B', turn: 'Mañana' },
    { id: 's3', name: '4° A', turn: 'Mañana' }, { id: 's4', name: '4° B', turn: 'Tarde' },
    { id: 's5', name: '5° A', turn: 'Mañana' }, { id: 's6', name: '5° B', turn: 'Tarde' },
  ]);

  mockAssignments = [
    { id: 'a1', label: 'Matemática' }, { id: 'a2', label: 'Comunicación' },
    { id: 'a3', label: 'Historia' }, { id: 'a4', label: 'Ciencias Nat.' },
  ];

  ngOnInit() {
    this.attendanceDate = new Date().toISOString().split('T')[0];
  }

  onSectionChange() {
    if (!this.selectedSection) { this.entries.set([]); return; }
    this.loading.set(true);
    setTimeout(() => {
      this.entries.set([
        { studentId: 's1', firstName: 'Carlos', lastName: 'Huamán Quispe', documentNumber: '72345678', status: 'PRESENT', note: '' },
        { studentId: 's2', firstName: 'José', lastName: 'Mamani Torres', documentNumber: '73456789', status: 'PRESENT', note: '' },
        { studentId: 's3', firstName: 'Luis', lastName: 'Ccopa Flores', documentNumber: '74567890', status: 'PRESENT', note: '' },
        { studentId: 's4', firstName: 'Ángel', lastName: 'Vásquez Díaz', documentNumber: '75678901', status: 'PRESENT', note: '' },
        { studentId: 's5', firstName: 'Miguel', lastName: 'Ramos Condori', documentNumber: '76789012', status: 'PRESENT', note: '' },
        { studentId: 's6', firstName: 'Pedro', lastName: 'Quispe Layme', documentNumber: '77890123', status: 'PRESENT', note: '' },
        { studentId: 's7', firstName: 'Raúl', lastName: 'Flores Cóndor', documentNumber: '78901234', status: 'PRESENT', note: '' },
        { studentId: 's8', firstName: 'Diego', lastName: 'Chura Apaza', documentNumber: '79012345', status: 'PRESENT', note: '' },
      ]);
      this.loading.set(false);
    }, 500);
  }

  setAll(status: string) { this.entries.update(e => e.map(x => ({ ...x, status }))); }
  setStatus(studentId: string, status: string) { this.entries.update(e => e.map(x => x.studentId === studentId ? { ...x, status } : x)); }

  getCount(status: string): number { return this.entries().filter(e => e.status === status).length; }

  save() {
    if (!this.selectedSection) { Swal.fire('Atención', 'Selecciona una sección.', 'warning'); return; }
    if (!this.attendanceDate) { Swal.fire('Atención', 'Selecciona la fecha.', 'warning'); return; }
    this.saving.set(true);
    setTimeout(() => {
      this.saving.set(false);
      Swal.fire({ title: '¡Asistencia registrada!', text: `${this.entries().length} registros guardados correctamente.`, icon: 'success', timer: 2000, showConfirmButton: false });
    }, 800);
  }
}
