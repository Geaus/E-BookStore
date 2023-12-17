package com.example.mainservice.Controller;
import com.example.mainservice.Entity.*;
import com.example.mainservice.Repository.*;
import com.example.mainservice.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
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
    KafkaTemplate<String, String> kafkaTemplate;
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
    @RequestMapping ("/makeOrder")
    public void makeOrder(@RequestParam int uid) {

        kafkaTemplate.send("Order",String.valueOf(uid),"request for making a order");
//        return cartService.makeOrder(uid);

    }
}
