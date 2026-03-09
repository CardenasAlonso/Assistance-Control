import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { LucideAngularModule, GraduationCap, User, Lock, Eye, EyeOff, AlertCircle } from 'lucide-angular';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-login', standalone: true,
  imports: [CommonModule, FormsModule, LucideAngularModule],
  templateUrl: './login.html', styleUrl: './login.scss'
})
export class Login {
  readonly LogoIcon = GraduationCap; readonly UserIcon = User;
  readonly LockIcon = Lock; readonly EyeIcon = Eye;
  readonly EyeOffIcon = EyeOff; readonly AlertIcon = AlertCircle;

  email = ''; password = '';
  loading = signal(false);
  showPass = signal(false);
  errorMsg = '';

  constructor(private auth: AuthService, private router: Router) { }

  onLogin() {
    if (!this.email || !this.password) { this.errorMsg = 'Completa usuario y contraseña.'; return; }
    this.loading.set(true); this.errorMsg = '';
    this.auth.login({ email: this.email, password: this.password }).subscribe({
      next: res => { localStorage.setItem('userName', res.role === 'admin' ? 'Administrador' : this.email.split('@')[0]); this.router.navigate(['/dashboard']); },
      error: () => { this.errorMsg = 'Credenciales incorrectas. Verifica tu usuario y contraseña.'; this.loading.set(false); }
    });
  }

  loginAs(role: string) {
    const names: Record<string, string> = { admin: 'Admin García', coordinator: 'Coord. Mendoza', teacher: 'Prof. Quispe', student: 'Juan Pérez' };
    localStorage.setItem('token', 'demo-token-' + role);
    localStorage.setItem('role', role);
    localStorage.setItem('userId', 'demo-' + role);
    localStorage.setItem('userName', names[role] ?? role);
    this.router.navigate(['/dashboard']);
  }
}
