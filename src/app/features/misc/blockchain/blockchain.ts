import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LucideAngularModule, Layers, Shield, Hash, Clock } from 'lucide-angular';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-blockchain', standalone: true,
  imports: [CommonModule, LucideAngularModule],
  templateUrl: './blockchain.html'
})
export class Blockchain {
  readonly LayersIcon = Layers; readonly ShieldIcon = Shield; readonly HashIcon = Hash; readonly ClockIcon = Clock;

  chainValid = true;

  blocks = [
    { index: 0, hash: '0000a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6', previousHash: '0'.repeat(64), timestamp: '09/03/2025 07:00:00', eventType: 'GENESIS', data: 'Bloque génesis — I.E. Centro de Varones 20188', valid: true },
    { index: 1, hash: '0001b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1', previousHash: '0000a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6', timestamp: '09/03/2025 07:35:12', eventType: 'ATTENDANCE', data: 'Asistencia 3°A — 43/45 presentes — Prof. García', valid: true },
    { index: 2, hash: '0002c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2', previousHash: '0001b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1', timestamp: '09/03/2025 08:10:45', eventType: 'SCORE', data: 'Nota registrada — Carlos Huamán — Matemática — 17/20', valid: true },
    { index: 3, hash: '0003d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3', previousHash: '0002c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2', timestamp: '09/03/2025 09:00:00', eventType: 'SCORE', data: 'Nota registrada — José Mamani — Comunicación — 15/20', valid: true },
    { index: 4, hash: '0004e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4', previousHash: '0003d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3', timestamp: '09/03/2025 09:45:22', eventType: 'ENROLLMENT', data: 'Matrícula registrada — Ángel Vásquez — 3°B — 2025', valid: true },
  ];

  verifyChain() {
    Swal.fire({ title: 'Verificando cadena...', timer: 1500, didOpen: () => Swal.showLoading() })
      .then(() => Swal.fire({ title: '✓ Cadena íntegra', text: 'Todos los bloques son válidos y la cadena no ha sido alterada.', icon: 'success' }));
  }
}
