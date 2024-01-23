import React from 'react';
import {Layout} from 'antd';
import HeaderBar from "../components/HeaderBar";
import SideBar from "../components/SideBar";
import "../css/Home.css"
import "../css/Header.css"
import Microservice from "../components/Microservice";
import TypeRelateSearch from "../components/TypeRelateSearch";
import Mr_graphl from "../components/Mr_graphl";

const { Header, Content } = Layout;

class MrView extends React.Component{
    render() {

        return(
            <Layout >
                <Header className="ant-header">
                    <HeaderBar></HeaderBar>
                </Header>
                <Layout className="ant-layout-has-side" >
                    <SideBar/>
                    <Content className="ant-layout" style={{ padding: '0 50px' }}>
                        <div className="home-content">
                            <Mr_graphl/>
                        </div>
                    </Content>
                </Layout>
            </Layout>

        );
    }


}

export default MrView;