from numpy import *
from scipy.interpolate import interp1d
from matplotlib.pyplot import plot, show, grid, legend, title

x = array([ 0, 1, 3, 3.1, 3, 3, 2, .8, 4.4])
y = array([ .5, .5, 4, 4, 1, 0, 2.7, 1.8, 2])
P = array([ x, y]) # interpolation points
n = size(x)

# Differenzen zwischen aufeinanderfolgenden Punkten berechnen (dx, dy)
# diff(P, axis=1) berechnet P[:, 1:] - P[:, :-1]
diffs = diff(P, axis=1)
# Euklidische Abstaende berechnen: sqrt(dx^2 + dy^2)
distances = sqrt(sum(diffs**2, axis=0))

# --- 1. Aequidistante Parametrisierung (Original) ---
knots_equi = arange(0, n)
t_equi = linspace(0, n-1, 501)
x_equi = interp1d(knots_equi, x, kind='cubic')(t_equi)
y_equi = interp1d(knots_equi, y, kind='cubic')(t_equi)
plot(x_equi, y_equi, 'b-', label='Aequidistant')

# --- 2. Chordale Parametrisierung ---
# Starte bei 0 und fuege die kumulierte Summe der Abstaende an
knots_chord = concatenate(([0], cumsum(distances)))
t_chord = linspace(knots_chord[0], knots_chord[-1], 501)
x_chord = interp1d(knots_chord, x, kind='cubic')(t_chord)
y_chord = interp1d(knots_chord, y, kind='cubic')(t_chord)
plot(x_chord, y_chord, 'g--', label='Chordal')

# --- 3. Zentripetale Parametrisierung ---
# Identisch zu chordal, aber mit der Wurzel aus den Abstaenden
knots_centri = concatenate(([0], cumsum(sqrt(distances))))
t_centri = linspace(knots_centri[0], knots_centri[-1], 501)
x_centri = interp1d(knots_centri, x, kind='cubic')(t_centri)
y_centri = interp1d(knots_centri, y, kind='cubic')(t_centri)
plot(x_centri, y_centri, 'm-.', label='Zentripetal')

# Originalpunkte plotten
plot(x, y, 'r*', My_markersize=10, label='Datenpunkte')

title('Vergleich parametrischer Splines')
grid(True)
legend()
show()