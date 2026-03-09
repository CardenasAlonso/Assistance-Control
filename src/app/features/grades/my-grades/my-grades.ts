import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LucideAngularModule, BookMarked, Award, BarChart3, TrendingUp, ChevronDown, ChevronRight } from 'lucide-angular';

interface SubjectGrade {
  id: string; name: string; teacher: string; color: string;
  competencies: CompetencyRow[];
  expanded: boolean;
}
interface CompetencyRow {
  name: string; tasks: TaskScore[];
  bim1: number | null; bim2: number | null; bim3: number | null; bim4: number | null;
}
interface TaskScore { title: string; score: number; date: string; }

@Component({
  selector: 'app-my-grades', standalone: true,
  imports: [CommonModule, LucideAngularModule],
  templateUrl: './my-grades.html', styleUrl: './my-grades.scss'
})
export class MyGrades implements OnInit {
  readonly BookIcon = BookMarked; readonly AwardIcon = Award;
  readonly ChartIcon = BarChart3; readonly TrendIcon = TrendingUp;
  readonly ChevDownIcon = ChevronDown; readonly ChevRightIcon = ChevronRight;

  userName = '';
  activePeriod = '2.º Bimestre';

  subjects = signal<SubjectGrade[]>([
    {
      id: '1', name: 'Matemática', teacher: 'Prof. Ramírez', color: '#1A2E5B', expanded: false, competencies: [
        { name: 'Resuelve problemas de cantidad', tasks: [{ title: 'Examen Bim 1', score: 16, date: '2025-04-10' }, { title: 'Práctica N.º 1', score: 14, date: '2025-03-20' }], bim1: 15, bim2: 16, bim3: null, bim4: null },
        { name: 'Resuelve problemas de forma, movimiento y localización', tasks: [{ title: 'Trabajo grupal', score: 17, date: '2025-03-28' }], bim1: 17, bim2: null, bim3: null, bim4: null },
      ]
    },
    {
      id: '2', name: 'Comunicación', teacher: 'Prof. Torres', color: '#1B6E3C', expanded: false, competencies: [
        { name: 'Se comunica oralmente', tasks: [{ title: 'Exposición oral', score: 18, date: '2025-04-05' }], bim1: 17, bim2: 18, bim3: null, bim4: null },
        { name: 'Lee diversos textos', tasks: [{ title: 'Comprensión lectora', score: 16, date: '2025-03-15' }], bim1: 16, bim2: null, bim3: null, bim4: null },
      ]
    },
    {
      id: '3', name: 'Historia, Geografía y Economía', teacher: 'Prof. Quispe', color: '#8B1A2B', expanded: false, competencies: [
        { name: 'Construye interpretaciones históricas', tasks: [{ title: 'Examen Inca', score: 14, date: '2025-04-12' }], bim1: 14, bim2: null, bim3: null, bim4: null },
      ]
    },
    {
      id: '4', name: 'Ciencia y Tecnología', teacher: 'Prof. Vega', color: '#C4962A', expanded: false, competencies: [
        { name: 'Explica el mundo físico', tasks: [{ title: 'Laboratorio N.º 1', score: 13, date: '2025-03-22' }, { title: 'Práctica de Laboratorio', score: 15, date: '2025-04-08' }], bim1: 14, bim2: null, bim3: null, bim4: null },
      ]
    },
  ]);

  ngOnInit() { this.userName = localStorage.getItem('userName') || 'Estudiante'; }

  getLevel(score: number | null): string {
    if (score === null) return '–';
    if (score >= 18) return 'AD';
    if (score >= 14) return 'A';
    if (score >= 11) return 'B';
    return 'C';
  }
  getLevelClass(score: number | null): string {
    if (score === null) return '';
    if (score >= 18) return 'level-DESTACADO';
    if (score >= 14) return 'level-LOGRADO';
    if (score >= 11) return 'level-EN_PROCESO';
    return 'level-EN_INICIO';
  }
  getSubjectAvg(s: SubjectGrade): number {
    const scores: number[] = [];
    s.competencies.forEach(c => [c.bim1, c.bim2, c.bim3, c.bim4].forEach(v => { if (v !== null) scores.push(v); }));
    if (!scores.length) return 0;
    return Math.round((scores.reduce((a, b) => a + b, 0) / scores.length) * 10) / 10;
  }
  toggleSubject(s: SubjectGrade) { s.expanded = !s.expanded; }
}
