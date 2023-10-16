import React from 'react';
import {Button, Col, Input, List, message, Row, Table} from "antd";
import {CheckOutlined, CheckSquareTwoTone} from "@ant-design/icons";
import {columns} from "./Orders";

let websocket;

class MakeOrder extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
           showOrder:false,
            orders: "",
            websocket:null
        };


    }

    componentDidMount() {

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

    componentWillUnmount() {
        if (this.websocket) {
            this.websocket.close();
        }
    }

    handleChange=(event)=>{


    }

    render(){
        return(

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

        )
    }
}

export default MakeOrder;