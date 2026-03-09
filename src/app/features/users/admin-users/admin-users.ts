import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LucideAngularModule, Plus, Edit2, X, Eye, EyeOff } from 'lucide-angular';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-admin-users', standalone: true,
  imports: [CommonModule, FormsModule, LucideAngularModule],
  templateUrl: './admin-users.html'
})
export class AdminUsers {
  readonly PlusIcon = Plus; readonly EditIcon = Edit2; readonly XIcon = X;
  readonly EyeIcon = Eye; readonly EyeOffIcon = EyeOff;

  showModal = signal(false); isEditing = signal(false);

  users = [
    { id: 'u1', name: 'Admin Principal', email: 'admin@ie20188.edu.pe', role: 'ADMIN', active: true, lastLogin: '09/03/2025 08:12' },
    { id: 'u2', name: 'Pedro Coordinador', email: 'coordinador@ie20188.edu.pe', role: 'COORDINATOR', active: true, lastLogin: '09/03/2025 07:55' },
    { id: 'u3', name: 'María Auxiliar', email: 'auxiliar@ie20188.edu.pe', role: 'COORDINATOR', active: false, lastLogin: '01/03/2025 09:00' },
  ];

  form: any = { name: '', email: '', role: 'ADMIN', password: '' };
  editingId = '';

  openAdd() { this.form = { name: '', email: '', role: 'ADMIN', password: '' }; this.isEditing.set(false); this.showModal.set(true); }
  openEdit(u: any) { this.form = { ...u }; this.editingId = u.id; this.isEditing.set(true); this.showModal.set(true); }

  save() {
    if (!this.form.name || !this.form.email) { Swal.fire('Atención', 'Completa nombre y correo.', 'warning'); return; }
    if (this.isEditing()) {
      this.users = this.users.map(u => u.id === this.editingId ? { ...u, ...this.form } : u);
    } else {
      this.users = [...this.users, { ...this.form, id: 'u' + Date.now(), active: true, lastLogin: '—' }];
    }
    this.showModal.set(false);
    Swal.fire({ title: this.isEditing() ? 'Actualizado' : 'Creado', icon: 'success', timer: 1500, showConfirmButton: false });
  }

  toggleStatus(u: any) { u.active = !u.active; }
}
