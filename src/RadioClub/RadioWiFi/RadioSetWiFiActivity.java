package RadioClub.RadioWiFi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class RadioSetWiFiActivity extends Activity {
    private Button discoverButton;
    private ListView devicesListView;
    private String[] testListView;
    private Button sendButton;
    private TextView chatTextView;
    private EditText messageEditText;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testListView = new String[]{"Device1", "Device2", "Device3", "Device4"};

        devicesListView = (ListView) findViewById(R.id.main_devices_listview);
        devicesListView.setAdapter(new ArrayAdapter<>(this, R.layout.list_view_item, testListView));
        devicesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                devicesListView.setVisibility(View.GONE);//GONE — элемент невидим и не занимает место в разметке
                chatTextView.setVisibility(View.VISIBLE);
                chatTextView.setText(getString(R.string.start_message_in_chat));
                messageEditText.setVisibility(View.VISIBLE);
                sendButton.setVisibility(View.VISIBLE);
            }
        });
        discoverButton = (Button) findViewById(R.id.main_discover_button);
        discoverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                devicesListView.setVisibility(View.VISIBLE);

            }
        });

        chatTextView = (TextView) findViewById(R.id.main_chat_textview);
        messageEditText = (EditText) findViewById(R.id.main_message_edittext);
        sendButton = (Button) findViewById(R.id.main_send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatTextView.append(messageEditText.getText() + "\n" + getString(R.string.start_message_in_chat));
            }
        });
    }
}
