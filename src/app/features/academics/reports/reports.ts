import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LucideAngularModule, FileText, BarChart3, Users, Calendar, BookOpen, Download } from 'lucide-angular';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-reports', standalone: true,
  imports: [CommonModule, FormsModule, LucideAngularModule],
  templateUrl: './reports.html'
})
export class Reports {
  filters = { period: 'B1', section: 'all', from: '', to: '' };

  reportTypes = [
    { title: 'Reporte de Asistencia', desc: 'Resumen por sección y período', icon: Calendar, bg: '#e8edfa', color: 'var(--cv-navy)', format: 'PDF/Excel', hover: false },
    { title: 'Boleta de Notas', desc: 'Notas por alumno y competencia', icon: FileText, bg: 'var(--cv-gold-tint)', color: 'var(--cv-gold)', format: 'PDF', hover: false },
    { title: 'Estadísticas Generales', desc: 'Promedios, asistencia y más', icon: BarChart3, bg: 'var(--cv-success-tint)', color: 'var(--cv-success)', format: 'PDF', hover: false },
    { title: 'Nómina de Estudiantes', desc: 'Lista completa por sección', icon: Users, bg: 'var(--cv-info-tint)', color: 'var(--cv-info)', format: 'Excel', hover: false },
    { title: 'Registro de Calificaciones', desc: 'Notas por curso y bimestre', icon: BookOpen, bg: 'var(--cv-warning-tint)', color: 'var(--cv-warning)', format: 'Excel', hover: false },
    { title: 'Informe de Rendimiento', desc: 'Análisis de niveles de logro', icon: BarChart3, bg: 'var(--cv-danger-tint)', color: 'var(--cv-danger)', format: 'PDF', hover: false },
  ];

  generate(r: any) {
    Swal.fire({ title: 'Generando reporte...', timer: 1800, didOpen: () => Swal.showLoading() })
      .then(() => Swal.fire({ title: `✓ ${r.title} generado`, text: 'El archivo está listo para descargar.', icon: 'success', confirmButtonText: 'Descargar', confirmButtonColor: 'var(--cv-navy)' }));
  }
}
