package ro.nymphis.controller.cart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.nymphis.dto.cart.CartDTO;
import ro.nymphis.dto.cart.CartItemRequestDTO;
import ro.nymphis.mapper.Mapper;
import ro.nymphis.model.cart.Cart;
import ro.nymphis.service.cart.CartService;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
@Getter
public class CartController {
    private final CartService cartService;
    private final Mapper<Cart, CartDTO> cartToCartDTOMapper;

    @GetMapping("/details")
    public ModelAndView showCartDetailsPage() {
        Cart cart = getCartService().getCart();
        CartDTO cartDTO = getCartToCartDTOMapper().map(cart);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cartDTO", cartDTO);
        modelAndView.setViewName("cart/cart-details");

        return modelAndView;
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateProductToCart(@RequestBody CartItemRequestDTO cartItemDTO) {
        cartService.updateCart(cartItemDTO);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductToCart(@PathVariable("id") Long id) {
        cartService.removeProduct(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void addProductToCart(@RequestBody final CartItemRequestDTO cartItemRequestDTO) {
        cartService.addProduct(cartItemRequestDTO.getProductId(), cartItemRequestDTO.getQuantity());
    }
}

