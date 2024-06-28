import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;

public class ChatClient implements ActionListener, Runnable {
    private static final String SERVER_IP = "127.0.0.1";
    private Socket socket;
    private JFrame jframe;
    private JTextField jtf;
    private JTextArea jta;
    private JLabel jlb1, jlb2;
    private JPanel jp1, jp2;
    private String chatName;
    private PrintWriter pw;

    public ChatClient() {
        // 사용자 이름 입력받기
        chatName = JOptionPane.showInputDialog(jframe, "이름을 입력하세요!!", "사용자명 입력 다이얼로그", JOptionPane.YES_NO_OPTION);
        if (chatName == null || chatName.trim().isEmpty()) {
            System.exit(0);
        }

        // GUI 설정
        jframe = new JFrame("Chat Client");

        jp1 = new JPanel(new BorderLayout());
        jtf = new JTextField(30); // 30 글자 입력 가능
        JButton jbtn = new JButton("종료"); // 종료 버튼 생성
        jp1.add(jbtn, BorderLayout.EAST);
        jp1.add(jtf, BorderLayout.CENTER);

        jp2 = new JPanel(new BorderLayout());
        jlb1 = new JLabel("사용자명 : [" + chatName + "]"); // 대화명 표시
        jlb2 = new JLabel("서버 IP 주소 : " + SERVER_IP); // 서버 IP 주소 표시
        jp2.add(jlb1, BorderLayout.CENTER);
        jp2.add(jlb2, BorderLayout.EAST);

        jta = new JTextArea(10, 50); // 채팅 내용 표시
        jta.setEditable(false); // 채팅 내용 편집 불가
        JScrollPane jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        jframe.add(jp1, BorderLayout.SOUTH);
        jframe.add(jp2, BorderLayout.NORTH);
        jframe.add(jsp, BorderLayout.CENTER);

        // 이벤트 리스너 추가
        jtf.addActionListener(this);
        jbtn.addActionListener(this);
        jframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (pw != null) {
                    pw.println(chatName + "#exit"); // 채팅 종료 메시지 전송
                }
                System.exit(0); // 프로그램 종료
            }

            @Override
            public void windowOpened(WindowEvent e) {
                jtf.requestFocus(); // 텍스트 필드에 포커스 설정
            }
        });

        jframe.pack();
        jframe.setResizable(false); // 창 크기 변경 불가
        jframe.setVisible(true); // 창 표시
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = jtf.getText().trim();
        if (e.getSource() == jtf && !msg.isEmpty()) {
            // 메시지 입력 후 엔터를 누른 경우
            if (pw != null) {
                pw.println(chatName + "#" + msg); // 메시지 전송
            }
            jtf.setText(""); // 입력 필드 초기화
        } else if (e.getActionCommand().equals("종료")) {
            // 종료 버튼을 누른 경우
            if (pw != null) {
                pw.println(chatName + "#exit"); // 종료 메시지 전송
            }
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close(); // 소켓 닫기
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0); // 프로그램 종료
        }
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new ChatClient().init();
    }

    public void init() {
        try {
            socket = new Socket(SERVER_IP, 54321);
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            new Thread(this).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String message;
            String[] receiveMsg;
            while ((message = br.readLine()) != null) {
                receiveMsg = message.split("#");
                if (receiveMsg.length > 1) {
                    if (receiveMsg[1].equals("exit")) {
                        if (receiveMsg[0].equals(chatName)) {
                            System.exit(0);
                        } else {
                            jta.append(receiveMsg[0] + "님이 종료했습니다.\n");
                            jta.setCaretPosition(jta.getDocument().getLength());
                        }
                    } else {
                        jta.append(receiveMsg[0] + " : " + receiveMsg[1] + "\n");
                        jta.setCaretPosition(jta.getDocument().getLength());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
