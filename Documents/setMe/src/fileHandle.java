import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

/**
 * Created by h4ck3r on 10/19/15.
 */
class fileHandle {
    private final Path file=Paths.get(System.getProperty("user.home"),".setMe","disabled");

    fileHandle(){
        if(!Files.exists(file)){
            try {
                Path dir = Paths.get(System.getProperty("user.home"), ".setMe");
                Files.createDirectories(dir);
                Files.createFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void writeToFile(String toWrite) {
        toWrite+="\n";
        try {
            Files.write(file, toWrite.getBytes(), StandardOpenOption.APPEND);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String readForCheck(String toWork) throws IOException{
        String pathTofile= String.valueOf(file);
        String line=null;
        try {
            BufferedReader br=new BufferedReader(new FileReader(pathTofile));
            while((line=br.readLine())!=null){
                if(Objects.equals(line,toWork))
                    break;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return line;
    }
    public void delForenable (String toRemove) {
        Path temp1=Paths.get(System.getProperty("user.home"),".setMe","temp");
        if(!Files.exists(temp1)){
            try {
                Files.createFile(temp1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File forWrite = new File(System.getProperty("user.home") + "/.setMe/disabled");
        File temp=new File(System.getProperty("user.home")+"/.setMe/temp");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(forWrite));
            BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String trimed=currentLine.trim();

                if (!Objects.equals(trimed, toRemove)) {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }
            writer.close();
            reader.close();
            temp.renameTo(forWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
