# created with Python 3.8
# author: Martin Hering-Bertram
from numpy import *
from matplotlib.pyplot import figure, plot, axis, show
from celluloid import Camera


def RailwayTrack( X):
    # returns position and tangent vector for railway track at arc length x
    # input:
    # X     arc length parameter in [0, 55]
    # output:
    # P     2D point
    # T     tangent vector alingned with track
    #
    # note: X may be a vector of parameters. then, P and T are matrices
    #
    # author: MHB, 04.2024

    p2 = array([-1.75, -6])
    p1 = p2 + array([0, 6]) + 6*array([sin(pi/4), -cos(pi/4)])
    p0 = p1 + array([7, 7])
    p3 = p2 + array([-2, 0])
    p4 = p3 + array([0, 12])

    m1 = p2 + array([0, 6])
    m2 = p2 + array([0, 3])
    m3 = (p3 + p4)/2;

    l1 = linalg.norm( p1-p0)
    l2 = l1 + 6*pi/4
    l3 = l2 + 3*2*pi
    l4 = l3 + linalg.norm( p3-p2)
    l5 = l4 + 6*pi

    r1 = 6
    r2 = 3
    r3 = 6

    sq2 = sqrt(.5)
    if size(X)==1:
        X = array([X])

    P = zeros([2, size(X)])
    T = zeros([2, size(X)])
        
    for i in range( 0,size(X)):
        x = X[i]

        if x<l1:
            p = p0 + x*array([-sq2, -sq2])
            up = array([-sq2, sq2])
        elif x<l2:
            alpha = -pi/4 - (x-l1)/r1
            p = m1 + r1*array([cos(alpha), sin(alpha)])
            up = -array([cos(alpha), sin(alpha)])
        elif x<l3:
            alpha = -pi/2 - (x-l2)/r2
            p = m2 + r2*array([cos(alpha), sin(alpha)])
            up = -array([cos(alpha), sin(alpha)])
        elif x<l4:
            p = p2 + (x-l3) * array([-1, 0])
            up = array([0, 1])
        elif x<l5:
            alpha = -pi/2 - (x-l4)/r3
            p = m3 + r3*array([cos(alpha), sin(alpha)])
            up = -array([cos(alpha), sin(alpha)])
        else:
            p = p4
            up = array([0, -1])

        p = array([-p[0], p[1]])   # mirror the road
        tangent = array([up[1], up[0]]) # forward vector
        P[ :,i] = p
        T[ :,i] = tangent

    if size(X)==1: # return vectors rather than matrices
        P = P[:,0]
        T = T[:,0]
        
    return P, T # points and tangent vectors along track


def DrawCart( p, R):
    # draw a tiny little cart at position p with rotation R
    #
    # p     2D point
    # R     2x2 rotation matrix
    #
    # author: MHB, 04.2024

    # define polygon
    poly = array([[.6, .6,  .45,-.45,-.6, -.6,-.4,-.2, .6],
                  [.6, .25, .1,  .1,  .25, .9, .9, .6, .6]])
    # define wheels
    iw = linspace( 0, 2*pi, 20)
    r = .13
    wheel1 = array([ r*cos(iw)+.2,r*sin(iw)+r])
    wheel2 = array([ r*cos(iw)-.2, r*sin(iw)+r])

    # positions of people in cart
    people = array([[.2, 0, -.2], [.7, .7, .7]])

    # rotate translate and draw the cart
    poly   = R@poly + array([p[0]*ones(9), p[1]*ones(9)])
    wheel1 = R@wheel1 + array([p[0]*ones(20), p[1]*ones(20)])
    wheel2 = R@wheel2 + array([p[0]*ones(20), p[1]*ones(20)])
    people = R@people + array([p[0]*ones(3), p[1]*ones(3)])
    
    plot( poly[0,:], poly[1,:], 'b')
    plot( wheel1[0,:], wheel1[1,:], 'k')
    plot( wheel2[0,:], wheel2[1,:], 'k')
    plot( people[0,:], people[1,:], 'r*')

    
# MAIN PROGRAM
fig = figure( figsize =(10, 8))
camera = Camera(fig)

Points, Tangents = RailwayTrack( linspace(0, 55, 500))
size(Points)


#Startwerte
u = 0.0      # Position auf der Strecke
v = 0.0      # Geschwindigkeit
g = -9.81    # Erdbeschleunigung
dt = 0.05    # Zeitschritt

while u < 55:
    # 1. Schiene zeichnen
    plot(Points[0, :], Points[1, :], 'k')

    # 2. Aktuelle Position und Tangente holen
    p, tv = RailwayTrack(u)
    
    # 3. Rotationsmatrix R aus tv und up bilden
    # tv ist [tv_x, tv_y], up ist [-tv_y, tv_x]
    R = array([[tv[0], -tv[1]], 
               [tv[1],  tv[0]]])
    
    # 4. Lore zeichnen
    DrawCart(p, R)
    
    # 5. Physik-Update (Euler-Verfahren)
    # a ist proportional zu g und der y-Komponente der Tangente
    a = g * tv[1]  
    
    v = v + a * dt  # v_neu = v_alt + a * dt
    u = u + v * dt  # u_neu = u_alt + v * dt
    
    # Sicherheit: u darf nicht negativ werden (falls sie zurückrollt)
    if u < 0: u = 0; v = 0 

    axis('equal')
    camera.snap()

anim = camera.animate(interval=50) # interval in Millisekunden
show()

