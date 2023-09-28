import React from 'react';
import {Table, DatePicker, Input, Button, message} from 'antd';
import "../css/Home.css"
import "../css/Header.css"
import {getOrders, UserFilterOrderBook, UserFilterOrderDate} from "../services/BookService";
import {SearchOutlined} from "@ant-design/icons";
const { RangePicker } = DatePicker;

const columns = [
    {
        title: '订单ID',
        dataIndex: 'id',
        key: 'id',
    },
    {
        title: '时间',
        dataIndex: 'time',
        key: 'time',
    },
    {
        title: '订单详情',
        dataIndex: 'orderItems',
        key: 'orderItems',
        render: (orderItems) => {
            return (
                <div>
                    {orderItems.length === 0 ? (
                        <div>订单中的书籍均已被删除</div>
                    ) : (
                        orderItems.map((item) => (

                            <div>
                                <div style={{float:'left'}}>
                                    <img src={item.book.image} width="80" height="80"/>
                                </div>
                                <div key={item.id}>
                                    <p>图书名称：{item.book.name}</p>
                                    <p>数量：{item.num}</p>
                                </div>
                                <hr />
                            </div>

                        ))
                    )}
                </div>
            );
        },
    }
];
class Orders extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            orders: [],
            bookName:'',
            startDate:'',
            endDate:''

        };
    }

    componentDidMount() {

        const callback =  (data) => {
            this.setState({orders:data});
        };

        getOrders(callback);

    }

    handleTextChange=(event)=>{
        this.setState({bookName:event.target.value});

    }
    handleTextSearch=(event)=>{


        console.log(this.state.bookName);

        if(this.state.bookName===null){
            return;
        }

        const uid=sessionStorage.getItem('uid');
        const params = new URLSearchParams();
        params.append('uid', uid);
        params.append('bookName', this.state.bookName);

        const callback =  (data) => {
            console.log(data)
            this.setState({orders:data});
        };

        UserFilterOrderBook(params,callback);

    }

    handleDateChange = (date, dateString) => {

        this.setState({startDate:dateString[0]});
        this.setState({endDate:dateString[1]});
        console.log(dateString[0]);
        console.log(dateString[1]);
    }

    handleDateSearch=()=>{

        console.log(this.state.startDate)
        console.log(this.state.endDate)

        if(this.state.startDate===null || this.state.endDate===null){
            return;
        }

        const uid=sessionStorage.getItem('uid');
        const params = new URLSearchParams();
        params.append('uid', uid);
        params.append('start', this.state.startDate);
        params.append('end', this.state.endDate);


        const callback =  (data) => {
            console.log(data)
            this.setState({orders:data});
        };

        UserFilterOrderDate(params,callback);

        // fetch('http://localhost:8080/UserFilterOrderDate?'+params.toString())
        //     .then(response => response.json())
        //     .then((data) => {
        //         console.log(data)
        //          this.setState({orders:data});
        //     })

    }
    render() {

        return(
            <div>
                <br/>
                <RangePicker
                    style={{width:'490px'}}
                    size="large" onChange={this.handleDateChange}

                />
                <Button size="large"  type="primary" icon={<SearchOutlined/>}
                        onClick={this.handleDateSearch}
                ></Button>
                <br/>
                <br/>
                <Input
                    suffix={
                        <Button
                            className="search-btn"
                            style={{ marginRight: -12 }}
                            size="large"
                            type="primary"
                            onClick={this.handleTextSearch}
                            icon={<SearchOutlined/>}
                        >
                        </Button>
                    }
                    value={this.state.bookName}
                    onChange={this.handleTextChange}

                    style={{width:'530px',height:'40px'}}

                />

                <Table style={{paddingTop:'30px'}} columns={columns} dataSource={this.state.orders}
                       pagination={{
                           onChange: page => {
                               console.log(page);
                           },
                           pageSize: 3,
                       }}/>
                />

            </div>

        )
    }
}

export default Orders;