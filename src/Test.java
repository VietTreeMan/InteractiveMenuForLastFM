import processing.core.PApplet;
import de.umass.lastfm.*;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Scanner;

public class Test extends PApplet {
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
    PImage vinyl;
    public void setup(){
        //initiate and set variables


        //vinyl = loadImage("vinyl.png");

        buttonxSize  = 200;
        buttonySize = 100;
        buttonX = (width/2)-(buttonxSize/2);
        buttonY = 600;

        rectxSize  = 300;
        rectySize = 150;
        rectX = (width/2)-(rectxSize/2);
        rectY = 200;




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
                if (mouseX >= buttonX && mouseX <= (buttonX + buttonxSize) && mouseY >= buttonY && mouseY <= (buttonY + buttonySize)) {

                    screen = 2;
                }
            }
            
            //checks for fun details button

            fill(232, 214, 174);
            rect(375, 600, 300, 75);
            fill(0, 0, 0);
            text("Fun Details", 380, 650);
            if(mousePressed) {
                if (mouseX >= buttonX && mouseX <= (buttonX + buttonxSize) && mouseY >= buttonY && mouseY <= (buttonY + buttonySize)) {

                    screen = 5;
                }
            }

            //checks for reccomended artists button

            fill(232, 214, 174);
            rect(115, 700, 500, 75);
            fill(0, 0, 0);
            text("Reccomended Artists",120, 750);
            if(mousePressed) {
                if (mouseX >= buttonX && mouseX <= (buttonX + buttonxSize) && mouseY >= buttonY && mouseY <= (buttonY + buttonySize)) {

                    screen = 6;
                }
            }

            //checks for reccomended songs button
            fill(232, 214, 174);
            rect(115, 800, 500, 75);
            fill(0, 0, 0);
            text("Reccomended Songs",120, 850);
            if(mousePressed) {
                if (mouseX >= buttonX && mouseX <= (buttonX + buttonxSize) && mouseY >= buttonY && mouseY <= (buttonY + buttonySize)) {

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
                    text(recArtists.get(i), 700, 200 + lineBreak);
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

        PApplet.main("Test");

    }
}
