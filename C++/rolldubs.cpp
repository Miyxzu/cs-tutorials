#include <iostream>

using namespace std;

int main(int argc, char const *argv[])
{
    cout << "Would you like to roll?: ";
    string answer;
    cin >> answer;

    const int max = 6;
    const int min = 1;

    if(answer == "yes"){
        srand(time(nullptr));
        short first = (rand() % (max - min + 1)) + min;
        short second = (rand() % (max - min + 1)) + min;

        cout << "Your rolls were " << first << ", " << second << endl;
    }else{
        cout << "alright then." << endl;
    }
    
    return 0;
}