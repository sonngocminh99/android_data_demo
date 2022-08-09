package ncmbdataquick.mbaas.com.nifcloud.datastorequickstart;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.nifcloud.mbaas.core.DoneCallback;
import com.nifcloud.mbaas.core.NCMB;
import com.nifcloud.mbaas.core.NCMBException;
import com.nifcloud.mbaas.core.NCMBObject;


public class MainActivity extends AppCompatActivity {
    public static String APP_KEY = "2bfb444423219ff54256bbe41ff270c5d8c3e81eaa3121c18603363e99b0b673";
    public static String CLIENT_KEY = "2e0167555ae06b73a73a8b2ef1ea9614d566b17cb7c0d191da80797221088bf2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //**************** APIキーの設定とSDKの初期化 **********************
        NCMB.initialize(this.getApplicationContext(), APP_KEY, CLIENT_KEY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void doStartDemo(View view) {
        final NCMBObject obj = new NCMBObject("TestClass");
        try {
            obj.put("message", "Hello, NCMB!");
            obj.saveInBackground(new DoneCallback() {
                @Override
                public void done(NCMBException e) {
                    if (e != null) {
                        //保存失敗
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Notification from NIFCLOUD")
                                .setMessage("Error:" + e.getMessage())
                                .setPositiveButton("OK", null)
                                .show();

                    } else {
                        //保存成功
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Notification from NIFCLOUD")
                                .setMessage("Save successfull! with ID:" + obj.getObjectId())
                                .setPositiveButton("OK", null)
                                .show();

                    }
                }
            });
        } catch (NCMBException e) {
            e.printStackTrace();
        }


    }
}
