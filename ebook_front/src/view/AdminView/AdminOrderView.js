import React from 'react';
import {Layout} from 'antd';

import AdminOrder from "../../components/Admin/AdminOrder";
import HeaderBar from "../../components/HeaderBar";
import SideBar from "../../components/SideBar";

const { Header, Content } = Layout;

class AdminOrderView extends React.Component{
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

                            <AdminOrder/>

                        </div>
                    </Content>
                </Layout>
            </Layout>

        );
    }


}

export default AdminOrderView;