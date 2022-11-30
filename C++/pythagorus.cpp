#include <iostream>
#include <cmath>

using namespace std;

int main(int argc, char const *argv[])
{
    cout << "Enter length of side A: ";
    double sideA;
    cin >> sideA;

    cout << "Enter length of side B: ";
    double sideB;
    cin >> sideB;

    double findC = sqrt(pow(sideA, 2) + pow(sideB, 2));

    cout << "Length of Hypotenuse: " << findC << endl;
    return 0;
}
