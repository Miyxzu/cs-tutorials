#include <iostream>
#include <cstdlib>
#include <time.h>
using namespace std;

int main() {
    srand(time(0));
    int randNum = (rand() % (25-1) + 1);
    int num = 1;
    
    while(num != 0){
        cout << "Enter a number between 1 - 25: ";
        cin >> num;

        if(num > randNum){
            cout << "The number is lower. Try again\n";
        }else if(num < randNum){
            cout << "The number is higher. Try again\n";
        }else{
            cout << "Congratulations! You did it!";
            break;
        }
    }
    return 0;
}