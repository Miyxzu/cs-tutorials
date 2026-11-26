#if !defined(PLAYLIST_H)
#define PLAYLIST_H

#include <string>
#include <vector>
#include "Track.hpp"
using namespace std;

class Playlist
{
private:
    string name;
    vector<Track> list;
public:
    Playlist();
    Playlist(const string &n);
    virtual ~Playlist() {}

    void operator+=(const Track &t);

    bool addTrack(const Track &t);
    void display();
};

#endif // PLAYLIST_H
