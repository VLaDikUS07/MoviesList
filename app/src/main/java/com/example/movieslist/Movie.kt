package com.example.movieslist

data class Movie(
    val title: String,
    val director: String,
    val year: Int,
    val posterRes: Int
)

val movies = listOf(
    Movie("5 сантиметров в секунду", "Макото Синкай", 2007, R.drawable.fivecentimeters),
    Movie("Джокер", "Тодд Филлипс", 2019, R.drawable.joker),
    Movie("Форрест Гамп", "Роберт Земекис", 1994, R.drawable.forrestgump)
)
