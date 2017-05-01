//import java.io.File;
//import java.io.IOException;
//
//import javax.sound.sampled.AudioFormat;
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.DataLine;
//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.SourceDataLine;
//
//public class MakeSound {
//
//    private final int BUFFER_SIZE = 128000;
//    private File soundFile;
//    private AudioInputStream audioStream;
//    private AudioFormat audioFormat;
//    private SourceDataLine sourceLine;
//
//    /**
//     * @param filename the name of the file that is going to be played
//     */
//    public void playSound(String filename){
//
//        String strFilename = filename;
//
//        try {
//            soundFile = new File(strFilename);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//
//        try {
//            audioStream = AudioSystem.getAudioInputStream(soundFile);
//        } catch (Exception e){
//            e.printStackTrace();
//            System.exit(1);
//        }
//
//        audioFormat = audioStream.getFormat();
//
//        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
//        try {
//            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
//            sourceLine.open(audioFormat);
//        } catch (LineUnavailableException e) {
//            e.printStackTrace();
//            System.exit(1);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//
//        sourceLine.start();
//
//        int nBytesRead = 0;
//        byte[] abData = new byte[BUFFER_SIZE];
//        while (nBytesRead != -1) {
//            try {
//                nBytesRead = audioStream.read(abData, 0, abData.length);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if (nBytesRead >= 0) {
//                @SuppressWarnings("unused")
//                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
//            }
//        }
//
//        sourceLine.drain();
//        sourceLine.close();
//    }
//    





	import java.io.*;
import sun.audio.*;
 
/**
 * A simple Java sound file example (i.e., Java code to play a sound file).
 * AudioStream and AudioPlayer code comes from a javaworld.com example.
 * @author alvin alexander, devdaily.com.
 */
public class MakeSound
{
  public static void main(String[] args) 
  throws Exception
  {
    // open the sound file as a Java input stream
    String gongFile = "error.wav";
    InputStream in = new FileInputStream(gongFile);
 
    // create an audiostream from the inputstream
    AudioStream audioStream = new AudioStream(in);
 
    // play the audio clip with the audioplayer class
    AudioPlayer.player.start(audioStream);
  }
}

    