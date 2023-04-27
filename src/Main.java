/*This is a billing system that charges the user when making calls
*program was written by @samwel gachiri Ng'ang'a
*                        J17/5082/2021
*                        KU: regular.
*Enjoy using the program
*/
import javax.sound.sampled.*;
import java.awt.*;      //this is for the gui
import javax.swing.*;   //this is for allowing use of gui
import java.awt.event.ActionEvent;// this is for the actionlistener where the action to be performed is contained
import java.awt.event.ActionListener;//this is where the event upon an action proceeds e.g. pressing a button
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.*;     //import is used for the date format
import java.util.Date;     //import is for use of the date
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;//this is an import for setting the titled border for the panel
public class Main extends JFrame{
        public static JFrame frame;//this is creating a frame object that can be accessed by all objects
        public static JPanel panel;//this is creating a panel that will be used by all of the classes
        public static JButton exit;//this button is used to return back to the previous window
        public static JButton button;//this performs the movement between windows
    public static void main(String[] args) {
        System.exit(0);
        File file = new File("c:\\users\\user\\desktop\\data.txt");
            try {
                Scanner scan = new Scanner(file);
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {

                   try {                       javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedLookAndFeelException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                    }
                }
        //this is reading from a file
        if(CallingWindow.callNo==0){
        //CallingWindow.callNo = 0;
        while(scan.hasNextLine()){
            for(int j = 0;j<=9;j++){
            CallingWindow.data[CallingWindow.callNo][j]= scan.nextLine();
            }
         CallingWindow.callNo++;
        }
        }
        scan.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        String filecontents = "";          
               File filew;
               FileWriter writer;
                try {
                    for(int i = 0;i<CallingWindow.callNo;i++){
                        for(int j = 0;j<10;j++){
                            filecontents = filecontents.concat(String.valueOf(CallingWindow.data[i][j]))+"\n";//concat is to add to the content already in the string
                        }
                    }
                        filew = new File("c:\\users\\user\\desktop\\data.txt");
                        writer = new FileWriter(filew);
                        writer.write(filecontents);
                        System.out.println(filecontents);
                        writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
        Border border = BorderFactory.createLineBorder(Color.white, 2, true);//This is creating a border that will reflect an etched border 
        panel();//calling the function panel that will build up the panel
        exit.setText("Exit");
        exit.addActionListener(new ActionListener(){//adding an action listener that exits the application
            public void actionPerformed(ActionEvent e){
               frame.dispose();
               System.exit(0);
            }
        });
        JLabel label = new JLabel("Welcome to my billing system!");//this sets the text of the label
        label.setForeground(new Color(0, 32, 90));   //this sets the color of the label to a random color
        label.setBounds(20, 40, 200, 40);//this sets the bounds of the label within the panel
        label.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 15));//this sets the font style, boldness and size
        panel.add(label);//this now adds the label to the panel
        
        JTextArea area = new JTextArea();//this creates a new textarea where call charges will be displayed
        area.setBounds(20, 90, 300,255);//setting the size and location of the area field
        area.setBorder(border);
        
        area.setText("""
                     Charges per call
                     -------------------------------------------------
                     1. My network
                     ----------------
                     - 4.00 sh/min after 6.00 a.m. to 6.00 p.m.
                     - 3.00 sh/min after 6.00 p.m. to 6.00 a.m.
                     
                     2. Other networks
                     -----------------
                     - 5.00 sh/min
                     
                     Thank you!""");//displaying charges in textblock
        area.setEditable(false);//this has been used so that the user does not edit what i've written on the text area
        area.setFont(new Font("times new roman", Font.PLAIN, 16));
        area.setForeground(new Color(50, 50, 150));//this is to set the color of the text displayed on the screen
        area.setBackground(new Color(190, 190, 190));//this is to set the color of the background;
        panel.add(area);//this is to add the text area to the panel
                      CallingWindow obj = new CallingWindow();
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent action){
                frame.dispose();
                obj.main();
            }
        });
        button.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setText("click me");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setText("continue");
                frame.dispose();
                obj.main();
            }
        });
        
        frame();
        SimpleDateFormat fm = new SimpleDateFormat("EEE dd/MM/YYY");
        Date systemDate = new Date();
        frame.setTitle(fm.format(systemDate) +"\t\tBilling System");//setting the title of the frame
        frame.add(panel);//this statement adds the panel to the frame
        frame.setVisible(true);//this statemet sets the frame to visible

    }
        static void frame(){// this is the method for making the frame used by all the classes
        frame = new JFrame();
        frame.setUndecorated(true);
        frame.setOpacity(0.8f);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(580, 620);
        frame.setLayout(new BorderLayout(10,10));//this is used to induce the border layout
        frame.setVisible(true);
        frame.setLocation(250,100);
    }
        static void panel(){
             frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(580, 620);
        frame.setLayout(new BorderLayout(10,10));
            panel = new JPanel();
            panel.setBackground(new Color(60,60,60));//setting the panel color to dark mode...
            //panel.setOpaque(false);
            panel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.white, 2, true),"Call Billing System", TitledBorder.LEFT, TitledBorder.ABOVE_TOP));
            panel.setLayout(null);

            exit = new JButton("return");
            exit.setFocusable(false);//to avoid highlighting the name 'exit'
            exit.setBounds(20, 540 , 100, 30);//setting the size to the bottom right of the frame
            exit.setBackground(Color.white);
            exit.setVisible(true);
//            exit.setBorder(BorderFactory.createLineBorder(Color.white, 2, true));
            panel.add(exit);//adding exit button to the panel
            
            JLabel theme = new JLabel("tricky sana");
            theme.setBounds(20, 20, 200, 30);
            panel.add(theme);
            theme.addMouseListener(new MouseListener(){
                public void actionPerformed(ActionEvent e){
                    
                }

                 @Override
                 public void mouseClicked(MouseEvent e) {
                     panel.setOpaque(true);
                     System.out.println("clicked");
                 }

                 @Override
                 public void mousePressed(MouseEvent e) {
                     panel.setOpaque(false);
                     theme.setText("i love you samwel");
                     System.out.println("pressed");
                 }

                 @Override
                 public void mouseReleased(MouseEvent e) {
                     panel.setOpaque(false);
                     theme.setText("tricky sana Samwel");
                     System.out.println("released");
                 }

                 @Override
                 public void mouseEntered(MouseEvent e) {
                     panel.setOpaque(false);
                     theme.setText("I love you samwel");
                 }

                 @Override
                 public void mouseExited(MouseEvent e) {
                     theme.setText("tricky sana samwel");
                     panel.setOpaque(false);
                 }
            });
            button = new JButton("Continue");//for opening up a new window
            button.setFocusable(false);//this prevents the button name from being highlighted
            button.setBounds(230, 540, 100, 30);//settin both the location and the size of the button
//            button.setBorder(BorderFactory.createLineBorder(Color.white, 2, true));
            panel.add(button);

            JLabel clock = new JLabel();//making a clock label that will be used to display the clock
            clock.setForeground(new Color(60, 60, 60));
            clock.setOpaque(true);
            clock.setBounds(450, 540, 100, 30);//setting the position of the clock
            clock.setVisible(true);//making the clock visible
            panel.add(clock);
            SimpleDateFormat clockform = new SimpleDateFormat("  hh:mm:ss aa");
            ActionListener clockListener = (ActionEvent actionEvent) -> {
                Date mydate = new Date();//getting the date
                clock.setText(clockform.format(mydate));//putting the date which is formatted to clock
            };
            Timer clocktimer = new Timer(1000, clockListener);
           clocktimer.start();//starting the clock timer
        }
    
}


class CallingWindow implements ActionListener{
        SimpleDateFormat fm = new SimpleDateFormat("dd/MM/YY");//this will take in the date when the call was made
        SimpleDateFormat  time = new SimpleDateFormat("hh: mm : ss aa");//this will format the start time and end time
        static int callNo=0;
        Date start;//used to get the time call has started
        Date endt;//used to get the time call has ended
        Timer timer = new Timer(1000, this);
        static int h, m, s, totalsec;//this are the ones to be used by the timer but the totalsec counts the total seconds taken for the call
        static JTextField timerfield;//this is where the timer will be displayed when the call button is pressed
        static JButton call;//this button will be used to make a call
        static JButton end;//this button will be used to end a call
        static JList list;//this jlist presents the contact numbers where one is chosen
        static String value;//this will be used to get the selected name from the contacts
        static String[] names = {"new contact", "Samwel", "Mary", "Joshua", "Elizabeth", "Judas"};//THIS IS THE NAMES OF THE CONTACTS I HAVE
        static String [] network = {"Default", "other"};//this displays the network to be selected
        static JLabel guide;//this guide the user before calling
        static int flag = 2;//this will indicate which network is chosen by joptionpane but set to a random number
        /*Formats for receiveing the hour of the day and the minute of the day*/
        SimpleDateFormat HH = new SimpleDateFormat("HH");
        SimpleDateFormat mm = new SimpleDateFormat("mm");
        float hour, minute;//thes variable will store the hour and minute
        float chargetime;// Charges per time taken
        float vat;      //charges on vat 
        float amount;   //total charges
        NumberFormat twodecimal = new DecimalFormat("0.00");//formatiing the amount to two decimal places
        Date mydate;   //setting mydate object
        JLabel pic;     //label containing the icon
        int mouseclicks;//getting the number of mouse clicks
        static public Object [][] data = new Object[100][10];
        static Clip clip;
    void main() {
        ImageIcon image = new ImageIcon("download.png");//setting an icon refering to the download icon
        Main.panel();//this is for making the panel for the new window
        Main.button.setText("view reports");
        Main.button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent a){
                Main.frame.dispose();
                Report objs  = new Report();
                objs.main();
            }
        });
        /*Adding a call button*/
        call = new JButton("Call");
        call.setVisible(true);
        call.setBounds(230,400, 90,30);
        call.setFocusable(false);
        call.addActionListener(this);
        call.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
        });
        call.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
             
            pic.setVisible(false);
            System.out.println("samwel");
            Main.exit.setEnabled(false);
            guide.setLocation(200, 350);
            Main.button.setEnabled(false);
          /*obtaining the hour and minute of the day*/
          mydate = new Date();
          hour = Integer.parseInt(HH.format(mydate));
          minute = Integer.parseInt(mm.format(mydate));//since we'll get the hour in float number we need to get the minute
          hour +=(minute/60);//the hour will now be found as float with decimal as the fraction of minutes in an hour
         /*timerfield will be used to display the contacts title*/
        timerfield.setVisible(true);
        timerfield.setBounds(200, 170, 140, 30);
        timerfield.setText("CONTACTS");
        /*obtaining the start time that will be used to charger the call*/
        /*list will be used to enlist the contacts*/
        list.setVisible(true);
        guide.setVisible(true);
        guide.setText("select a contact from the above list");
        /*Making the button end to be visible to the user while making call button to be inviscible*/
        call.setVisible(false);
        end.setVisible(true);
        end.setEnabled(false);
        /*Adding a listener that will take in the name chosen by the user*/
          try {
                       File file = new File("C:\\users\\user\\Downloads\\TILL_I_LET_GO(INSTRUMENTAL).WAV");
                       AudioInputStream audiostream = AudioSystem.getAudioInputStream(file);
                       clip = AudioSystem.getClip();
                       clip.open(audiostream);
                   } catch (IOException ex) {
                       Logger.getLogger(CallingWindow.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (LineUnavailableException ex) {
                       Logger.getLogger(CallingWindow.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(CallingWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
        list.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e){
            mouseclicks = e.getClickCount();//this is obtaining the number of clickcounts
            if(mouseclicks == 1){
                 
               int a = list.getSelectedIndex();
               value =(String)list.getSelectedValue();//the name of the person is stored by value variable
               if(a==0){
                   value = JOptionPane.showInputDialog("Name");
               }
               list.setVisible(false);
               timerfield.setBounds(200, 300, 140, 30);//setting the location and the size of timerfield altogether
               timerfield.setText("--h : -- min : -- sec");
                if(flag !=0&& flag != 1){
               guide.setText("Select the preferred network...");
                    flag = JOptionPane.showInternalOptionDialog(null, "select a network", "network selection", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, network, 2);
                    guide.setText("Calling...");
                    h = 0;m = 0;s = 0;totalsec= 0;//initializing the variables to 0 before starting
                    start = new Date();
                    timer.start();
                    if(flag!=1&&flag!=0){//this is to ensure that the user selects a network
                        timer.stop();
                        guide.setText("select network!!");
                    }
                    end.setEnabled(true);
                }
            }
        }
        });
         
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });
        Main.panel.add(call);
        /*Adding a button to end call*/
        end = new JButton("end");
        end.setVisible(false);
        end.setBounds(230,400, 70,30);
        end.setFocusable(false);
        end.addActionListener(this);
//        end.setIcon(endIcon);
        end.setText("end");
        Main.panel.add(end);
        /*Adding  a timer textfield*/
        timerfield = new JTextField();
        timerfield.setVisible(false);//the textfield is made visible during call process
        timerfield.setBounds(150, 350, 190, 30);//setting the location and the size of timerfield altogether
        timerfield.setEnabled(false);
        timerfield.setBorder(BorderFactory.createEtchedBorder(Color.gray, Color.white));
        timerfield.setFont(new Font("ms mincho", Font.BOLD, 14));
        timerfield.setForeground(new Color(200,200, 200));//this sets the text to white
        timerfield.setBackground(new Color(0, 0, 0));//this sets the background color to black
        timerfield.setText("Make call anywhere");
        /*Adding a jlist*/
        list = new JList(names);//setting a jlist that will contain my contacts
        list.setVisibleRowCount(5);//setting the visible rows to only 5 in my list
        list.setLayout(new BorderLayout(10,10));
//        list.setBackground(new Color(130, 130, 130));
        list.setBounds(200, 200, 140, 150);//setting the position and size of the list
        list.setFont(new Font(null, Font.BOLD, 16));//setting the font of the list
        list.setForeground(Color.black);//seting the color of the letters
        list.setBorder(BorderFactory.createEtchedBorder(Color.gray, Color.white)); //creating the border for the list       
        
        pic = new JLabel();//making a label that will display the icon
        pic.setBounds(200,100, 400, 300);//giving the label location and size
//        pic.setBounds(200, 100, 300, 300);
        pic.setIcon(image);
        Main.panel.add(pic);
        guide = new JLabel();
        Main.panel.add(guide);
        guide.setForeground(new Color(190,190,190));
        guide.setBounds(200, 350, 400, 30);
        guide.setText("click on the call button below to make a call");
        JScrollPane scroll = new JScrollPane(list);
        Main.panel.add(scroll);
        Main.panel.add(list);
//        list.add(scroll, BorderLayout.AFTER_LINE_ENDS);
//        scroll.setVisible(true);
//        scroll.setBounds(200, 200, 30,100);
        
        list.setVisible(false);// the list will be set visible during calling process
        
        Main.panel.add(timerfield);
        String []args = {"me"};//this is the argument that will be passed to the main method in main class
        Main.exit.addActionListener(new ActionListener(){//adding an action listener that exits the application
            public void actionPerformed(ActionEvent e){
                Main.frame.dispose();
                Main obj = new Main();
                Main.main(args);
            }
        });
        Main.frame();//this is for making the frame for the new window
        Main.frame.add(Main.panel);//this is for adding the panel to the frame
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object O = e.getSource();
        if(O==timer){
            s++;totalsec++;
            if(s==60){
                m++;
                if(m==60){
                    h++;
                    m=0;
                }
                s=0;
            }
            if(totalsec==5){
                clip.start();
            }
            if(totalsec>=5){
             int n = 200 - (totalsec-2);
             if(totalsec>=102){
                 n = 300+totalsec%20;
                 timerfield.setBounds(n, 300-(totalsec-4), 140, 30);
             }
            }
            timerfield.setText(h+" h:"+m+"min :"+s+" sec");
                        /*imposing charges for calls made after 6am to 6 pm*/
            if(hour>6&&hour<=18)
            {
                chargetime = ((float)totalsec*4)/60;
            }/*Imposing charges for calls made after 6pm to 6 am*/
            else
            {
                chargetime = ((float)totalsec*3)/60;
            }
            /*Imposing charges for calls made with other network regardless of the time*/
            if(flag == 1){
                chargetime = ((float)totalsec*5)/60;
//                timerfield.setText(chargetime+"");
            }
            /*Calculating the vat if the time taken is above two minutes. */
            if(totalsec>120){
               vat = (chargetime*16)/100;
            }
            amount = chargetime +vat;
            String how;
            how = "calling";
            int hs = totalsec%4;
            System.out.println(hs+"");
            for(int i=0;i<hs;i++)
            {
                how=how.concat(".");
            }
//            how = how.concat("___Amount--Ksh: "+twodecimal.format(amount));
            guide.setText(how);
        }
        else if(O == call){
            pic.setVisible(false);
            System.out.println("samwel");
            Main.exit.setEnabled(false);
            guide.setLocation(200, 350);
            Main.button.setEnabled(false);
          /*obtaining the hour and minute of the day*/
          mydate = new Date();
          hour = Integer.parseInt(HH.format(mydate));
          minute = Integer.parseInt(mm.format(mydate));//since we'll get the hour in float number we need to get the minute
          hour +=(minute/60);//the hour will now be found as float with decimal as the fraction of minutes in an hour
         /*timerfield will be used to display the contacts title*/
        timerfield.setVisible(true);
        timerfield.setBounds(200, 170, 140, 30);
        timerfield.setText("CONTACTS");
        /*obtaining the start time that will be used to charger the call*/
        /*list will be used to enlist the contacts*/
        list.setVisible(true);
        guide.setVisible(true);
        guide.setText("select a contact from the above list");
        /*Making the button end to be visible to the user while making call button to be inviscible*/
        call.setVisible(false);
        end.setVisible(true);
        end.setEnabled(false);
        /*Adding a listener that will take in the name chosen by the user*/
          try {
                       File file = new File("C:\\users\\user\\Downloads\\TILL_I_LET_GO(INSTRUMENTAL).WAV");
                       AudioInputStream audiostream = AudioSystem.getAudioInputStream(file);
                       clip = AudioSystem.getClip();
                       clip.open(audiostream);
                   } catch (IOException ex) {
                       Logger.getLogger(CallingWindow.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (LineUnavailableException ex) {
                       Logger.getLogger(CallingWindow.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(CallingWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
        list.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e){
            mouseclicks = e.getClickCount();//this is obtaining the number of clickcounts
            if(mouseclicks == 1){
                 
               int a = list.getSelectedIndex();
               value =(String)list.getSelectedValue();//the name of the person is stored by value variable
               if(a==0){
                   value = JOptionPane.showInputDialog("Name");
               }
               list.setVisible(false);
               timerfield.setBounds(200, 300, 140, 30);//setting the location and the size of timerfield altogether
               timerfield.setText("--h : -- min : -- sec");
                if(flag !=0&& flag != 1){
               guide.setText("Select the preferred network...");
                    flag = JOptionPane.showInternalOptionDialog(null, "select a network", "network selection", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, network, 2);
                    guide.setText("Calling...");
                    h = 0;m = 0;s = 0;totalsec= 0;//initializing the variables to 0 before starting
                    start = new Date();
                    timer.start();
                    if(flag!=1&&flag!=0){//this is to ensure that the user selects a network
                        timer.stop();
                        guide.setText("select network!!");
                    }
                    end.setEnabled(true);
                }
            }
        }
        });
            
        }
        /*This is the action performed when the button to end call is pressed*/
        else if(O == end)
        {
            endt = new Date();
            Main.exit.setEnabled(true);
            timer.stop();//this is to stop the timer immediately
            clip.stop();
            Main.button.setEnabled(true);
            end.setVisible(false);
            call.setVisible(true);
            /*imposing charges for calls made after 6am to 6 pm*/
            if(hour>6&&hour<=18)
            {
                chargetime = ((float)totalsec*4)/60;
            }/*Imposing charges for calls made after 6pm to 6 am*/
            else
            {
                chargetime = ((float)totalsec*3)/60;
            }
            /*Imposing charges for calls made with other network regardless of the time*/
            if(flag == 1){
                chargetime = ((float)totalsec*5)/60;
//                timerfield.setText(chargetime+"");
            }
            /*Calculating the vat if the time taken is above two minutes. */
            if(totalsec>120){
               vat = (chargetime*16)/100;
            }
            amount = chargetime +vat;
            guide.setText("call ended. charge on time : "+twodecimal.format(chargetime)+ " vat : "+twodecimal.format(vat)+" Amount: "+twodecimal.format(amount));
            guide.setLocation(100, 350);
            timerfield.setOpaque(false);
            timerfield.setBounds(200, 500, 200, 30);
            timerfield.setText("check your report here!");
            /*Inputting the data obtained to the table database*/
            data[callNo][0]= callNo+1;//stores the call number
            data[callNo][1]= value; //stores the person making the call
            data[callNo][2]= fm.format(start);//stores the start date
            data[callNo][3]= time.format(start);//stores the start time
            data[callNo][4]= time.format(endt);//stores the end time
            data[callNo][5]= "Ksh. "+twodecimal.format(chargetime);//stores the charge time
            data[callNo][6]= "Ksh. "+twodecimal.format(vat);//stores the vat
            data[callNo][7]= "Ksh. "+twodecimal.format(amount);//stores the total amount used
            data[callNo][8]= h+"h:"+m+"m:"+s+"s";//stores the time taken 
            if(flag == 1){//in case where other network was used flag will be 1 while for the default flag will be 0
            data[callNo][9]= "OTHER";//stores the string other
            }else if(flag==0){
            data[callNo][9]= "DEFAULT";//stores the string 'default'
            }
            callNo++;//increamenting the call no to fit in the next call to be made
            File file = new File("c:\\users\\user\\data.txt");
            try {
                FileWriter writer = new FileWriter(file);
                String filecontents = "";
                for(int i =1;i<=callNo;i++)
                {
                    for(int j = 1;j<=9;j++)
                    {
                        String convert;
                        convert = String.valueOf(data[i][j])+ "\n";
                        filecontents = filecontents.concat(convert);
                    }
                }
                writer.write(filecontents);
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(CallingWindow.class.getName()).log(Level.SEVERE, null, ex);
            }  
            flag = 2; //this is to avoid the flag meeting network condition when making a call again
        }
    }
    }
/*This is the window where the report is to be seen*/
class Report{
    JTable table;
    void main(){
        Main.button.setVisible(false);//this is since this is the last window
        /*this is the heading of the table*/
        String [] headings = {"NO","Name","date", "Start time", "End Time", "Charge on time", "VAT", "Amount", "Time taken", "Network"};//this will be the title of the table
        /*creating the panel*/
        Main.panel();
        /*letting the exit button to return to the calling window*/
        Main.exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                   Main.frame.dispose();//disposing the frame to avoid many frame altogether
                   CallingWindow objs = new CallingWindow();
                   objs.main();//calling main class in calling window
            }
        });
        Main.panel.setLayout(new BorderLayout());//setting the panel to borderlayout instead of null to accomodate the table
        /*Making the table with the data from the calls and headings as indicated above*/
        table = new JTable(CallingWindow.data, headings);//Making the table
        table.setFillsViewportHeight(true);
        /*Making the button that will enable one to return home where we started the program*/
        Main.button.setVisible(true);//setting the button to be visible
        Main.button.setText("Go home");//setting the text to go home for user interface
        Main.button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String[]args = {"from return"};
                Main.frame.dispose();//disposing the now frame 
                Main obj = new Main();//creating an object
                Main.main(args);//calling on to main in the main class
            }
        });
        JScrollPane scroll = new JScrollPane(table);//creating a scroll pane that will be used by the table
        Main.panel.add(scroll, BorderLayout.NORTH);//Adding the scroll pane to the northern part since it is in borderlayout
        Main.frame();//making the frame
        Main.frame.setSize(1000, 620);//changing the frame size to fit in the table
        Main.frame.add(Main.panel);//adding the panel to the frame
                table.setVisible(true);//Making the table visible
            try {
            table.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}