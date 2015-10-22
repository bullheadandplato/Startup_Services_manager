import java.io.IOException;

/**
 * Created by h4ck3r on 10/19/15.
 */
class Driver {
    public static void main(String[] args) throws IOException {
        new fileHandle();

        String commandTest = System.getProperty("user.home") + "/.setMe/allServices.sh";
        excuteShell exobj = new excuteShell();
        String[] passToGui = exobj.startexcuteShell(commandTest);

       new guiMe(passToGui);

    }
}
