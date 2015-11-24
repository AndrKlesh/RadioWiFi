package RadioClub.RadioWiFi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class RadioSetWiFi extends Activity
{
  Button _discover;
  ListView _listDevices;
  String[] _testListView = new String[]{"Device1", "Device2", "Device3", "Device4"};
  Button _send;
  TextView _chat;
  EditText _message;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    _listDevices = (ListView) findViewById(R.id.ListDevices);
    _listDevices.setAdapter(new ArrayAdapter<>(this, R.layout.item, _testListView));
    _listDevices.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id)
      {
        _listDevices.setVisibility(View.GONE);//GONE — элемент невидим и не занимает место в разметке
        _chat.setVisibility(View.VISIBLE);
        _chat.setText(">");
        _message.setVisibility(View.VISIBLE);
        _send.setVisibility(View.VISIBLE);
      }
    });
    _discover = (Button) findViewById(R.id.Button_Discover);
    _discover.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        _listDevices.setVisibility(View.VISIBLE);

      }
    });

    _chat = (TextView)findViewById(R.id.chatTextView);
    _message = (EditText)findViewById(R.id.message);
    _send = (Button) findViewById(R.id.SendButton);
    _send.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        _chat.append(_message.getText() + "\n" + ">");
      }
    });
  }
}
