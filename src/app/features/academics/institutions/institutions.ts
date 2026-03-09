import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LucideAngularModule, Edit2, Save, Plus } from 'lucide-angular';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-institutions', standalone: true,
  imports: [CommonModule, FormsModule, LucideAngularModule],
  templateUrl: './institutions.html'
})
export class Institutions {
  readonly EditIcon = Edit2; readonly SaveIcon = Save; readonly PlusIcon = Plus;
  editing = false;

  institution: Record<string, string> = {
    name: 'I.E. Centro de Varones N°20188', codModular: '0493353',
    ugel: 'UGEL 08 - Cañete', dre: 'DRE Lima Provincias',
    address: 'Calle Bolívar 150, Cañete, Lima', phone: '01-5840123',
    email: 'ie20188.canete@gmail.com', director: 'Lic. Eduardo Morales Quispe',
  };

  fields = [
    { key: 'name', label: 'Nombre de la Institución' },
    { key: 'codModular', label: 'Código Modular' },
    { key: 'ugel', label: 'UGEL' },
    { key: 'dre', label: 'DRE' },
    { key: 'address', label: 'Dirección' },
    { key: 'phone', label: 'Teléfono' },
    { key: 'email', label: 'Correo institucional' },
    { key: 'director', label: 'Director' },
  ];

  sections = ['3°A', '3°B', '4°A', '4°B', '5°A', '5°B'];
  summary = [
    { label: 'Total Estudiantes', value: '450' }, { label: 'Total Docentes', value: '32' },
    { label: 'Total Secciones', value: '6' }, { label: 'Bimestres', value: '4' },
  ];

  addSection() {
    Swal.fire({ title: 'Nueva sección', input: 'text', inputPlaceholder: 'Ej: 2°A', showCancelButton: true, confirmButtonColor: 'var(--cv-navy)' })
      .then(r => { if (r.value) this.sections.push(r.value.toUpperCase()); });
  }
  removeSection(s: string) { this.sections = this.sections.filter(x => x !== s); }
}
