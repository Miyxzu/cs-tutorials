#define _CRT_SECURE_NO_WARNINGS 1
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

struct movie
{
    char name[37];
    char date[12];
    char genre[25];
    int rating;
    double runtime;
};

typedef struct movie Mov;

void clearInputBuffer()
{
    int c;
    while ((c = getchar()) != '\n' && c != EOF);
}

void displayMovieByValue(Mov m)
{
    printf("Movie Name: %sRelease Date: %sMovie Genre: %sRating (1-10): %d\nMovie Runtime: %.2f\n", m.name, m.date, m.genre, m.rating, m.runtime);
}

void displayMovieByRef(Mov* m)
{
    printf("Movie Name: %sRelease Date: %sMovie Genre: %sRating (1-10): %d\nMovie Runtime: %.2f\n", (*m).name, (*m).date, (*m).genre, (*m).rating, (*m).runtime);
}

int main(int argc, char const *argv[])
{
    Mov *pMov = NULL;
    Mov m;
    bool ratingCheck;
    bool runtimeCheck;

    printf("Movie Name >> ");
    fgets(m.name, sizeof(m.name), stdin);

    printf("Release Date (dd/mm/yyyy) >> ");
    fgets(m.date, sizeof(m.date), stdin);

    printf("Movie Genre(s) >> ");
    fgets(m.genre, sizeof(m.genre), stdin);

    ratingCheck = false;
    while (!ratingCheck)
    {
        printf("Movie Rating (1-10) >> ");
        scanf("%d", &m.rating);
        if ((m.rating <= 10) && (m.rating >= 1))
        {
            ratingCheck = true;
        }
        else
        {
            printf("Please enter a valid rating\n");
        }
    }

    runtimeCheck = false;
    while (!runtimeCheck)
    {
        printf("Movie Runtime (min) >> ");
        scanf("%lf", &m.runtime);
        clearInputBuffer();
        if (m.runtime > 0)
        {
            runtimeCheck = true;
        }
        else
        {
            printf("Please enter a valid runtime\n");
        }
    }

    printf("\nPre-Modified Fields\n");
    displayMovieByValue(m);

    pMov = &m;
    printf("Memory address of variable 'm' is %p.\n", &pMov);

    printf("\nUpdate Fields\n");
    printf("Movie Name >> ");
    fgets((*pMov).name, sizeof((*pMov).name), stdin);

    printf("Release Date (dd/mm/yyyy) >> ");
    fgets((*pMov).date, sizeof((*pMov).date), stdin);

    printf("Movie Genre >> ");
    fgets((*pMov).genre, sizeof((*pMov).genre), stdin);

    ratingCheck = false;
    while (!ratingCheck)
    {
        printf("Movie Rating (1-10) >> ");
        scanf("%d", &(*pMov).rating);
        if (((*pMov).rating <= 10) && ((*pMov).rating >= 1))
        {
            ratingCheck = true;
        }
        else
        {
            printf("Please enter a valid rating\n");
        }
    }

    runtimeCheck = false;
    while (!runtimeCheck)
    {
        printf("Movie Runtime (min) >> ");
        scanf("%lf", &(*pMov).runtime);
        clearInputBuffer();
        if ((*pMov).runtime > 0)
        {
            runtimeCheck = true;
        }
        else
        {
            printf("Please enter a valid runtime\n");
        }
    }

    printf("\nPost-Modified Fields\n");
    displayMovieByRef(&m);

    return 0;
}