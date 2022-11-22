#include <iostream>

using namespace std;

int main(int argc, char const *argv[])
{
    cout << "Enter the current temperature in Celsius: ";
    double temp;
    cin >> temp;

    double farenheit = ((temp * 9)/ 5) + 32;

    cout << "The temperature in Farenheit is " << farenheit << endl;
    return 0;
}
