package rejolut_league.rpl.filters;

import java.io.IOException;
import java.util.Enumeration;

import rejolut_league.rpl.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
    throws IOException, ServletException {
        System.out.println("Hello World---------------------------------");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        System.out.println("request set");
        String authHeader = httpRequest.getHeader("authorization");
        System.out.println("What bro??");
        System.out.println(authHeader);

        // Print out all headers
        Enumeration<String> headerNames = httpRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            Enumeration<String> headerValues = httpRequest.getHeaders(headerName);
            while (headerValues.hasMoreElements()) {
                String headerValue = headerValues.nextElement();
                System.out.println(headerName + ": " + headerValue);
            }
        }

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                System.out.println("trying for token");
                Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY)
                        .parseClaimsJws(token).getBody();

                httpRequest.setAttribute("loginId", claims.get("loginId").toString());
                httpRequest.setAttribute("teamId", Integer.parseInt(claims.get("id").toString()));
            } 
            catch (Exception e) {
                System.out.println("throwing error");
                httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Invalid/Expired Token");
                return;
            }

        } 
        else {
            System.out.println("Authorization Token format is invalid");
            // httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization Token format is invalid");
            // return;
        }
        System.out.println("filter chain");
        filterChain.doFilter(servletRequest, servletResponse); // pass the request along the filter chainquest, response);
    }
    
}
