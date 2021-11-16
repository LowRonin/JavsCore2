package Lesson7.client;

import Lesson7.constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

    public class Client extends JFrame {
        private JTextField textField;
        private JTextArea textArea;

        private Socket socket;
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;
        private String login;
        boolean authCheck = false;

        public Client() {
            try {
                openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            prepareUI();
        }

        public void closeFrame(){
            this.dispose();
        }

        private void openConnection() throws IOException {
            socket = new Socket(Constants.SERVER_ADDRESS, Constants.SERVER_PORT);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    while (true) {
                        String messageFromServer = dataInputStream.readUTF();
                        if (messageFromServer.equals("/end")) {
                            break;
                        } else if(messageFromServer.startsWith(Constants.AUTH_OK_COMMAND)) {
                            String[] tokens = messageFromServer.split("\\s+");
                            this.login = tokens[1];
                            textArea.append("Успешно авторизован как " + login);
                            textArea.append("\n");
                            /*
                            Авторизация
                             */
                            authCheck = true;
                        }else if (messageFromServer.startsWith(Constants.CLIENTS_LIST_COMMAND)) {
                        } else {
                            textArea.append(messageFromServer);
                            textArea.append("\n");
                        }
                    }
                    textArea.append("Соединение разорвано");
                    textField.setEnabled(false);
                    closeConnection();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
        }


        private void closeConnection() {
            try {
                dataOutputStream.close();
            } catch (Exception ex) {

            }
            try {
                dataInputStream.close();
            } catch (Exception ex) {

            }
            try {
                socket.close();
            } catch (Exception ex) {

            }
        }

        private void sendMessage() {
            if (textField.getText().trim().isEmpty()) {
                return;
            }
            try {
                dataOutputStream.writeUTF(textField.getText());
                /*
                Выход по команде end
                */
                if (textField.getText().startsWith("/end")){
                    closeFrame();
                }
                textField.setText("");
                textField.grabFocus();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private void prepareUI() {
            setBounds(200, 200, 500, 500);
            setTitle("EchoClient");
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            add(new JScrollPane(textArea), BorderLayout.CENTER);


            JPanel panel = new JPanel(new BorderLayout());
            JButton button = new JButton("Send");
            panel.add(button, BorderLayout.EAST);
            textField = new JTextField();
            panel.add(textField, BorderLayout.CENTER);

            add(panel, BorderLayout.SOUTH);

            JPanel loginPanel = new JPanel(new BorderLayout());
            JTextField loginField = new JTextField();
            loginPanel.add(loginField, BorderLayout.WEST);
            JTextField passField = new JTextField();
            loginPanel.add(passField, BorderLayout.CENTER);
            JButton authButton = new JButton("Авторизоваться");
            loginPanel.add(authButton, BorderLayout.EAST);
            add(loginPanel, BorderLayout.NORTH);
            authButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        dataOutputStream.writeUTF(Constants.AUTH_COMMAND + " " + loginField.getText() + " " + passField.getText());

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

            });

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sendMessage();
                }
            });
            textField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sendMessage();
                }
            });

            setVisible(true);
            new Thread(() -> {
                try {
                    /*
                    Проверка авторизации
                     */
                    Thread.sleep(120000);
                    if (!authCheck){
                        closeConnection();
                        closeFrame();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(Client::new);
        }


    }
