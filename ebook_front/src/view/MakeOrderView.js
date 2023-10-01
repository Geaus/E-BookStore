import React from 'react';
import {Layout} from 'antd';
import HeaderBar from "../components/HeaderBar";
import SideBar from "../components/SideBar";
import BookList from "../components/BookList";
import "../css/Home.css"
import "../css/Header.css"
import MakeOrder from "../components/MakeOrder";

const { Header, Content } = Layout;

class MakeOrderView extends React.Component{
    render() {

        return(
            <Layout >
                <Header className="ant-header">
                    <HeaderBar></HeaderBar>
                </Header>
                <Layout className="ant-layout-has-side" >
                    <SideBar/>
                    <Content className="ant-layout" style={{ padding: '0 50px' }}>

                              <MakeOrder/>

                    </Content>
                </Layout>
            </Layout>

        );
    }


}

export default MakeOrderView;