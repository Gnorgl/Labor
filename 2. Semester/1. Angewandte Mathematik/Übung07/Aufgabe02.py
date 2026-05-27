import numpy as np
import matplotlib.pyplot as plt
from scipy.interpolate import interp1d

# 1. Gegebene Datenpunkte definieren
t = np.array([1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
f = np.array([1, 1, 3, 3, 2, 2, 0, 1, 2, 4])

# Feines Gitter fuer eine glatte Darstellung der Kurven
t_fine = np.linspace(1, 10, 500)

# --- Teil a) Kubischer Spline ---
# 'cubic' sorgt fuer den kubischen Spline
spline_func = interp1d(t, f, kind='cubic')
f_spline = spline_func(t_fine)

# --- Teil b) Interpolationspolynom vom Grad 9 ---
# polyfit berechnet die Koeffizienten des Polynoms (Grad = Anzahl Punkte - 1)
poly_coeffs = np.polyfit(t, f, deg=len(t)-1)
# poly1d erzeugt eine auswertbare Funktion aus den Koeffizienten
poly_func = np.poly1d(poly_coeffs)
f_poly = poly_func(t_fine)

# --- Plotten ---
plt.figure(figsize=(10, 6))

# Datenpunkte
plt.plot(t, f, 'ro', label='Datenpunkte (Knoten)', markersize=8)

# Spline-Kurve
plt.plot(t_fine, f_spline, 'b-', label='Kubischer Spline (interp1d)', linewidth=2)

# Polynom-Kurve
plt.plot(t_fine, f_poly, 'g--', label='Polynom Grad 9 (polyfit)', linewidth=2)

# Diagramm-Einstellungen
plt.title('Vergleich: Kubischer Spline vs. Polynominterpolation')
plt.xlabel('t')
plt.ylabel('f(t)')
plt.grid(True)
plt.legend()
plt.ylim(-2, 6) # Y-Achse leicht begrenzen, falls das Polynom ausbricht

plt.show()