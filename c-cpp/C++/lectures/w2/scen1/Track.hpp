#if !defined(TRACK_H)
#define TRACK_H

#include <string>
using namespace std;

class Track
{
private:
    string title;
    string artist;
    int duration;

public:
    Track();
    Track(const string &t, const string &a, int d);
    Track(const Track &other);
    virtual ~Track() {}

    string getTitle() const;
    string getArtist() const;
    int getDuration() const;

    void setTitle(const string &t);
    void setArtist(const string &a);
    void setDuration(const int &d);

    bool operator<(const Track& other) const;
};

#endif // TRACK_H
