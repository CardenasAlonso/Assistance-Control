import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LucideAngularModule, Search, X } from 'lucide-angular';

@Component({
  selector: 'app-student-grades', standalone: true,
  imports: [CommonModule, FormsModule, LucideAngularModule],
  templateUrl: './student-grades.html'
})
export class StudentGrades {
  readonly SearchIcon = Search; readonly XIcon = X;
  searchQuery = ''; selectedPeriod = '';
  selectedStudent: any = null;

  students = [
    { id: 's1', firstName: 'Carlos', lastName: 'Huamán Quispe', dni: '72345678', section: '3°A', avg: 16.8 },
    { id: 's2', firstName: 'José', lastName: 'Mamani Torres', dni: '73456789', section: '3°A', avg: 14.2 },
    { id: 's3', firstName: 'Luis', lastName: 'Ccopa Flores', dni: '74567890', section: '3°B', avg: 10.5 },
    { id: 's4', firstName: 'Ángel', lastName: 'Vásquez Díaz', dni: '75678901', section: '3°B', avg: 18.4 },
    { id: 's5', firstName: 'Miguel', lastName: 'Ramos Condori', dni: '76789012', section: '4°A', avg: 12.7 },
  ];

  filteredStudents = [...this.students];

  studentCourses = [
    {
      name: 'Matemática', teacher: 'Prof. García', avg: 17, scores: [
        { competency: 'Resuelve problemas de cantidad', task: 'Examen Bimestral', period: 'B1', score: 17 },
        { competency: 'Regularidad, equivalencia y cambio', task: 'Práctica 1', period: 'B1', score: 16 },
      ]
    },
    {
      name: 'Comunicación', teacher: 'Prof. Torres', avg: 15, scores: [
        { competency: 'Lee diversos tipos de textos', task: 'Comprensión lectora', period: 'B1', score: 15 },
        { competency: 'Se comunica oralmente', task: 'Exposición', period: 'B1', score: 16 },
      ]
    },
  ];

  filterStudents() {
    const q = this.searchQuery.toLowerCase();
    this.filteredStudents = this.students.filter(s =>
      s.firstName.toLowerCase().includes(q) || s.lastName.toLowerCase().includes(q) || s.dni.includes(q)
    );
  }

  selectStudent(s: any) { this.selectedStudent = s; }

  getPerf(n: number): string { if (n >= 18) return 'gold'; if (n >= 14) return 'green'; if (n >= 11) return 'orange'; return 'red'; }
  getPerfLabel(n: number): string { if (n >= 18) return '★ Destacado'; if (n >= 14) return 'Logrado'; if (n >= 11) return 'En Proceso'; return 'En Inicio'; }
}
