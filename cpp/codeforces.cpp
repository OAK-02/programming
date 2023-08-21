#include <iostream>
using namespace std;

int main(void) {
    int a, b;
    cin >> a;
    cin >> b;
    int count = 0;
    while (a <= b) {
        ++count;
        a = a*3;
        b = b*2;
    }
    cout << count << endl;
    return 0;
}


