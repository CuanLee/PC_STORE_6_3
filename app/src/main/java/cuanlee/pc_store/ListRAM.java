package cuanlee.pc_store;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Set;

import cuanlee.pc_store.database.database.GlobalContext;
import cuanlee.pc_store.domain.PC.RAM;
import cuanlee.pc_store.services.PC.Impl.RAMServiceImpl;

public class ListRAM extends AppCompatActivity {
    private static final String TAG="RAM TEST";
    private Long id;
    private RAMServiceImpl ramService;
    private boolean isBound;
    private RAM ram;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            RAMServiceImpl.ActivateServiceLocalBinder binder = (RAMServiceImpl.ActivateServiceLocalBinder) service;
            ramService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ram);
        Intent intent = getIntent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, RAMServiceImpl.class);

        GlobalContext.context = this;

        ramService = RAMServiceImpl.getInstance();
        GlobalContext.getAppContext().bindService(intent,connection, Context.BIND_AUTO_CREATE);

        Set<RAM> rams;
        rams = ramService.getAll();

        ArrayList<String> ramNames = new ArrayList<>();

        for (RAM ram: rams)
        {
            ramNames.add(ram.getCode() + "-" + ram.getDescription());
        }

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, ramNames);

        ListView listView = (ListView)findViewById(R.id.listView);

        listView.setAdapter(arrayAdapter);
    }
}
