import processing.core.PApplet;
import de.umass.lastfm.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import processing.core.PFont;
import processing.core.PImage;


public class LastFMUI extends PApplet {
    static Scanner s = new Scanner(System.in);


    public void settings(){
        size(1500, 900);
    }
    float buttonX;
    float buttonY;
    float buttonxSize;
    float buttonySize;
    float rectxSize;
    float rectySize;
    float rectX;
    float rectY;
    int screen;

    String period;

    ArrayList<String> topTracks = new ArrayList<>();
    ArrayList<String> topArtists = new ArrayList<>();
    ArrayList<String> topAlbumsP = new ArrayList<>();
    ArrayList<String> funDetails = new ArrayList<>();
    ArrayList<String> recArtists = new ArrayList<>();
    ArrayList<String> recSongs = new ArrayList<>();
    PFont myFont;

    PImage cat;
    PImage logo;




    public void setup(){
        //initiate and set variables


        cat = loadImage("fatCat.jpg");
        logo = loadImage("fmLogo.png");

        buttonxSize  = 200;
        buttonySize = 100;
        buttonX = (width/2)-(buttonxSize/2);
        buttonY = 600;

        rectxSize  = 300;
        rectySize = 150;
        rectX = (width/2)-(rectxSize/2);
        rectY = 200;

        myFont = createFont("Georgia", 32);
        textFont(myFont);

        screen = 0;

        ImageGetters imageGenerator = new ImageGetters();

        int valid = -1;
        while(valid == -1) {
            //valid is an int for the while loop. The while loop makes sure the try catch continues running until it works. If try doesn't completely run,
            //catch will make sure valid = -1 and while loop will keep running
            //if try runs through completely, it will set valid = 1 and while loop will finish running.
            //This while loop with int valid is to make sure program doesn't crash if we enter an invalid username




            try {
                String key = "5a78b2b840811b4ab8d26309e2b68ee6"; //this is the key used in the Last.fm API examples
                Scanner s = new Scanner(System.in);
                String user = "Roy19110"; //initializes user and period.
                period = "";


                System.out.println("What is your Last FM Name?");
                System.out.println("EXAMPLE USERNAMES:");
                System.out.println("silverhawk79");
                System.out.println("usrname7");
                System.out.println("Amixor33");

                user = s.nextLine();

                Collection<Artist> Artists = User.getTopArtists(user, Period.OVERALL, key);
                Collection<Track> tracks = User.getTopTracks(user, Period.OVERALL, key);
                Collection<Album> albums = User.getTopAlbums(user, Period.OVERALL, key);
                Collection<Artist> CountryA = Geo.getTopArtists("United States", key);
                Collection<Track> CountryT = Geo.getTopTracks("United States", key);

                System.out.println();
                System.out.println("Checking if username exists...");

                ArrayList<Artist> check = new ArrayList<>(Artists);
                Artist checkCrash = check.get(0);
                System.out.println("Username exists: " + user);
                System.out.println();


                System.out.println("What period of time would you like to observe?");
                System.out.println("A: Last week  B: Last month  C: Last 3 months  D: Last 6 months  E: Last year  F: Overall");
                period = s.nextLine();


                while (!(period.equals("A") || period.equals("a") || period.equals("B") || period.equals("b") || period.equals("C") ||
                        period.equals("c") || period.equals("D") || period.equals("d") || period.equals("E") || period.equals("e") || period.equals("F") || period.equals("f"))) {
                    //checks for valid input, if not one of the given options(not case sensitive) asks for different input
                    System.out.println("Try again with valid input!");
                    System.out.println("What period of time would you like to observe?");
                    System.out.println("A: Last week  B: Last month  C: Last 3 months  D: Last 6 months  E: Last year  F: Overall");
                    period = s.nextLine();
                }



                //program will set user's top artists, tracks, and albums based on the
                //time frame of the period they chose.
                if (period.equals("A") || period.equals("a")) {
                    Artists = User.getTopArtists(user, Period.WEEK, key);
                    tracks = User.getTopTracks(user, Period.WEEK, key);
                    albums = User.getTopAlbums(user, Period.WEEK, key);
                    period = "last week";
                } else if (period.equals("B") || period.equals("b")) {
                    Artists = User.getTopArtists(user, Period.ONE_MONTH, key);
                    tracks = User.getTopTracks(user, Period.ONE_MONTH, key);
                    albums = User.getTopAlbums(user, Period.ONE_MONTH, key);
                    period = "last month";
                } else if (period.equals("C") || period.equals("c")) {
                    Artists = User.getTopArtists(user, Period.THREE_MONTHS, key);
                    tracks = User.getTopTracks(user, Period.THREE_MONTHS, key);
                    albums = User.getTopAlbums(user, Period.THREE_MONTHS, key);
                    period = "Last 3 months";
                } else if (period.equals("D") || period.equals("d")) {
                    Artists = User.getTopArtists(user, Period.SIX_MONTHS, key);
                    tracks = User.getTopTracks(user, Period.SIX_MONTHS, key);
                    albums = User.getTopAlbums(user, Period.SIX_MONTHS, key);
                    period = "last six months";
                } else if (period.equals("E") || period.equals("e")) {
                    Artists = User.getTopArtists(user, Period.TWELVE_MONTHS, key);
                    tracks = User.getTopTracks(user, Period.TWELVE_MONTHS, key);
                    albums = User.getTopAlbums(user, Period.TWELVE_MONTHS, key);
                    period = "last year";
                } else if (period.equals("F") || period.equals("f")) {
                    Artists = User.getTopArtists(user, Period.OVERALL, key);
                    tracks = User.getTopTracks(user, Period.OVERALL, key);
                    albums = User.getTopAlbums(user, Period.OVERALL, key);
                    period = "overall";
                }




                //These for each loops take the collection datatypes and transfer them into String arrays for easy access when printing later on
                String[] countryArtists = new String[50];
                int z = 0;
                for (Artist artist : CountryA) {
                    countryArtists[z] = artist.getName();
                    z++;
                }

                String[] countryTracks = new String[50];
                int q = 0;
                for (Track track : CountryT) {
                    countryTracks[q] = track.getName();
                    q++;
                }

                String[] topTrackNames = new String[50];
                int k = 0;
                for (Track track : tracks) {
                    topTrackNames[k] = track.getName();
                    k++;
                }

                String[] topArtistNames = new String[50];

                //Here we are converting the top artists of the user from a collection into an Arraylist,
                //so that we can iterate specific intervals of top artists
                //We are doing the same for recommendedArtists, which is an arraylist of the similar artists
                //to a specific artist in top artists arraylist
                ArrayList<Artist> userArtists = new ArrayList<>(Artists);


                ArrayList<Artist> topRecArtists = new ArrayList<>();

                //count1 keeps track of while loop to make sure it stops once topRecArtists contains 5 recommended artists
                int count1 = 0;
                //artistCount iterates through songs within the arraylist of user's top tracks
                int artistCount = 0;

                while (count1 < 20) {
                    Artist tempArtist = userArtists.get(artistCount);
                    Collection<Artist> recArtistCollection = tempArtist.getSimilar(tempArtist.getName(), 21, key);

                    //changing the generated collection of recommended artists into an arraylist to make it more easily accessible
                    ArrayList<Artist> recommendedArtists = new ArrayList<>(recArtistCollection);

                    //the large if statement is to make sure that firstly, the recommendedArtists array is not null
                    if (recommendedArtists.size() > 0) {
                        //this int iterates through the artists in the recommended arraylist
                        int recArtistCount = 0;

                        //this boolean keeps track whether the current recommended artist is the same artist as any of the user's top artists
                        boolean sameAsTopArtists = false;

                        //for each loop to track if current rec artist is the same as an artist in top artists
                        //if boolean is true, that means current rec artist also appears in user's top artists (or was already recommended)
                        for (Artist artist : Artists) {
                            if (artist.getName().equals(recommendedArtists.get(recArtistCount).getName())) {
                                sameAsTopArtists = true;
                            }
                        }

                        //keeps looping through recommended artists by adding to recTrackCount until boolean is false
                        while (sameAsTopArtists == true) {
                            recArtistCount++;
                            sameAsTopArtists = false;
                            for (Artist artist : Artists) {
                                if (artist.getName().equals(recommendedArtists.get(recArtistCount).getName())) {
                                    sameAsTopArtists = true;
                                }
                            }

                            //also making sure that the artist recommended is an artist that has already been recommended
                            for (Artist name : topRecArtists) {
                                if (name.getName().equals(recommendedArtists.get(recArtistCount).getName())) {
                                    sameAsTopArtists = true;
                                }
                            }

                        }
                        //only when boolean is false (rec artist is not the same as a top artist or another rec artist) do we then add that rec artist we generated
                        //into the topRecArtists arraylist
                        topRecArtists.add(recommendedArtists.get(recArtistCount));
                        count1++;
                    }

                    artistCount++;
                }


                //--------------------------------------------------------------------------------------------------------------------------------------


                //converting the top tracks of user from collection to arraylist
                ArrayList<Track> userTracks = new ArrayList<>(tracks);

                ArrayList<Track> topRecTracks = new ArrayList<>();

                //completely similar and basically the same as code for recommended Artists, except for songs
                //count keeps track of while loop to make sure it stops once topRecTracks contains 5 recommended songs
                int count = 0;
                //trackCount iterates through songs within the arraylist of user's top tracks
                int trackCount = 0;

                while (count < 20) {
                    Track tempTrack = userTracks.get(trackCount);
                    Collection<Track> recTrackCollection = tempTrack.getSimilar(tempTrack.getArtist(), tempTrack.getName(), key);


                    //changing the generated collection of recommended songs into an arraylist to make it more easily accessible
                    ArrayList<Track> recommendedTracks = new ArrayList<>(recTrackCollection);

                    //large if statement is to make sure that firstly, the recommendedTracks array is not null
                    if (recommendedTracks.size() > 0) {
                        //this int iterates through the tracks in the recommended arraylist
                        int recTrackCount = 0;

                        //this boolean keeps track whether the current recommended song is the same song as any of the user's top songs
                        boolean sameAsTopSongs = false;

                        //for each loop to track if current rec song is the same as a song in top songs
                        //if boolean is true, that means current rec song also appears in user's top songs
                        for (Track track : tracks) {
                            if (track.getName().equals(recommendedTracks.get(recTrackCount).getName())) {
                                sameAsTopSongs = true;
                            }
                        }

                        //keeps looping through recommended songs by adding to recTrackCount until boolean is false
                        while (sameAsTopSongs == true) {
                            recTrackCount++;
                            sameAsTopSongs = false;
                            for (Track track : tracks) {
                                if (track.getName().equals(recommendedTracks.get(recTrackCount).getName())) {
                                    sameAsTopSongs = true;
                                }
                            }

                            //also making sure that the song recommended isn' a song that has already been recommended
                            for (Track name : topRecTracks) {
                                if (name.getName().equals(recommendedTracks.get(recTrackCount).getName())) {
                                    sameAsTopSongs = true;
                                }
                            }

                        }
                        //only when boolean is false (rec song is not the same as a top song) do we then add that rec song we generated
                        //into the topRecTracks arraylist
                        topRecTracks.add(recommendedTracks.get(recTrackCount));
                        count++;
                    }

                    trackCount++;
                }


                ArrayList<Album> topAlbums = new ArrayList<>(albums);


                int l = 0;
                int[] topTrackPlaycount = new int[50];
                int totalPlayCount = 0;
                for (Track Track : tracks) {
                    topTrackPlaycount[l] = Track.getPlaycount();
                    totalPlayCount += Track.getPlaycount();

                    //smilar to the other for each loops it takes the collection data type and makes it into a string array
                    //but with every addition we get the total play count and add it to total play count to use later

                    l++;
                }

                int p = 0;
                for (Artist artist : Artists) {
                    topArtistNames[p] = artist.getName();
                    p++;
                }

                //From here on out, it is printing out everything

                for (int i = 0; i < 5; i++) {
                    topTracks.add(topTrackNames[i] + " was played " + topTrackPlaycount[i] + " times");
                }

                for (int i = 0; i < 5; i++) {
                    topArtists.add(topArtistNames[i]);

                }


                for (int i = 0; i < 5; i++) {
                    topAlbumsP.add(topAlbums.get(i).getName() + " by " + topAlbums.get(i).getArtist());
                }

                System.out.println();
                System.out.println();
                System.out.println("How many recommended artists do you want?");
                System.out.println("Options: 1-20");
                int recArtistNum = getNum();
                while (recArtistNum < 1 || recArtistNum > 20) {
                    System.out.println("Invalid input try again!");
                    System.out.println("How many recommended artists do you want?");
                    System.out.println("Options: 1-20");
                    recArtistNum = getNum();
                }

                for (int i = 0; i < recArtistNum; i++) {
                    recArtists.add(topRecArtists.get(i).getName());
                }

                System.out.println();
                System.out.println();
                System.out.println("How many recommended songs do you want?");
                System.out.println("Options: 1-20");
                int recSongNum = getNum();
                while (recSongNum < 1 || recSongNum > 20) {
                    System.out.println("Invalid input try again!");
                    System.out.println("How many recommended songs do you want?");
                    System.out.println("Options: 1-20");
                    recSongNum = getNum();
                }

                for (int i = 0; i < recSongNum; i++) {
                    recSongs.add(topRecTracks.get(i).getName() + " by " + topRecTracks.get(i).getArtist());
                }


                funDetails.add("You've played your top 50 songs " + totalPlayCount + " times!");
                funDetails.add("Did you know that the Top Tracks of the Country are:");
                System.out.println("You've played your top 50 songs " + totalPlayCount + " times!");
                for (int i = 0; i < 5; i++) {
                    funDetails.add(countryTracks[i]);
                }
                funDetails.add("Lastly, Did you know that the Top Artists of the Country are:");
                for (int i = 0; i < 5; i++) {
                    funDetails.add(countryArtists[i]);
                }






                //making Arraylist of Track MBIDs
                ArrayList<String> trackIDs = new ArrayList<>();
                for (int i = 0; i<5; i++){
                    String tempTrackID = userTracks.get(i).getMbid();
                    if(userTracks.get(i).getMbid() == null){
                        tempTrackID = "";
                    }
                    trackIDs.add(tempTrackID);
//                    System.out.println(tempTrackID);
                }



                //ArrayList of Artist MBIDs
                ArrayList<String> artistIDs = new ArrayList<>();
                for (int i = 0; i<5; i++){
                    String tempArtistID = userArtists.get(i).getMbid();
                    if(userArtists.get(i).getMbid() == null){
                        tempArtistID = "";
                    }
                    artistIDs.add(tempArtistID);
//                    System.out.println(tempArtistID);
                }


                //ArrayList of Recommended Track MBIDs
                ArrayList<String> recTrackIDs = new ArrayList<>();
                for (int i = 0; i<20; i++){
                    String tempTrackID = topRecTracks.get(i).getMbid();
                    if(topRecTracks.get(i).getMbid() == null){
                        tempTrackID = "";
                    }
                    recTrackIDs.add(tempTrackID);
//                    System.out.println(tempTrackID);
                }


                //ArrayList of Recommended Artist MBIDs
                ArrayList<String> recArtistIDs = new ArrayList<>();
                for (int i = 0; i<20; i++){
                    String tempArtistID = topRecArtists.get(i).getMbid();
                    if(topRecArtists.get(i).getMbid() == null){
                        tempArtistID = "";
                    }
                    recArtistIDs.add(tempArtistID);
//                    System.out.println(tempArtistID);
                }

                //After trying to convert the MBIDs to urls, we realized there's a ton of errors where either lastFM API doesn't have the MBID for a specific song/artist or music brainz API doesn't have the coverart url based on the MBID given
                //We weren't able to get the MBID to url converter to properly work (the musicbrainz api couldn't make a connection using the IDs we gave it)
                //Here's the code we tried running to get the urls, but the imageReturn method only returns null.

                //Returning ArrayLists of cover art urls

                ArrayList<String> trackURLs = new ArrayList<>();

                for (int i=0; i< trackIDs.size(); i++){
                    String tempURL = imageGenerator.imageReturn(trackIDs.get(i));
                    trackURLs.add(tempURL);
                }

                for (int i=0; i<5; i++){
                    System.out.println("url link for song:" + trackURLs.get(i));
                }


                ArrayList<String> artistURLs = new ArrayList<>();

                for (int i=0; i< artistIDs.size(); i++){
                    String tempURL = imageGenerator.imageReturn(artistIDs.get(i));
                    artistURLs.add(tempURL);
                }

                for (int i=0; i<5; i++){
                    System.out.println("url link for artist:" + artistURLs.get(i));
                }



//                ArrayList<String> albumURLs = imageGenerator.imageReturn(artistIDs);
//                System.out.println(albumURLs.get(1));
//
//                ArrayList<String> recTrackURLs = imageGenerator.imageReturn(recTrackIDs);
//                System.out.println(recTrackURLs.get(1));
//
//                ArrayList<String> recArtistURLs = imageGenerator.imageReturn(recArtistIDs);
//                System.out.println(recArtistURLs.get(1));


                valid = 1;
            }
            catch (Exception e) {
                System.out.println("Invalid Username, please try in again");
                System.out.println();
                valid = -1;
            }
        }

    }


    
    




    public void draw(){

        //Screen is a variable that determines exactly as the variable says, the screen
        //Using the Screen variable by changing the screen number it changes what is drawn
        //0 is for the home screen, 1 is the selector, and the rest are for printing out certain outputs
        if(screen==0) {
            background(232, 214, 174);
            fill(173, 172, 168);
            rect(buttonX, buttonY, buttonxSize, buttonySize);
            textSize(50);
            fill(0, 0, 0);

            text("CLICK NEXT TO PROCEED TO HOME PAGE", 200, 150);

            textSize(50);
            text("NEXT", buttonX+50, buttonY+65);

            if(mousePressed) {
                if (mouseX >= buttonX && mouseX <= (buttonX + buttonxSize) && mouseY >= buttonY && mouseY <= (buttonY + buttonySize)) {

                    screen++;
                }
            }

        } else if(screen==1){
            background(232, 214, 174);

            fill(0, 0, 0);
            textSize(50);
            text("Wrapped of " + period, 100, 475);

            image(cat, 800, 400, 500, 500);
            image(logo, 20, 40, 1000, 300);

            //checks for press of "Top Artists" button

            textSize(50);
            fill(232, 214, 174);
            rect(50, 500, 300, 75);
            fill(0, 0, 0);
            text("Top Artists",60, 550);
            if(mousePressed) {
                if (mouseX >= 50 && mouseX <= (50 + 300) && mouseY >= 500 && mouseY <= (500 + 75)) {

                    screen = 3;
                }
            }

            //checks for "top album" button
            fill(232, 214, 174);
            rect(50, 600, 300, 75);
            fill(0, 0, 0);
            text("Top Albums",60, 650);
            if(mousePressed) {
                if (mouseX >= 50 && mouseX <= (50 + 300) && mouseY >= 600 && mouseY <= (600 + 75)) {

                    screen = 4;
                }
            }


            //checks for top songs button

            fill(232, 214, 174);
            rect(375, 500, 300, 75);
            fill(0, 0, 0);
            text("Top Songs", 380, 550);
            if(mousePressed) {
                if (mouseX >= 375 && mouseX <= (375 + 300) && mouseY >= 500 && mouseY <= (500 + 75)) {

                    screen = 2;
                }
            }
            
            //checks for fun details button

            fill(232, 214, 174);
            rect(375, 600, 300, 75);
            fill(0, 0, 0);
            text("Fun Details", 380, 650);
            if(mousePressed) {
                if (mouseX >= 375 && mouseX <= (375 + 300) && mouseY >= 600 && mouseY <= (600 + 75)) {

                    screen = 5;
                }
            }

            //checks for reccomended artists button

            fill(232, 214, 174);
            rect(115, 700, 500, 75);
            fill(0, 0, 0);
            text("Reccomended Artists",120, 750);
            if(mousePressed) {
                if (mouseX >= 115 && mouseX <= (115 + 500) && mouseY >= 700 && mouseY <= (700 + 75)) {

                    screen = 6;
                }
            }

            //checks for reccomended songs button
            fill(232, 214, 174);
            rect(115, 800, 500, 75);
            fill(0, 0, 0);
            text("Reccomended Songs",120, 850);
            if(mousePressed) {
                if (mouseX >= 115 && mouseX <= (115 + 500) && mouseY >= 800 && mouseY <= (800 + 75)) {

                    screen = 7;
                }
            }

        } else if(screen == 2){
            //TOP SONGS PAGE
            background(232, 214, 174);
            fill(232, 214, 174);

            //HOME SCREEN BUTTON
            fill(232, 214, 174);
            rect(600, 800, 375, 75);
            fill(0, 0, 0);
            text("HOME SCREEN",605, 850);
            if(mousePressed) {
                if (mouseX >= 115 && mouseX <= (600 + 375) && mouseY >= 800 && mouseY <= (800 + 75)) {
                    screen = 1;
                }
            }
            //--------------------------------------------------------------------------------------------

            text("Top 5 Songs of " + period,0,75);

            int lineBreak = 0;
            for (int i = 0; i < 5; i++) {
                text(topTracks.get(i), 100,200+lineBreak);
                lineBreak = lineBreak + 100;
            }

        } else if(screen == 3){
            //TOP ARTISTS PAGE
            background(232, 214, 174);
            fill(232, 214, 174);

            //HOME SCREEN BUTTON
            fill(232, 214, 174);
            rect(600, 800, 375, 75);
            fill(0, 0, 0);
            text("HOME SCREEN",605, 850);
            if(mousePressed) {
                if (mouseX >= 115 && mouseX <= (600 + 375) && mouseY >= 800 && mouseY <= (800 + 75)) {
                    screen = 1;
                }
            }
            //--------------------------------------------------------------------------------------------
            text("Top 5 Artists of " + period,0,75);
            int lineBreak = 0;
            for (int i = 0; i < 5; i++) {
                text(topArtists.get(i), 100, 200 + lineBreak);
                lineBreak = lineBreak + 100;

            }
        } else if(screen == 4){
            //TOP ALBUM PAGE
            background(232, 214, 174);
            fill(232, 214, 174);

            //HOME SCREEN BUTTON
            fill(232, 214, 174);
            rect(600, 800, 375, 75);
            fill(0, 0, 0);
            text("HOME SCREEN",605, 850);
            if(mousePressed) {
                if (mouseX >= 115 && mouseX <= (600 + 375) && mouseY >= 800 && mouseY <= (800 + 75)) {
                    screen = 1;
                }
            }
            //--------------------------------------------------------------------------------------------
            text("Top 5 Albums of " + period,0,75);
            int lineBreak = 0;
            for (int i = 0; i < 5; i++) {
                text(topAlbumsP.get(i), 100, 200 + lineBreak);
                lineBreak = lineBreak + 100;
            }
        } else if(screen == 5){
            //FUN DETAILS PAGE
            background(232, 214, 174);
            fill(232, 214, 174);

            //HOME SCREEN BUTTON
            fill(232, 214, 174);
            rect(600, 800, 375, 75);
            fill(0, 0, 0);
            text("HOME SCREEN",605, 850);
            if(mousePressed) {
                if (mouseX >= 115 && mouseX <= (600 + 375) && mouseY >= 800 && mouseY <= (800 + 75)) {
                    screen = 1;
                }
            }
            //--------------------------------------------------------------------------------------------
            textSize(100);
            text("Fun Details",500,75);
            int lineBreak = 0;
            for (int i = 0; i < funDetails.size(); i++) {
                textSize(30);
                if (i<7)
                    text(funDetails.get(i), 50, 200 + lineBreak);
                else if (i==7) {
                    lineBreak = 0;
                    text(funDetails.get(i), 700, 200 + lineBreak);
                }
                else
                    text(funDetails.get(i), 800, 200 + lineBreak);
                lineBreak = lineBreak + 100;
            }
        } else if(screen == 6){
            //RECOMMENDED ARTISTS
            background(232, 214, 174);
            fill(232, 214, 174);

            //HOME SCREEN BUTTON
            fill(232, 214, 174);
            rect(600, 800, 375, 75);
            fill(0, 0, 0);
            textSize(50);
            text("HOME SCREEN",605, 850);
            if(mousePressed) {
                if (mouseX >= 115 && mouseX <= (600 + 375) && mouseY >= 800 && mouseY <= (800 + 75)) {
                    screen = 1;
                }
            }
            //--------------------------------------------------------------------------------------------
            textSize(100);
            text("Recommended Artists",50,75);
            int lineBreak = 0;
            for (int i = 0; i < recArtists.size(); i++) {
                textSize(30);
                if (i<recArtists.size()/2+1)
                text(recArtists.get(i), 100, 200 + lineBreak);
                else if (i==recArtists.size()/2+1){
                    lineBreak=0;
                    text(recArtists.get(i), 700, 200 + lineBreak);
                }
                else
                    text(recArtists.get(i), 700, 200 + lineBreak);
                lineBreak = lineBreak + 75;
            }
        } else if(screen == 7){
            //RECCOMENDED SONGS PAGE
            background(232, 214, 174);
            fill(232, 214, 174);

            //HOME SCREEN BUTTON
            fill(232, 214, 174);
            rect(600, 800, 375, 75);
            fill(0, 0, 0);
            textSize(50);
            text("HOME SCREEN",605, 850);
            if(mousePressed) {
                if (mouseX >= 115 && mouseX <= (600 + 375) && mouseY >= 800 && mouseY <= (800 + 75)) {
                    screen = 1;
                }
            }
            //--------------------------------------------------------------------------------------------
            textSize(100);
            text("Recommended Songs",50,75);
            int lineBreak = 0;
            for (int i = 0; i < recSongs.size(); i++) {
                textSize(30);
                if (i<recSongs.size()/2+1)
                    text(recSongs.get(i), 100, 200 + lineBreak);
                else  if (i==recSongs.size()/2+1){
                    lineBreak=0;
                    text(recSongs.get(i), 700, 200 + lineBreak);
                }
                else
                    text(recSongs.get(i), 700, 200 + lineBreak);
                lineBreak = lineBreak + 75;
            }
        }
        //image(vinyl,1100,400,1000,1000);
//        rect(rectX, rectY, rectxSize, rectySize);


    }

    public void mousePressed(){

    }


    public static int getNum(){
        try{ return (Integer.parseInt(s.nextLine()));}
        catch(Exception e){return -1;}
    }





    public static void main(String[] args){

        PApplet.main("LastFMUI");

    }

//_______________________________________________________________________________________
    //Implementing MusicBrainz API to create album/song cover for songs




}
