import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import Swal from 'sweetalert2';
import { LucideAngularModule, Mail, Lock, User, ArrowRight } from 'lucide-angular';
// Si tienes un endpoint de registro en tu AuthService, impórtalo aquí.
// import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, FormsModule, LucideAngularModule],
  templateUrl: './signup.html',
  styleUrl: './signup.scss',
})
export class Signup {
  // Íconos de Lucide
  readonly Mail = Mail;
  readonly Lock = Lock;
  readonly User = User;
  readonly ArrowRight = ArrowRight;

  fullName = '';
  email = '';
  role = 'student'; // Valor por defecto
  password = '';
  confirmPassword = '';
  isLoading = false;

  private router = inject(Router);
  // private authService = inject(AuthService); // Descomentar cuando conectes al backend

  handleSignup() {
    // Validación de contraseñas
    if (this.password !== this.confirmPassword) {
      Swal.fire({
        icon: 'warning',
        title: 'Atención',
        text: 'Las contraseñas no coinciden',
        confirmButtonColor: '#2563eb'
      });
      return;
    }

    this.isLoading = true;

    // Por ahora simularemos la carga como lo hacía tu compañero en Next.js
    setTimeout(() => {
      this.isLoading = false;

      Swal.fire({
        icon: 'success',
        title: '¡Cuenta creada!',
        text: 'Te has registrado correctamente en Fine Flow.',
        timer: 2000,
        showConfirmButton: false
      }).then(() => {
        // Redirigir al dashboard (o al login para que inicie sesión)
        this.router.navigate(['/dashboard']);
      });
    }, 1500);
  }
}
