package Lesson_4;

import javax.swing.*;

/**
 * Создать окно для клиентской части чата: большое текстовое поле для отображения переписки в центре окна.
 * Однострочное текстовое поле для ввода сообщений и кнопка для отсылки сообщений на нижней панели.
 * Сообщение должно отсылаться либо по нажатию кнопки на форме, либо по нажатию кнопки Enter.
 * При «отсылке» сообщение перекидывается из нижнего поля в центральное.
 */

public class ChatApp extends JFrame {

    public ChatApp(){
        setTitle("Chat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100,0,400,615);
        setLayout(null);

        JTextArea messagesField = new JTextArea();
        messagesField.setBounds(0, 0, 400, 400);
        add(messagesField);

        JButton sendMessageButton = new JButton("Отправить сообщение");
        sendMessageButton.setBounds(100, 415, 200, 50);
        add(sendMessageButton);


        JTextField userMessageField = new JTextField();
        userMessageField.setBounds(0, 480, 400,100);
        add(userMessageField);

        sendMessageButton.addActionListener(e -> {
            messagesField.setText(messagesField.getText()+ "\n" +userMessageField.getText());
            userMessageField.setText("");
        });

        userMessageField.addActionListener(e -> {
            messagesField.setText(messagesField.getText()+ "\n" +userMessageField.getText());
            userMessageField.setText("");
        });





        setVisible(true);
    }

    public static void main(String[] args) {
        new ChatApp();
    }

}
