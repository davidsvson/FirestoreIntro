package se.iths.au20.firestoreintro

data class Item(var name: String? = null,
            var done: Boolean = false,
            var category: String? = null)