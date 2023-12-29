package rejolut_league.rpl.filters;

import java.io.IOException;

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
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String authHeader = httpRequest.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY)
                        .parseClaimsJws(token).getBody();

                httpRequest.setAttribute("loginId", claims.get("loginId").toString());
                httpRequest.setAttribute("teamId", Integer.parseInt(claims.get("id").toString()));
            } 
            catch (Exception e) {
                httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Invalid/Expired Token");
                return;
            }

        } 
        else {
            httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization Token format is invalid");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse); // pass the request along the filter chainquest, response);
    }

}
