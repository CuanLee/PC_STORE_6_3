package cuanlee.pc_store;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import cuanlee.pc_store.database.database.GlobalContext;
import cuanlee.pc_store.domain.PC.RAM;
import cuanlee.pc_store.services.PC.Impl.RAMServiceImpl;

public class MainActivity extends Activity {

    private static final String TAG="RAM TEST";
    private Long id;
    private RAMServiceImpl ramService;
    private boolean isBound;

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
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Intent intent = new Intent(this, RAMServiceImpl.class);


        GlobalContext.context = this;
        ramService = RAMServiceImpl.getInstance();
        GlobalContext.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    protected void enterApp(View view)
    {
        Toast.makeText(MainActivity.this, "Let's Go....:)", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent (this, Save_RAM.class);
        startActivity(intent);
    }
}
