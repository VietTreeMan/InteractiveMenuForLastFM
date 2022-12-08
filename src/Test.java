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

            text("TYPE NAME IN CONSOLE! CLICK NEXT WHEN DONE", 275, 150);

            textSize(50);
            text("NEXT", buttonX+50, buttonY+65);

            if(mousePressed) {
                if (mouseX >= buttonX && mouseX <= (buttonX + buttonxSize) && mouseY >= buttonY && mouseY <= (buttonY + buttonySize)) {

                    screen++;
                }
            }

        } else if(screen==1){
            background(232, 214, 174);
            fill(232, 214, 174);
            rect(50, 400, 300, 75);
            rect(50, 500, 300, 75);
            rect(50, 600, 300, 75);
            rect(115, 700, 500, 75);
            rect(115, 800, 500, 75);

            rect(375, 400, 300, 75);
            rect(375, 500, 300, 75);
            rect(375, 600, 300, 75);


            fill(0, 0, 0);
            text("Wrapped of " + period, 100, 375);
            text("Top Songs",60, 450);
            text("Top Artists",60, 550);
            text("Top Albums",60, 650);

            text("Reccomended Artists",120, 750);
            text("Reccomended Songs",120, 850);


            //checks for press of "Top Songs" button
            if(mousePressed) {
                if (mouseX >= 50 && mouseX <= (50 + 300) && mouseY >= 400 && mouseY <= (400 + 75)) {
                    screen = 2;
                }
            }

            //checks for press of "Top Artists" button
            if(mousePressed) {
                if (mouseX >= 50 && mouseX <= (50 + 300) && mouseY >= 500 && mouseY <= (500 + 75)) {

                    screen = 3;
                }
            }

            //checks for "top album" button
            if(mousePressed) {
                if (mouseX >= 50 && mouseX <= (50 + 300) && mouseY >= 600 && mouseY <= (600 + 75)) {

                    screen = 4;
                }
            }

            //checks for fun details button
            if(mousePressed) {
                if (mouseX >= buttonX && mouseX <= (buttonX + buttonxSize) && mouseY >= buttonY && mouseY <= (buttonY + buttonySize)) {

                    screen = 5;
                }
            }

            //checks for top artists of country
            if(mousePressed) {
                if (mouseX >= buttonX && mouseX <= (buttonX + buttonxSize) && mouseY >= buttonY && mouseY <= (buttonY + buttonySize)) {

                    screen = 6;
                }
            }

            //checks for top songs of country
            if(mousePressed) {
                if (mouseX >= buttonX && mouseX <= (buttonX + buttonxSize) && mouseY >= buttonY && mouseY <= (buttonY + buttonySize)) {

                    screen = 7;
                }
            }

            //checks for reccomended artists button
            if(mousePressed) {
                if (mouseX >= buttonX && mouseX <= (buttonX + buttonxSize) && mouseY >= buttonY && mouseY <= (buttonY + buttonySize)) {

                    screen = 8;
                }
            }

            //checks for reccomended songs button
            if(mousePressed) {
                if (mouseX >= buttonX && mouseX <= (buttonX + buttonxSize) && mouseY >= buttonY && mouseY <= (buttonY + buttonySize)) {

                    screen = 9;
                }
            }

        } else if(screen == 2){
            background(0, 0, 0);
            fill(0, 0, 0);
        } else if(screen == 3){
            background(0, 0, 0);
        } else if(screen == 4){
            background(0, 0, 0);
        } else if(screen == 5){
            background(0, 0, 0);
        } else if(screen == 6){
            background(0, 0, 0);
        } else if(screen == 7){
            background(0, 0, 0);
        } else if(screen == 8){
            background(0, 0, 0);
        } else if(screen == 9){

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
