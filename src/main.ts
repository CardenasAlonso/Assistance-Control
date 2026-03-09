// Zone.js is required by default for Angular's change
// detection system when you use provideZoneChangeDetection()
// (or any feature that depends on the global zone).
// Without it the app will show the opaque blank screen and
// throw RuntimeError NG9098: "In this configuration Angular
// requires Zone.js".
// Import zone.js core. the package's exports entry makes
// `zone.js` resolve to the correct ESM file, so no sub‑path is
// required. using the wrong path (e.g. `zone.js/dist/zone`) causes
// Vite to complain about a missing specifier.
import 'zone.js';

import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { App } from './app/app';

bootstrapApplication(App, appConfig)
  .catch((err) => console.error(err));
