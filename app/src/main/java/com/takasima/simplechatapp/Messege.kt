package com.takasima.simplechatapp

class Messege {
    var messege: String? = null
    var senderID: String? = null


    constructor(){}
    constructor(messege: String?, senderID: String?) {
        this.messege = messege
        this.senderID = senderID
    }
}