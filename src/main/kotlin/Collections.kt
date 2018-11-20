val myList = listOf<Int>() // same as emptyList()
val myOtherList = listOf(1) // SingletonList<E>
val mutableList = mutableListOf<Int>() // ArrayList
val emptyList = emptyList<Int>() // internal object EmptyList : List<Nothing>


myList.partition { }


list
.map { it.toString() }
.map { it.toUpperCase() }

list.map { it.toString().toUpperCase() }



list
.map { it.toString() }
.map { it.toUpperCase() } // map() call triggers a new O(n)-running for loop and a creation of a new list object that gets garbage-collected after processing is finished
Better: list.map { it.toString().toUpperCase() }
--
list
.map { it.toList() }
.flatMap { it }
Better:  list.flatMap { it.toList() }
--
list
.filterNotNull()
.map { it.toString() }
Better:  list.mapNotNull { it.toString() }
---
list
.map { it * 42 }
.last()
Better:
list
.last() * 42
=---
list
.filter { it % 2 == 1 }
.last() // first()
Better:
list.last { it % 2 == 1 }
----
list
.filter { it % 2 == 1 }
.count()
Better:
list.count { it % 2 == 1 }






