/*package Model.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;


public class LectorJson {
    private static final String FILE_NAME = "data/config.json"; //Nom de l'arxiu a obrir

    public static ConfiguracioServer llegeixConfiguracioServer(String[] args) {             //Els args son useless
        Configuracio data = new Configuracio();          // Dades a carregar
        Gson        gson = new Gson();                   // Entitat Gson
        JsonReader  reader;                              // Reader de JSON

        try {               //Intentem carregar el fitxer json

            reader = new JsonReader(new FileReader(FILE_NAME));
            data = gson.fromJson(reader, Configuracio.class);
            return data.configuracioServer;

        } catch (FileNotFoundException e) {
            System.err.println("No s'ha pogut trobar el fitxer.");
            return null;
        }
    }

    public static ConfiguracioReserva llegeixConfiguracioReserva(String[] args) {             //Els args son useless
        Configuracio data = new Configuracio();          // Dades a carregar
        Gson        gson = new Gson();                   // Entitat Gson
        JsonReader  reader;                              // Reader de JSON

        try {               //Intentem carregar el fitxer json

            reader = new JsonReader(new FileReader(FILE_NAME));
            data = gson.fromJson(reader, Configuracio.class);
            return data.configuracioReserva;

        } catch (FileNotFoundException e) {
            System.err.println("No s'ha pogut trobar el fitxer.");
            return null;
        }
    }

    public static ConfiguracioEntrada llegeixConfiguracioEntrada(String[] args) {             //Els args son useless
        Configuracio data = new Configuracio();          // Dades a carregar
        Gson        gson = new Gson();                   // Entitat Gson
        JsonReader  reader;                              // Reader de JSON

        try {               //Intentem carregar el fitxer json

            reader = new JsonReader(new FileReader(FILE_NAME));
            data = gson.fromJson(reader, Configuracio.class);
            return data.configuracioEntrada;

        } catch (FileNotFoundException e) {
            System.err.println("No s'ha pogut trobar el fitxer.");
            return null;
        }
    }
}


*/
