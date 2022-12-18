package com.example.quizzapp.model

class Repo {
    val data = arrayListOf(
        " Какая самая редкая группа крови?",
        " Кто из этих персонажей не входит в группу друзей из сериала \"Друзья\"?",
        "Сколько костей в теле человека?",
        "Fe — это символ какого химического элемента?"
    )
    val answersAsVariants = arrayListOf(
        arrayListOf("I группа", "II группа", "III группа", "IV группа"), //4
        arrayListOf("Рэйчел", "Джоуи", "Гюнтер", "Моника"),// 3
        arrayListOf("206", "205", "201", "209"), //1
        arrayListOf("Цинк", "Водород", "Фтор", "Железо") //4
    )
    val anserwsId = arrayListOf(3,2,0,3)
}