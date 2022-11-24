#define _USE_MATH_DEFINES

#include <iostream>
#include <cmath>

using namespace std;

int main(int argc, char const *argv[])
{
    cout << "Enter the radius of the circle: ";
    double radius;
    cin >> radius;

    double area = M_PI * pow(radius, 2);
    cout << "The area of the circle is " << area << endl;
    return 0;
}
