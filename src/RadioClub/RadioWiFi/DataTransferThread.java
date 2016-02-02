package RadioClub.RadioWiFi;

import android.app.Fragment;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;


public class DataTransferThread extends Thread {

    private Fragment fragment;
    private String deviceAddress;
    private boolean isServer;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public DataTransferThread(Fragment fragment, boolean isServer, String deviceAddress) {
        this.fragment = fragment;
        this.isServer = isServer;
        this.deviceAddress = deviceAddress;
    }

    @Override
    public void run() {

        if (isServer) {
            startServer();
        } else {
            startClient();
        }
    }


    private void startServer() {
        ServerSocket serverSocket = null;
        Socket client = null;
        try {
            serverSocket = new ServerSocket(8888);
            client = serverSocket.accept();
            dataInputStream = new DataInputStream(client.getInputStream());
            dataOutputStream = new DataOutputStream(client.getOutputStream());
            while (!this.isInterrupted()) {
                final String receivedMessage = dataInputStream.readUTF();
                fragment.getView().post(new Runnable() {
                    @Override
                    public void run() {
                        ((ChatFragment)fragment).addChatText(receivedMessage);
                    }
                });
            }

        } catch (IOException e) {

        } finally {
            try {
                if (client != null) {
                    client.close();
                }

                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
            }
        }
    }

    private void startClient() {
        Socket client = null;
        try {
            client = new Socket(deviceAddress, 8888);
            dataInputStream = new DataInputStream(client.getInputStream());
            dataOutputStream = new DataOutputStream(client.getOutputStream());

            while (!this.isInterrupted()) {
                final String receivedMessage = dataInputStream.readUTF();
                fragment.getView().post(new Runnable() {
                    @Override
                    public void run() {
                        ((ChatFragment)fragment).addChatText(receivedMessage);
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public void sendMessage(final String msg) {
        try {
            dataOutputStream.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


