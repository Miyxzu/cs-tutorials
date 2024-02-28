using System.Collections;
namespace CSharp
{
    public class Twitch
    {
        private Random? rand;
        private ArrayList? streams;
        private int numStreamers;

        public Twitch()
        {
            this.rand = new Random();
            this.streams = new ArrayList();
            this.numStreamers = 0;
        }

        public Boolean addStreamer(String? name, String? game, String? title)
        {
            if (name != null || game != null || title != null)
            {
                foreach (Streamer streamer in streams)
                {
                    if(streamer.getName() == name)
                    {
                        return false;
                    }
                }
                streams.Add(new Streamer(name, game, title));
                numStreamers++;
                return true;
            }
            else
            {
                return false;
            }
        }

        public Boolean removeStreamer(String name)
        {
            if (name != null)
            {
                foreach (Streamer streamer in streams)
                {
                    if (streamer.getName() == name)
                    {
                        streams.Remove(streamer);
                        numStreamers--;
                        return true;
                    }
                }
                return false;
            }
            else
            {
                return false;
            }
        }

        public String? getStreamer(String name)
        {
            if (name != null)
            {
                foreach (Streamer streamer in streams)
                {
                    if (streamer.getName() == name)
                    {
                        return streamer?.toString();
                    }
                }
                return "Streamer not found";
            }
            else
            {
                return "Please Enter a Channel Name";
            }
        }

        public void printStreamers()
        {
            foreach (Streamer streamer in streams)
            {
                if(streamer != null && streamer.isLive() == true)
                {
                    System.Console.WriteLine(streamer.toString());
                }
            }
            Console.WriteLine(numStreamers + " streamers streaming currently");
        }

        public void followedLive()
        {
            foreach (Streamer streamer in streams)
            {
                if (streamer != null && streamer.isLive() == true && streamer.isFollowing() == true)
                {
                    System.Console.WriteLine(streamer.toString());
                }
            }
        }

        public ArrayList showListFollowed()
        {
            ArrayList followed = new ArrayList();
            foreach (Streamer streamer in streams)
            {
                if (streamer != null && streamer.isFollowing() == true)
                {
                    followed.Add(streamer);
                }
            }
            return followed;
        }

        public void changeTitle(String name, String title)
        {
            if (name != null || title != null)
            {
                foreach (Streamer streamer in streams)
                {
                    if (streamer.getName() == name)
                    {
                        streamer.setTitle(title);
                        System.Console.WriteLine(name + ": " + title);
                    }
                }
            }
            else
            {
                System.Console.WriteLine("Please Enter a Channel Name and/or Title");
            }
        }

        public void changeGame(String name, String game)
        {
            if (name != null || game != null)
            {
                foreach (Streamer streamer in streams)
                {
                    if (streamer.getName() == name)
                    {
                        streamer.setGame(game);
                        System.Console.WriteLine(name + " is now playing " + game);
                    }
                }
            }
            else
            {
                System.Console.WriteLine("Please Enter a Channel Name and/or Game");
            }
        }

        public String giftSubs(String name, int numSubs)
        {
            if (name != null || numSubs > 0)
            {
                foreach (Streamer streamer in streams)
                {
                    if (streamer.getName() == name)
                    {
                        streamer.setSubCount(streamer.getSubCount() + numSubs);
                        return name + " has received " + numSubs + " new subs!";
                    }
                }
                return "Streamer not found";
            }
            else
            {
                return "Please Enter a Channel Name and/or Number of Subs";
            }
        }

        public String addViewers(String name)
        {
            if (name != null)
            {
                foreach (Streamer streamer in streams)
                {
                    if (streamer.getName() == name)
                    {   
                        int num = rand.Next(100, 75000);
                        if(streamer.getViewers() + num > 100000)
                        {
                            return "you are asking for TOO many viewers man";
                        }
                        else
                        {
                            streamer.setViewers(streamer.getViewers() + num);
                            return name + " has " + streamer.getViewers() + " viewers!";
                        }
                    }
                }
                return "Streamer not found";
            }
            else
            {
                return "Please Enter a Channel Name";
            }
        }

        public Streamer getHighestSubbed()
        {
            Streamer? highest = null;
            foreach (Streamer streamer in streams)
            {
                if (streamer != null && streamer.getSubCount() > highest.getSubCount())
                {
                    highest = streamer;
                }
            }
            return highest;
        }

        public Streamer getHighestWatched()
        {
            Streamer? highest = null;
            foreach (Streamer streamer in streams)
            {
                if (streamer != null && streamer.getViewers() > highest.getViewers())
                {
                    highest = streamer;
                }
            }
            return highest;
        }

        public ArrayList sortHighLowViewers()
        {
            ArrayList live = new ArrayList();
            foreach (Streamer streamer in streams)
            {
                if (streamer != null && streamer.isLive() == true)
                {
                    live.Add(streamer);
                }
            }

            ArrayList sorted = new ArrayList();
            for (int i = 0; i < live.Count; i++)
            {
                if(sorted.Count == 0)
                {
                    sorted.Add(live[i]);
                }
                else
                {
                    for (int j = 0; j < sorted.Count; j++)
                    {
                        if(((Streamer)live[i]).getViewers() > ((Streamer)sorted[j]).getViewers())
                        {
                            sorted.Insert(j, live[i]);
                            break;
                        }
                        else if(j == sorted.Count - 1)
                        {
                            sorted.Add(live[i]);
                            break;
                        }
                    }
                }
            }
            return sorted;
        }

        public ArrayList sortHighLowSubs()
        {
            ArrayList live = new ArrayList();
            foreach (Streamer streamer in streams)
            {
                if (streamer != null && streamer.isLive() == true)
                {
                    live.Add(streamer);
                }
            }

            ArrayList sorted = new ArrayList();
            for (var i = 0; i < live.Count; i++)
            {
                if (sorted.Count == 0)
                {
                    sorted.Add(live[i]);
                }
                else
                {
                    for (var j = 0; j < sorted.Count; j++)
                    {
                        if (((Streamer)live[i]).getSubCount() > ((Streamer)sorted[j]).getSubCount())
                        {
                            sorted.Insert(j, live[i]);
                            break;
                        }
                        else if (j == sorted.Count - 1)
                        {
                            sorted.Add(live[i]);
                            break;
                        }
                    }
                }
            }
            return sorted;
        }

        public ArrayList searchByGame(String game)
        {
            ArrayList live = new ArrayList();
            foreach (Streamer streamer in streams)
            {
                if (streamer != null && streamer.isLive() == true && streamer.getGame() == game)
                {
                    live.Add(streamer);
                }
            }
            return live;
        }

        public String getMostPopularGame()
        {
            int views = 0;
            String game = "";
            foreach (Streamer streamer in streams)
            {
                if (streamer != null && streamer.isLive() == true)
                {   
                    if(streamer.getViewers() > views)
                    {
                        views = streamer.getViewers();
                        game = streamer.getGame();
                    }
                    if(game == streamer.getGame())
                    {
                        views += streamer.getViewers();
                    }
                }
            }
            return String.Format("{0, -17} {1, -17}", game, views);
        }

        public int getTotalConcurrentViewers()
        {
            int total = 0;
            foreach (Streamer streamer in streams)
            {
                if (streamer != null && streamer.isLive() == true)
                {
                    total += streamer.getViewers();
                }
            }
            return total;
        }

        public String startStream(String name)
        {
            if (name != null)
            {
                foreach (Streamer streamer in streams)
                {
                    if (streamer.getName() == name)
                    {
                        if (streamer.isLive() == true)
                        {
                            return name + " is already live!";
                        }
                        else
                        {
                            streamer.setLive(true);
                            streamer.setViewers(0);
                            numStreamers++;
                            return name + " is now live!";
                        }
                    }
                }
                return "Streamer not found";
            }
            else
            {
                return "Please Enter a Channel Name";
            }
        }

        public String endStream(String name)
        {
            if (name != null)
            {
                foreach (Streamer streamer in streams)
                {
                    if (streamer.getName() == name)
                    {
                        if (streamer.isLive() == false)
                        {
                            return name + " is not live!";
                        }
                        else
                        {
                            streamer.setLive(false);
                            streamer.setViewers(0);
                            numStreamers--;
                            return name + " is no longer live!";
                        }
                    }
                }
                return "Streamer not found";
            }
            else
            {
                return "Please Enter a Channel Name";
            }
        }

        public String followStreamer(String name)
        {
            if (name != null)
            {
                foreach (Streamer streamer in streams)
                {
                    if (streamer.getName() == name)
                    {
                        if (streamer.isLive() == true)
                        {
                            return "You are already following " + name;
                        }
                        else
                        {
                            streamer.setFollowing(true);
                            return "You are now following " + name;
                        }
                    }
                }
                return "Streamer not found";
            }
            else
            {
                return "Please Enter a Channel Name";
            }
        }

        public String unfollowStreamer(String name)
        {
            if (name != null)
            {
                foreach (Streamer streamer in streams)
                {
                    if (streamer.getName() == name)
                    {
                        if (streamer.isLive() == false)
                        {
                            return "You are not following " + name;
                        }
                        else
                        {
                            streamer.setFollowing(false);
                            return "You are no longer following " + name;
                        }
                    }
                }
                return "Streamer not found";
            }
            else
            {
                return "Please Enter a Channel Name";
            }
        }
    }
}