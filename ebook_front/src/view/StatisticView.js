import React from 'react';
import {Button, Layout, Table} from 'antd';
import HeaderBar from "../components/HeaderBar";
import SideBar from "../components/SideBar";
import "../css/Home.css"
import "../css/Header.css"
import SearchBar from "../components/SearchBar";
import Cart from "../components/Cart";
import Statistic from "../components/Statistic";

const { Header, Content } = Layout;


class StatisticView extends React.Component{
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
                            <Statistic/>
                        </div>
                    </Content>
                </Layout>

            </Layout>

        );
    }

}

export default StatisticView;