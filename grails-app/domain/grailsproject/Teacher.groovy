package grailsproject

class Teacher {

    String firstName
    String lastName
    String degree

    static constraints = {
        firstName maxSize: 100
        lastName maxSize: 100
        degree inList: ['dr', 'dr hab.', 'mgr inż.', 'prof.']
    }
}
