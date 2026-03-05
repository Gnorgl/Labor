import numpy as np
import matplotlib.pyplot as plt #matplotlib ist sehr große bibliothek, man will nur pyplot haben.
import scipy as sp

#Aufgabe 1:

#a)
n = 10 #Wird erst nachgefragt
a = 1 #Wird erst nachgefragt
summe = 0

for k in range(a, n + 1):
    quadrat = pow(k, 2)
    summe += quadrat

print(summe)

#b)
n = 10
produkt = 1

for k in range(1, n + 1):
    produkt = produkt*2*k

print(produkt)

#c)
n = 10
summe = 0

for k in range(1, n + 1):
    summe += k

print(summe / (n + 1))

#d)
print(pow(n, 2))

#oder:

import math

n = 10
# Die direkte (unvereinfachte) Umsetzung:
ergebnis_kompliziert = math.exp(2 * math.log(n))

# Deine Lösung:
ergebnis_smart = n**2 # oder pow(n, 2)

#Aufgabe 2:

