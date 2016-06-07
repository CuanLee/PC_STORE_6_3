package cuanlee.pc_store;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import cuanlee.pc_store.domain.PC.RAM;

public class Save_RAM extends Activity {
    private static final String TAG="RAM TEST1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save__ram);
        Intent intent = getIntent();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    protected void saveRam(View view)
    {
        EditText code = (EditText)findViewById(R.id.txtBxCode);
        EditText description = (EditText)findViewById(R.id.txtBxDesc);
        EditText memorySize = (EditText)findViewById(R.id.txtBxMemSize);
        EditText memoryConfiguraion = (EditText)findViewById(R.id.txtBxMemCon);

        RAM entity = new RAM.Builder()
                .code(code.getText().toString())
                .description(description.getText().toString())
                .memorySize(memorySize.getText().toString())
                .voltage(1221)
                .memoryConfiguration(memoryConfiguraion.getText().toString())
                .stock(1222)
                .active(1)
                .build();

        Intent intent = new Intent(this,Confirm_Save.class);
        intent.putExtra("code", entity.getCode());
        intent.putExtra("description",entity.getDescription());
        intent.putExtra("memorySize", entity.getMemorySize());
        intent.putExtra("memoryConfiguration", entity.getMemoryConfiguration());

        startActivity(intent);
    }
}
