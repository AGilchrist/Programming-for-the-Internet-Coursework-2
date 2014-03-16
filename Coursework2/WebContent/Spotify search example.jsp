#include <string.h>

#include "spshell.h"
#include "cmd.h"


static void print_album(sp_album *album)
{
    printf("  Album \"%s\" (%d)\n",
           sp_album_name(album),
           sp_album_year(album));
}

static void print_artist(sp_artist *artist)
{
    printf("  Artist \"%s\"\n", sp_artist_name(artist));
}

static void print_search(sp_search *search)
{
    int i;

    printf("Query          : %s\n", sp_search_query(search));
    printf("Did you mean   : %s\n", sp_search_did_you_mean(search));
    printf("Tracks in total: %d\n", sp_search_total_tracks(search));
    puts("");

    for (i = 0; i < sp_search_num_tracks(search); ++i)
        print_track(sp_search_track(search, i));

    puts("");

    for (i = 0; i < sp_search_num_albums(search); ++i)
        print_album(sp_search_album(search, i));

    puts("");

    for (i = 0; i < sp_search_num_artists(search); ++i)
        print_artist(sp_search_artist(search, i));

    puts("");

    for (i = 0; i < sp_search_num_playlists(search); ++i) {
        // print some readily available metadata, the rest will
        // be available from the sp_playlist object loaded through
        // sp_search_playlist().
        printf("  Playlist \"%s\"\n", sp_search_playlist_name(search, i));
    }
}

static void SP_CALLCONV search_complete(sp_search *search, void *userdata)
{
    if (sp_search_error(search) == SP_ERROR_OK)
        print_search(search);
    else
        fprintf(stderr, "Failed to search: %s\n",
                sp_error_message(sp_search_error(search)));

    sp_search_release(search);
    cmd_done();
}



static void search_usage(void)
{
    fprintf(stderr, "Usage: search <query>\n");
}


int cmd_search(int argc, char **argv)
{
    char query[1024];
    int i;

    if (argc < 2) {
        search_usage();
        return -1;
    }

    query[0] = 0;
    for(i = 1; i < argc; i++)
        snprintf(query + strlen(query), sizeof(query) - strlen(query), "%s%s",
             i == 1 ? "" : " ", argv[i]);

    sp_search_create(g_session, query, 0, 100, 0, 100, 0, 100, 0, 100, SP_SEARCH_STANDARD, &search_complete, NULL);
    return 0;
}


int cmd_whatsnew(int argc, char **argv)
{
    sp_search_create(g_session, "tag:new", 0, 0, 0, 250, 0, 0, 0, 0, SP_SEARCH_STANDARD, &search_complete, NULL);
    return 0;
}