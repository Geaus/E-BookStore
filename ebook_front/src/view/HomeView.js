import React from 'react';
import {Layout} from 'antd';
import HeaderBar from "../components/HeaderBar";
import SideBar from "../components/SideBar";
import SearchBar from "../components/SearchBar";
import BookCarousel from "../components/BookCarousel";
import BookList from "../components/BookList";
import "../css/Home.css"
import "../css/Header.css"

const { Header, Content } = Layout;

class HomeView extends React.Component{
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

                                 <BookList />

                             </div>
                         </Content>
                     </Layout>
             </Layout>

         );
     }


}

export default HomeView;