import React from 'react';
import {Layout} from 'antd';
import HeaderBar from "../components/HeaderBar";
import SideBar from "../components/SideBar";
import "../css/Home.css"
import "../css/Header.css"
import Microservice from "../components/Microservice";

const { Header, Content } = Layout;

class MicroView extends React.Component{
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
                            <Microservice/>
                        </div>
                    </Content>
                </Layout>
            </Layout>

        );
    }


}

export default MicroView;