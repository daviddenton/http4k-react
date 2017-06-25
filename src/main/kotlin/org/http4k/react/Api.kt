package org.http4k.react

import org.http4k.core.Body
import org.http4k.core.Method.DELETE
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Response
import org.http4k.core.Status.Companion.ACCEPTED
import org.http4k.core.Status.Companion.CREATED
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.core.with
import org.http4k.format.Jackson.auto
import org.http4k.lens.Path
import org.http4k.lens.uuid
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import java.util.*

data class Employee(val id: String?, val firstName: String)

class Employees {
    private val all = mutableListOf<Employee>(Employee(UUID.randomUUID().toString(), "Bob"))

    fun list() = all.toList()

    operator fun get(id: UUID): Employee? = all.first { it.id == id.toString() }

    fun add(new: Employee): Employee {
        val withId = new.copy(id = UUID.randomUUID().toString())
        all.add(withId)
        return withId
    }

    fun delete(toRemove: UUID): Employee? = this[toRemove]?.let { all.remove(it); it }
}

object Api {

    private val employeeBody = Body.auto<Employee>().toLens()
    private val listEmployeesBody = Body.auto<List<Employee>>().toLens()

    private val id = Path.uuid().of("id")

    operator fun invoke(employees: Employees): RoutingHttpHandler = routes(
        "/employee" to GET bind {
            Response(OK).with(listEmployeesBody of employees.list())
        },
        "/employee/{id}" to GET bind {
            employees[id.extract(it)]
                ?.let {
                    Response(OK).with(employeeBody of it)
                } ?: Response(NOT_FOUND)
        },
        "/employee/{id}" to DELETE bind {
            employees.delete(id.extract(it))
                ?.let {
                    Response(ACCEPTED).with(employeeBody of it)
                } ?: Response(NOT_FOUND)
        },
        "/employee" to POST bind {
            Response(CREATED).with(employeeBody of employees.add(employeeBody.extract(it)))
        }
    )
}