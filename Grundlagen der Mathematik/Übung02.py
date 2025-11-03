from numpy import *
from matplotlib.pyplot import plot, show
x = linspace( 0, 10.5, 51) # von 0 bis 10 in 0.5er Schritten
plot( x, cos(x)) # cos(x) liefert ebenso viele Werte, wie x
plot (x, cos(2*x))
plot (x, (cos(x))**2)
show() # zeichne alles (hier etwas grob aufgelöst)
