package util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BGMPlayer implements Runnable{

    @Override
    public void run() {
        while(true)
        {
            File file = new File("music/马里奥BGM.wav");
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
}
