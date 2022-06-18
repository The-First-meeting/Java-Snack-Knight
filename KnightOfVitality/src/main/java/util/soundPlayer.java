package util;

import run.Run;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class soundPlayer implements Runnable {
    String path;
    public soundPlayer(String path)
    {
        this.path = path;
    }

    @Override
    public void run() {
        File file = new File(path);
        AudioInputStream am;
        try {
            am = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AudioFormat af = am.getFormat();
        SourceDataLine sd;
        try {
            sd = AudioSystem.getSourceDataLine(af);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            sd.open();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        sd.start();
        int sumByteRead = 0;
        byte[] b = new byte[320];
        while(sumByteRead != -1)
        {
            try {
                sumByteRead = am.read(b,0,b.length);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(sumByteRead>=0)
            {
                sd.write(b,0,b.length);
            }
        }
        sd.drain();
        sd.close();
    }
}
