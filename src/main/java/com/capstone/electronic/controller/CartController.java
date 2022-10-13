package main.java.com.capstone.electronic.controller;

import com.capstone.electronic.exception.AppException;
import com.capstone.electronic.models.entities.User;
import com.capstone.electronic.payload.request.CartReq;
import com.capstone.electronic.security.jwt.JwtUtils;
import com.capstone.fashionshop.services.cart.ICartService;
import com.capstone.fashionshop.utils.MoneyUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cart")

public class CartController {
    private final ICartService cartService;
    private final JwtUtils jwtUtils;

    @GetMapping(path = "")
    public ResponseEntity<?> getProductFromCart (HttpServletRequest request){
        User user = jwtUtils.getUserFromJWT(jwtUtils.getJwtFromHeader(request));
        if (!user.getId().isBlank())
            return cartService.getProductFromCart(user.getId());
        throw new AppException(HttpStatus.FORBIDDEN.value(), "You don't have permission! Token is invalid");
    }

    @PostMapping(path = "")
    public ResponseEntity<?> addAndUpdateProduct (@RequestBody @Valid CartReq req,
                                                  HttpServletRequest request){
        User user = jwtUtils.getUserFromJWT(jwtUtils.getJwtFromHeader(request));
        if (!user.getId().isBlank())
            return cartService.addAndUpdateProductToCart(user.getId(), req);
        throw new AppException(HttpStatus.FORBIDDEN.value(), "You don't have permission! Token is invalid");
    }

    @DeleteMapping(path = "/{orderItemId}")
    public ResponseEntity<?> deleteProductInCart (@PathVariable("orderItemId") String orderItemId,
                                                  HttpServletRequest request){
        User user = jwtUtils.getUserFromJWT(jwtUtils.getJwtFromHeader(request));
        if (!user.getId().isBlank())
            return cartService.deleteProductFromCart(user.getId(), orderItemId);
        throw new AppException(HttpStatus.FORBIDDEN.value(), "You don't have permission! Token is invalid");
    }
}
