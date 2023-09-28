import React from 'react';
import {Button, Input, Layout, Table} from 'antd';
import HeaderBar from "../components/HeaderBar";
import SideBar from "../components/SideBar";
import "../css/Home.css"
import "../css/Header.css"

import SearchBar from "../components/SearchBar";
import Orders from "../components/Orders";

const { Header, Content } = Layout;

class OrdersView extends React.Component{
    render() {

        return(
            <Layout >
                <Header className="ant-header">
                    <HeaderBar></HeaderBar>
                </Header>
                <Layout  className="ant-layout-has-side">
                    <SideBar/>
                    <Content  className="ant-layout" style={{ padding: '0 50px' }}>
                        <div className="order-content">

                            <Orders/>
                        </div>
                    </Content>
                </Layout>

            </Layout>

        );
    }
    return

}

export default OrdersView;