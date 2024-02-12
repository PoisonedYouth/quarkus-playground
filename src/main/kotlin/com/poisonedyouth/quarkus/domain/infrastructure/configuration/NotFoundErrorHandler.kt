import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@Provider
class NotFoundErrorHandler : ExceptionMapper<NotFoundException> {
    private val logger: Logger = LoggerFactory.getLogger(NotFoundErrorHandler::class.java)

    override fun toResponse(exception: NotFoundException): Response {
        logger.error("Request failed with exception.", exception)
        val originalErrorResponse: Response = exception.response
        return Response.fromResponse(originalErrorResponse)
            .entity(exception.message)
            .build()

    }
}