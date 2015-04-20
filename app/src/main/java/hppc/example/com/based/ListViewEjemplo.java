package hppc.example.com.based;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class ListViewEjemplo extends Activity {
    ListView listaxx;
    EditText edt_idauto, edt_marca, edt_modelo, edt_ano, edt_precio, edt_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_ejemplo);
/*
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "autos", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String id_auto = edt_idauto.getText().toString();
            Cursor fila = bd.rawQuery("select id_auto, marca, modelo, ano, precio, color from autos where id_auto=" + id_auto, null);

            edt_modelo.setText(fila.getString(1));
            edt_ano.setText(fila.getString(2));
            edt_precio.setText(fila.getString(3));
            edt_color.setText(fila.getString(4));


            bd.close();
        } catch (Exception e) {
            Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show();
        }


        listaxx = (ListView) findViewById(R.id.listax);
        edt_idauto = (EditText) findViewById(R.id.edt_idauto);
        edt_marca = (EditText) findViewById(R.id.edt_marca);
        edt_modelo = (EditText) findViewById(R.id.edt_modelo);
        edt_ano = (EditText) findViewById(R.id.edt_ano);
        edt_precio = (EditText) findViewById(R.id.edt_precio);
        edt_color = (EditText) findViewById(R.id.edt_color);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "autos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        //Se obtiene el texto de los edittext y de almacena en las variables
        String id_auto = edt_idauto.getText().toString();
        String marca = edt_marca.getText().toString();
        String modelo = edt_modelo.getText().toString();
        String ano = edt_ano.getText().toString();
        String precio = edt_precio.getText().toString();
        String color = edt_color.getText().toString();
        ContentValues registro = new ContentValues();//Array para almacenar los datos

        //Se almacenan los datos en los campos de la base de datos
        registro.put("id_auto", id_auto);
        registro.put("marca", marca);
        registro.put("modelo", modelo);
        registro.put("ano", ano);
        registro.put("precio", precio);
        registro.put("color", color);
        bd.insert("autos", null, registro); //Se insertan los datos del array en la tabla autos
        bd.close();//Se cierra la bd
    }

*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_view_ejemplo, menu);
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
}
