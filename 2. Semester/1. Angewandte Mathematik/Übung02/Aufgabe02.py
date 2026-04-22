import numpy as np
import matplotlib.pyplot as plt

#parameter hier erstmal definieren
y0 = 81
#das hier ist zeitschritt dt, je kleiner delta t ist, desto genauer wird es, aber rechenaufwand steigt.
dt = 0.5 #wenn man 0.1 macht, sieht man den unterschied kaum noch
t_end = 10
t = np.arange(0, t_end + dt, dt)

#a) Plotten der analytischen Lösung
# Wir haben berechnet y0 * np.exp(-np.log(1.5) * t), was auch y0 * (pow(1.5, (-t)))
y_analytisch = y0 * np.exp(-np.log(1.5) * t)

#b) Euler-Verfahren
#pre allocation, vorreservierung von speicher und index existiert schon.
y_euler = np.zeros(len(t))
#startwert
y_euler[0] = y0

for i in range(0, len(t) -1):
    #Formel dafür:
    y_abgeleitet = -np.log(1.5) * y_euler[i]
    y_euler[i+1] = y_euler[i] + dt * y_abgeleitet

#plotten hier
plt.figure(figsize=(10, 6))
plt.plot(t, y_analytisch, label='Analytisch', color='blue', linewidth=2)
plt.plot(t, y_euler, 'o--', label=f'Euler (dt={dt})', color='red')
plt.xlabel('Zeit t')
plt.ylabel('y(t)')
plt.title('Vergleich: Analytische vs. Numerische Lösung')
plt.legend()
plt.grid(True)
plt.show()