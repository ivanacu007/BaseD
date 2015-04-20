package hppc.example.com.based;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
    //variables
    EditText edt_idauto, edt_marca, edt_modelo, edt_ano, edt_precio, edt_color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //se enlazan las variables con el id de cada vista u objeto
        edt_idauto=(EditText) findViewById(R.id.edt_idauto);
        edt_marca=(EditText) findViewById(R.id.edt_marca);
        edt_modelo=(EditText) findViewById(R.id.edt_modelo);
        edt_ano=(EditText) findViewById(R.id.edt_ano);
        edt_precio=(EditText) findViewById(R.id.edt_precio);
        edt_color=(EditText) findViewById(R.id.edt_color);

    }
    public void add (View v) {
        //Valido si hay campos vacios
            if (edt_idauto.getText().length()==0||edt_marca.getText().length()==0||edt_modelo.getText().length()==0||edt_ano.getText().length()==0||edt_precio.getText().length()==0||edt_color.getText().length()==0) {
                Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show();//texto en caso de que se cumpla la condicion
            }
            else
            {
            //Si no hay campos vacios se ejecuta el codigo para agregar el auto
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
            //Se limpian los edittext
            edt_idauto.setText("");
            edt_marca.setText("");
            edt_modelo.setText("");
            edt_ano.setText("");
            edt_precio.setText("");
            edt_color.setText("");

            Toast.makeText(this, "Se agregó un nuevo auto", Toast.LENGTH_SHORT).show();//Mensaje
            }
    }

    public void consultar(View v) {
        try{//try para ejecutar el codigo con probabilidad de un error o excepcion
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "autos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();//
        String id_auto = edt_idauto.getText().toString();
        Cursor fila = bd.rawQuery("select marca, modelo, ano, precio, color from autos where id_auto=" + id_auto, null);//se crea array con la consulta
        if (fila.moveToFirst()) {//se recorren las posiciones
            edt_marca.setText(fila.getString(0));//obtiene los valores de acuerdo a su posicion en el arreglo
            edt_modelo.setText(fila.getString(1));
            edt_ano.setText(fila.getString(2));
            edt_precio.setText(fila.getString(3));
            edt_color.setText(fila.getString(4));
        } else {
            Toast.makeText(this,"El auto no existe",Toast.LENGTH_SHORT).show();//mensaje
        }
        bd.close();//se cierra la base de datos
        }
        catch (Exception e)//catch que captura los errores
        {
            Toast.makeText(this,"Campos vacios",Toast.LENGTH_SHORT).show();//accion que se realiza en caso de encontrar excepciones o errores
            //en este caso, envio un mensaje
        }
    }
    public void eliminar(View v) {
        try {//otro try
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "autos", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String id_auto = edt_idauto.getText().toString();
            int cant = bd.delete("autos", "id_auto=" + id_auto, null);//elimina los datos de la tabla de acuerdo a su id
            bd.close();
            //se vacian los edittext
            edt_idauto.setText("");
            edt_marca.setText("");
            edt_modelo.setText("");
            edt_ano.setText("");
            edt_precio.setText("");
            edt_color.setText("");

            if (cant == 1) {//verifica si se eliminaron los datos
                Toast.makeText(this, "Auto eliminado", Toast.LENGTH_SHORT).show();//mensaje
            } else {
                Toast.makeText(this, "No existe el auto", Toast.LENGTH_SHORT).show();//si no se elimninan
            }
        }catch(Exception e){Toast.makeText(this,"Campos vacios",Toast.LENGTH_SHORT).show();}//catch muestra mensaje
    }
    public void modificar (View v) {
        try {//otro try mas
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "autos", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            String id_auto = edt_idauto.getText().toString();
            String marca = edt_marca.getText().toString();
            String modelo = edt_modelo.getText().toString();
            String ano = edt_ano.getText().toString();
            String precio = edt_precio.getText().toString();
            String color = edt_color.getText().toString();

            ContentValues registro = new ContentValues();

            registro.put("id_auto", id_auto);
            registro.put("marca", marca);
            registro.put("modelo", modelo);
            registro.put("ano", ano);
            registro.put("precio", precio);
            registro.put("color", color);


            int cant = bd.update("autos", registro, "id_auto=" + id_auto, null);
            bd.close();

            if (cant == 1) {
                Toast.makeText(this, "Se modificaron los datos del auto", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No existe el auto", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){Toast.makeText(this,"Campos vacios",Toast.LENGTH_SHORT).show();}
    }

    public void limpia (View v){//limpia los edittext
        edt_idauto.setText("");
        edt_marca.setText("");
        edt_modelo.setText("");
        edt_ano.setText("");
        edt_precio.setText("");
        edt_color.setText("");
    }
    public void ejemplo(View v){
        Intent intent = new Intent(getBaseContext(),ListViewEjemplo.class);
        startActivity(intent);
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
}
