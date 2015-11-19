package sv.edu.ucad.inscripcion;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase; //Librerias para conexion
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PpalActivity extends AppCompatActivity {

    protected Button boton;
    protected TextView vinculo;
    EditText etusu;
    EditText etpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ppal);

        etusu = (EditText)findViewById(R.id.editusuario);
        TextInputLayout til = (TextInputLayout)findViewById(R.id.textlayout1);
        EditText et1 = (EditText) til.findViewById(R.id.editusuario);
        til.setHint(getString(R.string.txtusuario));

        etpass = (EditText)findViewById(R.id.editpassword);
        TextInputLayout til2 = (TextInputLayout)findViewById(R.id.textlayout2);
        EditText et2 = (EditText) til2.findViewById(R.id.editpassword);
        til2.setHint(getString(R.string.txtpassword));

        vinculo = (TextView)findViewById(R.id.newcount);
        vinculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next=new Intent(PpalActivity.this,NuevoUsuario.class);
                startActivity(next);
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Bienvenido a Inscripcion Online UCAD", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(PpalActivity.this, "admonInscripcion", null, 1);
                // Crea o abre una base de datos!!!
                SQLiteDatabase bd = admin.getWritableDatabase();

                String xcarnet = etusu.getText().toString();

                //devuelve 0 o 1 una fila, es una consulta
                Cursor fila = bd.rawQuery(
                        "select carnet from estudiantes where carnet=" + xcarnet, null);
                if (fila.moveToFirst()) {
                    Snackbar.make(v, "Bienvenido" + xcarnet, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Intent next = new Intent(PpalActivity.this, layout_inicio_inscripcion.class);
                    startActivity(next);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Carnet Invalido", Toast.LENGTH_LONG).show();
                }
                bd.close();
            }
        });//fin del listenes de boton
    }//fin del onCreate
}//fin de la clase
