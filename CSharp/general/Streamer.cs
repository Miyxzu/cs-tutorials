namespace CSharp;
public class Streamer
{
    private String? name;
    private String? game;
    private String? title;
    private int viewers;
    private int subCount;
    private Boolean Live;
    private Boolean Partner;
    private Boolean Affiliate;
    private Boolean following;

    public Streamer(String name, String game, String title)
    {
        this.name = name;
        this.game = game;
        this.title = title;
        this.viewers = 0;
        this.subCount = 0;
        this.Live = true;
        this.Partner = false;
        this.Affiliate = false;
        this.following = false;
    }

    public String? getName()
    {
        return this.name;
    }

    public String? getGame()
    {
        return this.game;
    }

    public String? getTitle()
    {
        return this.title;
    }

    public int getViewers()
    {
        return this.viewers;
    }

    public int getSubCount()
    {
        return this.subCount;
    }

    public Boolean isLive()
    {
        return this.Live;
    }

    public Boolean isPartner()
    {
        return this.Partner;
    }

    public Boolean isAffiliate()
    {
        return this.Affiliate;
    }

    public Boolean isFollowing()
    {
        return this.following;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setGame(String game)
    {
        this.game = game;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setViewers(int viewers)
    {
        this.viewers = viewers;
    }

    public void setSubCount(int subCount)
    {
        this.subCount = subCount;
    }

    public void setLive(Boolean isLive)
    {
        this.Live = isLive;
    }

    public void setPartner(Boolean isPartner)
    {
        this.Partner = isPartner;
    }

    public void setAffiliate(Boolean isAffiliate)
    {
        this.Affiliate = isAffiliate;
    }

    public void setFollowing(Boolean following)
    {
        this.following = following;
    }

    public String? toString()
    {
        String? tempTitle = title;
        String? tempGame = game;
        String? tempName = name;
        String dot = "...";

        if (title?.IndexOf((char)17) != -1)
        {
            tempTitle = title?.Substring(0, 17);
            tempTitle += dot;
        }
        if (game?.IndexOf((char)17) != -1)
        {
            tempGame = game?.Substring(0, 17);
            tempGame += dot;
        }
        if (name?.IndexOf((char)17) != -1)
        {
            tempName = name?.Substring(0, 17);
            tempName += dot;
        }

        return '\n' + String.Format("{0,-17} {1,-17} {2,-17} {3,-5} {4,-5}",
                                 tempName, tempGame, tempTitle, viewers, subCount);
    }
}