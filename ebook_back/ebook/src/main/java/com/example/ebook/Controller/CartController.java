package com.example.ebook.Controller;
import com.example.ebook.Dao.CartDao;
import com.example.ebook.Entity.*;
import com.example.ebook.Repository.*;
import com.example.ebook.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin

public class CartController {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    CartService cartService;

   // private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    @RequestMapping ("/getCarts")
    public List<Cart> list(@RequestParam int uid) {
        // your code here

//        return cartRepository.findCartsByUser_Id(uid);
        return cartService.getCart(uid);

    }

    @RequestMapping("/addCart")
    public String addCartById(@RequestParam int uid, @RequestParam int bookid) {

        return cartService.addCart(uid,bookid);

    }
    @RequestMapping ("/clearCarts")
    public int clear(@RequestParam int uid) {

        return cartService.clearCart(uid);

    }
}
