package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main extends JFrame {
    //Panel1 : User Registration
    JPanel subscriberPanel;
    JTextField subName;
    JTextField subLastName;
    JTextField subMobileNumber;
    JTextField subCity;

    JLabel nameLabel;
    JLabel lastNameLabel;
    JLabel mobileNumberLabel;
    JLabel cityLabel;

    // Panel2 : Cycle
    JPanel cyclePanel;
    JTextField startCycleField;
    JTextField endCycleField;
    JTextField numberTVField;
    JLabel currDateLabel;
    JLabel startCycleLabel;
    JLabel endCycleLabel;
    JLabel numberOfTVLabel;
    SimpleDateFormat df;
    Date currentDate;

    // Panel3: Channels Packages
    JCheckBox sportsCheckBox;
    JCheckBox moviesCheckBox;
    JCheckBox documentaryCheckBox;
    JPanel packagesPanel;

    // Panel4: Package details
    JTextArea channelsAreaSports;
    JTextArea channelsAreaMovies;
    JTextArea channelsAreaDocumentary;
    JPanel detailsPanel;

    // Panel 5 : Check and Payments
    JPanel feePanel;
    JLabel installFeeLabel;
    JLabel packageFeeLabel;
    JLabel totalFeeLabel;

    // Panel 6 : Table (Data of Subscription
    JTable table;
    DefaultTableModel tableModel;
    JPanel p6Panel;

    // Panel 7 : Action Panel
    JPanel actionPanel;
    JButton saveBTN;
    JButton loadBTN;
    JButton newBTN;

    // Classes and Objects
    Subscriber subscriber;
    Subscription subscription;

    int packagesSelectedPrice = 0;
    int totalPrice;

    // Save Subscription
    ArrayList<Subscription> listToSave = new ArrayList<>();
    File file;

    //Constructor
    public Main(){

        // panel 1
        subscriberPanel = new JPanel();
        Border panel1Title = BorderFactory.createTitledBorder("Subscribers Details");
        subscriberPanel.setBorder(panel1Title);
        subscriberPanel.setBounds(15,15,300,200);
        subscriberPanel.setLayout(new GridLayout(4,2));

        //JLabel
        nameLabel = new JLabel("First Name: ");
        lastNameLabel = new JLabel("Last Name: ");
        mobileNumberLabel = new JLabel("Mobile: ");
        cityLabel = new JLabel("City: ");

        //JTextFields
        subName = new JTextField();
        subName.setOpaque(false);
        subLastName = new JTextField();
        subLastName.setOpaque(false);
        subMobileNumber = new JTextField();
        subMobileNumber.setOpaque(false);
        subCity = new JTextField();
        subCity.setOpaque(false);

        // Adding components to panel1
        subscriberPanel.add(nameLabel);
        subscriberPanel.add(subName);
        subscriberPanel.add(lastNameLabel);
        subscriberPanel.add(subLastName);
        subscriberPanel.add(mobileNumberLabel);
        subscriberPanel.add(subMobileNumber);
        subscriberPanel.add(cityLabel);
        subscriberPanel.add(subCity);

        // panel 2

        cyclePanel = new JPanel();
        Border cycleBorder = BorderFactory.createTitledBorder("Subscribers Details");
        cyclePanel.setBounds(15,230,300,500);
        cyclePanel.setLayout(new GridLayout(14, 1));
        cyclePanel.setBorder(cycleBorder);

        // Adding components to panel2
        currDateLabel = new JLabel();
        df = new SimpleDateFormat("dd/MM/yyyy");
        currentDate = new Date();
        currDateLabel.setText("Today: "+ df.format(currentDate));

        //start & end cycle date
        startCycleLabel = new JLabel("Start Cycle Date (DD/MM/YYYY)");
        startCycleField = new JTextField();
        endCycleLabel = new JLabel("End Cycle Date (DD/MM/YYYY)");
        endCycleField = new JTextField();

        numberOfTVLabel = new JLabel("Number of TVs : ");
        numberTVField = new JTextField();

        cyclePanel.add(currDateLabel);
        cyclePanel.add(startCycleLabel);
        cyclePanel.add(startCycleField);
        cyclePanel.add(endCycleLabel);
        cyclePanel.add(endCycleField);
        cyclePanel.add(numberOfTVLabel);
        cyclePanel.add(numberTVField);

        //make opacity for fields
        startCycleField.setOpaque(false);
        endCycleField.setOpaque(false);
        numberTVField.setOpaque(false);

        // panel 3
        packagesPanel = new JPanel();
        packagesPanel.setBounds(330,15,300,200);
        packagesPanel.setLayout(new GridLayout(5,1));
        Border packBorder = BorderFactory.createTitledBorder("Available Packages");
        packagesPanel.setBorder(packBorder);

        JLabel packagesLabel = new JLabel("Please select your Packages");

        sportsCheckBox = new JCheckBox("Sports Packages");
        moviesCheckBox = new JCheckBox("Movies Package");
        documentaryCheckBox = new JCheckBox("Documentary Package");

        JButton subscribeBTN = new JButton("Subscribe");

        packagesPanel.add(packagesLabel);
        packagesPanel.add(sportsCheckBox);
        packagesPanel.add(moviesCheckBox);
        packagesPanel.add(documentaryCheckBox);
        packagesPanel.add(subscribeBTN);

        //Checkbox item Listeners
        sportsCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(sportsCheckBox.isSelected()){
                    DisplaySportsChannels();
                }else{
                    channelsAreaSports.setText("");
                }
            }
        });

        moviesCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(moviesCheckBox.isSelected()){
                    DisplayMoviesChannels();
                }else{
                    channelsAreaMovies.setText("");
                }
            }
        });

        documentaryCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(documentaryCheckBox.isSelected()){
                    DisplayDocumentaryChannels();

                }else{
                    channelsAreaDocumentary.setText("");
                }
            }
        });

        subscribeBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    getSubscriberData();
                }catch(Exception ee){

                }
            }
        });

        //Panel 4
        detailsPanel = new JPanel();
        detailsPanel.setBounds(330, 230, 300, 500);
        detailsPanel.setLayout(new GridLayout(3,1));
        Border p4Border = BorderFactory.createTitledBorder("Available Channels");
        detailsPanel.setBorder(p4Border);

        channelsAreaSports = new JTextArea(5,1);
        channelsAreaSports.setEditable(false);
        channelsAreaSports.setOpaque(false);
        channelsAreaSports.setLineWrap(true);

        channelsAreaMovies = new JTextArea(5,1);
        channelsAreaMovies.setEditable(false);
        channelsAreaMovies.setOpaque(false);
        channelsAreaMovies.setLineWrap(true);

        channelsAreaDocumentary = new JTextArea(5,1);
        channelsAreaDocumentary.setEditable(false);
        channelsAreaDocumentary.setOpaque(false);
        channelsAreaDocumentary.setLineWrap(true);

        detailsPanel.add(channelsAreaSports);
        detailsPanel.add(channelsAreaMovies);
        detailsPanel.add(channelsAreaDocumentary);

        // panel 5
        feePanel = new JPanel();
        feePanel.setBounds(645, 15,200,200);
        feePanel.setLayout(new GridLayout(3,1));

        Border border5 = BorderFactory.createTitledBorder("Fee and Check");
        feePanel.setBorder(border5);

        installFeeLabel = new JLabel("Installation Fee: ");
        packageFeeLabel = new JLabel("Packages Fee: ");
        totalFeeLabel = new JLabel("Total Amount to Pay: ");

        feePanel.add(installFeeLabel);
        feePanel.add(packageFeeLabel);
        feePanel.add(totalFeeLabel);

        // Panel 6
        p6Panel = new JPanel();
        p6Panel.setBounds(645,230,515,500);
        p6Panel.setLayout(new GridLayout(3,1));

        Border border6 = BorderFactory.createTitledBorder("Our Customers");
        p6Panel.setBorder(border6);

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Phone Number");
        tableModel.addColumn("Start Cycle");
        tableModel.addColumn("End Cycle");
        tableModel.addColumn("Total Fee");

        JScrollPane scrollPane = new JScrollPane(table);
        p6Panel.add(scrollPane);

        // Panel 7
        actionPanel = new JPanel();
        actionPanel.setBounds(860,15,300,200);
        Border border7 = BorderFactory.createTitledBorder("Action Tab");
        actionPanel.setBorder(border7);
        actionPanel.setLayout(new GridLayout(4,1));


        saveBTN = new JButton("Save Subscription");
        loadBTN = new JButton("Load Subscription");
        newBTN = new JButton("New Subscription");

        actionPanel.add(newBTN);
        actionPanel.add(saveBTN);
        actionPanel.add(loadBTN);

        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveSubscriptionToDisk();
            }
        });

        newBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewSubscription();
            }
        });

        loadBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Subscription>  k= LoadDataFromDisk();
            }
        });

        // Adding Panels to JFrame
        setLayout(null);     //null Layout for JFrame
        add(subscriberPanel);
        add(cyclePanel);
        add(packagesPanel);
        add(detailsPanel);
        add(feePanel);
        add(p6Panel);
        add(actionPanel);

    }
    // Methods

    private int DisplaySportsChannels() {
        SportChannel m1 = new SportChannel("STAR SPORTS","EN","SPORTS",4);
        SportChannel m2 = new SportChannel("STAR SPORTS HINDI","HINDI","SPORTS",5);
        SportChannel m3 = new SportChannel("TEN SPORTS","EN","SPORTS",4);
        SportChannel m4 = new SportChannel("SONY PIX","EN","SPORTS",2);
        SportChannel m5 = new SportChannel("STAR SPORTS HD","EN","SPORTS",3);
        SportChannel m6 = new SportChannel("TEN SPORTS 1","EN","SPORTS",2);

        ArrayList<SportChannel> sportList = new ArrayList<>();
        sportList.add(m1);
        sportList.add(m2);
        sportList.add(m3);
        sportList.add(m4);
        sportList.add(m5);
        sportList.add(m6);

        String sportChannelString = "";
        int packagePrice =0;
        for(int i=0; i<sportList.size(); i++){
            sportChannelString += "      "+sportList.get(i).getChannelName()+
                    "       "+ sportList.get(i).getChannelLanguage()+
                    "       "+ sportList.get(i).getPrice() +
                    "\n";
            packagePrice += sportList.get(i).getPrice();
        }
        channelsAreaSports.setText(sportChannelString);
        return packagePrice;
    }

    private int DisplayMoviesChannels() {
        MovieChannel m1 = new MovieChannel("STAR GOLD", "Hindi","MOV",2);
        MovieChannel m2 = new MovieChannel("MAX", "Hindi","MOV",5);
        MovieChannel m3 = new MovieChannel("ZEE CINEMA", "Hindi","MOV",3);
        MovieChannel m4 = new MovieChannel("MOVIES OK", "Hindi","MOV",1);
        MovieChannel m5 = new MovieChannel("HBO", "EN","MOV",4);
        MovieChannel m6 = new MovieChannel("STAR MOVIES", "EN","MOV",4);

        ArrayList<MovieChannel> movieList = new ArrayList<>();
        movieList.add(m1);
        movieList.add(m2);
        movieList.add(m3);
        movieList.add(m4);
        movieList.add(m5);
        movieList.add(m6);

        String movChannelString = "";
        int packagePrice = 0;

        for(int i=0; i<movieList.size(); i++){
            movChannelString += "      "+movieList.get(i).getChannelName()+
                    "       "+ movieList.get(i).getChannelLanguage()+
                    "       "+ movieList.get(i).getPrice() +
                    "\n";
            packagePrice += movieList.get(i).getPrice();
        }
        channelsAreaMovies.setText(movChannelString);
        return packagePrice;
    }

    private int DisplayDocumentaryChannels() {
        DocumentaryChannel m1 = new DocumentaryChannel("NAT GEO", "EN", "DOC", 3);
        DocumentaryChannel m2 = new DocumentaryChannel("Discovery", "EN", "DOC", 2);
        DocumentaryChannel m3 = new DocumentaryChannel("History", "EN", "DOC", 3);
        DocumentaryChannel m4 = new DocumentaryChannel("Animal Planet", "EN", "DOC", 4);
        DocumentaryChannel m5 = new DocumentaryChannel("Discovery+", "EN", "DOC", 5);
        DocumentaryChannel m6 = new DocumentaryChannel("World Documentary", "EN", "DOC", 1);

        ArrayList<DocumentaryChannel> documentaryChannels = new ArrayList<>();
        documentaryChannels.add(m1);
        documentaryChannels.add(m2);
        documentaryChannels.add(m3);
        documentaryChannels.add(m4);
        documentaryChannels.add(m5);
        documentaryChannels.add(m6);

        String docChannelString = "";
        int packagePrice = 0;

        for (int i = 0; i < documentaryChannels.size(); i++) {
            docChannelString += "      " + documentaryChannels.get(i).getChannelName() +
                    "       " + documentaryChannels.get(i).getChannelLanguage() +
                    "       " + documentaryChannels.get(i).getPrice() +
                    "\n";
            packagePrice += documentaryChannels.get(i).getPrice();
        }
        channelsAreaDocumentary.setText(docChannelString);
        return packagePrice;

    }



    private void DisplaySubscriptionsInTable(Subscription sub) {
        // Displaying Data

        tableModel.addRow(new Object[]{
                sub.getSubscriber().getFirstName(),
                sub.getSubscriber().getLastName(),
                sub.getSubscriber().getPhone(),
                sub.getCycle().getStartDate(),
                sub.getCycle().getEndDate(),
                sub.getTotalFee()
        });
    }

    private ArrayList<Subscription> LoadDataFromDisk() {
        ArrayList<Subscription> s = new ArrayList<>();
        file = new File("D:\\mytvfile.dat");

        try{
            InputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);

            s = (ArrayList) ois.readObject();
            ois.close();
            is.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for(Subscription sub : s){
            DisplaySubscriptionsInTable(sub);
        }
        return s;
    }

    private void getSubscriberData() throws ParseException {
        Date currentDate1 = new Date();

        //Subscriber data:
        Subscriber subscriber = new Subscriber(subName.getText(),subLastName.getText(),subCity.getText(),
                Integer.parseInt(subMobileNumber.getText()));

        //Cycle
        Date startCycle = df.parse(startCycleField.getText());
        Date endCycle = df.parse(endCycleField.getText());

        SubscriptionCycle cycle = new SubscriptionCycle(
                df.format(startCycle),
                df.format(endCycle)
        );

        //Subscription
        subscription = new Subscription(
                Integer.parseInt(numberTVField.getText()),
                subscriber,
                cycle,
                df.format(currentDate1)
        );

        installFeeLabel.setText("Installation Fee: $ " + subscription.getTotalFee());

        ShowPrice();
    }

    private void ShowPrice() {
        if(documentaryCheckBox.isSelected()){
            packagesSelectedPrice += DisplayDocumentaryChannels();
        }else if(moviesCheckBox.isSelected()){
            packagesSelectedPrice += DisplayMoviesChannels();
        }else if(sportsCheckBox.isSelected()){
            packagesSelectedPrice += DisplaySportsChannels();
        }

        packageFeeLabel.setText("Packages Fee : $ " +packagesSelectedPrice);
        totalPrice = subscription.getTotalFee()+ packagesSelectedPrice;

        totalFeeLabel.setText("Total Amount To Pay: $ " +totalPrice);
    }

    private void NewSubscription() {
        subName.setText("");
        subLastName.setText("");
        subCity.setText("");
        subMobileNumber.setText("");

        startCycleField.setText("");
        endCycleField.setText("");
        numberTVField.setText("");

        installFeeLabel.setText("Installation Fee: ");
        packageFeeLabel.setText("Packages Fee: ");
        totalFeeLabel.setText("Total Amount to Pay: ");

        moviesCheckBox.setSelected(false);
        documentaryCheckBox.setSelected(false);
        sportsCheckBox.setSelected(false);
    }

    private void SaveSubscriptionToDisk() {
        listToSave.add(subscription);

        file = new File("D:\\mytvfile.dat");

        try{
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);

            //saving the list
            oos.writeObject(listToSave);
            oos.flush();
            oos.close();
            os.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





    public static void main(String[] args) {
	    Main mainScreen = new Main();
	    mainScreen.setVisible(true);
	    mainScreen.setBounds(20,10,1200,800);
    }
}
