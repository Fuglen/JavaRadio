package javaradio;

import org.iot.raspberry.grovepi.GroveDigitalIn;
import org.iot.raspberry.grovepi.devices.GroveRgbLcd;
import org.iot.raspberry.grovepi.GrovePi;
import org.iot.raspberry.grovepi.pi4j.GrovePi4J;


public class JavaRadio {

  
public static void main(String[] args) throws Exception {

MultiThreading obj = new MultiThreading(); //Vi laver et objekt, så vi kan starte en tråd
obj.start(); //Tråden startes

GrovePi grovePi = new GrovePi4J();//Laver endnu et objekt, så vi kan bruge knapper og LCD

GroveRgbLcd lcd = grovePi.getLCD();//Vi finder vores LCD
GroveDigitalIn btn = grovePi.getDigitalIn(6);//Vi finder vores knap

//Variabler til knap og tid, så vi kan lave lange/korte tryk.
long StartTid = System.currentTimeMillis();
long ButtonTime = 0;
boolean Exit = false;
boolean ButtonStatus = false;
boolean DisplayUpdated = false;
String currentSong = null;

lcd.setRGB(0, 0, 255);//Sætter LCD-skærmen til blå.
System.out.println(obj.getSong());
while(!Exit) {//,
    if(!DisplayUpdated){
        lcd.setText("\n" + obj.getSong());
        currentSong = obj.getSong();
    } else {
        if(obj.getSong() != currentSong){
            DisplayUpdated = false;
        }
    }
      if(obj.getSong() != null) {//Vi henter vores linje med sang titel.
          if(obj.getSong() != null) {
          lcd.setText("\n" + obj.getSong());
        }
      }
      if(btn.get() == true) {//Hvis knappen bliver trykket på, vil den sætte status til true og sætte et punkt for tid.
          if(ButtonStatus == false)//Dette vil den kun gøre en gang, for hver gang du trykker på knappen.
          StartTid = System.currentTimeMillis();
          ButtonStatus = true;
      }
      else{
          if(ButtonStatus == true)//Koden vil nå hertil i anden omgang, da knappen allerede er trykket ned.
          ButtonTime = System.currentTimeMillis() - StartTid;
//          System.out.println(ButtonTime + " " + (Long.toString(ButtonTime/1000)) + "(sek)");
//          lcd.setText(ButtonTime + " " + (Long.toString(ButtonTime/1000)) + "(sek)");
          ButtonStatus = false;
            }
        }
    }
}
