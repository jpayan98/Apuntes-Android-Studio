package ramirezdelpozocorrales.jesusangel.prueba_examen_jesusangel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// SQLiteOpenHelper: clase auxiliar de Android que gestiona la creación y actualización
// de la base de datos SQLite. Al extenderla solo hay que implementar onCreate y onUpgrade.
// El sistema decide cuándo llamar a cada uno comparando el número de VERSION.
public class CrearBD extends SQLiteOpenHelper {

    // Constantes estáticas para evitar cadenas de texto sueltas por el código.
    // Si hay que cambiar un nombre, se cambia en un único sitio.
    public static final String NOMBRE_BD     = "libros.bd";
    public static final int    VERSION       = 1;            // Incrementar si cambia el esquema → dispara onUpgrade.
    public static final String TABLA_USUARIO = "users";

    public CrearBD(Context context) {
        super(context, NOMBRE_BD, null, VERSION);
        // null → no usamos CursorFactory personalizado (lo habitual).
    }

    // onCreate: se ejecuta UNA SOLA VEZ, cuando el fichero .db no existe todavía.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLA_USUARIO + " (" +
                "usuario TEXT PRIMARY KEY," +  // PRIMARY KEY garantiza que no haya dos usuarios con el mismo nombre.
                "password TEXT" +               // En caso de necesitar más campos, añadir coma aquí y la nueva columna debajo.
                ")";
        db.execSQL(sql);  // execSQL ejecuta SQL que no devuelve resultados (CREATE, DROP, INSERT…).
    }

    // onUpgrade: se ejecuta cuando VERSION en el código > VERSION de la BD instalada.
    // El enfoque más simple (y destructivo) es borrar las tablas y recrearlas desde cero.
    // En producción se usarían ALTER TABLE para conservar los datos del usuario.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // DROP TABLE IF EXISTS evita error si la tabla no existe por algún motivo.
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIO);
        // Reutilizamos onCreate para no repetir el SQL de creación.
        onCreate(db);
    }
}
