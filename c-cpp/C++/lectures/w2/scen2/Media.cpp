#include <iostream>
#include <string>
using namespace std;

class Media
{
private:
    string title;
    int duration;
public:
    Media();
    Media(const string &t, const int &d);
    virtual ~Media() {}

    virtual void play() = 0;
};

Media::Media() : title("N/A"), duration(0) {}

Media::Media(const string &t, const int &d) : title(t), duration(d) {}

void Media::play()
{
    cout << "In Media.play()" << endl;
}



class Audio : public Media
{
private:
    string format;
public:
    Audio() {}
    Audio(const string &f);
    Audio(const string &t, const int &d, const string &f);
    virtual ~Audio() {}
    virtual void play() override;
};

Audio::Audio() : Media(), format("N/A") {}

Audio::Audio(const string &f) : Media(), format(f) {}

Audio::Audio(const string &t, const int &d, const string &f) : Media(t, d), format(f) {}

Audio::~Audio() {}

void Audio::play()
{
    cout << "Playing audio now in format " << format << endl;
}



class Video : public Media
{
private:
    string resolution;
public:
    Video() {}
    Video(const string &r);
    Video(const string &t, const int &d, const string &r);
    virtual ~Video() {}
    virtual void play() override;
};

Video::Video() : Media(), resolution("N/A") {}

Video::Video(const string &r) : Media(), resolution(r) {}

Video::Video(const string &t, const int &d, const string &r) : Media(t, d), resolution(r) {}

Video::~Video() {}

void Video::play()
{
    cout << "Playing video now with resolution " << resolution << endl;
}



class Podcast : public Media
{
private:
    string transcript;
public:
    Podcast() {}
    Podcast(const string &tr);
    Podcast(const string &t, const int &d, const string &tr);
    virtual ~Podcast() {}
    virtual void play() override;
};

Podcast::Podcast() : Media(), transcript("N/A") {}

Podcast::Podcast(const string &tr) : Media(), transcript(tr) {}

Podcast::Podcast(const string &t, const int &d, const string &tr) : Media(t, d), transcript(tr) {}

Podcast::~Podcast() {}

void Podcast::play()
{
    cout << "Displaying podcast transcript: " << transcript << endl;
}
