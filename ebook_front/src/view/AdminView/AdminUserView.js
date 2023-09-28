import React from 'react';
import {Layout} from 'antd';

import AdminUser from "../../components/Admin/AdminUser";
import HeaderBar from "../../components/HeaderBar";
import SideBar from "../../components/SideBar";

const { Header, Content } = Layout;

class AdminUserView extends React.Component{
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

                            <AdminUser/>

                        </div>
                    </Content>
                </Layout>
            </Layout>

        );
    }


}

export default AdminUserView;