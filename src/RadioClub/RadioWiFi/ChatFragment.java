package RadioClub.RadioWiFi;

import android.app.Fragment;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ChatFragment extends Fragment implements WifiP2pManager.ConnectionInfoListener {

    private View contentView;

    private Button sendButton;
    private TextView chatTextView;
    private EditText messageEditText;

    private DataTransferThread dataTransferThread;

    public void addChatText(String str) {
        chatTextView.append(String.format("%s\n%s",str, getString(R.string.start_message_in_chat)));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_chat, container, false);
        contentView.setVisibility(View.GONE);
        return contentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        chatTextView = (TextView) contentView.findViewById(R.id.chat_textview);
        messageEditText = (EditText) contentView.findViewById(R.id.chat_message_edittext);
        sendButton = (Button) contentView.findViewById(R.id.chat_send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addChatText(messageEditText.getText().toString());
                dataTransferThread.sendMessage(messageEditText.getText().toString());
            }
        });
    }


    private void startDataTransfer(boolean isServer, String deviceAddress) {
        dataTransferThread = new DataTransferThread(this, isServer, deviceAddress);
        dataTransferThread.start();
    }

    public void resetViews() {
        if (dataTransferThread != null && dataTransferThread.isAlive()) {
            dataTransferThread.interrupt();
        }
        chatTextView.setText(getString(R.string.start_message_in_chat));
        this.getView().setVisibility(View.GONE);
    }

    @Override
    public void onConnectionInfoAvailable(WifiP2pInfo info) {
        startDataTransfer(info.isGroupOwner, info.groupOwnerAddress.getHostAddress());
        this.getView().setVisibility(View.VISIBLE);
    }
}
