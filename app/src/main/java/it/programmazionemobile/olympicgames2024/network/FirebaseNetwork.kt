package it.programmazionemobile.olympicgames2024.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore


open class FirebaseNetwork(initialCollectionPath: String) {
    protected val matchesReference: CollectionReference =
        FirebaseFirestore.getInstance().collection(initialCollectionPath)
    protected val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
}