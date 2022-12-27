package by.bntu.fitr.hashtranslatorservice.filter;


import by.bntu.fitr.hashtranslatorservice.constant.CommonConstant;
import by.bntu.fitr.hashtranslatorservice.constant.ExceptionConstant;
import by.bntu.fitr.hashtranslatorservice.exception.PermissionDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
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
        return !path.startsWith("/api/applications/");
    }

    @Autowired
    @Qualifier("handlerExceptionResolver")
    public void setResolver(HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }
}
