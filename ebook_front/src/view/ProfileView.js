import React, { useState } from "react";
import { Avatar, Form, Input, Button, message,Layout,Space,Card} from "antd";
import HeaderBar from "../components/HeaderBar";
import SideBar from "../components/SideBar";
import "../css/Home.css"
import "../css/Header.css"
import TextArea from "antd/es/input/TextArea";
import User from "../components/User";
import {getUser} from "../services/UserService";


const { Header, Content } = Layout;


class ProfileView extends React.Component {


    render(){
        return (

            <Layout >
                <Header className="ant-header">
                    <HeaderBar></HeaderBar>
                </Header>
                <Layout  className="ant-layout-has-side">
                    <SideBar/>

                    <Content className="ant-layout" style={{ padding: '0 50px' }}>
                        <User/>
                    </Content>
                </Layout>

            </Layout>

        );
    }

};

export default ProfileView;