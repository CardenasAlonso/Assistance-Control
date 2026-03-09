import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LucideAngularModule, User, Shield, Bell, Settings as SettingsIcon } from 'lucide-angular';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-settings', standalone: true,
  imports: [CommonModule, FormsModule, LucideAngularModule],
  templateUrl: './settings.html'
})
export class Settings {
  activeTab = 'profile';

  tabs = [
    { key: 'profile', label: 'Mi Perfil', icon: User },
    { key: 'security', label: 'Seguridad', icon: Shield },
    { key: 'notifications', label: 'Notificaciones', icon: Bell },
    { key: 'system', label: 'Sistema', icon: SettingsIcon },
  ];

  profile = { name: 'Usuario Demo', email: 'usuario@ie20188.edu.pe', phone: '', role: 'ADMIN' };

  notifSettings = [
    { label: 'Alertas de asistencia', desc: 'Notificar cuando un alumno tiene > 3 faltas', enabled: true },
    { label: 'Recordatorio de notas', desc: 'Alertar antes del cierre de bimestre', enabled: true },
    { label: 'Mensajes del sistema', desc: 'Actualizaciones y mantenimiento', enabled: false },
    { label: 'Reportes automáticos', desc: 'Enviar reporte semanal al correo', enabled: false },
  ];

  constructor(private auth: AuthService) {
    this.profile.role = this.auth.getRole() || 'ADMIN';
  }
}
