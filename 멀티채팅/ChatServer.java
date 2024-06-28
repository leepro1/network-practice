import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer extends JFrame {
    private final List<PrintWriter> clientWriters = new ArrayList<>();
    private final JTextArea ta; // 서버 로그를 표시할 텍스트 영역
    private final JTextField tf; // 서버 상태를 표시할 텍스트 필드
    private ServerSocket serverSocket;
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public ChatServer() {
        setTitle("멀티 채팅 서버");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ta = new JTextArea();
        add(new JScrollPane(ta), BorderLayout.CENTER); // 스크롤 가능 텍스트 영역 추가

        tf = new JTextField();
        tf.setEditable(false); // 텍스트 필드는 편집 불가능
        add(tf, BorderLayout.SOUTH);

        setSize(300, 300);
        setVisible(true);

        try (ServerSocket serverSocket = new ServerSocket(54321)) {
            this.serverSocket = serverSocket;
            tf.setText("서버 정상 실행 중입니다.");
            ta.append("서버가 54321 포트에서 시작되었습니다.\n");

            while (true) {
                Socket socket = serverSocket.accept(); // 클라이언트 연결 대기
                executorService.submit(new ClientHandler(socket));
                ta.append(socket.getInetAddress() + " IP 주소의 사용자가 입장하셨습니다.\n");
                tf.setText("현재 사용자 수: " + clientWriters.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatServer();
    }

    // 클라이언트와의 통신을 담당하는 클래스
    private class ClientHandler implements Runnable {
        private final Socket socket;
        private PrintWriter pw;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true)) {

                this.pw = pw;
                synchronized (clientWriters) {
                    clientWriters.add(pw);
                }

                String message;
                boolean isStop = false;

                while (!isStop && (message = br.readLine()) != null) {
                    String[] str = message.split("#");

                    if (str[1].equals("exit")) {
                        isStop = true;
                        synchronized (clientWriters) {
                            clientWriters.remove(pw);
                        }
                        ta.append(socket.getInetAddress() + " IP 주소의 사용자가 종료하셨습니다.\n");
                        tf.setText("남은 사용자 수: " + clientWriters.size());
                    }

                    broadcastMessage(message);
                }
            } catch (IOException e) {
                synchronized (clientWriters) {
                    clientWriters.remove(pw);
                }
                ta.append(socket.getInetAddress() + " IP 주소의 사용자가 비정상 종료하셨습니다.\n");
                tf.setText("남은 사용자 수: " + clientWriters.size());
            }
        }

        private void broadcastMessage(String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters) {
                    writer.println(message);
                }
            }
        }
    }
}
