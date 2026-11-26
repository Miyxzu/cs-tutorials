#include <string>
#include "Track.hpp"
using namespace std;

Track::Track() : title("N/A"), artist("N/A"), duration(0) {}

Track::Track(const string &t, const string &a, int d) : title(t), artist(a), duration(d) {}

Track::Track(const Track &other) : title(other.title), artist(other.artist), duration(other.duration) {}

string Track::getTitle() const
{
    return title;
}

string Track::getArtist() const
{
    return artist;
}

int Track::getDuration() const
{
    return duration;
}

void Track::setTitle(const string &t)
{
    title = t;
}

void Track::setArtist(const string &a)
{
    artist = a;
}

void Track::setDuration(const int &d)
{
    duration = d;
}

bool Track::operator<(const Track &other) const
{
    return duration < other.duration;
}
