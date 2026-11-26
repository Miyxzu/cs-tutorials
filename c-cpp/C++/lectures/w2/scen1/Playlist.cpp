#include <iostream>
#include <string>
#include <vector>
#include "Playlist.hpp"
using namespace std;

Playlist::Playlist() : name("N/A"), list() {}
Playlist::Playlist(const string &n) : name(n), list() {}

void Playlist::operator+=(const Track &t)
{
    list.push_back(t);
}

bool Playlist::addTrack(const Track &t)
{
    bool chk = false;

    try
    {
        list.push_back(t);
        chk = true;
    }
    catch(const std::exception& e)
    {
        std::cerr << e.what() << '\n';
        chk = false;
    }
    
    return chk;
}

void Playlist::display()
{
    for (Track t : list)
    {
        cout << "Track " << t.getTitle() << " by " << t.getArtist() << " (" << t.getDuration() << "s)" << endl;
    }
}