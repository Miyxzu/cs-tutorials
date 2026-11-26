#include <iostream>
#include <vector>
#include <cctype>
using namespace std;

double calc(vector<int> vec, int n)
{
    double toCalc = 0;
    
    if (n == 1)
    {
        for (int v : vec)
        {
            toCalc += v;
        }

        toCalc = toCalc / vec.size();
    }

    if (n == 2)
    {
        for (int v : vec)
        {
            if (toCalc < v || toCalc == 0)
            {
                toCalc = v;
            }
        }
    }

    if (n == 3)
    {
        for (int v : vec)
        {
            if (toCalc > v || toCalc == 0)
            {
                toCalc = v;
            }
        }
    }

    return toCalc;
}

int main(int argc, char const *argv[])
{
    vector<int> vec;
    int i, grade = 0;

    char flag = 'y';
    cout << "Would you like to specify the number of grades? [(Y)/n]: ";
    cin >> flag;

    if (tolower(flag) == 'y')
    {
        int limit;
        cout << "Number of grades to be read: ";
        cin >> limit;

        for (i = 0; i < limit; i++)
        {
            cout << "Grade " << i + 1 << ": ";
            cin >> grade;
            vec.push_back(grade);
        }
    }

    if (tolower(flag) == 'n')
    {
        i = 1;

        cout << "Enter grades; -1 to exit" << endl;
        while (grade != -1)
        {
            cout << "Grade " << i++ << ": ";
            cin >> grade;
            if (grade == -1) break;
            vec.push_back(grade);
        }
    }

    cout << "\nStats" << endl;
    cout << "Average: " << calc(vec, 1) << endl;
    cout << "Highest: " << calc(vec, 2) << endl;
    cout << "Lowest: " << calc(vec, 3) << endl;

    return 0;
}
