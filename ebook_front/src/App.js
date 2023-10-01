
import BookDetailView from "./view/BookDetailView"
import HomeView from "./view/HomeView"
import LoginView from "./view/LoginView"
import CartView from "./view/CartView";
import OrdersView from "./view/OrdersView";
import ProfileView from "./view/ProfileView";
import {
    Route,  BrowserRouter, Routes
} from "react-router-dom";
import AdminView from "./view/AdminView";
import StatisticView from "./view/StatisticView";
import AdminUserView from "./view/AdminView/AdminUserView";
import AdminBookView from "./view/AdminView/AdminBookView";
import AdminOrderView from "./view/AdminView/AdminOrderView";
import AdminSaleView from "./view/AdminView/AdminSaleView";
import AdminConsumeView from "./view/AdminView/AdminConsumeView";
import MakeOrder from "./components/MakeOrder";
import MakeOrderView from "./view/MakeOrderView";


function App() {
  return (


      <BrowserRouter>
          <Routes>

              <Route path="/" element={<LoginView />} />
              <Route path="/home" element={<HomeView />} />
              <Route path="/bookDetails/:bookId" element={<BookDetailView />} />
              <Route path="/cart" element={<CartView />} />
              <Route path="/orders" element={<OrdersView />} />
              <Route path="/profile" element={<ProfileView />} />
              <Route path="/statistic" element={<StatisticView />} />
              <Route path="/makeOrder" element={<MakeOrderView/>} />

              <Route path="adminUser" element={<AdminUserView/>}/>
              <Route path="adminBook" element={<AdminBookView/>}/>
              <Route path="adminOrder" element={<AdminOrderView/>}/>
              <Route path="adminSale" element={<AdminSaleView/>}/>
              <Route path="adminConsume" element={<AdminConsumeView/>}/>



          </Routes>
      </BrowserRouter>


  );
}

export default App;
