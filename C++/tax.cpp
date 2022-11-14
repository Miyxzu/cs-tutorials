#include <iostream>

using namespace std;

int main(int argc, char const *argv[])
{
    cout << "Enter Sales Amount: ";
    double sales;
    cin >> sales;
    
    cout << "Enter County Tax Percentage: ";
    double countyPer;
    cin >> countyPer;
    countyPer = countyPer / 100;
    double county = sales * countyPer;

    cout << "Enter State Tax Percentage: ";
    double statePer;
    cin >> statePer;
    statePer = statePer / 100;
    double state = sales * statePer;

    double totaltax = state + county;

    cout << "Sales: $" << sales << endl
         << "County Tax: $" << county << endl
         << "State Tax: $" << state << endl
         << "Total Tax: $" << totaltax << endl;
    return 0;
}