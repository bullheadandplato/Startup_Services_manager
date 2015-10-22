import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Created by h4ck3r on 10/15/15.
 */
class excuteShell extends JFrame {
    private final String userofSystem=System.getProperty("user.home");

     String[] startexcuteShell(String command) throws IOException{
        String[] result=new String[100];
        ProcessBuilder pb=new ProcessBuilder(command);
        Process myProc= pb.start();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(myProc.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line;
        int i=1;
        while ( (line = reader.readLine()) != null) {
            builder.append(line);
            result[i]=builder.toString();
            builder.append(System.getProperty("line.separator"));
            builder.setLength(0);
            i++;

        }

       return result;

    }
    public void disableService(String serviceToDisable)  {
        fileHandle obj=new fileHandle();
        try {

            if(Objects.equals(serviceToDisable, obj.readForCheck(serviceToDisable))){
                JOptionPane.showMessageDialog(null,"Service is already Disabled.");
            }
            else{
                Process p=null;
                String scriptPath=userofSystem + "/.setMe/disable.sh";
                ProcessBuilder pb=new ProcessBuilder(scriptPath,serviceToDisable);
                try {
                    p=pb.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    assert p != null;
                    if (p.waitFor() == 0) {
                        obj.writeToFile(serviceToDisable);
                        JOptionPane.showMessageDialog(null,"Service successfully disabled ");
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Service Not Disabled!");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    public void enableService(String serviceToEnable){
        fileHandle obj=new fileHandle();
        Process p=null;
        try {
            if(Objects.equals(serviceToEnable,obj.readForCheck(serviceToEnable))){
                String scriptPath=userofSystem+"/.setMe/enable.sh";
                ProcessBuilder pb=new ProcessBuilder(scriptPath,serviceToEnable);
                try{
                    p=pb.start();
                }catch(IOException e){
                    e.printStackTrace();
                }
                try{
                    assert p != null;
                    if(p.waitFor()==0){
                        obj.delForenable(serviceToEnable);
                        JOptionPane.showMessageDialog(null,"Service successfully enabled");
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Error occurred");
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

            }
            else
                JOptionPane.showMessageDialog(null,"Service is already enabled");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
