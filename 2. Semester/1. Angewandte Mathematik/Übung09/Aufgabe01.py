import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

# 1. Definitionsbereich fuer s und t erstellen
# 's' laeuft von 0 bis 2*pi, 't' laeuft von 0 bis 4
s_vals = np.linspace(0, 2 * np.pi, 100)
t_vals = np.linspace(0, 4, 100)

# 2. Ein zweidimensionales Gitter (Grid) aus s und t erzeugen
S, T = np.meshgrid(s_vals, t_vals)

# 3. Die Parameterfunktion f(s, t) definieren
X = np.cos(S) * T
Y = np.sin(S) * T
Z = T**2

# 4. Dreidimensionalen Plot erstellen
fig = plt.figure(figsize=(8, 6))
ax = fig.add_subplot(111, projection='3d')

# Flaeche plotten (mit einem Farbverlauf fuer die Hoehe Z)
surface = ax.plot_surface(X, Y, Z, cmap='viridis', edgecolor='none')

# Beschriftungen und Titel hinzufuegen
ax.set_title('Parameterflaeche $f(s,t)$')
ax.set_xlabel('X')
ax.set_ylabel('Y')
ax.set_zlabel('Z')

# Farbleiste (Colorbar) als Legende fuer die Z-Werte
fig.colorbar(surface, ax=ax, shrink=0.5, aspect=5, label='t^2 (Hoehe)')

# Plot anzeigen
plt.show()