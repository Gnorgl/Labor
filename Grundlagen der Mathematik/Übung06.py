#Übungsblatt 6
#Gruppe: C04
#Gruppenmitglieder: Gian Isikli, Khang Pham, Finn Rusche, Richard Schröder

import matplotlib.pyplot as plt
import numpy as np
from numpy import*
from scipy.fft import fft, fftfreq
from matplotlib.pyplot import plot, grid, show
from matplotlib import pyplot as plt
from celluloid import Camera

#Aufgabe 2:

#Wir gehen von der Funktion f(t) = a*cos(bt+c) aus.

a = 3.0 #Das ist unsere Amplitude aus der Aufgabe
T = 5.0 #Hier haben wir die Wellenlänge T
b = (2 * np.pi) / T #b berechnen, aus (2pi/T)
c = (2 * np.pi) / 5 #phasenverschiebung ergibt sich aus 1/5 * (2pi)

t_max = 3 * T #Wie in der Aufgabe, damit wir die Funktion auf Länge 3T machen können.
dt = 1.0/1000 #wie in vorlesung das 1.0/1000 für die sampling distance
t = np.arange(0, t_max, dt) #domain


y = a * np.cos(b * t + c) #signal, wie oben unsere funktionsgleichung

#grafische sachen:
plt.plot(t, y)
plt.grid(True)
plt.xlabel("Zeit t")
plt.ylabel("Amplitude f(t)")
plt.title("Plot der Funktion f(t) = 3 cos(2pi/5 * t + 2pi/5)")
plt.show()

#Aufgabe 3:

#a)
#b)

def DrawShip(alpha, x, y):
    #punkte fürs schiff 
    X0 = np.array([-1, -1, 0, 0, 1, 1, 3, 2, -1])
    Y0 = np.array([0, 1, 1, 2, 2, 1, 1, 0, 0])

    # schiff um ursprung rotieren
    # Rotiertes X: x' = x0*cos(a) - y0*sin(a)
    X_rot = X0 * np.cos(alpha) - Y0 * np.sin(alpha)
    # Rotiertes Y: y' = x0*sin(a) + y0*cos(a)
    Y_rot = X0 * np.sin(alpha) + Y0 * np.cos(alpha)

    #Translation
    X_final = X_rot + x
    Y_final = Y_rot + y

    # Plotten
    #dieses k- für die schwarze linie
    #k steht für schwarz und das - sthet für durchgezogene linie
    plt.plot(X_final, Y_final, 'k-')
    plt.show

#c)

#Wir wollen erreichen, dass das schiff wie auf wellen schwimmt.

#Funktion testen mit code aus übung:
fig = plt.figure()
camera = Camera(fig)
dt = .2 # timestep
for t in arange( 0, 20, dt):
 DrawShip( cos(t)/2, 2*t, sin(t)) # draw ship using plt.plot()
 plt.axis('equal')
 camera.snap()

animation = camera.animate()
plt.show()