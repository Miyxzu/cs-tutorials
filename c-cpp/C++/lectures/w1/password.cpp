#include <iostream>
#include <cctype>
#include <vector>
#include <algorithm>
#include <string>
using namespace std;

vector<string> isValidPassword(string s)
{
    vector<string> missing;

    if (s.size() < 8 || s.size() > 20)
    {
        missing.push_back("Character Length");
    }

    if (!any_of(s.begin(), s.end(), [](unsigned char ch) { return isupper(ch); }))
    {
        missing.push_back("Uppercase letter (i.e. ABC)");
    }

    if (!any_of(s.begin(), s.end(), [](unsigned char ch) { return isdigit(ch); }))
    {
        missing.push_back("Digit (i.e. 123)");
    }

    if (!any_of(s.begin(), s.end(), [](unsigned char ch) { return ispunct(ch); }))
    {
        missing.push_back("Punctuation/Symbol (i.e. !@#)");
    }
    
    return missing;
}

int main(int argc, char const *argv[])
{
    string pass;

    cout << "Enter a password: ";
    cin >> pass;

    if (isValidPassword(pass).empty())
    {
        cout << "Strong Password";
    }
    else
    {
        std::cout << "Weak password. Missing:" << endl;
        for (string s : isValidPassword(pass))
            cout << "- " << s << endl;
    }

    return 0;
}
