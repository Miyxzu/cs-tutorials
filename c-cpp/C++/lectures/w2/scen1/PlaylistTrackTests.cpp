#include <cassert>
#include <iostream>
#include <sstream>
#include <string>

#include "Playlist.hpp"
#include "Track.hpp"

namespace
{
std::string displayPlaylist(Playlist &playlist)
{
    std::ostringstream output;
    std::streambuf *original = std::cout.rdbuf(output.rdbuf());
    playlist.display();
    std::cout.rdbuf(original);
    return output.str();
}

void testTrackDefaults()
{
    Track track;

    assert(track.getTitle() == "N/A");
    assert(track.getArtist() == "N/A");
    assert(track.getDuration() == 0);
}

void testTrackValuesAndSetters()
{
    Track track("Blue in Green", "Miles Davis", 337);

    assert(track.getTitle() == "Blue in Green");
    assert(track.getArtist() == "Miles Davis");
    assert(track.getDuration() == 337);

    track.setTitle("So What");
    track.setArtist("Miles Davis Quintet");
    track.setDuration(562);

    assert(track.getTitle() == "So What");
    assert(track.getArtist() == "Miles Davis Quintet");
    assert(track.getDuration() == 562);
}

void testTrackCopyAndOrdering()
{
    Track original("Take Five", "The Dave Brubeck Quartet", 324);
    Track copy(original);
    Track longer("Blue Rondo a la Turk", "The Dave Brubeck Quartet", 376);

    assert(copy.getTitle() == original.getTitle());
    assert(copy.getArtist() == original.getArtist());
    assert(copy.getDuration() == original.getDuration());
    assert(original < longer);
    assert(!(longer < original));
}

void testPlaylistAddTrack()
{
    Playlist playlist("Jazz");
    Track track("So What", "Miles Davis", 562);

    assert(playlist.addTrack(track));
    assert(displayPlaylist(playlist) == "Track So What by Miles Davis (562s)\n");
}

void testPlaylistAdditionOperator()
{
    Playlist playlist("Favorites");
    Track first("Blue in Green", "Miles Davis", 337);
    Track second("Take Five", "The Dave Brubeck Quartet", 324);

    playlist += first;
    playlist += second;

    assert(displayPlaylist(playlist) ==
           "Track Blue in Green by Miles Davis (337s)\n"
           "Track Take Five by The Dave Brubeck Quartet (324s)\n");
}
}

int main()
{
    testTrackDefaults();
    testTrackValuesAndSetters();
    testTrackCopyAndOrdering();
    testPlaylistAddTrack();
    testPlaylistAdditionOperator();
}