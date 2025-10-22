import matplotlib.pyplot as plt
import numpy as np
from scipy.integrate import quad
from numpy import*

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

