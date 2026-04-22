import numpy as np
import matplotlib.pyplot as plt

#a) Eigenwerte und Eigenvektoren
A = np.array([[0,1],
             [-0.25,0]])
eigenwerte, eigenvektoren = np.linalg.eig(A)
#in python ist j die imaginäreeinheit
print(f"Eigenwerte: {eigenwerte}")

#b) Analytische Eigenlösung mit real und imaginär
t_span = np.linspace(0, 200, 2000)
#durch Euler Formel kennen wir realteil und imaginärteil, es ergibt sich:
y_analytisch = np.array([np.cos(0.5 * t_span), -0.5 * np.sin(0.5 * t_span)])

#c) und d) Numerische Integration
#Wie bei der anderen Aufgabe machen wir delta t auf 0.5
dt = 0.5
t_num = np.arange(0, 200 + dt, dt)
#mit der länge t_num 
n = len(t_num)

#speicher reservieren, mit nullen auffüllen
y_euler = np.zeros((2, n))
y_heun = np.zeros((2, n))

#startwert bei t=0
y_start = np.array([1.0,0.0])
y_euler[:, 0] = y_start
y_heun[:, 0] = y_start

for i in range(n - 1):
    #hier euler 
    f_aktuell = A @ y_euler[:, i]
    y_euler[:, i+1] = y_euler[:, i] + dt * f_aktuell

    #hier heun
    #vorhersage, euler schritt
    y_vorher = y_heun[:, i] + dt * (A @ y_heun[:, i]) #matrix vektor multiplikation
    #korrektur
    f_start = A @ y_heun[:, i]# : alle zeilen berücksichtigen, spalte i
    f_ende = A @ y_vorher
    y_heun[:, i+1] = y_heun[:, i] + (dt / 2) * (f_start + f_ende)
    #erst euler schritt immer dann korrektur schritt, steigung wird an neuem geschätzen
    #punkt berechnet, bilden mittelwert aus steigung am startpunkt und steigung am zielpunkt
    #mittelwert wird genutzt, um schritt zu gehen.

#hier wird gezeichnet
plt.figure(figsize=(12, 6))
plt.plot(t_span, y_analytisch[0, :], label='Analytisch (Exakt)', color='black', alpha=0.5)
plt.plot(t_num, y_euler[0, :], label='Euler (Instabil)', color='red', linestyle='--')
plt.plot(t_num, y_heun[0, :], label='Heun (Praeziser)', color='blue')
plt.legend()
plt.title("Vergleich der Integrationsverfahren")
plt.xlabel("Zeit t")
plt.ylabel("Position y1")
plt.grid(True)
plt.show()
