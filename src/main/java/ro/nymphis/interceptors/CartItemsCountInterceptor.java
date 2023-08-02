package ro.nymphis.interceptors;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import ro.nymphis.service.cart.CartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class CartItemsCountInterceptor implements HandlerInterceptor {
    private final CartService cartService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)  {
        if(modelAndView == null) return;
        if(modelAndView.getViewName() == null || modelAndView.getViewName().startsWith(UrlBasedViewResolver.REDIRECT_URL_PREFIX)) return;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) return;

        modelAndView.addObject("cartItemsCount", cartService.getCartItemsCount());
    }
}
