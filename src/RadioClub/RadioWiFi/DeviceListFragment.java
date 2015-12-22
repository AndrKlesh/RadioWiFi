package RadioClub.RadioWiFi;

import android.app.ListFragment;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class DeviceListFragment extends ListFragment implements PeerListListener {

    private List<WifiP2pDevice> peers = new ArrayList<WifiP2pDevice>(5);

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.setListAdapter(new ArrayAdapter<>(getActivity(), R.layout.list_view_item, peers));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

    }


    @Override
    public void onPeersAvailable(WifiP2pDeviceList peerList) {

        peers.clear();
        peers.addAll(peerList.getDeviceList());
        ((ArrayAdapter) getListAdapter()).notifyDataSetChanged();
        }
}
