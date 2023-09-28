import React from 'react';
import {Button, Layout, Table} from 'antd';
import HeaderBar from "../components/HeaderBar";
import SideBar from "../components/SideBar";
import "../css/Home.css"
import "../css/Header.css"
import SearchBar from "../components/SearchBar";
import Cart from "../components/Cart";

const { Header, Content } = Layout;


class CartView extends React.Component{
    render() {

        return(
            <Layout >
                <Header className="ant-header">
                    <HeaderBar></HeaderBar>
                </Header>
                <Layout className="ant-layout-has-side">
                    <SideBar/>
                    <Content className="ant-layout" style={{ padding: '0 50px' }}>
                        <div className="cart-content">

                           <Cart/>
                        </div>
                    </Content>
                </Layout>

            </Layout>

        );
    }

}

export default CartView;