#include <iostream>

using namespace std;

int main(int argc, char const *argv[])
{
    char grade;

    cout << "Enter grade: ";
    cin >> grade;

    switch(grade){
        case 'A':
            cout << "Well Done, Keep it that way";
            break;
        case 'B':
            cout << "Good Job, Do better";
            break;
        case 'C':
            cout << "Underaverage but not terrible";
            break;
        case 'D':
            cout << "You sum ass ngl";
            break;
        case 'F':
            cout << "You fucking suck";
            break;
    }

    return 0;
}
