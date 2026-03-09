import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import {
  LucideAngularModule, GraduationCap, Users, Calendar, BarChart3,
  BookOpen, ClipboardList, QrCode, MessageSquare, Award,
  AlertTriangle, CheckCircle, ShieldCheck, BookMarked, TrendingUp
} from 'lucide-angular';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-dashboard', standalone: true,
  imports: [CommonModule, LucideAngularModule],
  templateUrl: './dashboard.html', styleUrl: './dashboard.scss'
})
export class Dashboard implements OnInit {
  userRole = 'STUDENT';
  today = new Date().toLocaleDateString('es-PE', { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' });

  studentScores = [
    { subject: 'Matemática', score: 17 }, { subject: 'Comunicación', score: 15 },
    { subject: 'Historia', score: 18 }, { subject: 'Ciencias', score: 16 },
    { subject: 'Inglés', score: 14 }, { subject: 'Ed. Física', score: 19 },
  ];
  studentAlerts = [
    { type: 'warning', text: '1 falta sin justificar', sub: 'Matemática · 03 Mar' },
    { type: 'success', text: 'Promedio 16.5 — Logrado', sub: 'Período actual' },
    { type: 'info', text: 'Evaluación el viernes', sub: 'Historia del Perú' },
  ];
  teacherActions = [
    { label: 'Registrar Asistencia', sub: '3 clases hoy', route: 'record-attendance', bg: '#e8edfa', color: 'var(--cv-navy)', icon: ClipboardList },
    { label: 'Ingresar Notas', sub: '2 tareas pendientes', route: 'grades', bg: 'var(--cv-gold-tint)', color: 'var(--cv-gold)', icon: BookMarked },
    { label: 'Ver Mis Alumnos', sub: '45 estudiantes', route: 'students', bg: 'var(--cv-success-tint)', color: 'var(--cv-success)', icon: Users },
    { label: 'Asistente IA MINEDU', sub: 'Consultar currículo', route: 'chat', bg: 'var(--cv-info-tint)', color: 'var(--cv-info)', icon: MessageSquare },
  ];
  adminActions = [
    { label: 'Gestionar Estudiantes', sub: '450 registrados', route: 'students', bg: '#e8edfa', color: 'var(--cv-navy)', icon: GraduationCap },
    { label: 'Control de Entrada', sub: 'QR y Manual', route: 'entrance', bg: 'var(--cv-gold-tint)', color: 'var(--cv-gold)', icon: QrCode },
    { label: 'Ver Asistencia', sub: 'Reporte del día', route: 'attendance-dashboard', bg: 'var(--cv-success-tint)', color: 'var(--cv-success)', icon: Calendar },
    { label: 'Calificaciones', sub: 'Por competencia', route: 'student-grades', bg: 'var(--cv-info-tint)', color: 'var(--cv-info)', icon: BookOpen },
    { label: 'Reportes', sub: 'PDF y Excel', route: 'reports', bg: 'var(--cv-warning-tint)', color: 'var(--cv-warning)', icon: BarChart3 },
    { label: 'Blockchain', sub: 'Integridad datos', route: 'blockchain', bg: 'var(--cv-danger-tint)', color: 'var(--cv-danger)', icon: ShieldCheck },
  ];
  systemAlerts = [
    { type: 'danger', text: '12 alumnos con +3 faltas', sub: 'Requieren seguimiento' },
    { type: 'warning', text: '5 justificaciones pendientes', sub: 'En espera de revisión' },
    { type: 'info', text: 'Cierre de notas en 7 días', sub: '1° Bimestre 2025' },
    { type: 'success', text: 'Asistencia del día: 96.2%', sub: '433 de 450 presentes' },
  ];
  attendanceSummary = [
    { label: 'Presentes', pct: 96, color: 'var(--cv-success)' },
    { label: 'Tardanzas', pct: 2, color: 'var(--cv-warning)' },
    { label: 'Ausentes', pct: 2, color: 'var(--cv-danger)' },
  ];

  constructor(private auth: AuthService, private router: Router) { }
  ngOnInit() { this.userRole = this.auth.getRole() || 'STUDENT'; }

  getGreetingName() { return this.getRoleLabel(this.userRole); }
  getRoleDesc() {
    return { STUDENT: 'Portal Estudiantil', TEACHER: 'Panel Docente', COORDINATOR: 'Panel de Coordinación', ADMIN: 'Panel Administrativo', GUARDIAN: 'Portal del Apoderado' }[this.userRole] || '';
  }
  getRoleLabel(r: string) {
    return { STUDENT: 'Estudiante', TEACHER: 'Docente', COORDINATOR: 'Coordinador', ADMIN: 'Administrador' }[r] || r;
  }
  getWelcomeIcon() {
    return { STUDENT: GraduationCap, TEACHER: BookOpen, COORDINATOR: BarChart3, ADMIN: ShieldCheck }[this.userRole] || Award;
  }
  getStats(): any[] {
    if (this.userRole === 'STUDENT') return [
      { label: 'Promedio General', value: '16.5', sub: 'Logrado', color: 'gold', icon: Award },
      { label: 'Asistencia', value: '94%', sub: 'Excelente', color: 'green', icon: Calendar },
      { label: 'Mis Cursos', value: '6', sub: 'Este ciclo', color: 'navy', icon: BookOpen },
      { label: 'Evaluaciones', value: '3', sub: 'Esta semana', color: 'orange', icon: ClipboardList },
    ];
    if (this.userRole === 'TEACHER') return [
      { label: 'Mis Asignaciones', value: '3', sub: 'Activas', color: 'navy', icon: BookOpen },
      { label: 'Total Alumnos', value: '45', sub: 'Registrados', color: 'gold', icon: Users },
      { label: 'Notas Ingresadas', value: '128', sub: 'Esta semana', color: 'green', icon: CheckCircle },
      { label: 'Asistencia Hoy', value: '96%', sub: 'Promedio', color: 'blue', icon: TrendingUp },
    ];
    return [
      { label: 'Total Estudiantes', value: '450', sub: 'Matriculados', color: 'navy', icon: GraduationCap },
      { label: 'Docentes', value: '32', sub: 'Activos', color: 'gold', icon: Users },
      { label: 'Asistencia Hoy', value: '96.2%', sub: 'Excelente', color: 'green', icon: Calendar },
      { label: 'Alertas Activas', value: '17', sub: 'Pendientes', color: 'red', icon: AlertTriangle },
    ];
  }
  go(route: string) { this.router.navigate([route]); }
}
