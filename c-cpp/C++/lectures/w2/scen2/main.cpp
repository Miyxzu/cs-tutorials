#include "Media.cpp"
#include <iostream>
using namespace std;

int main(int argc, char const *argv[])
{
    Audio audio("mp3");
    Video video("1080p");
    Podcast podcast("This is a transcript");

    audio.play();
    video.play();
    podcast.play();
    return 0;
}
