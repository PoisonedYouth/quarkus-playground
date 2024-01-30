package com.poisonedyouth.quarkus.domain.infrastructure.adapter.input

import com.poisonedyouth.quarkus.domain.application.usecases.UserUsecase
import com.poisonedyouth.quarkus.domain.vo.LongIdentity
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.Response
import org.jboss.resteasy.reactive.RestResponse.StatusCode.NOT_FOUND

@Path("/user")
class UserController(
    private val userUsecase: UserUsecase
) {

    @GET
    fun getUser(@QueryParam("userId") userId: Long): Response {
        val user = userUsecase.find(LongIdentity(userId))?.toUserDto()
        return if (user != null) {
            Response.ok(user).build()
        } else {
            Response.status(NOT_FOUND, "User with id '$userId' does not exist.").build()
        }
    }

    @POST
    fun addUser(user: UserDto): Response {
        val id = userUsecase.addNew(user.toUser())
        return Response.ok(id).build()
    }
}