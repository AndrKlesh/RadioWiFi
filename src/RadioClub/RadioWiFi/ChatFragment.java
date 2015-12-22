package RadioClub.RadioWiFi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ChatFragment extends Fragment {

    private View mContentView;

    private Button sendButton;
    private TextView chatTextView;
    private EditText messageEditText;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContentView = inflater.inflate(R.layout.fragment_chat,container, false);
        chatTextView = (TextView) mContentView.findViewById(R.id.chat_textview);
        messageEditText = (EditText) mContentView.findViewById(R.id.chat_message_edittext);
        sendButton = (Button) mContentView.findViewById(R.id.chat_send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatTextView.append(messageEditText.getText() + "\n" + getString(R.string.start_message_in_chat));
            }
        });

        return mContentView;
    }
}
