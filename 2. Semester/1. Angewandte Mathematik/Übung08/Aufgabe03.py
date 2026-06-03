import numpy as np
import matplotlib.pyplot as plt

# 1. Parameter-Vektoren für s und t im Intervall [0, 2*pi] definieren
s = np.linspace(0, 2 * np.pi, 50)
t = np.linspace(0, 2 * np.pi, 50)

# 2. Gitter S und T als Matrizen erzeugen
S, T = np.meshgrid(s, t)

# Konstanten für die Radien
r = 1
R = 3

# 3. Koordinatenmatrizen FX, FY und FZ berechnen
FX = np.cos(S) * (R + r * np.cos(T))
FY = np.sin(S) * (R + r * np.cos(T))
FZ = r * np.sin(T)

# 4. 3D-Plot erstellen
fig = plt.figure(figsize=(8, 6))
ax = fig.add_subplot(111, projection='3d')

# plot_surface zeichnet die berechnete Parameterfläche
surf = ax.plot_surface(FX, FY, FZ, cmap='viridis', edgecolor='none')

# Layout-Einstellungen
ax.set_title('Parameterfläche: Torus ($R=3, r=1$)')
ax.set_xlabel('X')
ax.set_ylabel('Y')
ax.set_zlabel('Z')

# Proportionen der Achsen anpassen, damit der Torus nicht verzerrt wirkt
ax.set_box_aspect([1, 1, 0.3]) 

fig.colorbar(surf, shrink=0.5, aspect=5)
plt.show()