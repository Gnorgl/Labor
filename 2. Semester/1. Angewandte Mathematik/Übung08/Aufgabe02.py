
from numpy import *
import matplotlib.pyplot as plt
from scipy.special import binom

def Bernstein( n, i, t):
    return binom( n, i) * (1.0 - t)**(n-i) * t**i


def DrawBezier( bx, by):
    n = size( bx) - 1

    # 1. Parameterintervall t von 0 bis 1 definieren (z.B. 100 Schritte)
    t = linspace(0.0, 1.0, 100)
    
    # 2. Koordinaten-Arrays für die Kurvenpunkte mit Nullen initialisieren
    x = zeros_like(t)
    y = zeros_like(t)
    
    # 3. Bézier-Kurve über die Bernstein-Polynome aufsummieren
    for i in range(n + 1):
        bi = Bernstein(n, i, t)
        x += bx[i] * bi
        y += by[i] * bi
        
    # 4. Die berechnete Kurve zeichnen (in Rot '-r', um sie vom blauen Kontrollpolygon abzuheben)
    plt.plot( x, y, '-r')
   

class Curve: # enter and edit a control polygon
    def __init__(self, fig, ax):
        self.ax = ax
        self.canvas = fig.canvas
        self.px = array([]) # x coordinates for control polygon
        self.py = array([]) # y coordinates
        self.nDrag = -1

        self.canvas.mpl_connect('button_press_event', self.ButtonPress)
        self.canvas.mpl_connect('motion_notify_event', self.Move)
        self.canvas.mpl_connect('button_release_event', self.Release)

        self.xlim = (0, 16)
        self.ylim = (0, 12)
        self.ax.set_xlim( self.xlim)
        self.ax.set_ylim( self.ylim)
        plt.show() # draw empty subplot with axes
        
             
    def ButtonPress( self, event): # mouse button pressed event
        #print( event.xdata, event.ydata)
        if event.button == 1: # add new point
            self.px = r_[self.px, event.xdata]
            self.py = r_[self.py, event.ydata]
    
        elif event.button == 3: # move closest point
            if size( self.px) > 0:
                dist2 = (self.px - event.xdata)**2 + (self.py - event.ydata)**2
                self.nDrag = argmin( dist2)
                self.px[ self.nDrag] = event.xdata
                self.py[ self.nDrag] = event.ydata
        self.Draw( event)

    def Move( self, event): # drag point when moving with pressed button
        if self.nDrag != -1:
            self.px[ self.nDrag] = event.xdata
            self.py[ self.nDrag] = event.ydata
            self.Draw( event)

    def Release( self, event): # mouse button released
        self.nDrag = -1 # no dragging
 
    def Draw( self, event): # re-draw canvas
        #print('draw')
        self.ax.cla()
        self.ax.set_xlim( self.xlim)
        self.ax.set_ylim( self.ylim)

        plt.plot( self.px, self.py, '*-b')

        if size(self.px) > 2:
            #DrawDeCasteljau( self.px, self.py, .5)
            DrawBezier( self.px, self.py)

        event.canvas.draw()
        # using plt.show() here will cause stack overflow
        

fig, ax = plt.subplots()
ax.set_title('left button to add points, right button to drag')

curve = Curve( fig, ax) # generate curve object and start editing


