package cuanlee.pc_store;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import cuanlee.pc_store.database.database.GlobalContext;
import cuanlee.pc_store.domain.PC.RAM;
import cuanlee.pc_store.services.PC.Impl.RAMServiceImpl;

public class Confirm_Save extends Activity {

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
        setContentView(R.layout.activity_confirm__save);
        Intent intent = getIntent();

        TextView code = (TextView)findViewById(R.id.txtCode);
        TextView description = (TextView)findViewById(R.id.txtDescription);
        TextView memorySize = (TextView)findViewById(R.id.txtMemorySize);
        TextView memoryConfiguraion = (TextView)findViewById(R.id.txtMemConfig);

        code.setText(intent.getStringExtra("code"));
        description.setText(intent.getStringExtra("description"));
        memorySize.setText(intent.getStringExtra("memorySize"));
        memoryConfiguraion.setText(intent.getStringExtra("memoryConfiguration"));

        ram = new RAM.Builder()
                .code(intent.getStringExtra("code"))
                .description(intent.getStringExtra("description"))
                .memorySize(intent.getStringExtra("memorySize"))
                .voltage(1112)
                .memoryConfiguration(intent.getStringExtra("memoryConfiguration"))
                .stock(12)
                .active(1)
                .build();
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

    protected void saveRecord(View v)
    {
        RAM record = null;
        record = ramService.addRam(ram);

        if (record == null)
        {
            Toast.makeText(Confirm_Save.this,"Record Could Not Be Saved",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(Confirm_Save.this, "Your Record Was Succesfully Added", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ListRAM.class);
            startActivity(intent);
        }
    }
}
