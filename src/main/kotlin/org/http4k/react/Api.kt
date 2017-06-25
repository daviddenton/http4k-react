package org.http4k.react

import org.http4k.core.Body
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.with
import org.http4k.format.Jackson.auto
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import java.util.*

data class Employee(val id: String, val firstName: String)

data class EmployeeList(val employees: List<Employee>)

object Api {

    private val employees = Body.auto<EmployeeList>().toLens()

    operator fun invoke(): RoutingHttpHandler = routes(
        "/api/employees" to GET bind {
            Response(OK).with(employees of EmployeeList(listOf(Employee(UUID.randomUUID().toString(), "rita"))))
        }
    )
}