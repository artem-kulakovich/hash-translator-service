package by.bntu.fitr.authenticationservice.filter;

import by.bntu.fitr.authenticationservice.constant.CommonConstant;
import by.bntu.fitr.authenticationservice.constant.ExceptionConstant;
import by.bntu.fitr.authenticationservice.excecption.PermissionDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authenticationHeaderParam = request.getHeader(CommonConstant.AUTHORIZATION_HEADER_NAME);
        if (authenticationHeaderParam == null || authenticationHeaderParam.isEmpty()) {
            resolver.resolveException(request, response, null, new PermissionDeniedException(ExceptionConstant.PERMISSION_DENIED_EXCEPTION_MSG));
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return !path.startsWith("/api/v1/authentication-service/authorized/");
    }

    @Autowired
    @Qualifier("handlerExceptionResolver")
    public void setResolver(HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }
}
