# Cohort Exercise 2

def parse_complex(z):
    try:
        return complex(z)
    except ValueError:
        pass

    try:
        return complex(z.replace(' ', '').replace('i', 'j'))
    except ValueError:
        pass

def input_complex(prompt=''):
    z = parse_complex(input(prompt))
    while z is None:
        print('Error: Could not parse the number.')
        z = parse_complex(input(prompt))
    return z

zs = tuple(input_complex('Enter a complex number: ') for _ in range(2))

print('a: {}, b: {}'.format(*zs))
print('a + b = {}'.format(sum(zs)))
print('a - b = {}'.format((zs[0]-zs[1])))
print('a * b = {}'.format((zs[0]*zs[1])))
try:
    print('a / b = {}'.format((zs[0]/zs[1])))
except ZeroDivisionError:
    print('Your second number is zero')
