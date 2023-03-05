#include <iostream>
#include <cstdlib>
#include <time.h>
using namespace std;

int main() {
    int high;
    int low;

    cout << "Enter a beginning number to the range of the game: ";
    cin >> low;

    cout << "Enter a ending number to the range of the game: ";
    cin >> high;

    srand(time(0));
    int randNum = (rand() % (high-low) + 1);
    int num = 1;
    int count;
    
    while(num != 0){
        cout << "Enter a number between " << low << " - " << high << ": ";
        cin >> num;
        if(num <= high && num >= low)
            if(num > randNum){
                cout << "The number is lower. Try again\n";
                count++;
            }else if(num < randNum){
                cout << "The number is higher. Try again\n";
                count++;
            }else{
                cout << "Congratulations! You did it!\n";
                cout << "It took you " << count << " amount of tries!";
                break;
            }else{
                cout << "This number exceeds the range, enter a number within the range\n";
            }
    }
    return 0;
}