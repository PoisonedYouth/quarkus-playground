package com.poisonedyouth.quarkus.domain.infrastructure.adapter.input

import com.poisonedyouth.quarkus.domain.application.usecases.UserUsecase
import com.poisonedyouth.quarkus.domain.vo.LongIdentity
import io.micrometer.core.annotation.Counted
import io.micrometer.core.annotation.Timed
import jakarta.ws.rs.GET
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.Response
import org.jboss.resteasy.reactive.RestResponse.StatusCode.NOT_FOUND
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Path("/user")
class UserController(
    private val userUsecase: UserUsecase,
) {
    private val logger: Logger = LoggerFactory.getLogger(UserController::class.java)

    @GET
    @Timed("user.get")
    @Counted("user.get.count")
    fun getUser(@QueryParam("userId") userId: Long): Response {
        logger.info("Searching for user with id '$userId'...")
        val user = userUsecase.find(LongIdentity(userId))?.toUserDto()
        return if (user != null) {
            Response.ok(user).build()
        } else {
            throw NotFoundException("User with id '$userId' does not exist.")
        }
    }

    @POST
    @Timed("user.add")
    @Counted("user.add.count")
    fun addUser(user: UserDto): Response {
        logger.info("Adding new user '$user'...")
        val id = userUsecase.addNew(user.toUser())
        return Response.ok(id).build()
    }
}