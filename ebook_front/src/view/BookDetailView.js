import React from 'react';
import { Layout ,Button} from 'antd';
import HeaderBar from "../components/HeaderBar";
import SideBar from "../components/SideBar";
import BookDetail from "../components/BookDetail";
import { useNavigate } from 'react-router-dom';
import "../css/Home.css"
import "../css/Header.css"

const { Header, Content} = Layout;

function BookDetailView() {

    const navigate = useNavigate();

    const handleClick = () => {
        navigate(-1);
    };


    return(
        <Layout >
            <Header className="ant-header">
                <HeaderBar></HeaderBar>
            </Header>
            <Layout className="ant-layout-has-side">
                <SideBar/>
                <Content className="ant-layout" style={{ padding: '0 50px' }}>
                    <div className="home-content">

                        <BookDetail style={{paddingTop:'50px'}}/>
                        <div className={"foot-wrapper"}>
                        </div>
                    </div>
                </Content>
            </Layout>

        </Layout>

    )


}

export default BookDetailView;