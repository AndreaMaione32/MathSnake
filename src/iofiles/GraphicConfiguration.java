package iofiles;

import java.awt.Color;
import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import environment.Environment;

public class GraphicConfiguration {
    
    public GraphicConfiguration() {
        try {
            read();
        } catch (IOException ex) {
            Logger.getLogger(Environment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void read() throws IOException {
        File f = new File(Environment.getInstance().UTILITY_FILES_PATH + "graphic_configuration.txt");
        if(!f.exists()) {
            f.createNewFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
                bw.write("base_background.png", 0, "base_background.png".length());
                bw.newLine();
                bw.write(Integer.toString(Color.black.getRGB()));
                Environment.getInstance().WRITECOLOR = Color.black;
                bw.newLine();
                bw.write("dot.png", 0, "dot.png".length());
            }
        }
        else {
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                Environment.getInstance().PATHBACKGROUND = Environment.getInstance().PATHIMAGES + br.readLine();
                Environment.getInstance().WRITECOLOR = new Color(Integer.parseInt(br.readLine()));
                Environment.getInstance().PATHSKIN = Environment.getInstance().PATHIMAGES + br.readLine();
            }
        }
        
        f = new File(Environment.getInstance().UTILITY_FILES_PATH + "bought_features.txt");
        if(!f.exists()) {
            f.createNewFile();
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(f));
            writer.writeObject(Environment.getInstance().BOUGHT_FEATURES);
        }
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream(f));
        try {
            Environment.getInstance().BOUGHT_FEATURES = (HashMap) reader.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Environment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void writeGraphicConfiguration() throws IOException {
        String background = Environment.getInstance().PATHBACKGROUND.split("/")[3];
        String skin = Environment.getInstance().PATHSKIN.split("/")[3];
        File f = new File(Environment.getInstance().UTILITY_FILES_PATH + "graphic_configuration.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f, false))) {
            bw.write(background, 0, background.length());
            bw.newLine();
            bw.write(Integer.toString(Environment.getInstance().WRITECOLOR.getRGB()));
            bw.newLine();
            bw.write(skin, 0, skin.length());
        }
    }
    
    public void writeBoughtFeatures() throws FileNotFoundException, IOException {
        File f = new File(Environment.getInstance().UTILITY_FILES_PATH + "bought_features.txt");
        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(f));
        writer.writeObject(Environment.getInstance().BOUGHT_FEATURES);
    }
}
