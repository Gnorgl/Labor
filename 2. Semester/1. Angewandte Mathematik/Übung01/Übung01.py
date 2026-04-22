import numpy as np
import matplotlib.pyplot as plt

# Zeitvektor erstellen
t = np.linspace(0, 15, 1000)

# a) Ursprüngliche Funktion
# y(t) = exp((-1/3 + pi*i) * t)
y_a = np.exp((-1/3 + np.pi * 1j) * t)

# b) Verdopplung der Frequenz
# Der Imaginaerteil (Frequenzanteil) wird von pi auf 2*pi erhoeht.
y_b = np.exp((-1/3 + 2 * np.pi * 1j) * t)

# c) Verdopplung der Halbwertszeit (und Beibehaltung der Frequenz aus b)
# Die Daempfung muss halbiert werden (von 1/3 auf 1/6), 
# damit die Amplitude doppelt so lange braucht, um abzusinken.
y_c = np.exp((-1/6 + 2 * np.pi * 1j) * t)

# Visualisierung
plt.figure(figsize=(12, 8))

# Plot fuer Teilaufgabe a)
plt.subplot(3, 1, 1)
plt.plot(t, np.abs(y_a), 'k--', label='Betrag (Amplitude)')
plt.plot(t, y_a.real, label='Realteil (Original)')
plt.title('a) Originale Eigenloesung')
plt.legend(loc='upper right')
plt.grid(True)

# Plot fuer Teilaufgabe b)
plt.subplot(3, 1, 2)
plt.plot(t, y_b.real, color='orange', label='Realteil (2x Frequenz)')
plt.title('b) Verdoppelte Frequenz')
plt.legend(loc='upper right')
plt.grid(True)

# Plot fuer Teilaufgabe c)
plt.subplot(3, 1, 3)
plt.plot(t, np.abs(y_c), 'r--', label='Betrag (2x Halbwertszeit)')
plt.plot(t, y_c.real, color='green', label='Realteil (2x Frequenz & 2x HWZ)')
plt.title('c) Verdoppelte Frequenz & Verdoppelte Halbwertszeit')
plt.xlabel('Zeit t')
plt.legend(loc='upper right')
plt.grid(True)

plt.tight_layout()
plt.show()