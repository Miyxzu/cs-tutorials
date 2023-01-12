#include <iostream>

using namespace std;

int main(int argc, char const *argv[])
{
    char op;
    double num1;
    double num2;
    double result;

    cout << "********** CALCULATOR **********\n";

    cout << "Enter an Operator: ";
    cin >> op;

    cout << "Enter #1: ";
    cin >> num1;

    cout << "Enter #2: ";
    cin >> num2;

    switch(op){
        case '+':
            result = num1 + num2;
            cout << "Result: " << result << endl;
            break;
        case '-':
            result = num1 - num2;
            cout << "Result: " << result << endl;
            break;
        case '*':
            result = num1 * num2;
            cout << "Result: " << result << endl;
            break;
        case '/':
            result = num1 / num2;
            cout << "Result: " << result << endl;
            break;
        default:
            cout << "Not a valid operator" << endl;
            break;
    }

    cout << "********************************\n";
    return 0;
}
