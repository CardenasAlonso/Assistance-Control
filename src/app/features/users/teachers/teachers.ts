import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { LucideAngularModule, Plus, Search, RefreshCw, Edit2, Trash2, X } from 'lucide-angular';
import { TeacherService } from '../../../core/services/teacher.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-teachers', standalone: true,
  imports: [CommonModule, FormsModule, MatProgressSpinnerModule, LucideAngularModule],
  templateUrl: './teachers.html'
})
export class Teachers implements OnInit {
  readonly PlusIcon = Plus; readonly SearchIcon = Search; readonly RefreshIcon = RefreshCw;
  readonly EditIcon = Edit2; readonly TrashIcon = Trash2; readonly XIcon = X;

  loading = signal(false); showModal = signal(false); isEditing = signal(false);
  searchQuery = ''; filterStatus = 'all';

  teachers = signal<any[]>([]);
  filteredTeachers = signal<any[]>([]);
  form: any = { firstName: '', lastName: '', documentNumber: '', email: '', speciality: '' };
  editingId = '';

  mockData = [
    { id: 't1', firstName: 'Juan', lastName: 'García López', documentNumber: '12345678', email: 'jgarcia@ie20188.edu.pe', speciality: 'Matemática', status: 'ACTIVO' },
    { id: 't2', firstName: 'María', lastName: 'Torres Vidal', documentNumber: '23456789', email: 'mtorres@ie20188.edu.pe', speciality: 'Comunicación', status: 'ACTIVO' },
    { id: 't3', firstName: 'Carlos', lastName: 'Ramírez Quispe', documentNumber: '34567890', email: 'cramirez@ie20188.edu.pe', speciality: 'Historia', status: 'ACTIVO' },
    { id: 't4', firstName: 'Ana', lastName: 'Flores Condori', documentNumber: '45678901', email: 'aflores@ie20188.edu.pe', speciality: 'Ciencias', status: 'ACTIVO' },
    { id: 't5', firstName: 'Luis', lastName: 'Huanca Mamani', documentNumber: '56789012', email: 'lhuanca@ie20188.edu.pe', speciality: 'Inglés', status: 'INACTIVO' },
  ];

  constructor(private teacherSvc: TeacherService) { }

  ngOnInit() { this.loadData(); }

  loadData() {
    this.loading.set(true);
    this.teacherSvc.getAll().subscribe({
      next: (d: any[]) => { this.teachers.set(d); this.applyFilter(); this.loading.set(false); },
      error: () => { this.teachers.set(this.mockData); this.filteredTeachers.set(this.mockData); this.loading.set(false); }
    });
  }

  applyFilter() {
    let d = [...this.teachers()];
    if (this.filterStatus !== 'all') d = d.filter(t => t.status === this.filterStatus);
    if (this.searchQuery) {
      const q = this.searchQuery.toLowerCase();
      d = d.filter(t => t.firstName.toLowerCase().includes(q) || t.lastName.toLowerCase().includes(q) || t.documentNumber.includes(q));
    }
    this.filteredTeachers.set(d);
  }

  openAdd() { this.form = { firstName: '', lastName: '', documentNumber: '', email: '', speciality: '' }; this.isEditing.set(false); this.showModal.set(true); }
  openEdit(t: any) { this.form = { ...t }; this.editingId = t.id; this.isEditing.set(true); this.showModal.set(true); }

  save() {
    if (!this.form.firstName || !this.form.documentNumber) { Swal.fire('Atención', 'Completa los campos obligatorios.', 'warning'); return; }
    this.teachers.update(prev =>
      this.isEditing() ? prev.map(t => t.id === this.editingId ? { ...t, ...this.form } : t)
        : [...prev, { ...this.form, id: 'tmp-' + Date.now(), status: 'ACTIVO' }]
    );
    this.applyFilter(); this.showModal.set(false);
    Swal.fire({ title: this.isEditing() ? 'Actualizado' : 'Creado', icon: 'success', timer: 1500, showConfirmButton: false });
  }

  delete(id: string) {
    Swal.fire({ title: '¿Eliminar docente?', icon: 'warning', showCancelButton: true, confirmButtonColor: 'var(--cv-danger)', confirmButtonText: 'Eliminar', cancelButtonText: 'Cancelar' })
      .then(r => { if (r.isConfirmed) { this.teachers.update(prev => prev.filter(t => t.id !== id)); this.applyFilter(); } });
  }
}
