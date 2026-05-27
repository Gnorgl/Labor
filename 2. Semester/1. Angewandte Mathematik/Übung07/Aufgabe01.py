# M2 Uebung 7

from numpy import *
from pylab import plot, show, grid, axis

def Lotfusspunkt( p, v, m):
    # t berechnen nach der hergeleiteten Formel:
    # (m - p) skalar multipliziert mit v, geteilt durch das Skalarprodukt von v mit sich selbst
    t = dot(m - p, v) / dot(v, v)
    
    # Den Parameter t in die Geradengleichung einsetzen
    l = p + t * v
    return l
 

# Testprogramm

def DrawLine( p1, p2, color = "b*-"):
    P = c_[p1, p2]
    plot( P[0,:], P[1,:], color)

p = random.randn(2)
v = array([3, random.randn(1)[0]])
DrawLine( p - 3*v, p + 3*v, 'r')

for i in range(20):
    m1 = 3*random.randn(2)
    l1 = Lotfusspunkt( p, v, m1)
    DrawLine( m1, l1)

grid( True)
axis( "equal")
show()