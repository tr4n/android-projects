package com.tr4n.listinstalledapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by MyPC on 19/03/2018.
 */

public class CPUReader {
    public long total = 0;
    public long idle = 0;
    public float usage = 0;

    public CPUReader(){
        readUsage();
    }

    public float getUsage(){
        readUsage();
        return usage;
    }

    public float readUsage() {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("/proc/stat", "r");
            String load = randomAccessFile.readLine();
            String[] toks = load.split(" +");
            long idle1 = Long.parseLong(toks[4]);
            long cpu1 = Long.parseLong(toks[2])+ Long.parseLong(toks[3]) + Long.parseLong(toks[5]) + Long.parseLong(toks[6])+
            Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

            randomAccessFile.seek(0);
            load = randomAccessFile.readLine();
            randomAccessFile.close();

            toks = load.split(" +");

            long idle2 = Long.parseLong(toks[4]);
            long cpu2 = Long.parseLong(toks[2])+ Long.parseLong(toks[3]) + Long.parseLong(toks[5]) + Long.parseLong(toks[6])+
                    Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

            return (float)(cpu2 - cpu1)/((cpu2 + idle2) - (cpu1 + idle1));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return 0;
    }
}
