#include <iostream>
using namespace std;

int main(int argc, const char** argv) {
    string name;

    cout << "Enter yoour name: ";
    getline(cin, name);

    if(name.length() > 16){
        cout << "Your name cannot be over 16 characters\n";

    }else if(name.empty()){
        cout << "This is empty. Enter a name\n";
    }else{
        cout << "Welcome " << name << endl;
    }

    return 0;
}