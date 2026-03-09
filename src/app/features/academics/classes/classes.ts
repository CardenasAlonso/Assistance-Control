import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LucideAngularModule, Plus, Search, BookOpen, Trash2, X } from 'lucide-angular';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-classes', standalone: true,
  imports: [CommonModule, FormsModule, LucideAngularModule],
  templateUrl: './classes.html'
})
export class Classes {
  readonly PlusIcon = Plus; readonly SearchIcon = Search; readonly BookIcon = BookOpen;
  readonly TrashIcon = Trash2; readonly XIcon = X;

  showModal = signal(false); searchQuery = ''; filterYear = '2025';
  form: any = { course: '', teacher: '', section: '', hours: 4 };

  assignments = [
    { id: 'a1', course: 'Matemática', teacher: 'Prof. García López', section: '3°A', year: 2025, hours: 5, active: true },
    { id: 'a2', course: 'Comunicación', teacher: 'Prof. Torres Vidal', section: '3°A', year: 2025, hours: 4, active: true },
    { id: 'a3', course: 'Historia', teacher: 'Prof. Ramírez Quispe', section: '3°A', year: 2025, hours: 3, active: true },
    { id: 'a4', course: 'Matemática', teacher: 'Prof. García López', section: '3°B', year: 2025, hours: 5, active: true },
    { id: 'a5', course: 'Ciencias', teacher: 'Prof. Flores Condori', section: '4°A', year: 2025, hours: 4, active: true },
    { id: 'a6', course: 'Inglés', teacher: 'Prof. Huanca Mamani', section: '4°A', year: 2025, hours: 3, active: false },
  ];

  filtered = [...this.assignments];

  filter() {
    const q = this.searchQuery.toLowerCase();
    this.filtered = this.assignments.filter(a => a.course.toLowerCase().includes(q) || a.teacher.toLowerCase().includes(q));
  }

  save() {
    if (!this.form.course || !this.form.section) { Swal.fire('Atención', 'Completa todos los campos.', 'warning'); return; }
    this.assignments.push({ ...this.form, id: 'a' + Date.now(), year: parseInt(this.filterYear), active: true });
    this.filtered = [...this.assignments];
    this.showModal.set(false);
    Swal.fire({ title: 'Asignación creada', icon: 'success', timer: 1500, showConfirmButton: false });
  }

  delete(id: string) {
    Swal.fire({ title: '¿Eliminar?', icon: 'warning', showCancelButton: true, confirmButtonColor: 'var(--cv-danger)', cancelButtonText: 'Cancelar', confirmButtonText: 'Eliminar' })
      .then(r => { if (r.isConfirmed) { this.assignments.splice(this.assignments.findIndex(a => a.id === id), 1); this.filtered = [...this.assignments]; } });
  }

  view(a: any) { Swal.fire({ title: a.course, html: `<p>Docente: <b>${a.teacher}</b></p><p>Sección: <b>${a.section}</b></p><p>Horas/semana: <b>${a.hours}</b></p>`, icon: 'info' }); }
}
