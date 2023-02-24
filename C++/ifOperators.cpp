#include <iostream>
using namespace std;

int main(int argc, const char** argv) {
    cout << "Enter a number: "
    int num;
    cin >> num

    string nonMulti = "Not a multiple of two\n\n";
    string multiOf = "Multiple of 2\n\n";

    num % 2 ? multiOf << endl << endl : nonMulti << endl << endl;

    cout << "Enter the temperature: ";
    int temp;
    cin >> temp;

    string cold = "That temperature is cold as shit"
    string hot = "That temperature is hot as hell"
    string middle = "The temperature is good"


    temp >= 2 || temp <= 28 ? middle : 
    temp < 2  ? cold : hot;
}