import React from 'react';
import { Menu,Layout} from 'antd'
import {
    ReadOutlined,
    ShoppingCartOutlined,
    AuditOutlined,
    UserOutlined,
    CarryOutOutlined,
    UserAddOutlined, BarChartOutlined, DotChartOutlined
} from '@ant-design/icons';
import "../css/SiderBar.css"
import {Link} from 'react-router-dom'
const { Sider } = Layout;

class SideBar extends React.Component{
    state = {
        collapsed: false,
        selectedKeys:['1'],
    };

    onCollapse = collapsed => {
        if(collapsed){
        }
        console.log(collapsed);
        this.setState({ collapsed });
    };

    onMenuClick = (e) => {
        this.setState({ selectedKeys: [e.key] });
    };

    render() {

        return (
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

                              onClick={this.onMenuClick}
                        >
                            {sessionStorage.getItem('type').toString()==='0'&&(

                                <Menu.Item key="6"  icon={<UserAddOutlined/>}>
                                    <Link to="/adminUser">
                                        <span style={{ fontSize: '16px' }}>User manage</span>
                                    </Link>
                                </Menu.Item>

                            )
                            }
                            {sessionStorage.getItem('type').toString()==='0'&&(

                                <Menu.Item key="7"  icon={<ReadOutlined/>}>

                                    <Link to="/adminBook">
                                        <span style={{ fontSize: '16px' }}>Book manage</span>
                                    </Link>


                                </Menu.Item>

                            )
                            }
                            {sessionStorage.getItem('type').toString()==='0'&&(

                                <Menu.Item key="8"  icon={<AuditOutlined/>}>

                                    <Link to="/adminOrder">
                                        <span style={{ fontSize: '16px' }}>Order manage</span>
                                    </Link>

                                </Menu.Item>

                            )
                            }
                            {sessionStorage.getItem('type').toString()==='0'&&(

                                <Menu.Item key="9"  icon={<BarChartOutlined/>}>

                                    <Link to="/adminSale">
                                        <span style={{ fontSize: '16px' }}>sale ranking</span>
                                    </Link>

                                </Menu.Item>
                            )
                            }
                            {sessionStorage.getItem('type').toString()==='0'&&(

                                <Menu.Item key="10"  icon={<DotChartOutlined/>}>

                                    <Link to="/adminConsume">
                                        <span style={{ fontSize: '16px' }}>consume ranking</span>
                                    </Link>

                                </Menu.Item>

                            )
                            }

                            <Menu.Item key="1"  icon={<ReadOutlined/>}>
                                <Link to="/home">
                                    <span style={{ fontSize: '16px' }}>Books</span>
                                </Link>
                            </Menu.Item>
                            <Menu.Item key="2" icon={<ShoppingCartOutlined/>}>
                                <Link to="/cart">
                                    <span style={{ fontSize: '16px' }}>Cart</span>
                                </Link>
                            </Menu.Item>
                            <Menu.Item key="3" icon={<AuditOutlined/>}>
                                <Link to="/orders">
                                    <span style={{ fontSize: '16px' }}>Orders</span>
                                </Link>
                            </Menu.Item>

                            <Menu.Item key="4" icon={<CarryOutOutlined/>}>
                                <Link to="/statistic">
                                    <span style={{ fontSize: '16px' }}>Statistic</span>
                                </Link>
                            </Menu.Item>


                            <Menu.Item key="5" icon={<UserOutlined/>}>
                                <Link to="/profile">
                                    <span style={{ fontSize: '16px' }}>Profile</span>
                                </Link>
                            </Menu.Item>

                            {/**/}



                        </Menu>
                    </Sider>
                </div>
            </div>

        );
    }
}

export default SideBar;