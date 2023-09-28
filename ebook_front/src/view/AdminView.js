import React from 'react';
import {Layout, Menu} from 'antd';
import AdminUser from "../components/Admin/AdminUser";

import {
    AudioMutedOutlined, AudioOutlined,
    BarChartOutlined, CarryOutOutlined,
    DotChartOutlined,
    ReadOutlined,
    ShoppingCartOutlined,
    UserAddOutlined, UserOutlined
} from "@ant-design/icons";
import Sider from "antd/es/layout/Sider";
import AdminBook from "../components/Admin/AdminBook";
import AdminSale from "../components/Admin/AdminSale";
import AdminConsume from "../components/Admin/AdminConsume";
import BookList from "../components/BookList";
import Cart from "../components/Cart";
import Orders from "../components/Orders";
import Statistic from "../components/Statistic";
import User from "../components/User";
import HeaderBar from "../components/HeaderBar";

const { Header, Content } = Layout;

class AdminView extends React.Component{
    state = {
        collapsed: false,
        selectedKeys:'1',
    };

    onCollapse = collapsed => {
        if(collapsed){
        }
        console.log(collapsed);
        this.setState({ collapsed });
    };

    onMenuClick = (e) => {
        this.setState({ selectedKeys: e.key });
    };
    render() {

        return(
            <Layout >
                <Header className="ant-header">
                    <HeaderBar></HeaderBar>
                </Header>
                <Layout className="ant-layout-has-side" >
                    <div style={{width:this.state.collapsed? "80px":"180px",
                        maxWidth:this.state.collapsed? "80px":"180px",
                        minWidth:this.state.collapsed? "80px":"180px" }}

                    >
                        <div className="mySide">
                            <Sider collapsible
                                   bodered
                                   collapsed={this.state.collapsed}
                                   width="180px" onCollapse={this.onCollapse}
                                   className="side"
                                   style={{ background: '#fff'}}

                            >
                                <Menu className={"layout"}  mode="inline"
                                      default

                                      onClick={this.onMenuClick}
                                >
                                    <Menu.Item key="5"  icon={<UserAddOutlined/>}>

                                        <span style={{ fontSize: '16px' }}>User manage</span>

                                    </Menu.Item>

                                    <Menu.Item key="2"  icon={<ReadOutlined/>}>

                                        <span style={{ fontSize: '16px' }}>Book manage</span>

                                    </Menu.Item>

                                    <Menu.Item key="3"  icon={<BarChartOutlined/>}>

                                        <span style={{ fontSize: '16px' }}>sale ranking</span>

                                    </Menu.Item>

                                    <Menu.Item key="4"  icon={<DotChartOutlined/>}>

                                        <span style={{ fontSize: '16px' }}>consume ranking</span>

                                    </Menu.Item>



                                    <Menu.Item key="1"  icon={<ReadOutlined/>}>

                                        <span style={{ fontSize: '16px' }}>Book</span>

                                    </Menu.Item>
                                    <Menu.Item key="6"  icon={<ShoppingCartOutlined/>}>

                                        <span style={{ fontSize: '16px' }}>Cart</span>

                                    </Menu.Item>

                                    <Menu.Item key="7"  icon={<AudioOutlined/>}>

                                        <span style={{ fontSize: '16px' }}>Order</span>

                                    </Menu.Item>

                                    <Menu.Item key="8"  icon={<CarryOutOutlined/>}>

                                        <span style={{ fontSize: '16px' }}>Statistic</span>

                                    </Menu.Item>

                                    <Menu.Item key="9"  icon={<UserOutlined/>}>

                                    <span style={{ fontSize: '16px' }}>Profile</span>

                                    </Menu.Item>



                                </Menu>
                            </Sider>
                        </div>
                    </div>
                    <Content className="ant-layout" style={{ padding: '0 50px' }}>
                        <div className="home-content">

                            {this.state.selectedKeys === '5' && (
                                <AdminUser/>
                            )}
                            {this.state.selectedKeys === '2' && (
                                <AdminBook/>
                            )}
                            {this.state.selectedKeys === '3' && (
                                <AdminSale/>
                            )}
                            {this.state.selectedKeys === '4' && (
                                <AdminConsume/>
                            )}
                            {this.state.selectedKeys === '1' && (
                                <BookList/>
                            )}
                            {this.state.selectedKeys === '6' && (
                                <Cart/>
                            )}
                            {this.state.selectedKeys === '7' && (
                                <Orders/>
                            )}
                            {this.state.selectedKeys === '8' && (
                                <Statistic/>
                            )}
                            {this.state.selectedKeys === '9' && (
                                <User/>
                            )}


                        </div>
                    </Content>
                </Layout>
            </Layout>

        );
    }


}

export default AdminView;