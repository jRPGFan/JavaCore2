package Homework_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class ClientGUI extends JFrame implements ActionListener,
        Thread.UncaughtExceptionHandler {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();

    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top");
    private final JTextField tfLogin = new JTextField("p.stora");
    private final JPasswordField tfPassword = new JPasswordField("!6s(<pw$");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("<html><b>Disconnect</b></html>");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    private final JList<String> userList = new JList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            }
        });
    }

    private File chatLog;

    private PrintWriter printWriter;

    private ClientGUI() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        JScrollPane scrollUsers = new JScrollPane(userList);
        String[] users = {"user1", "user2", "user3", "user4", "user5", "user6",
                            "user_with_an_exceptionally_long_nickname"};
        userList.setListData(users);
        scrollUsers.setPreferredSize(new Dimension(100, 0));
        cbAlwaysOnTop.addActionListener(this);
        btnSend.addActionListener(this);
        tfMessage.addActionListener(this);

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);

        add(scrollLog, BorderLayout.CENTER);
        add(scrollUsers, BorderLayout.EAST);
        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.SOUTH);

        setVisible(true);

        //openChatLogToWriter();
        try {
            chatLog = new File("Homework_4\\ChatLog.txt");
            if (!chatLog.createNewFile()){
                Scanner chatLogReader = new Scanner(chatLog, "UTF-8");
                while (chatLogReader.hasNextLine()){
                    log.append(chatLogReader.nextLine() + "\n");
                }
            }

        } catch (IOException e){
            System.out.println("IOException occurred when creating ChatLog.txt");
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == tfMessage){
            btnSendPressed();
        } else if (src == btnSend){
            btnSendPressed();
        } else if (src == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } else {
            throw new RuntimeException("Undefined source: " + src);
        }
    }

//    private void openChatLogToWriter(){
//        try {
//            File chatLog = new File("Homework_4\\ChatLog.txt");
//            FileWriter writeToChatLog = new FileWriter(chatLog, true);
//            chatLog.createNewFile();
//            BufferedWriter bufferedWriter = new BufferedWriter(writeToChatLog);
//            printWriter = new PrintWriter(bufferedWriter);
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }
    private void btnSendPressed(){
        String message = tfMessage.getText();
        if (message.length() > 0) {
            //System.out.println("sent");
            log.append(message + "\n");

            try (FileWriter writeToChatLog = new FileWriter(chatLog, true);
                 BufferedWriter bufferedWriter = new BufferedWriter(writeToChatLog);
                 PrintWriter printWriter = new PrintWriter(bufferedWriter))
            {
                //writeToChatLog.append(message + "\n");
                //writeToChatLog.close();
                printWriter.println(message);
                printWriter.close();
            } catch (IOException e){
                System.out.println("IOException on writing to ChatLog.txt");
                e.printStackTrace();
            }

            tfMessage.setText("");
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        StackTraceElement[] ste = e.getStackTrace();
        String msg = String.format("Exception in thread \"%s\" %s: %s\n\tat %s",
                t.getName(), e.getClass().getCanonicalName(),
                e.getMessage(), ste[0]);
        JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}
