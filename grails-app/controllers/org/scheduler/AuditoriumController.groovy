package org.scheduler

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AuditoriumController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Auditorium.list(params), model:[auditoriumCount: Auditorium.count()]
    }

    def show(Auditorium auditorium) {
        respond auditorium
    }

    def create() {
        respond new Auditorium(params)
    }

    @Transactional
    def save(Auditorium auditorium) {
        if (auditorium == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (auditorium.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond auditorium.errors, view:'create'
            return
        }

        auditorium.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'auditorium.label', default: 'Auditorium'), auditorium.id])
                redirect auditorium
            }
            '*' { respond auditorium, [status: CREATED] }
        }
    }

    def edit(Auditorium auditorium) {
        respond auditorium
    }

    @Transactional
    def update(Auditorium auditorium) {
        if (auditorium == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (auditorium.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond auditorium.errors, view:'edit'
            return
        }

        auditorium.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'auditorium.label', default: 'Auditorium'), auditorium.id])
                redirect auditorium
            }
            '*'{ respond auditorium, [status: OK] }
        }
    }

    @Transactional
    def delete(Auditorium auditorium) {

        if (auditorium == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        auditorium.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'auditorium.label', default: 'Auditorium'), auditorium.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'auditorium.label', default: 'Auditorium'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
