import matplotlib.pyplot as plt
import numpy as np
from scipy.integrate import quad
from numpy import*
import pandas as pd

#hallo

def a (n) : 
    a = 0
    for k in range(1, n + 1):
        a += k**2
    return a

#Test:
print(a(5))
###########################
def b (n) : 
    b = 1
    for k in range(1, n + 1):
        b *= 2*k
    return b

print(b(3))
#########################
def c (n) : 
    c = 0
    for k in range(1, n + 1):
        c += k
    return c/(n+1)

print(c(6)) 
########################

def d (n) :
    if n <= 0 :
        return "Geht nicht, n muss natürliche Zahl sein!"
    return n**2

print(d(5))
########################
# 1. Eingabewerte generieren
a = np.array([0, 0, 0, 0, 1, 1, 1, 1], dtype=bool)
b = np.array([0, 0, 1, 1, 0, 0, 1, 1], dtype=bool)
c = np.array([0, 1, 0, 1, 0, 1, 0, 1], dtype=bool)

# 2. Teilausdrücke berechnen
term1 = np.logical_and(a, np.logical_not(b))
term2 = np.logical_and(np.logical_not(a), c)
term3 = np.logical_and(np.logical_and(a, b), np.logical_not(c))

# 3. Gesamtergebnis berechnen
f = logical_or(logical_or(term1, term2), term3)

# Ausgabe als DataFrame zur besseren Lesbarkeit
df = pd.DataFrame({
    'a': a.astype(int),
    'b': b.astype(int),
    'c': c.astype(int),
    'T1 (a ^ ¬b)': term1.astype(int),
    'T2 (¬a ^ c)': term2.astype(int),
    'T3 (a ^ b ^ ¬c)': term3.astype(int),
    'f(a,b,c)': f.astype(int)
})

print("Wertetabelle (0=Falsch, 1=Wahr):")
print(df)