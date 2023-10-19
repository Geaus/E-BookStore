import React from 'react';
import {Button, Input, Layout, message, Modal, Table} from 'antd';
import {makeOrder, getCart} from "../services/BookService";
import {Link} from "react-router-dom";
import {columns} from "./Orders";
import {CheckSquareTwoTone} from "@ant-design/icons";

let websocket;


// 定义列的配置
const cart_columns = [
    {
        title: 'ID',
        dataIndex: 'id',
        key: 'id',
    },
    {
        title: '购物车详情',
        dataIndex: 'book',
        key: 'book',
        render: (book) => (
            <div>
                <img src={book.image} alt={book.name} width="100" height="100" />
                <p>图书名称:{book.name}</p>
                {book.exist.toString()==='0'&&(
                    <p>该书已经被下架了！</p>
                )}

            </div>
        )
    },
    {
        title: '数量',
        dataIndex: 'num',
        key: 'num',
    }

];


class Cart extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            cart:[],
            modalIsOpen:false,
            orders: "",
        };
    }

    componentDidMount() {
        const callback =  (data) => {
            this.setState({cart:data});
        };

        getCart(callback);


    }

    handleSocket=()=>{

        this.setState({modalIsOpen:true});

        let uid = sessionStorage.getItem('uid');
        let url = "ws://localhost:8080/websocket/"+uid;
        websocket = new WebSocket(url);
        websocket.onopen = ()=> {
            console.log("建立 websocket 连接...");
        };
        websocket.onerror = (event) => {
            console.log("websocket发生错误..." + event + '\n');
            websocket.close();
        }
        websocket.onclose = ()=> {
            console.log("关闭 websocket 连接...");
            this.setState({showOrder:false});

        };
        websocket.onmessage = (event) => {
            console.log(event.data);
            let tmp =JSON.parse(event.data.toString());
            console.log(tmp);
            let tmpArr =[];
            tmpArr.push(tmp);
            this.setState({orders:tmpArr});
            this.setState({showOrder:true});

        };
    }

    handleClear=()=>{

        const callback =  () => {
            this.setState({cart:[]});
        };

       this.handleSocket();

       if(this.state.cart!==[]){
           makeOrder(callback);
           this.setState({cart:[]})
       }

    }
    closeModal = () => {
        this.setState({ modalIsOpen: false });
        websocket.close();
    };


    render() {
        return(

            <Layout>
                <content>
                    <Table style={{paddingTop:'50px'}} columns={cart_columns} dataSource={this.state.cart}/>
                    <Button type="primary"  size={"large"}  ghost onClick={this.handleClear} >清空购物车</Button>

                    <Modal open={this.state.modalIsOpen} onOk={this.closeModal} onCancel={this.closeModal}>
                        <div>
                            <div>
                                <CheckSquareTwoTone style={{ fontSize: '1000%' , paddingLeft:'200px'}}/>
                                <div style={{paddingLeft:'100px' , fontSize:'30px'}}>
                                    <h >订单请求已经收到，请稍等</h>
                                </div>
                            </div>
                            <div>
                                <div style={{paddingLeft:'150px' ,paddingTop:'100px', fontSize:'30px'}}>
                                    <h >订单详细信息</h>
                                </div>
                                <Table style={{paddingTop:'30px'}} columns={columns} dataSource={this.state.orders}/>
                            </div>
                        </div>
                    </Modal>

                </content>
            </Layout>

        );
    }
}
export default Cart;