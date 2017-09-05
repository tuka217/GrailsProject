package grailsproject

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"home")
        "/teacher/create"(controller:"teacher", action:"create")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
