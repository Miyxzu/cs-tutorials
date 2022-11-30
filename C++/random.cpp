#include <iostream>

using namespace std;

int main(int argc, char const *argv[])
{
    srand(time(nullptr));
    int number = rand() % 10;
    cout << number << endl;
    return 0;
}
