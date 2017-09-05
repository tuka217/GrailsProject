package org.scheduler

class Auditorium {

    Integer level
    Integer roomNumber
    String buildingName

    static constraints = {
        level min: 0
        roomNumber blank: false, min: 1
        buildingName blank: false, maxSize: 50
    }
}
