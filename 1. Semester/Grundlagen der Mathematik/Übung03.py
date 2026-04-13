import numpy as np
import matplotlib.pyplot as plt


#Aufgabe 03 Polynome:
def DrawCubic(c):
    """
    Plottet ein kubisches Polynom und dessen erste Ableitung im Intervall [-5, 5].

    Das Polynom ist definiert als: f(x) = c[3]*x**3 + c[2]*x**2 + c[1]*x + c[0].

    Args:
        c (numpy.ndarray oder list): Vektor der vier Koeffizienten [c0, c1, c2, c3].
    """
    # 1. Erzeugung der x-Werte im Intervall [-5, 5]
    x = np.linspace(-5, 5, 400) # 400 Punkte für eine glatte Kurve

    # Stellen Sie sicher, dass c ein NumPy-Array der Länge 4 ist, falls es eine Liste war
    c = np.array(c)
    if c.size != 4:
        raise ValueError("Der Koeffizientenvektor c muss genau 4 Elemente enthalten.")

    # 2. Berechnung des Polynoms f(x)
    # f(x) = c[3]*x**3 + c[2]*x**2 + c[1]*x + c[0]
    f_x = c[3] * x**3 + c[2] * x**2 + c[1] * x + c[0]
    
    # Vereinfachte Berechnung mit np.polyval (optional, aber gut zu wissen):
    # Die Koeffizienten für np.polyval müssen in absteigender Reihenfolge sein: [c3, c2, c1, c0]
    # f_x_polyval = np.polyval(c[::-1], x) 
    
    # 3. Berechnung der ersten Ableitung f'(x)
    # f'(x) = 3*c[3]*x**2 + 2*c[2]*x + c[1]
    f_prime_x = 3 * c[3] * x**2 + 2 * c[2] * x + c[1]
    
    # 4. Plotten
    plt.figure(figsize=(10, 6))
    plt.plot(x, f_x, label='$f(x)$', color='blue')
    plt.plot(x, f_prime_x, label="$f'(x)$", color='red', linestyle='--')
    
    plt.title('Kubisches Polynom und dessen Ableitung')
    plt.xlabel('$x$')
    plt.ylabel('$f(x)$, $f\'(x)$')
    plt.grid(True)
    plt.axhline(0, color='black', linewidth=0.5) # x-Achse
    plt.axvline(0, color='black', linewidth=0.5) # y-Achse
    plt.legend()
    plt.show()

    # Koeffizienten für f(x) = 1*x^3 + 1*x^2 - 6*x + 0
    c_test = [0, -6, 1, 1] 

    DrawCubic(c_test)

    #Aufgabe 03: Newton Iteration:
    # Definieren der Funktion f(x) = x^3 - 7x
def f(x):
    """Berechnet den Wert der Funktion f(x) = x^3 - 7x."""
    return x**3 - 7 * x

# Definieren der Ableitung f'(x) = 3x^2 - 7
def f_prime(x):
    """Berechnet den Wert der ersten Ableitung f'(x) = 3x^2 - 7."""
    return 3 * x**2 - 7

# Implementierung der Newton-Iterationsformel
def newton_step(x):
    """Führt einen Schritt der Newton-Iteration durch: x := x - f(x) / f'(x)."""
    # Überprüfen auf Division durch Null (oder sehr kleine Werte)
    if np.abs(f_prime(x)) < 1e-10:
        # Hier könnte man eine Fehlermeldung ausgeben oder die Iteration stoppen
        print("Achtung: Ableitung f'(x) ist fast Null. Iteration bricht ab.")
        return x
    
    # Die Kernformel
    x_new = x - f(x) / f_prime(x)
    return x_new

x = 7.0  # Startwert x=7

print(f"Startwert: x = {x}")
print("-" * 30)

# Führen wir 5 Iterationen durch:
for i in range(1, 6):
    x_old = x
    x = newton_step(x)
    print(f"Iteration {i}: x = {x:.10f}")

    x = 1.0  # Startwert x=1
print(f"\nStartwert: x = {x}")
print("-" * 30)

# Führen wir 5 Iterationen durch:
for i in range(1, 6):
    x_old = x
    x = newton_step(x)
    print(f"Iteration {i}: x = {x:.10f}")