package javaradio;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MultiThreading extends Thread {//A class that extends Thread
    
    String[] Stations = new String[] {//A list of radiostations we can listen to (Array)
    "http://www.listenlive.eu/myrock.m3u",
    "http://live-icy.gss.dr.dk:8000/A/A05H.mp3.m3u",
    "http://www.listenlive.eu/streams/denmark/novafm.m3u",
    "http://www.listenlive.eu/thevoice_dk.m3u",
    "http://dir.xiph.org/listen/1676612/listen.m3u",
    "http://traxx001.ice.infomaniak.ch/traxx002-low.mp3.m3u",
    
};

    public String getSong() {
        return song;
    }
    private String song;
    private String line;
    private String RadioStation;
    public void run() {//
    try {
      Process p = Runtime.getRuntime().exec("mplayer -playlist " + Stations[1]);
      BufferedReader bri = new BufferedReader
        (new InputStreamReader(p.getInputStream()));
      BufferedReader bre = new BufferedReader
        (new InputStreamReader(p.getErrorStream()));
      while(p.isAlive()){
      while ((line = bri.readLine()) != null) {
        if(line.contains("ICY Info")) {
        song = line.substring(23,((line.length())-2));
        if(song.length() > 0) {
            System.out.println(song);
            } else if(line.contains("Name")){
                RadioStation = line;
            }
        }
    }
}
      bre.close();
      while ((line = bre.readLine()) != null) {
        System.out.println(line);
        }
      bri.close();
      p.waitFor();
      System.out.println("Done.");
    }
    catch (Exception err) {
      err.printStackTrace();
        }
    }
}
