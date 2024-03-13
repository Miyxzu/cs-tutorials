public class checking {
    public static void main(String[] args) {
        
    }

    int avgScore(int scores[]) {
        int total = 0;
        for (int i = 0; i < 10; i++) {
            total += scores[i];
        }
        return total / 10;
    }

    int bSearchNames(String n, String names[], Double Scores[]) {
        int x = -1, l = 0, r = names.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if(n.compareTo(names[mid]) == 0) {
                x = mid;
                break; // Exit the loop once the name is found
            }
            if(n.compareTo(names[mid]) > 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return x;
    }

    double getHighestScores(double Scores[][]) {
        double count = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 8; j++) {
                if(Scores[i][j] == 10.0) {
                    count++;
                }
            }
        }
        return count;
    }

    void getFinalScores(double[][] Scores, String[] names) {
        double[] finalScore = new double[20];
        for (int i = 0; i < 20; i++) {
            double highest = Scores[i][0];
            double lowest = Scores[i][0];
            double total = 0;
            for (int j = 0; j < 8; j++) {
                if (Scores[i][j] > highest) {
                    highest = Scores[i][j];
                }
                if (Scores[i][j] < lowest) {
                    lowest = Scores[i][j];
                }
                total += Scores[i][j];
            }
            finalScore[i] = (total - highest - lowest) / 6;
        }
        for (int i = 0; i < 20; i++) {
            System.out.println("Name: " + names[i] + "| Final Score: " + finalScore[i]);
        }
    }
}
