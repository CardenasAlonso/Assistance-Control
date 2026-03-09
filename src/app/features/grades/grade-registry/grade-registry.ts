import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { LucideAngularModule, Plus, BookMarked, RefreshCw, Save, Trash2, X, BookOpen } from 'lucide-angular';
import Swal from 'sweetalert2';

interface ScoreEntry { studentId: string; firstName: string; lastName: string; documentNumber: string; score: number | null; comment: string; }
interface TaskForm { title: string; competencyId: string; dueDate: string; description: string; }

@Component({
  selector: 'app-grade-registry', standalone: true,
  imports: [CommonModule, FormsModule, MatProgressSpinnerModule, LucideAngularModule],
  templateUrl: './grade-registry.html'
})
export class GradeRegistry implements OnInit {
  readonly PlusIcon = Plus; readonly BookIcon = BookMarked; readonly RefreshIcon = RefreshCw;
  readonly SaveIcon = Save; readonly TrashIcon = Trash2; readonly XIcon = X; readonly BookOpenIcon = BookOpen;

  tab = signal<'record' | 'tasks' | 'view'>('record');
  loading = signal(false); saving = signal(false);
  showTaskModal = signal(false);

  selectedAssignment = ''; selectedPeriod = 'B1'; selectedTask = '';

  assignments = signal([
    { id: 'a1', label: 'Matemática — 3°A — 2025' },
    { id: 'a2', label: 'Matemática — 3°B — 2025' },
    { id: 'a3', label: 'Álgebra — 4°A — 2025' },
  ]);

  tasks = signal([
    { id: 't1', title: 'Examen Bimestral', competencyName: 'Resuelve problemas de cantidad', dueDate: '2025-03-28', isActive: true, courseAssignmentId: 'a1', competencyId: 'c1', academicPeriodId: 'B1' },
    { id: 't2', title: 'Práctica Calificada 1', competencyName: 'Regularidad, equivalencia y cambio', dueDate: '2025-03-15', isActive: true, courseAssignmentId: 'a1', competencyId: 'c2', academicPeriodId: 'B1' },
  ]);

  competencies = signal([
    { id: 'c1', name: 'Resuelve problemas de cantidad' },
    { id: 'c2', name: 'Regularidad, equivalencia y cambio' },
    { id: 'c3', name: 'Forma, movimiento y localización' },
    { id: 'c4', name: 'Gestión de datos e incertidumbre' },
  ]);

  entries = signal<ScoreEntry[]>([]);
  savedScores = signal<any[]>([]);

  taskForm: TaskForm = { title: '', competencyId: '', dueDate: '', description: '' };

  ngOnInit() { this.loadMockStudents(); }

  loadData() { if (this.selectedAssignment) this.loadMockStudents(); }

  onAssignmentChange() { this.loadMockStudents(); }

  loadMockStudents() {
    this.loading.set(true);
    setTimeout(() => {
      this.entries.set([
        { studentId: 's1', firstName: 'Carlos', lastName: 'Huamán Quispe', documentNumber: '72345678', score: null, comment: '' },
        { studentId: 's2', firstName: 'José', lastName: 'Mamani Torres', documentNumber: '73456789', score: null, comment: '' },
        { studentId: 's3', firstName: 'Luis', lastName: 'Ccopa Flores', documentNumber: '74567890', score: null, comment: '' },
        { studentId: 's4', firstName: 'Ángel', lastName: 'Vásquez Díaz', documentNumber: '75678901', score: null, comment: '' },
        { studentId: 's5', firstName: 'Miguel', lastName: 'Ramos Condori', documentNumber: '76789012', score: null, comment: '' },
        { studentId: 's6', firstName: 'Pedro', lastName: 'Quispe Layme', documentNumber: '77890123', score: null, comment: '' },
      ]);
      this.loading.set(false);
    }, 600);
  }

  saveScores() {
    if (!this.selectedTask) { Swal.fire('Atención', 'Selecciona una tarea/actividad primero.', 'warning'); return; }
    const missing = this.entries().filter(e => e.score === null);
    if (missing.length > 0) {
      Swal.fire({ title: '¿Guardar con vacíos?', text: `${missing.length} estudiantes sin nota. ¿Continuar?`, icon: 'question', showCancelButton: true, confirmButtonColor: 'var(--cv-navy)', confirmButtonText: 'Sí, guardar' }).then(r => { if (r.isConfirmed) this.doSave(); });
    } else { this.doSave(); }
  }

  private doSave() {
    this.saving.set(true);
    setTimeout(() => {
      const newScores = this.entries().filter(e => e.score !== null).map(e => ({
        id: Math.random().toString(36).slice(2), studentId: e.studentId,
        classTaskTitle: this.tasks().find(t => t.id === this.selectedTask)?.title || '',
        competencyName: this.tasks().find(t => t.id === this.selectedTask)?.competencyName || '',
        score: e.score, registeredAt: new Date().toISOString(), approved: (e.score || 0) >= 11,
      }));
      this.savedScores.update(prev => [...prev, ...newScores]);
      this.saving.set(false);
      Swal.fire({ title: '¡Notas guardadas!', text: `Se registraron ${newScores.length} calificaciones.`, icon: 'success', timer: 2000, showConfirmButton: false });
    }, 800);
  }

  openNewTask() { this.taskForm = { title: '', competencyId: '', dueDate: '', description: '' }; this.showTaskModal.set(true); }

  saveTask() {
    if (!this.taskForm.title || !this.taskForm.competencyId) { Swal.fire('Atención', 'Completa título y competencia.', 'warning'); return; }
    const comp = this.competencies().find(c => c.id === this.taskForm.competencyId);
    this.tasks.update(prev => [...prev, {
      id: 't' + Date.now(), title: this.taskForm.title, competencyName: comp?.name || '',
      dueDate: this.taskForm.dueDate, isActive: true, courseAssignmentId: this.selectedAssignment,
      competencyId: this.taskForm.competencyId, academicPeriodId: this.selectedPeriod,
    }]);
    this.showTaskModal.set(false);
    Swal.fire({ title: 'Tarea creada', icon: 'success', timer: 1800, showConfirmButton: false });
  }

  deleteTask(id: string) {
    Swal.fire({ title: '¿Eliminar tarea?', icon: 'warning', showCancelButton: true, confirmButtonColor: 'var(--cv-danger)', confirmButtonText: 'Sí, eliminar' })
      .then(r => { if (r.isConfirmed) this.tasks.update(prev => prev.filter(t => t.id !== id)); });
  }

  getPerfClass(s: number): string { if (s >= 18) return 'gold'; if (s >= 14) return 'green'; if (s >= 11) return 'orange'; return 'red'; }
  getPerfLabel(s: number): string { if (s >= 18) return '★ Destacado'; if (s >= 14) return 'Logrado'; if (s >= 11) return 'En Proceso'; return 'En Inicio'; }
}
