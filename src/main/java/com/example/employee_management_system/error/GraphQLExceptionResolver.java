package com.example.employee_management_system.error;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import jakarta.validation.ConstraintViolationException;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.stream.Collectors;

@Component
public class GraphQLExceptionResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof CustomResponseException customEx) {
            return GraphqlErrorBuilder.newError(env)
                    .message(customEx.getMessage())
                    .errorType(mapErrorType(customEx.getCode()))
                    .build();
        }

        if (ex instanceof MethodArgumentNotValidException validationEx) {
            String message = validationEx.getBindingResult().getFieldErrors().stream()
                    .map(err -> err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.joining(", "));

            return GraphqlErrorBuilder.newError(env)
                    .message(message)
                    .errorType(ErrorType.BAD_REQUEST)
                    .build();
        }

        if (ex instanceof ConstraintViolationException validationEx) {
            String message = validationEx.getConstraintViolations().stream()
                    .map(v -> v.getPropertyPath() + " " + v.getMessage())
                    .collect(Collectors.joining(", "));

            return GraphqlErrorBuilder.newError(env)
                    .message(message)
                    .errorType(ErrorType.BAD_REQUEST)
                    .build();
        }

//        if (ex instanceof AccessDeniedException || ex instanceof AuthenticationCredentialsNotFoundException) {
//            return GraphqlErrorBuilder.newError(env)
//                    .message("You are not allowed to do this")
//                    .errorType(ErrorType.FORBIDDEN)
//                    .build();
//        }

        return null;
    }

    private ErrorType mapErrorType(int code) {
        return switch (code) {
            case 404 -> ErrorType.NOT_FOUND;
            case 401 -> ErrorType.UNAUTHORIZED;
            case 403 -> ErrorType.FORBIDDEN;
            default -> ErrorType.BAD_REQUEST;
        };
    }
}
