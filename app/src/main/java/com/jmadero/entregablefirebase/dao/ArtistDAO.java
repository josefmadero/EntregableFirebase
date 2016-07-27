package com.jmadero.entregablefirebase.dao;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jmadero.entregablefirebase.model.Artist;
import com.jmadero.entregablefirebase.model.ArtistContainer;

import java.util.List;

import util.ResultListener;


/**
 * Created by digitalhouse on 6/06/16.
 */
public class ArtistDAO {
//    extends SQLiteOpenHelper

    private DatabaseReference mDatabase;

    public void getArtistsFromFireBase(final ResultListener<List<Artist>> listenerFromController) {

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArtistContainer artistsContainer = dataSnapshot.getValue(ArtistContainer.class);
                        listenerFromController.finish(artistsContainer.getArtists());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

//    public void addProduct(final ResultListener<Product> listenerFromController, final Product product) {
//
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        mDatabase.child("results").push().setValue(product, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                if (databaseError != null) {
//                    listenerFromController.finish(product);
//                } else {
//                    listenerFromController.finish(null);
//                }
//
//            }
//        });
}
//
//    //CONSTANTES PARA LOS NOMBRES DE LA BD Y LOS CAMPOS
//    private static final String DATABASENAME = "ProductsDB";
//    private static final Integer DATABASEVERSION = 1;
//
//    //TABLA PERSONA CON SUS CAMPOS
//    private static final String TABLEPRODUCT = "Paint";
//    private static final String ID = "ID";
//    private static final String TITLE = "title";
//    private static final String PRICE = "Description";
//    private static final String THUMBNAIL = "Link";
//
//    //El contexto lo necesitamos para poder crear una BD.
//    private Context context;
//
//    //Constructor que permite crear la BD
//    public ArtistDAO(Context context) {
//        super(context, DATABASENAME, null, DATABASEVERSION);
//        this.context = context;
//    }
//
//    //CREACION DE LA BASE DE DATOS POR PRIMERA VEZ
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//        //Creo la tabla que contendrá mi base de datos
//        String createTable = "CREATE TABLE " + TABLEPRODUCT + "("
//                + ID + " TEXT PRIMARY KEY, "
//                + TITLE + " TEXT, "
//                + PRICE + " REAL, "
//                + THUMBNAIL + " TEXT" + ")";
//
//        db.execSQL(createTable);
//    }
//
//    //SE EJECUTA CUANDO SE MODIFICA ALGO EN LA ESTRUCTURA DE LA TABLA
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//        //BORRAR ESTO SI QUIEREN QUE CUANDO REINICIEN SU APP NO SE BORRE LA TABLA
//        db.execSQL("DROP TABLE IF EXISTS " + TABLEPRODUCT);
//        onCreate(db);
//    }
//
//    //METODO QUE ME PERMITE AGREGAR UNA LISTA DE PRODUCTOS A MI BASE DE DATOS
//    public void addProducts(List<Paint> products) {
//        for (Paint aProduct : products) {
//            this.addProduct(aProduct);
//        }
//    }
//
//    //METODO QUE ME PERMITE AGREGAR UN PRODUCTO A MI MI BD
//    public void addProduct(Paint product) {
//        SQLiteDatabase database = getWritableDatabase();
//
//        ContentValues row = new ContentValues();
//
//        //Obtengo los datos de la noticia y los cargo en el row
//        row.put(ID, product.getId());
//        row.put(TITLE, product.getTitle());
//        row.put(THUMBNAIL, product.getThumbnail());
//        row.put(PRICE, product.getPrice());
//
//        database.insert(TABLEPRODUCT, null, row);
//
//        database.close();
//    }
//
//    //ESTE METODO CREA LA TAREA ASINCRONA Y PIDE UNA LISTA DE PRODUCTOS
//    public void getAllProducts(final ResultListener<List<Paint>> listener) {
//        RetrieveProductsTask retrieveProductsTask = new RetrieveProductsTask(listener);
//        retrieveProductsTask.execute();
//    }
//
//    //ESTA CLASE ES UNA CLASE QUE ME PERMITE GENERAR UNA TAREA ASINCRONICA. ES DECIR, ESTA TAREA SE EJECUTARA
//// INDEPENDIENTEMENTE DE LO QUE ESTE HACIENDO COMO ACTIVIDAD PRINCIPAL
//    class RetrieveProductsTask extends AsyncTask<String, Void, List<Paint>> {
//
//        private ResultListener<List<Paint>> listener;
//
//        public RetrieveProductsTask(ResultListener<List<Paint>> listener) {
//            this.listener = listener;
//        }
//
//        //Esto método se ejecuta mientras sigue corriendo la tarea principal. Aqui lo que haremos es conectarnos
//        // al servicio y descargar la lista.
//        @Override
//        protected List<Paint> doInBackground(String... params) {
//
//            HTTPConnectionManager connectionManager = new HTTPConnectionManager();
//            String input = null;
//
//            try {
//                input = connectionManager.getRequestString("https://api.mercadolibre.com/sites/MLA/search?q=dvd");
//            } catch (DAOException e) {
//                e.printStackTrace();
//            }
//
//            Gson gson = new Gson();
//            PaintContainer productsContainer = gson.fromJson(input, PaintContainer.class);
//
//            return productsContainer.getResults();
//        }
//
//        //Una vez terminado el procesamiento, le avisamos al listener que ya tiene la lista disponible.
//        @Override
//        protected void onPostExecute(List<Paint> productList) {
//
//            addProducts(productList);
//            this.listener.finish(productList);
//        }
//    }





















