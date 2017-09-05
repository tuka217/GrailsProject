package org.scheduler

class Teacher {
    String firstName
    String lastName
    String degree

    static constraints = {
        firstName maxSize: 100
        lastName maxSize: 100
        degree inList: ['dr', 'dr hab.', 'mgr inż.', 'prof.']
        version nullable: true
        id nullable: true
    }

//    static mapping = {
//        table "Teacher"
//        version false
//        id generator: 'uuid',
//    }
}
