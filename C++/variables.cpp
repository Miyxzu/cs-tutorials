#include <iostream>

using namespace std;

int main(int argc, char const *argv[])
{
    double price = 99.99;
    float interest_rate = 3.67f;
    long filesize = 900000l;
    char letter = 'a';
    bool isValid = false;

    int binnumber = 0b11111111;
    int hexnumber = 0xff;
    unsigned int unsign = -255;
    unsign--;

    short number = 100;
    int another = number;

    cout << binnumber << endl;
    cout << hexnumber << endl;
    cout << unsign << endl;
    cout << another << endl;
    
    return 0;
}
