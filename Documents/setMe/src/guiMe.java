import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by h4ck3r on 10/16/15.
 */
class guiMe extends JFrame implements ActionListener {



    private final JList<String> services;
    private final JButton disable;
    private final JButton enable;

    guiMe(String[] dataFromCommand){

        //set look of window.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
            e.printStackTrace();
        }
        //Not Gui related variables
        ArrayList<String> fix=new ArrayList<>();


        //create the window and its other options
        JFrame gui=new JFrame();
        gui.setTitle("Manage Startup Services");
        gui.setLayout(new BorderLayout());
        gui.setLocation(400,200);
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel(" All Services"), BorderLayout.BEFORE_FIRST_LINE);



        services = new JList<>(fix.toArray(dataFromCommand));
        services.setLayoutOrientation(JList.VERTICAL_WRAP);
        services.setBackground(Color.darkGray);
        services.setForeground(Color.GREEN);
        services.setVisibleRowCount(-1);

        JScrollPane areaScrollPane = new JScrollPane(services);
        areaScrollPane.getPreferredSize();
        panel.add(areaScrollPane, BorderLayout.CENTER);

        JPanel buttons=new JPanel();
        disable=new JButton("Disable");
        enable = new JButton("Enable");
        buttons.add(disable);
        buttons.add(enable);

        panel.add(buttons, BorderLayout.PAGE_END);

        gui.add(panel);
        gui.setSize(400,400);
        gui.setVisible(true);
        enable.addActionListener(this);
        disable.addActionListener(this);


    }
    public void actionPerformed(ActionEvent e){
        excuteShell obj=new excuteShell();
        String serviceName=services.getSelectedValue();
        if(Objects.isNull(serviceName))
            JOptionPane.showMessageDialog(null,"No Service selected");
        else{
            if(e.getSource()==disable){
                obj.disableService(serviceName);
            }
            if(e.getSource()==enable){
                obj.enableService(serviceName);
            }
        }

    }

}
