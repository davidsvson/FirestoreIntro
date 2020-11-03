package se.iths.au20.firestoreintro

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()

     //   val item = Item("gurka", false, "grönsak")

        // lagra i ettav oss namngivet document
        //  db.collection("items").document("onion").set(item)

        // lagra i ett document som har autogenererat namn
     //   db.collection("items").add(item)

        val shoppingItems = mutableListOf<Item>()

        val itemsRef =  db.collection("items")

        // läser data _en_ gång
    /*    itemsRef.get().addOnSuccessListener {documentSnapshot ->
            for (document in documentSnapshot.documents) {
                val newItem = document.toObject(Item::class.java)
                if ( newItem != null)
                    shoppingItems.add(newItem)
                Log.d("!!!", "${newItem}")
            }

            Log.d("!!!", "List1: ${shoppingItems.size}")
        } */

        // vi vill lyssna på vår data och få reda på varje gång som den ändras
        itemsRef.addSnapshotListener { snapshot, e ->
            if( snapshot != null ) {
                shoppingItems.clear()
                for (document in snapshot.documents) {
                    val newItem = document.toObject(Item::class.java)
                    if ( newItem != null)
                        shoppingItems.add(newItem)
                    Log.d("!!!", "${newItem}")
                }
            }
            Log.d("!!!", "List1: ${shoppingItems.size}")
        }


        Log.d("!!!", "List2: ${shoppingItems.size}")





/* Kod från firestore guiden
        // Create a new user with a first and last name
        val user: MutableMap<String, Any> = HashMap()
        user["first"] = "Ada"
        user["last"] = "Lovelace"
        user["born"] = 1815

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference -> Log.d("!!!", "DocumentSnapshot added with ID: " + documentReference.id) }
                .addOnFailureListener { e -> Log.w("!!!", "Error adding document", e) }

*/


    }
}