package com.zadira.test3.Model

class User {
    var id: Int? = null
    var lessonName1: String? = null
    var group1: String? = null
    var room1: String? = null
    var time1: String? = null
    constructor(id: Int?, lessonName1: String?, group1: String?, room1: String?, time1: String?) {
        this.id = id
        this.lessonName1 = lessonName1
        this.group1 = group1
        this.room1 = room1
        this.time1 = time1
    }
    constructor(lessonName1: String?, group1: String?, room1: String?, time1: String?) {
        this.lessonName1 = lessonName1
        this.group1 = group1
        this.room1 = room1
        this.time1 = time1
    }

    constructor()


}