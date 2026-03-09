import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { LucideAngularModule, Plus, Search, RefreshCw, Edit2, Trash2, X, GraduationCap } from 'lucide-angular';
import { StudentService, Student } from '../../../core/services/student.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-students', standalone: true,
  imports: [CommonModule, FormsModule, MatProgressSpinnerModule, LucideAngularModule],
  templateUrl: './students.html', styleUrl: './students.scss'
})
export class Students implements OnInit {
  readonly PlusIcon = Plus; readonly SearchIcon = Search; readonly RefreshIcon = RefreshCw;
  readonly EditIcon = Edit2; readonly TrashIcon = Trash2; readonly XIcon = X; readonly GradIcon = GraduationCap;

  loading = signal(false); showModal = signal(false); isEditing = signal(false);
  searchQuery = ''; filterStatus = 'all'; filterSection = 'all';

  students = signal<Student[]>([]);
  filteredStudents = signal<Student[]>([]);

  form: Student = { firstName: '', lastName: '', documentNumber: '', sectionId: '' };
  editingId = '';

  mockStudents: Student[] = [
    { id: 's1', firstName: 'Carlos', lastName: 'Huamán Quispe', documentNumber: '72345678', sectionId: '3°A', email: 'carlos@demo.pe', status: 'ACTIVO' },
    { id: 's2', firstName: 'José', lastName: 'Mamani Torres', documentNumber: '73456789', sectionId: '3°A', email: 'jose@demo.pe', status: 'ACTIVO' },
    { id: 's3', firstName: 'Luis', lastName: 'Ccopa Flores', documentNumber: '74567890', sectionId: '3°B', email: 'luis@demo.pe', status: 'ACTIVO' },
    { id: 's4', firstName: 'Ángel', lastName: 'Vásquez Díaz', documentNumber: '75678901', sectionId: '3°B', email: 'angel@demo.pe', status: 'ACTIVO' },
    { id: 's5', firstName: 'Miguel', lastName: 'Ramos Condori', documentNumber: '76789012', sectionId: '4°A', email: 'miguel@demo.pe', status: 'ACTIVO' },
    { id: 's6', firstName: 'Pedro', lastName: 'Quispe Layme', documentNumber: '77890123', sectionId: '4°A', email: 'pedro@demo.pe', status: 'INACTIVO' },
    { id: 's7', firstName: 'Raúl', lastName: 'Flores Cóndor', documentNumber: '78901234', sectionId: '5°A', email: 'raul@demo.pe', status: 'ACTIVO' },
    { id: 's8', firstName: 'Diego', lastName: 'Chura Apaza', documentNumber: '79012345', sectionId: '5°B', email: 'diego@demo.pe', status: 'ACTIVO' },
  ];

  constructor(private studentSvc: StudentService) { }

  ngOnInit() { this.loadData(); }

  loadData() {
    this.loading.set(true);
    this.studentSvc.getAll().subscribe({
      next: data => { this.students.set(data); this.applyFilter(); this.loading.set(false); },
      error: () => { this.students.set(this.mockStudents); this.filteredStudents.set(this.mockStudents); this.loading.set(false); }
    });
  }

  applyFilter() {
    let data = [...this.students()];
    if (this.filterStatus !== 'all') data = data.filter(s => s.status === this.filterStatus);
    if (this.filterSection !== 'all') data = data.filter(s => s.sectionId === this.filterSection);
    if (this.searchQuery) {
      const q = this.searchQuery.toLowerCase();
      data = data.filter(s => s.firstName.toLowerCase().includes(q) || s.lastName.toLowerCase().includes(q) || s.documentNumber.includes(q));
    }
    this.filteredStudents.set(data);
  }

  openAdd() { this.form = { firstName: '', lastName: '', documentNumber: '', sectionId: '' }; this.isEditing.set(false); this.showModal.set(true); }
  openEdit(s: Student) { this.form = { ...s }; this.editingId = s.id!; this.isEditing.set(true); this.showModal.set(true); }

  save() {
    if (!this.form.firstName || !this.form.lastName || !this.form.documentNumber) {
      Swal.fire('Atención', 'Completa los campos obligatorios.', 'warning'); return;
    }
    if (this.isEditing()) {
      this.studentSvc.update(this.editingId, this.form).subscribe({
        next: () => { this.loadData(); this.showModal.set(false); Swal.fire({ title: 'Actualizado', icon: 'success', timer: 1500, showConfirmButton: false }); },
        error: () => {
          this.students.update(prev => prev.map(s => s.id === this.editingId ? { ...s, ...this.form } : s));
          this.applyFilter(); this.showModal.set(false);
        }
      });
    } else {
      this.studentSvc.create(this.form).subscribe({
        next: () => { this.loadData(); this.showModal.set(false); Swal.fire({ title: 'Creado', icon: 'success', timer: 1500, showConfirmButton: false }); },
        error: () => {
          this.students.update(prev => [...prev, { ...this.form, id: 'tmp-' + Date.now(), status: 'ACTIVO' }]);
          this.applyFilter(); this.showModal.set(false);
        }
      });
    }
  }

  delete(id: string) {
    Swal.fire({ title: '¿Eliminar estudiante?', text: 'Esta acción no se puede deshacer.', icon: 'warning', showCancelButton: true, confirmButtonColor: 'var(--cv-danger)', cancelButtonText: 'Cancelar', confirmButtonText: 'Eliminar' })
      .then(r => {
        if (r.isConfirmed) {
          this.studentSvc.delete(id).subscribe({
            next: () => this.loadData(),
            error: () => { this.students.update(prev => prev.filter(s => s.id !== id)); this.applyFilter(); }
          });
        }
      });
  }

  getInitials(s: Student): string { return (s.firstName.charAt(0) + s.lastName.charAt(0)).toUpperCase(); }
}
